package co.com.mercadolibre.mutants.controller;

import co.com.mercadolibre.mutants.dto.DnaSequence;
import co.com.mercadolibre.mutants.dto.Stats;
import co.com.mercadolibre.mutants.service.MutantService;
import co.com.mercadolibre.mutants.service.StatsService;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.time.Duration;

/**
 * Clase controladora de los servicios web.
 */
@RestController
@Validated
public class MutantController {

    /**
     * Logger.
     */
    private static final Logger logger = Logger.getLogger(MutantController.class);

    /**
     * Servicio mutante.
     */
    @Autowired
    private MutantService mutantService;

    /**
     * Servicio estadisticas.
     */
    @Autowired
    private StatsService statsService;

    /**
     * Controlador peticiones.
     */
    private final Bucket bucket;

    /**
     * Constructor con parametros.
     *
     * @param requestsAllowed  - Consumos permitidos.
     * @param requestsDuration -  Duracion.
     */
    public MutantController(@Value("${bucket.requestsAllowed}") Integer requestsAllowed,
                            @Value("${bucket.requestsDuration}") Integer requestsDuration) {
        Bandwidth limit = Bandwidth.classic(requestsAllowed,
                Refill.greedy(requestsAllowed, Duration.ofSeconds(requestsDuration)));
        this.bucket = Bucket4j.builder()
                .addLimit(limit)
                .build();
    }

    /**
     * Servicio para detectar si el ADN recibido es Mutante o Humano.
     *
     * @param dnaSequence - Secuencia de ADN.
     * @return Cadena que identifica si es un Mutante o Humano.
     */
    @Operation(summary = "Analyze a person's DNA sequence to identify humans or mutants")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Analyze a person's DNA sequence",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid NxN DNA sequence",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "The DNA sequence analyzed is Human",
                    content = @Content),
            @ApiResponse(responseCode = "429", description = "Too Many Requests",
                    content = @Content)})
    @PostMapping(value = "/mutant", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<String>> isMutant(
            @Valid
            @RequestBody DnaSequence dnaSequence) {

        if (bucket.tryConsume(1)) {
            return mutantService.isMutant(dnaSequence)
                    .map(isMutant -> Boolean.TRUE.equals(isMutant) ?
                            ResponseEntity.ok().body("The DNA sequence analyzed is MUTANT!!!")
                            : ResponseEntity.status(HttpStatus.FORBIDDEN).body("The DNA sequence analyzed is Human"))
                    .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
        }
        logger.error(String.format("Error getting Stats: %s", HttpStatus.TOO_MANY_REQUESTS));
        return Mono.just(ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build());
    }

    /**
     * Servicio para consultar las estadisticas de las verificaciones de ADN.
     *
     * @return Objeto de tipo 'Stats' con las estadisticas.
     */
    @Operation(summary = "Get statistics")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get statistics of DNA checks",
                    content = @Content(schema = @Schema(implementation = Stats.class))),
            @ApiResponse(responseCode = "429", description = "Too Many Requests",
                    content = @Content)})
    @GetMapping("/stats")
    public Mono<ResponseEntity<Stats>> getStats() {
        if (bucket.tryConsume(1)) {
            return statsService.getStats().map(stats -> new ResponseEntity<>(stats, HttpStatus.OK));
        }
        logger.error(String.format("Error getting Stats: %s", HttpStatus.TOO_MANY_REQUESTS));
        return Mono.just(ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build());
    }
}
