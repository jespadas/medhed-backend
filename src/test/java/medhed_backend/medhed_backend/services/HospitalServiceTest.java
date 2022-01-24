package medhed_backend.medhed_backend.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.jespadas.medhedBackendApi.entities.Hospital;
import com.jespadas.medhedBackendApi.entities.Reservation;
import com.jespadas.medhedBackendApi.entities.Room;
import com.jespadas.medhedBackendApi.entities.Shift;
import com.jespadas.medhedBackendApi.exceptions.ReservationException;
import com.jespadas.medhedBackendApi.jsons.HospitalRest;
import com.jespadas.medhedBackendApi.repositories.HospitalRepository;
import com.jespadas.medhedBackendApi.services.impl.HospitalServiceImpl;

public class HospitalServiceTest {

	private static final Long HOSPITAL_ID = 1L;

	private static final String NAME = "Hospital Purpan";
	private static final String DESCRIPTION = "Hospital Purpan description";
	private static final String ADDRES = "Purpan";
	private static final String IMAGE = "www.image.com";

	public static final Hospital HOSPITAL = new Hospital();
	public static final List<Shift> SHIFT_LIST = new ArrayList<>();
	public static final List<Room> ROOM_LIST = new ArrayList<>();
	public static final List<Reservation> RESERVATION_LIST = new ArrayList<>();

	@Mock
	HospitalRepository hospitalRepository;

	@InjectMocks
	HospitalServiceImpl hospitalServiceImpl;

	@Before
	public void init() throws ReservationException {
		MockitoAnnotations.initMocks(this);

		HOSPITAL.setName(NAME);
		HOSPITAL.setDescription(DESCRIPTION);
		HOSPITAL.setAddress(ADDRES);
		HOSPITAL.setId(HOSPITAL_ID);
		HOSPITAL.setImage(IMAGE);
		HOSPITAL.setShifts(SHIFT_LIST);
		HOSPITAL.setRooms(ROOM_LIST);
		HOSPITAL.setReservations(RESERVATION_LIST);

	}

	@Test
	public void getHospitalByIdTest() throws ReservationException {
		Mockito.when(hospitalRepository.findById(HOSPITAL_ID)).thenReturn(Optional.of(HOSPITAL));
		hospitalServiceImpl.getHospitalById(HOSPITAL_ID);
	}

	@Test(expected = ReservationException.class)
	public void getHospitalByIdTestError() throws ReservationException {
		Mockito.when(hospitalRepository.findById(HOSPITAL_ID)).thenReturn(Optional.empty());
		hospitalServiceImpl.getHospitalById(HOSPITAL_ID);
		fail();
	}

	@Test
	public void getHospitalsTest() throws ReservationException {
		final Hospital hospital = new Hospital();
		Mockito.when(hospitalRepository.findAll()).thenReturn(Arrays.asList(hospital));
		final List<HospitalRest> response = hospitalServiceImpl.getHospitals();
		assertNotNull(response);
		assertFalse(response.isEmpty());
		assertEquals(response.size(), 1);
	}

}
