package org.example.question1.json;

public class JsonConversionException extends RuntimeException {
    public JsonConversionException(String message) {
        super(message);
    }
    public JsonConversionException(String message, Throwable cause) {
        super(message, cause);
    }
}