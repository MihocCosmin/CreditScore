package com.sample;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class FormPersonalLoanApplicant {

	private PersonalLoanApplicant applicant;
	private List<PersonalLoanApplicant> applicants = new ArrayList<PersonalLoanApplicant>();
	
	private ApplicationResult result;
	private List<ApplicationResult> results = new ArrayList<ApplicationResult>();
	

	public PersonalLoanApplicant getApplicant() {
		return applicant;
	}
	public void setApplicant(PersonalLoanApplicant applicant) {
		this.applicant = applicant;
	}

	public List<PersonalLoanApplicant> getApplicants() {
		return applicants;
	}
	public void setApplicants(List<PersonalLoanApplicant> applicants) {
		this.applicants = applicants;
	}

	public ApplicationResult getResult() {
		return result;
	}
	public void setResult(ApplicationResult result) {
		this.result = result;
	}

	public List<ApplicationResult> getResults() {
		return results;
	}
	public void setResults(List<ApplicationResult> results) {
		this.results = results;
	}

	private EntityManager em;
	KieServices ks = KieServices.Factory.get();
    KieContainer kContainer = ks.getKieClasspathContainer();
	KieSession kSession = kContainer.newKieSession("ksession-rules");
	
	public FormPersonalLoanApplicant() {
		
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("CreditScore");
		em=emf.createEntityManager();
		
		this.applicants=em.createQuery("SELECT p FROM PersonalLoanApplicant p").getResultList();
		if(!this.applicants.isEmpty())
			this.applicant=this.applicants.get(0);
		
		this.results=em.createQuery("SELECT r FROM ApplicationResult r").getResultList();
		if(!this.results.isEmpty())
			this.result=this.results.get(0);
	}
	
	//Navigation
	public void previousApplicant (ActionEvent event) {
		Integer idx = this.applicants.indexOf(this.applicant);
		if (idx -1 >= 0)
			this.applicant = this.applicants.get(idx - 1);
	}
	
	public void nextApplicant (ActionEvent event) {
		Integer idx = this.applicants.indexOf(this.applicant);
		if (idx + 1 < this.applicants.size())
			this.applicant = this.applicants.get(idx + 1);
	}
	
	//Add new entries

	public void addApplicant(ActionEvent event) {
		this.applicant = new PersonalLoanApplicant();
		this.applicant.setName("Dan");
		this.applicant.setAge(34);
		this.applicant.setLevel(EducationLevel.HIGHSCHOOL);
		this.applicant.setOccupation(Occupation.EMPLOYEE);
		this.applicant.setSituation(LivingSituation.RENT);
		this.applicant.setSector(WorkingSector.OTHER);
		this.applicant.setWorkingSeniority(20);
		this.applicant.setMonthlyIncome(3300.0);
		this.applicant.setIsMarried(true);
		this.applicant.setHasChildren(false);
		this.applicant.setHasCar(false);
		this.applicant.setNrOfPaymentsWithoutDelays(10);
		this.applicant.setNrOfDelays(0);
		this.applicant.setAmountToBePayed(0.0);
		this.applicant.setType(CreditType.PERSONAL);
	
		this.applicants.add(this.applicant);
	}
	
	//Delete entries from DB
	public void deleteApplicant(ActionEvent event) {
		this.applicants.remove(this.applicant);
		if(this.em.contains(this.applicant)){
			this.em.getTransaction().begin();
			this.em.remove(this.applicant);
			this.em.getTransaction().commit();
	    }
		if(this.applicants.size() > 0)
			this.applicant = this.applicants.get(0);
		else
			this.applicant = null;
	}
	
	//Save entries to DB
	public void saveApplicant(ActionEvent event){
		this.em.getTransaction().begin();
		this.em.persist(this.applicant);
		this.em.getTransaction().commit();
	}
	
	public void refreshApplicant(ActionEvent event){
		if(this.em.contains(this.applicant)) {
			this.em.getTransaction().begin();
			this.em.refresh(this.applicant);
			this.em.getTransaction().commit();
		}else
			if(this.applicants.size()>0)
				this.applicant=this.applicants.get(0);
	}
	
	public void addResult(ActionEvent event){
		this.result=new ApplicationResult();
		this.result.setApplicant(this.applicant);
		this.result.setCreditScore(0);
		this.result.setLoanAccepted(true);
		this.results.add(this.result);
		kSession.insert(this.applicant);
		kSession.insert(this.result);
		kSession.fireAllRules();
	}
		
}
