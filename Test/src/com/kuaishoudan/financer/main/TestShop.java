package com.kuaishoudan.financer.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;

import org.openqa.selenium.WebDriver;

import com.kuaishoudan.financer.bean.KSDCase;
import com.kuaishoudan.financer.bean.ShopBeanCase;
import com.kuaishoudan.financer.selenium.AppSPUtil;
import com.kuaishoudan.financer.selenium.AppShopUtil;
import com.kuaishoudan.financer.selenium.AppUtil;
import com.kuaishoudan.financer.util.RandomValue;

public class TestShop {

	/**
	 * @param args
	 */
	
	public AppiumDriver<AndroidElement> driver;
	String devicename = "";

//	KSDCase ksd = null;
	ShopBeanCase  shopBeanCase=new ShopBeanCase();
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
       
		TestShop ct = new TestShop();
		ct.setUp();// app启动
//		ct.setUp2();// web启动
		
		for (int i = 0; i < 1; i++) {

		   ct.appCreateShop();
		   ct.goback();
		}
		/*
		 * ct.sp4(); ct.sp5(); ct.sp6();
		 */
		ct.tearDown();

	}
	
	public void setUp() throws IOException, InterruptedException {
		driver = AppUtil.getdriver();
		Process process = Runtime.getRuntime().exec("adb devices");
		process.waitFor();
		InputStreamReader isr = new InputStreamReader(process.getInputStream());
		BufferedReader br = new BufferedReader(isr);
		br.readLine();
		devicename = br.readLine().replaceAll("device", "").trim();
		System.out.println(devicename);
		Thread.sleep(5000);
	}
	
	public void tearDown() throws Exception {
		driver.quit();
		//webdriver.quit();
	}
	
	//创建商户，备案信息
	public void appCreateShop(){
		try {
		    shopBeanCase = RandomValue.getShop();
			shopBeanCase = AppShopUtil.createShop(driver, shopBeanCase,devicename);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		System.out.println("##===" + shopBeanCase.getStatue());
	}
	
	
	public void goback() {
		AppUtil.goBack0(driver);
	}

}
