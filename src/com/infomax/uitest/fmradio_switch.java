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

public class fmradio_switch extends UiAutomatorTestCase
{
	public static final int DEFAULT_LOOP = 300; /* Define a default loop count for testing. */
	TestSupport ts;
	UiWatcher anr_watcher;
	BuildTestSummary buildSummary;
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		ts = new TestSupport();
		buildSummary = new BuildTestSummary();
		anr_watcher = new ANRWatcher("FM");
		ts.getDevice().registerWatcher("ANR", anr_watcher);
		super.setUp();
	}
	
	public void test() throws UiObjectNotFoundException 
	{ 
		getAutomationSupport().sendStatus(-1, buildSummary.buildSummary(getParams(), 
				TestSummary.fmradio_switch, DEFAULT_LOOP));
			
        UiObject switchSetting = new UiObject(new UiSelector().resourceId("com.android.fm:id/fm_power"));  
        UiObject checkApp = new UiObject(new UiSelector().packageName("com.android.fm"));
        for(int i = 0; i < ts.getLoop(getParams(),DEFAULT_LOOP); i++)
        {
        	ts.launchapp("Radio");
        	assertTrue("FM launch failed!", checkApp.exists());
        	ts.mSleep(2);
        	// Turn on radio.
            switchSetting.click();
            ts.mSleep(5);
            // Turn off radio.
            switchSetting.click();
            ts.mSleep(5);			
        }
        ts.log("***************************Test is done!***************************");
    } 
}
