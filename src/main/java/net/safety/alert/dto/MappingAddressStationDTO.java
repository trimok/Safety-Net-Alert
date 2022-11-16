package net.safety.alert.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.safety.alert.model.Address;
import net.safety.alert.model.FireStation;
import net.safety.alert.util.StringsUtil;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MappingAddressStationDTO {
	private String address;
	private String station;

	@JsonIgnore
	public boolean isValid() {
		return StringsUtil.isValid(address) && StringsUtil.isValid(station);
	}

	public FireStation toFireStation() {
		return new FireStation(station);
	}

	public Address toAddress() {
		return new Address(address, new FireStation(station));
	}

	public static MappingAddressStationDTO toFireStationDTO(Address address) {
		String id = null;
		if (address.getFireStation() != null) {
			id = address.getFireStation().getId();
		}
		return new MappingAddressStationDTO(address.getName(), id);
	}
}