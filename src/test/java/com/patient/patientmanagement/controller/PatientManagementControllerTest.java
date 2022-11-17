/**
 * 
 */
package com.patient.patientmanagement.controller;

import static org.mockito.Mockito.lenient;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.patient.patientmanagement.constants.PatientConstants;
import com.patient.patientmanagement.schemaobjects.AddressListSO;
import com.patient.patientmanagement.schemaobjects.PatientDetailsSO;
import com.patient.patientmanagement.schemaobjects.PatientIdentifierSO;
import com.patient.patientmanagement.service.PatientManagementService;

/**
 * @author KL105911
 *
 */

@ExtendWith(MockitoExtension.class)
class PatientManagementControllerTest {

	PatientDetailsSO patientDetailsSOList = new PatientDetailsSO();
	@InjectMocks
	PatientManagementController controller;

	@Mock
	private PatientManagementService patientService;

	private PatientDetailsSO patientDetailsSO;

	private static ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private MockMvc mockMvc;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		patientDetailsSO = new PatientDetailsSO();

		patientDetailsSO.setPatientId(123);
		patientDetailsSO.setPatientName("Kavitha");
		patientDetailsSO.setContactNumber("9645422876");
		patientDetailsSO.setAlternateContactNumber("9856872933");

		PatientIdentifierSO patientIdentifier = new PatientIdentifierSO("Aadhar Card", "131232142424214");

		List<PatientIdentifierSO> patientIdentifierList = new ArrayList<>();

		patientIdentifierList.add(patientIdentifier);

		AddressListSO address = new AddressListSO("Addr Line", "Addr Line2", "Kerala", "India", "680004");

		List<AddressListSO> addressList = new ArrayList<>();

		addressList.add(address);
		
		MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

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
	 * @throws Exception 
	 */
	@Test
	void testSavePatientDetails() throws Exception {
		
		Mockito.when(patientService.savePatientDetails(ArgumentMatchers.any())).thenReturn(patientDetailsSO);
		String json = mapper.writeValueAsString(patientDetailsSO);
		
		this.mockMvc.perform(post("http://localhost:8080/patient/savePatientDetails").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk())
				.andExpect(jsonPath("$.patientId", Matchers.equalTo(123)))
				.andExpect(jsonPath("$.patientName", Matchers.equalTo("Kavitha")));

		
	}

	/**
	 * Test method for
	 * {@link com.patient.patientmanagement.controller.PatientManagementController#getPatientDetails(com.patient.patientmanagement.schemaobjects.PatientDetailsSO)}.
	 * @throws Exception 
	 */
	@Test
	void testGetPatientDetails() throws Exception {
		
		List<PatientDetailsSO> patientSOList =new ArrayList<>();
		patientSOList.add(patientDetailsSO);
		
		Mockito.when(patientService.getPatientDetails(ArgumentMatchers.any())).thenReturn(patientSOList);
		String json = mapper.writeValueAsString(patientDetailsSO);
		
		this.mockMvc.perform(post("http://localhost:8080/patient/getPatientDetails").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].patientId", Matchers.equalTo(123)))
				.andExpect(jsonPath("$[0].patientName", Matchers.equalTo("Kavitha")));
	}

	/**
	 * Test method for
	 * {@link com.patient.patientmanagement.controller.PatientManagementController#updatePatientDetails(com.patient.patientmanagement.schemaobjects.PatientDetailsSO)}.
	 * @throws Exception 
	 */
	@Test
	void testUpdatePatientDetails() throws Exception {
		// fail("Not yet implemented");
		Mockito.when(patientService.updatePatientDetails(ArgumentMatchers.any())).thenReturn(patientDetailsSO);
		String json = mapper.writeValueAsString(patientDetailsSO);
		
		this.mockMvc.perform(post("http://localhost:8080/patient/updatePatientDetails").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk())
				.andExpect(jsonPath("$.patientId", Matchers.equalTo(123)))
				.andExpect(jsonPath("$.patientName", Matchers.equalTo("Kavitha")));
	}

	/**
	 * Test method for
	 * {@link com.patient.patientmanagement.controller.PatientManagementController#removePatientDetails(com.patient.patientmanagement.schemaobjects.PatientDetailsSO)}.
	 * @throws Exception 
	 */
	@Test
	void testRemovePatientDetails() throws Exception {
		
		String msg=PatientConstants.DELETE_SUCCESS;
		
		lenient().when(patientService.removePatientDetails(ArgumentMatchers.any())).thenReturn(msg);
		String json = mapper.writeValueAsString(patientDetailsSO);
		
		this.mockMvc.perform(post("http://localhost:8080/patient/savePatientDetails").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk());
				
	}

}
