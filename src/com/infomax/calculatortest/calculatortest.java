/* Before executing the test suite, you need to copy reference PIC folder to SDcard*/
/* adb push x:\project_directory\ref_pic /sdcard/ref_pic */

package com.infomax.calculatortest;

import java.io.FileNotFoundException;
import android.os.RemoteException;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiWatcher;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import libs.BuildTestSummary;
import libs.CalcSupport;
import libs.Calc_SPEC;
import libs.IncomingCallWatcher;
import libs.ANRWatcher;
import libs.ImageCompare;
import libs.TestSummary;
import libs.TestSupport;

public class calculatortest extends UiAutomatorTestCase
{
	CalcSupport cs;
	TestSupport ts = new TestSupport();
	UiWatcher mtcall_watcher, anr_watcher;
	BuildTestSummary buildSummary;
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		cs = new CalcSupport();
		mtcall_watcher = new IncomingCallWatcher();
		anr_watcher = new ANRWatcher("calculator");
		cs.getDevice().registerWatcher("MT_Call", mtcall_watcher);
		cs.getDevice().registerWatcher("ANR", anr_watcher);
		buildSummary = new BuildTestSummary();
		super.setUp();
	}
	
	public void testcase1() throws UiObjectNotFoundException, FileNotFoundException
	{
		getAutomationSupport().sendStatus(-1, buildSummary.buildSummary(TestSummary.calc_testcase01, 
											Calc_SPEC.SPEC_CASE01));
		int capArea[] = {0,32,480,768}; /* index0: x / index1: y / index2: width / index3: height */
		boolean result;
		String ref_pic = Calc_SPEC.SPEC_CASE01;
		String test_pic = "/sdcard/case01.png";
		ts.launchapp("Calculator");
		ts.mSleep(1);
		cs.clearText();
		ts.mSleep(1);
		ts.takeScreenCap(test_pic);
		ts.mSleep(1);
		result = ImageCompare.isSame(test_pic, ref_pic, capArea[0], capArea[1], 
										capArea[2], capArea[3], 0.95);
		ts.mSleep(2);
		assertTrue("TestCase01 Failed!!", result);
		ts.log("Testcase01 Done!");
	}

	public void testcase2() throws UiObjectNotFoundException, FileNotFoundException, RemoteException
	{
		getAutomationSupport().sendStatus(-1, buildSummary.buildSummary(TestSummary.calc_testcase02, 
											Calc_SPEC.SPEC_CASE02));
		int capArea[] = {0,32,800,448}; /* index0: x / index1: y / index2: width / index3: height */
		boolean result;
		String ref_pic = Calc_SPEC.SPEC_CASE02;
		String test_pic = "/sdcard/case02.png";
		ts.launchapp("Calculator");
		ts.mSleep(1);
		ts.launchLandscapeView();
		ts.mSleep(3);
		ts.takeScreenCap(test_pic);
		ts.mSleep(2);
		ts.launchPortraitView();
		result = ImageCompare.isSame(test_pic, ref_pic, capArea[0], capArea[1], 
										capArea[2], capArea[3], 0.95);
		ts.mSleep(2);
		assertTrue("TestCase02 Failed!!", result);
		ts.log("Testcase02 Done!");
	}

	public void testcase4() throws UiObjectNotFoundException, RemoteException
	{
		getAutomationSupport().sendStatus(-1, buildSummary.buildSummary(TestSummary.calc_testcase04, 
											""));
		ts.launchRecentApps();
		ts.mSleep(4);
		assertTrue("Calculator launch failed!!",cs.findObjectByText("Calculator").exists());
		ts.goBack();
		ts.log("Testcase04 Done!");
	}
	
	public void testcase5a() throws UiObjectNotFoundException
	{
		getAutomationSupport().sendStatus(-1, buildSummary.buildSummary(TestSummary.calc_testcase05a, 
											Calc_SPEC.SPEC_CASE05_a));
		String input;
		ts.launchapp("Calculator");
		cs.basicInput("0123456789.");
		input = cs.calcResultConvert(cs.findObjectByClassName("android.widget.EditText").getText());
		assertTrue("TestCase5-a Failed!!", input.equals(Calc_SPEC.SPEC_CASE05_a));
		ts.log("TestCase05-a Done!");
	}
	
	public void testcase5b() throws UiObjectNotFoundException
	{
		getAutomationSupport().sendStatus(-1, buildSummary.buildSummary(TestSummary.calc_testcase05b, 
											Calc_SPEC.SPEC_CASE05_b));
		String result;
		ts.launchapp("Calculator");
		cs.clearText();
		cs.basicInput("0123456789.=");
		result = cs.calcResultConvert(cs.findObjectByClassName("android.widget.EditText").getText());
		assertTrue("TestCase5-b Failed!!", result.equals(Calc_SPEC.SPEC_CASE05_b));
		ts.log("TestCase05-b Done!");
	}
	
	public void testcase5c() throws UiObjectNotFoundException, FileNotFoundException
	{
		getAutomationSupport().sendStatus(-1, buildSummary.buildSummary(TestSummary.calc_testcase05c, 
											Calc_SPEC.SPEC_CASE05_c));
		int capArea[] = {0,38,480,157}; /* index0: x / index1: y / index2: width / index3: height */
		boolean result;
		String ref_pic = Calc_SPEC.SPEC_CASE05_c;
		String test_pic = "/sdcard/case05c.png";
		ts.launchapp("Calculator");
		cs.clearText();
		cs.basicInput("1+2-3*4/5");
		ts.mSleep(1);
		ts.takeScreenCap(test_pic);
		ts.mSleep(1);
		result = ImageCompare.isSame(test_pic, ref_pic, capArea[0], capArea[1], 
										capArea[2], capArea[3], 0.98);
		ts.mSleep(2);
		assertTrue("TestCase05-c Failed!!", result);
		ts.log("Testcase05-c Done!");
	}
	
	public void testcase5d() throws UiObjectNotFoundException
	{
		getAutomationSupport().sendStatus(-1, buildSummary.buildSummary(TestSummary.calc_testcase05d, 
											Calc_SPEC.SPEC_CASE05_d));
		String after_clear, after_delete;
		ts.launchapp("Calculator");
		cs.clearText();
		cs.basicInput("0123456789.");
		cs.clearText();
		after_delete = cs.findObjectByClassName("android.widget.EditText").getText();
		assertTrue("TestCase5-d Failed!!", after_delete.equals(Calc_SPEC.SPEC_CASE05_d));
		ts.mSleep(1);
		cs.basicInput("0123456789.=");
		cs.clearText();
		after_clear = cs.findObjectByClassName("android.widget.EditText").getText();
		assertTrue("TestCase5-d Failed!!", after_clear.equals(Calc_SPEC.SPEC_CASE05_d));
		ts.log("Testcase05-d Done!");
	}
	
	public void testcase5e() throws UiObjectNotFoundException
	{
		getAutomationSupport().sendStatus(-1, buildSummary.buildSummary(TestSummary.calc_testcase05e, 
										 	""));
		ts.launchapp("Calculator");
		ts.launchOptionMenu();
		assertTrue("TestCase5-e Failed!!",cs.findObjectByText("Clear history").exists() 
					&& cs.findObjectByText("Advanced panel").exists());
		ts.log("Testcase05-e Done!");
	}

	
	public void testcase6() throws UiObjectNotFoundException
	{
		getAutomationSupport().sendStatus(-1, buildSummary.buildSummary(TestSummary.calc_testcase06, 
											Calc_SPEC.SPEC_CASE06));
		String result;
		ts.launchapp("Calculator");
		cs.clearText();
		cs.basicInput("12345678901234567891=");
		result = cs.calcResultConvert(cs.findObjectByClassName("android.widget.EditText").getText());
		assertTrue("TestCase6 Failed!!", result.equals(Calc_SPEC.SPEC_CASE06));
		ts.log("TestCase06 Done!");
	}
	
	public void testcase7() throws UiObjectNotFoundException
	{
		getAutomationSupport().sendStatus(-1, buildSummary.buildSummary(TestSummary.calc_testcase07, 
											Calc_SPEC.SPEC_CASE07));
		String result;
		ts.launchapp("Calculator");
		cs.clearText();
		cs.basicInput("-12345678901234567891=");
		result = cs.calcResultConvert(cs.findObjectByClassName("android.widget.EditText").getText());
		assertTrue("TestCase7 Failed!!", result.equals(Calc_SPEC.SPEC_CASE07));
		ts.log("TestCase07 Done!");
	}
	
	public void testcase8() throws UiObjectNotFoundException
	{
		getAutomationSupport().sendStatus(-1, buildSummary.buildSummary(TestSummary.calc_testcase08, 
											Calc_SPEC.SPEC_CASE08));
		String result;
		ts.launchapp("Calculator");
		cs.clearText();
		cs.basicInput("0.1234567890123456789=");
		result = cs.calcResultConvert(cs.findObjectByClassName("android.widget.EditText").getText());
		assertTrue("TestCase8 Failed!!", result.equals(Calc_SPEC.SPEC_CASE08));
		ts.log("TestCase08 Done!");	
	}
	
	public void testcase9() throws UiObjectNotFoundException
	{
		getAutomationSupport().sendStatus(-1, buildSummary.buildSummary(TestSummary.calc_testcase09, 
											""));
		String result_a, result_b;
		ts.launchapp("Calculator");
		cs.clearText();
		cs.basicInput("0.1000=");
		result_a = cs.calcResultConvert(cs.findObjectByClassName("android.widget.EditText").getText());
		cs.clearText();
		cs.basicInput("0.1000+11=");
		result_b = cs.calcResultConvert(cs.findObjectByClassName("android.widget.EditText").getText());
		assertTrue("TestCase9 Failed!!", result_a.equals(Calc_SPEC.SPEC_CASE09_a)&& result_b.equals(Calc_SPEC.SPEC_CASE09_b));
		ts.log("Testcase09 Done!");	
	}
	
	public void testcaseA() throws UiObjectNotFoundException
	{
		getAutomationSupport().sendStatus(-1, buildSummary.buildSummary(TestSummary.calc_testcase10, 
											Calc_SPEC.SPEC_CASE10));
		String result;
		ts.launchapp("Calculator");
		cs.clearText();
		cs.basicInput("1+2=");
		result = cs.calcResultConvert(cs.findObjectByClassName("android.widget.EditText").getText());
		assertTrue("TestCase10 Failed!!", result.equals(Calc_SPEC.SPEC_CASE10));
		ts.log("TestCase10 Done!");	
	}
	
	public void testcaseB() throws UiObjectNotFoundException
	{
		getAutomationSupport().sendStatus(-1, buildSummary.buildSummary(TestSummary.calc_testcase11, 
											Calc_SPEC.SPEC_CASE11));
		String result;
		ts.launchapp("Calculator");
		cs.clearText();
		cs.basicInput("999999999999999+88888888888888=");
		result = cs.calcResultConvert(cs.findObjectByClassName("android.widget.EditText").getText());
		assertTrue("TestCase11 Failed!!", result.equals(Calc_SPEC.SPEC_CASE11));
		ts.log("TestCase11 Done!");	
	}
	
	public void testcaseC() throws UiObjectNotFoundException
	{
		getAutomationSupport().sendStatus(-1, buildSummary.buildSummary(TestSummary.calc_testcase12, 
											Calc_SPEC.SPEC_CASE12));
		String result;
		ts.launchapp("Calculator");
		cs.clearText();
		cs.basicInput("2-1=");
		result = cs.calcResultConvert(cs.findObjectByClassName("android.widget.EditText").getText());
		assertTrue("TestCase12 Failed!!", result.equals(Calc_SPEC.SPEC_CASE12));
		ts.log("TestCase12 Done!");	
	}
	
	public void testcaseD() throws UiObjectNotFoundException
	{
		getAutomationSupport().sendStatus(-1, buildSummary.buildSummary(TestSummary.calc_testcase13, 
											Calc_SPEC.SPEC_CASE13));
		String result;
		ts.launchapp("Calculator");
		cs.clearText();
		cs.basicInput("999999999999999-88888888888888=");
		result = cs.calcResultConvert(cs.findObjectByClassName("android.widget.EditText").getText());
		assertTrue("TestCase13 Failed!!", result.equals(Calc_SPEC.SPEC_CASE13));
		ts.log("TestCase13 Done!");	
	}
	
	public void testcaseE() throws UiObjectNotFoundException
	{
		getAutomationSupport().sendStatus(-1, buildSummary.buildSummary(TestSummary.calc_testcase14, 
											Calc_SPEC.SPEC_CASE14));
		String result;
		ts.launchapp("Calculator");
		cs.clearText();
		cs.basicInput("3*2=");
		result = cs.calcResultConvert(cs.findObjectByClassName("android.widget.EditText").getText());
		assertTrue("TestCase14 Failed!!", result.equals(Calc_SPEC.SPEC_CASE14));
		ts.log("TestCase14 Done!");	
	}
	
	public void testcaseF() throws UiObjectNotFoundException
	{
		getAutomationSupport().sendStatus(-1, buildSummary.buildSummary(TestSummary.calc_testcase15, 
											Calc_SPEC.SPEC_CASE15));
		String result;
		ts.launchapp("Calculator");
		cs.clearText();
		cs.basicInput("3333333333*2222222222=");
		result = cs.calcResultConvert(cs.findObjectByClassName("android.widget.EditText").getText());
		assertTrue("TestCase15 Failed!!", result.equals(Calc_SPEC.SPEC_CASE15));
		ts.log("TestCase15 Done!");	
	}
	
	public void testcaseG() throws UiObjectNotFoundException
	{
		getAutomationSupport().sendStatus(-1, buildSummary.buildSummary(TestSummary.calc_testcase16, 
											Calc_SPEC.SPEC_CASE16));
		String result;
		ts.launchapp("Calculator");
		cs.clearText();
		cs.basicInput("3/2=");
		result = cs.calcResultConvert(cs.findObjectByClassName("android.widget.EditText").getText());
		assertTrue("TestCase16 Failed!!", result.equals(Calc_SPEC.SPEC_CASE16));
		ts.log("TestCase16 Done!");	
	}
	
	public void testcaseH() throws UiObjectNotFoundException
	{
		getAutomationSupport().sendStatus(-1, buildSummary.buildSummary(TestSummary.calc_testcase17, 
											Calc_SPEC.SPEC_CASE17));
		String result;
		ts.launchapp("Calculator");
		cs.clearText();
		cs.basicInput("3333333333/2222222222=");
		result = cs.calcResultConvert(cs.findObjectByClassName("android.widget.EditText").getText());
		assertTrue("TestCase17 Failed!!", result.equals(Calc_SPEC.SPEC_CASE17));
		ts.log("TestCase17 Done!");	
	}
	
	public void testcaseI() throws UiObjectNotFoundException, FileNotFoundException
	{
		getAutomationSupport().sendStatus(-1, buildSummary.buildSummary(TestSummary.calc_testcase18, 
											Calc_SPEC.SPEC_CASE18));
		int capArea[] = {420,100,45,45}; /* index0: x / index1: y / index2: width / index3: height */
		boolean result;
		String ref_pic = Calc_SPEC.SPEC_CASE18;
		String test_pic = "/sdcard/case18.png";
		ts.launchapp("Calculator");
		cs.clearText();
		cs.basicInput("3/0=");
		ts.mSleep(1);
		ts.takeScreenCap(test_pic);
		ts.mSleep(1);
		result = ImageCompare.isSame(test_pic, ref_pic, capArea[0], capArea[1], 
										capArea[2], capArea[3], 1.0);
		ts.mSleep(2);
		assertTrue("TestCase18 Failed!!", result);
		ts.log("TestCase18 Done!");	
	}
	
	public void testcaseJ() throws UiObjectNotFoundException
	{
		getAutomationSupport().sendStatus(-1, buildSummary.buildSummary(TestSummary.calc_testcase19, 
											Calc_SPEC.SPEC_CASE19));
		String result;
		ts.launchapp("Calculator");
		cs.clearText();
		cs.basicInput("47+93-46*37/57=");
		result = cs.calcResultConvert(cs.findObjectByClassName("android.widget.EditText").getText());
		assertTrue("TestCase19 Failed!!", result.equals(Calc_SPEC.SPEC_CASE19));
		ts.log("TestCase19 Done!");	
	}
	
	public void testcaseK() throws UiObjectNotFoundException
	{
		getAutomationSupport().sendStatus(-1, buildSummary.buildSummary(TestSummary.calc_testcase20, 
											Calc_SPEC.SPEC_CASE20));
		String result;
		ts.launchapp("Calculator");
		cs.clearText();
		cs.basicInput("47+93=-46=*37=/57=");
		result = cs.calcResultConvert(cs.findObjectByClassName("android.widget.EditText").getText());
		assertTrue("TestCase20 Failed!!", result.equals(Calc_SPEC.SPEC_CASE20));
		ts.log("TestCase20 Done!");	
	}
	
	public void testcaseL() throws UiObjectNotFoundException
	{
		getAutomationSupport().sendStatus(-1, buildSummary.buildSummary(TestSummary.calc_testcase21, 
											Calc_SPEC.SPEC_CASE21));
		String result;
		ts.launchapp("Calculator");
		cs.clearText();
		cs.basicInput("47+93-46*37/0.57=");
		result = cs.calcResultConvert(cs.findObjectByClassName("android.widget.EditText").getText());
		assertTrue("TestCase21 Failed!!", result.equals(Calc_SPEC.SPEC_CASE21));
		ts.log("TestCase21 Done!");	
	}
	
	public void testcaseM() throws UiObjectNotFoundException
	{
		getAutomationSupport().sendStatus(-1, buildSummary.buildSummary(TestSummary.calc_testcase22, 
											Calc_SPEC.SPEC_CASE22));
		String result;
		ts.launchapp("Calculator");
		cs.clearText();
		cs.basicInput("999+0.1=");
		result = cs.calcResultConvert(cs.findObjectByClassName("android.widget.EditText").getText());
		assertTrue("TestCase22 Failed!!", result.equals(Calc_SPEC.SPEC_CASE22));
		ts.log("TestCase22 Done!");	
	}
	
	public void testcaseN() throws UiObjectNotFoundException
	{
		getAutomationSupport().sendStatus(-1, buildSummary.buildSummary(TestSummary.calc_testcase23, 
											Calc_SPEC.SPEC_CASE23));
		String result;
		ts.launchapp("Calculator");
		cs.clearText();
		cs.basicInput("-123-99=");
		result = cs.calcResultConvert(cs.findObjectByClassName("android.widget.EditText").getText());
		assertTrue("TestCase23 Failed!!", result.equals(Calc_SPEC.SPEC_CASE23));
		ts.log("TestCase23 Done!");	
	}
	
	public void testcaseO() throws UiObjectNotFoundException
	{
		getAutomationSupport().sendStatus(-1, buildSummary.buildSummary(TestSummary.calc_testcase24, 
											Calc_SPEC.SPEC_CASE24));
		String result;
		ts.launchapp("Calculator");
		cs.clearText();
		cs.basicInput("29+71=");
		result = cs.calcResultConvert(cs.findObjectByClassName("android.widget.EditText").getText());
		assertTrue("TestCase24 Failed!!", result.equals(Calc_SPEC.SPEC_CASE24));
		ts.log("TestCase24 Done!");	
	}
	
	public void testcaseP() throws UiObjectNotFoundException
	{
		getAutomationSupport().sendStatus(-1, buildSummary.buildSummary(TestSummary.calc_testcase25, 
											""));
		ts.launchapp("Calculator");
		cs.launchAdvancedPanel();
		assertTrue("TestCase5-f Failed!!", cs.findObjectByText("sin").exists()
			&& cs.findObjectByText("cos").exists() && cs.findObjectByText("tan").exists()
			&& cs.findObjectByText("ln").exists() && cs.findObjectByText("log").exists()
			&& cs.findObjectByText("!").exists() && cs.findObjectByResID("com.android.calculator2:id/pi").exists()
			&& cs.findObjectByText("e").exists() && cs.findObjectByText("^").exists()
			&& cs.findObjectByText("(").exists() && cs.findObjectByText(")").exists() 
			&& cs.findObjectByResID("com.android.calculator2:id/sqrt").exists());
		ts.goBack();
		ts.log("TestCase25 Done!");
	}
	
	public void testcaseQ() throws UiObjectNotFoundException
	{
		getAutomationSupport().sendStatus(-1, buildSummary.buildSummary(TestSummary.calc_testcase26, 
											""));
		String result_a, result_b, result_c;
		ts.launchapp("Calculator");
		cs.clearText();
		cs.advancedInput(new String[]{"sin","47="});
		result_a = cs.calcResultConvert(cs.findObjectByClassName("android.widget.EditText").getText());
		cs.clearText();
		cs.advancedInput(new String[]{"cos","93="});
		result_b = cs.calcResultConvert(cs.findObjectByClassName("android.widget.EditText").getText());
		cs.clearText();
		cs.advancedInput(new String[]{"tan","46="});
		result_c = cs.calcResultConvert(cs.findObjectByClassName("android.widget.EditText").getText());
		assertTrue("TestCase26 Failed!!", result_a.equals(Calc_SPEC.SPEC_CASE26_a)&& 
					result_b.equals(Calc_SPEC.SPEC_CASE26_b)&& result_c.equals(Calc_SPEC.SPEC_CASE26_c));
		ts.log("TestCase26 Done!");
	}
	
	public void testcaseR() throws UiObjectNotFoundException
	{
		getAutomationSupport().sendStatus(-1, buildSummary.buildSummary(TestSummary.calc_testcase27, 
											""));
		String result_a, result_b;
		ts.launchapp("Calculator");
		cs.clearText();
		cs.advancedInput(new String[]{"ln","47="});
		result_a = cs.calcResultConvert(cs.findObjectByClassName("android.widget.EditText").getText());
		cs.clearText();
		cs.advancedInput(new String[]{"log","93="});
		result_b = cs.calcResultConvert(cs.findObjectByClassName("android.widget.EditText").getText());
		assertTrue("TestCase27 Failed!!", result_a.equals(Calc_SPEC.SPEC_CASE27_a)&& result_b.equals(Calc_SPEC.SPEC_CASE27_b));
		ts.log("TestCase27 Done!");
	}
	
	public void testcaseS() throws UiObjectNotFoundException
	{
		getAutomationSupport().sendStatus(-1, buildSummary.buildSummary(TestSummary.calc_testcase28, 
											Calc_SPEC.SPEC_CASE28));
		String result;
		ts.launchapp("Calculator");
		cs.clearText();
		cs.advancedInput(new String[]{"47+93-46*37/","pi","="});
		result = cs.calcResultConvert(cs.findObjectByClassName("android.widget.EditText").getText());
		assertTrue("TestCase28 Failed!!", result.equals(Calc_SPEC.SPEC_CASE28));
		ts.log("TestCase28 Done!");	
	}
	
	public void testcaseT() throws UiObjectNotFoundException
	{
		getAutomationSupport().sendStatus(-1, buildSummary.buildSummary(TestSummary.calc_testcase29, 
											Calc_SPEC.SPEC_CASE29));
		String result;
		ts.launchapp("Calculator");
		cs.clearText();
		cs.advancedInput(new String[]{"47+","(","93-46",")","*","(","37/57",")","="});
		result = cs.calcResultConvert(cs.findObjectByClassName("android.widget.EditText").getText());
		assertTrue("TestCase29 Failed!!", result.equals(Calc_SPEC.SPEC_CASE29));
		ts.log("TestCase29 Done!");	
	}
	
	public void testcaseU() throws UiObjectNotFoundException
	{
		getAutomationSupport().sendStatus(-1, buildSummary.buildSummary(TestSummary.calc_testcase30, 
											Calc_SPEC.SPEC_CASE30));
		String result;
		ts.launchapp("Calculator");
		cs.clearText();
		cs.advancedInput(new String[]{"sin","47",")","+","cos","93",")","-","tan","46",")",
										"*","(","ln","37",")","/","pi","="});
		result = cs.calcResultConvert(cs.findObjectByClassName("android.widget.EditText").getText());
		assertTrue("TestCase30 Failed!!", result.equals(Calc_SPEC.SPEC_CASE30));
		ts.log("TestCase30 Done!");	
	}
	
	public void testcaseV() throws UiObjectNotFoundException
	{
		getAutomationSupport().sendStatus(-1, buildSummary.buildSummary(TestSummary.calc_testcase31, 
											Calc_SPEC.SPEC_CASE31));
		String result;
		ts.launchapp("Calculator");
		cs.clearText();
		cs.advancedInput(new String[]{"47","!","="});
		result = cs.calcResultConvert(cs.findObjectByClassName("android.widget.EditText").getText());
		assertTrue("TestCase31 Failed!!", result.equals(Calc_SPEC.SPEC_CASE31));
		ts.log("TestCase31 Done!");	
	}
	
	public void testcaseW() throws UiObjectNotFoundException
	{
		getAutomationSupport().sendStatus(-1, buildSummary.buildSummary(TestSummary.calc_testcase32, 
											Calc_SPEC.SPEC_CASE32));
		String result;
		ts.launchapp("Calculator");
		cs.clearText();
		cs.advancedInput(new String[]{"2","^","3","="});
		result = cs.calcResultConvert(cs.findObjectByClassName("android.widget.EditText").getText());
		assertTrue("TestCase32 Failed!!", result.equals(Calc_SPEC.SPEC_CASE32));
		ts.log("TestCase32 Done!");	
	}
	
	public void testcaseX() throws UiObjectNotFoundException
	{
		getAutomationSupport().sendStatus(-1, buildSummary.buildSummary(TestSummary.calc_testcase33, 
											Calc_SPEC.SPEC_CASE33));
		String result;
		ts.launchapp("Calculator");
		cs.clearText();
		cs.advancedInput(new String[]{"2","sqrt","3","="});
		result = cs.calcResultConvert(cs.findObjectByClassName("android.widget.EditText").getText());
		assertTrue("TestCase33 Failed!!", result.equals(Calc_SPEC.SPEC_CASE33));
		ts.log("TestCase33 Done!");	
	}
	
	public void testcaseY() throws UiObjectNotFoundException
	{
		getAutomationSupport().sendStatus(-1, buildSummary.buildSummary(TestSummary.calc_testcase34, 
											Calc_SPEC.SPEC_CASE34));
		String result;
		ts.launchapp("Calculator");
		cs.clearText();
		cs.basicInput("1234");
		cs.clearHistory();
		result = cs.findObjectByClassName("android.widget.EditText").getText();
		assertTrue("TestCase34 Failed!!", result.equals(Calc_SPEC.SPEC_CASE34));
		ts.log("TestCase34 Done!");	
	}
	
	public void testcaseZ() throws UiObjectNotFoundException
	{
		getAutomationSupport().sendStatus(-1, buildSummary.buildSummary(TestSummary.calc_testcase35, 
											""));
		String after_cut, after_paste;
		ts.launchapp("Calculator");
		cs.clearText();
		cs.basicInput("123456789");
		cs.launchContextMenu();
		cs.findObjectByText("Cut").click();
		after_cut = cs.findObjectByClassName("android.widget.EditText").getText();
		cs.launchContextMenu();
		cs.findObjectByText("Paste").click();
		after_paste = cs.findObjectByClassName("android.widget.EditText").getText();
		assertTrue("TestCase35 Failed!!", after_cut.equals(Calc_SPEC.SPEC_CASE35_a)
											&& after_paste.equals(Calc_SPEC.SPEC_CASE35_b));
		ts.log("TestCase35 Done!");
	}
	
	public void testcasea() throws UiObjectNotFoundException
	{
		getAutomationSupport().sendStatus(-1, buildSummary.buildSummary(TestSummary.calc_testcase36, 
											Calc_SPEC.SPEC_CASE36));
		String result;
		ts.launchapp("Calculator");
		cs.clearText();
		cs.advancedInput(new String[]{"sin"});
		cs.deleteSingleChar();
		cs.basicInput("=");
		result = cs.calcResultConvert(cs.findObjectByClassName("android.widget.EditText").getText());
		assertTrue("TestCase36 Failed!!", result.equals(Calc_SPEC.SPEC_CASE36));
		ts.log("TestCase36 Done!");
		ts.log("All test cases had been finished!!!");
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		ts.goHome();
		super.tearDown();
	}
}
