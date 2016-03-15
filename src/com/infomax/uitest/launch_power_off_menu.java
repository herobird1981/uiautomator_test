/* Pending... Because "getRuntime.exec()" could not be executed under MI2S native */

package com.infomax.uitest;

import java.io.IOException;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class launch_power_off_menu extends UiAutomatorTestCase
{
	
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}

	public void test() throws UiObjectNotFoundException
	{
		try {
			Runtime.getRuntime().exec("su sendevent /dev/input/event1 1 116 1;" +
									  "su sendevent /dev/input/event1 0 0 0;" +
									  "su sleep 1;" +
									  "su sendevent /dev/input/event1 1 116 0;" +
									  "su sendevent /dev/input/event1 0 0 0;");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("test");
	}
}