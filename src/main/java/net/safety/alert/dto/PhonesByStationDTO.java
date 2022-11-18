package net.safety.alert.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author trimok
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhonesByStationDTO {
	/**
	 * 
	 */
	List<String> phones = new ArrayList<>();

	/**
	 * @param phones
	 * @return
	 */
	public static PhonesByStationDTO toPhonesByStationDTO(List<String> phones) {
		PhonesByStationDTO phonesByStationDTO = new PhonesByStationDTO();
		phonesByStationDTO.setPhones(phones);

		return phonesByStationDTO;
	}
}
