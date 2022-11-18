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
@AllArgsConstructor
@NoArgsConstructor
public class PersonsByStationDTO {
	/**
	 * 
	 */
	private List<PersonByStationDTO> persons = new ArrayList<>();
	/**
	 * 
	 */
	private Long childrenCount;
	/**
	 * 
	 */
	private Long adultCount;

	/**
	 * @param persons
	 * @param childrenCount
	 * @param adultCount
	 * @return
	 */
	public static PersonsByStationDTO toPersonsByStationDTO(List<PersonByStationDTO> persons, long childrenCount,
			long adultCount) {
		PersonsByStationDTO personsByStationDTO = new PersonsByStationDTO();
		personsByStationDTO.setAdultCount(adultCount);
		personsByStationDTO.setChildrenCount(childrenCount);
		personsByStationDTO.setPersons(persons);

		return personsByStationDTO;
	}
}
