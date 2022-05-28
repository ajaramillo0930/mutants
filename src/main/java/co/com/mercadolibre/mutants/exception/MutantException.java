package co.com.mercadolibre.mutants.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Mutante Excepcion.
 */
@Getter
public class MutantException extends RuntimeException {

    /**
     * Http estado.
     */
    private HttpStatus status;
    /**
     * Mensaje error.
     */
    private String errorMessage;
    /**
     * Detalle error.
     */
    private String detailMessage;

    /**
     * Constructor.
     *
     * @param message - Mensaje error.
     */
    public MutantException(String message) {
        super(message);
    }

    /**
     * Constructor.
     *
     * @param status        - Http estado.
     * @param errorMessage  - Mensaje error.
     * @param detailMessage - Detalle error.
     */
    public MutantException(HttpStatus status, String errorMessage, String detailMessage) {
        super(errorMessage);
        this.status = status;
        this.errorMessage = errorMessage;
        this.detailMessage = detailMessage;
    }
}
