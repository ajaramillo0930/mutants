package co.com.mercadolibre.mutants.detector;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static co.com.mercadolibre.mutants.util.Constant.CHARACTER_SEQUENCE;
import static co.com.mercadolibre.mutants.util.Constant.MINIMUM_MUTANT_SEQUENCE;

/**
 * Clase utilitaria para analizar un ADN.
 */
@Component
public class MutantDetector {

    /**
     * Metodo que permite validar una secuencia ADN.
     *
     * @param dnaSequence - Secuencia de ADN.
     * @return True si es Mutante; False si es Humano.
     */
    public boolean isMutant(String[] dnaSequence) {

        int mutantsDetected = 0;
        int totalRows = dnaSequence.length;
        int totalColumns = dnaSequence[0].length();

        for (int row = 0; row < totalRows; row++) {
            for (int column = 0; column < totalColumns; column++) {

                if (mutantsDetected >= MINIMUM_MUTANT_SEQUENCE) break;

                Character actualChar = dnaSequence[row].charAt(column);

                if (column + CHARACTER_SEQUENCE < totalColumns && analyzeRightSide(row, column, actualChar, dnaSequence))
                    mutantsDetected++;

                if (row + CHARACTER_SEQUENCE < totalRows) {
                    if (analyzeDownSide(row, column, actualChar, dnaSequence)) mutantsDetected++;
                    if (column + CHARACTER_SEQUENCE < totalColumns && analyzeDiagonalRightSide(row, column, actualChar, dnaSequence))
                        mutantsDetected++;
                    if (column - CHARACTER_SEQUENCE >= 0 && analyzeDiagonalLeftSide(row, column, actualChar, dnaSequence))
                        mutantsDetected++;
                }
            }
        }
        return mutantsDetected >= MINIMUM_MUTANT_SEQUENCE;
    }

    /**
     * Metodo que permite analizar hacia la derecha en la matriz.
     *
     * @param row             - Fila.
     * @param column          - Columna.
     * @param actualCharacter - Caracter actual.
     * @param dnaSequence     - Secuencia de ADN.
     * @return True si detecta una secuencia Mutante; False de lo contrario.
     */
    private boolean analyzeRightSide(Integer row, Integer column, Character actualCharacter, String[] dnaSequence) {
        return IntStream.rangeClosed(column + 1, column + CHARACTER_SEQUENCE)
                .mapToObj(pos -> actualCharacter.equals(dnaSequence[row].charAt(pos)))
                .allMatch(equal -> equal);
    }

    /**
     * Metodo que permite analizar hacia la abajo en la matriz.
     *
     * @param row             - Fila.
     * @param column          - Columna.
     * @param actualCharacter - Caracter actual.
     * @param dnaSequence     - Secuencia de ADN.
     * @return True si detecta una secuencia Mutante; False de lo contrario.
     */
    private boolean analyzeDownSide(Integer row, Integer column, Character actualCharacter, String[] dnaSequence) {
        return IntStream.rangeClosed(row + 1, row + CHARACTER_SEQUENCE)
                .mapToObj(pos -> actualCharacter.equals(dnaSequence[pos].charAt(column)))
                .allMatch(equal -> equal);
    }

    /**
     * Metodo que permite analizar diagonal a la derecha en la matriz.
     *
     * @param row             - Fila.
     * @param column          - Columna.
     * @param actualCharacter - Caracter actual.
     * @param dnaSequence     - Secuencia de ADN.
     * @return True si detecta una secuencia Mutante; False de lo contrario.
     */
    private boolean analyzeDiagonalRightSide(Integer row, Integer column, Character actualCharacter, String[] dnaSequence) {
        AtomicInteger j = new AtomicInteger(column + 1);
        return IntStream.rangeClosed(row + 1, row + CHARACTER_SEQUENCE)
                .mapToObj(pos -> {
                    boolean equal = actualCharacter.equals(dnaSequence[pos].charAt(j.get()));
                    j.getAndIncrement();
                    return equal;
                }).allMatch(equal -> equal);
    }

    /**
     * Metodo que permite analizar diagonal a la izquierda en la matriz.
     *
     * @param row             - Fila.
     * @param column          - Columna.
     * @param actualCharacter - Caracter actual.
     * @param dnaSequence     - Secuencia de ADN.
     * @return True si detecta una secuencia Mutante; False de lo contrario.
     */
    private boolean analyzeDiagonalLeftSide(Integer row, Integer column, Character actualCharacter, String[] dnaSequence) {
        AtomicInteger j = new AtomicInteger(column - 1);
        return IntStream.rangeClosed(row + 1, row + CHARACTER_SEQUENCE)
                .mapToObj(pos -> {
                    boolean equal = actualCharacter.equals(dnaSequence[pos].charAt(j.get()));
                    j.getAndDecrement();
                    return equal;
                }).allMatch(equal -> equal);
    }

}
