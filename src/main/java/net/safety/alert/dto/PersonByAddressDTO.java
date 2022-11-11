package net.safety.alert.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.safety.alert.view.IPersonsByAddress;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonByAddressDTO {
	Iterable<IPersonsByAddress> persons;
}
