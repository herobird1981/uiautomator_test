/* Author: Max.li */

package com.infomax.uitest;

import java.io.IOException;
import libs.ANRWatcher;
import libs.BuildTestSummary;
import libs.TestSummary;
import libs.TestSupport;
import android.os.RemoteException;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.core.UiWatcher;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class screen_unlock_pin extends UiAutomatorTestCase {
	
	public static final int DEFAULT_LOOP = 600; /* Define a default loop count for testing. */
	TestSupport ts;
	UiWatcher anr_watcher;
	BuildTestSummary buildSummary;
	String pin="1234";
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		ts = new TestSupport();
		buildSummary = new BuildTestSummary();
		anr_watcher = new ANRWatcher("unlock");
		ts.getDevice().registerWatcher("ANR", anr_watcher);
		super.setUp();
	}
	
	public void test()throws UiObjectNotFoundException, RemoteException, IOException
	{	
		getAutomationSupport().sendStatus(-1, buildSummary.buildSummary(getParams(), 
				TestSummary.screen_unlock_pin, DEFAULT_LOOP));
		
		//enter screen lock
		ts.launchapp("Settings");	
		UiScrollable settingItems = new UiScrollable(  
	            new UiSelector().scrollable(true)); 
		UiObject security = settingItems.getChildByText(  
	            new UiSelector().text("Security"), "Security",  
	            true); 
		security.clickAndWaitForNewWindow();	
			
		UiObject scrnlock=new UiObject(new UiSelector().text("Screen lock"));
		scrnlock.clickAndWaitForNewWindow();
		
		//set pin
		UiObject lock=new UiObject(new UiSelector().text("PIN"));
		lock.clickAndWaitForNewWindow();
		new UiObject(new UiSelector().className("android.widget.EditText"))
			.setText(pin);
		new UiObject(new UiSelector().text("Continue"))
			.click();
		new UiObject(new UiSelector().className("android.widget.EditText"))
			.setText(pin);
		new UiObject(new UiSelector().text("OK"))
			.click();
		
		ts.getDevice().pressHome();
		ts.mSleep(2);
		
		int i=1;
		
		//start to test
		while(i<=ts.getLoop(getParams(),DEFAULT_LOOP)){

			ts.getDevice().sleep();
			ts.mSleep(5);
			ts.getDevice().wakeUp();
			ts.mSleep(2);
			inputpin();
			new UiObject(new UiSelector().resourceId("com.android.keyguard:id/key_enter"))
				.click();
			ts.mSleep(1);
			ts.log("pin lock&unlock "+i+" rounds");
			i+=1;
		}
		ts.log("=============== Testing Done! ===============\n");
		//disable lock
		
		ts.launchapp("Settings");
		settingItems = new UiScrollable(  
	            new UiSelector().scrollable(true));
		security = settingItems.getChildByText(  
	            new UiSelector().text("Security"), "Security",true); 
		security.clickAndWaitForNewWindow();
		scrnlock.clickAndWaitForNewWindow();
		new UiObject(new UiSelector().className("android.widget.EditText"))
		.setText(pin);
		new UiObject(new UiSelector().text("Next"))
		.click();
		lock=new UiObject(new UiSelector().text("None"));
		lock.clickAndWaitForNewWindow();

	}
	
	public void inputpin() throws UiObjectNotFoundException{
		new UiObject(new UiSelector().className("android.widget.Button").textContains("1"))
			.click();
		new UiObject(new UiSelector().className("android.widget.Button").textContains("2"))
		.click();
		new UiObject(new UiSelector().className("android.widget.Button").textContains("3"))
		.click();
		new UiObject(new UiSelector().className("android.widget.Button").textContains("4"))
		.click();
		
	}
}
