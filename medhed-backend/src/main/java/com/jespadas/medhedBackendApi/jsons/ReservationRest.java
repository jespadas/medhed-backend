package com.jespadas.medhedBackendApi.jsons;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationRest {

	@JsonProperty("locator")
	private String locator;

	@JsonProperty("hospitalId")
	private Long hospitalId;

	@JsonProperty("date")
	private Date date;

	@JsonProperty("patient")
	private Long patient;

	@JsonProperty("shiftId")
	private Long shiftId;

	@JsonProperty("reservationId")
	private Long reservationId;

	@JsonProperty("shift")
	private List<ShiftRest> shifts;

	public String getLocator() {
		return locator;
	}
	
	public Long getReservationId() {
		return reservationId;
	}

	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}

	public void setLocator(String locator) {
		this.locator = locator;
	}

	public Long getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(Long hospitalId) {
		this.hospitalId = hospitalId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getPatient() {
		return patient;
	}

	public void setPatient(Long patient) {
		this.patient = patient;
	}

	public Long getShiftId() {
		return shiftId;
	}

	public void setShiftId(Long shiftId) {
		this.shiftId = shiftId;
	}
}
