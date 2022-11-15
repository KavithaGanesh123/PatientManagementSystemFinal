package com.patient.patientmanagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "PATIENT_ADDRESS")
public class PatientAddress {

	@TableGenerator(name = "patientIdSeq", table = "sys_uuid", pkColumnName = "uuid_code", pkColumnValue = "pat_id_seq", valueColumnName = "strt_num")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "patientadr_id")

	private long patientAdrId;

	private String addressLine1;
	private String addressLine2;

	private String state;

	private String country;

	private String pincode;

	@ManyToOne(targetEntity = Patient.class, optional = true, fetch = FetchType.LAZY)
	@JoinColumns(@JoinColumn(name = "patient_id", referencedColumnName = "patient_id", nullable = false))
	private Patient patientAdr;

	/**
	 * @return the patientAdrId
	 */
	public long getPatientAdrId() {
		return patientAdrId;
	}

	/**
	 * @param patientAdrId the patientAdrId to set
	 */
	public void setPatientAdrId(long patientAdrId) {
		this.patientAdrId = patientAdrId;
	}

	/**
	 * @return the addressLine1
	 */
	public String getAddressLine1() {
		return addressLine1;
	}

	/**
	 * @param addressLine1 the addressLine1 to set
	 */
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	/**
	 * @return the addressLine2
	 */
	public String getAddressLine2() {
		return addressLine2;
	}

	/**
	 * @param addressLine2 the addressLine2 to set
	 */
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the pincode
	 */
	public String getPincode() {
		return pincode;
	}

	/**
	 * @param pincode the pincode to set
	 */
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	/**
	 * @return the patientAdr
	 */
	public Patient getPatientAdr() {
		return patientAdr;
	}

	/**
	 * @param patientAdr the patientAdr to set
	 */
	public void setPatientAdr(Patient patientAdr) {
		this.patientAdr = patientAdr;
	}

	

}
