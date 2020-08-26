package reporting;

import java.util.Date;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

public class Reporting_Extent {
	
	public static ExtentReports ext;
	public static ExtentReports getReport(){
		if(ext==null){
		Date d=new Date();
		String FN=d.toString().replace(" ", "_").replace(":", "_")+".html";
		ext=new ExtentReports(System.getProperty("user.dir")+"//report//"+FN,true,DisplayOrder.NEWEST_FIRST);
		ext.addSystemInfo("QA manoj", "Kushwaha").addSystemInfo("Environment", " Testing - 10.67.175.30");
		}
		return ext;
	}

}
