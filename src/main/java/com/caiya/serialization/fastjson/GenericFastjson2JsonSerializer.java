package com.caiya.serialization.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.caiya.serialization.Serializer;
import com.caiya.serialization.exception.SerializationException;
import com.caiya.serialization.util.SerializationUtils;

import java.nio.charset.StandardCharsets;

/**
 * Generic JSON Serializer based on fastjson.
 */
public class GenericFastjson2JsonSerializer implements Serializer<Object> {

    private final static ParserConfig DEFAULT_REDIS_CONFIG = new ParserConfig();


    static {
        DEFAULT_REDIS_CONFIG.setAutoTypeSupport(true);
    }

    public GenericFastjson2JsonSerializer() {
    }

    @Override
    public byte[] serialize(Object object) throws SerializationException {
        if (object == null) {
            return SerializationUtils.EMPTY_ARRAY;
        }
        return JSON.toJSONBytes(object, SerializerFeature.WriteClassName);
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        return deserialize(bytes, Object.class);
    }

    public <T> T deserialize(byte[] bytes, Class<T> type) throws SerializationException {
        if (SerializationUtils.isEmpty(bytes)) {
            return null;
        }
        if (type == null) {
            throw new IllegalArgumentException("Deserialization type must not be null!");
        }
        return JSON.parseObject(new String(bytes, StandardCharsets.UTF_8), type, DEFAULT_REDIS_CONFIG);
    }


}
