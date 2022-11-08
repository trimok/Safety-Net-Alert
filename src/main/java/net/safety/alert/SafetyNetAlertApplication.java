package net.safety.alert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import net.safety.alert.service.JsonService;

@SpringBootApplication
public class SafetyNetAlertApplication {
	@Autowired
	private JsonService jsonService;

	public static void main(String[] args) {
		SpringApplication.run(SafetyNetAlertApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void initDatabase() {
		jsonService.initDatabase(this);
	}
}
