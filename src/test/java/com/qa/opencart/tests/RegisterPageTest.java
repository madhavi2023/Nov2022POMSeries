package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class RegisterPageTest extends BaseTest {
	
	@BeforeClass
	public void regPageSetup() {
		registerPage = loginPage.navigateToRegister();	
	}
	
	public String getRandomEmail() {
		Random random = new Random();
		String email = "automation"+random.nextInt(1000) + "@gmail.com";
		return email;
	}
	@DataProvider
	public Object[][] getRegTestData() {
		
		return new Object[][] {{"Ram", "konda","9731231234","ram@123","yes"},
			{"Rishi","kante","9732341234","rishi@123","no"},
			{"Neha","kiki","8761238722","neha@123","no"},
			
		};
	}
	@Test(dataProvider="getRegTestData")
	public void userRegTest(String firstName,String lastName,String telephone,String password,String subscribe) {
	Assert.assertTrue(registerPage.
			registerUser(firstName,lastName,getRandomEmail(),telephone,password,subscribe));
	}

}
