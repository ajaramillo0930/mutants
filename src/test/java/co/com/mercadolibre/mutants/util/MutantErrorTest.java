package co.com.mercadolibre.mutants.util;

import co.com.mercadolibre.mutants.exception.MutantException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MutantErrorTest {

    @Test
    void testMutantError_whenIsOk() {
        final MutantError mutantError = new MutantError(
                HttpStatus.BAD_REQUEST, ConstantTest.GENERAL_ERROR,
                ConstantTest.INVALID_SEQUENCE, new MutantException(ConstantTest.INVALID_SEQUENCE));
        assertEquals(HttpStatus.BAD_REQUEST, mutantError.getStatus());
    }
}
