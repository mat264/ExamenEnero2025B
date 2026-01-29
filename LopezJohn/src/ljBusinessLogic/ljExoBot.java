package ljBusinessLogic;

import ljDataAccess.ljDataHelper;
import ljInfrastructure.ljAppMSG;
import ljInfrastructure.ljCMD;

/**
 * Clase base abstracta para todos los Exobots.
 */
public abstract class ljExoBot {
    private ljIIAEXO iaBot;

    // Propiedades comunes
    // IdExobot, TipoExobot, Entreno, No. Accion
    private int idExobot;
    private String tipoExobot;
    private String entreno; // "SI" o "NO"
    private int numeroAccion;

    public ljExoBot(ljIIAEXO iaBot) {
        this.iaBot = iaBot;
        this.entreno = "NO";
        this.numeroAccion = 0;
    }

    // Getters y Setters
    public int getIdExobot() {
        return idExobot;
    }

    public void setIdExobot(int idExobot) {
        this.idExobot = idExobot;
    }

    public String getTipoExobot() {
        return tipoExobot;
    }

    public void setTipoExobot(String tipoExobot) {
        this.tipoExobot = tipoExobot;
    }

    public String getEntreno() {
        return entreno;
    }

    public void setEntreno(String entreno) {
        this.entreno = entreno;
    }

    public int getNumeroAccion() {
        return numeroAccion;
    }

    public void setNumeroAccion(int numeroAccion) {
        this.numeroAccion = numeroAccion;
    }

    // Métodos delegados a la IA
    public void entrenar(String soldadoExperto, String tipoArma, String accionArma) {
        if (iaBot.entrenar(soldadoExperto, tipoArma, accionArma)) {
            this.entreno = "SI";
        }
    }

    public void usarArma(String tipoArma, String accionArma) {
        if ("SI".equals(this.entreno)) {
            if (iaBot.ejecutarAccion(tipoArma, accionArma)) {
                this.numeroAccion++;
            }
        } else {
            String municion = ljDataHelper.getMunicion(tipoArma);
            String mensaje = String.format(ljAppMSG.ACCION_FALLIDA, tipoArma, accionArma, municion);
            ljCMD.escribir(mensaje);
        }
    }

    // Métodos abstractos específicos si son necesarios
    public abstract String getArmaPrincipal();

    public abstract String getAccionPrincipal();
}
