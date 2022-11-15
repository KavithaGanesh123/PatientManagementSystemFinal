package com.patient.patientmanagement.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.patient.patientmanagement.entity.Patient;
import com.patient.patientmanagement.entity.PatientAddress;
import com.patient.patientmanagement.entity.PatientIdentifier;
import com.patient.patientmanagement.repository.PatientDetailsRepository;
import com.patient.patientmanagement.schemaobjects.AddressListSO;
import com.patient.patientmanagement.schemaobjects.PatientDetailsSO;
import com.patient.patientmanagement.schemaobjects.PatientIdentifierSO;
import com.patient.patientmanagement.util.GenericUtils;

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
	public PatientDetailsSO savePatientDetails(PatientDetailsSO patientDetailsSO) throws ValidationException {

		Patient newPatient = new Patient();

		PatientDetailsSO newPatientDetailsSO = new PatientDetailsSO();

		try {
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

		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}

		newPatient = patientDetailsRepository.saveAndFlush(newPatient);

		try {
			genericUtils.copyProperties(newPatient, newPatientDetailsSO);
			

			newPatientDetailsSO.setPatientId(newPatient.getPatientId());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}

		return newPatientDetailsSO;
	}

	
	/**
	 * @param patientDetailsSO Details of patient matching
	 * patien_id, name or government id.
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

		else if (patientDetailsSO.getPatientName() != null) {
			List<Patient> patientList = patientDetailsRepository
					.getPatientDetailsByName(patientDetailsSO.getPatientName());
			for (Patient patientObj : patientList) {
				PatientDetailsSO newPatientDetailSO = new PatientDetailsSO();
				newPatientDetailsSO = copyPatientToPatientSO(newPatientDetailSO, patientObj);
				patientDetailsSOList.add(newPatientDetailsSO);
			}
		}

		else if (patientDetailsSO.getPatientIdentifiers().size() > 0) {

			if (patientDetailsSO.getPatientIdentifiers().size() == 1) {

				PatientIdentifier patientIdentifier = new PatientIdentifier();
				patient = patientDetailsRepository.getPatientDetailsByGovtId(patientIdentifier.getIdentifierNumber());
				patientDetailsSO = copyPatientToPatientSO(newPatientDetailsSO, patient);
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
			msg = "Please enter a valid Patient ID";
		}

		if (patientDetailsSO.getPatientId() != 0) {
			

			Patient existingPatient = patientDetailsRepository.getPatientDetailsById(patientDetailsSO.getPatientId());
			if (existingPatient == null) {
				msg = "Invalid Patient ID";
			} else {
				patientDetailsRepository.deleteById(patientDetailsSO.getPatientId());
				msg = "Patient Successfully Deleted";

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
	 */
	public PatientDetailsSO updatePatientDetails(PatientDetailsSO patientDetailsSO) throws ValidationException {

		PatientDetailsSO newPatientDetailsSO = new PatientDetailsSO();
		

		if (patientDetailsSO.getPatientId() != 0) {
			Patient patientToUpdate = patientDetailsRepository.getPatientDetailsById(patientDetailsSO.getPatientId());

			try {
				genericUtils.copyProperties(patientDetailsSO, patientToUpdate);
				patientToUpdate.getPatientAddresses().clear();
				List<PatientAddress> patientAddressList = new ArrayList<>();
				for (AddressListSO address : patientDetailsSO.getPatientAddresses()) {
					PatientAddress patientAddress = new PatientAddress();
					genericUtils.copyProperties(address, patientAddress);
					patientAddressList.add(patientAddress);
				}

				patientToUpdate.setPatientAddresses(patientAddressList);
				List<PatientIdentifier> patientIdentifierList = new ArrayList<>();
				patientToUpdate.getPatientIdentifier().clear();
				for (PatientIdentifierSO identifier : patientDetailsSO.getPatientIdentifiers()) {
					PatientIdentifier patientIdentifier = new PatientIdentifier();
					genericUtils.copyProperties(identifier, patientIdentifier);
					patientIdentifierList.add(patientIdentifier);
				}
				patientToUpdate.setPatientIdentifier(patientIdentifierList);

			} catch (IllegalAccessException | InvocationTargetException e) {
				throw new RuntimeException(e);
			}

			patientToUpdate = patientDetailsRepository.saveAndFlush(patientToUpdate);

			newPatientDetailsSO = copyPatientToPatientSO(patientDetailsSO, patientToUpdate);

		}

		return newPatientDetailsSO;
	}

	/**
	 * 
	 * * This method copies Patient Object to PatientSO Object
	 * 
	 * @param patientDetailsSO, Patient 
	 * 
	 * @return patientDetailsSO
	 * 
	 */
	public PatientDetailsSO copyPatientToPatientSO(PatientDetailsSO patientDetailsSO, Patient patient) {

		try {
			genericUtils.copyProperties(patient, patientDetailsSO);
			List<AddressListSO> patientAddressSOList = new ArrayList<>();
			for (PatientAddress patientAddress : patient.getPatientAddresses()) {
				AddressListSO addressSO = new AddressListSO();
				genericUtils.copyProperties(patientAddress, addressSO);
				patientAddressSOList.add(addressSO);
			}
			patientDetailsSO.setPatientAddresses(patientAddressSOList);

			List<PatientIdentifierSO> patientIdentifierSOList = new ArrayList<>();
			for (PatientIdentifier patientIdentifier : patient.getPatientIdentifier()) {
				PatientIdentifierSO identifierSO = new PatientIdentifierSO();
				genericUtils.copyProperties(patientIdentifier, identifierSO);
				patientIdentifierSOList.add(identifierSO);
			}
			patientDetailsSO.setPatientIdentifiers(patientIdentifierSOList);

		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}

		return patientDetailsSO;

	}

}
