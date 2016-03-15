/* Author: Jack.zhou */

package com.infomax.uitest;  

import libs.BuildTestSummary;
import libs.TestSummary;
import libs.TestSupport;
import android.os.RemoteException;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
  
public class lcd_onoff extends UiAutomatorTestCase 
{  
	public static final int DEFAULT_LOOP = 1000; /* Define a default loop count for testing. */
	public static final int DEFAULT_INTERVAL = 5;/*Define on off interval*/
	TestSupport ts;
	BuildTestSummary buildSummary;
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		ts = new TestSupport();
		buildSummary = new BuildTestSummary();
		super.setUp();
	}
	
	public void test() throws UiObjectNotFoundException, RemoteException 
	{		
		getAutomationSupport().sendStatus(-1, buildSummary.buildSummary(getParams(), 
				TestSummary.lcd_onoff, DEFAULT_LOOP));
		
		ts.getDevice().pressHome(); 	
		for(int i=1 ; i <= ts.getLoop(getParams(),DEFAULT_LOOP); i++)
		{
		 //sleep
			ts.getDevice().sleep();
			ts.mSleep(ts.getInterval(getParams(),DEFAULT_INTERVAL));
		 //resume
			ts.getDevice().wakeUp();
			ts.mSleep(ts.getInterval(getParams(),DEFAULT_INTERVAL));
		 //unlock
	        ts.getDevice().drag(360, 1000, 750, 1000, 5);
	        ts.mSleep(ts.getInterval(getParams(),DEFAULT_INTERVAL));
		}
	}
}