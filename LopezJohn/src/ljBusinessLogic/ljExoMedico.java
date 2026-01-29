package ljBusinessLogic;

/**
 * Implementación específica para ExoMedico (Ultimo dígito cédula 8).
 * Armas: BioSensor / BioEscaner
 * Acción: asistir (Energía)
 */
public class ljExoMedico extends ljExoBot {

    public ljExoMedico() {
        super(new ljIAEXO());
        setTipoExobot("ExoMedico");
    }

    @Override
    public String getArmaPrincipal() {
        return "BioSensor"; // O BioEscaner
    }

    @Override
    public String getAccionPrincipal() {
        return "asistir";
    }
}
