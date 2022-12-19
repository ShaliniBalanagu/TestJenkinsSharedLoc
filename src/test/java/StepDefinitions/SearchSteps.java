package StepDefinitions;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Recordset;

import Utilities.DriverUtils;
import Utilities.Helper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.SearchPage;

public class SearchSteps extends DriverUtils {
	WebDriver driver;
	SearchPage searchPage = new SearchPage();

	public SearchSteps() {
		driver = getDriver();
		System.out.println("Driver in Search steps:" + driver);
	}

	@Given("Google application is launched")
	public void google_application_is_launched() throws IOException {
		System.out.println("Driver in search steps:" + driver);
		driver.navigate().to("https://www.google.com");
		System.out.println("Launching google applications");
		TakesScreenshot scrShot =((TakesScreenshot)driver);
		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(SrcFile, new File("./src/test/resources/report/image.png"));
		System.out.println("Screen captured");

	}

	@When("I see the search field and enter data in it")
	public void i_see_the_search_field_and_enter_data_in_it() {
		Assert.assertTrue(searchPage.inputSearch.isDisplayed(), "Search box is not displayed");
		System.out.println("Search box is displayed");
		searchPage.inputSearch.sendKeys("Test data" + Keys.ENTER);
	}

	@Then("I validate the results displayed")
	public void i_validate_the_results_displayed() throws InterruptedException {
		Thread.sleep(3000);
		Assert.assertTrue(searchPage.links.size() != 0);
		System.out.println("Links are displayed");
	}

	@When("User enters data from {string} and {string} and hits enter")
	public void user_enters_data_from_and_and_hits_enter(String string, String rowNum)
			throws NumberFormatException, IOException, FilloException {
		System.out.println("Entered step,row num is:"+rowNum);
		Helper helper = new Helper();
		System.out.println("Excel path is:"+System.getProperty("excelPath"));

		  List<Map<String, String>> data = helper.readDataFromExcel("Sheet1",Integer.parseInt(rowNum),System.getProperty("excelPath")); 
		  System.out.println("Data read"); 
		  Map<String,String> txt = data.get(Integer.parseInt(rowNum));
		  searchPage.inputSearch.sendKeys(txt.get("Name") + Keys.ENTER);
		 

		/*
		 * Recordset recordSet = helper.readExcelFillo(string,
		 * Integer.parseInt(rowNum));
		 * 
		 * System.out.println("Data to enter is:"+recordSet.getField("Name"));
		 * searchPage.inputSearch.sendKeys(recordSet.getField("Name")+Keys.ENTER);
		 * recordSet.close(); helper.connection.close();
		 */
		 

	}

	@Then("Verify that the results are displayed")
	public void verify_that_the_results_are_displayed() {

	}

}
