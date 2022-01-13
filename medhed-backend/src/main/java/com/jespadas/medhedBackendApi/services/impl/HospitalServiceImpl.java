package com.jespadas.medhedBackendApi.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jespadas.medhedBackendApi.entities.Hospital;
import com.jespadas.medhedBackendApi.exceptions.NotFoundException;
import com.jespadas.medhedBackendApi.exceptions.ReservationException;
import com.jespadas.medhedBackendApi.jsons.HospitalRest;
import com.jespadas.medhedBackendApi.repositories.HospitalRepository;
import com.jespadas.medhedBackendApi.services.HospitalService;

@Service
public class HospitalServiceImpl implements HospitalService {

	@Autowired
	HospitalRepository hospitalRepository;

	public static final ModelMapper modelMapper = new ModelMapper();

	public HospitalRest getHospitalById(Long hospitalId) throws ReservationException {
		return modelMapper.map(getHospitalEntity(hospitalId), HospitalRest.class);
	}

	public List<HospitalRest> getHospitals() throws ReservationException {
		final List<Hospital> hospitalsEntity = hospitalRepository.findAll();
		return hospitalsEntity.stream().map(service -> modelMapper.map(service, HospitalRest.class))
				.collect(Collectors.toList());
	}

	private Hospital getHospitalEntity(Long hospitalId) throws ReservationException {
		return hospitalRepository.findById(hospitalId)
				.orElseThrow(() -> new NotFoundException("SNOT-404-1", "HOSPITAL_NOT_FOUND"));
	}

}
