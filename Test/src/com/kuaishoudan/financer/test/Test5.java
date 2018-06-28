package com.kuaishoudan.financer.test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.kuaishoudan.financer.bean.KSDCase;
import com.kuaishoudan.financer.dao.UserDaoImpl;
import com.kuaishoudan.financer.selenium.AppUtil;
import com.kuaishoudan.financer.selenium.WebUtil;
import com.kuaishoudan.financer.selenium.ZcjjUtil;
import com.kuaishoudan.financer.util.RandomValue;
public class Test5 {

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
 
		ksd = RandomValue.getRandom(driver);
		System.out.println("名称" + ksd.getUsername() + "手机" + ksd.getPhone()
				+ "身份证号" + ksd.getIdentitynum() + "身份类型"
				+ ksd.getIdentitytype() + "军官" + ksd.getJgid() + "企业个人"
				+ ksd.getQygr() + "车类型" + ksd.getCartype() + "车品牌"
				+ ksd.getCarbrand() + "车系" + ksd.getCarseries() + "车价格"
				+ ksd.getCarprice() + "贷款价格" + ksd.getSqdk() + "融资期限"
				+ ksd.getHkqs() + "\n  " + ksd.getPurchase_tax() + "\n "
				+ ksd.getInsurance() + " \n" + ksd.getGps_charge() + "\n "
				+ ksd.getService_charge() + "," + ksd.getRegisttype() + ","
				+ ksd.getPledge()
				+"车架号"+ksd.getVin());
	}
	
	@AfterTest
	public void tearDown() throws Exception {
		driver.quit();
		webdriver.quit();
	} 
	
	
	
	// 客户-->客户详情-->返回
	//客户未上传-->编辑订单--返回
	@Test(priority = 2, invocationCount = 1, threadPoolSize = 1)
	public void test2() throws InterruptedException, IOException {
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_name"))
		.get(0).click();// 首页列表
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    	driver.findElement(
				By.id("com.kuaishoudan.financer:id/toolbar_back"))
				.click(); 
	}

	//客户-->客户详情-->编辑客户-->返回  
	//客户未上传的状态-->编辑客户-->点击确认-->查看状态-->返回
	//客户待分配状态-->贷款详情-->点击右边的imageVIew-->编辑客户-->返回
	@Test(priority = 3, invocationCount = 1, threadPoolSize = 1)
	public void test3() throws InterruptedException, IOException {
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_name"))
		.get(0).click();// 首页列表
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        String title=driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_title")).getText();
        if(title.equals("贷款详情")){
        	driver.findElement(
    				By.id("com.kuaishoudan.financer:id/toolbar_loan_status"))
    				.click();//客户详情右边的imageView 	
        	driver.findElement(
    				By.id("com.kuaishoudan.financer:id/text_customer_edit"))
    				.click();//编辑客户	
        	driver.findElement(
    				By.id("com.kuaishoudan.financer:id/toolbar_back"))
    				.click();//返回
        	driver.findElement(
    				By.id("	com.kuaishoudan.financer:id/dialog_custom_confirm"))
    				.click();//提醒 是否放弃编辑客户，点"是"
        	 Assert.assertEquals(title, "贷款详情");
        	
        }else if (title.equals("编辑订单")) {//未上传的状态
        	driver.findElement(
    				By.id("com.kuaishoudan.financer:id/toolbar_confirm"))
    				.click();//编辑详情的确认	
        	driver.findElement(
    				By.id("com.kuaishoudan.financer:id/dialog_custom_confirm"))
    				.click();//客户详情右边的imageView 
        	driver.findElement(
    				By.id("com.kuaishoudan.financer:id/toolbar_back"))
    				.click();//返回
        	 Assert.assertEquals(title, "编辑订单");
		}else if(title.contains("客户")){
			driver.findElement(
    				By.id("com.kuaishoudan.financer:id/toolbar_menu"))
    				.click(); //客户详情右边的imageView
			
			driver.findElement(
    				By.id("com.kuaishoudan.financer:id/text_customer_edit"))
    				.click();//编辑客户
			driver.findElement(
    				By.id("com.kuaishoudan.financer:id/toolbar_back"))
    				.click();//返回
			  String title1=driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_title")).getText();
        	 Assert.assertEquals(title, title1);
		}
    	 	
	}
	//贷款详情-->查看进度
	@Test(priority = 4, invocationCount = 1, threadPoolSize = 1)
	public void test4() throws InterruptedException, IOException {
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_name"))
		.get(0).click();// 首页列表
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        String title=driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_title")).getText();
        if(title.equals("贷款详情")){
        	driver.findElement(
    				By.id("com.kuaishoudan.financer:id/toolbar_loan_status"))
    				.click();//客户详情右边的imageView 	
        	driver.findElement(
    				By.id("com.kuaishoudan.financer:id/text_customer_look_status"))
    				.click();//查看进度	
        	driver.findElement(
    				By.id("com.kuaishoudan.financer:id/toolbar_back"))
    				.click();//返回
        	 Assert.assertEquals(title, "贷款详情");       	      	     	
        }	 	
	}
	
	//贷款详情-->再次进件
	@Test(priority = 5, invocationCount = 1, threadPoolSize = 1)
	public void test5() throws InterruptedException, IOException {
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_name"))
		.get(0).click();// 首页列表
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        String title=driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_title")).getText();
        if(title.equals("贷款详情")){
        	driver.findElement(
    				By.id("com.kuaishoudan.financer:id/toolbar_loan_status"))
    				.click();//客户详情右边的imageView 	
        	driver.findElement(
    				By.id("com.kuaishoudan.financer:id/text_customer_algin_jinjian"))
    				.click();//再次进件
        	driver.findElement(
    				By.id("com.kuaishoudan.financer:id/toolbar_back"))
    				.click();//返回
        	 Assert.assertEquals(title, "贷款详情");       	      	     	
        }	 	
	}
	
	//贷款详情-->取消
	@Test(priority = 6, invocationCount = 1, threadPoolSize = 1)
	public void test6() throws InterruptedException, IOException {
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_name"))
		.get(0).click();// 首页列表
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        String title=driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_title")).getText();
        if(title.equals("贷款详情")){
        	driver.findElement(
    				By.id("com.kuaishoudan.financer:id/toolbar_loan_status"))
    				.click();//客户详情右边的imageView 	
        	driver.findElement(
    				By.id("com.kuaishoudan.financer:id/text_cancel"))
    				.click();//取消
        	driver.findElement(
    				By.id("com.kuaishoudan.financer:id/toolbar_back"))
    				.click();//返回
        	 Assert.assertEquals(title, "贷款详情");       	      	     	
        }	 	
	}
	
	//客户首页-->客户详情--进件-->进件页面-->返回
	@Test(priority = 7, invocationCount = 1, threadPoolSize = 1)
	public void test7() throws InterruptedException, IOException {
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_name"))
		.get(0).click();// 首页列表
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        String title=driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_title")).getText();
       if(title.contains("客户")){
			driver.findElement(
    				By.id("com.kuaishoudan.financer:id/btn_add_loan"))
    				.click(); //进件
			
			driver.findElement(
    				By.id("com.kuaishoudan.financer:id/toolbar_back"))
    				.click();//返回
			  String title1=driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_title")).getText();
        	 Assert.assertEquals(title, title1);
		} 	
	}
	
	
	//客户首页-->新建客户-->返回
	@Test(priority = 8, invocationCount = 1, threadPoolSize = 1)
	public void test8() throws InterruptedException, IOException {
		driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_custom_img"))
		.click();// 添加
		driver.findElement(By.id("com.kuaishoudan.financer:id/menu_manual_input"))
		.click();// 手动输入
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			driver.findElement(
    				By.id("com.kuaishoudan.financer:id/toolbar_back"))
    				.click();//返回
			driver.findElement(
    				By.id("com.kuaishoudan.financer:id/dialog_custom_confirm"))
    				.click();//提醒 放弃新建客户
			  String title1=driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_title")).getText();
        	 Assert.assertEquals(title1, "客户");
	}
	
	//新建客户成功--->选件类型-->返回
	@Test(priority = 9, invocationCount = 1, threadPoolSize = 1)
	public void test9() throws InterruptedException, IOException {
		driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_custom_img"))
		.click();// 添加
		driver.findElement(By.id("com.kuaishoudan.financer:id/menu_manual_input"))
		.click();// 手动输入
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        AppUtil.createUser(driver, devicename, 1, ksd);
        Thread.sleep(2500);
			driver.findElement(
    				By.id("com.kuaishoudan.financer:id/toolbar_back"))
    				.click();//返回
	    String title1=driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_title")).getText();
        Assert.assertEquals(title1, "客户");
	}
	
	//新建客户成功-->个人或者企业进件完成--->查看状态-->点击返回-->回到客户首页
	@Test(priority = 10, invocationCount = 1, threadPoolSize = 1)
	public void test10() throws InterruptedException, IOException {
		driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_custom_img"))
		.click();// 添加
		driver.findElement(By.id("com.kuaishoudan.financer:id/menu_manual_input"))
		.click();// 手动输入
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        AppUtil.addTest(driver,webdriver, devicename, 1);
			driver.findElement(
    				By.id("com.kuaishoudan.financer:id/toolbar_back"))
    				.click();//返回
	    String title1=driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_title")).getText();
        Assert.assertEquals(title1, "客户");
	}
	
	//客户列表-->进件---->查看状态-->点击返回-->回到客户列表  (企业贷款)
	@Test(priority = 11, invocationCount = 1, threadPoolSize = 1)
	public void test11() throws InterruptedException, IOException {
		
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_name"))
		.get(0).click();// 首页列表
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        String title=driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_title")).getText();
       if(title.contains("客户")){
			driver.findElement(
    				By.id("com.kuaishoudan.financer:id/btn_add_loan"))
    				.click(); //进件
			 AppUtil.addQy(driver,webdriver, devicename, 1, ksd);
			driver.findElement(
    				By.id("com.kuaishoudan.financer:id/toolbar_back"))
    				.click();//返回
			  String title1=driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_title")).getText();
        	 Assert.assertEquals(title, title1);
		} 	
           
		} 


	//客户列表-->客户详情-->进件---->查看状态-->点击返回-->回到客户列表  (个人贷款)
		@Test(priority = 12, invocationCount = 1, threadPoolSize = 1)
		public void test12() throws InterruptedException, IOException {
			
			driver.findElements(By.id("com.kuaishoudan.financer:id/text_name"))
			.get(0).click();// 首页列表
	        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	        String title=driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_title")).getText();
	       if(title.contains("客户")){
				driver.findElement(
	    				By.id("com.kuaishoudan.financer:id/btn_add_loan"))
	    				.click(); //进件
				 AppUtil.addGr(driver, webdriver,devicename, 1, ksd);
				driver.findElement(
	    				By.id("com.kuaishoudan.financer:id/toolbar_back"))
	    				.click();//返回
				  String title1=driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_title")).getText();
	        	 Assert.assertEquals(title, title1);
			} 	
	           
		} 
}
