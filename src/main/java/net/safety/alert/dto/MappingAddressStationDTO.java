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
public class MappingAddressStationDTO {
	// DO NOT CHANGE THE NAME OF THE ATTRIBUTES (USED IN JSON DESERIALIZATION)
	/**
	 * 
	 */
	private String address;
	/**
	 * 
	 */
	private String station;

	/**
	 * @return
	 */
	@JsonIgnore
	public boolean isValid() {
		return StringsUtil.isValid(address) && StringsUtil.isValid(station);
	}

	/**
	 * @return
	 */
	public FireStation toFireStation() {
		return new FireStation(station);
	}

	/**
	 * @return
	 */
	public Address toAddress() {
		return new Address(address, new FireStation(station));
	}

	/**
	 * @param address
	 * @return
	 */
	public static MappingAddressStationDTO toFireStationDTO(Address address) {
		String id = null;
		if (address.getFireStation() != null) {
			id = address.getFireStation().getId();
		}
		return new MappingAddressStationDTO(address.getName(), id);
	}

	/**
	 * @param address
	 * @return
	 */
	public static MappingAddressStationDTO toMappingAddressStationDTO(Address address) {
		MappingAddressStationDTO mappingAddressStationDTO = new MappingAddressStationDTO();
		if (address != null) {
			mappingAddressStationDTO.setAddress(address.getName());
			FireStation fireStation = address.getFireStation();
			if (fireStation != null) {
				mappingAddressStationDTO.setStation(fireStation.getId());
			}
		}
		return mappingAddressStationDTO;
	}
}
