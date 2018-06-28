package com.kuaishoudan.financer.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.kuaishoudan.financer.bean.KSDCase;
import com.kuaishoudan.financer.selenium.AppSPUtil;
import com.kuaishoudan.financer.selenium.AppUtil;
import com.kuaishoudan.financer.selenium.WebSPUtil;
import com.kuaishoudan.financer.selenium.WebUtil;
import com.kuaishoudan.financer.selenium.ZcjjUtil;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;

public class TestZcjjSqht {
	public AppiumDriver<AndroidElement> driver;
	String devicename = "";
	public WebDriver webdriver;
	KSDCase ksd = null;

	/**
	 * 再次进件-申请合同
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		TestZcjjSqht ct = new TestZcjjSqht();

		System.out.println("***@");
		ct.setUp2();// web启动
		ct.setUp();// app启动

		ct.dfp();// 待分配app

		ct.webDksp();// 已录入到申请合同

		ct.appSqht();// App申请合同

		ct.webSpht();// web审核

		ct.appSqqk();// app请款

		ct.sp1();
		ct.sp2();
		ct.sp3();
		ct.sp4();
		ct.sp5();
		ct.sp6();
		ct.sp7();
		ct.tearDown();
		// ct.back()

	}

	public void back() {
		AppUtil.goBack1(driver);
	}

	public void setUp() throws IOException, InterruptedException {
		driver = AppUtil.getdriver();



	}

	// web
	public void setUp2() throws IOException, InterruptedException {
		webdriver = WebUtil.getdriver();

	}

	public void loginWeb(String username) {
		// String username="liuhl@jizhicar.com";

		WebUtil.login(webdriver, ksd);// 登录
	}

	public void logoutWeb() {
		// String username="liuhl@jizhicar.com";

		WebUtil.logout(webdriver);// 登出
	}

	// App不申请合同
	public void appBsqht() {

		ZcjjUtil.testBCSQQK(driver, webdriver, ksd, devicename);

	}

	/**
	 * 创建用户，进件，待审批
	 */
	public void dfp() {
		// AppUtil.addTest(driver, devicename,1);
		ksd = AppUtil.addZjjtest(driver, webdriver, devicename, 1);
	}

	/**
	 * web
	 */
	public void webDksp() {
		WebUtil.login(webdriver, ksd);// 登录
		WebUtil.testDFP(webdriver, ksd);// 待分配
		WebUtil.testYFP(webdriver, ksd);// 已分配
		WebUtil.testYLR(webdriver, ksd);// 已录入
		WebUtil.logout(webdriver);// 登出

	}

	// App申请合同
	public void appSqht() {

		ksd = ZcjjUtil.sqhtZCJJ(driver, ksd);

	}

	// web审批合同
	public void webSpht() {
		WebUtil.login(webdriver, ksd);// 登录
		WebUtil.testYSQHT(webdriver, ksd);// 申请合同审批
		WebUtil.logout(webdriver);// 登出

	}

	// 申请请款
	public void appSqqk() {
		ZcjjUtil.zcjjHTSQQK(driver, webdriver, ksd, devicename);

	}

	// 请款审批同意专员
	public void sp1() {
		try {

			Map<String, String> map = ZcjjUtil.getSPname(driver, ksd);// 从app获取审批人名字
			String itename = map.get("prename");
			String email = WebSPUtil.nameToemail(map.get("name"));
			WebSPUtil.testSP1(webdriver, email, itename, ksd); // 请款审批同意专员
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	//
	public void sp2() {
		try {

			Map<String, String> map = ZcjjUtil.getSPname(driver, ksd);// 从app获取审批人名字
			String itename = map.get("prename");
			String email = WebSPUtil.nameToemail(map.get("name"));
			WebSPUtil.testSP2(webdriver, email, itename); // 请款审批同意专员

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 333
	public void sp3() {
		try {

			Map<String, String> map = ZcjjUtil.getSPname(driver, ksd);// 从app获取审批人名字
			if (map.size() == 1) {
				// bd操作
				String email = WebSPUtil.nameToemail(map.get("name"));
				ZcjjUtil.loginBD(driver, email);
				AppUtil.login(driver, devicename, ksd);// 登录
				Thread.sleep(1000);
				Map<String, String> map2 = ZcjjUtil.getSPname(driver, ksd);// 从app获取审批人名字
				String itename2 = map2.get("prename");
				String email2 = WebSPUtil.nameToemail(map2.get("name"));
				WebSPUtil.testSP3(webdriver, email2, itename2); // 请款审批同意专员
			} else {

				String itename = map.get("prename");
				String email = WebSPUtil.nameToemail(map.get("name"));
				WebSPUtil.testSP3(webdriver, email, itename); // 请款审批同意专员
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sp4() {
		try {

			Map<String, String> map = ZcjjUtil.getSPname(driver, ksd);// 从app获取审批人名字
			String itename = map.get("prename");
			String email = WebSPUtil.nameToemail(map.get("name"));
			WebSPUtil.testSP4(webdriver, email, itename, ksd); // 请款审批同意专员
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 状态已放款
	public void sp5() {
		try {

			Map<String, String> map = ZcjjUtil.getSPname(driver, ksd);// 从app获取审批人名字
			String itename = map.get("prename");
			String email = WebSPUtil.nameToemail(map.get("name"));
			WebSPUtil.testSP5(webdriver, email, itename, ksd); // 请款审批同意专员
			AppUtil.goBack1(driver);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 归档--通知下发材料

	public void sp6() {

		ksd = WebSPUtil.testSP6(webdriver, ksd); // 请款审批同意专员
		ZcjjUtil.sp6App(driver, ksd);

	}

	// 归档

	public void sp7() {

		ksd = WebSPUtil.testSP7(webdriver, ksd); // 请款审批同意专员

	}

	public void tearDown() throws Exception {

		driver.quit();
		webdriver.quit();
	}

}
