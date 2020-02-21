package driverScript;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunctionLibrary.FuctionLibrary;

public class ExtendReports {

	public static void main(String[] args) throws Exception {
		
		ExtentReports reports=new ExtentReports("C:\\Users\\sridevich\\Desktop\\myreport.html");
		
		ExtentTest writer=reports.startTest("Login Test");
		
		
		WebDriver driver1=FuctionLibrary.startBrowser();
		
		writer.log(LogStatus.INFO, "chrome opened");
		FuctionLibrary.openApplication(driver1);
		
		writer.log(LogStatus.INFO,"waiting for username");
		FuctionLibrary.waitForElement(driver1, "id", "username", "10");
		
		writer.log(LogStatus.INFO,"entering admin into username");
		FuctionLibrary.typeAction(driver1, "id","username" ,"admin");
		
		
		writer.log(LogStatus.INFO,"waiting for password");
		FuctionLibrary.waitForElement(driver1, "name", "password", "10");
		
		writer.log(LogStatus.INFO,"Entering master into password");
		FuctionLibrary.typeAction(driver1, "name", "password","master" );
		
		
		writer.log(LogStatus.INFO,"waiting login button");
		FuctionLibrary.waitForElement(driver1, "id", "btnsubmit", "10");
		
		writer.log(LogStatus.INFO,"waiting login element");
		FuctionLibrary.clickAction(driver1, "id", "btnsubmit");
		
		writer.log(LogStatus.INFO,"clicking on login button");
		
		writer.log(LogStatus.PASS,"Execution completed");
		
		reports.endTest(writer);
		
		reports.flush();
		
		
		
		
		
		
		
		
		
	}

}
