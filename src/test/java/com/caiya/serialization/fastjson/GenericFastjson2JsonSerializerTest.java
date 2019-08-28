package com.caiya.serialization.fastjson;

import com.alibaba.fastjson.JSON;
import com.caiya.serialization.Serializer;
import com.caiya.serialization.User;
import org.junit.Assert;
import org.junit.Test;

public class GenericFastjson2JsonSerializerTest {

    private Serializer<Object> jsonSerializer = Serializer.json();

    @Test
    public void testSerializeAndDeserialize() {
        User user = new User();
        user.setName("我是tom");
        byte[] outPut = jsonSerializer.serialize(user);
        Assert.assertNotNull(outPut);
        Assert.assertTrue(outPut.length > 0);

        User result = (User) jsonSerializer.deserialize(outPut);
        Assert.assertNotNull(result);
        Assert.assertEquals(JSON.toJSONString(result), JSON.toJSONString(user));
    }


}
