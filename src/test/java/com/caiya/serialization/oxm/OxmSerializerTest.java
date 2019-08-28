package com.caiya.serialization.oxm;

import com.alibaba.fastjson.JSON;
import com.caiya.serialization.Serializer;
import com.caiya.serialization.User;
import org.jibx.runtime.JiBXException;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.oxm.jibx.JibxMarshaller;
import org.springframework.oxm.xstream.XStreamMarshaller;

/**
 * OxmSerializerTest
 */
public class OxmSerializerTest {

    private Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
    private Serializer<Object> serializer = new OxmSerializer(jaxb2Marshaller, jaxb2Marshaller);

    {
        jaxb2Marshaller.setClassesToBeBound(User.class);
    }

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
    public void testSerializeAndDeserializeJibx() throws JiBXException {
//        JibxMarshaller jibxMarshaller = new JibxMarshaller();
//        jibxMarshaller.setTargetClass(User.class);
//        jibxMarshaller.afterPropertiesSet();
//        serializer = new OxmSerializer(jibxMarshaller, jibxMarshaller);
//        testSerializeAndDeserialize();
    }

    @Test
    public void testSerializeAndDeserializeXStream() throws JiBXException {
        XStreamMarshaller xStreamMarshaller = new XStreamMarshaller();
        xStreamMarshaller.setSupportedClasses(User.class);
        serializer = new OxmSerializer(xStreamMarshaller, xStreamMarshaller);
        testSerializeAndDeserialize();
    }

}
