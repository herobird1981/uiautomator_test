package com.infomax.uitest;

import libs.ANRWatcher;
import libs.BuildTestSummary;
import libs.TestSummary;
import libs.TestSupport;
import android.os.RemoteException;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.core.UiWatcher;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
  
public class clock_delete extends UiAutomatorTestCase 
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
		anr_watcher = new ANRWatcher("clock");
		ts.getDevice().registerWatcher("ANR", anr_watcher);
		super.setUp();
	}
	
	public void test() throws UiObjectNotFoundException , RemoteException
	{ 
		getAutomationSupport().sendStatus(-1, buildSummary.buildSummary(getParams(), 
				TestSummary.clock_delete, DEFAULT_LOOP));
		
		/* Enter clock interface. */ 
	    ts.launchapp("Clock");
		
		/* Enter clock edit interface. */
        UiObject clockicon = new UiObject(new UiSelector().className("android.app.ActionBar$Tab").index(0)); 
		clockicon.click();
		ts.mSleep(2); 
	    
	    for(int loop = 0; loop  < ts.getLoop(getParams(),DEFAULT_LOOP); loop++ )
	    {
	    	/* Press delete icon. */
	    	ts.getDevice().click(440,260);
	    	ts.mSleep(2);
	    }
	    
	    UiObject equalText = new UiObject(new UiSelector().text("No Alarms"));
		assertTrue("^^^Delete failed!^^^", equalText.exists());
	}
}
