package com.epam.jwd.carrentproject.controller;

/**
 * The {@code CommandException} class represents a checked exception from {@link Command}
 *
 * @see Exception
 *
 * @author Dmitry Murzo
 */
public class CommandException extends Exception {
    public CommandException() {
        super();
    }

    public CommandException(String message) {
        super(message);
    }

    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandException(Throwable cause) {
        super(cause);
    }
}
