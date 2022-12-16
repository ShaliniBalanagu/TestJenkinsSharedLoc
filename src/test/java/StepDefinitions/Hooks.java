package StepDefinitions;

import org.openqa.selenium.WebDriver;

import Utilities.DriverUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
	
	@Before
	public void beforeMethod() {
		System.out.println("Entered before method");
		DriverUtils.createDriver();

	}
	
	@After
	public void afterMethod() {
		System.out.println("Entered after method");
		DriverUtils.getDriver().quit();
		System.out.println("Driver closed");
	}
}
