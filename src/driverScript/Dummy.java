package driverScript;

import org.openqa.selenium.WebDriver;

import commonFunctionLibrary.FuctionLibrary;

public class Dummy {

	public static void main(String[] args) throws Exception {
		
		WebDriver driver1=FuctionLibrary.startBrowser();
		FuctionLibrary.openApplication(driver1);
		FuctionLibrary.waitForElement(driver1, "id","username", "10");
		FuctionLibrary.typeAction(driver1, "id","usename" ,"admin");
		FuctionLibrary.waitForElement(driver1, "name", "password", "10");
		FuctionLibrary.typeAction(driver1, "name", "password","master" );
		FuctionLibrary.waitForElement(driver1, "id", "btnsubmit", "10");
		FuctionLibrary.clickAction(driver1, "id", "btnsubmit");
	}

}
