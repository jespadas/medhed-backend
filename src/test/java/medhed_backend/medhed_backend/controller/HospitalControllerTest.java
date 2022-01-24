package medhed_backend.medhed_backend.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.jespadas.medhedBackendApi.controllers.HospitalController;
import com.jespadas.medhedBackendApi.exceptions.ReservationException;
import com.jespadas.medhedBackendApi.jsons.HospitalRest;
import com.jespadas.medhedBackendApi.jsons.ShiftRest;
import com.jespadas.medhedBackendApi.responses.ReservationResponse;
import com.jespadas.medhedBackendApi.services.HospitalService;

public class HospitalControllerTest {

	private static final Long HOSPITAL_ID = 1L;

	private static final String NAME = "Hospital Purpan";
	private static final String DESCRIPTION = "Hospital Purpan description";
	private static final String ADDRES = "Purpan";
	private static final String IMAGE = "www.image.com";

	private static final String SUCCES_STATUS = "Succes";
	private static final String SUCCES_CODE = "200";
	private static final String OK = "OK";

	public static final HospitalRest HOSPITAL_REST = new HospitalRest();
	public static final List<ShiftRest> SHIFT_LIST = new ArrayList<>();
	public static final List<HospitalRest> HOSPITAL_REST_LIST = new ArrayList<>();
	@Mock
	HospitalService hospitalService;

	@InjectMocks
	HospitalController hospitalController;

	@Before
	public void init() throws ReservationException {
		MockitoAnnotations.initMocks(this);

		HOSPITAL_REST.setName(NAME);
		HOSPITAL_REST.setDescription(DESCRIPTION);
		HOSPITAL_REST.setAddress(ADDRES);
		HOSPITAL_REST.setId(HOSPITAL_ID);
		HOSPITAL_REST.setImage(IMAGE);
		HOSPITAL_REST.setShifts(SHIFT_LIST);

		Mockito.when(hospitalService.getHospitalById(HOSPITAL_ID)).thenReturn(HOSPITAL_REST);
	}

	@Test
	public void getHospitalByIdTest() throws ReservationException {
		final ReservationResponse<HospitalRest> response = hospitalController.getHospitalById(HOSPITAL_ID);
		assertEquals(response.getStatus(), SUCCES_STATUS);
		assertEquals(response.getCode(), SUCCES_CODE);
		assertEquals(response.getMessage(), OK);
		assertEquals(response.getData(), HOSPITAL_REST);
	}

	@Test
	public void getHospitalsTest() throws ReservationException {
		final ReservationResponse<List<HospitalRest>> response = hospitalController.getHospitals();

		assertEquals(response.getStatus(), SUCCES_STATUS);
		assertEquals(response.getCode(), SUCCES_CODE);
		assertEquals(response.getMessage(), OK);
		assertEquals(response.getData(), HOSPITAL_REST_LIST);

	}

	// TODO tests for the controllers left

}
