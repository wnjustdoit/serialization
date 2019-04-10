package com.caiya.serialization.jdk;

import com.alibaba.fastjson.JSON;
import com.caiya.serialization.Serializer;
import lombok.Data;
import org.junit.Assert;
import org.junit.Test;

import java.io.Serializable;
import java.util.Objects;

/**
 * JdkSerializationSerializerTest
 *
 * @author wangnan
 * @since 1.0
 */
public class JdkSerializationSerializerTest {

    static Serializer<Object> jdkSerializationSerializer = new JdkSerializationSerializer();

    @Test
    public void testSerializeAndDeserialize() throws Exception {
        User user = new User();
        user.setName("我是tom");
        byte[] outPut = jdkSerializationSerializer.serialize(user);
        Assert.assertNotNull(outPut);
        Assert.assertTrue(outPut.length > 0);

        Object result = jdkSerializationSerializer.deserialize(outPut);
        Assert.assertTrue(result instanceof User);
        Assert.assertTrue(Objects.equals(JSON.toJSONString(result), JSON.toJSONString(user)));
    }

    @Test
    public void testSerializeAndDeserialize2() throws Exception {
        jdkSerializationSerializer = new JdkSerializationSerializer(this.getClass().getClassLoader());
        testSerializeAndDeserialize();
    }

    @Data
    static class User implements Serializable {

        private static final long serialVersionUID = 5446326261249843062L;

        private String name;

    }
}