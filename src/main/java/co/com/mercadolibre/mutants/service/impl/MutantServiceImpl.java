package co.com.mercadolibre.mutants.service.impl;

import co.com.mercadolibre.mutants.detector.MutantDetector;
import co.com.mercadolibre.mutants.dto.DnaSequence;
import co.com.mercadolibre.mutants.exception.MutantServiceException;
import co.com.mercadolibre.mutants.jpa.model.DnaSequenceEntity;
import co.com.mercadolibre.mutants.jpa.repository.DnaSequenceRepository;
import co.com.mercadolibre.mutants.service.MutantService;
import co.com.mercadolibre.mutants.util.MutantUtility;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Optional;

import static co.com.mercadolibre.mutants.util.Constant.*;


/**
 * Servicio con las operaciones para el manejo de las validaciones.
 */
@Service
public class MutantServiceImpl implements MutantService {

    /**
     * Logger.
     */
    private static final Logger logger = Logger.getLogger(MutantServiceImpl.class);

    /**
     * mutantDetector.
     */
    @Autowired
    private MutantDetector mutantDetector;

    /**
     * dnaSequenceRepository.
     */
    @Autowired
    private DnaSequenceRepository dnaSequenceRepository;

    /**
     * mutantUtility.
     */
    @Autowired
    private MutantUtility mutantUtility;

    /**
     * Servicio para detectar si el ADN recibido es Mutante o Humano.
     *
     * @param dnaSequence - Secuencia de ADN.
     * @return Cadena que identifica si es un Mutante o Humano.
     */
    @Override
    public Mono<Boolean> isMutant(DnaSequence dnaSequence) throws MutantServiceException {

        logger.info(VALIDATING_DNA_SEQUENCE);

        if (mutantUtility.validateSequence(dnaSequence)) {
            Optional<DnaSequenceEntity> dnaSequenceEntity =
                    dnaSequenceRepository.findDnaSequenceByDna(Arrays.toString(dnaSequence.getDnaSequence()));

            return dnaSequenceEntity.map(sequenceEntity -> {
                logger.info(String.format(THE_DNA_ANALIZED_IS, Boolean.TRUE.equals(dnaSequenceEntity.get().isMutant()) ? MUTANT : HUMAN));
                return Mono.just(sequenceEntity.isMutant());
            }).orElseGet(() -> Mono.just(mutantDetector.isMutant(dnaSequence.getDnaSequence()))
                    .doOnSuccess(isMutant -> {
                        logger.info(String.format(THE_DNA_ANALIZED_IS, Boolean.TRUE.equals(isMutant) ? MUTANT : HUMAN));
                        dnaSequenceRepository.save(new DnaSequenceEntity().toBuilder().dna(Arrays.toString(dnaSequence.getDnaSequence())).mutant(isMutant).build());
                    }));
        } else {
            logger.error(INVALID_SEQUENCE);
            throw new MutantServiceException(HttpStatus.BAD_REQUEST, GENERAL_ERROR, INVALID_SEQUENCE);
        }
    }
}
