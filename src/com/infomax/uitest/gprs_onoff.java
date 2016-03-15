/* Author: Max.li */

package com.infomax.uitest;

import java.io.IOException;
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

public class gprs_onoff extends UiAutomatorTestCase {
	
	public static final int DEFAULT_LOOP = 300; /* Define a default loop count for testing. */
	public static final int DEFAULT_INTERVAL=20;/*Define on off interval*/
	TestSupport ts;
	UiWatcher anr_watcher;
	BuildTestSummary buildSummary;
	int i=1;
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		ts = new TestSupport();
		buildSummary = new BuildTestSummary();
		anr_watcher = new ANRWatcher("gprs");
		ts.getDevice().registerWatcher("ANR", anr_watcher);
		super.setUp();
	}

	//test gprs onoff
	public void test()throws UiObjectNotFoundException, RemoteException, IOException
	{
		getAutomationSupport().sendStatus(-1, buildSummary.buildSummary(getParams(), 
				TestSummary.gprs_onoff, DEFAULT_LOOP));
		
		//enter settings
		ts.launchapp("Settings");
		
		UiObject More=new UiObject(new UiSelector().textContains("Data usage"));
		More.clickAndWaitForNewWindow();
		
		UiObject Mobile_data=getbutton(0);
		init(Mobile_data);
		sleep(1);
		
		while(i<=ts.getLoop(getParams(),DEFAULT_LOOP)){
			//*******************SIMA*************//
			//data on
			Mobile_data.click();
			UiObject sima=new UiObject(new UiSelector().textContains("SIMA"));
			sima.clickAndWaitForNewWindow();
			ts.mSleep(ts.getInterval(getParams(),DEFAULT_INTERVAL));
			assertEquals("GPRS ON fail","ON",getbutton(0).getText());
			//data off
			Mobile_data.click();
			sleep(1);
			//new UiObject(new UiSelector().text("OK")).click();
			ts.mSleep(ts.getInterval(getParams(),DEFAULT_INTERVAL));
			assertEquals("GPRS OFF fail","OFF",getbutton(0).getText());
			ts.log("SIMA GPRS on&off for " +i+ " cycles");
			//*******************SIMB*************//
			//data on
			Mobile_data.click();
			UiObject simb=new UiObject(new UiSelector().textContains("SIMB"));
			simb.clickAndWaitForNewWindow();
			ts.mSleep(ts.getInterval(getParams(),DEFAULT_INTERVAL));
			assertEquals("GPRS ON fail","ON",getbutton(0).getText());
			//data off
			Mobile_data.click();
			sleep(1);
			//new UiObject(new UiSelector().text("OK")).click();
			ts.mSleep(ts.getInterval(getParams(),DEFAULT_INTERVAL));
			assertEquals("GPRS OFF fail","OFF",getbutton(0).getText());
			ts.log("SIMB GPRS on&off for " +i+ " cycles");
			i+=1;
		}
		ts.log("=============== Testing Done! ===============\n");
	}	
	//initialize button
	private void init(UiObject button) throws UiObjectNotFoundException {
		if(button.getText().equals("ON")){
			button.click();
			//new UiObject(new UiSelector().text("OK")).click();
			ts.log("initialize data status");
			ts.mSleep(ts.getInterval(getParams(),DEFAULT_INTERVAL));
		}
	}
	//get button by index  BT---index 2   WIFI---index 1
	public UiObject getbutton(int index){
		UiObject button=new UiObject(new UiSelector().className("android.widget.Switch"));
		return button;
	}
}
