package co.com.mercadolibre.mutants.controller;

import co.com.mercadolibre.mutants.dto.DnaSequence;
import co.com.mercadolibre.mutants.dto.Stats;
import co.com.mercadolibre.mutants.service.MutantService;
import co.com.mercadolibre.mutants.service.StatsService;
import co.com.mercadolibre.mutants.util.ConstantTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static reactor.core.publisher.Mono.just;

@WebFluxTest(MutantController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MutantControllerTest {

    @Autowired
    public WebTestClient testClient;

    @MockBean
    public MutantService mutantService;

    @MockBean
    public StatsService statsService;

    @Test
    void testIsMutant_whenIsMutant() {
        when(mutantService.isMutant(DnaSequence.builder().dnaSequence(ConstantTest.DNA_MUTANT).build())).thenReturn(just(true));

        final WebTestClient.ResponseSpec spec = testClient.post().uri(ConstantTest.MUNTAN_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(DnaSequence.builder().dnaSequence(ConstantTest.DNA_MUTANT).build())
                .exchange();

        spec.expectBody(String.class).consumeWith(res -> {
            final HttpStatus status = res.getStatus();
            final String body = res.getResponseBody();
            assertThat(status.is2xxSuccessful()).isTrue();
            assertThat(body).isEqualTo(ConstantTest.MUTANT_RESULT_SUCCESS);
        });
    }

    @Test
    void testIsMutant_wheIsHuman() {
        when(mutantService.isMutant(DnaSequence.builder().dnaSequence(ConstantTest.DNA_HUMAN).build())).thenReturn(just(false));

        final WebTestClient.ResponseSpec spec = testClient.post().uri(ConstantTest.MUNTAN_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(DnaSequence.builder().dnaSequence(ConstantTest.DNA_HUMAN).build())
                .exchange();

        spec.expectBody(String.class).consumeWith(res -> {
            final HttpStatus status = res.getStatus();
            final String body = res.getResponseBody();
            assertThat(status.is4xxClientError()).isTrue();
            assertThat(body).isEqualTo(ConstantTest.HUMANT_RESULT_SUCCESS);
        });
    }

    @Test
    void testGetStats_getData() {
        when(statsService.getStats()).thenReturn(
                just(Stats.builder().countMutantDna(ConstantTest.COUNT_MUTANT)
                        .countHumanDna(ConstantTest.COUNT_HUMANT).ratio(new BigDecimal(ConstantTest.RATIO)).build()));

        final WebTestClient.ResponseSpec spec = testClient.get().uri(ConstantTest.STATS_ENDPOINT)
                .exchange();

        spec.expectBody(Stats.class).consumeWith(res -> {
            final HttpStatus status = res.getStatus();
            final Stats body = res.getResponseBody();
            assertThat(status.is2xxSuccessful()).isTrue();
            assertThat(body).extracting(Stats::getCountMutantDna, Stats::getCountHumanDna, Stats::getRatio)
                    .containsExactly(ConstantTest.COUNT_MUTANT, ConstantTest.COUNT_HUMANT, new BigDecimal(ConstantTest.RATIO));
        });
    }
}
