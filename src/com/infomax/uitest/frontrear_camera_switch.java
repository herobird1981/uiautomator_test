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

public class frontrear_camera_switch extends UiAutomatorTestCase
{
	public static final int DEFAULT_LOOP = 600; /* Define a default loop count for testing. */
	int para[] = {540, 1190, 470, 950, 410, 940};
	TestSupport ts;
	BuildTestSummary buildSummary;
	UiWatcher anr_watcher;
	
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
				TestSummary.frontrear_camera_switch, DEFAULT_LOOP));
		
		ts.launchapp("Camera");
		
        for(int i = 0; i < ts.getLoop(getParams(),DEFAULT_LOOP); i++)
        {
        	ts.mSleep(1);
        	//click switch button
        	ts.getDevice().click(para[0], para[1]);
        	ts.mSleep(1);
        	//click front switch button
        	ts.getDevice().click(para[2], para[3]);
        	ts.mSleep(1); 
        	//click switch button
        	ts.getDevice().click(para[0], para[1]);
        	ts.mSleep(1);
        	//click rear switch button
        	ts.getDevice().click(para[4], para[5]);
        	UiObject checkApp = new UiObject(new UiSelector().packageName("com.android.camera2"));
			assertTrue("Enter in app failed!", checkApp.exists());
        }
        ts.getDevice().pressBack();
        ts.getDevice().pressBack();
        ts.log("***************************Test is done!***************************");
    }
}