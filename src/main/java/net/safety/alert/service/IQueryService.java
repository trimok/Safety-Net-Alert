package net.safety.alert.service;

import net.safety.alert.view.IPersonsByStation;
import net.safety.alert.view.PersonsByStationDTO;

public interface IQueryService {

	Iterable<IPersonsByStation> findPersonsByStation(String station);

	PersonsByStationDTO findPersonsByStationDTO(String station);

}
