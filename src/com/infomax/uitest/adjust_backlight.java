/* Author: kevin.sun */

package com.infomax.uitest;  
import libs.BuildTestSummary;
import libs.TestSummary;
import libs.TestSupport;
import android.os.RemoteException;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
  
public class adjust_backlight extends UiAutomatorTestCase 
{  
	public static final int DEFAULT_LOOP = 1000; /* Define a default loop count for testing. */
	TestSupport ts;
	BuildTestSummary buildSummary;
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		ts = new TestSupport();
		buildSummary = new BuildTestSummary();
		super.setUp();
	}
	
	public void test() throws UiObjectNotFoundException, RemoteException 
	{  
		getAutomationSupport().sendStatus(-1, buildSummary.buildSummary(getParams(), 
											TestSummary.adjust_backlight, DEFAULT_LOOP));
		
		int point_x[] = {210,229,248,267,286,305,324,343,362,381,398};
		int point_y = 418;
		
		ts.getDevice().pressHome(); 	
		ts.mSleep(1);
		ts.getDevice().openQuickSettings();
		ts.mSleep(1);
		UiObject brightness = new UiObject(new UiSelector().text("Brightness"));
		brightness.click();
		ts.mSleep(1);
		
		for(int i=1 ; i <= ts.getLoop(getParams(),DEFAULT_LOOP); i++)
		{
			for (int m=0; m<point_x.length; m++)
			{
				ts.getDevice().click(point_x[m],point_y);
				ts.mSleep(1);
			}
			for (int n=(point_x.length)-1; n>=0; n--)
			{
				ts.getDevice().click(point_x[n],point_y);
				ts.mSleep(1);
			}
		}
	}
}