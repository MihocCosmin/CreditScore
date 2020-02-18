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

public class FormCommercialLoanApplicant {

	private CommercialLoanApplicant company;
	private List<CommercialLoanApplicant> companies = new ArrayList<CommercialLoanApplicant>();
	
	public CommercialLoanApplicant getCompany() {
		return company;
	}
	public void setCompany(CommercialLoanApplicant company) {
		this.company = company;
	}
	
	public List<CommercialLoanApplicant> getCompanies() {
		return companies;
	}
	public void setCompanies(List<CommercialLoanApplicant> companies) {
		this.companies = companies;
	}

	private EntityManager em;
	KieServices ks = KieServices.Factory.get();
    KieContainer kContainer = ks.getKieClasspathContainer();
	KieSession kSession = kContainer.newKieSession("ksession-rules");
	
	public FormCommercialLoanApplicant() {
		
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("CreditScore");
		em=emf.createEntityManager();
		
		this.companies=em.createQuery("SELECT c FROM CommercialLoanApplicant c").getResultList();
		if(!this.companies.isEmpty())
			this.company=this.companies.get(0);
	}
	
	public void previousCompany (ActionEvent event) {
		Integer idx = this.companies.indexOf(this.company);
		if (idx > 0)
			this.company = this.companies.get(idx - 1);
	}
	
	public void nextCompany (ActionEvent event) {
		Integer idx = this.companies.indexOf(this.company);
		if ((idx + 1) < this.companies.size())
			this.company = this.companies.get(idx + 1);
	}
	
	
	public void addCompany(ActionEvent event) {
		this.company = new CommercialLoanApplicant();
		this.company.setName("SC Test3");
		this.company.setCUI("1hdwu3");
		this.company.setYearlyIncome(5000000.0);
		this.company.setLoanReason("refinancing");
		this.company.setRating(CreditRating.D);
		this.company.setIsApproved(true);
	
		this.companies.add(this.company);
	}
	
	public void deleteCompany(ActionEvent event) {
		this.companies.remove(this.company);
		if(this.em.contains(this.company)){
			this.em.getTransaction().begin();
			this.em.remove(this.company);
			this.em.getTransaction().commit();
	    }
		if(this.companies.size() > 0)
			this.company = this.companies.get(0);
		else
			this.company = null;
	}
	
	public void saveCompany(ActionEvent event){
		this.em.getTransaction().begin();
		this.em.persist(this.company);
		this.em.getTransaction().commit();
		kSession.insert(this.company);
		kSession.fireAllRules();
	}
	
	public void refreshCompany(ActionEvent event){
		if(this.em.contains(this.company)) {
			this.em.getTransaction().begin();
			this.em.refresh(this.company);
			this.em.getTransaction().commit();
		}else
			if(this.companies.size()>0)
				this.company=this.companies.get(0);
	}

}
