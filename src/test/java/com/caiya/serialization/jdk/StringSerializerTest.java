package com.caiya.serialization.jdk;

import com.caiya.serialization.Serializer;
import org.junit.Assert;
import org.junit.Test;

import java.util.Objects;

/**
 * StringSerializerTest
 *
 * @author wangnan
 * @since 1.0
 */
public class StringSerializerTest {

    static Serializer<String> stringSerializer = new StringSerializer();

    @Test
    public void testSerializeAndDeserialize() throws Exception {
        String input = "我是tom";
        byte[] outPut = stringSerializer.serialize(input);
        Assert.assertNotNull(outPut);
        Assert.assertTrue(outPut.length > 0);

        String result = stringSerializer.deserialize(outPut);
        Assert.assertTrue(Objects.equals(result, input));
    }
}