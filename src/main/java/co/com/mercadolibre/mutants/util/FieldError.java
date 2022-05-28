package co.com.mercadolibre.mutants.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para el manejo de errores.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FieldError {

    /**
     * Nombre del campo.
     */
    private String fieldName;

    /**
     * Mensaje error.
     */
    private String message;
}
