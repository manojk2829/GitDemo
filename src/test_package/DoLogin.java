package test_package;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import base.BaseSep;

public class DoLogin extends BaseSep {
	
	@Test
	public void doLogin(){
		test=ext.startTest("My Testing Star");
        openBrowser(pro.getProperty("Browser"));
		dr.manage().window().maximize();
		navigate(pro.getProperty("appurl"));
		screenshot();
		test.log(LogStatus.PASS,"Test Pass");
	}
}
