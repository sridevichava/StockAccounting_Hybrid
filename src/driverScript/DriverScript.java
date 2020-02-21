package driverScript;

import java.io.File;

import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunctionLibrary.FuctionLibrary;
import utilities.ExcelFileUtiliti;

public class DriverScript {
	static WebDriver driver;
	static ExtentReports report;
	static ExtentTest test;
	

	public static void main(String[] args) throws Exception {
		
		ExcelFileUtiliti excel=new ExcelFileUtiliti();
		
		for(int i=1;i<=excel.rowCount("MasterTestCases");i++){
			
			String ModuleStatus="";
			
			if(excel.getData("MasterTestCases", i, 2).equalsIgnoreCase("Y")){
				
				String TCModule=excel.getData("MasterTestCases", i, 1);
				
				report=new ExtentReports("D:\\sridevi_82\\StockAccounting_Hybrid\\Reports\\"+TCModule+FuctionLibrary.generateData()+".html");
				test=report.startTest(TCModule);
				for(int j=1;j<=excel.rowCount(TCModule);j++){
					
					String Description=excel.getData(TCModule, j, 0);
					String Function_Name=excel.getData(TCModule, j, 1);
					String Locator_Type=excel.getData(TCModule, j, 2);
					String Locator_Value=excel.getData(TCModule, j, 3);
					String Test_Data=excel.getData(TCModule, j, 4);
					try{
						if(Function_Name.equalsIgnoreCase("startBrowser")){
						      driver=	FuctionLibrary.startBrowser();
						      test.log(LogStatus.INFO, Description);
						}else if(Function_Name.equalsIgnoreCase("openApplication")){
							FuctionLibrary.openApplication(driver);
							 test.log(LogStatus.INFO, Description);
						}else if(Function_Name.equalsIgnoreCase("waitForElement")){
							FuctionLibrary.waitForElement(driver, Locator_Type, Locator_Value, Test_Data);
							test.log(LogStatus.INFO, Description);
						}else if(Function_Name.equalsIgnoreCase("typeAction")){
							FuctionLibrary.typeAction(driver, Locator_Type, Locator_Value, Test_Data);
							test.log(LogStatus.INFO, Description);
						}else if(Function_Name.equalsIgnoreCase("clickAction")){
							FuctionLibrary.clickAction(driver, Locator_Type, Locator_Value);
							test.log(LogStatus.INFO, Description);
						}else if(Function_Name.equalsIgnoreCase("closeBrowser")){
							FuctionLibrary.closeBrowser(driver);
							test.log(LogStatus.INFO, Description);
						}
						else if(Function_Name.equalsIgnoreCase("captureData")){
							FuctionLibrary.captureData(driver,  Locator_Type, Locator_Value);
							test.log(LogStatus.INFO, Description);
						}
						else if(Function_Name.equalsIgnoreCase("tableValidation")){
							FuctionLibrary.tableValidation(driver, Test_Data);
							test.log(LogStatus.INFO, Description);
						}
						excel.setData(TCModule, j, 5, "PASS");
						ModuleStatus="PASS";
						test.log(LogStatus.PASS, Description);
						
					}catch(Exception e){
						System.out.println("the exception is ");
						e.printStackTrace();
						excel.setData(TCModule, j, 5, "FAIL");
						ModuleStatus="FAIL";
						File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
						FileUtils.copyFile(srcFile, new File("D:\\sridevi_82\\StockAccounting_Hybrid\\ScreenShots\\"+Description+FuctionLibrary.generateData()+".png"));
						test.log(LogStatus.FAIL, Description);
						break;
					}		
				}
				
				if(ModuleStatus.equalsIgnoreCase("PASS")){
					excel.setData("MasterTestCases", i, 3, "PASS");
				}else{
					excel.setData("MasterTestCases", i, 3, "FAIL");
				}
				
				report.endTest(test);
               report.flush();
				
			}else{
				excel.setData("MasterTestCases", i, 3, "Not Executed");
			}
			
			
		}

	}

}
