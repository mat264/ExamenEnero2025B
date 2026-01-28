package ljBusinessLogic;

/**
 * Interfaz para la Inteligencia Artificial del Exobot.
 */
public interface ljIIAEXO {
    /**
     * Realiza el entrenamiento del arma.
     * 
     * @param soldadoExperto Nombre del soldado experto.
     * @param tipoArma       Tipo de arma.
     * @param accionArma     Acción del arma.
     * @return true si el entrenamiento fue exitoso.
     */
    boolean entrenar(String soldadoExperto, String tipoArma, String accionArma);

    /**
     * Ejecuta la acción del arma.
     * 
     * @param tipoArma   Tipo de arma.
     * @param accionArma Acción del arma.
     * @return Resultado de la acción.
     */
    boolean ejecutarAccion(String tipoArma, String accionArma);
}
