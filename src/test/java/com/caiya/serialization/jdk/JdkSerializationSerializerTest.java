package com.caiya.serialization.jdk;

import com.alibaba.fastjson.JSON;
import com.caiya.serialization.Serializer;
import com.caiya.serialization.User;
import org.junit.Assert;
import org.junit.Test;

/**
 * JdkSerializationSerializerTest
 *
 * @author wangnan
 * @since 1.0
 */
public class JdkSerializationSerializerTest {

    private static Serializer<Object> serializer = new JdkSerializationSerializer();

    @Test
    public void testSerializeAndDeserialize() {
        User user = new User();
        user.setName("我是tom");
        byte[] outPut = serializer.serialize(user);
        Assert.assertNotNull(outPut);
        Assert.assertTrue(outPut.length > 0);

        Object result = serializer.deserialize(outPut);
        Assert.assertTrue(result instanceof User);
        Assert.assertEquals(JSON.toJSONString(result), JSON.toJSONString(user));
    }

    @Test
    public void testSerializeAndDeserialize2() {
        serializer = new JdkSerializationSerializer(this.getClass().getClassLoader());
        testSerializeAndDeserialize();
    }

}