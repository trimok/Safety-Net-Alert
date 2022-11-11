package net.safety.alert.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.safety.alert.view.IPersonsByStation;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonsByStationDTO {
	private Iterable<IPersonsByStation> persons;
	private Long childrenCount;
	private Long adultCount;
}
