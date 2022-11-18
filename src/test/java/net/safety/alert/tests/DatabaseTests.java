package net.safety.alert.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import net.safety.alert.database.Database;

@SpringBootTest(classes = net.safety.alert.config.SafetyNetAlertApplication.class)
public class DatabaseTests {
	@Autowired
	private Database database;

	@Test
	@DisplayName(value = "Test Loading Database")
	public void testLoadingDatabase() {
		try {
			database.reset();
			assert (true);
		} catch (Exception e) {
			assert (false);
		}
	}
}
