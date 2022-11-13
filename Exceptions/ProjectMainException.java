package Exceptions;

/**
 * Предок усіх виключень
 */
public class ProjectMainException extends Exception {
    public ProjectMainException(String message) {
        super("Виявлено помилку: " + message);
    }
}
