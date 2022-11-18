package net.safety.alert.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import net.safety.alert.database.Database;

/**
 * @author trimok
 *
 */
@SpringBootApplication(scanBasePackages = "net.safety.alert")
public class SafetyNetAlertApplication {

	/**
	 * 
	 */
	@Autowired
	private Database database;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(SafetyNetAlertApplication.class, args);
	}

	/**
	 * 
	 */
	@EventListener(ApplicationReadyEvent.class)
	public void initDatabase() {
		database.reset();
	}
}
