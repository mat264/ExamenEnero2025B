package ljInfrastructure;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase para manejar las excepciones de la aplicación y registrar errores.
 */
public class ljAppException extends Exception {

    public ljAppException(String mensaje, Throwable causa) {
        super(mensaje, causa);
        escribirLog(mensaje, causa);
    }

    public ljAppException(String mensaje) {
        super(mensaje);
        escribirLog(mensaje, null);
    }

    /**
     * Escribe el error en un archivo de log.
     * 
     * @param mensaje Mensaje de error.
     * @param e       Excepción original (opcional).
     */
    private void escribirLog(String mensaje, Throwable e) {
        String logPath = ljAppConfig.getRutaPropiedad("PathLog");
        if (logPath == null)
            logPath = "storage/Logs/AppErrors.log"; // Default fallback

        // Asegurar que el directorio existe (simple check)
        java.io.File logFile = new java.io.File(logPath);
        if (logFile.isDirectory()) {
            // If config points to dir, append filename
            logFile = new java.io.File(logFile, "AppErrors.log");
        } else if (logFile.getParentFile() != null && !logFile.getParentFile().exists()) {
            logFile.getParentFile().mkdirs();
        }

        try (FileWriter fw = new FileWriter(logFile, true);
                PrintWriter pw = new PrintWriter(fw)) {

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            pw.println("[" + dtf.format(LocalDateTime.now()) + "] ERROR: " + mensaje);
            if (e != null) {
                e.printStackTrace(pw);
            }
            pw.println("--------------------------------------------------");
        } catch (IOException ioException) {
            System.err.println("No se pudo escribir en el log de errores: " + ioException.getMessage());
        }
    }
}
