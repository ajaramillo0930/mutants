package co.com.mercadolibre.mutants.service;

import co.com.mercadolibre.mutants.dto.Stats;
import co.com.mercadolibre.mutants.exception.MutantServiceException;
import reactor.core.publisher.Mono;

/**
 * Interfaz de las operaciones para el manejo de las estadisticas.
 */
public interface StatsService {

    /**
     * Servicio para consultar las estadisticas de las verificaciones de ADN.
     *
     * @return Objeto de tipo 'Stats' con las estadisticas.
     */
    Mono<Stats> getStats() throws MutantServiceException;
}
