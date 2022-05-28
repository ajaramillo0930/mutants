package co.com.mercadolibre.mutants.util;

/**
 * Clase con las constantes.
 */
public class Constant {
    /**
     * Constructor privado.
     */
    private Constant() {
    }

    public static final String RATIO_DEFAULT = "0.00";
    public static final Integer CHARACTER_SEQUENCE = 3;
    public static final Integer MINIMUM_MUTANT_SEQUENCE = 2;

    public static final String MUTANT = "MUTANT";
    public static final String HUMAN = "HUMAN";

    public static final String VALIDATING_DNA_SEQUENCE = "Validating DNA sequence";
    public static final String THE_DNA_ANALIZED_IS = "The DNA analyzed is %s!!!";
    public static final String GETTING_STATISTICS = "Getting statistics from DNA verifications";
    public static final String GENERAL_ERROR = "General Error";
    public static final String INVALID_SEQUENCE = "Invalid NxN DNA sequence";
    public static final String ERROR_GENERAL_GETTING_COUNT = "Error while getting count of DNA: ";
    public static final String STATS_LOG = "Stats:: [ Number of Mutants: %s, Number of Humans: %s, Total collected: %s ]";
}
