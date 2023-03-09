
package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.aspectj.util.FileUtil;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

public class DriverFactory {
	
	public WebDriver driver;
	public Properties prop;
	public OptionsManager OptionsManager;
	
	public static String highlight;
	
	public static ThreadLocal<WebDriver> tlDriver =  new ThreadLocal<WebDriver>();
/**
 * This method is initalizing driver on basis of given browser name
 * @param prop
 * @return this method returns driver
 */
	
	public WebDriver initDriver(Properties prop) {
		OptionsManager = new OptionsManager(prop);
		highlight = prop.getProperty("highlight").trim();
		String browserName = prop.getProperty("browser").toLowerCase().trim();
		
		System.out.println("Browser name is : " +browserName);
		
		if(browserName.equalsIgnoreCase("chrome")) {
			
//			driver = new ChromeDriver(OptionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(OptionsManager.getChromeOptions()));
			
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
//			driver = new FirefoxDriver(OptionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(OptionsManager.getFirefoxOptions()));
		}
		else if(browserName.equalsIgnoreCase("edge")) {
//			driver = new EdgeDriver(OptionsManager.getEdgeOptions());
			tlDriver.set(new EdgeDriver(OptionsManager.getEdgeOptions()));
	}
		else
		{
			System.out.println("Please pass the right browser " + browserName);
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}
/**
 * get the local thread copy of the driver
 * @return 
 */
	
	public synchronized static WebDriver getDriver() {
		
		return tlDriver.get();
	}
	/**
	 * This method is reading properties from .Properties file
	 * @return
	 */
	
	public Properties initProp() {

		// mvn clean install -Denv="qa"
		// mvn clean install
		prop = new Properties();
		FileInputStream ip = null;
		String envName = System.getProperty("env");
		System.out.println("Running test cases on Env: " + envName);

		try {
			if (envName == null) {
				System.out.println("no env is passed....Running tests on QA env...");
				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
			} else {
				switch (envName.toLowerCase().trim()) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
					break;
				case "prod":
					ip = new FileInputStream("./src/test/resources/config/config.properties");
					break;

				default:
					System.out.println("....Wrong env is passed....No need to run the test cases....");
//					throw new FrameworkException("WRONG ENV IS PASSED...");
					break;
				}

			}
		} catch (FileNotFoundException e) {

		}

		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}
	/**
	 * take screenshot
	 */
	public static String getScreenshot() {
		File srcFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"/screenshot/"+System.currentTimeMillis()+".png";
		File destination = new File(path);
		try {
			FileUtil.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
	
}

