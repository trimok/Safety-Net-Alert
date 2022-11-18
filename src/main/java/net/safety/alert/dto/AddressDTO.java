package net.safety.alert.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.safety.alert.model.Address;
import net.safety.alert.util.StringsUtil;

/**
 * @author trimok
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
	/**
	 * 
	 */
	private String address;

	/**
	 * @return true if address is not empty
	 */
	@JsonIgnore
	public boolean isValid() {
		return StringsUtil.isValid(address);
	}

	/**
	 * @return a new Address object with the address attribute
	 */
	public Address toAddress() {
		return new Address(address, null);
	}

	/**
	 * @param address
	 *            an Address object
	 * @return an AddressDTO object
	 */
	public static AddressDTO toAddressDTO(Address address) {
		AddressDTO addressDTO = new AddressDTO();
		if (address != null) {
			addressDTO.setAddress(address.getName());
		}
		return addressDTO;
	}
}
