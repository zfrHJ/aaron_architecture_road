package com.zfr.aaron.spring.test;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author zfr
 */
@Data
@ToString
public class SeckillTemp {
    private Long seckillId;

    private String name;

    private Integer number;

    private Date startTime;

    private Date endTime;

    private Date createTime;


}
