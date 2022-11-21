package net.safety.alert.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author trimok
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailsByCityDTO {
	/**
	 * 
	 */
	private List<String> emails;

	/**
	 * @param emails
	 *            : the list of emails
	 * @return : a EmailsByCityDTO object
	 */
	public static EmailsByCityDTO toEmailsByCityDTO(List<String> emails) {
		return new EmailsByCityDTO(emails);
	}
}
