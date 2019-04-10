package com.caiya.serialization.jdk;

import com.caiya.serialization.Serializer;
import com.caiya.serialization.exception.SerializationException;

import java.nio.charset.Charset;

/**
 * Simple String to byte[] (and back) serializer. Converts Strings into bytes and vice-versa using the specified charset
 * (by default UTF-8).
 * <p/>
 * Useful when the interaction with the Redis happens mainly through Strings.
 * <p/>
 * Does not perform any null conversion since empty strings are valid keys/values.
 *
 * @author wangnan
 * @since 1.0
 */
public class StringSerializer implements Serializer<String> {


    private final Charset charset;

    public StringSerializer() {
        this(Charset.forName("UTF-8"));
    }

    public StringSerializer(Charset charset) {
        if (charset == null)
            throw new IllegalArgumentException("Charset must not be null!");

        this.charset = charset;
    }

    @Override
    public byte[] serialize(String string) throws SerializationException {
        return (string == null ? null : string.getBytes(charset));
    }

    @Override
    public String deserialize(byte[] bytes) throws SerializationException {
        return (bytes == null ? null : new String(bytes, charset));
    }
}
