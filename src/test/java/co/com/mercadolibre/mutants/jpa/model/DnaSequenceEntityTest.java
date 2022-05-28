package co.com.mercadolibre.mutants.jpa.model;

import co.com.mercadolibre.mutants.util.ConstantTest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class DnaSequenceEntityTest {

    @Test
    void testDnaSequenceEntity_whenIsOk() {
        final DnaSequenceEntity dnaSequenceEntity =
                new DnaSequenceEntity(1L, Arrays.toString(ConstantTest.DNA_MUTANT), true);
        assertEquals(1L, dnaSequenceEntity.getId());
        assertEquals( Arrays.toString(ConstantTest.DNA_MUTANT), dnaSequenceEntity.getDna());
        assertTrue(dnaSequenceEntity.isMutant());
    }

    @Test
    void testDnaSequenceEntity_whenConstructorIsEmpty() {
        final DnaSequenceEntity dnaSequenceEntity = new DnaSequenceEntity();
        assertNull(dnaSequenceEntity.getId());
        assertNull(dnaSequenceEntity.getDna());
        assertFalse(dnaSequenceEntity.isMutant());
    }
}
