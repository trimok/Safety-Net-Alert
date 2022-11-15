package net.safety.alert.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhonesByStationDTO {
	List<String> phones = new ArrayList<>();

	public static PhonesByStationDTO toPhonesByStationDTO(List<String> phones) {
		PhonesByStationDTO phonesByStationDTO = new PhonesByStationDTO();
		phonesByStationDTO.setPhones(phones);

		return phonesByStationDTO;
	}
}
