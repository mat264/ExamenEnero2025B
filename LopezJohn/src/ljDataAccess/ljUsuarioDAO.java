package ljDataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import ljBusinessLogic.ljUsuario;

public class ljUsuarioDAO {

    // SQL Only

    public static void crearTablaUsuario() {
        String sqlCreateTable = "CREATE TABLE IF NOT EXISTS Usuario (\n"
                + "    Cedula TEXT PRIMARY KEY,\n"
                + "    Nombre TEXT NOT NULL,\n"
                + "    Clave TEXT NOT NULL\n"
                + ");";

        try (Connection conn = ljExoBotDAO.getConnection();
                java.sql.Statement stmt = conn.createStatement()) {
            stmt.execute(sqlCreateTable);
        } catch (Exception e) {
            System.err.println("Error initializing User Table (SQL): " + e.getMessage());
        }
    }

    public static List<ljUsuario> loadAll() {
        List<ljUsuario> lista = new ArrayList<>();
        String sql = "SELECT Cedula, Nombre, Clave FROM Usuario";

        try (Connection conn = ljExoBotDAO.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                ljUsuario u = new ljUsuario();
                u.setCedula(rs.getString("Cedula"));
                u.setNombre(rs.getString("Nombre"));
                u.setClave(rs.getString("Clave"));
                lista.add(u);
            }
        } catch (Exception e) {
            System.err.println("Error Loading Users (SQL): " + e.getMessage());
        }
        return lista;
    }

    public static void save(ljUsuario u) {
        // Simple UPSERT or Insert if not exists might be better,
        // but for now let's just use append behavior for CSV and Insert for SQL.
        // Actually, easiest to maintain list consistency is load -> check -> save logic
        // in business,
        // but here we just want to ensure the INITIAL user exists.

        String sqlInsert = "INSERT INTO Usuario(Cedula, Nombre, Clave) VALUES(?, ?, ?)";
        try (Connection conn = ljExoBotDAO.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sqlInsert)) {

            pstmt.setString(1, u.getCedula());
            pstmt.setString(2, u.getNombre());
            pstmt.setString(3, u.getClave());
            pstmt.executeUpdate();

        } catch (Exception e) {
            System.err.println("Error Saving User (SQL): " + e.getMessage());
        }
    }

    // Initializes DB with default users if empty
    public static void inicializarUsuarios() {
        List<ljUsuario> usuarios = loadAll();
        if (usuarios.isEmpty()) {
            ljUsuario u1 = new ljUsuario("patmic", "Pat", "123");
            ljUsuario u2 = new ljUsuario("1751330158", "Lopez John", "321");
            save(u1);
            save(u2);
            System.out.println("Usuarios predeterminados creados.");
        }
    }

    public static boolean validarUsuario(String cedula, String clave) {
        for (ljUsuario u : loadAll()) {
            if (u.getCedula().equals(cedula) && u.getClave().equals(clave)) {
                return true;
            }
        }
        return false;
    }
}
