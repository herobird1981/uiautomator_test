/* Author: Max.li */

package com.infomax.uitest;

import java.io.IOException;

import libs.ANRWatcher;
import libs.BuildTestSummary;
import libs.TestSummary;
import libs.TestSupport;
import android.os.Bundle;
import android.os.RemoteException;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.core.UiWatcher;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class flight_mode_onoff extends UiAutomatorTestCase {
	
	public static final int DEFAULT_LOOP = 300; /* Define a default loop count for testing. */
	public static final int DEFAULT_INTERVAL=20;/*Define on off interval*/
	int i=1;
	TestSupport ts;
	UiWatcher anr_watcher;
	BuildTestSummary buildSummary;
	Bundle bundle=getParams();
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		ts = new TestSupport();
		buildSummary = new BuildTestSummary();
		anr_watcher = new ANRWatcher("settings");
		ts.getDevice().registerWatcher("ANR", anr_watcher);
		super.setUp();
	}
	
	public void test()throws UiObjectNotFoundException, RemoteException, IOException
	{		
		getAutomationSupport().sendStatus(-1, buildSummary.buildSummary(getParams(), 
				TestSummary.flight_mode_onoff, DEFAULT_LOOP));
		
		//enter settings
	    ts.launchapp("Settings");
		//press more...
		UiObject More=new UiObject(new UiSelector().textContains("More"));
		More.clickAndWaitForNewWindow();

		UiObject airplane_mode=getcheckbox(0);
		init(airplane_mode);
		sleep(3);
		while(i<=ts.getLoop(getParams(),DEFAULT_LOOP)){
	/*airplane mode on*/
			airplane_mode.click();
			ts.mSleep(ts.getInterval(getParams(),DEFAULT_INTERVAL));
			assertTrue("flight mode on fail",getcheckbox(0).isChecked());
			ts.getDevice().openQuickSettings();
			assertTrue("flight mode on fail",geticon(4).getText().equals("No service"));
			ts.getDevice().pressBack();
	/*airplane mode off*/
			airplane_mode.click();
			ts.mSleep(ts.getInterval(getParams(),DEFAULT_INTERVAL));
			assertTrue("flight mode off fail",!getcheckbox(0).isChecked());
			ts.getDevice().openQuickSettings();
			sleep(2);
			assertTrue("flight mode off fail",!geticon(4).getText().equals("No service"));
			ts.log("flight mode on&off  " +i+ " cycles");
			i+=1;
			ts.getDevice().pressBack();
		}
		ts.log("=============== Testing Done! ===============\n");
	}	
	//initialize checkbox
	private void init(UiObject checkbox) throws UiObjectNotFoundException {
		if(checkbox.isChecked()){
			checkbox.click();
			ts.log("initialize Check box");
			ts.mSleep(ts.getInterval(getParams(),DEFAULT_INTERVAL));
		}
	}
	//icon index on quick settings wifi---3 airplane---6 BT---7  service----4
	public UiObject geticon(int index){
		UiObject icon=new UiObject(new UiSelector().className("android.widget.FrameLayout").index(index)
				.childSelector(new UiSelector().className("android.widget.TextView")));
		return icon;
		
	}
	//get button by index  BT---index 2   WIFI---index 1
	public UiObject getcheckbox(int index){
		UiObject ckeckbox=new UiObject(new UiSelector().className("android.widget.CheckBox"));
		return ckeckbox;
	}
}
