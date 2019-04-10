package com.caiya.serialization;

import com.caiya.serialization.exception.SerializationException;

/**
 * Basic interface com.caiya.serialization and deserialization of Objects to byte arrays (binary data). It is recommended that
 * implementations are designed to handle null objects/empty arrays on com.caiya.serialization and deserialization side.
 *
 * @author wangnan
 * @since 1.0
 */
public interface Serializer<T> {

    /**
     * Serialize the given object to binary data.
     *
     * @param t object to serialize
     * @return the equivalent binary data
     */
    byte[] serialize(T t) throws SerializationException;

    /**
     * Deserialize an object from the given binary data.
     *
     * @param bytes object binary representation
     * @return the equivalent object instance
     */
    T deserialize(byte[] bytes) throws SerializationException;
}
