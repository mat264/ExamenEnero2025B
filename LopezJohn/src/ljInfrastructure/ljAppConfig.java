package ljInfrastructure;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Clase para manejar la configuración de la aplicación.
 * Lee el archivo app.properties.
 */
public class ljAppConfig {
    private static Properties propiedades = new Properties();

    /**
     * Carga las propiedades desde el archivo de configuración.
     * 
     * @return true si la carga fue exitosa, false de lo contrario.
     */
    public static boolean cargarConfiguracion() {
        try (FileInputStream entrada = new FileInputStream("app.properties")) {
            propiedades.load(entrada);
            return true;
        } catch (IOException e) {
            System.err.println("Error al cargar app.properties: " + e.getMessage());
            return false;
        }
    }

    /**
     * Obtiene una propiedad por su clave.
     * 
     * @param clave La clave de la propiedad.
     * @return El valor de la propiedad o null si no existe.
     */
    public static String getPropiedad(String clave) {
        return propiedades.getProperty(clave);
    }
}
