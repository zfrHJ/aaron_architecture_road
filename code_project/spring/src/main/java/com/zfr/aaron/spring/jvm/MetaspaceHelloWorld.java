package com.zfr.aaron.spring.jvm;

public class MetaspaceHelloWorld {

    public static void main(String[] args) {
        String message ="helloword";
        System.out.println(message);
        sayHello("nihao");
    }

    private static void sayHello(String nihao) {
        System.out.println("nihao,aaron");
    }

}
