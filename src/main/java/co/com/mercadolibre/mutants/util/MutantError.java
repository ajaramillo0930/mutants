package co.com.mercadolibre.mutants.util;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * DTO con el formato de error.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MutantError {

    /**
     * Http estado.
     */
    private HttpStatus status;

    /**
     * Fecha y hora.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

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
     */
    private MutantError() {
        timestamp = LocalDateTime.now();
    }

    /**
     * Constructor.
     *
     * @param status - Http estado.
     */
    public MutantError(HttpStatus status) {
        this();
        this.status = status;
    }

    /**
     * Constructor.
     *
     * @param status - Http estado.
     * @param ex     - Excepcion.
     */
    public MutantError(HttpStatus status, Throwable ex) {
        this(status);
        this.detailMessage = "Unexpected error";
        this.errorMessage = ex.getLocalizedMessage();
    }

    /**
     * Constructor.
     *
     * @param status        - Http estado.
     * @param errorMessage  - Mensaje error.
     * @param detailMessage - Detalle error.
     * @param ex            - Excepcion.
     */
    public MutantError(HttpStatus status, String errorMessage, String detailMessage, Throwable ex) {
        this(status, ex);
        this.errorMessage = errorMessage;
        this.detailMessage = detailMessage;
    }
}
