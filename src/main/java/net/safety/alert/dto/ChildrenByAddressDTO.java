package net.safety.alert.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.safety.alert.view.IChildrenByAddress;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChildrenByAddressDTO {
	private Iterable<IChildrenByAddress> children;
	private List<AdultDTO> adults;
}
