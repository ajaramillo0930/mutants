package co.com.mercadolibre.mutants.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO que representa las estadisticas.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Stats implements Serializable {

    /**
     * Cantidad de mutantes.
     */
    @JsonProperty("count_mutant_dna")
    private Long countMutantDna;

    /**
     * Cantidad de humanos.
     */
    @JsonProperty("count_human_dna")
    private Long countHumanDna;

    /**
     * Radio.
     */
    @JsonProperty("ratio")
    private BigDecimal ratio;
}
