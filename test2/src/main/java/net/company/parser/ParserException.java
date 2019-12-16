package net.company.parser;
/**
 * class ParserException
 * класс используется для передачи Exception "наверх"
 */
public class ParserException extends Exception {
    public ParserException() {
    }

    public ParserException(String message) {
        super(message);
    }

    public ParserException(String message, Throwable cause) {
        super(message, cause);
    }
}
