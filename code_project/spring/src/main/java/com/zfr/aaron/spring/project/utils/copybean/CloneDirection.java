package com.zfr.aaron.spring.project.utils.copybean;

/**
 * 克隆方向类
 * @author fr
 */
public class CloneDirection {
    /**
     * 正向克隆：从VO->DTO，DTO->DO
     */
    public static final Integer FORWARD = 1;
    /**
     * 反向克隆：从DO->DTO，DTO->VO
     */
    public static final Integer OPPOSITE = 2;

    private CloneDirection() {

    }
}
