package co.com.mercadolibre.mutants.service.impl;

import co.com.mercadolibre.mutants.dto.Stats;
import co.com.mercadolibre.mutants.exception.MutantServiceException;
import co.com.mercadolibre.mutants.jpa.repository.DnaSequenceRepository;
import co.com.mercadolibre.mutants.service.StatsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

import static co.com.mercadolibre.mutants.util.Constant.*;

/**
 * Servicio con las operaciones para el manejo de las estadisticas.
 */
@Service
public class StatsServiceImpl implements StatsService {

    /**
     * Logger.
     */
    private static final Logger logger = Logger.getLogger(StatsServiceImpl.class);

    /**
     * dnaSequenceRepository.
     */
    @Autowired
    private DnaSequenceRepository dnaSequenceRepository;

    /**
     * Servicio para consultar las estadisticas de las verificaciones de ADN.
     *
     * @return Objeto de tipo 'Stats' con las estadisticas.
     */
    @Override
    public Mono<Stats> getStats() throws MutantServiceException {
        logger.info(GETTING_STATISTICS);
        try {
            Long mutants = dnaSequenceRepository.countIdentifiedMutants();
            Long humans = dnaSequenceRepository.countIdentifiedHumans();
            return Mono.just(Stats.builder()
                            .countMutantDna(mutants)
                            .countHumanDna(humans)
                            .ratio(
                                    Objects.nonNull(mutants) && Objects.nonNull(humans) && humans > 0 ?
                                            new BigDecimal(mutants).divide(new BigDecimal(humans), 2, RoundingMode.UNNECESSARY) :
                                            new BigDecimal(RATIO_DEFAULT))
                            .build())
                    .doOnSuccess(stats -> logger.info(String.format(STATS_LOG, mutants, humans, mutants + humans)));

        } catch (Exception ex) {
            logger.error(ERROR_GENERAL_GETTING_COUNT + ex.getMessage(), ex);
            throw new MutantServiceException(GENERAL_ERROR, ERROR_GENERAL_GETTING_COUNT + ex.getMessage());
        }
    }
}
