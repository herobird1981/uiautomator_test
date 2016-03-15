package libs;

import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import android.os.Bundle;

public class BuildTestSummary extends UiAutomatorTestCase
{
	TestSupport ts = new TestSupport();
	Bundle testinfo = new Bundle();
	public Bundle buildSummary(Bundle bundle, String summary, int default_loop)
	{
		testinfo.putString("Test Summary", summary);
		testinfo.putInt("Total Cycles", ts.getLoop(bundle,default_loop));
		return testinfo;
	}
	
	public Bundle buildSummary(String summary, String critera)
	{
		testinfo.putString("Test Summary", summary);
		testinfo.putString("Test Critera", critera);
		return testinfo;
	}
}