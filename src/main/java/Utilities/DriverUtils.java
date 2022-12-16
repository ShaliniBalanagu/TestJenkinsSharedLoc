package Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverUtils {

	public static WebDriver driver;
	public static ThreadLocal<WebDriver> driverM=new ThreadLocal<WebDriver>();
	
	public synchronized static void createDriver() {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();	
		System.out.println("Driver created:"+driver);
		driverM.set(driver);
		
	}
	
	public static synchronized WebDriver getDriver() {
		
		return driverM.get();
	}
}
