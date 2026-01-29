package ljBusinessLogic;

public class ljUsuario {
    private String cedula;
    private String nombre;
    private String clave;

    public ljUsuario() {
    }

    public ljUsuario(String cedula, String nombre, String clave) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.clave = clave;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
