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
public class ChildrensByAddressDTO {
	/**
	 * 
	 */
	private List<ChildrenByAddressDTO> children;
	/**
	 * 
	 */
	private List<AdultByAddressDTO> adults;

	/**
	 * @param children
	 *            : the list of children
	 * @param adults
	 *            ; the list of adults
	 * @return : a ChildrensByAddressDTO object
	 */
	public static ChildrensByAddressDTO toChildrensByAddressDTO(List<ChildrenByAddressDTO> children,
			List<AdultByAddressDTO> adults) {
		ChildrensByAddressDTO childrensByAddressDTO = new ChildrensByAddressDTO();
		childrensByAddressDTO.setChildren(children);
		childrensByAddressDTO.setAdults(adults);
		return childrensByAddressDTO;
	}
}
