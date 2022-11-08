package net.safety.alert.model;

import lombok.Data;

@Data
public class Medication {
	private String name;
	private String quantity;
	public Medication(String medication) {
		String[] infos = medication.split(":");
		this.name = infos[0];
		this.quantity = infos[1];
	}
}
