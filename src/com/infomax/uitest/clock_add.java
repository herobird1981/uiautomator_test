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
  
public class clock_add extends UiAutomatorTestCase 
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
				TestSummary.clock_add, DEFAULT_LOOP));

		/* Enter clock interface. */ 
	    ts.launchapp("Clock");
		
		/* Enter clock edit interface. */
		UiObject clockicon = new UiObject(new UiSelector().className("android.app.ActionBar$Tab").index(0)); 
		clockicon.click();
		ts.mSleep(2);   
        
	    for(int loop = 0; loop < ts.getLoop(getParams(),DEFAULT_LOOP); loop++ )
	    {
			String text = "clock";
			String clockCount1 = loop + " clock";
	    	/* Enter clock add interface. */
	    	UiObject addicon = new UiObject(new UiSelector().resourceId("com.android.deskclock:id/alarm_add_alarm")); 
			addicon.click();
			ts.mSleep(2);
	    
	    	/* Click Done button. */
	    	UiObject donebutton = new UiObject(new UiSelector().text("Done"));  
	    	donebutton.click();  
	    	ts.mSleep(2);
	    	
	    	/* Edit label button. */
	    	ts.getDevice().click(100,3500);
	    	ts.mSleep(2);
		    /* Edit label text. */
		    
        	UiObject editName = new UiObject(new UiSelector().text("Label"));
        	editName.setText(loop + " "+ text);
        	ts.mSleep(2);
        	/* Click OK button. */
        	UiObject okbutton = new UiObject(new UiSelector().text("OK"));  
	    	okbutton.click();
	    	ts.mSleep(2);
		    
		    /* Judge the clock exist or not. */
			String clockCount2 = new UiObject(new UiSelector().resourceId("com.android.deskclock:id/edit_label")).getText();
			ts.log(clockCount2);
			if ( clockCount1.equals(clockCount2)){
				ts.log("***clocks add normally***");
			}
			else{
				ts.log("***clocks add abnormally***");
			}
	    }
	    
	}
}
