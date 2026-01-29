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
    private static String projectRoot = ".";

    /**
     * Carga las propiedades desde el archivo de configuración.
     * Searches for app.properties in: ., src/, ../
     * 
     * @return true si la carga fue exitosa, false de lo contrario.
     */
    public static boolean cargarConfiguracion() {
        // 1. Try current directory
        try (FileInputStream entrada = new FileInputStream("app.properties")) {
            propiedades.load(entrada);
            projectRoot = ".";
            return true;
        } catch (IOException e) {
            // 2. Try parent directory (e.g. running from src/ or bin/)
            try (FileInputStream entradaParent = new FileInputStream("../app.properties")) {
                propiedades.load(entradaParent);
                projectRoot = "..";
                return true;
            } catch (IOException e2) {
                // 3. Try src directory (Dev environment, running from root but config in src -
                // rare now)
                try (FileInputStream entradaSrc = new FileInputStream("src/app.properties")) {
                    propiedades.load(entradaSrc);
                    projectRoot = ".";
                    return true;
                } catch (IOException e3) {
                    // 4. Try ClassLoader
                    try (java.io.InputStream entradaStream = ljAppConfig.class.getClassLoader()
                            .getResourceAsStream("app.properties")) {
                        if (entradaStream != null) {
                            propiedades.load(entradaStream);
                            projectRoot = "."; // Assume classpath execution implies standard relative paths or jarring
                            return true;
                        }
                    } catch (IOException e4) {
                    }
                }
            }
            System.err.println("Error: No se pudo encontrar app.properties.");
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

    /**
     * Resolves a path property relative to the working directory.
     * Ensures paths are correctly formatted for the OS.
     * 
     * @param clave The property key containing the path.
     * @return The absolute path or the original value if not found.
     */
    /**
     * Resolves a relative path against the detected project root.
     * 
     * @param relativePath The path to resolve.
     * @return The absolute path.
     */
    public static String resolvePath(String relativePath) {
        if (relativePath == null)
            return null;
        java.io.File file = new java.io.File(projectRoot, relativePath);
        return file.getAbsolutePath();
    }

    /**
     * Resolves a path property relative to the working directory.
     * Ensures paths are correctly formatted for the OS.
     * 
     * @param clave The property key containing the path.
     * @return The absolute path or the original value if not found.
     */
    public static String getRutaPropiedad(String clave) {
        String valor = getPropiedad(clave);
        return resolvePath(valor);
    }

    /**
     * Checks if the critical folders (like storage) exist relative to the detected
     * root.
     * 
     * @return true if the environment is valid.
     */
    public static boolean validarIntegridad() {
        java.io.File storageDir = new java.io.File(projectRoot, "storage");
        if (!storageDir.exists() || !storageDir.isDirectory()) {
            System.err.println("ALERTA CRITICA: No se encuentra la carpeta 'storage'.");
            System.err.println("Ubicación esperada: " + storageDir.getAbsolutePath());
            System.err.println("Asegurese de que la carpeta 'storage' esté en el mismo nivel que 'app.properties'.");
            return false;
        }
        return true;
    }
}
