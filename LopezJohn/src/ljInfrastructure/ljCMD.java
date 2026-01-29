package ljInfrastructure;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Utilidad para salida en consola y tracer.
 */
public class ljCMD {

    /**
     * Imprime un mensaje en consola y lo guarda en el tracer.
     * 
     * @param mensaje El mensaje a registrar.
     */
    public static void escribir(String mensaje) {
        System.out.println(mensaje);
        guardarEnTracer(mensaje);
    }

    /**
     * Guarda el mensaje en el archivo ExoTracer.txt
     * 
     * @param mensaje El mensaje a guardar.
     */
    private static void guardarEnTracer(String mensaje) {
        String tracerPath = ljAppConfig.getRutaPropiedad("PathTracer");
        if (tracerPath == null)
            tracerPath = "storage/DataFiles/ExoTracer.txt";

        try (FileWriter fw = new FileWriter(tracerPath, true);
                PrintWriter pw = new PrintWriter(fw)) {
            pw.println(mensaje);
        } catch (IOException e) {
            System.err.println("Error escribiendo en tracer: " + e.getMessage());
        }
    }
}
