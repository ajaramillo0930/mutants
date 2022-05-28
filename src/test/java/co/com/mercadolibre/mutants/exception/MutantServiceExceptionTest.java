package co.com.mercadolibre.mutants.exception;

import co.com.mercadolibre.mutants.util.ConstantTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

class MutantServiceExceptionTest {

    @Test
    void testException_whenIsOk() {
        final MutantServiceException ex = new MutantServiceException(
                ConstantTest.GENERAL_ERROR, ConstantTest.INVALID_SEQUENCE);
        assertThat(ex).hasMessage(ConstantTest.GENERAL_ERROR);
        assertThat(ex.getDetailMessage()).isEqualTo(ConstantTest.INVALID_SEQUENCE);
    }

    @Test
    void testException_withStatus() {
        final MutantServiceException ex = new MutantServiceException(
                HttpStatus.BAD_REQUEST, ConstantTest.GENERAL_ERROR, ConstantTest.INVALID_SEQUENCE);
        assertThat(ex.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(ex.getErrorMessage()).isEqualTo(ConstantTest.GENERAL_ERROR);
        assertThat(ex.getDetailMessage()).isEqualTo(ConstantTest.INVALID_SEQUENCE);
    }
}
