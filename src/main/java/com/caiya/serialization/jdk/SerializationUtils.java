package com.caiya.serialization.jdk;

import com.caiya.serialization.Serializer;

import java.util.*;

/**
 * Utility class with various com.caiya.serialization-related methods.
 *
 * @author wangnan
 * @since 1.0
 */
public abstract class SerializationUtils {

    protected static final byte[] EMPTY_ARRAY = new byte[0];


    static boolean isEmpty(byte[] data) {
        return (data == null || data.length == 0);
    }

    @SuppressWarnings("unchecked")
    static <T extends Collection<?>> T deserializeValues(Collection<byte[]> rawValues, Class<T> type,
                                                         Serializer<?> serializer) {
        // connection in pipeline/multi mode
        if (rawValues == null) {
            return null;
        }

        Collection<Object> values = (List.class.isAssignableFrom(type) ? new ArrayList<>(rawValues.size())
                : new LinkedHashSet<>(rawValues.size()));
        for (byte[] bs : rawValues) {
            values.add(serializer.deserialize(bs));
        }

        return (T) values;
    }

    @SuppressWarnings("unchecked")
    public static <T> Set<T> deserialize(Set<byte[]> rawValues, Serializer<T> serializer) {
        return deserializeValues(rawValues, Set.class, serializer);
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> deserialize(List<byte[]> rawValues, Serializer<T> serializer) {
        return deserializeValues(rawValues, List.class, serializer);
    }

    @SuppressWarnings("unchecked")
    public static <T> Collection<T> deserialize(Collection<byte[]> rawValues, Serializer<T> serializer) {
        return deserializeValues(rawValues, List.class, serializer);
    }

    public static <T> Map<T, T> deserialize(Map<byte[], byte[]> rawValues, Serializer<T> serializer) {
        if (rawValues == null) {
            return null;
        }
        Map<T, T> ret = new LinkedHashMap<>(rawValues.size());
        for (Map.Entry<byte[], byte[]> entry : rawValues.entrySet()) {
            ret.put(serializer.deserialize(entry.getKey()), serializer.deserialize(entry.getValue()));
        }
        return ret;
    }

    @SuppressWarnings("unchecked")
    public static <HK, HV> Map<HK, HV> deserialize(Map<byte[], byte[]> rawValues, Serializer<HK> hashKeySerializer,
                                                   Serializer<HV> hashValueSerializer) {
        if (rawValues == null) {
            return null;
        }
        Map<HK, HV> map = new LinkedHashMap<HK, HV>(rawValues.size());
        for (Map.Entry<byte[], byte[]> entry : rawValues.entrySet()) {
            // May want to deserialize only key or value
            HK key = hashKeySerializer != null ? hashKeySerializer.deserialize(entry.getKey()) : (HK) entry.getKey();
            HV value = hashValueSerializer != null ? hashValueSerializer.deserialize(entry.getValue()) : (HV) entry
                    .getValue();
            map.put(key, value);
        }
        return map;
    }
}
