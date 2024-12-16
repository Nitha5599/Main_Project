package TestScript;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import Constants.ConstantValues;
import PageClasses.QALegendAddClientsPage;
import PageClasses.QALegendEditNotePage;
import PageClasses.QALegendEventPage;
import PageClasses.QALegendHomePage;
import PageClasses.QALegendLoginPage;
import PageClasses.QALegendNotesPage;

public class BaseClass {
	public WebDriver driver;
	QALegendLoginPage loginpage;
	QALegendHomePage homepage;
	QALegendEventPage eventpage;
	QALegendNotesPage notespage;
	QALegendEditNotePage editnotepage;
	QALegendAddClientsPage clientpage;
	public Properties prop;
	public FileInputStream fis;
	
	@BeforeMethod(alwaysRun = true)
	@Parameters("browser")
	public void browserInitialization(String browserName) throws Exception {
		if(browserName.equalsIgnoreCase("Chrome")) {
			driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("Firefox")) {
			driver = new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("Edge")) {
			driver = new EdgeDriver();
		}
		else {
			throw new Exception("Invalid name Exception");
		}
		prop = new Properties();
		fis = new FileInputStream(ConstantValues.USERDATAFILE);
		prop.load(fis);
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		loginpage = new QALegendLoginPage(driver);
		homepage = new QALegendHomePage(driver);
		eventpage = new QALegendEventPage(driver);
		notespage = new QALegendNotesPage(driver);
		editnotepage = new QALegendEditNotePage(driver);
		clientpage = new QALegendAddClientsPage(driver);
	}

}
