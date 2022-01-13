package com.jespadas.medhedBackendApi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jespadas.medhedBackendApi.exceptions.ReservationException;
import com.jespadas.medhedBackendApi.jsons.HospitalRest;
import com.jespadas.medhedBackendApi.responses.ReservationResponse;
import com.jespadas.medhedBackendApi.services.HospitalService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/reservation-hospital" + "/v1")
public class HospitalController {

	@Autowired
	HospitalService hospitalService;

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "hospital" + "/{" + "hospitalId"
			+ "}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ReservationResponse<HospitalRest> getHospitalById(@PathVariable Long hospitalId)
			throws ReservationException {
		return new ReservationResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK",
				hospitalService.getHospitalById(hospitalId));
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "hospitals", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ReservationResponse<List<HospitalRest>> getHospitals() throws ReservationException {
		return new ReservationResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK", hospitalService.getHospitals());
	}

}
