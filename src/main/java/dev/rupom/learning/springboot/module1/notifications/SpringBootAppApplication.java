package dev.rupom.learning.springboot.module1.notifications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

//@SpringBootApplication
public class SpringBootAppApplication implements CommandLineRunner {
//	@Autowired //injecting dependency (field injection-must avoid in productions)

//	private final NotificationService notificationServiceObj;
	@Autowired
	Map<String,NotificationService> notificationServiceMap = new HashMap<>();

	/*public IntroductionProjectApplication(@Qualifier("smsNotificationService") NotificationService notificationServiceObj) { //constructor dependency injection (DI-best and recommended)
		this.notificationServiceObj = notificationServiceObj;
	}*/
//	public IntroductionProjectApplication(NotificationService notificationServiceObj) { //constructor dependency injection (DI-best and recommended)
//		this.notificationServiceObj = notificationServiceObj;
//	}
	public static void main(String[] args) {
		SpringApplication.run(SpringBootAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		notificationServiceObj = new EmailNotificationService();
//		notificationServiceObj.send("Hello World!");
/*		notificationServiceMap.get("email").send("Hello World!");
		notificationServiceMap.get("sms").send("Hello World!");*/
		for (var notificationService : notificationServiceMap.entrySet()){
			System.out.println(notificationService.getKey());
			notificationService.getValue().send("Hello World!");
		}
	}
}
