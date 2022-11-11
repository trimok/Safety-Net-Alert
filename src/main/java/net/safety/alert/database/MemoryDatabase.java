package net.safety.alert.database;

import java.util.List;

import lombok.Data;
import net.safety.alert.model.Person;

@Data
public class MemoryDatabase {
	private List<Person> persons;
	public static MemoryDatabase instance = new MemoryDatabase();

	public static MemoryDatabase getInstance() {
		return instance;
	}
}
