package net.safety.alert.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class PersonsGroupByAddressByListStationDTO {
	/**
	 * 
	 */
	private Map<String, List<PersonGroupByAddressByListStationDTO>> personsMap = new HashMap<>();

	/**
	 * @param personsMap
	 *            : a Map (String List(PersonGroupByAddressByListStationDTO))
	 * @return a PersonsGroupByAddressByListStationDTO object
	 */
	public static PersonsGroupByAddressByListStationDTO toPersonsGroupByAddressByListStationDTO(
			Map<String, List<PersonGroupByAddressByListStationDTO>> personsMap) {
		return new PersonsGroupByAddressByListStationDTO(personsMap);
	}
}
