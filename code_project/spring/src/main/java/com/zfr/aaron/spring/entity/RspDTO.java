package com.zfr.aaron.spring.entity;

import java.io.Serializable;

/**
 * 返回结果 实体类
 * @author zfr
 *
 */
public class RspDTO implements Serializable {

    private Integer validationCode;

    private String message;

    public RspDTO(int validationCode, String message) {

        this.validationCode = validationCode;

        this.message = message;
    }

    public Integer getValidationCode() {
        return validationCode;
    }

    public void setValidationCode(Integer validationCode) {
        this.validationCode = validationCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
