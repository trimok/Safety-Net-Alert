package net.safety.alert.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailsByCityDTO {
	private List<String> emails;

	public static EmailsByCityDTO toEmailsByCityDTO(List<String> emails) {
		return new EmailsByCityDTO(emails);
	}
}
