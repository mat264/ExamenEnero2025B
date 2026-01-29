package ljDataAccess;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import ljInfrastructure.ljAppConfig;

/**
 * Helper para acceso a datos.
 */
public class ljDataHelper {

    /**
     * Busca la munición o energía correspondiente a un tipo de arma.
     * Lee el archivo ExoMunision.txt
     * 
     * @param tipoArma El tipo de arma a buscar.
     * @return La munición/energía correspondiente o "Desconocido".
     */
    public static String getMunicion(String tipoArma) {
        String path = ljAppConfig.getPropiedad("PathMunision");
        if (path == null)
            path = "storage/DataFiles/ExoMunision.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Formato esperado: Separado por comas
                String[] partes = linea.split(",");
                for (String parte : partes) {
                    parte = parte.trim();
                    // Buscamos si el token contiene el nombre del arma (ignorando
                    // mayúsculas/minúsculas)
                    if (!parte.isEmpty() && parte.toLowerCase().contains(tipoArma.toLowerCase())) {
                        return parte;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error leyendo ExoMunision.txt: " + e.getMessage());
        }
        return "Desconocido";
    }
}
