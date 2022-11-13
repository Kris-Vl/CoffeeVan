package Exceptions;

/**
 * Помилка під час клонування
 */
public class CloneItemException extends ProjectMainException {
    public CloneItemException(String message) {
        super(message);
    }
}
