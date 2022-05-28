package co.com.mercadolibre.mutants.util;

/**
 * Clase con las constantes.
 */
public class ConstantTest {
    /**
     * Constructor privado.
     */
    private ConstantTest() {
    }

    public static final String MUNTAN_ENDPOINT = "/mutant";
    public static final String STATS_ENDPOINT = "/stats";

    public static final String[] DNA_MUTANT = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
    public static final String[] DNA_HUMAN = {"TTGCGA", "CAGTGC", "TTATGT", "AGAAGG", "TCCCTA", "TCACTG"};

    public static final String DNA_SEQUENCE = "dnaSequence";
    public static final String FIELD_DNA_REQUIRED = "Field 'dna' is required";
    public static final String MUTANT_RESULT_SUCCESS = "The DNA sequence analyzed is MUTANT!!!";
    public static final String HUMANT_RESULT_SUCCESS = "The DNA sequence analyzed is Human";

    public static final Long COUNT_MUTANT = 40L;
    public static final Long COUNT_HUMANT = 100L;
    public static final String RATIO = "0.4";

    public static final String GENERAL_ERROR = "General Error";
    public static final String INVALID_SEQUENCE = "Invalid NxN DNA sequence";
    public static final String ERROR_GETTING_COUNT = "Error while getting count of DNA";
    public static final String ERROR_GENERAL_GETTING_COUNT = "Error while getting count of DNA: General Error";
}
