package ljApp;

import ljConsoleApp.ljConsoleApp;
import ljInfrastructure.ljAppConfig;
import ljInfrastructure.ljAppException;
import javax.swing.SwingUtilities;

/**
 * Clase principal de arranque de la aplicación.
 */
public class ljApp {

    public static void main(String[] args) {
        // Cargar configuración
        if (!ljAppConfig.cargarConfiguracion()) {
            System.err.println("No se pudo cargar la configuración. Saliendo.");
            return;
        }

        // Validar estructura de carpetas
        if (!ljAppConfig.validarIntegridad()) {
            System.err.println("Error de integridad: La estructura de carpetas (storage/) es incorrecta.");
            return;
        }

        try {
            // Iniciar Login por consola
            ljConsoleApp consoleApp = new ljConsoleApp();
            if (consoleApp.login()) {
                // Si login ok, lanzar GUI
                SwingUtilities.invokeLater(() -> {
                    try {
                        new ljExoTrooperGUI().setVisible(true);
                    } catch (Exception e) {
                        new ljAppException("Error al iniciar la GUI", e);
                    }
                });
            } else {
                System.exit(0);
            }
        } catch (Exception e) {
            new ljAppException("Error fatal en la aplicación", e);
        }
    }
}
