package com.caiya.serialization.exception;

/**
 * Generic exception indicating a com.caiya.serialization/deserialization error.
 *
 * @author Costin Leau
 */
public class SerializationException extends NestedRuntimeException {

    /**
     * Constructs a new <code>SerializationException</code> instance.
     *
     * @param msg
     * @param cause
     */
    public SerializationException(String msg, Throwable cause) {
        super(msg, cause);
    }

    /**
     * Constructs a new <code>SerializationException</code> instance.
     *
     * @param msg
     */
    public SerializationException(String msg) {
        super(msg);
    }
}
