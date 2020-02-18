package com.sample;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class CommercialLoanApplicant extends LoanApplicant{

	@Column(name = "ORGANIZATION_NAME")
	private String name;
	
	@Column(name = "CUI")
	private String CUI;
	
	@Column(name = "YEARLY_INCOME")
	private Double yearlyIncome;
	
	@Column(name = "LOAN_REASON")
	private String loanReason;
	
	@Column(name = "CREDIT_RATING")
	@Enumerated(EnumType.STRING)
	private CreditRating rating;
	
	@Column(name = "IS_IT_APPROVED")
	private Boolean isApproved;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCUI() {
		return CUI;
	}
	public void setCUI(String CUI) {
		this.CUI = CUI;
	}
	
	public Double getYearlyIncome() {
		return yearlyIncome;
	}	
	public void setYearlyIncome(Double yearlyIncome) {
		this.yearlyIncome = yearlyIncome;
	}

    public String getLoanReason() {
	    return loanReason;
    }
    public void setLoanReason(String loanReason) {
	    this.loanReason = loanReason;
    }
	
	public CreditRating getRating() {
		return rating;
	}
	public void setRating(CreditRating rating) {
		this.rating = rating;
	}
	
	public Boolean getIsApproved() {
		return isApproved;
	}
	public void setIsApproved(Boolean isApproved) {
		this.isApproved = isApproved;
	}
	
	public CommercialLoanApplicant(Integer idApplicant, String name, String CUI, Double yearlyIncome, String loanReason,
			CreditRating rating, Boolean isApproved) {
		super(idApplicant);
		this.name = name;
		this.CUI = CUI;
		this.yearlyIncome = yearlyIncome;
		this.loanReason = loanReason;
		this.rating = rating;
		this.isApproved = isApproved;
	}
	public CommercialLoanApplicant() {
		super();
	}
}
