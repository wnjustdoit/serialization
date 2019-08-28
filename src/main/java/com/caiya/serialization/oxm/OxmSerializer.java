package com.caiya.serialization.oxm;

import com.caiya.serialization.Serializer;
import com.caiya.serialization.exception.SerializationException;
import com.caiya.serialization.util.SerializationUtils;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * XML Serialization Serializer based on spring-oxm.
 * <p/>
 *
 * @see <a href="https://docs.spring.io/spring/docs/current/spring-framework-reference/data-access.html#oxm">spring-oxm</a>
 */
public class OxmSerializer implements Serializer<Object> {

    private Marshaller marshaller;
    private Unmarshaller unmarshaller;

    public OxmSerializer(Marshaller marshaller, Unmarshaller unmarshaller) {
        this.marshaller = marshaller;
        this.unmarshaller = unmarshaller;
    }

    @Override
    public byte[] serialize(Object o) throws SerializationException {
        if (o == null) {
            return SerializationUtils.EMPTY_ARRAY;
        }

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        StreamResult result = new StreamResult(stream);

        try {
            marshaller.marshal(o, result);
        } catch (Exception ex) {
            throw new SerializationException("Cannot serialize object", ex);
        }
        return stream.toByteArray();
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        if (SerializationUtils.isEmpty(bytes)) {
            return null;
        }

        try {
            return unmarshaller.unmarshal(new StreamSource(new ByteArrayInputStream(bytes)));
        } catch (Exception ex) {
            throw new SerializationException("Cannot deserialize bytes", ex);
        }
    }

}
