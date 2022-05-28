package co.com.mercadolibre.mutants.detector;

import co.com.mercadolibre.mutants.util.ConstantTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@WebFluxTest(MutantDetector.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MutantDetectorTest {

    @Autowired
    public MutantDetector mutantDetector;

    @Test
    void testIsMutant_whenIsOk() {
        assertTrue(mutantDetector.isMutant(ConstantTest.DNA_MUTANT));
    }

    @Test
    void testIsHuman_whenIsOk() {
        assertFalse(mutantDetector.isMutant(ConstantTest.DNA_HUMAN));
    }
}
