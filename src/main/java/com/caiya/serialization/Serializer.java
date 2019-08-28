package com.caiya.serialization;

import com.caiya.serialization.exception.SerializationException;
import com.caiya.serialization.fastjson.GenericFastjson2JsonSerializer;
import com.caiya.serialization.jdk.JdkSerializationSerializer;
import com.caiya.serialization.jdk.StringSerializer;
import com.caiya.serialization.oxm.OxmSerializer;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import javax.xml.bind.JAXBContext;

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

    /**
     * Obtain a {@link Serializer} using java serialization.<br />
     * <strong>Note:</strong> Ensure that your domain objects are actually {@link java.io.Serializable serializable}.
     *
     * @return never {@literal null}.
     */
    static Serializer<Object> java() {
        return java(null);
    }

    /**
     * Obtain a {@link Serializer} using java serialization with the given {@link ClassLoader}.<br />
     * <strong>Note:</strong> Ensure that your domain objects are actually {@link java.io.Serializable serializable}.
     *
     * @param classLoader the {@link ClassLoader} to use for deserialization. Can be {@literal null}.
     * @return new instance of {@link Serializer}. Never {@literal null}.
     * @since 2.1
     */
    static Serializer<Object> java(ClassLoader classLoader) {
        return new JdkSerializationSerializer(classLoader);
    }

    /**
     * Obtain a simple {@link java.lang.String} to {@literal byte[]} (and back) serializer using
     * {@link java.nio.charset.StandardCharsets#UTF_8 UTF-8} as the default {@link java.nio.charset.Charset}.
     *
     * @return never {@literal null}.
     */
    static Serializer<String> string() {
        return new StringSerializer();
    }

    /**
     * Obtain a {@link Serializer} that can read and write JSON using
     * <a href="https://github.com/alibaba/fastjson">Jackson</a>.
     *
     * @return never {@literal null}.
     */
    static Serializer<Object> json() {
        return new GenericFastjson2JsonSerializer();
    }

    /**
     * Obtain a {@link Serializer} that can read and write XML using JAXB2.
     *
     * @return never {@literal null}.
     * @see JAXBContext
     */
    static Serializer<Object> xml() {
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        return new OxmSerializer(jaxb2Marshaller, jaxb2Marshaller);
    }
}
