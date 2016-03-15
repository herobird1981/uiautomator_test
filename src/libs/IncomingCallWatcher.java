package libs;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.core.UiWatcher;

public class IncomingCallWatcher implements UiWatcher
{
	UiDevice mDevice = UiDevice.getInstance();
	UiObject mtcall = new UiObject(new UiSelector().text("Incoming call"));
	@Override
	public boolean checkForCondition() {
		// TODO Auto-generated method stub
		if(mtcall.exists())
		{
			System.out.println("MO_Call watcher is triggered --> End this call!!");
			// End the call
			mDevice.drag(350,1003,150,1003,5);
			return true;
		}   
		return false;
	}
}