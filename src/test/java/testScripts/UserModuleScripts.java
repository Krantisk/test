package testScripts;

import java.util.Map;
import org.openqa.selenium.WebDriver;
import driver.DriverScript;

public class UserModuleScripts extends DriverScript{
	/*******************************************************
	 * TestScript Name		: TS_LoginLogout
	 * TestCase ID			: SRS_Login01
	 * Purpose				: 
	 * Return Type			: boolean
	 * Date Created			: 
	 * Date modified		:
	 * Reviewed By			:
	 * Example				:  
	 ******************************************************
	 */
	public boolean TS_LoginLogout() {
		WebDriver oBrowser = null;
		String strStatus = null;
		Map<String, String> objData = null;
		try {
			extent = reports.startReport("LoginLogout", "User_101");
			test = extent.startTest("Read Excel Test data");
			objData = datatable.getExcelTestData("userModule", "User_101");
			
			oBrowser = appInd.launchBrowser(objData.get("Browser"));
			if(oBrowser!=null) {
				strStatus+=appDep.navigateURL(oBrowser, appInd.readPropFile("URL"));
				strStatus+=appDep.loginToApp(oBrowser, objData.get("UserName"), 
						objData.get("Password"));
				strStatus+=appDep.logoutFromApp(oBrowser);
				appInd.closeBrowser(oBrowser);
			}else {
				return false;
			}
			
			if(strStatus.contains("false")) {
				return false;
			}else {
				return true;
			}
		}catch(Exception e)
		{
			reports.WriteResult(oBrowser, "Exception", "Exception in TS_LoginLogout() test script. "+e.getMessage(), test, false);
			return false;
		}
		finally {
			reports.endTest(test);
			oBrowser = null;
			strStatus = null;
			objData = null;
		}
	}
	
	
	
	/*******************************************************
	 * TestScript Name		: TS_CreateDeleteUser
	 * TestCase ID			: SRS_User01
	 * Purpose				: 
	 * Return Type			: boolean
	 * Date Created			: 
	 * Date modified		:
	 * Reviewed By			:
	 * Example				:  
	 ******************************************************
	 */
	public boolean TS_CreateDeleteUser() {
		WebDriver oBrowser = null;
		boolean blnRes = false;
		Map<String, String> objData = null;
		try {
			extent = reports.startReport("CreateDeleteUser", "User_102");
			test = extent.startTest("Read Excel Test data");
			objData = datatable.getExcelTestData("userModule", "User_102");
			
			
			oBrowser = appInd.launchBrowser(objData.get("Browser"));
			if(oBrowser!=null) {
				blnRes = appDep.navigateURL(oBrowser, appInd.readPropFile("URL"));
				if(!blnRes) return false;
				
				blnRes = appDep.loginToApp(oBrowser, objData.get("UserName"), 
						objData.get("Password"));
				if(!blnRes) return false;
				
				
				String strUserName = userMethods.createUser(oBrowser, objData);
				blnRes = userMethods.deleteUser(oBrowser, strUserName);
				if(!blnRes) return false;
				
				blnRes = appDep.logoutFromApp(oBrowser);
				if(!blnRes) return false;
				
			}else {
				return false;
			}
			return true;
		}catch(Exception e)
		{
			reports.WriteResult(oBrowser, "Exception", "Exception in TS_CreateDeleteUser() test script. "+e.getMessage(), test, false);
			return false;
		}
		finally {
			appInd.closeBrowser(oBrowser);
			reports.endTest(test);
			oBrowser = null;
			objData = null;
		}
	}
	
	
	
	/*******************************************************
	 * TestScript Name		: TS_LoginWithNewUser_DeleteUser
	 * TestCase ID			: SRS_User02
	 * Purpose				: 
	 * Return Type			: boolean
	 * Date Created			: 
	 * Date modified		:
	 * Reviewed By			:
	 * Example				:  
	 ******************************************************
	 */
	public boolean TS_LoginWithNewUser_DeleteUser() {
		WebDriver oBrowser1 = null;
		WebDriver oBrowser2 = null;
		String strStatus = null;
		Map<String, String> objData = null;
		try {
			extent = reports.startReport("CreateLoginAndDelete", "User_103");
			test = extent.startTest("Read Excel Test data");
			objData = datatable.getExcelTestData("userModule", "User_103");
			
			oBrowser1 = appInd.launchBrowser(objData.get("Browser"));
			if(oBrowser1!=null) {
				strStatus+=appDep.navigateURL(oBrowser1, appInd.readPropFile("URL"));
				strStatus+=appDep.loginToApp(oBrowser1, objData.get("UserName"), 
						objData.get("Password"));
				String strUserName = userMethods.createUser(oBrowser1, objData);
				
				//Login with new USer
				oBrowser2 = appInd.launchBrowser(objData.get("Browser"));
				if(oBrowser2!=null) {
					strStatus+=appDep.navigateURL(oBrowser2, appInd.readPropFile("URL"));
					boolean blnRes = appDep.loginToApp(oBrowser2, objData.get("User_UserName"), 
							objData.get("User_Password"));
					if(blnRes) {
						reports.WriteResult(oBrowser2, "Pass", "Login with new user was successful", test, false);
						oBrowser2.close();
					}else {
						reports.WriteResult(oBrowser2, "Fail", "Failed to login with newly created user", test, true);
						oBrowser2.close();
						return false;
					}
				}else {
					return false;
				}
				strStatus+=userMethods.deleteUser(oBrowser1, strUserName);
				strStatus+=appDep.logoutFromApp(oBrowser1);
				strStatus+=appInd.closeBrowser(oBrowser1);
			}else {
				return false;
			}
			
			if(strStatus.contains("false")) {
				return false;
			}else {
				return true;
			}
		}catch(Exception e)
		{
			reports.WriteResult(oBrowser1, "Exception", "Exception in TS_LoginWithNewUser_DeleteUser() test script. "+e.getMessage(), test, true);
			return false;
		}
		finally {
			reports.endTest(test);
			oBrowser1 = null;
			oBrowser2 = null;
			strStatus = null;
		}
	}
}
