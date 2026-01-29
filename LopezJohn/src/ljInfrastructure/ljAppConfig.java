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
        // Intentar cargar desde el directorio actual
        try (java.io.InputStream entrada = new FileInputStream("app.properties")) {
            propiedades.load(entrada);
            return true;
        } catch (IOException e) {
            // Intentar cargar desde src (entorno de desarrollo)
            try (java.io.InputStream entrada = new FileInputStream("src/app.properties")) {
                propiedades.load(entrada);
                return true;
            } catch (IOException e2) {
                // Intentar cargar desde el directorio padre (útil si se corre desde bin)
                try (java.io.InputStream entrada = new FileInputStream("../app.properties")) {
                    propiedades.load(entrada);
                    return true;
                } catch (IOException e3) {
                    // Intentar cargar desde el classpath (jar o bin)
                    try (java.io.InputStream entrada = ljAppConfig.class.getClassLoader()
                            .getResourceAsStream("app.properties")) {
                        if (entrada != null) {
                            propiedades.load(entrada);
                            return true;
                        }
                    } catch (IOException e4) {
                        // Fallo total
                    }
                }
            }
        }
        System.err.println("Error grave: No se pudo encontrar app.properties en: ./, ./src/, ../, o classpath.");
        return false;
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
