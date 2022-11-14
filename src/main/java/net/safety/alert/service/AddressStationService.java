package net.safety.alert.service;

import static net.safety.alert.constants.HttpMessageConstants.CREATE_MAPPING_ADDRESS_STATION;
import static net.safety.alert.constants.HttpMessageConstants.DELETE_MAPPING_ADDRESS_STATION;
import static net.safety.alert.constants.HttpMessageConstants.MAPPING_ADDRESS_STATION_ALREADY_CREATED;
import static net.safety.alert.constants.HttpMessageConstants.MAPPING_ADDRESS_STATION_NOT_FOUND;
import static net.safety.alert.constants.HttpMessageConstants.MAPPING_ADDRESS_STATION_NOT_VALID;
import static net.safety.alert.constants.HttpMessageConstants.UPDATE_MAPPING_ADDRESS_STATION;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.safety.alert.exception.AddressStationAlreadyCreatedException;
import net.safety.alert.exception.AddressStationNotFoundException;
import net.safety.alert.exception.PersonNotValidException;
import net.safety.alert.model.Address;
import net.safety.alert.repository.IAddressStationRepository;

@Service
public class AddressStationService implements IAddressStationService {

	@Autowired
	IAddressStationRepository addressStationRepository;

	@Override
	public Address createMappingAddressStation(Address address) {
		if (!address.isValid()) {
			throw new PersonNotValidException(CREATE_MAPPING_ADDRESS_STATION, MAPPING_ADDRESS_STATION_NOT_VALID,
					address);
		}

		Address addressDatabase = getPersistent(address);

		if (addressDatabase == null || addressDatabase.getFireStation() == null) {
			return addressStationRepository.save(address);
		} else {
			throw new AddressStationAlreadyCreatedException(CREATE_MAPPING_ADDRESS_STATION,
					MAPPING_ADDRESS_STATION_ALREADY_CREATED, address);
		}
	}

	@Override
	public Address updateMappingAddressStation(Address address) {
		if (!address.isValid()) {
			throw new PersonNotValidException(UPDATE_MAPPING_ADDRESS_STATION, MAPPING_ADDRESS_STATION_NOT_VALID,
					address);
		}

		Address addressDatabase = getPersistent(address);

		if (addressDatabase != null) {
			return addressStationRepository.save(address);
		} else {
			throw new AddressStationAlreadyCreatedException(UPDATE_MAPPING_ADDRESS_STATION,
					MAPPING_ADDRESS_STATION_NOT_FOUND, address);
		}
	}

	@Override
	public void deleteMappingAddressStation(Address address) {
		if (!address.isValid()) {
			throw new PersonNotValidException(DELETE_MAPPING_ADDRESS_STATION, MAPPING_ADDRESS_STATION_NOT_VALID,
					address);
		}

		Address addressDatabase = getPersistent(address);

		if (address.equals(addressDatabase)) {
			addressStationRepository.delete(address);
		} else {
			throw new AddressStationNotFoundException(DELETE_MAPPING_ADDRESS_STATION, MAPPING_ADDRESS_STATION_NOT_FOUND,
					address);
		}
	}

	@Override
	public Address getPersistent(Address address) {
		return addressStationRepository.getPersistent(address);
	}
}
