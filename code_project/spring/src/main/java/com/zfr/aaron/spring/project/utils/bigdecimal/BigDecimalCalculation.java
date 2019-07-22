package com.zfr.aaron.spring.project.utils.bigdecimal;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @Description: BigDecimal 计算 处理工具类
 * @author wenfeiguo
 * @date 2018年5月4日
 * @version V1.0
 */
public class BigDecimalCalculation {
    private final BigDecimal amount;

    public BigDecimalCalculation(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimalCalculation(String amount) {
        this.amount = new BigDecimal(amount);
    }
    /**
     * 加法计算
     * @param value
     * @return BigDecimalCalculation 需要再 result()--》BigDecimal
     */
    public BigDecimalCalculation plus(BigDecimal value) {
        return new BigDecimalCalculation(this.amount.add(value));
    }
    /**
     * 加法计算
     * @param value
     * @return BigDecimalCalculation 需要再 result()--》BigDecimal
     */
    public BigDecimalCalculation plus(String value) {
        return plus(new BigDecimal(value));
    }

    /**
     * 减法计算
     * @param value
     * @return BigDecimalCalculation 需要再 result()--》BigDecimal
     */
    public BigDecimalCalculation minus(BigDecimal value) {
        return new BigDecimalCalculation(this.amount.subtract(value));
    }
    /**
     * 减法计算
     * @param value
     * @return BigDecimalCalculation 需要再 result()--》BigDecimal
     */
    public BigDecimalCalculation minus(String value) {
        return minus(new BigDecimal(value));
    }
    /**
     * 乘法计算
     * @param value
     * @return BigDecimalCalculation 需要再 result()--》BigDecimal
     */
    public BigDecimalCalculation mul(BigDecimal value) {
        return new BigDecimalCalculation(this.amount.multiply(value));
    }
    /**
     * 乘法计算
     * @param value
     * @return BigDecimalCalculation 需要再 result()--》BigDecimal
     */
    public BigDecimalCalculation mul(String value) {
        return mul(new BigDecimal(value));
    }
    /**
     * 除法计算
     * @param value
     * @return BigDecimalCalculation 需要再 result()--》BigDecimal
     */
    public BigDecimalCalculation div(BigDecimal value) {
        return new BigDecimalCalculation(this.amount.divide(value, RoundingMode.HALF_EVEN));
    }
    /**
     * 除法计算
     * @param value
     * @return BigDecimalCalculation 需要再 result()--》BigDecimal
     */
    public BigDecimalCalculation div(String value) {
        return div(new BigDecimal(value));
    }
    /**
     * 除法计算
     * @param scale 精度
     * @return BigDecimalCalculation 需要再 result()--》BigDecimal
     */
    public BigDecimalCalculation div(BigDecimal value, int scale) {
        return new BigDecimalCalculation(this.amount.divide(value, scale, RoundingMode.HALF_EVEN));
    }
    /**
     * 除法计算
     * @param scale 精度
     * @return BigDecimalCalculation 需要再 result()--》BigDecimal
     */
    public BigDecimalCalculation div(String value, int scale) {
        return div(new BigDecimal(value), scale);
    }

    /**
     * 计算结果转换成 BigDecimal
     */
    public BigDecimal result() {
        return this.amount;
    }
    /**
     * 计算结果转换成 BigDecimal
     */
    public BigDecimal result(int scale) {

        return this.amount.setScale(scale,RoundingMode.HALF_UP);
    }
    /**
     * 计算结果转换成 BigDecimal
     */
    public BigDecimal result(int scale, RoundingMode roundingMode) {

        return this.amount.setScale(scale, roundingMode);
    }

}
