package com.sample;

import static javax.persistence.InheritanceType.TABLE_PER_CLASS;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;

@Entity
@Inheritance(strategy = TABLE_PER_CLASS)
public class LoanApplicant {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_APPLICANT")
	private Integer idApplicant;

	public Integer getIdApplicant() {
		return idApplicant;
	}

	public void setIdApplicant(Integer idApplicant) {
		this.idApplicant = idApplicant;
	}

	public LoanApplicant(Integer idApplicant) {
		super();
		this.idApplicant = idApplicant;
	}

	public LoanApplicant() {
		super();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idApplicant == null) ? 0 : idApplicant.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoanApplicant other = (LoanApplicant) obj;
		if (idApplicant == null) {
			if (other.idApplicant != null)
				return false;
		} else if (!idApplicant.equals(other.idApplicant))
			return false;
		return true;
	}
	
}
