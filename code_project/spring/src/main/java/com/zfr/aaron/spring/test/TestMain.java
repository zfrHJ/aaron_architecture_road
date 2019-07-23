package com.zfr.aaron.spring.test;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.runtime.RuntimeSchema;

import java.io.*;
import java.util.Date;

public class TestMain {

    public static void main(String[] args) {
       /* SeckillCopy skc = new SeckillCopy();
        skc.setCreateTime(new Date());
        skc.setEndTime(new Date());
        skc.setName("测试数据002");
        skc.setNumber(2000);
        skc.setSeckillId(2L);
        skc.setStartTime(new Date());
        //序列化
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(skc);
            byte[] bytes = baos.toByteArray();
            System.out.println(bytes.length);
            SeckillCopy sc = (SeckillCopy) handle(bytes);
            System.out.println("反序列化后>>>>>>>>>>>>"+sc);
        } catch (IOException e) {
            System.out.println(e);
        }*/

        SeckillTemp st = new SeckillTemp();
        st.setCreateTime(new Date());
        st.setEndTime(new Date());
        st.setName("测试数据001");
        st.setNumber(1000);
        st.setSeckillId(1L);
        st.setStartTime(new Date());
        //序列化
        RuntimeSchema<SeckillTemp> schema = RuntimeSchema.createFrom(SeckillTemp.class);
        byte[] bytes = ProtostuffIOUtil.toByteArray(st, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
        System.out.println(bytes.length);
        //反序列化
        SeckillTemp sc = schema.newMessage();
        ProtostuffIOUtil.mergeFrom(bytes, sc, schema);
        System.out.println("反序列化后>>>>>>>>>>>>"+sc);
    }
    private static Serializable handle(byte[] bytes){
        try(ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais)	) {
            Serializable copy = (Serializable) ois.readObject();
            return copy;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


}
