package co.com.mercadolibre.mutants.service;

import co.com.mercadolibre.mutants.detector.MutantDetector;
import co.com.mercadolibre.mutants.dto.DnaSequence;
import co.com.mercadolibre.mutants.exception.MutantServiceException;
import co.com.mercadolibre.mutants.jpa.repository.DnaSequenceRepository;
import co.com.mercadolibre.mutants.util.ConstantTest;
import co.com.mercadolibre.mutants.util.MutantUtility;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@WebFluxTest(MutantService.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MutantServiceTest {

    @MockBean
    public DnaSequenceRepository dnaSequenceRepository;

    @Autowired
    public MutantService mutantService;

    @MockBean
    public MutantDetector mutantDetector;

    @MockBean
    public MutantUtility mutantUtility;

    @BeforeAll
    public void init() {
        doReturn(null).when(dnaSequenceRepository).save(ArgumentMatchers.any());
    }

    @Test
    void testIsMutant_whenIsMutant() {
        DnaSequence dnaSequence = DnaSequence.builder().dnaSequence(ConstantTest.DNA_MUTANT).build();
        when(mutantUtility.validateSequence(dnaSequence)).thenReturn(true);
        when(mutantDetector.isMutant(ConstantTest.DNA_MUTANT)).thenReturn(true);

        Mono<Boolean> stats = mutantService.isMutant(dnaSequence);
        StepVerifier.create(stats.log())
                .expectNext(true)
                .verifyComplete();
    }

    @Test
    void testIsMutant_whenIsInvalidDnaSequence() {
        DnaSequence dnaSequence = DnaSequence.builder().dnaSequence(ConstantTest.DNA_HUMAN).build();
        when(mutantUtility.validateSequence(dnaSequence)).thenReturn(false);

        try {
            mutantService.isMutant(dnaSequence);
        } catch (MutantServiceException ex) {
            assertEquals(HttpStatus.BAD_REQUEST, ex.getStatus());
            assertEquals(ConstantTest.GENERAL_ERROR, ex.getMessage());
            assertEquals(ConstantTest.INVALID_SEQUENCE, ex.getDetailMessage());
        }
    }
}
