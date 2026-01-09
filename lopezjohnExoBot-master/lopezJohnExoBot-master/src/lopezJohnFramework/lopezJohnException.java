package lopezJohnFramework;

public class lopezJohnException extends Exception {
    
    public lopezJohnException(String e, String clase, String metodo) {
        //grabar en log el error que está abajo
        //System.out.println("[ERROR DE LA APP -----> LOG] " + clase + "." + metodo + " : " + e);
    }

    @Override
    public String getMessage() {
        return "No se pudo completar la operación solicitada.";
    }
}
