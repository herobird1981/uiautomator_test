package libs;

import java.io.File;
import android.os.Bundle;
import android.os.RemoteException;
import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class TestSupport extends UiAutomatorTestCase
{
	public UiDevice getDevice()
	{
		UiDevice mDevice = UiDevice.getInstance();
		return mDevice;
	}
	
	public void launchapp(String appname) throws UiObjectNotFoundException
	{
		getDevice().pressHome();
		new UiObject(new UiSelector().description("Apps")).clickAndWaitForNewWindow();
		//Horizontally scroll applist to find the app icon.
		UiObject pageNum = new UiObject(new UiSelector().resourceId("com.android.launcher3:id/page_indicator"));
        if (pageNum.exists())
        {
        	UiScrollable applist = new UiScrollable(new UiSelector().scrollable(true));
            applist.setAsHorizontalList();
            applist.setMaxSearchSwipes(pageNum.getChildCount());
            UiObject calc = applist.getChildByText(new UiSelector().text(appname), appname, true);
            calc.clickAndWaitForNewWindow();
        }
        else
        {
        	new UiObject(new UiSelector().textContains(appname)).clickAndWaitForNewWindow();
        }
        mSleep(2);
	}

	public int getLoop(Bundle bundle, int default_loop)
	{
		int run_loop;
		Bundle def_loop = bundle;
		/* Get command line parameters ("-e loop [value]") from Bundle */  
        if (def_loop.getString("loop")!=null)
        {
        	run_loop = Integer.valueOf(def_loop.getString("loop")).intValue();
        }
        else
        {
        	run_loop = default_loop;
        }
        return run_loop;
	}

	public int getSteps(Bundle bundle, int default_steps)
	{
		int drag_step;
		Bundle step = bundle;
		/* Get command line parameters ("-e step [value]") from Bundle */  
		if (step.getString("step")!=null)
        {
        	drag_step = Integer.valueOf(step.getString("step")).intValue();
        }
        else
        {
        	drag_step = default_steps;
        }
		return drag_step;
	}
	
	public int getInterval(Bundle bundle, int default_interval)
	{
		int interval;
		Bundle def_interval = bundle;
		if (def_interval.getString("interval")!=null)
        {
        	interval = Integer.valueOf(def_interval.getString("interval")).intValue();
        }
        else
        {
        	interval = default_interval;
        }
        return interval;
	}
	
	public String getNum(Bundle bundle, String default_num)
	{
		String phone_num;
		Bundle num = bundle;
		/* Get command line parameters ("-e step [value]") from Bundle */  
		if (num.getString("phone")!=null)
        {
			phone_num = num.getString("phone");
        }
        else
        {
        	phone_num = default_num;
        }
		return phone_num;
	}
	
	public String getPwd(Bundle bundle, String default_pwd)
	{
		String pw;
		/* Get command line parameters ("-e key value") from Bundle */
		Bundle def_password = bundle;
		if (def_password.getString("password")!=null)
		{
			pw = def_password.getString("password");
		}
		else
		{
			pw = default_pwd;
		}
		return pw;
	}
	
	public String getLockSequence(Bundle bundle, String default_seq)
	{
		String seq;
		Bundle def_seq = bundle;
		if (def_seq.getString("lockseq")!=null)
        {
			seq = def_seq.getString("lockseq");
        }
        else
        {
        	seq = default_seq;
        }
        return seq;
	}
	
	public void mSleep(int i)
	{
        try {
            Thread.sleep(i*1000);
        } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
	}
	
	public int random(int length)
	{
		int i = (int)(Math.random()*length);
		return i;
	}
	
	public void takeScreenCap(String path)
	{
		File pic = new File(path);
		getDevice().takeScreenshot(pic);
	}
	
	public void log(String str)
	{
		 System.out.println(str);
	}
	
	public void goBack()
	{
		getDevice().pressBack();
	}
	
	public void goHome()
	{
		getDevice().pressHome();
	}
	
	public void launchRecentApps() throws RemoteException
	{
		getDevice().pressRecentApps();
	}
	
	public void launchOptionMenu()
	{
		getDevice().pressMenu();
	}
	
	public void launchLandscapeView() throws UiObjectNotFoundException, RemoteException
	{
		if (getDevice().isNaturalOrientation())
		{
			getDevice().setOrientationLeft();
		}
	}
	
	public void launchPortraitView() throws RemoteException
	{
		getDevice().setOrientationNatural();
	}
}