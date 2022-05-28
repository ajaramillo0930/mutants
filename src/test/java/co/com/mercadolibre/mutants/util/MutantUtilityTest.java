package co.com.mercadolibre.mutants.util;

import co.com.mercadolibre.mutants.dto.DnaSequence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;

import static org.assertj.core.api.Assertions.assertThat;

@WebFluxTest(MutantUtility.class)
class MutantUtilityTest {

    @Autowired
    public MutantUtility mutantUtility;

    @Test
    void testValidateSequence_whenDnaIsOK() {
        assertThat(mutantUtility.validateSequence(DnaSequence.builder().dnaSequence(ConstantTest.DNA_MUTANT).build())).isTrue();
    }

    @Test
    void testValidateSequence_whenDnaSequenceIsNull() {
        assertThat(mutantUtility.validateSequence(null)).isFalse();
    }

    @Test
    void testValidateSequence_whenSequenceIsNull() {
        assertThat(mutantUtility.validateSequence(DnaSequence.builder().dnaSequence(null).build())).isFalse();
    }
}
