package net.safety.alert.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.safety.alert.model.Address;
import net.safety.alert.model.FireStation;
import net.safety.alert.util.StringsUtil;

/**
 * @author trimok
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FireStationDTO {
	/**
	 * 
	 */
	private String fireStation;

	/**
	 * @return : the validity of the firestation field
	 */
	@JsonIgnore
	public boolean isValid() {
		return StringsUtil.isValid(fireStation);
	}

	/**
	 * @return : an address object from the firestation field
	 */
	public Address toAddress() {
		return new Address("", new FireStation(fireStation));
	}

	/**
	 * @param address
	 *            : a Address object
	 * @return : a FireStationDTO object
	 */
	public static FireStationDTO toFireStationDTO(Address address) {
		FireStationDTO fireStationDTO = new FireStationDTO();
		if (address != null) {
			FireStation fireStation = address.getFireStation();
			if (fireStation != null) {
				fireStationDTO.setFireStation(fireStation.getId());
			}
		}
		return fireStationDTO;
	}
}
