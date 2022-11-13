package Exceptions;

/**
 * Вилючення при передачі некоректних даних
 */
public class InputValuesZeroOrNegativeException extends ProjectMainException {
    public InputValuesZeroOrNegativeException(String message) {
        super(message);
    }
}
