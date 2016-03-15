/* Author: Kevin.sun */

package com.infomax.uitest;  

import libs.ANRWatcher;
import libs.BuildTestSummary;
import libs.ConnectionProblemWatcher;
import libs.IncomingCallWatcher;
import libs.TestSummary;
import libs.TestSupport;
import android.os.RemoteException;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.core.UiWatcher;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
  
public class web_browsing extends UiAutomatorTestCase 
{  
	public static final int DEFAULT_LOOP = 600; /* Define a default loop count for testing. */
	TestSupport ts;
	UiWatcher mtcall_watcher, anr_watcher, conn_problem_watcher;
	BuildTestSummary buildSummary;
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		ts = new TestSupport();
		buildSummary = new BuildTestSummary();
		mtcall_watcher = new IncomingCallWatcher();
		anr_watcher = new ANRWatcher("browser");
		conn_problem_watcher = new ConnectionProblemWatcher();
		ts.getDevice().registerWatcher("MT_Call", mtcall_watcher);
		ts.getDevice().registerWatcher("ANR", anr_watcher);
		ts.getDevice().registerWatcher("Connection_problem", conn_problem_watcher);
		super.setUp();
	}
	
	public void test() throws UiObjectNotFoundException, RemoteException
	{  
		getAutomationSupport().sendStatus(-1, buildSummary.buildSummary(getParams(), 
				TestSummary.web_browsing, DEFAULT_LOOP));
		
		int bookmark_grid_x[] = {80,240,390};
		int bookmark_grid_y[] = {280,470,660,790};
		
		ts.launchapp("Browser");
	
		for(int i=1 ; i <= ts.getLoop(getParams(),DEFAULT_LOOP); i++)
		{
			goToBookmark();
			//ts.getDevice().click(570,630);
			ts.getDevice().click(bookmark_grid_x[ts.random(3)], bookmark_grid_y[ts.random(4)]);
			ts.mSleep(25);
		}
		
		if (ts.getDevice().hasWatcherTriggered("Connection_problem"))
		{
			ts.log("Connection_Problem watcher is triggered!!");
		}
		else 
		{
			ts.log("Connection_Problem watcher is NOT triggered!!");
		}
		
		if (ts.getDevice().hasWatcherTriggered("ANR"))
		{
			ts.log("ANR watcher is triggered!!");
		}
		else
		{
			ts.log("ANR watcher is NOT triggered!!");
		}		
	}
	
	private void goToBookmark() throws UiObjectNotFoundException
	{
		ts.getDevice().pressMenu();
		ts.mSleep(1);
		new UiObject(new UiSelector().text("Bookmarks")).clickAndWaitForNewWindow();
		ts.mSleep(1);
	}
}