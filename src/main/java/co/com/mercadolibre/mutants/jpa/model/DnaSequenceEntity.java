package co.com.mercadolibre.mutants.jpa.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entidad que representa la tabla DNA_SEQUENCE.
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "DNA_SEQUENCE")
@NamedQueries({
        @NamedQuery(name = "DnaSequenceEntity.countIdentifiedMutants", query = "SELECT COUNT(dna) FROM DnaSequenceEntity dna WHERE dna.mutant = true"),
        @NamedQuery(name = "DnaSequenceEntity.countIdentifiedHumans", query = "SELECT COUNT(dna) FROM DnaSequenceEntity dna WHERE dna.mutant = false")
})
public class DnaSequenceEntity implements Serializable {

    /**
     * Id-Llave primaria.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    /**
     * Secuencia de ADN.
     */
    @Column(name = "DNA")
    private String dna;

    /**
     * Identifica si es Mutante o Humano.
     */
    @Column(name = "MUTANT")
    private boolean mutant;
}
