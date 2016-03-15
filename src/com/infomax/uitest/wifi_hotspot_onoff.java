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

public class wifi_hotspot_onoff extends UiAutomatorTestCase {
	
	public static final int DEFAULT_LOOP = 300; /* Define a default loop count for testing. */
	static final int DEFAULT_INTERVAL = 20;/*Define on off interval*/
	TestSupport ts;
	BuildTestSummary buildSummary;
	UiWatcher anr_watcher;
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		ts = new TestSupport();
		buildSummary = new BuildTestSummary();
		anr_watcher = new ANRWatcher("hotspot");
		ts.getDevice().registerWatcher("ANR", anr_watcher);
		super.setUp();
	}
	
	public void test()throws UiObjectNotFoundException, RemoteException, IOException
	{
		getAutomationSupport().sendStatus(-1, buildSummary.buildSummary(getParams(), 
				TestSummary.wifi_hotspot_onoff, DEFAULT_LOOP));
		
		int i=1;
		//enter settings
	    ts.launchapp("Settings");
		//press more...
		UiObject More=new UiObject(new UiSelector().textContains("More"));
		More.clickAndWaitForNewWindow();
		new UiObject(new UiSelector().textContains("portable hotspot"))
			.clickAndWaitForNewWindow();
		UiObject wifihotspot=getcheckbox(0);
		init(wifihotspot);
		sleep(3);
		
		while(i<=ts.getLoop(getParams(),DEFAULT_LOOP)){
	/*hotspot  on*/
			wifihotspot.click();
			ts.mSleep(ts.getInterval(getParams(),DEFAULT_INTERVAL));
			assertTrue("wifi hotspot on fail",getcheckbox(0).isChecked());

	/*hotspot  off*/
			wifihotspot.click();
			ts.mSleep(ts.getInterval(getParams(),DEFAULT_INTERVAL));
			assertTrue("wifi hotspot  off fail",!getcheckbox(0).isChecked());
			ts.log("wifi hotspot  on&off  " +i+ " cycles");
			i+=1;
			//ts.getDevice().pressBack();
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
	//get button by index  BT---index 2   WIFI---index 1
	public UiObject getcheckbox(int index){
		UiObject checkbox=new UiObject(new UiSelector().className("android.widget.CheckBox").index(index));
		return checkbox;
	}
}
