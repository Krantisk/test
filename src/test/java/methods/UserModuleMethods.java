package methods;

import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import driver.DriverScript;
import locators.ObjectLocators;

public class UserModuleMethods extends DriverScript implements ObjectLocators{
	/*******************************************************
	 * Method Name			: createUser
	 * Purpose				: to create the user in the actiTime application
	 * Return Type			: void
	 * Date Created			: 
	 * Date modified		:
	 * Reviewed By			:
	 * Example				:  
	 ******************************************************
	 */
	public String createUser(WebDriver oDriver, Map<String, String> objData)
	{
		String strUserName = null;
		String strStatus = null;
		try {
			test = extent.startTest("Create a User");
			strStatus+=appInd.clickObject(oDriver, obj_USER_Menu);
			Thread.sleep(2000);
			
			strStatus+=appInd.clickObject(oDriver, obj_AddUser_Btn);
			Thread.sleep(2000);
			
			String LN = objData.get("FirstName");
			String FN = objData.get("LastName");
			strStatus+=appInd.setObject(oDriver, obj_FirstName_Edit, FN);
			strStatus+=appInd.setObject(oDriver, obj_LastName_Edit, LN);
			strStatus+=appInd.setObject(oDriver, obj_Email_Edit, objData.get("Email"));
			strStatus+=appInd.setObject(oDriver, obj_User_UN_Edit, objData.get("User_UserName"));
			strStatus+=appInd.setObject(oDriver, obj_User_PWD_Edit, objData.get("User_Password"));
			strStatus+=appInd.setObject(oDriver, obj_User_Retype_Edit, objData.get("User_Password"));
			
			
			//click on "Create User" button
			strStatus+=appInd.clickObject(oDriver, obj_CreateUser_Btn);
			Thread.sleep(2000);
			strUserName = LN+", "+FN;
			
			//5. Verify user is created
			strStatus+=appInd.verifyElementExist(oDriver, By.xpath("//div[@class='name']/span[text()="+"'"+LN+", "+FN+"'"+"]"));
			
			if(strStatus.contains("false")) {
				reports.WriteResult(oDriver, "Fail", "Failed to create the user", test, true);
				return null;
			}
			else {
				reports.WriteResult(oDriver, "Pass", "The user is created successful", test, false);
				return strUserName;
			}
		}catch(Exception e)
		{
			reports.WriteResult(oDriver, "Exception", "Exception in 'createUser()' method."+e.getMessage(), test, true);
			return null;
		}
	}
	
	
	
	/*******************************************************
	 * Method Name			: deleteUser
	 * Purpose				: to delete the user in the actiTime application
	 * Return Type			: void
	 * Date Created			: 
	 * Date modified		:
	 * Reviewed By			:
	 * Example				:  
	 ******************************************************
	 */
	public boolean deleteUser(WebDriver oDriver, String userToBeDeleted)
	{
		String strStatus = null;
		try {
			test = extent.startTest("Delete the User");
			strStatus+=appInd.clickObject(oDriver, By.xpath("//div[@class='name']/span[text()="+"'"+userToBeDeleted+"'"+"]"));
			Thread.sleep(2000);
			
			strStatus+=appInd.clickObject(oDriver, obj_DeleteUser_Btn);
			Thread.sleep(2000);
			
			oDriver.switchTo().alert().accept();
			Thread.sleep(2000);
			
			
			//7. verify user is deleted
			strStatus+=appInd.verifyElementNotExist(oDriver, By.xpath("//div[@class='name']/span[text()="+"'"+userToBeDeleted+"'"+"]"));
		
			if(strStatus.contains("false")) {
				reports.WriteResult(oDriver, "Fail", "Failed to delete the user '"+userToBeDeleted+"'", test, true);
				return false;
			}else {
				reports.WriteResult(oDriver, "Pass", "User deleted successful", test, false);
				return true;
			}
		}catch(Exception e)
		{
			reports.WriteResult(oDriver, "Exception", "Exception in 'deleteUser()' method."+e.getMessage(), test, true);
			return false;
		}
	}
}
