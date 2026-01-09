package dev.rupom.learning.springboot.module1;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

//@Component
public class PaymentSevices {
    public void pay(){
        System.out.println("Paying...");
    }

    @PostConstruct
    public void afterInitialization(){
        System.out.println("afterInitialization");
    }
    @PreDestroy
    public void beforeDestroy(){
        System.out.println("beforeDestroy");
    }
}
