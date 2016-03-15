package libs;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.core.UiWatcher;

public class ConnectionProblemWatcher implements UiWatcher
{
	TestSupport ts = new TestSupport();
	int conn_problem_count = 0;
	UiDevice mDevice = UiDevice.getInstance();
	UiObject conn_problem = new UiObject(new UiSelector().textContains("Connection problem"));	
	
	@Override
	public boolean checkForCondition() {
		// TODO Auto-generated method stub
		if (conn_problem.exists())
		{
			ts.takeScreenCap("/sdcard/conn_problem_dialog.png");
			mDevice.pressBack();
			mDevice.pressMenu();
			conn_problem_count = conn_problem_count+1;
			return true;
		}
		return false;
	}
}