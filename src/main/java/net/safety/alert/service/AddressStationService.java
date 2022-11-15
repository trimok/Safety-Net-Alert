package net.safety.alert.service;

import static net.safety.alert.constants.HttpMessageConstants.CREATE_MAPPING_ADDRESS_STATION;
import static net.safety.alert.constants.HttpMessageConstants.DELETE_MAPPING_ADDRESS_STATION;
import static net.safety.alert.constants.HttpMessageConstants.MAPPING_ADDRESS_STATION_ALREADY_CREATED;
import static net.safety.alert.constants.HttpMessageConstants.MAPPING_ADDRESS_STATION_NOT_FOUND;
import static net.safety.alert.constants.HttpMessageConstants.MAPPING_ADDRESS_STATION_NOT_VALID;
import static net.safety.alert.constants.HttpMessageConstants.UPDATE_MAPPING_ADDRESS_STATION;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.safety.alert.dto.AddressDTO;
import net.safety.alert.dto.FireStationDTO;
import net.safety.alert.dto.MappingAddressStationDTO;
import net.safety.alert.exception.AddressStationAlreadyCreatedException;
import net.safety.alert.exception.PersonNotValidException;
import net.safety.alert.model.Address;
import net.safety.alert.repository.IAddressStationRepository;

@Service
public class AddressStationService implements IAddressStationService {

	@Autowired
	IAddressStationRepository addressStationRepository;

	@Override
	public MappingAddressStationDTO createMappingAddressStation(MappingAddressStationDTO mappingAddressStationDTO) {
		Address address = mappingAddressStationDTO.toAddress();
		if (!address.isValid()) {
			throw new PersonNotValidException(CREATE_MAPPING_ADDRESS_STATION, MAPPING_ADDRESS_STATION_NOT_VALID,
					address);
		}

		Address addressDatabase = getPersistent(address);

		if (addressDatabase == null || addressDatabase.getFireStation() == null) {
			return MappingAddressStationDTO.toFireStationDTO(addressStationRepository.save(address));
		} else {
			throw new AddressStationAlreadyCreatedException(CREATE_MAPPING_ADDRESS_STATION,
					MAPPING_ADDRESS_STATION_ALREADY_CREATED, address);
		}
	}

	@Override
	public MappingAddressStationDTO updateMappingAddressStation(MappingAddressStationDTO mappingAddressStationDTO) {
		Address address = mappingAddressStationDTO.toAddress();
		if (!address.isValid()) {
			throw new PersonNotValidException(UPDATE_MAPPING_ADDRESS_STATION, MAPPING_ADDRESS_STATION_NOT_VALID,
					address);
		}

		Address addressDatabase = getPersistent(address);

		if (addressDatabase != null) {
			return MappingAddressStationDTO.toFireStationDTO(addressStationRepository.save(address));
		} else {
			throw new AddressStationAlreadyCreatedException(UPDATE_MAPPING_ADDRESS_STATION,
					MAPPING_ADDRESS_STATION_NOT_FOUND, address);
		}
	}

	@Override
	public void deleteMappingAddressStationByFireStation(FireStationDTO fireStationDTO) {
		Address address = fireStationDTO.toAddress();
		if (!fireStationDTO.isValid()) {
			throw new PersonNotValidException(DELETE_MAPPING_ADDRESS_STATION, MAPPING_ADDRESS_STATION_NOT_VALID,
					address);
		}

		addressStationRepository.deleteByFireStation(address);
	}

	@Override
	public void deleteMappingAddressStationByAddress(AddressDTO addressDTO) {
		Address address = addressDTO.toAddress();
		if (!addressDTO.isValid()) {
			throw new PersonNotValidException(DELETE_MAPPING_ADDRESS_STATION, MAPPING_ADDRESS_STATION_NOT_VALID,
					address);
		}

		addressStationRepository.deleteByAddress(address);
	}

	@Override
	public Address getPersistent(Address address) {
		return addressStationRepository.getPersistent(address);
	}
}
