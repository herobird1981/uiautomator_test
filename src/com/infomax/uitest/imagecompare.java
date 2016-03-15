package com.infomax.uitest;

import java.io.File;
import java.io.FileNotFoundException;
import libs.ImageCompare;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class imagecompare extends UiAutomatorTestCase
{
	String path1 = "/sdcard/1.png";
	String path2 = "/sdcard/2.png";
	boolean result;
	public void test() throws UiObjectNotFoundException, FileNotFoundException
	{
		getUiDevice().pressHome();
		File pic1 = new File(path1);
		File pic2 = new File(path2);
		getUiDevice().takeScreenshot(pic1);
		sleep(4000);
		getUiDevice().takeScreenshot(pic2);
		sleep(1000);
		//result = ImageCompare.isSame(path1, path2, 0.97);
		result = ImageCompare.isSame(path1, path2, 0, 462, 100, 300, 1.0);
		assertTrue("Two pics are different!!",result);
	}
}
