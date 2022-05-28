package co.com.mercadolibre.mutants.service;

import co.com.mercadolibre.mutants.dto.Stats;
import co.com.mercadolibre.mutants.exception.MutantServiceException;
import co.com.mercadolibre.mutants.jpa.repository.DnaSequenceRepository;
import co.com.mercadolibre.mutants.util.ConstantTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@WebFluxTest(StatsService.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StatsServiceTest {

    @MockBean
    public DnaSequenceRepository dnaSequenceRepository;

    @Autowired
    public StatsService statsService;

    @BeforeAll
    public void init() {
        when(dnaSequenceRepository.countIdentifiedHumans()).thenReturn(ConstantTest.COUNT_HUMANT);
    }

    @Test
    void testGetStats_whenIsOk() {
        when(dnaSequenceRepository.countIdentifiedMutants()).thenReturn(ConstantTest.COUNT_MUTANT);
        Mono<Stats> stats = statsService.getStats();
        StepVerifier.create(stats.log())
                .expectNext(Stats.builder()
                        .countMutantDna(ConstantTest.COUNT_MUTANT)
                        .countHumanDna(ConstantTest.COUNT_HUMANT)
                        .ratio(new BigDecimal(ConstantTest.RATIO).setScale(2, RoundingMode.UNNECESSARY)).build())
                .verifyComplete();
    }

    @Test
    void testGetStats_whenIsInvalidCount() {
        when(dnaSequenceRepository.countIdentifiedMutants())
                .thenThrow(new MutantServiceException(ConstantTest.GENERAL_ERROR, ConstantTest.ERROR_GETTING_COUNT));
        try {
            statsService.getStats();
        } catch (MutantServiceException ex) {
            assertEquals(ConstantTest.GENERAL_ERROR, ex.getMessage());
            assertEquals(ConstantTest.ERROR_GENERAL_GETTING_COUNT, ex.getDetailMessage());
        }
    }
}
