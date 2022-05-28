package co.com.mercadolibre.mutants.util;

import co.com.mercadolibre.mutants.dto.DnaSequence;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;

/**
 * Clase utilitaria.
 */
@Component
public class MutantUtility {

    /**
     * Metodo que permite validar la secuencia de ADN.
     *
     * @param dnaSequence - Secuencia de ADN.
     * @return True si es correcta; False de lo contrario.
     */
    public boolean validateSequence(DnaSequence dnaSequence) {
        return Objects.nonNull(dnaSequence) &&
                Objects.nonNull(dnaSequence.getDnaSequence()) &&
                dnaSequence.getDnaSequence().length > 0 &&
                Arrays.stream(dnaSequence.getDnaSequence())
                        .map(String::length)
                        .allMatch(length -> length == dnaSequence.getDnaSequence().length);
    }
}
