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
  
public class contact_delete extends UiAutomatorTestCase {  
  
	public static final int DEFAULT_LOOP = 1000; /* Define a default loop count for testing. */
	TestSupport ts;
	UiWatcher anr_watcher;
	BuildTestSummary buildSummary;
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		ts = new TestSupport();
		buildSummary = new BuildTestSummary();
		anr_watcher = new ANRWatcher("contact");
		ts.getDevice().registerWatcher("ANR", anr_watcher);
		super.setUp();
	}
	
	public void test() throws UiObjectNotFoundException , RemoteException
	{ 
		getAutomationSupport().sendStatus(-1, buildSummary.buildSummary(getParams(), 
				TestSummary.contact_delete, DEFAULT_LOOP));
		
		/* Enter contact interface. */ 
	    ts.launchapp("People");
				
		for(int loop = 0; loop < ts.getLoop(getParams(),DEFAULT_LOOP); loop++ )
		{
			/* Click the top contact. */
			UiObject firstcontact = new UiObject(new UiSelector().resourceId("com.android.contacts:id/cliv_name_textview").index(3));
			firstcontact.click();
			ts.mSleep(2);
		
			/* Click menu. */
			UiObject menubutton = new UiObject(new UiSelector().className("android.widget.ImageButton"));
			menubutton.click();
			ts.mSleep(2);
		
			/* Click delete icon. */
			UiObject delete = new UiObject(new UiSelector().text("Delete"));  
			delete.click();  
			ts.mSleep(2);
			
			/* Click OK icon. */
			UiObject confirm = new UiObject(new UiSelector().text("OK"));  
			confirm.click();  
			ts.mSleep(2);
		}
		
		/* Judge the contact delete or not. */
		UiObject equalText = new UiObject(new UiSelector().text("No contacts."));
		assertTrue("^^^Delete failed!^^^", equalText.exists());
	}	
}
