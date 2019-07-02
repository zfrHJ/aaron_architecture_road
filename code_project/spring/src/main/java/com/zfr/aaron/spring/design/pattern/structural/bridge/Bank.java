package com.zfr.aaron.spring.design.pattern.structural.bridge;

/**
 *@author 繁荣Aaron
 */
public abstract class Bank {
    protected Account account;
    public Bank(Account account){
        this.account = account;
    }
    abstract Account openAccount();

}
