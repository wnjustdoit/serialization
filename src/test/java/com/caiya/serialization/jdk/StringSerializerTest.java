package com.caiya.serialization.jdk;

import com.caiya.serialization.Serializer;
import org.junit.Assert;
import org.junit.Test;


/**
 * StringSerializerTest
 *
 * @author wangnan
 * @since 1.0
 */
public class StringSerializerTest {

    private Serializer<String> serializer = new StringSerializer();

    @Test
    public void testSerializeAndDeserialize() {
        String input = "我是tom";
        byte[] outPut = serializer.serialize(input);
        Assert.assertNotNull(outPut);
        Assert.assertTrue(outPut.length > 0);

        String result = serializer.deserialize(outPut);
        Assert.assertEquals(result, input);
    }
}