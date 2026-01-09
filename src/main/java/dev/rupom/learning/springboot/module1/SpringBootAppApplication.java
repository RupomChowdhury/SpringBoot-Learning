package dev.rupom.learning.springboot.module1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

//@SpringBootApplication
public class SpringBootAppApplication implements CommandLineRunner {
	@Autowired
	PaymentSevices obj; //by default singleton
	@Autowired
	PaymentSevices obj2;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		obj.pay();
		System.out.println(obj.hashCode());
		obj2.pay();
		System.out.println(obj2.hashCode()); //obj and obj2 is same bc of singleton(default). if @Scope("prototype or other parameter") is used then things will be different!
	}
}
