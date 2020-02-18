package com.tests;

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

public class Test {

	public static void main(String[] args){

		KieServices kService=KieServices.Factory.get();
		KieContainer kContainer=kService.getKieClasspathContainer();
		KieSession kSession=kContainer.newKieSession("ksession-rules");
		
		PersonalLoanApplicant p1 = new PersonalLoanApplicant();
		p1.setIdApplicant(1);
		p1.setName("Alex");
		p1.setAge(28);
		p1.setLevel(EducationLevel.HIGHSCHOOL);
		p1.setOccupation(Occupation.EMPLOYEE);
		p1.setSituation(LivingSituation.RENT);
		p1.setSector(WorkingSector.OTHER);
		p1.setWorkingSeniority(20);
		p1.setMonthlyIncome(3300.0);
		p1.setIsMarried(false);
		p1.setHasChildren(false);
		p1.setHasCar(false);
		p1.setNrOfPaymentsWithoutDelays(8);
		p1.setNrOfDelays(0);
		p1.setAmountToBePayed(0.0);
		p1.setType(CreditType.PERSONAL);
		
		ApplicationResult r1 = new ApplicationResult();
		r1.setIdResult(1);
		r1.setApplicant(p1);
		r1.setCreditScore(0);
		r1.setLoanAccepted(true);
		
		CommercialLoanApplicant c1 = new CommercialLoanApplicant();
		c1.setIdApplicant(2);
		c1.setName("SC Test");
		c1.setCUI("1hdwu3");
		c1.setYearlyIncome(5000000.0);
		c1.setLoanReason("refinancing");
		c1.setRating(CreditRating.B);
		c1.setIsApproved(true);
		
		kSession.insert(p1);
		kSession.insert(r1);
		kSession.insert(c1);
		kSession.fireAllRules();	
	}
}
