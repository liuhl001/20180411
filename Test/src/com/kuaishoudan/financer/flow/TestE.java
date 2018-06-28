package com.kuaishoudan.financer.flow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.kuaishoudan.financer.bean.KSDCase;
import com.kuaishoudan.financer.dao.UserDaoImpl;
import com.kuaishoudan.financer.selenium.AppSPUtil;
import com.kuaishoudan.financer.selenium.AppUtil;
import com.kuaishoudan.financer.selenium.WebOrgan;
import com.kuaishoudan.financer.selenium.WebSPUtil;
import com.kuaishoudan.financer.selenium.WebUtil;
import com.kuaishoudan.financer.util.RandomValue;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;

public class TestE {
	public AppiumDriver<AndroidElement> driver;
	String devicename = "";
	public WebDriver webdriver;
	static KSDCase ksd = null;

	/**
	 * 不出合同-审批流
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		TestE ct = new TestE();

		System.out.println("***@");
		ct.setUp2();// web启动
		ct.setUp();// app启动
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
		for (int i = 0; i < 1; i++) {
		//	ct.dfp();// 待分配app

			/*		ct.webDksp();// 已录

			
			ct.appBsqht();// App不申请合同-申请请款
*/
//			ct.sp1();
			ct.sp2();
//			ct.sp3();
//			ct.sp4();
//			ct.sp5();
//			ct.sp6();
//			ct.sp7();

		}

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
	

		WebUtil.login(webdriver, ksd);// 登录
	}

	public void logoutWeb() {
 

		WebUtil.logout(webdriver);// 登出
	}

	/**
	 * 创建用户，进件，待审批
	 */
	public void dfp() {
		ksd = AppUtil.addTest(driver, webdriver, devicename, 1);

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

	// App不申请合同
	public void appBsqht() {

		ksd = testBCSQQK(driver, webdriver, ksd, devicename);

	}



	// 申请请款
	public void appSqqk() {
		AppSPUtil.testHTSQQK(driver, webdriver, ksd, devicename);// 请款
		// System.out.println(AppUtil.getStatue(driver));
		// AppUtil.look_status(driver);//查看进度

	}

	// 请款审批同意专员
	public void sp1() {
		try {

			Map<String, String> map = AppSPUtil.getSPname(driver, ksd);// 从app获取审批人名字
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
			Map<String, String> map = AppSPUtil.getSPname(driver, ksd);// 从app获取审批人名字
			String itename = map.get("prename");
			String email = WebSPUtil.nameToemail(map.get("name"));
			 testSP2(webdriver, email, itename,ksd); // 请款审批同意专员

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

			Map<String, String> map = AppSPUtil.getSPname(driver, ksd);// 从app获取审批人名字

			if (map.size() == 1) {
				// bd操作

				String email = WebSPUtil.nameToemail(map.get("name"));
				AppSPUtil.loginBD(driver, email);
				AppUtil.login(driver, devicename, ksd);// 登录

				Thread.sleep(1000);
				Map<String, String> map2 = AppSPUtil.getSPname(driver, ksd);// 从app获取审批人名字
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

			Map<String, String> map = AppSPUtil.getSPname(driver, ksd);// 从app获取审批人名字
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

			Map<String, String> map = AppSPUtil.getSPname(driver, ksd);// 从app获取审批人名字
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
		AppSPUtil.sp6App(driver, ksd);

	}

	// 归档

	public void sp7() {

		ksd = WebSPUtil.testSP7(webdriver, ksd); // 请款审批同意专员

	}

	public void tearDown() throws Exception {

		driver.quit();
		webdriver.quit();
	}
	
	// 不出合同申请请款
	public static KSDCase testBCSQQK(AppiumDriver<AndroidElement> driver,WebDriver webdriver,
			KSDCase ksd,String devicename) {
		driver.manage().timeouts().implicitlyWait(115, TimeUnit.SECONDS);
		WebUtil.login(webdriver, ksd );// 登录
		List<Integer> list = WebOrgan.getImge2(webdriver, ksd);
		WebUtil.logout(webdriver);
		List<Integer> list2=ksd.getImgtypes(); 
		int aa=0,countImg=0;		 
		 Collections.sort(list);  
	/*	for(int i=0;i<list.size();i++){
			if(list.get(i)<9){
			List<Integer> 	 list3=UserDaoImpl.getImgType(list.get(i)+7,list);
				for(int p=0;p<list3.size();p++){
					System.out.println("###"+list3.get(p));
				}
				list2.addAll(list3);
				aa=list3.size();
				countImg=aa+countImg;
			}
			
			if(countImg==0){
			for(Integer type:list){
				if(type>99){
					list2.add(type);break;
				}
			}
		}
		}*/
		
		
		
		for(int i=0;i<list.size();i++){
			if(list.get(i)<99){
				list.remove(i);
				i--;
			}
		}
	/*	for(int p=0;p<list.size();p++){
			System.out.println("###"+list.get(p));
		}*/
		ksd.setImgcount(list.size());
	
		list2.addAll(list);		
		ksd.setImgtypes(list);

		countImg=ksd.getImgtypes().size();
		System.out.println("$$$@"+countImg);
		ksd.setImgcount(countImg);
		driver.manage().timeouts().implicitlyWait(145, TimeUnit.SECONDS);
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_name"))
				.get(0).click();// 首页列表
		driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);

		driver.findElement(
				By.id("com.kuaishoudan.financer:id/tv_not_apply_compact"))
				.click();// 不出合同
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		int gxs = driver.findElements(
				By.id("com.kuaishoudan.financer:id/check_group")).size();// 勾选数
		System.out.println("gxs" + gxs);
		driver.findElements(By.id("com.kuaishoudan.financer:id/check_group"))
				.get(gxs - 1).click();// 不安装 选择GPS安装方式
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_submit"))
				.click();// 提交
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/dialog_custom_confirm"))
				.click();// 是按钮

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		/*
		 * driver.findElement(
		 * By.id("com.kuaishoudan.financer:id/btn_apply_request")).click();//
		 * 申请请款445整数进位
		 */
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/text_request_pay_name"))
				.click();// dian账号名
		driver.findElement(By.id("com.kuaishoudan.financer:id/iv_is_show"))
				.click();// xia下标
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/et_chekuan_chejia"))
				.sendKeys(ksd.getVin());// 车架号
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/ll_chekuan_shangpaidiya"))
				.click();// 上牌抵押地
		driver.findElement(By.id("com.kuaishoudan.financer:id/options3"))
				.click();// 城市
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;
		driver.swipe(width * 2 / 3, height - 80, width * 2 / 3, height - 280,
				800);
		driver.findElement(By.id("com.kuaishoudan.financer:id/btnSubmit"))
				.click();// 城市确定
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/tv_chekuan_shangpaifang"))
				.click();// 上牌方
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_select"))
				.get(ksd.getRegisttype() - 1).click();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/tv_chekuan_diyafang"))
				.click();// 抵押方
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_select"))
				.get(ksd.getPledge() - 1).click();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	
		try {
			driver.findElement(By.id("com.kuaishoudan.financer:id/tv_chekuan_kouchuxiang")).sendKeys(""+ksd.getDeduction());//扣除款项
			Thread.sleep(500);
			AppUtil.swipeToUp(driver, 1000);// 向上滑动
			Thread.sleep(500);
		} catch ( InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	

	 
		AppUtil.uploadQk(driver,ksd.getImgcount());
	
		//RequestPayout requestPyout = ksd.getRequestpayout();
		/*try {
	
		//	AppUtil.testFd(driver, devicename,requestPyout);
		//	AppUtil.testDy(driver,devicename, requestPyout);
		//	AppUtil.testZx(driver,devicename, requestPyout);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
		//	e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
		//	e1.printStackTrace();
		}*/
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/tv_toolbar_confirm"))
				.click();// 确定
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		//
		driver.findElement(By.id("com.kuaishoudan.financer:id/tv_confirm"))
				.click();// 申请请款确定

		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("com.kuaishoudan.financer:id/tv_countdown"))
				.click();// 倒计时确认

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_back"))
				.click();// 返回
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_back"))
				.click();// 返回
		String actualstatue = AppSPUtil.getActstatue(driver);
		ksd.setStatue(actualstatue);
		return ksd;
	}
	
	// 请款审批同意数据运营

		public static boolean testSP2(WebDriver driver, String email, String itename,KSDCase ksd) {
		 
			boolean flag = false;
			WebSPUtil.login2(driver, email, "@123456");

			//WebSPUtil.clickItem(driver, itename);
			/*try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/

			driver.manage().timeouts().implicitlyWait(23, TimeUnit.SECONDS);		
			driver.findElement(By.linkText("客户")).click();
			driver.manage().timeouts().implicitlyWait(23, TimeUnit.SECONDS);
			driver.findElement(By.linkText("审批管理")).click();
			driver.manage().timeouts().implicitlyWait(23, TimeUnit.SECONDS);
			WebSPUtil.clickItemorder(driver, ksd.getLoginname());
			driver.manage().timeouts().implicitlyWait(23, TimeUnit.SECONDS);
	  
				driver.findElement(By.linkText("确认提交")).click();// 确认提交
				driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
				driver.findElement(By.id("risk_type3")).click();
				driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
				driver.findElement(By.id("is_allReturn")).click();//全部
				driver.manage().timeouts().implicitlyWait(33, TimeUnit.SECONDS);//xuan
				
				Select userSelect = new Select(
						driver.findElement(By.id("is_allReturn")));
				userSelect.selectByVisibleText("全部到我司");
				
				driver.manage().timeouts().implicitlyWait(33, TimeUnit.SECONDS);
				driver.findElement(By.name("remark")).sendKeys("同意");
				driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
				driver.findElement(By.xpath("//div[@class='cashed_mark']/div/a")).click();//确认
		 
			flag = true;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			WebUtil.logout(driver);
			return flag;
		}
}
