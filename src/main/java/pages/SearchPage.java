package pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Utilities.DriverUtils;

public class SearchPage extends DriverUtils {

	public SearchPage(){
		PageFactory.initElements(getDriver(), this);
	}
	@FindBy(xpath="//input[@aria-label=\"Search\"]")
	public WebElement inputSearch;
	
	@FindBy(xpath="//h3")
	public List<WebElement> links;
}
