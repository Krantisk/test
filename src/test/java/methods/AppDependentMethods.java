package methods;

import org.openqa.selenium.WebDriver;
import driver.DriverScript;
import locators.ObjectLocators;

public class AppDependentMethods extends DriverScript implements ObjectLocators{
	/*******************************************************
	 * Method Name			: navigateURL
	 * Purpose				: It is used to load the required URL
	 * Return Type			: void
	 * Date Created			: 
	 * Date modified		:
	 * Reviewed By			:
	 * Example				:  
	 ******************************************************
	 */
	public boolean navigateURL(WebDriver oDriver, String strURL)
	{
		try {
			oDriver.navigate().to(strURL);
			Thread.sleep(2000);
			if(appInd.compareValue(oDriver, oDriver.getTitle(), "actiTIME - Login")) {
				return true;
			}else {
				return false;
			}
		}catch(Exception e)
		{
			reports.WriteResult(oDriver, "Exception", "Exception in 'navigateURL()' method. "+e.getMessage(), test, true);
			return false;
		}
	}
	
	
	
	/*******************************************************
	 * Method Name			: loginToApp
	 * Purpose				: Login to the actiTime application
	 * Return Type			: void
	 * Date Created			: 
	 * Date modified		:
	 * Reviewed By			:
	 * Example				:  
	 ******************************************************
	 */
	public boolean loginToApp(WebDriver oDriver, String strUN, String strPWD)
	{
		String strStatus = null;
		try {
			test = extent.startTest("login To App");
			strStatus+=appInd.setObject(oDriver, obj_UserName_Edit, strUN);
			strStatus+=appInd.setObject(oDriver, obj_Password_Edit, strPWD);
			strStatus+=appInd.clickObject(oDriver, obj_Login_Btn);
			Thread.sleep(4000);
			
			//Verify optional element 
			if(appInd.verifyOptionalElement(oDriver, obj_Explore_Btn)) {
				strStatus+=String.valueOf(appInd.clickObject(oDriver, obj_Explore_Btn));
			}
			Thread.sleep(4000);
			
			
			//Handle the shortcut window
			if(appInd.verifyOptionalElement(oDriver, obj_ShortCut_Window))
			{
				strStatus+=appInd.clickObject(oDriver, obj_shortCut_Close_Btn);
			}
			
			
			//3. Verify login is successful
			strStatus+=appInd.verifyElementExist(oDriver, obj_Logo_Img);
			
			if(strStatus.contains("false"))
			{
				return false;
			}else {
				return true;
			}
		}catch(Exception e)
		{
			reports.WriteResult(oDriver, "Exception", "Exception in 'loginToApp()' method."+e.getMessage(), test, true);
			return false;
		}
	}
	
	
	
	/*******************************************************
	 * Method Name			: logoutFromApp()
	 * Purpose				: to logout from the actiTime application
	 * Return Type			: void
	 * Date Created			: 
	 * Date modified		:
	 * Reviewed By			:
	 * Example				:  
	 ******************************************************
	 */
	public boolean logoutFromApp(WebDriver oDriver)
	{
		String strStatus = null;
		try {
			test = extent.startTest("Logout from App");
			strStatus+=appInd.clickObject(oDriver, obj_Logout_Btn);
			Thread.sleep(2000);
			
			
			//9. VErify logout is successful
			strStatus+=appInd.verifyElementExist(oDriver, obj_Header_Text);
			
			if(strStatus.contains("false")) {
				reports.WriteResult(oDriver, "Fail", "FAiled to logout form the application", test, true);
				return false;
			}else {
				reports.WriteResult(oDriver, "PAss", "Logout is successful", test, false);
				return true;
			}
		}catch(Exception e)
		{
			reports.WriteResult(oDriver, "Exception", "Exception in 'logoutFromApp()' method."+e.getMessage(), test, true);
			return false;
		}
	}
}
