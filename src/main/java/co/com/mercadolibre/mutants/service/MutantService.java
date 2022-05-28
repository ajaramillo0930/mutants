package co.com.mercadolibre.mutants.service;

import co.com.mercadolibre.mutants.dto.DnaSequence;
import co.com.mercadolibre.mutants.exception.MutantServiceException;
import reactor.core.publisher.Mono;

/**
 * Interfaz de las operaciones para el manejo de las validaciones.
 */
public interface MutantService {

    /**
     * Servicio para detectar si el ADN recibido es Mutante o Humano.
     *
     * @param dnaSequence - Secuencia de ADN.
     * @return Cadena que identifica si es un Mutante o Humano.
     */
    Mono<Boolean> isMutant(DnaSequence dnaSequence) throws MutantServiceException;
}
