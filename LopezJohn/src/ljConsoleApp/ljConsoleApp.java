package ljConsoleApp;

import java.util.Scanner;
import ljInfrastructure.ljAppConfig;
import ljInfrastructure.ljCMD;
import ljInfrastructure.ljAppMSG;

/**
 * Clase que maneja la interacción por consola (Login).
 */
public class ljConsoleApp {

    /**
     * Inicia el proceso de login.
     * 
     * @return true si el login es exitoso.
     */
    public boolean login() {
        // Ensure default users exist
        ljDataAccess.ljUsuarioDAO.crearTablaUsuario(); // Create table first!
        ljDataAccess.ljUsuarioDAO.inicializarUsuarios();

        Scanner scanner = new Scanner(System.in);
        int maxIntentos = Integer.parseInt(ljAppConfig.getPropiedad("MaxIntentos"));
        int intentos = 0;

        System.out.println("..:: ExoTrooper Login ::..");

        while (intentos < maxIntentos) {
            System.out.print("Cédula/Usuario: ");
            String inputUser = scanner.nextLine();

            System.out.print("Clave: ");
            String inputPass = scanner.nextLine();

            if (ljDataAccess.ljUsuarioDAO.validarUsuario(inputUser, inputPass)) {
                ljCMD.escribir(ljAppMSG.ACCESO_CONCEDIDO);
                return true;
            } else {
                ljCMD.escribir(ljAppMSG.ACCESO_DENEGADO);
                System.out.println("Credenciales incorrectas. Intentos restantes: " + (maxIntentos - intentos - 1));
                intentos++;
            }
        }

        System.out.println("Has excedido el número de intentos.");
        return false;
    }
}
