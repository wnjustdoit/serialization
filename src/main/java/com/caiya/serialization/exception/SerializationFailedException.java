package com.caiya.serialization.exception;

/**
 * Wrapper for the native IOException (or similar).
 *
 * @author wangnan
 * @since 1.0
 */
public class SerializationFailedException extends NestedRuntimeException {

    /**
     * Construct a {@code SerializationException} with the specified detail message.
     *
     * @param message the detail message
     */
    public SerializationFailedException(String message) {
        super(message);
    }

    /**
     * Construct a {@code SerializationException} with the specified detail message
     * and nested exception.
     *
     * @param message the detail message
     * @param cause   the nested exception
     */
    public SerializationFailedException(String message, Throwable cause) {
        super(message, cause);
    }

}
