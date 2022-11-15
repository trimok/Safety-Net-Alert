package net.safety.alert.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChildrensByAddressDTO {
	private List<ChildrenByAddressDTO> children;
	private List<AdultDTO> adults;

	public static ChildrensByAddressDTO toChildrensByAddressDTO(List<ChildrenByAddressDTO> children,
			List<AdultDTO> adults) {
		ChildrensByAddressDTO childrensByAddressDTO = new ChildrensByAddressDTO();
		childrensByAddressDTO.setChildren(children);
		childrensByAddressDTO.setAdults(adults);
		return childrensByAddressDTO;
	}
}
