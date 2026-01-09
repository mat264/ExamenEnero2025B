import java.util.Arrays;
import java.util.Scanner;

import lopezJohnDataAccess.lopezJohnEstadoCivilDAO;
import lopezJohnDataAccess.lopezJohnPersonaTipoDAO;
import lopezJohnDataAccess.lopezJohnSexoDAO;
import lopezJohnDataAccess.lopezJohnDTO.lopezJohnEstadoCivilDTO;
import lopezJohnDataAccess.lopezJohnDTO.lopezJohnPersonaTipoDTO;
import lopezJohnDataAccess.lopezJohnDTO.lopezJohnSexoDTO;
import lopezJohnUserInterface.lopezJohnForm.lopezJohnMainForm;
import lopezJohnUserInterface.lopezJohnForm.lopezJohnSplashScreenForm;
import lopezJohnBusinessLogic.lopezJohnEstadoCivilBL;
import lopezJohnBusinessLogic.lopezJohnSexoBL;
import lopezJohnBusinessLogic.lopezJohnPersonaTipoBL;

public class App {
    public static void main(String[] args) throws Exception {
       
        //lopezjohnargumento(args);

        //lopezJohnflujoEntrada();

       lopezJohnSplashScreenForm.lopezJohnshow();
       lopezJohnMainForm lopezJohnMainForm = new lopezJohnMainForm("IABot");



        //TESTING DAOs
        System.out.println("--------------------------------------------------------------------");
        try{
            lopezJohnSexoDAO sexoDAO = new lopezJohnSexoDAO();
            
           for (lopezJohnSexoDTO s : sexoDAO.lopezJohnreadAll())
               System.out.println(s.toString());

              System.out.println("------------------------------");

              lopezJohnEstadoCivilDAO estcivilDAO = new lopezJohnEstadoCivilDAO();

              for (lopezJohnEstadoCivilDTO reg : estcivilDAO.lopezJohnreadAll())
                  System.out.println(reg.toString());
              
              System.out.println("------------------------------");

              lopezJohnPersonaTipoDAO perTDAO = new lopezJohnPersonaTipoDAO();

              for (lopezJohnPersonaTipoDTO reg : perTDAO.lopezJohnreadAll())
                  System.out.println(reg.toString());

           }
           catch (Exception e){
               System.out.println(e.toString());
           }

        //------------
        System.out.println("--------------------------------------------------------------------");
        try{

            int a[] = {10,0}, b = 10;

            try { // INFORMAR UN ERROR
                int resultadoa = a[1] / b;
                int resultadob = b / a[0];
                throw new Exception(" Error personalizado de prueba ");
            } 
            catch (ArithmeticException e) {
                System.out.println("El denominador no debe ser cero "); //+ e.getMessage());
            }
            catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Fuera de rango"); //+ e.getMessage());
            }
            catch (Exception e){
                System.out.println("Ha ocurrido un error" + e.getMessage());
            } finally{
                b = 10;
                //System.out.println("Finally: Ejecución con o sin error");
            }

            //TESTING BLs
            lopezJohnSexoBL sBL = new lopezJohnSexoBL();
            //sBL.lopezJohnset(new lopezJohnSexoDTO(0,0,"Nuevo Sexo","Prueba",null,null,null));
            for (lopezJohnSexoDTO s : sBL.getAll())
                System.out.println(s.toString());

            System.out.println("------------------------------");

            lopezJohnEstadoCivilBL eCivilBL = new lopezJohnEstadoCivilBL();
            for (lopezJohnEstadoCivilDTO reg : eCivilBL.getAll())
                System.out.println(reg.toString());

            System.out.println("------------------------------");

            lopezJohnPersonaTipoBL perTBL = new lopezJohnPersonaTipoBL();
            for (lopezJohnPersonaTipoDTO reg : perTBL.getAll())
                System.out.println(reg.toString());
        }
        catch (Exception e){
            System.out.println(e.toString());
        }

     }

    private static void lopezJohnflujoEntrada() {
        int total = 0;
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Ingrese números para sumar:");
        while (sc.hasNextInt()) {
            total += sc.nextInt();
        }
        System.out.println("La suma de números es: " + total);
        sc.close();
    }

    private static void lopezJohnargumento(String[] args) {
        if  ( (args.length == 2)
            && (args[0].equals("kevin"))
            && (args[1].equals("08"))      ){
                System.out.println("Hola John, estoy listo para ejecutar el programa");
                
                System.out.println(args);
                var entrada = Arrays.toString(args);
                System.out.println("Los argumentos ingresados son: " + entrada);
            }
        else{
            System.out.println("Tú no eres John, no puedes ejecutar este programa");
            System.exit(0);
        }

        // if (args.length < 1) {
        //   System.out.println("Tú no eres John, no puedes ejecutar este programa.");
        //   System.exit(0);
        // }      
        // String nombre = args[0];
        // if (nombre.equals("kevin")) 
        //   System.out.println("Hola John, estoy listo para ejecutar el programa.");
    }

}

