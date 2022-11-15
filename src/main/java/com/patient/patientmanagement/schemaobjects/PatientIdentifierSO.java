package com.patient.patientmanagement.schemaobjects;

/**
 * @author KL105911
 *
 */
public class PatientIdentifierSO {
	
	private String identifierType;
	
	private String identifierNumber;

	/**
	 * @return the identifierType
	 */
	public String getIdentifierType() {
		return identifierType;
	}

	/**
	 * @return the identifierNumber
	 */
	public String getIdentifierNumber() {
		return identifierNumber;
	}

	/**
	 * @param identifierType the identifierType to set
	 */
	public void setIdentifierType(String identifierType) {
		this.identifierType = identifierType;
	}

	/**
	 * @param identifierNumber the identifierNumber to set
	 */
	public void setIdentifierNumber(String identifierNumber) {
		this.identifierNumber = identifierNumber;
	}

	

}
