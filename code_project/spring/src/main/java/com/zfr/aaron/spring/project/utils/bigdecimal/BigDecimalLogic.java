package com.zfr.aaron.spring.project.utils.bigdecimal;

import java.math.BigDecimal;

/**
 * @author wenfei.guo
 * @date: 2018/5/2 15:15
 * @Description: TODD
 */
public class BigDecimalLogic {
    private static final int ZERO = 0;

    private final BigDecimal amount;

    public BigDecimalLogic(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimalLogic(String amount) {
        if (amount == null) {
            this.amount = null;
        } else {
            this.amount = new BigDecimal(amount);
        }
    }

    /**
     * 是否 等于
     */
    public boolean eq(BigDecimal bigDecimal) {
        if(bigDecimal == null){
            return false;
        }
        return this.amount.compareTo(bigDecimal) == ZERO;
    }
    /**
     * 是否 等于
     */
    public boolean eq(String value) {
        if(value == null){
            return false;
        }
        return eq(new BigDecimal(value));
    }
    /**
     * 是否 大于
     */
    public boolean gt(BigDecimal bigDecimal) {
        return this.amount.compareTo(bigDecimal) > ZERO;
    }
    /**
     * 是否 大于
     */
    public boolean gt(String value) {
        return gt(new BigDecimal(value));
    }
    /**
     * 是否 大于等于
     */
    public boolean gteq(BigDecimal bigDecimal) {
        return this.amount.compareTo(bigDecimal) >= ZERO;
    }
    /**
     * 是否 大于等于
     */
    public boolean gteq(String value) {
        return gteq(new BigDecimal(value));
    }
    /**
     * 是否 小于
     */
    public boolean lt(BigDecimal bigDecimal) {
        return this.amount.compareTo(bigDecimal) < ZERO;
    }
    /**
     * 是否 小于
     */
    public boolean lt(String value) {
        return lt(new BigDecimal(value));
    }
    /**
     * 是否 小于等于
     */
    public boolean lteq(BigDecimal bigDecimal) {
        return this.amount.compareTo(bigDecimal) <= ZERO;
    }
    /**
     * 是否 小于等于
     */
    public boolean lteq(String value) {
        return lteq(new BigDecimal(value));
    }
    /**
     * 是否 等于=0
     */
    public boolean isZero() {
        return eq(BigDecimal.ZERO);
    }
    /**
     * 是否 为null等于=0
     */
    public boolean isNullOrZero() {
        return this.amount == null || isZero();
    }
}
