package reports;

import java.io.File;
import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import driver.DriverScript;

public class ReportUtils extends DriverScript{
	/**************************************************
	 * Method Name		: startReport
	 * Purpose			:
	 * Author Name		:
	 *  
	 * ************************************************
	 */
	public ExtentReports startReport(String strFileName, String testCaseName) 
	{
		File resultDir = null;
		File screenShot = null;
		String strResDir = null;
		try {
			strResDir = System.getProperty("user.dir")+"\\Results";
			strResLocation = strResDir+"\\"+testCaseName;
			screenshotLocation = strResLocation+"\\screenshots";
			
			resultDir = new File(strResLocation);
			if(!resultDir.exists()) {
				resultDir.mkdir();
			}
			
			screenShot = new File(screenshotLocation);
			if(!screenShot.exists()) {
				screenShot.mkdir();
			}
			
			extent = new ExtentReports(strResLocation+"\\"+strFileName
					+appInd.getDataTime("MMyyyyhhmmss")+".html", true);
			extent.addSystemInfo("Host Name", System.getProperty("os.name"));
			extent.addSystemInfo("User Name", System.getProperty("user.name"));
			extent.addSystemInfo("Environemnt", System.getProperty("os.version"));
			extent.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));
			return extent;
		}catch(Exception e)
		{
			System.out.println("Exception in startReport() method. "+e.getMessage());
			return null;
		}
		finally {
			resultDir = null;
			screenShot = null;
		}
	}
	
	
	
	
	/**************************************************
	 * Method Name		: endTest
	 * Purpose			:
	 * Author Name		:
	 *  
	 * ************************************************
	 */
	public void endTest(ExtentTest test) {
		try {
			extent.endTest(test);
			extent.flush();
		}catch(Exception e)
		{
			System.out.println("Exception in endTest() method. "+e.getMessage());
		}
	}
	
	
	
	/**************************************************
	 * Method Name		: WriteResult
	 * Purpose			:
	 * Author Name		:
	 *  
	 * ************************************************
	 */
	public void WriteResult(WebDriver oDriver, String status, String description, ExtentTest test, 
			boolean isScreenshotRequired) {
		
		try {
			if(isScreenshotRequired) {
				switch(status.toLowerCase()) {
					case "pass":
						test.log(LogStatus.PASS, description+" : "
								+test.addScreenCapture(appInd.captureScreenshot(oDriver)));
						break;
					case "fail":
						test.log(LogStatus.FAIL, description+" : "
								+test.addScreenCapture(appInd.captureScreenshot(oDriver)));
						break;
					case "exception":
						test.log(LogStatus.FATAL, description+" : "
								+test.addScreenCapture(appInd.captureScreenshot(oDriver)));
						break;
					case "warning":
						test.log(LogStatus.WARNING, description+" : "
								+test.addScreenCapture(appInd.captureScreenshot(oDriver)));
						break;
					default:
						System.out.println("Invalid result status '"+status+"'");
				}
			}else {
				switch(status.toLowerCase()) {
				case "pass":
					test.log(LogStatus.PASS, description);
					break;
				case "fail":
					test.log(LogStatus.FAIL, description);
					break;
				case "exception":
					test.log(LogStatus.FATAL, description);
					break;
				case "warning":
					test.log(LogStatus.WARNING, description);
					break;
				default:
					System.out.println("Invalid result status '"+status+"'");
				}
			}
		}catch(Exception e)
		{
			System.out.println("Exception in WriteResult() method. "+e.getMessage());
		}
	}
	
}
