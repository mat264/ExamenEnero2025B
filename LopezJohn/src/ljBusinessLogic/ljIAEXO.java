package ljBusinessLogic;

import ljInfrastructure.ljCMD;
import ljInfrastructure.ljAppMSG;
import ljDataAccess.ljDataHelper;

/**
 * Implementación de la Inteligencia Artificial del Exobot.
 * Gestiona el entrenamiento y la ejecución de acciones.
 */
public class ljIAEXO implements ljIIAEXO {

    @Override
    public boolean entrenar(String soldadoExperto, String tipoArma, String accionArma) {
        // Simular lógica de entrenamiento
        // En un caso real, aquí iría la lógica compleja de IA

        // Registrar en tracer
        String mensaje = String.format(ljAppMSG.ENTRENAMIENTO_EXITOSO, soldadoExperto, tipoArma, accionArma);
        ljCMD.escribir(mensaje);
        return true;
    }

    @Override
    public boolean ejecutarAccion(String tipoArma, String accionArma) {
        // Obtener munición o energía requerida
        String municion = ljDataHelper.getMunicion(tipoArma);

        // Registrar en tracer
        String mensaje = String.format(ljAppMSG.ACCION_EXITOSA, tipoArma, accionArma, municion);
        ljCMD.escribir(mensaje);
        return true;
    }
}
