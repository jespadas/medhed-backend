package com.jespadas.medhedBackendApi.services;

import java.util.List;

import com.jespadas.medhedBackendApi.exceptions.ReservationException;
import com.jespadas.medhedBackendApi.jsons.CreateReservationRest;
import com.jespadas.medhedBackendApi.jsons.ReservationRest;

public interface ReservationService {

	ReservationRest getReservationById(Long reservationId) throws ReservationException;

	String createReservation(CreateReservationRest createReservationRest) throws ReservationException;

	public List<ReservationRest> getReservations() throws ReservationException;
	
	String updateReservation(CreateReservationRest createReservationRest) throws ReservationException;

}
