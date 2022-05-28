package co.com.mercadolibre.mutants.jpa.repository;

import co.com.mercadolibre.mutants.jpa.model.DnaSequenceEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio para gestionar el CRUD de la tabla DNA_SEQUENCE.
 */
@Repository
public interface DnaSequenceRepository extends CrudRepository<DnaSequenceEntity, Long> {

    /**
     * Consultar la secuencia de ADN.
     *
     * @param dna - Secuencia de ADN.
     * @return Objeto con la informacion de la secuencia.
     */
    @Query("SELECT dna FROM DnaSequenceEntity dna WHERE dna.dna = :dna")
    Optional<DnaSequenceEntity> findDnaSequenceByDna(@Param("dna") String dna);

    /**
     * Contar la cantidad de Mutantes.
     *
     * @return Cantidad de Mutantes.
     */
    Long countIdentifiedMutants();

    /**
     * Contar la cantidad de Humanos.
     *
     * @return Cantidad de Humanos.
     */
    Long countIdentifiedHumans();
}
