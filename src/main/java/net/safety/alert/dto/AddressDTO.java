package net.safety.alert.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.safety.alert.model.Address;
import net.safety.alert.util.StringsUtil;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
	private String address;

	@JsonIgnore
	public boolean isValid() {
		return StringsUtil.isValid(address);
	}

	public Address toAddress() {
		return new Address(address, null);
	}

	public static AddressDTO toAddressDTO(Address address) {
		AddressDTO addressDTO = new AddressDTO();
		if (address != null) {
			addressDTO.setAddress(address.getName());
		}
		return addressDTO;
	}
}
