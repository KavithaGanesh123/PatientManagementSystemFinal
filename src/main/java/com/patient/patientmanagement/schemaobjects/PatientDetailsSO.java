package com.patient.patientmanagement.schemaobjects;

import java.util.Date;
import java.util.List;

/**
 * @author KL105911
 *
 */
public class PatientDetailsSO {
	
	private long patientId;
	
	private String patientName;
	
	private Date patientDOB;
	
	private List<PatientIdentifierSO> patientIdentifiers;
	
	private List<AddressListSO> patientAddresses;
	
	
	private String contactNumber;
	
	private String alternateContactNumber;

	/**
	 * @return the patient_id
	 */
	public long getPatientId() {
		return patientId;
	}

	/**
	 * @param patientId the patientId to set
	 */
	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}

	/**
	 * @return the patientName
	 */
	public String getPatientName() {
		return patientName;
	}

	/**
	 * @param patientName the patientName to set
	 */
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	/**
	 * @return the patientDOB
	 */
	public Date getPatientDOB() {
		return patientDOB;
	}

	/**
	 * @param patientDOB the patientDOB to set
	 */
	public void setPatientDOB(Date patientDOB) {
		this.patientDOB = patientDOB;
	}

	/**
	 * @return the patientAddresses
	 */
	public List<AddressListSO> getPatientAddresses() {
		return patientAddresses;
	}

	/**
	 * @param patientAddresses the patientAddresses to set
	 */
	public void setPatientAddresses(List<AddressListSO> patientAddresses) {
		this.patientAddresses = patientAddresses;
	}


	/**
	 * @return the contactNumber
	 */
	public String getContactNumber() {
		return contactNumber;
	}

	/**
	 * @param contactNumber the contactNumber to set
	 */
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	/**
	 * @return the alternateContactNumber
	 */
	public String getAlternateContactNumber() {
		return alternateContactNumber;
	}

	/**
	 * @param alternateContactNumber the alternateContactNumber to set
	 */
	public void setAlternateContactNumber(String alternateContactNumber) {
		this.alternateContactNumber = alternateContactNumber;
	}

	/**
	 * @return the patientIdentifiers
	 */
	public List<PatientIdentifierSO> getPatientIdentifiers() {
		return patientIdentifiers;
	}

	/**
	 * @param patientIdentifiers the patientIdentifiers to set
	 */
	public void setPatientIdentifiers(List<PatientIdentifierSO> patientIdentifiers) {
		this.patientIdentifiers = patientIdentifiers;
	}
	

}
