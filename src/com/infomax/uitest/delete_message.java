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
  
public class delete_message extends UiAutomatorTestCase
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
				TestSummary.delete_message, DEFAULT_LOOP));
		
		//enter sms
	    ts.launchapp("Messaging");
       
        for(int i = 0; i < ts.getLoop(getParams(),DEFAULT_LOOP); i++ )
        {
        	//long press first message
            ts.getDevice().drag(106, 150, 400, 150, 100);
            ts.mSleep(3);
            
            //press trash button
            UiObject trashButton = new UiObject(new UiSelector().resourceId("com.android.mms:id/delete")); 
            trashButton.clickAndWaitForNewWindow(); 
           
            
            //press delete button in dialog
            UiObject deleteButton = new UiObject(new UiSelector().resourceId("android:id/button1"));  
            deleteButton.click();  
            ts.mSleep(2); 
        }
        
        //Judge if all the message delete successfully
        UiObject emptySms = new UiObject(new UiSelector().text("No conversations."));
        assertTrue("Failed!! Some of SMS threads had NOT been deleted!", emptySms.exists());
    }
}         
