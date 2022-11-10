package net.safety.alert.view;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChildrenByAddressDTO {
	private Iterable<IChildrenByAddress> children;
	private List<AdultDTO> adults;
}
