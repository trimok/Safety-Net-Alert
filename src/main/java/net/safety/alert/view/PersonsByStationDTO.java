package net.safety.alert.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonsByStationDTO {
	private Iterable<IPersonsByStation> persons;
	private long childrenCount;
	private long adultCount;
}
