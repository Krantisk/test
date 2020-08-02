package driver;

import java.lang.reflect.Method;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import methods.AppDependentMethods;
import methods.AppIndependentMethods;
import methods.Datatable;
import methods.TaskModuleMethods;
import methods.UserModuleMethods;
import reports.ReportUtils;

public class DriverScript {
	public static AppIndependentMethods appInd = null;
	public static AppDependentMethods appDep = null;
	public static Datatable datatable = null;
	public static TaskModuleMethods taskMethods = null;
	public static UserModuleMethods userMethods = null;
	public static ReportUtils reports = null;
	public static ExtentReports extent = null;
	public static ExtentTest test = null;
	public static String strResLocation = null;
	public static String screenshotLocation = null;
	public static String strController = null;
	public static String moduleName = null;
	
	
	@BeforeSuite
	public void loadClasses() {
		try {
			appInd = new AppIndependentMethods();
			appDep = new AppDependentMethods();
			datatable = new Datatable();
			taskMethods = new TaskModuleMethods();
			userMethods = new UserModuleMethods();
			reports = new ReportUtils();
			strController = System.getProperty("user.dir")+"\\ExecutionController\\Controller.xlsx";
		}catch(Exception e)
		{
			System.out.println("Exception in () method. "+e.getMessage());
		}
	}
	
	
	@Test
	public void executeTestScripts()
	{
		int mRows = 0;
		int tcRow = 0;
		String executeTest = null;
		Class cls = null;
		Object obj = null;
		Method meth = null;
		try {
			mRows = datatable.getRowCount(strController, "Modules");
			for(int rows=1; rows<mRows-1; rows++)
			{
				executeTest = datatable.getCellData(strController, "Modules", "ExecuteModule", rows);
				if(executeTest.equalsIgnoreCase("Yes")) {
					moduleName = datatable.getCellData(strController, "Modules", "ModuleName", rows);
					tcRow = datatable.getRowCount(strController, moduleName);
					
					for(int row=1; row<tcRow; row++) {
						executeTest = datatable.getCellData(strController, moduleName, "ExecuteTest", row);
						if(executeTest.equalsIgnoreCase("Yes")) {
							String scriptName = datatable.getCellData(strController, moduleName, "TestScriptName", row);
							String className = datatable.getCellData(strController, moduleName, "ClassName", row);
							cls = Class.forName(className);
							obj = cls.newInstance();
							meth = obj.getClass().getMethod(scriptName);
							String status = String.valueOf(meth.invoke(obj));
							
							if(status.equalsIgnoreCase("true")) {
								datatable.setCellData(strController, moduleName, "Status", row, "PASS");
							}else {
								datatable.setCellData(strController, moduleName, "Status", row, "FAIL");
							}
						}
					}
				}
			}
		}catch(Exception e)
		{
			System.out.println("Exception in () method. "+e.getMessage());
		}
		finally {
			cls = null;
			obj = null;
			meth = null;
		}
	}
}
