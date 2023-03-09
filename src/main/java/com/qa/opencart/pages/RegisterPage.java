package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {
	private WebDriver driver;
	private ElementUtil eleUtil;

	By firstName = By.id("input-firstname");
	By lastName = By.id("input-lastname");
	By email = By.id("input-email");
	By telephone = By.id("input-telephone");
	By password = By.id("input-password");
	By confpassword = By.id("input-confirm");

	By agreeCheckBox = By.name("agree");
	By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");
	By subscribeYes = By.xpath("//label[normalize-space()='Yes']/input[@type='radio']");
	By subscribeNo = By.xpath("//label[normalize-space()='No']/input[@type='radio']");

	By registerSuccessMesg = By.cssSelector("div#content h1");
	By logoutLink = By.linkText("Logout");
	By registerLink = By.linkText("Register");

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);

	}

	public boolean registerUser(String firstName, String lastName, String email, String telephone, String password,
			String subscribe) {

		eleUtil.waitForElementVisible(this.firstName, AppConstants.DEFAULT_SHORT_TIME_OUT).sendKeys(firstName);
		eleUtil.dosendKeys(this.lastName, lastName);
		eleUtil.dosendKeys(this.email, email);
		eleUtil.dosendKeys(this.telephone, telephone);
		eleUtil.dosendKeys(this.password, password);
//		eleUtil.dosendKeys(this., password);
		eleUtil.dosendKeys(this.confpassword, password);

		if(subscribe.equalsIgnoreCase("Yes")) {
			eleUtil.doClick(subscribeYes);
		}
		else
		{
			
			eleUtil.doClick(subscribeNo);
		}
		
		
		eleUtil.doActionsClick(agreeCheckBox);
		eleUtil.doClick(continueButton);
		String successMesg = eleUtil.waitForElementVisible(registerSuccessMesg, AppConstants.DEFAULT_MEDIUM_TIME_OUT).getText();
		System.out.println("Register User Success message "+successMesg);
		
		if(successMesg.contains(AppConstants.USER_REGISTER_SUCCESS_MESSAGE)) {
			eleUtil.doClick(logoutLink);
			eleUtil.doClick(registerLink);
			return true;
		}
		else
			return false;
	}
	
	

}
