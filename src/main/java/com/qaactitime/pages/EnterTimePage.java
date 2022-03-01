package com.qaactitime.pages;

import com.qaactitime.base.DriverScript;

public class EnterTimePage extends DriverScript {
	// *********************************************** Page Elements ************************************************************* //
	
		@FindBy(id="logoutLink") WebElement linkLogout;
		@FindBy(xpath="//a[@class='userProfileLink username ']") WebElement textUserLoggedIn;
		@FindBy(id="container_tasks") WebElement menuTasks;
		
	// *********************************************** Page Initialization ***************************************************** //
		
		public EnterTimePage()
		{
			PageFactory.initElements(driver, this);
		}
		
	// *********************************************** Page Methods ******* ***************************************************** //
		
		
		public void clickTasksMenu()
		{
			menuTasks.click();
		}
		
		public String verifyUserLoggedIn()
		{
			return textUserLoggedIn.getText();
		}
		
		public void clickLogout()
		{
			linkLogout.click();
		}

}
