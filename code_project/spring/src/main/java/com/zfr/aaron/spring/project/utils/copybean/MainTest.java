package com.zfr.aaron.spring.project.utils.copybean;

import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

public class MainTest {
    public static void main(String[] args) {
        TestDTo testVo = new TestDTo();
        testVo.setCode("aaron");
        testVo.setName("繁荣");

        List<Integer> list =  new ArrayList();

        list.add(1);
        list.add(2);

        testVo.setList(list);


        TestMapper mapper = Mappers.getMapper(TestMapper.class);
        TestVo vo = mapper.getVo(testVo);
        System.out.println(vo);

    }
}
