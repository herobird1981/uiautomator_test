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

public class screen_unlock_slide extends UiAutomatorTestCase {
	
	public static final int DEFAULT_LOOP = 600; /* Define a default loop count for testing. */
	TestSupport ts;
	BuildTestSummary buildSummary;
	UiWatcher anr_watcher;
	int slide_start[]={240,635};
	int slide_end[]={470,635};
	
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
				TestSummary.screen_unlock_slide, DEFAULT_LOOP));
		
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
		
		//slide
		UiObject lock=new UiObject(new UiSelector().text("Slide"));
		lock.clickAndWaitForNewWindow();
		
		ts.getDevice().pressHome();
		ts.mSleep(2);
		
		int i=1;
		
		//start to test
		while(i<=ts.getLoop(getParams(),DEFAULT_LOOP))
		{
			ts.getDevice().sleep();
			ts.mSleep(5);
			ts.getDevice().wakeUp();
			ts.mSleep(2);
			slide();
			ts.mSleep(1);
			ts.log("slide lock&unlock "+i+" rounds");
			i+=1;
		}
		System.out.print("=============== Testing Done! ===============\n");
		//disable lock		
		ts.launchapp("Settings");
		settingItems = new UiScrollable(  
	            new UiSelector().scrollable(true));
		security = settingItems.getChildByText(  
	            new UiSelector().text("Security"), "Security",true); 
		security.clickAndWaitForNewWindow();
		scrnlock.clickAndWaitForNewWindow();
		lock=new UiObject(new UiSelector().text("None"));
		lock.clickAndWaitForNewWindow();	
	}

	public void slide(){
		ts.getDevice().drag(slide_start[0], slide_start[1], slide_end[0], slide_end[1], 5);			
	}
}
