package medhed_backend.medhed_backend.services;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;
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
import com.jespadas.medhedBackendApi.entities.Shift;
import com.jespadas.medhedBackendApi.exceptions.ReservationException;
import com.jespadas.medhedBackendApi.jsons.CreateReservationRest;
import com.jespadas.medhedBackendApi.repositories.HospitalRepository;
import com.jespadas.medhedBackendApi.repositories.ReservationRepository;
import com.jespadas.medhedBackendApi.repositories.ShiftRepository;
import com.jespadas.medhedBackendApi.services.impl.ReservationServiceImpl;

public class ReservationServiceTest {

	private static final Date DATE = new Date();
	private static final Long HOSPITAL_ID = 1L;
	private static final Long PATIENT = 1L;
	private static final Long SHIFT_ID = 1L;
	private static final String LOCATOR = "Hôpital Garonne/1986";
	private static final String SHIFTNAME = "NUIT_23_00-7_00";

	private static final String NAME = "Hospital Purpan";
	private static final String DESCRIPTION = "Hospital Purpan description";
	private static final String ADDRES = "Purpan";
	private static final String IMAGE = "www.image.com";

	CreateReservationRest CREATE_RESERVATION_REST = new CreateReservationRest();

	// TODO:
	// => JAutoDoc (commentaires)
	// firebugs (surefire) fiabilité du code
	// xDocLet (java doc externe)
	// Cobertura (parcour du code)
	// JavaNCSS (complexite du code, maintenance)
	// Checkstyle (validation des normes, harmonization)
	// => getReservationsTest()
	// final ReservationResponse<List<ReservationRest>> response =
	// reservationService.getReservations();

	private static final Hospital HOSPITAL = new Hospital();
	private static final Shift SHIFT = new Shift();
	private static final Reservation RESERVATION = new Reservation();
	private static final List<Shift> SHIFT_LIST = new ArrayList<>();

	private static final Optional<Hospital> OPTIONAL_HOSPITAL = Optional.of(HOSPITAL);
	private static final Optional<Shift> OPTIONAL_SHIFT = Optional.of(SHIFT);
	private static final Optional<Reservation> OPTIONAL_RESERVATION = Optional.of(RESERVATION);

	private static final Optional<Hospital> OPTIONAL_HOSPITAL_EMPTY = Optional.empty();
	private static final Optional<Shift> OPTIONAL_SHIFT_EMPTY = Optional.empty();
	private static final Optional<Reservation> OPTIONAL_RESERVATION_EMPTY = Optional.empty();

	@Mock
	private HospitalRepository hospitalRepository;

	@Mock
	private ShiftRepository shiftRepository;

	@Mock
	private ReservationRepository reservationRepository;

	@InjectMocks
	private ReservationServiceImpl reservationServiceImpl;

	@Before
	public void init() throws ReservationException {
		MockitoAnnotations.initMocks(this);

		HOSPITAL.setName(NAME);
		HOSPITAL.setDescription(DESCRIPTION);
		HOSPITAL.setAddress(ADDRES);
		HOSPITAL.setId(HOSPITAL_ID);
		HOSPITAL.setImage(IMAGE);
		HOSPITAL.setShifts(SHIFT_LIST);

		SHIFT.setHospital(HOSPITAL);
		SHIFT.setId(SHIFT_ID);
		SHIFT.setName(NAME);

		RESERVATION.setDate(DATE);
		RESERVATION.setHospital(HOSPITAL);
		RESERVATION.setId(HOSPITAL_ID);
		RESERVATION.setLocator(LOCATOR);
		RESERVATION.setPatient(PATIENT);
		RESERVATION.setShift(SHIFTNAME);

		CREATE_RESERVATION_REST.setDate(DATE);
		CREATE_RESERVATION_REST.setHospitalId(HOSPITAL_ID);
		CREATE_RESERVATION_REST.setPatient(PATIENT);
		CREATE_RESERVATION_REST.setShiftId(SHIFT_ID);

	}

	@Test
	public void createReservationTest() throws ReservationException {
		Mockito.when(hospitalRepository.findById(HOSPITAL_ID)).thenReturn(OPTIONAL_HOSPITAL);
		Mockito.when(shiftRepository.findById(SHIFT_ID)).thenReturn(OPTIONAL_SHIFT);
		Mockito.when(reservationRepository.findByShiftAndHospital(SHIFT.getName(), HOSPITAL.getId()))
				.thenReturn(OPTIONAL_RESERVATION_EMPTY);

		Mockito.when(reservationRepository.save(Mockito.any(Reservation.class))).thenReturn(new Reservation());
		reservationServiceImpl.createReservation(CREATE_RESERVATION_REST);
	}

	@Test(expected = ReservationException.class)
	public void createReservationFindByIdTestError() throws ReservationException {
		Mockito.when(hospitalRepository.findById(HOSPITAL_ID)).thenReturn(OPTIONAL_HOSPITAL_EMPTY);
		reservationServiceImpl.createReservation(CREATE_RESERVATION_REST);
		fail();
	}

	@Test(expected = ReservationException.class)
	public void createReservationShiftFindByIdTestError() throws ReservationException {
		Mockito.when(hospitalRepository.findById(HOSPITAL_ID)).thenReturn(OPTIONAL_HOSPITAL);
		Mockito.when(shiftRepository.findById(SHIFT_ID)).thenReturn(OPTIONAL_SHIFT_EMPTY);
		reservationServiceImpl.createReservation(CREATE_RESERVATION_REST);
		fail();
	}

	@Test(expected = ReservationException.class)
	public void createReservationShiftAndHospitalTestError() throws ReservationException {
		Mockito.when(hospitalRepository.findById(HOSPITAL_ID)).thenReturn(OPTIONAL_HOSPITAL);
		Mockito.when(shiftRepository.findById(SHIFT_ID)).thenReturn(OPTIONAL_SHIFT);
		Mockito.when(reservationRepository.findByShiftAndHospital(SHIFT.getName(), HOSPITAL.getId()))
				.thenReturn(OPTIONAL_RESERVATION);
		reservationServiceImpl.createReservation(CREATE_RESERVATION_REST);
		fail();
	}

	@Test(expected = ReservationException.class)
	public void createReservationInternalServerErrorTestErr() throws ReservationException {
		Mockito.when(hospitalRepository.findById(HOSPITAL_ID)).thenReturn(OPTIONAL_HOSPITAL);
		Mockito.when(shiftRepository.findById(SHIFT_ID)).thenReturn(OPTIONAL_SHIFT);
		Mockito.when(reservationRepository.findByShiftAndHospital(SHIFT.getName(), HOSPITAL.getId()))
				.thenReturn(OPTIONAL_RESERVATION_EMPTY);
		Mockito.doThrow(Exception.class).when(reservationRepository).save(Mockito.any(Reservation.class));
		reservationServiceImpl.createReservation(CREATE_RESERVATION_REST);
		fail();
	}

}
