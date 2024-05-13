package by.aston.jdbc.exeption;

public class NotEmptyException extends RuntimeException {

    public NotEmptyException(String message) {
        super(message);
    }
}