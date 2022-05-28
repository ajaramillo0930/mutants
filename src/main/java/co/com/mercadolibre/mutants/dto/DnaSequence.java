package co.com.mercadolibre.mutants.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * DTO que represencia la secuencia de ADN.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class DnaSequence implements Serializable {

    /**
     * Secuencia de ADN.
     */
    @NotNull(message = "Field 'dna' is required")
    @JsonProperty("dna")
    private String[] dnaSequence;
}
