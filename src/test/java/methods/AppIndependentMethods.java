package methods;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import driver.DriverScript;

public class AppIndependentMethods extends DriverScript{
	/**************************************************
	 * Method Name		: getDataTime
	 * Purpose			: to create timestamp for the filenames
	 * Author Name		:
	 *  
	 * ************************************************
	 */
	public String getDataTime(String dtFormat) {
		Date dt = null;
		SimpleDateFormat sdf = null;
		try {
			dt = new Date();
			sdf = new SimpleDateFormat(dtFormat);
			return sdf.format(dt);
		}catch(Exception e)
		{
			reports.WriteResult(null, "Exception", "Exception in getDataTime() method."+e.getMessage(), test, false);
			return null;
		}
		finally {
			dt = null;
			sdf = null;
		}
	}
	
	
	
	/**************************************************
	 * Method Name		: captureScreenshot
	 * Purpose			:
	 * Author Name		:
	 *  
	 * ************************************************
	 */
	public String captureScreenshot(WebDriver oDriver) {
		File objFile = null;
		String resDestination = null;
		try {
			resDestination = screenshotLocation
					+"\\screenShot_"+appInd.getDataTime("MMYYhhmmss")+".png";
			objFile = ((TakesScreenshot)oDriver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(objFile, new File(resDestination));
			return resDestination;
		}catch(Exception e)
		{
			reports.WriteResult(oDriver, "Exception", "Exception in captureScreenshot() method."+e.getMessage(), test, true);
			return null;
		}
		finally {
			objFile = null;
			resDestination = null;
		}
	}
	
	
	
	
	/*******************************************************
	 * Method Name			: clickObject
	 * Purpose				: It is used to click on the elements
	 * Return Type			: boolean
	 * Date Created			: 
	 * Date modified		:
	 * Reviewed By			:
	 * Example				:  boolean blnRes = clickObject(oBrowser, By.xpath(""));
	 ******************************************************
	 */
	public boolean clickObject(WebDriver oDriver, By objBy)
	{
		WebElement oEle = null;
		try {
			oEle = oDriver.findElement(objBy);
			if(oEle.isDisplayed()) {
				oEle.click();
				reports.WriteResult(oDriver, "Pass", "The element '"+String.valueOf(objBy)+"' was clicked successful", test, false);
				return true;
			}else {
				reports.WriteResult(oDriver, "Fail", "Failed to find the element '"+String.valueOf(objBy)+"' in the DOM", test, true);
				return false;
			}
		}catch(Exception e)
		{
			reports.WriteResult(oDriver, "Exception", "Exception in clickObject() method. "+ e.getMessage(), test, true);
			return false;
		}
		finally {
			oEle = null;
		}
	}
	
	
	
	/*******************************************************
	 * Method Name			: clickObject
	 * Purpose				: It is used to click on the elements
	 * Return Type			: boolean
	 * Date Created			: 
	 * Date modified		:
	 * Reviewed By			:
	 * Example				:  boolean blnRes = clickObject(oBrowser, By.xpath(""));
	 ******************************************************
	 */
	public boolean clickObject(WebDriver oDriver, String objLocator)
	{
		WebElement oEle = null;
		try {
			oEle = oDriver.findElement(By.xpath(objLocator));
			if(oEle.isDisplayed()) {
				oEle.click();
				reports.WriteResult(oDriver, "Pass", "The element '"+objLocator+"' was clicked successful", test, false);
				return true;
			}else {
				reports.WriteResult(oDriver, "Fail", "Failed to find the element '"+objLocator+"' in the DOM", test, true);
				return false;
			}
		}catch(Exception e)
		{
			reports.WriteResult(oDriver, "Exception", "Exception in clickObject() method. "+ e.getMessage(), test, true);
			return false;
		}
		finally {
			oEle = null;
		}
	}
	
	
	/*******************************************************
	 * Method Name			: setObject
	 * Purpose				: It is used to enter the values in the elements
	 * Return Type			: boolean
	 * Date Created			: 
	 * Date modified		:
	 * Reviewed By			:
	 * Example				:  boolean blnRes = setObject(oBrowser, By.xpath(""), "values");
	 ******************************************************
	 */
	public boolean setObject(WebDriver oDriver, By objBy, String strData) {
		WebElement oEle = null;
		try {
			oEle = oDriver.findElement(objBy);
			if(oEle.isDisplayed()) {
				oEle.sendKeys(strData);
				reports.WriteResult(oDriver, "Pass", "The value '"+strData+"' was entered in the element '"+String.valueOf(objBy)+"'", test, false);
				return true;
			}else {
				reports.WriteResult(oDriver, "Fail", "Failed to find the element '"+String.valueOf(objBy)+"' in the DOM", test, true);
				return false;
			}
		}catch(Exception e)
		{
			reports.WriteResult(oDriver, "Exception", "Exception in setObject() method. "+ e.getMessage(), test, true);
			return false;
		}
		finally {
			oEle = null;
		}
	}
	
	
	
	/*******************************************************
	 * Method Name			: setObject
	 * Purpose				: It is used to enter the values in the elements
	 * Return Type			: boolean
	 * Date Created			: 
	 * Date modified		:
	 * Reviewed By			:
	 * Example				:  boolean blnRes = setObject(oBrowser, By.xpath(""), "values");
	 ******************************************************
	 */
	public boolean setObject(WebDriver oDriver, String objLocator, String strData) {
		WebElement oEle = null;
		try {
			oEle = oDriver.findElement(By.xpath(objLocator));
			if(oEle.isDisplayed()) {
				oEle.sendKeys(strData);
				reports.WriteResult(oDriver, "Pass", "The value '"+strData+"' was entered in the element '"+objLocator+"'", test, false);
				return true;
			}else {
				reports.WriteResult(oDriver, "Fail", "Failed to find the element '"+objLocator+"' in the DOM", test, true);
				return false;
			}
		}catch(Exception e)
		{
			reports.WriteResult(oDriver, "Exception", "Exception in setObject() method. "+ e.getMessage(), test, true);
			return false;
		}
		finally {
			oEle = null;
		}
	}
	
	
	
	
	/*******************************************************
	 * Method Name			: clearAndSetObject
	 * Purpose				: It is used to enter the values in the elements after clearing the old values
	 * Return Type			: boolean
	 * Date Created			: 
	 * Date modified		:
	 * Reviewed By			:
	 * Example				:  boolean blnRes = clearAndSetObject(oBrowser, By.xpath(""), "values");
	 ******************************************************
	 */
	public boolean clearAndSetObject(WebDriver oDriver, By objBy, String strData) {
		WebElement oEle = null;
		try {
			oEle = oDriver.findElement(objBy);
			if(oEle.isDisplayed()) {
				oEle.clear();
				oEle.sendKeys(strData);
				reports.WriteResult(oDriver, "Pass", "The value '"+strData+"' was entered in the element '"+String.valueOf(objBy)+"'", test , false);
				return true;
			}else {
				reports.WriteResult(oDriver, "Fail", "Failed to find the element '"+String.valueOf(objBy)+"' in the DOM", test, true);
				return false;
			}
		}catch(Exception e)
		{
			reports.WriteResult(oDriver, "Exception", "Exception in clearAndSetObject() method. "+ e.getMessage(), test, false);
			return false;
		}
		finally {
			oEle = null;
		}
	}
	
	
	
	/*******************************************************
	 * Method Name			: clearAndSetObject
	 * Purpose				: It is used to enter the values in the elements after clearing the old values
	 * Return Type			: boolean
	 * Date Created			: 
	 * Date modified		:
	 * Reviewed By			:
	 * Example				:  boolean blnRes = clearAndSetObject(oBrowser, By.xpath(""), "values");
	 ******************************************************
	 */
	public boolean clearAndSetObject(WebDriver oDriver, String objLocator, String strData) {
		WebElement oEle = null;
		try {
			oEle = oDriver.findElement(By.xpath(objLocator));
			if(oEle.isDisplayed()) {
				oEle.clear();
				oEle.sendKeys(strData);
				reports.WriteResult(oDriver, "Pass", "The value '"+strData+"' was entered in the element '"+objLocator+"'", test, false);
				return true;
			}else {
				reports.WriteResult(oDriver, "Fail", "Failed to find the element '"+objLocator+"' in the DOM", test, true);
				return false;
			}
		}catch(Exception e)
		{
			reports.WriteResult(oDriver, "Exception", "Exception in clearAndSetObject() method. "+ e.getMessage(), test, true);
			return false;
		}
		finally {
			oEle = null;
		}
	}
	
	
	
	/*******************************************************
	 * Method Name			: selectObject
	 * Purpose				: It is used to select the values from the dropdown elements
	 * Return Type			: boolean
	 * Date Created			: 
	 * Date modified		:
	 * Reviewed By			:
	 * Example				:  boolean blnRes = selectObject(oBrowser, By.xpath(""), "valuesToSelect");
	 ******************************************************
	 */
	public boolean selectObject(WebDriver oDriver, By objBy, String valueToSelect)
	{
		WebElement oEle = null;
		Select oSel = null;
		try {
			oEle = oDriver.findElement(objBy);
			if(oEle.isDisplayed()) {
				oSel = new Select(oEle);
				oSel.selectByVisibleText(valueToSelect);
				reports.WriteResult(oDriver, "Pass", "The value '"+valueToSelect+"' was selected from the dropdown element '"+String.valueOf(objBy)+"'", test, false);
				return true;
			}else {
				reports.WriteResult(oDriver, "Fail", "Failed to find the element '"+String.valueOf(objBy)+"' in the DOM", test, true);
				return false;
			}
		}catch(Exception e)
		{
			reports.WriteResult(oDriver, "Exception", "Exception in selectObject() method. "+ e.getMessage(), test, true);
			return false;
		}
		finally {
			oEle = null;
			oSel = null;
		}
	}
	
	
	/*******************************************************
	 * Method Name			: selectObject
	 * Purpose				: It is used to select the values from the dropdown elements
	 * Return Type			: boolean
	 * Date Created			: 
	 * Date modified		:
	 * Reviewed By			:
	 * Example				:  boolean blnRes = selectObject(oBrowser, By.xpath(""), "valuesToSelect");
	 ******************************************************
	 */
	public boolean selectObject(WebDriver oDriver, String objLocator, String valueToSelect)
	{
		WebElement oEle = null;
		Select oSel = null;
		try {
			oEle = oDriver.findElement(By.xpath(objLocator));
			if(oEle.isDisplayed()) {
				oSel = new Select(oEle);
				oSel.selectByVisibleText(valueToSelect);
				reports.WriteResult(oDriver, "Pass", "The value '"+valueToSelect+"' was selected from the dropdown element '"+objLocator+"'", test, false);
				return true;
			}else {
				reports.WriteResult(oDriver, "Fail", "Failed to find the element '"+objLocator+"' in the DOM", test, true);
				return false;
			}
		}catch(Exception e)
		{
			reports.WriteResult(oDriver, "Exception", "Exception in selectObject() method. "+ e.getMessage(), test, true);
			return false;
		}
		finally {
			oEle = null;
			oSel = null;
		}
	}
	
	
	/*******************************************************
	 * Method Name			: compareValue
	 * Purpose				: It is used to compare both actual & expected values
	 * Return Type			: boolean
	 * Date Created			: 
	 * Date modified		:
	 * Reviewed By			:
	 * Example				:  boolean blnRes = compareValue("ActualValue", "ExpectedValue");
	 ******************************************************
	 */
	public boolean compareValue(WebDriver oDriver, String actual, String expected) {
		try {
			if(actual.equalsIgnoreCase(expected)) {
				reports.WriteResult(oDriver, "Pass", "The both actual '"+actual+"' & expected '"+expected+"' are matching", test, false);
				return true;
			}else {
				reports.WriteResult(oDriver, "Fail", "Mis-match in both actual '"+actual+"' & expected '"+expected+"'", test, true);
				return false;
			}
		}catch(Exception e)
		{
			reports.WriteResult(oDriver, "Exception", "Exception in compareValue() method. "+ e.getMessage(), test, true);
			return false;
		}
	}
	
	
	
	/*******************************************************
	 * Method Name			: verifyText
	 * Purpose				: It is used to verify the text on the DOM
	 * Return Type			: boolean
	 * Date Created			: 
	 * Date modified		:
	 * Reviewed By			:
	 * Example				:  boolean blnRes = verifyText(oBrowser, By, Type, Expected);
	 ******************************************************
	 */
	public boolean verifyText(WebDriver oDriver, By objBy, String objType, String expected)
	{
		WebElement oEle = null;
		String actual = null;
		Select oSel = null;
		try {
			oEle = oDriver.findElement(objBy);
			if(oEle.isDisplayed()) {
				
				switch(objType.toLowerCase()) {
					case "text":
						actual = oEle.getText();
						break;
					case "value":
						actual = oEle.getAttribute("value");
						break;
					case "list":
						oSel = new Select(oEle);
						actual = oSel.getFirstSelectedOption().getText();
						break;
					default:
						reports.WriteResult(oDriver, "Fail", "Invalid object type '"+objType+"' was specified. please correct it & try", test, false);
						
				}
				
				if(appInd.compareValue(oDriver, actual, expected)) {
					return true;
				}else {
					return false;
				}
			}else {
				reports.WriteResult(oDriver, "Fail", "Failed to find the element '"+String.valueOf(objBy)+"' in the DOM", test, true);
				return false;
			}
		}catch(Exception e)
		{
			reports.WriteResult(oDriver, "Exception", "Exception in verifyText() method. "+ e.getMessage(), test, true);
			return false;
		}
		finally {
			oEle = null;
			oSel = null;
		}
	}
	
	
	
	
	/*******************************************************
	 * Method Name			: verifyText
	 * Purpose				: It is used to verify the text on the DOM
	 * Return Type			: boolean
	 * Date Created			: 
	 * Date modified		:
	 * Reviewed By			:
	 * Example				:  boolean blnRes = verifyText(oBrowser, By, Type, Expected);
	 ******************************************************
	 */
	public boolean verifyText(WebDriver oDriver, String objLocator, String objType, String expected)
	{
		WebElement oEle = null;
		String actual = null;
		Select oSel = null;
		try {
			oEle = oDriver.findElement(By.xpath(objLocator));
			if(oEle.isDisplayed()) {
				
				switch(objType.toLowerCase()) {
					case "text":
						actual = oEle.getText();
						break;
					case "value":
						actual = oEle.getAttribute("value");
						break;
					case "list":
						oSel = new Select(oEle);
						actual = oSel.getFirstSelectedOption().getText();
						break;
					default:
						reports.WriteResult(oDriver, "Fail", "Invalid object type '"+objType+"' was specified. please correct it & try", test, false);
						
				}
				
				if(appInd.compareValue(oDriver, actual, expected)) {
					return true;
				}else {
					return false;
				}
			}else {
				reports.WriteResult(oDriver, "Fail", "Failed to find the element '"+objLocator+"' in the DOM", test, true);
				return false;
			}
		}catch(Exception e)
		{
			reports.WriteResult(oDriver, "Exception", "Exception in verifyText() method. "+ e.getMessage(), test, true);
			return false;
		}
		finally {
			oEle = null;
			oSel = null;
		}
	}
	
	
	
	/*******************************************************
	 * Method Name			: verifyElementExist
	 * Purpose				: It is used to verify the presence of element in the DOM
	 * Return Type			: boolean
	 * Date Created			: 
	 * Date modified		:
	 * Reviewed By			:
	 * Example				:  boolean blnRes = verifyElementExist(oBrowser, By);
	 ******************************************************
	 */
	public boolean verifyElementExist(WebDriver oDriver, By objBy)
	{
		List<WebElement> oEles = null;
		try {
			oEles = oDriver.findElements(objBy);
			if(oEles.size()>0) {
				reports.WriteResult(oDriver, "PAss", "The element '"+String.valueOf(objBy)+"' present in the DOM", test, false);
				return true;
			}else {
				reports.WriteResult(oDriver, "Fail", "The element '"+String.valueOf(objBy)+"' doesnot exist in the DOM", test, true);
				return false;
			}
		}catch(Exception e)
		{
			reports.WriteResult(oDriver, "Exception", "Exception in verifyElementExist() method. "+ e.getMessage(), test, true);
			return false;
		}
		finally {
			oEles = null;
		}
	}
	
	
	
	/*******************************************************
	 * Method Name			: verifyElementExist
	 * Purpose				: It is used to verify the presence of element in the DOM
	 * Return Type			: boolean
	 * Date Created			: 
	 * Date modified		:
	 * Reviewed By			:
	 * Example				:  boolean blnRes = verifyElementExist(oBrowser, By);
	 ******************************************************
	 */
	public boolean verifyElementExist(WebDriver oDriver, String objLocator)
	{
		List<WebElement> oEles = null;
		try {
			oEles = oDriver.findElements(By.xpath(objLocator));
			if(oEles.size()>0) {
				reports.WriteResult(oDriver, "PAss", "The element '"+objLocator+"' present in the DOM", test, false);
				return true;
			}else {
				reports.WriteResult(oDriver, "Fail", "The element '"+objLocator+"' doesnot exist in the DOM", test, true);
				return false;
			}
		}catch(Exception e)
		{
			reports.WriteResult(oDriver, "Exception", "Exception in verifyElementExist() method. "+ e.getMessage(), test, true);
			return false;
		}
		finally {
			oEles = null;
		}
	}
	
	
	
	/*******************************************************
	 * Method Name			: verifyElementNotExist
	 * Purpose				: It is used to verify the non-presence of element in the DOM
	 * Return Type			: boolean
	 * Date Created			: 
	 * Date modified		:
	 * Reviewed By			:
	 * Example				:  boolean blnRes = verifyElementNotExist(oBrowser, By);
	 ******************************************************
	 */
	public boolean verifyElementNotExist(WebDriver oDriver, By objBy)
	{
		List<WebElement> oEles = null;
		try {
			oEles = oDriver.findElements(objBy);
			if(oEles.size()>0) {
				reports.WriteResult(oDriver, "Fail", "The element '"+String.valueOf(objBy)+"' still exist in the DOM", test, true);
				return false;
			}else {
				reports.WriteResult(oDriver, "Pass", "The element '"+String.valueOf(objBy)+"' was removed from the DOM", test, false);
				return true;
			}
		}catch(Exception e)
		{
			reports.WriteResult(oDriver, "Exception", "Exception in verifyElementNotExist() method. "+ e.getMessage(), test, true);
			return false;
		}
		finally {
			oEles = null;
		}
	}
	
	
	
	/*******************************************************
	 * Method Name			: verifyElementNotExist
	 * Purpose				: It is used to verify the non-presence of element in the DOM
	 * Return Type			: boolean
	 * Date Created			: 
	 * Date modified		:
	 * Reviewed By			:
	 * Example				:  boolean blnRes = verifyElementNotExist(oBrowser, By);
	 ******************************************************
	 */
	public boolean verifyElementNotExist(WebDriver oDriver, String objLocator)
	{
		List<WebElement> oEles = null;
		try {
			oEles = oDriver.findElements(By.xpath(objLocator));
			if(oEles.size()>0) {
				reports.WriteResult(oDriver, "Fail", "The element '"+objLocator+"' still exist in the DOM", test, true);
				return false;
			}else {
				reports.WriteResult(oDriver, "Pass", "The element '"+objLocator+"' was removed from the DOM", test, false);
				return true;
			}
		}catch(Exception e)
		{
			reports.WriteResult(oDriver, "Exception", "Exception in verifyElementNotExist() method. "+ e.getMessage(), test, true);
			return false;
		}
		finally {
			oEles = null;
		}
	}
	
	
	
	
	/*******************************************************
	 * Method Name			: verifyOptionalElement
	 * Purpose				: It is used to verify the presence of optional element in the DOM
	 * Return Type			: boolean
	 * Date Created			: 
	 * Date modified		:
	 * Reviewed By			:
	 * Example				:  boolean blnRes = verifyOptionalElemnet(oBrowser, By);
	 ******************************************************
	 */
	public boolean verifyOptionalElement(WebDriver oDriver, By objBy) {
		List<WebElement> oEles = null;
		try {
			oEles = oDriver.findElements(objBy);
			if(oEles.size()>0) {
				return true;
			}else {
				return false;
			}
		}catch(Exception e)
		{
			reports.WriteResult(oDriver, "Exception", "Exception in verifyOptionalElement() method. "+ e.getMessage(), test, false);
			return false;
		}
		finally {
			oEles = null;
		}
	}
	
	
	
	/*******************************************************
	 * Method Name			: verifyOptionalElement
	 * Purpose				: It is used to verify the presence of optional element in the DOM
	 * Return Type			: boolean
	 * Date Created			: 
	 * Date modified		:
	 * Reviewed By			:
	 * Example				:  boolean blnRes = verifyOptionalElemnet(oBrowser, By);
	 ******************************************************
	 */
	public boolean verifyOptionalElement(WebDriver oDriver, String objLocator) {
		List<WebElement> oEles = null;
		try {
			oEles = oDriver.findElements(By.xpath(objLocator));
			if(oEles.size()>0) {
				return true;
			}else {
				return false;
			}
		}catch(Exception e)
		{
			reports.WriteResult(oDriver, "Exception", "Exception in verifyOptionalElement() method. "+ e.getMessage(), test, false);
			return false;
		}
		finally {
			oEles = null;
		}
	}
	
	
	
	
	/*******************************************************
	 * Method Name			: closeBrowser()
	 * Purpose				: to close the browser & null the webdriver instance
	 * Return Type			: void
	 * Date Created			: 
	 * Date modified		:
	 * Reviewed By			:
	 * Example				:  
	 ******************************************************
	 */
	public boolean closeBrowser(WebDriver oDriver)
	{
		try {
			oDriver.close();
			return true;
		}catch(Exception e)
		{
			reports.WriteResult(oDriver, "Exception", "Exception in 'closeBrowser()' method."+e.getMessage(), test, false);
			return false;
		}
	}
	
	
	
	/*******************************************************
	 * Method Name			: launchBrowser
	 * Purpose				: It launches the chrome, ie & ff browsers
	 * Return Type			: webDriver instance
	 * Date Created			: 
	 * Date modified		:
	 * Reviewed By			:
	 * Example				:  ChromeDriver ch = launchBrowser("chrome");
	 ******************************************************
	 */
	public WebDriver launchBrowser(String strBrowser)
	{
		WebDriver oDriver = null;
		try {
			test = extent.startTest("launchBrowser");
			switch(strBrowser.toLowerCase()) {
				case "chrome":
					System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")
							+"\\Library\\drivers\\chromedriver.exe");
					oDriver = new ChromeDriver();
					break;
				case "firefox":
					System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")
							+"\\Library\\drivers\\geckodriver.exe");
					oDriver = new FirefoxDriver();
					break;
				case "ie":
					System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")
							+"\\Library\\drivers\\IEDriverServer.exe");
					oDriver = new InternetExplorerDriver();
					break;
				default:
					reports.WriteResult(oDriver, "Fail", "Invalid browser name '"+strBrowser+"' was specified.", test, false);					
			}
			
			if(oDriver!=null) {
				reports.WriteResult(oDriver, "Pass", "The '"+strBrowser+"' browser has launched successful", test, false);
				oDriver.manage().window().maximize();
				return oDriver;
			}else {
				reports.WriteResult(oDriver, "Fail", "Failed to launch '"+strBrowser+"' browser", test, false);
				return null;
			}
		}catch(Exception e)
		{
			reports.WriteResult(oDriver, "Exception", "Exception in 'launchBrowser()' method."+e.getMessage(), test, false);
			return null;
		}
	}
	
	
	
	/*******************************************************
	 * Method Name			: readPropFile
	 * Purpose				: It is used to read the key values from prop file
	 * Return Type			: boolean
	 * Date Created			: 
	 * Date modified		:
	 * Reviewed By			:
	 * Example				:  String strUN = readPropFile(strKeyValue);
	 ******************************************************
	 */
	public String readPropFile(String strKey) {
		FileInputStream fin = null;
		Properties prop = null;
		try {
			fin = new FileInputStream(System.getProperty("user.dir")+"\\Configuration\\Config.properties");
			prop = new Properties();
			prop.load(fin);
			return prop.getProperty(strKey);
		}catch(Exception e)
		{
			reports.WriteResult(null, "Exception", "Exception in 'readPropFile()' method."+e.getMessage(), test, false);
			return null;
		}
		finally
		{
			try {
				fin.close();
				fin = null;
				prop = null;
			}catch(Exception e)
			{
				reports.WriteResult(null, "Exception", "Exception in 'readPropFile()' method."+e.getMessage(), test, false);
				return null;
			}
		}
	}
}
