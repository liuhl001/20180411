package com.kuaishoudan.financer.test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.kuaishoudan.financer.bean.KSDCase;
import com.kuaishoudan.financer.dao.UserDaoImpl;
import com.kuaishoudan.financer.selenium.AppSPUtil;
import com.kuaishoudan.financer.selenium.AppUtil;
import com.kuaishoudan.financer.selenium.WebDisAgree;
import com.kuaishoudan.financer.selenium.WebSPUtil;
import com.kuaishoudan.financer.selenium.WebUtil;
import com.kuaishoudan.financer.util.CaseUtil;
import com.kuaishoudan.financer.util.RandomValue;

/**
 * 不同意
 * 
 * @author Administrator
 * 
 */
public class Test6 {

	public AppiumDriver<AndroidElement> driver = null;
	String devicename = "";
	public WebDriver webdriver = null;
	KSDCase ksd = null;

	@BeforeTest
	public void setUp() throws Exception {
		webdriver = WebUtil.getdriver();
		driver = AppUtil.getdriver();

		Process process = Runtime.getRuntime().exec("adb devices");
		process.waitFor();
		InputStreamReader isr = new InputStreamReader(process.getInputStream());
		BufferedReader br = new BufferedReader(isr);
		br.readLine();
		devicename = br.readLine().replaceAll("device", "").trim();
		System.out.println(devicename);
		Thread.sleep(3000);
 
		ksd = RandomValue.getRandom();
		System.out.println("名称" + ksd.getUsername() + "手机" + ksd.getPhone()
				+ "身份证号" + ksd.getIdentitynum() + "身份类型"
				+ ksd.getIdentitytype() + "军官" + ksd.getJgid() + "企业个人"
				+ ksd.getQygr() + "车类型" + ksd.getCartype() + "车品牌"
				+ ksd.getCarbrand() + "车系" + ksd.getCarseries() + "车价格"
				+ ksd.getCarprice() + "贷款价格" + ksd.getSqdk() + "融资期限"
				+ ksd.getHkqs() + "\n  " + ksd.getPurchase_tax() + "\n "
				+ ksd.getInsurance() + " \n" + ksd.getGps_charge() + "\n "
				+ ksd.getService_charge() + "," + ksd.getRegisttype() + ","
				+ ksd.getPledge());
	}

	@AfterTest
	public void tearDown() throws Exception {
		driver.quit();
		webdriver.quit();
	}
	
	/*// 创建用户
	@Test(priority = 1, invocationCount = 1, threadPoolSize = 1)
	public void test1() throws InterruptedException, IOException {
		System.out.println("***1@");

		boolean flag = AppUtil.createUser(driver, devicename, 1, ksd);
		Map<String, String> actual = UserDaoImpl.getCustomer(ksd);
		Map<String, String> expect = CaseUtil.getCustomer(ksd);
		Assert.assertEquals(flag, true);
		Assert.assertEquals(actual, expect);

	}

	// 个人进件或企业
	@Test(priority = 2, invocationCount = 1, threadPoolSize = 1)
	public void test2() throws InterruptedException, IOException {

		if (ksd.getQygr() == 1) {
			System.out.println("***2@");
			ksd = AppUtil.addGr(driver, webdriver, devicename, 1, ksd);
			String statue = AppSPUtil.getActstatue(driver);
			Assert.assertEquals(statue, "待分配");
			Assert.assertEquals(UserDaoImpl.getFinanstatue_id(ksd),
					UserDaoImpl.getstatus_id("待分配"));
		} else {
			System.out.println("***3@");
			ksd = AppUtil.addQy(driver, webdriver, devicename, 1, ksd);
			String statue = AppSPUtil.getActstatue(driver);
			Assert.assertEquals(statue, "待分配");
			Assert.assertEquals(UserDaoImpl.getFinanstatue_id(ksd),
					UserDaoImpl.getstatus_id("待分配"));

		}
	}

	// web审批待分配
	@Test(priority = 3, invocationCount = 1, threadPoolSize = 1)
	public void test3() throws InterruptedException, IOException {
		System.out.println("***4@");
		WebUtil.login(webdriver, ksd);// 登录
		WebUtil.testDFP(webdriver, ksd);// 待分配
		WebUtil.logout(webdriver);// 登出
		Assert.assertEquals(UserDaoImpl.getFinanstatue_id(ksd),
				UserDaoImpl.getstatus_id("已分配"));
	}

	// web审批已分配
	@Test(priority = 4, invocationCount = 1, threadPoolSize = 1)
	public void test4() throws InterruptedException, IOException {
		System.out.println("***5@");
		WebUtil.login(webdriver, ksd );// 登录
		WebUtil.testYFP(webdriver,ksd);// 已分配
		WebUtil.logout(webdriver);// 登出
		Assert.assertEquals(UserDaoImpl.getFinanstatue_id(ksd),
				UserDaoImpl.getstatus_id("已录入"));

	}

	// web审批已录入
	@Test(priority = 5, invocationCount = 1, threadPoolSize = 1)
	public void test5() throws InterruptedException, IOException {
		System.out.println("***5@");
		WebUtil.login(webdriver, ksd );// 登录
		WebUtil.testYLR(webdriver, ksd);// 已分配
		WebUtil.logout(webdriver);// 登出
		Map<String, String> actual = UserDaoImpl.getFinance(ksd);
		Map<String, String> expect = CaseUtil.getFinance(ksd);
		Assert.assertEquals(actual, expect);
	}

	// app不出合同---申请请款
	@Test(priority = 8, invocationCount = 1, threadPoolSize = 1)
	public void test8() {
		ksd = AppSPUtil.testBCSQQK(driver, webdriver, ksd, devicename);// 请款

		Assert.assertEquals(ksd.getStatue(), "已请款");
		Assert.assertEquals(UserDaoImpl.getFinanstatue_id(ksd),
				UserDaoImpl.getstatus_id("已请款"));

	}

	// 请款审批同意专员
	@Test(priority = 9, invocationCount = 1, threadPoolSize = 1)
	public void test9() {
		try {

			Map<String, String> map = AppSPUtil.getSPname(driver,ksd);// 从app获取审批人名字
			String itename = map.get("prename");
			System.out.println("************"+map.get("name"));
			String email = WebSPUtil.nameToemail(map.get("name"));
			WebDisAgree.testSP1(webdriver, email, ksd.getLoginname(), ksd); 
			AppUtil.goBack1(driver);
			 WebDisAgree .testDisAgreeQk(driver,ksd);
			 WebSPUtil.testSP1(webdriver, email, ksd.getLoginname(), ksd); // 请款审批同意专员
			Map<String, String> actual = UserDaoImpl.getAdvance(ksd);
			Map<String, String> expect = CaseUtil.getAdvance(ksd);
			Assert.assertEquals(actual, expect);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	//
	@Test(priority = 10, invocationCount = 1, threadPoolSize = 1)
	public void test10() {
		try {
			Map<String, String> map = AppSPUtil.getSPname(driver,ksd);// 从app获取审批人名字
			String itename = map.get("prename");
			String email = WebSPUtil.nameToemail(map.get("name"));

			WebDisAgree.testSP2(webdriver, email, itename); // 请款审批同意专员
			AppUtil.goBack1(driver);
			 WebDisAgree .testDisAgreeQk(driver,ksd);
			 
			Map<String, String> map1 = AppSPUtil.getSPname(driver,ksd);// 从app获取审批人名字
			 WebSPUtil.testSP1(webdriver, WebSPUtil.nameToemail(map1.get("name")), ksd.getLoginname(), ksd); // 请款审批同意专员
			Map<String, String> map2 = AppSPUtil.getSPname(driver,ksd);// 从app获取审批人名字
			 WebSPUtil.testSP2(webdriver, WebSPUtil.nameToemail(map2.get("name")), map2.get("prename")); // 请款审批同意专员
		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 333
		@Test(priority = 11, invocationCount = 1, threadPoolSize = 1)
	public void test11() {
		try {

			Map<String, String> map = AppSPUtil.getSPname(driver,ksd);// 从app获取审批人名字
			if (map.size() == 1) {
				// bd操作

				String email = WebSPUtil.nameToemail(map.get("name"));
				WebDisAgree.loginBD(driver, email);
				AppUtil.login(driver, devicename, ksd);// 登录
				 WebDisAgree .testDisAgreeQk(driver,ksd);
			
			//	
				Map<String, String> map1 = AppSPUtil.getSPname(driver,ksd);// 从app获取审批人名字
				 WebSPUtil.testSP1(webdriver, WebSPUtil.nameToemail(map1.get("name")), ksd.getLoginname(), ksd); // 请款审批同意专员
				Map<String, String> map2 = AppSPUtil.getSPname(driver,ksd);// 从app获取审批人名字
				 WebSPUtil.testSP2(webdriver, WebSPUtil.nameToemail(map2.get("name")), map2.get("prename")); // 请款审批同意专员
				 AppSPUtil.loginBD(driver, email);
				 AppUtil.login(driver, devicename, ksd);// 登录
				Thread.sleep(1000);
				Map<String, String> map3 = AppSPUtil.getSPname(driver,ksd);
				String itename3 = map3.get("prename");
				String email3 = WebSPUtil.nameToemail(map3.get("name"));
				WebDisAgree.testSP3(webdriver, email3, itename3); // 请款审批同意专员
				 AppUtil.goBack1(driver);
				WebDisAgree .testDisAgreeQk(driver,ksd);
		
				Map<String, String> map11 = AppSPUtil.getSPname(driver,ksd);// 从app获取审批人名字
				 WebSPUtil.testSP1(webdriver, WebSPUtil.nameToemail(map11.get("name")), ksd.getLoginname(), ksd); // 请款审批同意专员
				Map<String, String> map12 = AppSPUtil.getSPname(driver,ksd);// 从app获取审批人名字
				 WebSPUtil.testSP2(webdriver, WebSPUtil.nameToemail(map12.get("name")), map12.get("prename")); // 请款审批同意专员
					Map<String, String> map121 = AppSPUtil.getSPname(driver,ksd);// 从app获取审批人名字
					AppSPUtil.loginBD(driver, WebSPUtil.nameToemail(map121.get("name")));
					AppUtil.login(driver, devicename, ksd);// 登录
					Thread.sleep(1000);
				 Map<String, String> map13 = AppSPUtil.getSPname(driver,ksd);// 从app获取审批人名字
				WebSPUtil.testSP3(webdriver, WebSPUtil.nameToemail(map13.get("name")),  map13.get("prename")); // 请款审批同意专员
			} else {
				String itename = map.get("prename");
				String email = WebSPUtil.nameToemail(map.get("name"));
				WebDisAgree.testSP3(webdriver, email, itename); // 请款审批同意专员
				 AppUtil.goBack1(driver);
				WebDisAgree .testDisAgreeQk(driver,ksd);
		
				Map<String, String> map1 = AppSPUtil.getSPname(driver,ksd);// 从app获取审批人名字
				 WebSPUtil.testSP1(webdriver, WebSPUtil.nameToemail(map1.get("name")), ksd.getLoginname(), ksd); // 请款审批同意专员
				Map<String, String> map2 = AppSPUtil.getSPname(driver,ksd);// 从app获取审批人名字
				 WebSPUtil.testSP2(webdriver, WebSPUtil.nameToemail(map2.get("name")), map2.get("prename")); // 请款审批同意专员
				Map<String, String> map3 = AppSPUtil.getSPname(driver,ksd);// 从app获取审批人名字
				WebSPUtil.testSP3(webdriver, WebSPUtil.nameToemail(map3.get("name")),  map3.get("prename")); // 请款审批同意专员
			
			
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

		@Test(priority = 12, invocationCount = 1, threadPoolSize = 1)
	public void test12() {
		try {
//ksd.setSssh("北郊商户1");
			Map<String, String> map = AppSPUtil.getSPname(driver,ksd);// 从app获取审批人名字
			String itename = map.get("prename");
			String email = WebSPUtil.nameToemail(map.get("name"));
			WebDisAgree.testSP4(webdriver, email, itename,ksd); // 请款审批同意专员
			AppUtil.goBack1(driver);//
			WebDisAgree .testDisAgreeQk(driver,ksd);
			Map<String, String> map1 = AppSPUtil.getSPname(driver,ksd);// 从app获取审批人名字
			WebSPUtil.testSP1(webdriver, WebSPUtil.nameToemail(map1.get("name")), ksd.getLoginname(), ksd); // 请款审批同意专员
			 Map<String, String> map2 = AppSPUtil.getSPname(driver,ksd);// 从app获取审批人名字
			 WebSPUtil.testSP2(webdriver, WebSPUtil.nameToemail(map2.get("name")), map2.get("prename")); // 请款审批同意专员
			 Map<String, String> map3 = AppSPUtil.getSPname(driver,ksd);// 从app获取审批人名字
			if (map3.size() == 1) {
				// bd操作

				String email3 = WebSPUtil.nameToemail(map3.get("name"));
				AppSPUtil.loginBD(driver, email3);
				AppUtil.login(driver, devicename, ksd);// 登录
				Thread.sleep(1000);
				Map<String, String> map31 = AppSPUtil.getSPname(driver,ksd);// 从app获取审批人名字
				String itename31 = map31.get("prename");
				String email31= WebSPUtil.nameToemail(map31.get("name"));
				WebSPUtil.testSP3(webdriver, email31, itename31); // 请款审批同意专员
			} else {
				
				String itename3 = map3.get("prename");
				String email3 = WebSPUtil.nameToemail(map3.get("name"));
				WebSPUtil.testSP3(webdriver, email3, itename3); // 请款审批同意专员
			}
			
			
			Map<String, String> map4= AppSPUtil.getSPname(driver,ksd);// 从app获取审批人名字
			WebSPUtil.testSP4(webdriver, WebSPUtil.nameToemail(map4.get("name")),  map4.get("prename"),ksd); // 请款审批同意专员
		
			
				String statue = AppSPUtil.getActstatue(driver);
			Assert.assertEquals(statue, "已放款");
			Assert.assertEquals(UserDaoImpl.getFinanstatue_id(ksd),
					UserDaoImpl.getstatus_id("已放款"));

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 状态已放款
		@Test(priority = 13, invocationCount = 1, threadPoolSize = 1)
	public void test13() {
		try {

			Map<String, String> map = AppSPUtil.getSPname(driver,ksd);// 从app获取审批人名字
			String itename = map.get("prename");
			String email = WebSPUtil.nameToemail(map.get("name"));
			WebSPUtil.testSP5(webdriver, email, itename, ksd); // 请款审批同意专员
			AppUtil.goBack1(driver);//
			String statue = AppSPUtil.getActstatue(driver);
			Assert.assertEquals(statue, "已回款");
			Assert.assertEquals(UserDaoImpl.getFinanstatue_id(ksd),
					UserDaoImpl.getstatus_id("已回款"));

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 归档--通知下发材料
	//@Test(priority = 14, invocationCount = 1, threadPoolSize = 1)
	public void test14() {

		ksd = WebSPUtil.testSP6(webdriver, ksd); // 请款审批同意专员
		AppSPUtil.sp6App(driver, ksd);
		List<Integer> lisss = ksd.getImgtypes();
		for (int i = 0; i < lisss.size(); i++) {
			System.out.println("##" + lisss.get(i));
		}

		List<Integer> actual=UserDaoImpl.getLoanname(ksd);
		 Collections.sort(actual);  
		Assert.assertEquals(actual, lisss);
	}*/

	// 归档
	@Test(priority = 15, invocationCount = 1, threadPoolSize = 1)
	public void test15() {
		ksd.setImgcount(4);
		ksd = WebDisAgree.testSP7(webdriver, ksd); // 请款审批同意专员
		AppSPUtil.sp6App(driver, ksd);
		ksd = WebSPUtil.testSP7(webdriver, ksd); // 请款审批同意专员

		String statue = AppSPUtil.getActstatue(driver);
		Assert.assertEquals(statue, "已归档");
		Assert.assertEquals(UserDaoImpl.getFinanstatue_id(ksd),
				UserDaoImpl.getstatus_id("已归档"));

	}

}
