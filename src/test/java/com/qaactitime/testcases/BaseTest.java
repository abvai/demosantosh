package com.qaactitime.testcases;

import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import com.qaactitime.base.DriverScript;
import com.qaactitime.pages.EnterTimePage;
import com.qaactitime.pages.LoginPage;
import com.qaactitime.pages.TaskPage;
import com.qaactitime.utils.ExcelReader;
import com.qaactitime.utils.Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

/*
 * Name: BaseTest
 * Description: This Script is a parent script which has the set of prerequiste steps before executing actual test case
 * Author: Shantosh
 * Date Modified: 2/7/2022
 */

public class BaseTest extends DriverScript {
	
	protected static ExtentReports report;
	protected static ExtentTest logger;
	LoginPage lp;
	EnterTimePage ep;
	TaskPage tp;
	
	@BeforeSuite
	public void setReport()
	{
		ExtentHtmlReporter extent = new ExtentHtmlReporter("./acreports/actiauto.html");
		report = new ExtentReports();
		report.attachReporter(extent);
	}
	
	@BeforeMethod
	public void setUp()
	{
		initApplication();
		lp = new LoginPage();
		ep = new EnterTimePage();
		tp = new TaskPage();
	}
	
	@AfterMethod
	public void tearDown(ITestResult result)
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{
			try {
				logger.fail("Test Failed",MediaEntityBuilder.createScreenCaptureFromPath(Utils.captureScreenshot(driver)).build());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		report.flush();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		quitBrowser();
	}
	
	public void quitBrowser() {
		driver.quit();
		
	}

	@DataProvider(name="actiData")
	public Object[][] testData()
	{
		ExcelReader excel = new ExcelReader("./atTestdata/actidata.xls");
		int rows = excel.getRowCount(0);
		
		Object[][] data = new Object[rows][2];
		
		for(int i=0;i<rows;i++)
		{
			data[i][0]=excel.getCellData(0, i, 0);
			data[i][1]=excel.getCellData(0, i, 1);
		}
		
		return data;	
	}

}
