package co.com.mercadolibre.mutants.exception;

import org.springframework.http.HttpStatus;

/**
 * Mutante servicio Excepcion.
 */
public class MutantServiceException extends MutantException {

    /**
     * Constructor.
     *
     * @param message - Mensaje error.
     * @param details - Detalle error.
     */
    public MutantServiceException(String message, String details) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, message, details);
    }

    /**
     * Constructor.
     *
     * @param status  - Http estado.
     * @param message - Mensaje error.
     * @param details - Detalle error.
     */
    public MutantServiceException(HttpStatus status, String message, String details) {
        super(status, message, details);
    }
}
