package dev.rupom.learning.springboot.module1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ConfigBean {
    @Scope("prototype")
    @Bean
    public PaymentSevices obj() {
        System.out.println("PaymentSevices");
        return new PaymentSevices();
    }
}
