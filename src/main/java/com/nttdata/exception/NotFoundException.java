package com.nttdata.exception;

/**
 * Custom exception class with specified message.
 *
 * @author Catanas Kaj
 */
public class NotFoundException extends RuntimeException {

    /**
     * Constructor for NotFoundException.
     *
     * @param message exception message
     */
    public NotFoundException(String message) {
        super(message);
    }
}
