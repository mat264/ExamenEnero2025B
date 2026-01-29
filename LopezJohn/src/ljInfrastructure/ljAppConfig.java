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
        // 1. Intentar cargar desde "src/app.properties" (Ubicación estándar solicitada)
        try (java.io.InputStream entrada = new FileInputStream("src/app.properties")) {
            propiedades.load(entrada);
            return true;
        } catch (IOException e) {
            // 2. Intentar cargar desde el directorio actual (Legacy / Producción)
            try (java.io.InputStream entrada = new FileInputStream("app.properties")) {
                propiedades.load(entrada);
                return true;
            } catch (IOException e2) {
                // 3. Intentar cargar desde "../src/app.properties" (Si se corre desde bin)
                try (java.io.InputStream entrada = new FileInputStream("../src/app.properties")) {
                    propiedades.load(entrada);
                    return true;
                } catch (IOException e3) {
                    // 4. Intentar cargar desde el directorio padre (Si se corre desde bin y el
                    // archivo esta en root)
                    try (java.io.InputStream entrada = new FileInputStream("../app.properties")) {
                        propiedades.load(entrada);
                        return true;
                    } catch (IOException e4) {
                        // 5. Intentar cargar desde el classpath (jar)
                        try (java.io.InputStream entrada = ljAppConfig.class.getClassLoader()
                                .getResourceAsStream("app.properties")) {
                            if (entrada != null) {
                                propiedades.load(entrada);
                                return true;
                            }
                        } catch (Exception e5) {
                            // Fallo total
                        }
                    }
                }
            }
        }
        System.err.println("Error grave: No se pudo encontrar app.properties en src/, ./, ../src/, ../, o classpath.");
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
