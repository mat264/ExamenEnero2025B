package ljDataAccess;

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

    // SQL Only

    private static String getConnectionString() {
        String dbUrl = ljAppConfig.getPropiedad("DBUrl");
        if (dbUrl != null && dbUrl.startsWith("jdbc:sqlite:")) {
            String relativePath = dbUrl.substring("jdbc:sqlite:".length());
            String absPath = ljAppConfig.resolvePath(relativePath);
            // Ensure we don't double slash if not needed, but absPath is safe.
            return "jdbc:sqlite:" + absPath;
        }
        if (dbUrl == null) {
            return "jdbc:sqlite:" + ljAppConfig.resolvePath("storage/DataFiles/ExoTrooper.db");
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
        } catch (SQLException e) {
            System.err.println("Error Loading Exobots (SQL): " + e.getMessage());
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
        } catch (SQLException e) {
            System.err.println("Error Saving Exobots (SQL): " + e.getMessage());
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

}
