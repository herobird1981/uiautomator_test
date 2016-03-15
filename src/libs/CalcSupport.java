package libs;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class CalcSupport extends UiAutomatorTestCase
{
	TestSupport ts = new TestSupport();
	public static boolean DEBUG = false;
	
	public UiDevice getDevice()
	{
		UiDevice mDevice = UiDevice.getInstance();
		return mDevice;
	}
	
	public void clearText() throws UiObjectNotFoundException
	{
		UiObject del_btn = findObjectByText("DELETE");
		UiObject clr_btn = findObjectByText("CLR");
		if (del_btn.exists())
		{
			del_btn.longClick();
		}
		if (clr_btn.exists())
		{
			clr_btn.click();
		}
	}
	
	public void deleteSingleChar() throws UiObjectNotFoundException
	{
		UiObject del_btn = findObjectByText("DELETE");
		if (del_btn.exists())
		{
			del_btn.click();
		}
	}
	
	public void launchAdvancedPanel() throws UiObjectNotFoundException
	{
		getDevice().pressMenu();
		findObjectByText("Advanced panel").click();
	}
	
	public void launchContextMenu() throws UiObjectNotFoundException
	{
		findObjectByClassName("android.widget.EditText").longClick();
	}
	
	public void clearHistory() throws UiObjectNotFoundException
	{
		getDevice().pressMenu();
		findObjectByText("Clear history").click();
	}
	
	public UiObject findObjectByText(String text) throws UiObjectNotFoundException
	{
		UiObject mObject = new UiObject(new UiSelector().text(text));
		return mObject;
	}
	
	public UiObject findObjectByResID(String id) throws UiObjectNotFoundException
	{
		UiObject mObject = new UiObject(new UiSelector().resourceId(id));
		return mObject;
	}
	
	public UiObject findObjectByClassName(String classname) throws UiObjectNotFoundException
	{
		UiObject mObject = new UiObject(new UiSelector().className(classname));
		return mObject;
	}
	
	public void basicInput(String str) throws UiObjectNotFoundException
	{
		for (int i=0; i< str.length(); i++)
		{
			if (str.charAt(i)== '0')
			{
				findObjectByResID("com.android.calculator2:id/digit0").click();
			}
			else if (str.charAt(i)== '1')
			{
				findObjectByResID("com.android.calculator2:id/digit1").click();
			}
			else if (str.charAt(i)== '2')
			{
				findObjectByResID("com.android.calculator2:id/digit2").click();
			}
			else if (str.charAt(i)== '3')
			{
				findObjectByResID("com.android.calculator2:id/digit3").click();
			}
			else if (str.charAt(i)== '4')
			{
				findObjectByResID("com.android.calculator2:id/digit4").click();
			}
			else if (str.charAt(i)== '5')
			{
				findObjectByResID("com.android.calculator2:id/digit5").click();
			}
			else if (str.charAt(i)== '6')
			{
				findObjectByResID("com.android.calculator2:id/digit6").click();
			}
			else if (str.charAt(i)== '7')
			{
				findObjectByResID("com.android.calculator2:id/digit7").click();
			}
			else if (str.charAt(i)== '8')
			{
				findObjectByResID("com.android.calculator2:id/digit8").click();
			}
			else if (str.charAt(i)== '9')
			{
				findObjectByResID("com.android.calculator2:id/digit9").click();
			}
			else if (str.charAt(i)== '.')
			{
				findObjectByResID("com.android.calculator2:id/dot").click();
			}
			else if (str.charAt(i)== '+')
			{
				findObjectByResID("com.android.calculator2:id/plus").click();
			}
			else if (str.charAt(i)== '-')
			{
				findObjectByResID("com.android.calculator2:id/minus").click();
			}
			else if (str.charAt(i)== '*')
			{
				findObjectByResID("com.android.calculator2:id/mul").click();
			}
			else if (str.charAt(i)== '/')
			{
				findObjectByResID("com.android.calculator2:id/div").click();
			}
			else if (str.charAt(i)== '=')
			{
				findObjectByResID("com.android.calculator2:id/equal").click();
			}
			ts.mSleep(1);
		}
	}
	
	public void advancedInput(String[] array) throws UiObjectNotFoundException
	{
		for (int i=0; i< array.length; i++)
		{
			if (array[i] == "sin")
			{
				launchAdvancedPanel();
				findObjectByResID("com.android.calculator2:id/sin").click();
			}
			else if (array[i] == "cos")
			{
				launchAdvancedPanel();
				findObjectByResID("com.android.calculator2:id/cos").click();
			}
			else if (array[i] == "tan")
			{
				launchAdvancedPanel();
				findObjectByResID("com.android.calculator2:id/tan").click();
			}
			else if (array[i] == "ln")
			{
				launchAdvancedPanel();
				findObjectByResID("com.android.calculator2:id/ln").click();
			}
			else if (array[i] == "log")
			{
				launchAdvancedPanel();
				findObjectByResID("com.android.calculator2:id/lg").click();
			}
			else if (array[i] == "!")
			{
				launchAdvancedPanel();
				findObjectByResID("com.android.calculator2:id/factorial").click();
			}
			else if (array[i] == "pi")
			{
				launchAdvancedPanel();
				findObjectByResID("com.android.calculator2:id/pi").click();
			}
			else if (array[i] == "e")
			{
				launchAdvancedPanel();
				findObjectByResID("com.android.calculator2:id/e").click();
			}
			else if (array[i] == "^")
			{
				launchAdvancedPanel();
				findObjectByResID("com.android.calculator2:id/power").click();
			}
			else if (array[i] == "(")
			{
				launchAdvancedPanel();
				findObjectByResID("com.android.calculator2:id/leftParen").click();
			}
			else if (array[i] == ")")
			{
				launchAdvancedPanel();
				findObjectByResID("com.android.calculator2:id/rightParen").click();
			}
			else if (array[i] == "sqrt")
			{
				launchAdvancedPanel();
				findObjectByResID("com.android.calculator2:id/sqrt").click();
			}
			else 
			{
				basicInput(array[i]);
			}
			ts.mSleep(1);
		}
	}
	
	/* To convert the original string get from EditText to normal characters (e.g. '-' / '.' / 'e' ...) */
	public String calcResultConvert(String str)
	{
		boolean point_ins_flag = true;
		String temp = "";
		StringBuffer sb = new StringBuffer();
		if (str.charAt(0)== 'm' && !str.contains("point"))
		{
			temp = str.replace("minus", "-");
		}
		else if(str.charAt(0)== 'm' && str.contains("point"))
		{
			temp = str.replace("minus", "-");
			for (int i=0; i< temp.length(); i++)
			{
				if (DEBUG)ts.log("temp length: " + temp.length());
				if (temp.charAt(i)=='0' || temp.charAt(i)=='1' || temp.charAt(i)=='2'
					|| temp.charAt(i)=='3' || temp.charAt(i)=='4' || temp.charAt(i)=='5'
					|| temp.charAt(i)=='6' || temp.charAt(i)=='7' || temp.charAt(i)=='8'
					|| temp.charAt(i)=='9' || temp.charAt(i)=='-' || temp.charAt(i)=='e')
				{
					if(DEBUG)ts.log("index "+ i + " is a digit!!!");
					sb.append(temp.charAt(i));
				}
				else
				{
					if (point_ins_flag)
					sb.append('.');
					point_ins_flag = false;
				}
			}
			temp = sb.toString();
		}
		else if (str.charAt(0)!= 'm' && str.contains("point"))
		{
			if(DEBUG) ts.log("Detect point!");			
			for (int i=0; i< str.length(); i++)
			{
				if (str.charAt(i)=='0' || str.charAt(i)=='1' || str.charAt(i)=='2' 
					|| str.charAt(i)=='3' || str.charAt(i)=='4' || str.charAt(i)=='5' 
					|| str.charAt(i)=='6' || str.charAt(i)=='7' || str.charAt(i)=='8'
					|| str.charAt(i)=='9' || str.charAt(i)=='e')
				{
					if(DEBUG)ts.log("index "+ i + " is a digit!!!");
					sb.append(str.charAt(i));
				}
				else 
				{
					if (point_ins_flag)
					sb.append('.');
					point_ins_flag = false;
				}
			}
			temp = sb.toString();
		}
		else temp = str;
		return temp;
	}
}
