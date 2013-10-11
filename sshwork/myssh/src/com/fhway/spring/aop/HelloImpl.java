package com.fhway.spring.aop;

public class HelloImpl implements Hello {
    public void sayHello(String name) {
        System.out.println("Hello, the world.Your name is " + name);
    }
}