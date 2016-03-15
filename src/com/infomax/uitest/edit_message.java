/* Author: Sally.zhou */

package com.infomax.uitest;  

import libs.ANRWatcher;
import libs.BuildTestSummary;
import libs.IncomingCallWatcher;
import libs.TestSummary;
import libs.TestSupport;
import com.android.uiautomator.core.UiObject;  
import com.android.uiautomator.core.UiObjectNotFoundException;  
import com.android.uiautomator.core.UiSelector;  
import com.android.uiautomator.core.UiWatcher;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;  
  
public class edit_message extends UiAutomatorTestCase 
{  
	public static final int DEFAULT_LOOP = 1000; /* Define a default loop count for testing. */
	TestSupport ts;
	UiWatcher mtcall_watcher, anr_watcher;
	BuildTestSummary buildSummary;
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		ts = new TestSupport();
		buildSummary = new BuildTestSummary();
		mtcall_watcher = new IncomingCallWatcher();
		anr_watcher = new ANRWatcher("message");
		ts.getDevice().registerWatcher("MT_Call", mtcall_watcher);
		ts.getDevice().registerWatcher("ANR", anr_watcher);
		super.setUp();
	}
	
	public void test() throws UiObjectNotFoundException
    {
		getAutomationSupport().sendStatus(-1, buildSummary.buildSummary(getParams(), 
				TestSummary.edit_message, DEFAULT_LOOP));
		
		//enter sms 
	    ts.launchapp("Messaging");
        
        int toNumber=0;
        int text=0;
        for(int i = 0; i < ts.getLoop(getParams(),DEFAULT_LOOP); i++ )
        {    	     	
        	// Find and click New message button
        	UiObject newicon = new UiObject(new UiSelector().resourceId("com.android.mms:id/action_compose_new")); 
        	newicon.click();
        	
        	// Find to box and enter the number into it
        	toNumber=toNumber+ 1;
        	UiObject toBox = new UiObject(new UiSelector().text("To"));
        	toBox.setText(String.valueOf(toNumber));
        	ts.mSleep(2);
    		
        	// Find text box and enter the message into it
        	text=text+ 1;
        	UiObject textBox = new UiObject(new UiSelector().text("Type message"));
        	textBox.setText(String.valueOf(text));
        	ts.mSleep(2);
    	
        	ts.getDevice().pressBack();  
        	ts.getDevice().pressBack(); 
        	ts.log("edit sms for "+ i + " cycle!");
        	ts.mSleep(2);
        }
    }
}
       


