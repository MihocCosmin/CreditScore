package com.tests;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.sample.ApplicationResult;
import com.sample.CommercialLoanApplicant;
import com.sample.CreditRating;
import com.sample.CreditType;
import com.sample.EducationLevel;
import com.sample.LivingSituation;
import com.sample.Occupation;
import com.sample.PersonalLoanApplicant;
import com.sample.WorkingSector;

public class JPATest {

	public static void main(String[] args) {
		
		KieServices kService=KieServices.Factory.get();
		KieContainer kContainer=kService.getKieClasspathContainer();
		KieSession kSession=kContainer.newKieSession("ksession-rules");
		
		PersonalLoanApplicant p4 = new PersonalLoanApplicant();
		p4.setName("Dan");
		p4.setAge(25);
		p4.setLevel(EducationLevel.HIGHSCHOOL);
		p4.setOccupation(Occupation.EMPLOYEE);
		p4.setSituation(LivingSituation.OWNER);
		p4.setSector(WorkingSector.OTHER);
		p4.setWorkingSeniority(30);
		p4.setMonthlyIncome(2900.0);
		p4.setIsMarried(false);
		p4.setHasChildren(false);
		p4.setHasCar(false);
		p4.setNrOfPaymentsWithoutDelays(0);
		p4.setNrOfDelays(3);
		p4.setAmountToBePayed(1000.0);
		p4.setType(CreditType.PERSONAL);
		
		ApplicationResult r4 = new ApplicationResult();
		r4.setApplicant(p4);
		r4.setCreditScore(0);
		r4.setLoanAccepted(true);
		
		CommercialLoanApplicant c4 = new CommercialLoanApplicant();
		c4.setName("SC Test4");
		c4.setCUI("1hdwu3");
		c4.setYearlyIncome(5000000.0);
		c4.setLoanReason("refinancing");
		c4.setRating(CreditRating.B);
		c4.setIsApproved(true);

		kSession.insert(p4);
		kSession.insert(r4);
		kSession.insert(c4);

		kSession.fireAllRules();
				
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("CreditScore");
		EntityManager em = emf.createEntityManager();
		
		//CREATE
		em.getTransaction().begin();
		em.persist(p4);
		em.persist(r4);
		em.persist(c4);
		em.getTransaction().commit();
		
		//QUERY
		List<ApplicationResult> lstResult = em.createQuery("SELECT r FROM ApplicationResult r").getResultList();
		System.out.println("Scores: ");
		for(ApplicationResult r: lstResult)
			System.out.println("CreditScore: " + r.getCreditScore());
		em.getTransaction().begin();			
	}
}
