/* Author: Sally.zhou */

package com.infomax.uitest; 

import libs.ANRWatcher;
import libs.BuildTestSummary;
import libs.IncomingCallWatcher;
import libs.TestSummary;
import libs.TestSupport;
import android.util.AndroidException;

import com.android.uiautomator.core.UiObject;  
import com.android.uiautomator.core.UiObjectNotFoundException;  
import com.android.uiautomator.core.UiSelector;  
import com.android.uiautomator.core.UiWatcher;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;  
  
public class mo_call extends UiAutomatorTestCase
{  
	public static final int DEFAULT_LOOP = 1000; /* Define a default loop count for test. */
	public static final String DEFAULT_NUM = "10086"; /* Define a default phone number for test. */
	TestSupport ts;
	BuildTestSummary buildSummary;
	UiWatcher mtcall_watcher, anr_watcher;
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		ts = new TestSupport();
		buildSummary = new BuildTestSummary();
		mtcall_watcher = new IncomingCallWatcher();
		anr_watcher = new ANRWatcher("mo_call");
		ts.getDevice().registerWatcher("MT_Call", mtcall_watcher);
		ts.getDevice().registerWatcher("ANR", anr_watcher);
		super.setUp();
	}
	
	public void test() throws UiObjectNotFoundException, AndroidException
    {
		getAutomationSupport().sendStatus(-1, buildSummary.buildSummary(getParams(), 
				TestSummary.mo_call, DEFAULT_LOOP));
		
		//enter phone
        ts.launchapp("Phone");
        
        ts.mSleep(1);
        clearCallLog();
        
        for(int i = 0; i < ts.getLoop(getParams(),DEFAULT_LOOP); i++ )
        { 
        	//click dialpad button
        	UiObject dialpadButton = new UiObject(new UiSelector().resourceId("com.android.dialer:id/dialpad_button")); 
        	dialpadButton.click(); 
        	ts.mSleep(1);
        	
        	UiObject inputField = new UiObject(new UiSelector().resourceId("com.android.dialer:id/digits"));
        	inputField.setText(ts.getNum(getParams(),DEFAULT_NUM));
        	
        	//dial out the number
        	dialPhone();
        	
        	//SIMA
        	UiObject selectboxA = new UiObject(new UiSelector().resourceId("android:id/button1"));
        	selectboxA.click();
        	ts.mSleep(5);
        	//end the call
        	endPhone();
        	ts.mSleep(2);
        	
        	
        	ts.getDevice().click(241, 198);
        	
        	//SIMB
        	UiObject selectboxB = new UiObject(new UiSelector().resourceId("android:id/button2"));
        	selectboxB.click();
        	ts.mSleep(5);
        	//end the call
        	endPhone();
        	ts.mSleep(2);

        	ts.log("mo call for "+ i + " cycle!");
        }  	
    }
	
	private void dialPhone() throws UiObjectNotFoundException
	{
		UiObject dialButton = new UiObject(new UiSelector().resourceId("com.android.dialer:id/dialButton")); 
    	dialButton.click();
		ts.mSleep(1);
    	
	}
	
	private void endPhone() throws UiObjectNotFoundException
	{
		UiObject endcallButton = new UiObject(new UiSelector().resourceId("com.android.dialer:id/endButton")); 
    	endcallButton.click(); 
	}
    
    private void clearCallLog() throws UiObjectNotFoundException
    {
    	UiObject callHistory = new UiObject(new UiSelector().resourceId("com.android.dialer:id/call_history_button")); 
    	callHistory.click(); 
    	ts.mSleep(1);
    	if (new UiObject(new UiSelector().text("Call log is empty.")).exists())
    	{
    		ts.getDevice().pressBack();
    	}
    	else
    	{
    		ts.getDevice().pressMenu();
    		UiObject clear = new UiObject(new UiSelector().text("Clear call log"));
    		clear.click();
    		ts.mSleep(1);
    		UiObject ok = new UiObject(new UiSelector().text("OK"));
    		ok.click();
    		ts.mSleep(1);
    		ts.getDevice().pressBack();
    		ts.mSleep(1);
    	}
    }
}         
       


