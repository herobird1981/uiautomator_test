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
  
public class contact_add extends UiAutomatorTestCase 
{  
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
	
	public void test() throws UiObjectNotFoundException ,RemoteException
	{     
		getAutomationSupport().sendStatus(-1, buildSummary.buildSummary(getParams(), 
				TestSummary.contact_add, DEFAULT_LOOP));
		
		/* Enter contacts interface. */ 
	    ts.launchapp("People");
		
		/* Create new contacts. */ 
		/* Choose Create a new contact.  
		UiObject createNew = new UiObject(new UiSelector().text("Create a new contact"));
		 Choose keep local. 
		UiObject keepLocal = new UiObject(new UiSelector().text("Keep local"));*/
		/* Click add contact. */ 
		UiObject addicon = new UiObject(new UiSelector().resourceId("com.android.contacts:id/menu_add_contact"));
		
		for(int loop = 0; loop < ts.getLoop(getParams(),DEFAULT_LOOP); loop++ )
		{
			/* judge how to enter edit interface  
			if (createNew.exists() )
			{
				createNew.click();
				if(keepLocal.exists()){
					keepLocal.click();
					}
			}
			else
			{
				addicon.click();
			}*/
			addicon.click();
			ts.mSleep(1);
		        	    
			/* Edit contact. */ 
        	String text = "android";
        	UiObject editName = new UiObject(new UiSelector().text("Name"));
        	UiObject actionbar = new UiObject(new UiSelector().resourceId("android:id/action_bar_title"));
        	editName.setText(text+loop);
        	ts.mSleep(1); 
	    
        	ts.getDevice().click(300,600);
        	int number = 10000;
        	int phoneNumber = number + loop;
        	UiObject phone = new UiObject(new UiSelector().text("Phone"));
        	phone.setText(String.valueOf(phoneNumber));
        	ts.mSleep(1); 
	    	/* Click done button. */ 
        	UiObject donebutton = new UiObject(new UiSelector().text("Done"));
        	donebutton.click();
        	ts.mSleep(3); 
	    	/* Click back key. */ 
	    	actionbar.click();
	    	ts.mSleep(1); 
        }
		
		/* Count contacts. */
		String contactCount1 = ts.getLoop(getParams(),DEFAULT_LOOP) + " contacts";
		String contactCount2 = new UiObject(new UiSelector().resourceId("com.android.contacts:id/contacts_count")).getText();
		ts.log(contactCount2);
		assertTrue("Adding contacts failed", contactCount1.equals(contactCount2));
	}
}
