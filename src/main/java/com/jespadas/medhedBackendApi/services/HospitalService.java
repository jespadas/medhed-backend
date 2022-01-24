package com.jespadas.medhedBackendApi.services;

import java.util.List;

import com.jespadas.medhedBackendApi.exceptions.ReservationException;
import com.jespadas.medhedBackendApi.jsons.HospitalRest;

public interface HospitalService {

	HospitalRest getHospitalById(Long hospitalId) throws ReservationException;
	
	public List<HospitalRest> getHospitals() throws ReservationException;
	
}
