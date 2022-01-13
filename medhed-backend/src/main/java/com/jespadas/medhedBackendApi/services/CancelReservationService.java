package com.jespadas.medhedBackendApi.services;

import com.jespadas.medhedBackendApi.exceptions.ReservationException;

public interface CancelReservationService {

	public String deleteReservation(String locator) throws ReservationException;

}
