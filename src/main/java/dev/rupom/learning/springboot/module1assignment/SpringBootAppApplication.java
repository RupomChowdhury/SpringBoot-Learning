package dev.rupom.learning.springboot.module1assignment;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

//@SpringBootApplication
public class SpringBootAppApplication implements CommandLineRunner {
	private final CakeBaker cakeBaker;

	public SpringBootAppApplication(CakeBaker cakeBaker) {
		this.cakeBaker = cakeBaker;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		cakeBaker.bakeCake();
	}
}
