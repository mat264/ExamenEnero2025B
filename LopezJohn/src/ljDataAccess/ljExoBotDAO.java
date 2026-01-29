package ljDataAccess;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ljBusinessLogic.ljExoBot;
import ljBusinessLogic.ljExoMedico;
import ljInfrastructure.ljAppConfig;

public class ljExoBotDAO {

    // Path for CSV fallback
    private static final String CSV_PATH = "storage/DataFiles/ExoBot.csv";

    private static String getConnectionString() {
        String dbUrl = ljAppConfig.getPropiedad("DBUrl");
        if (dbUrl == null) {
            return "jdbc:sqlite:storage/DataFiles/ExoTrooper.db";
        }
        return dbUrl;
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(getConnectionString());
    }

    public static List<ljExoBot> loadAll() {
        List<ljExoBot> lista = new ArrayList<>();
        String sql = "SELECT IdExobot, TipoExobot, Entreno, NumeroAccion FROM ExoBot";

        try (Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                ljExoBot bot;
                String tipo = rs.getString("TipoExobot");

                if ("ExoMedico".equalsIgnoreCase(tipo)) {
                    bot = new ljExoMedico();
                } else {
                    bot = new ljExoMedico();
                    bot.setTipoExobot(tipo);
                }

                bot.setIdExobot(rs.getInt("IdExobot"));
                bot.setEntreno(rs.getString("Entreno"));
                bot.setNumeroAccion(rs.getInt("NumeroAccion"));
                lista.add(bot);
            }
        } catch (Exception e) {
            // Log briefly that we are using CSV
            System.out.println("LOG: SQL Database unavailable. Switching to CSV storage mode.");
            return loadFromCSV();
        }
        return lista;
    }

    public static void saveAll(List<ljExoBot> lista) {
        String sqlDelete = "DELETE FROM ExoBot";
        String sqlInsert = "INSERT INTO ExoBot(IdExobot, TipoExobot, Entreno, NumeroAccion) VALUES(?, ?, ?, ?)";

        try (Connection conn = getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement stmtDelete = conn.prepareStatement(sqlDelete)) {
                stmtDelete.executeUpdate();
            }

            try (PreparedStatement stmtInsert = conn.prepareStatement(sqlInsert)) {
                for (ljExoBot bot : lista) {
                    stmtInsert.setInt(1, bot.getIdExobot());
                    stmtInsert.setString(2, bot.getTipoExobot());
                    stmtInsert.setString(3, bot.getEntreno());
                    stmtInsert.setInt(4, bot.getNumeroAccion());
                    stmtInsert.addBatch();
                }
                stmtInsert.executeBatch();
            }

            conn.commit();
        } catch (Exception e) {
            // Silent fallback for save
            saveToCSV(lista);
        }
    }

    public static void inicializarBD() {
        String sqlCreateTable = "CREATE TABLE IF NOT EXISTS ExoBot (\n"
                + "    IdExobot INTEGER PRIMARY KEY,\n"
                + "    TipoExobot TEXT NOT NULL,\n"
                + "    Entreno TEXT NOT NULL,\n"
                + "    NumeroAccion INTEGER NOT NULL\n"
                + ");";

        try (Connection conn = getConnection();
                java.sql.Statement stmt = conn.createStatement()) {
            stmt.execute(sqlCreateTable);
        } catch (Exception e) {
            // Suppress SQL Init error for cleaner startup
        }
    }

    // --- CSV Fallback Implementation ---

    private static void saveToCSV(List<ljExoBot> lista) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CSV_PATH))) {
            for (ljExoBot bot : lista) {
                // Format: Id,Tipo,Entreno,Accion
                String line = String.format("%d,%s,%s,%d",
                        bot.getIdExobot(),
                        bot.getTipoExobot(),
                        bot.getEntreno(),
                        bot.getNumeroAccion());
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving to CSV: " + e.getMessage());
        }
    }

    private static List<ljExoBot> loadFromCSV() {
        List<ljExoBot> lista = new ArrayList<>();
        java.io.File csvFile = new java.io.File(CSV_PATH);

        if (!csvFile.exists()) {
            return lista; // Normal first run condition, no error needed.
        }

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    ljExoBot bot = new ljExoMedico();
                    bot.setIdExobot(Integer.parseInt(parts[0]));
                    bot.setTipoExobot(parts[1]);
                    bot.setEntreno(parts[2]);
                    bot.setNumeroAccion(Integer.parseInt(parts[3]));
                    lista.add(bot);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading CSV: " + e.getMessage());
        }
        return lista;
    }
}
