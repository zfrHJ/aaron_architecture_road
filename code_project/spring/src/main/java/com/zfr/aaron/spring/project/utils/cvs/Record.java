package com.zfr.aaron.spring.project.utils.cvs;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class Record {


    private Long employeeId;

    private String depName;

    private String employeeName;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private String signInTime;

    private Integer id;
}
