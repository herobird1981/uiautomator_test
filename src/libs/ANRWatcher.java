package libs;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.core.UiWatcher;

public class ANRWatcher implements UiWatcher
{
	TestSupport ts = new TestSupport();
	UiDevice mDevice = UiDevice.getInstance();
	UiObject anr = new UiObject(new UiSelector().textContains("Unfortunately"));
	String capFileName;
	
	public ANRWatcher(String name) {
		super();
		capFileName = name;
	}

	@Override
	public boolean checkForCondition() {
		// TODO Auto-generated method stub
		if (anr.exists())
		{
			ts.takeScreenCap("/sdcard/"+capFileName+"_anr.png");
			System.out.println("ANR happened!!! Test Stopped!!!!");
			return true;
		} 
		return false;
	}
}