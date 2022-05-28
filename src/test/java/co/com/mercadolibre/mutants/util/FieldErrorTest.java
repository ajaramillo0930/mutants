package co.com.mercadolibre.mutants.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class FieldErrorTest {

    @Test
    void testFieldError_whenIsOk() {
        final FieldError fieldError = new FieldError(ConstantTest.DNA_SEQUENCE, ConstantTest.FIELD_DNA_REQUIRED);
        assertEquals(ConstantTest.DNA_SEQUENCE, fieldError.getFieldName());
        assertEquals(ConstantTest.FIELD_DNA_REQUIRED, fieldError.getMessage());
    }

    @Test
    void testFieldError_whenConstructorEmpty() {
        final FieldError fieldError = new FieldError();
        assertNull(fieldError.getFieldName());
        assertNull(fieldError.getMessage());
    }
}