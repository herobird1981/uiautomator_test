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
  
public class send_message extends UiAutomatorTestCase 
{   
	public static final int DEFAULT_LOOP = 100; /* Define a default loop count for testing. */
	public static final String DEFAULT_NUM = "15962195980"; /* Define a default phone number to receive message. */
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
				TestSummary.send_message, DEFAULT_LOOP));
    	
    	//enter sms 
	    ts.launchapp("Messaging");
        
        //click New message button
        ts.getDevice().click(45,766);
        
        int text=0;int j=0;
        
    	// Find to box and enter the number into it
        UiObject toBox = new UiObject(new UiSelector().text("To"));
        toBox.setText(ts.getNum(getParams(),DEFAULT_NUM));
        ts.mSleep(3);
        
        for(int i = 0; i<ts.getLoop(getParams(),DEFAULT_LOOP); i++ )
        {     	
        	// Find text box and enter the message into it
        	text=text+ 1;
        	UiObject textBox = new UiObject(new UiSelector().text("Type message"));
        	textBox.setText(String.valueOf(text));
        	ts.mSleep(3);
        	
        	//press send icon
        	UiObject sendicon = new UiObject(new UiSelector().resourceId("com.android.mms:id/send_button_sms")); 
        	sendicon.click(); 
        	//ts.mSleep(10);
        	//choose SIM card
        	//SIMA
        	ts.mSleep(1);
        	UiObject selectboxA = new UiObject(new UiSelector().resourceId("android:id/button1"));
        	selectboxA.click();
        	ts.mSleep(10);
        	//SIMB
        	textBox.setText(String.valueOf(text));
        	ts.mSleep(3);
        	sendicon.click(); 
        	ts.mSleep(1);
        	UiObject selectboxB = new UiObject(new UiSelector().resourceId("android:id/button2"));
        	selectboxB.click();
        	ts.mSleep(10);
        	
        	//judgment if message send ok
        	UiObject failbutton = new UiObject(new UiSelector().resourceId("com.android.mms:id/delivered_indicator")); 
        	if (failbutton.exists())
        	{ 		 
        		j=j+1;
    		}
        	
    	    ts.log("Message has send for "+ (i+1) + " cycle!");
         }     
            ts.log("send sms failed for "+ j + " cycle!"); 
    }
}