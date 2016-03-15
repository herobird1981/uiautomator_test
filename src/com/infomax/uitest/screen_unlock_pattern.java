/* Author: Max.li */

package com.infomax.uitest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import libs.ANRWatcher;
import libs.BuildTestSummary;
import libs.TestSummary;
import libs.TestSupport;
import android.graphics.Point;
import android.os.RemoteException;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.core.UiWatcher;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class screen_unlock_pattern extends UiAutomatorTestCase 
{
	public static final int DEFAULT_LOOP = 600; /* Define a default loop count for testing. */
	public static final String DEFAULT_LOCK_SEQ = "0124678";
	TestSupport ts;
	UiWatcher anr_watcher;
	BuildTestSummary buildSummary;
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		ts = new TestSupport();
		buildSummary = new BuildTestSummary();
		anr_watcher = new ANRWatcher("unlock");
		ts.getDevice().registerWatcher("ANR", anr_watcher);
		super.setUp();
	}
	
	public void test()throws UiObjectNotFoundException, RemoteException, IOException
	{
		getAutomationSupport().sendStatus(-1, buildSummary.buildSummary(getParams(), 
				TestSummary.screen_unlock_pattern, DEFAULT_LOOP));
		
		//Lock pattern
		Point[] lp=new Point[9];
		lp[0]=new Point(95,270);
		lp[1]=new Point(240,270);
		lp[2]=new Point(385,270);
		lp[3]=new Point(95,410);
		lp[4]=new Point(240,410);
		lp[5]=new Point(385,410);
		lp[6]=new Point(95,555);
		lp[7]=new Point(240,555);
		lp[8]=new Point(385,555);

		//Unlock pattern
		Point[] up=new Point[9];
		up[0]=new Point(120,385);
		up[1]=new Point(240,385);
		up[2]=new Point(360,385);
		up[3]=new Point(120,505);
		up[4]=new Point(240,505);
		up[5]=new Point(360,505);
		up[6]=new Point(120,620);
		up[7]=new Point(240,620);
		up[8]=new Point(360,620);
		
		// Put designated points into ArrayList, then convert ArrayList to Point array.
		List<Point> locksequence = new ArrayList<Point>();
		List<Point> unlocksequence = new ArrayList<Point>();

		for (int i=0; i < ts.getLockSequence(getParams(),DEFAULT_LOCK_SEQ).length(); i++)
		{
			locksequence.add(lp[Integer.parseInt(ts.getLockSequence(getParams(),DEFAULT_LOCK_SEQ).charAt(i)+"")]);
			unlocksequence.add(up[Integer.parseInt(ts.getLockSequence(getParams(),DEFAULT_LOCK_SEQ).charAt(i)+"")]);
		}
		
		Point[] lockseq = locksequence.toArray(new Point[locksequence.size()]);
		Point[] unlockseq = unlocksequence.toArray(new Point[unlocksequence.size()]);			

		//enter screen lock
		ts.launchapp("Settings");		
		UiScrollable settingItems = new UiScrollable(  
	            new UiSelector().scrollable(true)); 
		UiObject security = settingItems.getChildByText(  
	            new UiSelector().text("Security"), "Security",  
	            true); 
		security.clickAndWaitForNewWindow();	
			
		UiObject scrnlock=new UiObject(new UiSelector().text("Screen lock"));
		scrnlock.clickAndWaitForNewWindow();
		
		//set Pattern
		UiObject lock=new UiObject(new UiSelector().text("Pattern"));
		lock.clickAndWaitForNewWindow();
		
		ts.getDevice().swipe(lockseq, 20);/*draw pattern lp*/
		ts.mSleep(1);
		
		new UiObject(new UiSelector().text("Continue"))
			.click();
		ts.getDevice().swipe(lockseq, 20);/*draw pattern lp*/
		ts.mSleep(1);
		new UiObject(new UiSelector().text("Confirm"))
			.click();
		
		ts.getDevice().pressHome();
		ts.mSleep(2);
		
		int i=1;
		//start to test
		while(i<=ts.getLoop(getParams(),DEFAULT_LOOP)){

			ts.getDevice().sleep();
			ts.mSleep(5);
			ts.getDevice().wakeUp();
			ts.mSleep(2);
			ts.getDevice().swipe(unlockseq, 20);/*draw pattern up*/
			ts.mSleep(1);
			ts.log("password lock&unlock "+i+" rounds");
			i+=1;
		}
		ts.log("=============test done============");
		//disable lock
		ts.launchapp("Settings");
		settingItems = new UiScrollable(  
	            new UiSelector().scrollable(true));
		security = settingItems.getChildByText(  
	            new UiSelector().text("Security"), "Security",true); 
		security.clickAndWaitForNewWindow();
		scrnlock.clickAndWaitForNewWindow();
		ts.getDevice().swipe(lockseq, 20);/*draw pattern lp*/
		ts.mSleep(1);
		lock=new UiObject(new UiSelector().text("None"));
		lock.clickAndWaitForNewWindow();
	}
}
