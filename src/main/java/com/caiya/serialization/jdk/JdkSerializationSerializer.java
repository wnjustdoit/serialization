package com.caiya.serialization.jdk;

import com.caiya.serialization.Serializer;
import com.caiya.serialization.exception.NestedIOException;
import com.caiya.serialization.exception.SerializationException;
import com.caiya.serialization.exception.SerializationFailedException;

import java.io.*;

/**
 * Java Serialization serializer.
 *
 * @author wangnan
 * @since 1.0
 */
public class JdkSerializationSerializer implements Serializer<Object> {

    private final ClassLoader classLoader;

    public JdkSerializationSerializer() {
        this.classLoader = null;
    }

    public JdkSerializationSerializer(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }


    @Override
    public byte[] serialize(Object object) throws SerializationException {
        if (object == null) {
            return SerializationUtils.EMPTY_ARRAY;
        }

        try {
            if (!(object instanceof Serializable)) {
                throw new IllegalArgumentException(getClass().getSimpleName() + " requires a Serializable payload " +
                        "but received an object of type [" + object.getClass().getName() + "]");
            }

            ByteArrayOutputStream byteStream = new ByteArrayOutputStream(1024);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteStream);
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();

            return byteStream.toByteArray();
        } catch (Exception ex) {
            throw new SerializationException("Cannot serialize", ex);
        }
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        if (SerializationUtils.isEmpty(bytes)) {
            return null;
        }

        try {
            ByteArrayInputStream byteStream = new ByteArrayInputStream(bytes);
            try {
                ObjectInputStream objectInputStream = new ConfigurableObjectInputStream(byteStream, this.classLoader);
                try {
                    return objectInputStream.readObject();
                } catch (ClassNotFoundException ex) {
                    throw new NestedIOException("Failed to deserialize object type", ex);
                }
            } catch (Throwable ex) {
                throw new SerializationFailedException("Failed to deserialize payload. " +
                        "Is the byte array a result of corresponding com.caiya.serialization for " +
                        this.getClass().getSimpleName() + "?", ex);
            }
        } catch (Exception ex) {
            throw new SerializationException("Cannot deserialize", ex);
        }
    }
}
