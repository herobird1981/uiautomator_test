/* Author: Jack.zhou */

package com.infomax.uitest;  
import java.io.File;
import android.os.RemoteException;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.core.UiWatcher;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import libs.ANRWatcher;
import libs.BuildTestSummary;
import libs.TestSummary;
import libs.TestSupport;
  
public class app_launching_from_gridview extends UiAutomatorTestCase 
{  
	public static final int DEFAULT_LOOP = 1000; /* Define a default loop count for testing. */
	String applist[] = {"Browser", "Calculator", "Calendar", "Clock", "Downloads", "Email", 
						"Gallery", "Messaging", "Movie Studio", "Music", "People", "Phone", 
						"Radio", "Search", "Settings", "Recorder", "Voice Dialer"};
	
	String packageList[] = {"com.android.browser", "com.android.calculator2", "com.android.calendar", 
							"com.android.deskclock", "com.android.documentsui", "com.android.email", 
							"com.android.gallery3d", "com.android.mms", "com.android.videoeditor", 
							"com.android.music", "com.android.contacts", "com.android.dialer", "com.android.fm",
							"com.android.quicksearchbox", "com.android.settings", "com.android.soundrecorder", 
							"com.android.voicedialer"};
	
	TestSupport ts;
	UiWatcher anr_watcher;
	BuildTestSummary buildSummary;
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		ts = new TestSupport();
		anr_watcher = new ANRWatcher("app_launch");
		ts.getDevice().registerWatcher("ANR", anr_watcher);
		buildSummary = new BuildTestSummary();
		super.setUp();
	}
	
	public void test() throws UiObjectNotFoundException, RemoteException 
	{  
		getAutomationSupport().sendStatus(-1, buildSummary.buildSummary(getParams(), 
											TestSummary.app_launching_from_gridview, DEFAULT_LOOP));

	    File file = new File("/sdcard/screenshot/");
	    file.mkdir();
		
		  for(int i = 0; i < ts.getLoop(getParams(),DEFAULT_LOOP); i++)
		  {
				for(int j = 0; j < applist.length; j++ )
				{
					if(applist[j]=="Email")
					{
						ts.launchapp(applist[j]);
						UiObject checkApp = new UiObject(new UiSelector().packageName(packageList[j]));
						ts.mSleep(3);
						assertTrue("Launching app failed!", checkApp.exists());
						
						ts.mSleep(1);
						//屏幕先向左后向右旋转然后回到正常
						ts.getDevice().setOrientationLeft();
						ts.mSleep(2);
					    ts.takeScreenCap("/sdcard/screenshot/"+applist[j]+"_left"+i+".png");
					    ts.mSleep(1);
						ts.getDevice().setOrientationRight();
						ts.mSleep(2);
						ts.takeScreenCap("/sdcard/screenshot/"+applist[j]+"_right"+i+".png");
					    ts.launchPortraitView();
					    ts.mSleep(1);
						ts.goBack();
						ts.mSleep(1);
						ts.goBack();
						ts.mSleep(1);
					}
					else if(applist[j]=="Phone" || applist[j]=="Search" || applist[j]=="Radio" || applist[j]=="Voice Dialer"){
						ts.launchapp(applist[j]);
						UiObject checkApp = new UiObject(new UiSelector().packageName(packageList[j]));
						ts.mSleep(3);
						assertTrue("Launching app failed!", checkApp.exists());
						ts.mSleep(1); 
						ts.goBack();
						ts.mSleep(1);
					}
					else{
						ts.launchapp(applist[j]);
						UiObject checkApp = new UiObject(new UiSelector().packageName(packageList[j]));
						ts.mSleep(3);
						assertTrue("Launching app failed!", checkApp.exists());
						ts.mSleep(1); 
						ts.getDevice().setOrientationLeft();
						ts.mSleep(2);
						ts.takeScreenCap("/sdcard/screenshot/"+applist[j]+"_left"+i+".png");
					    ts. mSleep(1);
						ts.getDevice().setOrientationRight();
						ts.mSleep(2);
						ts.takeScreenCap("/sdcard/screenshot/"+applist[j]+"_right"+i+".png");
					    ts.launchPortraitView();
					    ts.goBack();
					    ts.mSleep(1);
						}
					}
					ts.log("***************************Test "+i+" cycle!***************************");
				}
		  ts.log("***************************Test is done!***************************");
	}
}