package com.zfr.aaron.spring.test;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zfr
 */
@Data
@ToString
public class SeckillCopy implements Serializable {
    private Long seckillId;

    private String name;

    private Integer number;

    private Date startTime;

    private Date endTime;

    private Date createTime;


}
