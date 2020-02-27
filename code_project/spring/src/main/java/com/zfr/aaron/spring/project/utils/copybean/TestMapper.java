package com.zfr.aaron.spring.project.utils.copybean;

import org.mapstruct.Mapper;

@Mapper
public interface TestMapper {
    /**
     * 转换实体类
     * @param testDTo testDto
     * @return 返回实体
     *
     */
    TestVo getVo(TestDTo testDTo);




}
