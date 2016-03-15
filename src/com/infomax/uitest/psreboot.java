/* Author: Jack.zhou */

/* Pending ..................*/
package com.infomax.uitest;

import libs.TestSupport;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class psreboot extends UiAutomatorTestCase
{
	public static final int DEFAULT_LOOP = 10; /* Define a default loop count for testing. */
	TestSupport ts = new TestSupport();
	public void test() throws UiObjectNotFoundException
	{  
		//Bundle bundle = getParams();
		//int loop = Integer.valueOf(bundle.getString("loop"));
        getUiDevice().pressHome();  
        // Enter APP list.
        UiObject handleView = new UiObject(new UiSelector().descriptionContains("Apps"));  
        handleView.click();
        ts.mSleep(1);
        UiObject phone = new UiObject(new UiSelector().text("Phone"));  
        phone.clickAndWaitForNewWindow(); 
        ts.mSleep(1); 
        UiObject dialpadButton = new UiObject(new UiSelector().resourceId("com.android.dialer:id/dialpad_button")); 
        dialpadButton.click(); 
        ts.mSleep(1);
        String number[] = {"*", "#", "9", "8", "1", "5", "#", "*"};
        for(int i = 0; i < number.length; i++ )
        {
        	ts.mSleep(1);
		    UiObject nButton = new UiObject(new UiSelector().descriptionContains(number[i]));
		    nButton.click();
        }
        UiObject dialButton = new UiObject(new UiSelector().resourceId("com.android.dialer:id/dialButton")); 
        dialButton.click();
        UiScrollable settingItems = new UiScrollable(new UiSelector().scrollable(true));  
        UiObject engineerItem = settingItems.getChildByText(  
	            new UiSelector().text("Engineer mode"), "Engineer mode", true);  
        engineerItem.clickAndWaitForNewWindow(); 
        
        UiScrollable engineerItems = new UiScrollable(new UiSelector().scrollable(true));  
        UiObject atcommandItem = engineerItems.getChildByText(  
	            new UiSelector().text("AT command"), "AT command", true);  
        atcommandItem.clickAndWaitForNewWindow(); 
        
        
//      for(int i = 0; i < loop; i++){
//        }
        getUiDevice().pressBack();
        getUiDevice().pressBack();
        ts.log("***************************Test is done!***************************");
    }
}