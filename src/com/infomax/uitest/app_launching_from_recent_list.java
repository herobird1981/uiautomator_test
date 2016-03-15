/* Author: kevin.sun */

package com.infomax.uitest;

import libs.BuildTestSummary;
import libs.TestSummary;
import libs.TestSupport;
import android.os.RemoteException;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class app_launching_from_recent_list extends UiAutomatorTestCase
{
	public static final int DEFAULT_LOOP = 1000; /* Define a default loop count for testing. */
	int app_list_x = 300;
	int app_list_y[] = {60,220,450,690};
	int drag_start_point[] = {50,40};
	int drag_end_point[] = {50,780};
	TestSupport ts = new TestSupport();
	BuildTestSummary buildSummary = new BuildTestSummary();
	
	public void test() throws UiObjectNotFoundException, RemoteException
	{
		getAutomationSupport().sendStatus(-1, buildSummary.buildSummary(getParams(), 
				TestSummary.app_launching_from_recent_list, DEFAULT_LOOP));
		
		ts.log("run_loop=" + ts.getLoop(getParams(),DEFAULT_LOOP));
		for (int i=1; i<=ts.getLoop(getParams(),DEFAULT_LOOP); i++)
		{
			ts.launchRecentApps();
			ts.mSleep(2);
			dragList(ts.random(3));
			ts.mSleep(2);
			launchApp(ts.random(app_list_y.length));
			ts.mSleep(2);
		}	
	}
	
	private void dragList(int cycle)
	{
		if (cycle == 0)
		{
			return;
		}
		else
		{
			for (int i=1 ; i<=cycle; i++)
			{
				ts.getDevice().drag(drag_start_point[0], drag_start_point[1], 
								   drag_end_point[0], drag_end_point[1], 40);
				ts.mSleep(1);
			}
		}
	}
	
	private void launchApp (int n)
	{
		ts.getDevice().click(app_list_x, app_list_y[n]);
	}
}
