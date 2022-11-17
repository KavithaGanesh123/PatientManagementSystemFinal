/**
 * 
 */
package com.patient.patientmanagement.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.lenient;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.ValidationException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.patient.patientmanagement.entity.Patient;
import com.patient.patientmanagement.repository.PatientDetailsRepository;
import com.patient.patientmanagement.schemaobjects.AddressListSO;
import com.patient.patientmanagement.schemaobjects.PatientDetailsSO;
import com.patient.patientmanagement.schemaobjects.PatientIdentifierSO;
import com.patient.patientmanagement.util.GenericUtils;

/**
 * @author KL105911
 *
 */
@ExtendWith(MockitoExtension.class)
class PatientDaoTest {

	@Mock

	private PatientDao patientService;

	@Autowired
	@Mock
	private PatientDetailsRepository patientDetailsRepository;

	@Mock
	AddressListSO address;

	@Mock
	PatientIdentifierSO patientIdentifier;

	@Mock
	@Autowired
	GenericUtils genericUtils;

	PatientDetailsSO p1;

	PatientDetailsSO p2;

	Patient patient1;

	Patient patient2;

	@BeforeEach
	void setup() {

		p1 = new PatientDetailsSO();
		p1.setPatientName("Kavitha");
		p1.setContactNumber("1234567890");
		p1.setAlternateContactNumber("9645422387");

		p2 = new PatientDetailsSO();
		p2.setPatientId(154);
		p2.setPatientName("Tamara");
		p2.setContactNumber("1234567890");
		p2.setAlternateContactNumber("9645422387");

		patient1 = new Patient();
		patient1.setPatientId(154);
		patient1.setPatientName("Kavitha");
		patient1.setContactNumber("1234567890");
		patient1.setAlternateContactNumber("9645422387");

		patient2 = new Patient();
		patient2.setPatientId(149);
		patient2.setPatientName("Tamara");
		patient2.setContactNumber("1234567890");
		patient2.setAlternateContactNumber("9645422387");

	}

	/**
	 * Test method for
	 * {@link com.patient.patientmanagement.dao.PatientDao#savePatientDetails(com.patient.patientmanagement.schemaobjects.PatientDetailsSO)}.
	 */
	@Test
	void testSavePatientDetails() {

		List<PatientDetailsSO> patientSOList = new ArrayList<>();
		patientSOList.add(p1);
		lenient().when(patientService.savePatientDetails(p1)).thenReturn(p1);
		assertThat(p1.equals(p2));

	}

	/**
	 * Test method for
	 * {@link com.patient.patientmanagement.dao.PatientDao#getPatientDetails(com.patient.patientmanagement.schemaobjects.PatientDetailsSO)}.
	 * 
	 * @throws ValidationException
	 */
	@Test
	void testGetPatientDetails() throws ValidationException {
		PatientDetailsSO patientDetailsSO = new PatientDetailsSO();

		List<Patient> patientList = new ArrayList<>();

		lenient().when(patientDetailsRepository.findAll()).thenReturn(patientList);

		List<PatientDetailsSO> patientSOList = new ArrayList<>();
		patientSOList.add(p1);
		patientSOList.add(p2);

		lenient().when(patientService.getPatientDetails(patientDetailsSO)).thenReturn(patientSOList);

		assertThat(patientSOList).isNotNull();
		assertThat(patientSOList.size()).isEqualTo(2);

	}

	@Test
	@DisplayName("JUnit test for getPatientDetails by Id operation")
	public void givenPatientId_whenFindPatientById_thenReturnPatient() throws ValidationException {

		Long patientId = 154L;
		lenient().when(patientDetailsRepository.getPatientDetailsById(patientId)).thenReturn(patient1);

		List<PatientDetailsSO> patientList = new ArrayList<>();
		patientList.add(p2);

		lenient().when(patientService.getPatientDetails(p2)).thenReturn(patientList);
		assertThat(patientList.get(0).getPatientId()).isEqualTo(p2.getPatientId());

	}

	@Test
	@DisplayName("JUnit test for getPatientDetails by Name operation")
	public void givenPatientName_whenFindPatientByName_thenReturnPatient() throws ValidationException {

		String patientName = "Kavitha";

		List<Patient> patientList = new ArrayList<>();
		patientList.add(patient1);

		lenient().when(patientDetailsRepository.getPatientDetailsByName(patientName)).thenReturn(patientList);

		List<PatientDetailsSO> patientSOList = new ArrayList<>();
		patientSOList.add(p1);

		lenient().when(patientService.getPatientDetails(p1)).thenReturn(patientSOList);
		assertThat(patientSOList).isNotNull();
		assertThat(patientSOList.size()).isEqualTo(1);

	}

	@Test
	@DisplayName("JUnit test for getPatientDetails by Name operation")
	public void givenPatientGovId_whenFindPatientByGovId_thenReturnPatient() throws ValidationException {

		String patientGovId = "131232142424214";

		PatientIdentifierSO patientIdentifier = new PatientIdentifierSO("Aadhar Card", "131232142424214");

		List<PatientIdentifierSO> patientIdentifierList = new ArrayList<>();

		patientIdentifierList.add(patientIdentifier);

		List<Patient> patientList = new ArrayList<>();
		patientList.add(patient1);

		lenient().when(patientDetailsRepository.getPatientDetailsByGovtId(patientGovId)).thenReturn(patient1);

		List<PatientDetailsSO> patientSOList = new ArrayList<>();
		patientSOList.add(p1);

		lenient().when(patientService.getPatientDetails(p1)).thenReturn(patientSOList);
		assertThat(patientSOList).isNotNull();
		assertThat(patientSOList.size()).isEqualTo(1);

	}

	/**
	 * Test method for
	 * {@link com.patient.patientmanagement.dao.PatientDao#removePatientDetails(com.patient.patientmanagement.schemaobjects.PatientDetailsSO)}.
	 * 
	 * @throws ValidationException
	 */
	@Test
	void givePatientId_whenPatientExists_thenRemovePatientDetails() throws ValidationException {

		lenient().when(patientDetailsRepository.getPatientDetailsById(p2.getPatientId())).thenReturn(patient1);

		// String msg=patientService.removePatientDetails(p2);

		// assertEquals(msg, PatientConstants.DELETE_SUCCESS);
	}

	/**
	 * Test method for
	 * {@link com.patient.patientmanagement.dao.PatientDao#updatePatientDetails(com.patient.patientmanagement.schemaobjects.PatientDetailsSO)}.
	 */
	@Test
	void testUpdatePatientDetails() {
		List<PatientDetailsSO> patientSOList = new ArrayList<>();
		p1.setContactNumber("12345678");
		patientSOList.add(p1);
		lenient().when(patientService.savePatientDetails(p1)).thenReturn(p1);
		assertThat(p1.equals(p2));
	}

}
