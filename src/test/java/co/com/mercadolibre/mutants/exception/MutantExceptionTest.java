package co.com.mercadolibre.mutants.exception;

import co.com.mercadolibre.mutants.util.ConstantTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MutantExceptionTest {

    @Test
    void testExceptionStructure_whenIsOk() {
        final MutantException ex = new MutantException(ConstantTest.INVALID_SEQUENCE);
        assertThat(ex).hasMessage(ConstantTest.INVALID_SEQUENCE);
    }
}
