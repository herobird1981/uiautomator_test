package com.infomax.uitest;

import libs.TestSupport;
import android.os.RemoteException;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class test_temp extends UiAutomatorTestCase
{
	TestSupport ts = new TestSupport();	
	public void test() throws UiObjectNotFoundException, RemoteException
	{			
		ts.launchRecentApps();
		ts.mSleep(100);
	}	
}