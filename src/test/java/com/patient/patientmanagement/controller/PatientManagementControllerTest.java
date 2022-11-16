/**
 * 
 */
package com.patient.patientmanagement.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import javax.xml.bind.ValidationException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.patient.patientmanagement.entity.Patient;
import com.patient.patientmanagement.schemaobjects.PatientDetailsSO;

/**
 * @author KL105911
 *
 */
class PatientManagementControllerTest {
	
	PatientDetailsSO patientDetailsSO = new PatientDetailsSO();
	@InjectMocks
	PatientManagementController controller;
	
	private ResponseEntity<PatientDetailsSO> response;
	
	private RestTemplate restTemplate;
	 

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.patient.patientmanagement.controller.PatientManagementController#savePatientDetails(com.patient.patientmanagement.schemaobjects.PatientDetailsSO)}.
	 */
	@Test
	void testSavePatientDetails() {
	}

	/**
	 * Test method for
	 * {@link com.patient.patientmanagement.controller.PatientManagementController#getPatientDetails(com.patient.patientmanagement.schemaobjects.PatientDetailsSO)}.
	 */
	@Test
	void testGetPatientDetails() throws ValidationException {
		/*
		try {
			patientDetailsSO.setPatientId(159);
			String addURI = "http://localhost:8080/patient/getPatientDetails";
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Accept", "application/json");
			headers.add("Content-Type", "application/json"); 
			HttpEntity<PatientDetailsSO> entity = new HttpEntity<PatientDetailsSO>(patientDetailsSO, headers); 
			
			response = this.restTemplate.postForEntity(addURI, patientDetailsSO, PatientDetailsSO.class);
			// responseBody = response.getBody().toString();
			assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
	}

			
		

	/**
	 * Test method for
	 * {@link com.patient.patientmanagement.controller.PatientManagementController#updatePatientDetails(com.patient.patientmanagement.schemaobjects.PatientDetailsSO)}.
	 */
	@Test
	void testUpdatePatientDetails() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.patient.patientmanagement.controller.PatientManagementController#removePatientDetails(com.patient.patientmanagement.schemaobjects.PatientDetailsSO)}.
	 */
	@Test
	void testRemovePatientDetails() {
	}

}
