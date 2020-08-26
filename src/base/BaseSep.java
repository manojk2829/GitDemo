package base;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import reporting.Reporting_Extent;

public class BaseSep {
	
	public Properties pro;
	public WebDriver dr;
	public ExtentReports ext = Reporting_Extent.getReport();
	public ExtentTest test;
	
	public BaseSep(){
		init();
	}

	
	public void init(){
		pro=new Properties();
		try{
			FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\config\\OR.properties");
		    pro.load(fis);
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		Reporter.log("Properties file initialized.......");
	}
	
	public void openBrowser(String bName){
		if(bName.equalsIgnoreCase("chrome")){
				System.setProperty("webdriver.chrome.driver", pro.getProperty("Browser_exe"));
		dr=new ChromeDriver();
		dr.manage().window().maximize();
		dr.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}else{
		System.setProperty("webdriver.gecko.driver", pro.getProperty("firefox_Browser_exe"));
		dr=new FirefoxDriver();
	  }
	}
	
	public void navigate(String url){
		dr.navigate().to(url);
	}
	
	public void screenshot(){
		Date d=new Date();
		String FN=d.toString().replace(" ", "_").replace(":", "_")+".jpg";
		File src = ((TakesScreenshot)dr).getScreenshotAs(OutputType.FILE);
		try{
			FileHandler.copy(src, new File(System.getProperty("user.dir")+"//report//"+FN));
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		Reporter.log("Screenshot taken successfully ----- ");
		test.log(LogStatus.INFO,"Test screenshot Taken ====>  " + test.addScreenCapture(System.getProperty("user.dir")+"//report//"+FN));
	}
	
	public void wait(int s){
		try{
			Thread.sleep(s*1000);
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	
	public void quite_Browser(int s){
		try{
			Thread.sleep(s*1000);
			dr.quit();
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	
	@AfterTest
	public void tearDown(){
		ext.endTest(test);
		ext.flush();
		quite_Browser(5);
	}
	
	
}
