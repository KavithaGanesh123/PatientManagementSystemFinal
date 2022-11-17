package com.patient.patientmanagement.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.patient.patientmanagement.constants.PatientConstants;
import com.patient.patientmanagement.entity.Patient;
import com.patient.patientmanagement.entity.PatientAddress;
import com.patient.patientmanagement.entity.PatientIdentifier;
import com.patient.patientmanagement.repository.PatientDetailsRepository;
import com.patient.patientmanagement.schemaobjects.AddressListSO;
import com.patient.patientmanagement.schemaobjects.PatientDetailsSO;
import com.patient.patientmanagement.schemaobjects.PatientIdentifierSO;
import com.patient.patientmanagement.util.GenericUtils;
import com.patient.patientmanagement.validators.PatientValidator;

/**
 * @author KL105911
 *
 */
@Component
public class PatientDao {

	@Autowired
	private GenericUtils genericUtils;

	@Autowired
	private PatientDetailsRepository patientDetailsRepository;
	
	@Autowired
	   private PatientValidator patientValidator;
	   

	/**
	 * 
	 * * This method saves the new patient details.
	 * 
	 * @param patientDetailsSO Patient object
	 * 
	 * @return PatientDetailsSO Return saved Patient object
	 * 
	 * @throws ValidationException Throws ValidationException
	 */
	public PatientDetailsSO savePatientDetails(PatientDetailsSO patientDetailsSO) {

		Patient newPatient = new Patient();

		PatientDetailsSO newPatientDetailsSO = new PatientDetailsSO();
		// BeanPropertyBindingResult errors = new BeanPropertyBindingResult(patientDetailsSO, "patientDetailsSO");
		//ValidationUtils.invokeValidator(this.patientValidator, patientDetailsSO, (Errors)errors);
		
		//if(errors.getErrorCount() > 0){
		//	throw new ValidationException(errors.get);
		//	}

			genericUtils.copyProperties(patientDetailsSO, newPatient);
			List<PatientAddress> patientAddressList = new ArrayList<>();
			for (AddressListSO address : patientDetailsSO.getPatientAddresses()) {
				PatientAddress patientAddress = new PatientAddress();
				genericUtils.copyProperties(address, patientAddress);
				patientAddressList.add(patientAddress);
			}

			newPatient.setPatientAddresses(patientAddressList);
			List<PatientIdentifier> patientIdentifierList = new ArrayList<>();
			for (PatientIdentifierSO identifier : patientDetailsSO.getPatientIdentifiers()) {
				PatientIdentifier patientIdentifier = new PatientIdentifier();
				genericUtils.copyProperties(identifier, patientIdentifier);
				patientIdentifierList.add(patientIdentifier);
			}
			newPatient.setPatientIdentifier(patientIdentifierList);


		try {
		newPatient = patientDetailsRepository.saveAndFlush(newPatient);
		newPatientDetailsSO.setStatusMsg(PatientConstants.SAVE_SUCCESS);
		}catch(Exception e) {
			newPatientDetailsSO.setStatusMsg(PatientConstants.SAVE_FAILED);
		}

			genericUtils.copyProperties(newPatient, newPatientDetailsSO);
			newPatientDetailsSO=copyPatientToPatientSO(newPatientDetailsSO, newPatient);
			newPatientDetailsSO.setPatientId(newPatient.getPatientId());

	
		return newPatientDetailsSO;
	}

	/**
	 * @param patientDetailsSO Details of patient matching patien_id, name or
	 *                         government id.
	 * 
	 * @return Returns list of patients matching the search
	 * 
	 * @throws ValidationException Throws ValidationException
	 */
	public List<PatientDetailsSO> getPatientDetails(PatientDetailsSO patientDetailsSO) throws ValidationException {

		Patient patient = new Patient();

		PatientDetailsSO newPatientDetailsSO = new PatientDetailsSO();

		List<PatientDetailsSO> patientDetailsSOList = new ArrayList<>();
		

		if (patientDetailsSO.getPatientId() != 0) {
			patient = patientDetailsRepository.getPatientDetailsById(patientDetailsSO.getPatientId());
			patientDetailsSO = copyPatientToPatientSO(newPatientDetailsSO, patient);
			patientDetailsSOList.add(patientDetailsSO);
		}

		else if (patientDetailsSO.getPatientName() != null && !patientDetailsSO.getPatientName().isEmpty()) {
			List<Patient> patientList = patientDetailsRepository
					.getPatientDetailsByName(patientDetailsSO.getPatientName());
			for (Patient patientObj : patientList) {
				PatientDetailsSO newPatientDetailSO = new PatientDetailsSO();
				newPatientDetailsSO = copyPatientToPatientSO(newPatientDetailSO, patientObj);
				patientDetailsSOList.add(newPatientDetailsSO);
			}
		}

		else if (patientDetailsSO.getPatientIdentifiers()!=null && patientDetailsSO.getPatientIdentifiers().size() > 0) {

			if (patientDetailsSO.getPatientIdentifiers().size() == 1) {

				PatientIdentifierSO patientIdentifier = patientDetailsSO.getPatientIdentifiers().get(0);
				patient = patientDetailsRepository.getPatientDetailsByGovtId(patientIdentifier.getIdentifierNumber());
				if (patient != null) {
					patientDetailsSO = copyPatientToPatientSO(newPatientDetailsSO, patient);
					patientDetailsSOList.add(patientDetailsSO);
				}
			}
		}
		else {
			

			List<Patient> patientList = patientDetailsRepository
					.findAll();
			for (Patient patientObj : patientList) {
				PatientDetailsSO newPatientDetailSO = new PatientDetailsSO();
				newPatientDetailsSO = copyPatientToPatientSO(newPatientDetailSO, patientObj);
				patientDetailsSOList.add(newPatientDetailsSO);
			}
		
		}
		

		return patientDetailsSOList;
	}

	/**
	 * 
	 * * This method deletes or removes the existing patient.
	 * 
	 * @param patientDetailsSO Patient object
	 * 
	 * @return String Success or Failed removing patient
	 * 
	 * @throws ValidationException Throws ValidationException
	 */
	public String removePatientDetails(PatientDetailsSO patientDetailsSO) throws ValidationException {

		String msg = "";
		if (patientDetailsSO.getPatientId() == 0) {
			msg = PatientConstants.PATIENTID_EMPTY;
		}

		if (patientDetailsSO.getPatientId() != 0) {

			Patient existingPatient = patientDetailsRepository.getPatientDetailsById(patientDetailsSO.getPatientId());
			if (existingPatient == null) {
				msg = PatientConstants.INVALID_PATIENT;
			} else {
				patientDetailsRepository.deleteById(patientDetailsSO.getPatientId());
				msg = PatientConstants.DELETE_SUCCESS;

			}

		}

		return msg;
	}

	/**
	 * 
	 * * This method updates the existing patient details
	 * 
	 * @param patientDetailsSO Patient object
	 * 
	 * @return PatientDetailsSO Return the updated patient details
	 * 
	 * @throws ValidationException Throws ValidationException
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public PatientDetailsSO updatePatientDetails(Patient patientDetailsSO) throws ValidationException {

		PatientDetailsSO newPatientDetailsSO = new PatientDetailsSO();

		if (patientDetailsSO.getPatientId() != 0) {
			Patient patientToUpdate = patientDetailsRepository.getPatientDetailsById(patientDetailsSO.getPatientId());
			
			if(patientToUpdate != null) {
				genericUtils.copyProperties(patientDetailsSO, patientToUpdate);
				
				patientDetailsSO = patientDetailsRepository.saveAndFlush(patientToUpdate);
				
				genericUtils.copyProperties(patientDetailsSO, newPatientDetailsSO);
				
			}
		}

		return newPatientDetailsSO;
	}

//			try {
////				genericUtils.copyProperties(patientDetailsSO, patientToUpdate);
////				patientToUpdate.getPatientAddresses().clear();
////				List<PatientAddress> patientAddressList = new ArrayList<>();
////				for (AddressListSO address : patientDetailsSO.getPatientAddresses()) {
////					PatientAddress patientAddress = new PatientAddress();
////					genericUtils.copyProperties(address, patientAddress);
////					patientAddressList.add(patientAddress);
////				}
////
////				patientToUpdate.setPatientAddresses(patientAddressList);
////				List<PatientIdentifier> patientIdentifierList = new ArrayList<>();
////				patientToUpdate.getPatientIdentifier().clear();
////				for (PatientIdentifierSO identifier : patientDetailsSO.getPatientIdentifiers()) {
////					PatientIdentifier patientIdentifier = new PatientIdentifier();
////					genericUtils.copyProperties(identifier, patientIdentifier);
////					patientIdentifierList.add(patientIdentifier);
////				}
////				patientToUpdate.setPatientIdentifier(patientIdentifierList);
//				
//				try {
//					patientToUpdate = patientDetailsRepository.saveAndFlush(patientToUpdate);
//					
//					newPatientDetailsSO.setStatusMsg(null);
//				}catch(Exception e) {
//					
//				}
				
				
		

	/**
	 * 
	 * * This method copies Patient Object to PatientSO Object
	 * 
	 * @param patientDetailsSO Patient schema object
	 * @param patient          Patient entity object
	 * 
	 * @return patientDetailsSO
	 * 
	 */
	public PatientDetailsSO copyPatientToPatientSO(PatientDetailsSO patientDetailsSO, Patient patient) {

			genericUtils.copyProperties(patient, patientDetailsSO);
			List<AddressListSO> patientAddressSOList = new ArrayList<>();
			if (!patient.getPatientAddresses().isEmpty()) {
				for (PatientAddress patientAddress : patient.getPatientAddresses()) {
					AddressListSO addressSO = new AddressListSO();
					genericUtils.copyProperties(patientAddress, addressSO);
					patientAddressSOList.add(addressSO);
				}
				patientDetailsSO.setPatientAddresses(patientAddressSOList);
			}

			List<PatientIdentifierSO> patientIdentifierSOList = new ArrayList<>();

			if (!patient.getPatientIdentifier().isEmpty()) {
				for (PatientIdentifier patientIdentifier : patient.getPatientIdentifier()) {
					PatientIdentifierSO identifierSO = new PatientIdentifierSO();
					genericUtils.copyProperties(patientIdentifier, identifierSO);
					patientIdentifierSOList.add(identifierSO);
				}
				patientDetailsSO.setPatientIdentifiers(patientIdentifierSOList);
			}


		return patientDetailsSO;

	}

}
