package net.safety.alert.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhoneByStationDTO {
	Iterable<IPhoneByStation> phones;
}
