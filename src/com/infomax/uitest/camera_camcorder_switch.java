/* Author: Jack.zhou */

package com.infomax.uitest;

import libs.ANRWatcher;
import libs.BuildTestSummary;
import libs.TestSummary;
import libs.TestSupport;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.core.UiWatcher;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class camera_camcorder_switch extends UiAutomatorTestCase
{
	public static final int DEFAULT_LOOP = 600; /* Define a default loop count for testing. */
	TestSupport ts;
	UiWatcher anr_watcher;
	BuildTestSummary buildSummary;
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		ts = new TestSupport();
		buildSummary = new BuildTestSummary();
		anr_watcher = new ANRWatcher("camera");
		ts.getDevice().registerWatcher("ANR", anr_watcher);
		super.setUp();
	}
	
	public void test() throws UiObjectNotFoundException 
	{  
		getAutomationSupport().sendStatus(-1, buildSummary.buildSummary(getParams(), 
				TestSummary.camera_camcorder_switch, DEFAULT_LOOP));
		
		ts.launchapp("Camera");
        for(int i = 0; i < ts.getLoop(getParams(),DEFAULT_LOOP); i++)
        {
        	ts.mSleep(1);
        	UiObject switchSetting = new UiObject(new UiSelector().descriptionContains("Camera"));  
        	switchSetting.click();
        	ts.mSleep(1);
        	UiObject cameraSwitch1 = new UiObject(new UiSelector().descriptionContains("Switch to video"));
        	cameraSwitch1.click();
        	ts.mSleep(1);
        	switchSetting.click();
        	ts.mSleep(1);
        	UiObject cameraSwitch2 = new UiObject(new UiSelector().descriptionContains("Switch to photo"));  
        	cameraSwitch2.click();
        }
        ts.goBack();
        ts.goBack();
        ts.log("***************************Test is done!***************************");
    }
}

