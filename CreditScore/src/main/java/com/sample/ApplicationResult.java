package com.sample;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ApplicationResult {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idResult;

	@ManyToOne
	@JoinColumn(name = "ID_APPLICANT")
	private PersonalLoanApplicant applicant;
	
	@Column(name = "CREDIT_SCORE")
	private Integer creditScore;
	
	@Column(name = "LOAN_ACCEPTED")
	private Boolean loanAccepted;
	
	public Integer getIdResult() {
		return idResult;
	}
	public void setIdResult(Integer idResult) {
		this.idResult = idResult;
	}
	
	public PersonalLoanApplicant getApplicant() {
		return applicant;
	}
	public void setApplicant(PersonalLoanApplicant applicant) {
		this.applicant = applicant;
	}

	public Integer getCreditScore(){
		return creditScore;
	}
	public void setCreditScore(Integer creditScore) {
		this.creditScore = creditScore;
	}

	public Boolean getLoanAccepted() {
		return loanAccepted;
	}
	public void setLoanAccepted(Boolean loanAccepted) {
		this.loanAccepted = loanAccepted;
	}
	
	public ApplicationResult() {
		super();
	}
	
	public ApplicationResult(PersonalLoanApplicant applicant) {
		super();
		this.applicant = applicant;
	}
	
	public ApplicationResult(PersonalLoanApplicant applicant, Integer creditScore, Boolean loanAccepted) {
		super();
		this.applicant = applicant;
		this.creditScore = creditScore;
		this.loanAccepted = loanAccepted;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idResult== null) ? 0 : idResult.hashCode());
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
		ApplicationResult other = (ApplicationResult) obj;
		if (idResult == null) {
			if (other.idResult != null)
				return false;
		} else if (!idResult.equals(other.idResult))
			return false;
		return true;
	}
	
}
