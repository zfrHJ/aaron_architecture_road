package com.zfr.aaron.spring.project.utils;

import com.zfr.aaron.spring.project.utils.bigdecimal.BigDecimalCalculation;
import com.zfr.aaron.spring.project.utils.bigdecimal.BigDecimalLogic;

import java.math.BigDecimal;

/**
 * @Description: BigDecimal 构造方法处理工具类
 * @author zfr
 * @version V1.0
 */
public class MyBigDecimalUtils {

    private MyBigDecimalUtils() {
    }

    /**
     * 用于bigdecimal 比较操作 前的构造
     * @param value
     * @return
     */
    public static BigDecimalLogic is(BigDecimal value) {
        return new BigDecimalLogic(value);
    }
    /**
     * 用于bigdecimal 比较操作 前的构造
     * @param value
     * @return
     */
    public static BigDecimalLogic is(String value) {
        return new BigDecimalLogic(value);
    }

    /**
     * 用于 bigdecimal 计算操作 前的构造
     * @param value
     * @return
     */
    public static BigDecimalCalculation cal(BigDecimal value) {
        return new BigDecimalCalculation(value);
    }
    /**
     * 用于比较bigdecimal 计算操作 前的构造
     * @param value
     * @return
     */
    public static BigDecimalCalculation cal(String value) {
        return new BigDecimalCalculation(new BigDecimal(value));
    }

    /**
     * 用于比较bigdecimal 比较操作 前的构造  if value值为null 则 用0代替
     * @param value
     * @return
     */
    public static BigDecimalLogic isBDIfNullAsZero(String value) {
        return new BigDecimalLogic(MyStringUtils.isObjEmpty(value) ? BigDecimal.ZERO : new BigDecimal(value));
    }
    /**
     * 用于比较bigdecimal 比较操作 前的构造if value值为null 则 用0代替
     * @param value
     * @return
     */
    public static BigDecimalLogic isBDIfNullAsZero(BigDecimal value) {
        return new BigDecimalLogic(MyStringUtils.isObjEmpty(value) ? BigDecimal.ZERO : value);
    }

    /**
     * 用于比较bigdecimal 计算操作 前的构造  if value值为null 则 用0代替
     * @param value
     * @return
     */
    public static BigDecimalCalculation calBDIfNullAsZero(String value) {
        return new BigDecimalCalculation(MyStringUtils.isObjEmpty(value) ? BigDecimal.ZERO : new BigDecimal(value));
    }
    /**
     * 用于比较bigdecimal 计算操作 前的构造if value值为null 则 用0代替
     * @param value
     * @return
     */
    public static BigDecimalCalculation calBDIfNullAsZero(BigDecimal value) {
        return new BigDecimalCalculation(MyStringUtils.isObjEmpty(value) ? BigDecimal.ZERO : value);
    }
}
