package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
int i =10;
	private WebDriver driver;
	private ElementUtil eleUtil;
	//1.Private By locaters:
	
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By registerLink = By.linkText("Register");
	
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;	
		eleUtil = new ElementUtil(driver);
	}
	
	//2. page actions and methods :
	@Step("....getting the login page title.....")
	public String getLoginPageTitle() {
		
		String title = eleUtil.waitForTitleIsAndFetch(AppConstants.DEFAULT_MEDIUM_TIME_OUT, AppConstants.LOGIN_PAGE_TITLE_VALUE); 
		System.out.println("Login page title: " +title);
		return title;
		}
	@Step("....getting the login page url.....")
	public String getLoginPageUrl() {
		String url=eleUtil.waitForURLContainsAndFetch(AppConstants.DEFAULT_MEDIUM_TIME_OUT, AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE);
		System.out.println("Login page url :  " +url);
		return url;
		}
	@Step("....getting the forgot pwd link.....")
	
	public boolean isForgotPwdLinkExist() {
		return eleUtil.waitForElementVisible(forgotPwdLink, AppConstants.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();
		}
	
	@Step("login with username : {0} and password: {1}")
	public AccountsPage doLogin(String un,String pwd) {
		System.out.println("App credentials are : " + un + "," + pwd);
		eleUtil.waitForElementPresence(emailId, AppConstants.DEFAULT_MEDIUM_TIME_OUT).sendKeys(un);
		eleUtil.dosendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);	
	}
	
	@Step("navigating to register page")
	public RegisterPage navigateToRegister() {
		eleUtil.doClick(registerLink);
		return new RegisterPage(driver);
		
	}
	
	
	
}
