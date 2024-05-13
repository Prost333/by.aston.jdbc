package by.aston.jdbc.exeption;


import by.aston.jdbc.exeption.model.ErrorResponse;

public interface ExceptionHandler {
    ErrorResponse handleException(Exception e);
}
