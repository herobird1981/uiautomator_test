/* Author: Max.li */

package com.infomax.uitest;

import libs.ANRWatcher;
import libs.BuildTestSummary;
import libs.TestSummary;
import libs.TestSupport;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.core.UiWatcher;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class wifi_bt_onoff extends UiAutomatorTestCase {
	
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
		anr_watcher = new ANRWatcher("wifi_bt");
		ts.getDevice().registerWatcher("ANR", anr_watcher);
		super.setUp();
	}	
	
	public void test()throws UiObjectNotFoundException
	{
		getAutomationSupport().sendStatus(-1, buildSummary.buildSummary(getParams(), 
				TestSummary.wifi_bt_onoff, DEFAULT_LOOP));
		
		ts.launchapp("Settings");
		UiObject wifi=getbutton(1);
		UiObject bluetooth=getbutton(2);
		init(wifi);
		init(bluetooth);
		
		while(i<=ts.getLoop(getParams(),DEFAULT_LOOP)){
	/*wifi on*/
			wifi.click();
			ts.mSleep(ts.getInterval(getParams(),DEFAULT_INTERVAL));
			assertEquals("Wifi on fail","ON",getbutton(1).getText());
			ts.getDevice().openQuickSettings();
			assertTrue("Wifi on fail",!geticon(3).getText().equals("Wi-Fi Off"));
			ts.getDevice().pressBack();
	/*wifi off*/		
			wifi.click();
			ts.mSleep(ts.getInterval(getParams(),DEFAULT_INTERVAL));
			assertEquals("wifi off fail","OFF",getbutton(1).getText());
			ts.getDevice().openQuickSettings();
			assertTrue("wifi off fail",geticon(3).getText().equals("Wi-Fi Off"));
			ts.getDevice().pressBack();
	/*BT on*/		
			bluetooth.click();
			ts.mSleep(ts.getInterval(getParams(),DEFAULT_INTERVAL));
			assertEquals("BT on fail","ON",getbutton(2).getText());
			ts.getDevice().openQuickSettings();
			assertTrue("BT on fail",!geticon(7).getText().equals("Bluetooth Off"));
			ts.getDevice().pressBack();
	/*BT off*/
			bluetooth.click();
			ts.mSleep(ts.getInterval(getParams(),DEFAULT_INTERVAL));
			assertEquals("BT off fail","OFF",getbutton(2).getText());
			ts.getDevice().openQuickSettings();
			assertTrue("BT off fail",geticon(7).getText().equals("Bluetooth Off"));
			ts.getDevice().pressBack();
			
			ts.log("BT wifi  ON OFF "+i+" rounds");
			i+=1;
		}
		ts.log("===================test done==============");
	} 
	
	//initialize button
		private void init(UiObject button) throws UiObjectNotFoundException {
			if(button.getText().equals("ON")){
				button.click();
				ts.log("initialize");
				ts.mSleep(ts.getInterval(getParams(),DEFAULT_INTERVAL));
			}
		}
		//icon index on quick settings wifi---3 airplane---6 BT---7
		public UiObject geticon(int index){
			UiObject icon=new UiObject(new UiSelector().className("android.widget.FrameLayout").index(index)
					.childSelector(new UiSelector().className("android.widget.TextView")));
			return icon;
			
		}
		//get button by index  BT---index 2   WIFI---index 1
		public UiObject getbutton(int index){
			UiObject button=new UiObject(new UiSelector().className("android.widget.LinearLayout").index(index)
					.childSelector(new UiSelector().className("android.widget.Switch")));
			return button;
		}
}
