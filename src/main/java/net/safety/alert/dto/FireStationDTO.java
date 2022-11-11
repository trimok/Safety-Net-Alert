package net.safety.alert.dto;

import lombok.Data;
import net.safety.alert.util.StringsUtil;

@Data
public class FireStationDTO {
	private String address;
	private String station;

	public boolean isValid() {
		return StringsUtil.isValid(address) && StringsUtil.isValid(station);
	}
}
