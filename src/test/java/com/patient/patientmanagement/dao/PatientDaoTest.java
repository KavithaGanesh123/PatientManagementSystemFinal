/**
 * 
 */
package com.patient.patientmanagement.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import javax.xml.bind.ValidationException;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import com.patient.patientmanagement.repository.PatientDetailsRepository;
import com.patient.patientmanagement.schemaobjects.AddressListSO;
import com.patient.patientmanagement.schemaobjects.PatientDetailsSO;
import com.patient.patientmanagement.schemaobjects.PatientIdentifierSO;

/**
 * @author KL105911
 *
 */

class PatientDaoTest {
	
	
	@InjectMocks
	@Autowired
	PatientDao patientService;
	
	@Mock
    private PatientDetailsRepository patientDetailsRepository;
	
	AddressListSO address;
	
	PatientIdentifierSO patientIdentifier;

	/**
	 * Test method for
	 * {@link com.patient.patientmanagement.dao.PatientDao#savePatientDetails(com.patient.patientmanagement.schemaobjects.PatientDetailsSO)}.
	 */
	@Test
	void testSavePatientDetails() {
		
		
		
		PatientDetailsSO p1 = new PatientDetailsSO();
	p1.setPatientName("Tamara");
	p1.setContactNumber("1234567890");
	p1.setAlternateContactNumber("9645422387");
	
	

	//case 1: HttpStatus.CREATED
    Mockito.when(patientService.savePatientDetails(Mockito.any(PatientDetailsSO.class))).thenReturn(p1);

    //case 2: HttpStatus.BAD_REQUEST
    //Mockito.when(patientService.savePatientDetails(Mockito.any(PatientDetailsSO.class))).thenReturn(p1);




		PatientDetailsSO p2 = patientService.savePatientDetails(p1);
		assertThat(p2.getPatientId()).isNotNull();
		
	}

	/**
	 * Test method for
	 * {@link com.patient.patientmanagement.dao.PatientDao#getPatientDetails(com.patient.patientmanagement.schemaobjects.PatientDetailsSO)}.
	 */
	@Test
	void testGetPatientDetails() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.patient.patientmanagement.dao.PatientDao#removePatientDetails(com.patient.patientmanagement.schemaobjects.PatientDetailsSO)}.
	 */
	@Test
	void testRemovePatientDetails() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.patient.patientmanagement.dao.PatientDao#updatePatientDetails(com.patient.patientmanagement.schemaobjects.PatientDetailsSO)}.
	 */
	@Test
	void testUpdatePatientDetails() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.patient.patientmanagement.dao.PatientDao#copyPatientToPatientSO(com.patient.patientmanagement.schemaobjects.PatientDetailsSO, com.patient.patientmanagement.entity.Patient)}.
	 */
	@Test
	void testCopyPatientToPatientSO() {
		fail("Not yet implemented");
	}

}
