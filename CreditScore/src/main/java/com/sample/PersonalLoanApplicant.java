package com.sample;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;

@Entity
public class PersonalLoanApplicant extends LoanApplicant{

	@Column(name = "APPLICANT_NAME")
	private String name;
	
	@Column(name = "AGE")
	private Integer age;
	
	@Column(name = "EDUCATION_LEVEL")
	@Enumerated(EnumType.STRING)
	private EducationLevel level;
	
	@Column(name = "OCCUPATION")
	@Enumerated(EnumType.STRING)
	private Occupation occupation;
	
	@Column(name = "LIVING_SITUATION")
	@Enumerated(EnumType.STRING)
	private LivingSituation situation;
	
	@Column(name = "WORKING_SECTOR")
	@Enumerated(EnumType.STRING)
	private WorkingSector sector;
	
	@Column(name = "SENIORITY")
	private Integer workingSeniority;
	
	@Column(name = "MONTHLY_INCOME")
	private Double monthlyIncome;
	
	@Column(name = "IS_MARRIED")
	private Boolean isMarried;
	
	@Column(name = "HAS_CHILDREN")
	private Boolean hasChildren;
	
	@Column(name = "HAS_CAR")
	private Boolean hasCar;
	
	@Column(name = "NR_OF_PAYMENTS_WITHOUT_DELAY")
	private Integer nrOfPaymentsWithoutDelays;
	
	@Column(name = "NR_OF_DELAYS")
	private Integer nrOfDelays;
	
	@Column(name = "DEBT_OWNED")
	private Double amountToBePayed;
	
	@Column(name = "CREDIT_TYPE")
	@Enumerated(EnumType.STRING)
	private CreditType type;
	
	@OneToMany(mappedBy = "applicant", cascade=CascadeType.ALL, orphanRemoval = true)
	private List<ApplicationResult> results = new ArrayList<ApplicationResult>();

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	public EducationLevel getLevel() {
		return level;
	}
	public void setLevel(EducationLevel level) {
		this.level = level;
	}
	
	public Occupation getOccupation() {
		return occupation;
	}
	public void setOccupation(Occupation occupation) {
		this.occupation = occupation;
	}
	
	public LivingSituation getSituation() {
		return situation;
	}
	public void setSituation(LivingSituation situation) {
		this.situation = situation;
	}
	
	public WorkingSector getSector() {
		return sector;
	}
	public void setSector(WorkingSector sector) {
		this.sector = sector;
	}
	
	public Integer getWorkingSeniority() {
		return workingSeniority;
	}
	public void setWorkingSeniority(Integer workingSeniority) {
		this.workingSeniority = workingSeniority;
	}
	
	public Double getMonthlyIncome() {
		return monthlyIncome;
	}
	public void setMonthlyIncome(Double monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}
	
	public Boolean getIsMarried() {
		return isMarried;
	}
	public void setIsMarried(Boolean isMarried) {
		this.isMarried = isMarried;
	}
	
	public Boolean getHasChildren() {
		return hasChildren;
	}
	public void setHasChildren(Boolean hasChildren) {
		this.hasChildren = hasChildren;
	}
	
	public Boolean getHasCar() {
		return hasCar;
	}
	public void setHasCar(Boolean hasCar) {
		this.hasCar = hasCar;
	}
	
	public Integer getNrOfPaymentsWithoutDelays() {
		return nrOfPaymentsWithoutDelays;
	}
	public void setNrOfPaymentsWithoutDelays(Integer nrOfPaymentsWithoutDelays) {
		this.nrOfPaymentsWithoutDelays = nrOfPaymentsWithoutDelays;
	}
	public Integer getNrOfDelays() {
		return nrOfDelays;
	}
	public void setNrOfDelays(Integer nrOfDelays) {
		this.nrOfDelays = nrOfDelays;
	}
	public Double getAmountToBePayed() {
		return amountToBePayed;
	}
	public void setAmountToBePayed(Double amountToBePayed) {
		this.amountToBePayed = amountToBePayed;
	}
	
	public CreditType getType() {
		return type;
	}
	public void setType(CreditType type) {
		this.type = type;
	}
	
	public List<ApplicationResult> getResults() {
		return results;
	}
	public void setResults(List<ApplicationResult> results) {
		this.results = results;
	}
	
	public PersonalLoanApplicant(Integer idApplicant, String name, Integer age, EducationLevel level,
			Occupation occupation, LivingSituation situation, WorkingSector sector, Integer workingSeniority,
			Double monthlyIncome, Boolean isMarried, Boolean hasChildren, Boolean hasCar,
			Integer nrOfPaymentsWithoutDelays, Integer nrOfDelays, Double amountToBePayed, CreditType type) {
		super(idApplicant);
		this.name = name;
		this.age = age;
		this.level = level;
		this.occupation = occupation;
		this.situation = situation;
		this.sector = sector;
		this.workingSeniority = workingSeniority;
		this.monthlyIncome = monthlyIncome;
		this.isMarried = isMarried;
		this.hasChildren = hasChildren;
		this.hasCar = hasCar;
		this.nrOfPaymentsWithoutDelays = nrOfPaymentsWithoutDelays;
		this.nrOfDelays = nrOfDelays;
		this.amountToBePayed = amountToBePayed;
		this.type = type;
	}
	
	public PersonalLoanApplicant() {
		super();
	}
}
