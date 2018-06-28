package com.kuaishoudan.financer.selenium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import com.kuaishoudan.financer.bean.KSDCase;
import com.kuaishoudan.financer.bean.RequestPayout;
import com.kuaishoudan.financer.dao.UserDaoImpl;
import com.kuaishoudan.financer.util.IdCardGenerator;
import com.kuaishoudan.financer.util.RandomValue;

public class AppUtil {

	public static AppiumDriver<AndroidElement> getdriver()
			throws MalformedURLException {
		System.out.println("**");
		// set up appium
		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "apps");
		File app = new File(appDir,
				"financer_ceshi_2.4.8.0_24800_2018-06-15.apk");// financerfinalVersionjiagusign.apk
		DesiredCapabilities capabilities = new DesiredCapabilities();
	 
		capabilities.setCapability("automationName", "uiautomator2");
		capabilities.setCapability("newCommandTimeout", 480);
		capabilities.setCapability("device", "Android");
		capabilities.setCapability("platformName", "Android");
		// 虚拟机
		capabilities.setCapability("deviceName", "Android Emulator");
		// 真机
		capabilities.setCapability("deviceName", "Android");
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability(CapabilityType.VERSION, "4.4");
		capabilities.setCapability(CapabilityType.PLATFORM, "WINDOWS");
		capabilities.setCapability("app", app.getAbsolutePath());
		// support Chinese
		capabilities.setCapability("unicodeKeyboard", "True");
		capabilities.setCapability("resetKeyboard", "True");

		capabilities.setCapability("noSign", "True");
		capabilities.setCapability("noReset", true);
		capabilities.setCapability("app-package", "com.kuaishoudan.financer");
		capabilities.setCapability("app-activity",
				"com.kuaishoudan.financer.activity.act.WelcomeActivity");
		return new AndroidDriver<AndroidElement>(new URL(
				"http://127.0.0.1:4723/wd/hub"), capabilities);

	}

	// 向上滑动
	public static void swipeToUp(AppiumDriver driver, int during) {
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;
		// System.out.print(width+"@"+height);
		for (int i = 0; i < 2; i++)
			driver.swipe(width / 2, height * 3 / 4, width / 2, height / 4,
					during);
		// wait for page loading12801321
	}

	// 向上滑动
	public static void swipeToUp2(AppiumDriver driver, int during) {
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;
		// System.out.print(width+"@"+height);
		for (int i = 0; i < 9; i++)
			driver.swipe(width / 2, height * 3 / 4, width / 2, height / 4,
					during);

	}

	// 向上滑动
	public static void swipeToUp3(AppiumDriver driver, int during) {
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;
		// System.out.print(width+"@"+height);
		for (int i = 0; i < 5; i++)
			driver.swipe(width / 2, height * 3 / 4, width / 2, height / 4,
					during);

	}
	// 向上滑动
	public static void swipeToUpQk(AppiumDriver driver, int during,int count) {
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;
		// System.out.print(width+"@"+height);
		for (int i = 0; i < (count/2); i++)
			driver.swipe(width / 2, height * 3 / 4, width / 2, height / 4,
					during);

	}

	// 向下滑动
	public static void swipeToDown(AppiumDriver driver, int during) {
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;
		// System.out.print(width+"@"+height);
		for (int i = 0; i < 2; i++)
			driver.swipe(width / 2, height * 3 / 4, width / 2, height - 20,
					during);
		// wait for page loading12801321
	}

	/**
	 * 创建用户
	 * 
	 * @param driver
	 * @param devicename
	 * @param k
	 */
	public static boolean createUser(AppiumDriver<AndroidElement> driver,
			String devicename, int k, KSDCase ksd) {
		boolean flag = false;
		driver.manage().timeouts().implicitlyWait(38, TimeUnit.SECONDS);
		try {
			
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/toolbar_custom_img"))
					.click();// +号

	//		driver.tap(1, 700, 100, 1000);//+
			driver.manage().timeouts().implicitlyWait(38, TimeUnit.SECONDS);
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/menu_manual_input"))
					.click();// 手动输入

	//		driver.tap(1, 550, 300, 1000) ;

			driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
			driver.findElement(By.id("com.kuaishoudan.financer:id/edit_name"))
					.sendKeys(ksd.getUsername());// 名字
			driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
			driver.findElement(By.id("com.kuaishoudan.financer:id/edit_phone")).sendKeys(ksd.getPhone())
					 ;// 手机
		
			driver.manage().timeouts().implicitlyWait(18, TimeUnit.SECONDS);
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/text_id_type")).click();// 点击身份证
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			if (ksd.getIdentitytype() == 1) {
				driver.findElements(
						By.id("com.kuaishoudan.financer:id/text_select"))
						.get(1).click();// 点击身份证
				driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
			
				driver.findElement(
						By.id("com.kuaishoudan.financer:id/edit_id_code")).sendKeys(ksd.getIdentitynum());// 证件号码 *****
			} else if (ksd.getIdentitytype() == 2) {
				driver.findElements(
						By.id("com.kuaishoudan.financer:id/text_select"))
						.get(2).click();// 点击军官证
				driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
	
				driver.findElement(
						By.id("com.kuaishoudan.financer:id/edit_id_code")).sendKeys(ksd.getJgid());// 证件号码 *****
			}

			driver.findElement(
					By.id("com.kuaishoudan.financer:id/edit_id_address")).sendKeys(ksd.getAddress());// 地址
			driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/toolbar_confirm"))
					.click();// 确认
			driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/dialog_custom_confirm"))
					.click();// 马上进件
			flag = true;
		 
			} catch (org.openqa.selenium.NoSuchElementException ex) {
			System.out.println(k + "createuser  " + "NoSuchElementException");
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/toolbar_back")).click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/dialog_custom_confirm"))
					.click();
		}
		return flag;
	}

	/**
	 * 个人贷款
	 * 
	 * @param driver
	 * @param devicename
	 * @param k
	 */
	public static KSDCase addGr(AppiumDriver<AndroidElement> driver,
			WebDriver webdriver, String devicename, int k, KSDCase ksd) {

		String actualstatue = "";

		boolean flag = false;
		boolean cx = false;

		try {
			Thread.sleep(500);
			driver.manage().timeouts().implicitlyWait(158, TimeUnit.SECONDS);

			driver.findElement(
					By.id("com.kuaishoudan.financer:id/btn_select_order_type_individual"))
					.click();// 去申请
		} catch (org.openqa.selenium.NoSuchElementException | InterruptedException ex) {
			// TODO Auto-generated catch block
			System.out.println(ex);
			flag = true;
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/toolbar_back")).click();
		}
		if (!flag) {
			try {
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				int ran = ksd.getCartype();
				// ------------
				if (ran == 1) {
					driver.findElement(
							By.id("com.kuaishoudan.financer:id/check_old_car"))
							.click();// 二手车
				} else {
					driver.findElement(
							By.id("com.kuaishoudan.financer:id/layout_new_car"))
							.click();// 新车
				}
				// ___________

				driver.findElement(
						By.id("com.kuaishoudan.financer:id/text_supplier"))
						.click();// 所属商户
				try {
					AndroidElement supplier = driver.findElements(
							By.id("com.kuaishoudan.financer:id/tv_name"))
							.get(0);
					ksd.setSssh(supplier.getText());
					supplier.click();// 所属商户列表

				} catch (java.lang.IndexOutOfBoundsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					driver.findElement(
							By.id("com.kuaishoudan.financer:id/toolbar_back"))
							.click();
				}
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.findElement(
						By.id("com.kuaishoudan.financer:id/text_brand"))
						.click();// 品牌车系
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				try {
					List<AndroidElement> clpps = driver.findElements(By
							.id("com.kuaishoudan.financer:id/item_brand_item"));// 车辆品牌（奥迪）
					for (int i = 0; i < clpps.size(); i++) {
						String brand = clpps
								.get(i)
								.findElement(
										By.id("com.kuaishoudan.financer:id/text_brand"))
								.getText();

						if (ksd.getCarbrand().equals(brand)) {
							clpps.get(i).click();
							break;
						}

					}
					// clpps.get(2).click();//车辆品牌点
					cx = true;
				} catch (java.lang.IndexOutOfBoundsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					driver.findElement(
							By.id("com.kuaishoudan.financer:id/toolbar_back"))
							.click();
				}
				try {
					if (cx) {
					 
						List<AndroidElement> seriess = driver.findElements(By
								.id("com.kuaishoudan.financer:id/text_series"));// 车辆品牌（奥迪）
						for (int i = 0; i < seriess.size(); i++) {
							String series = seriess.get(i).getText();
							if (ksd.getCarseries().equals(series)) {
								seriess.get(i).click();// 车辆型号
								break;
							}

						}
					}
				} catch (java.lang.IndexOutOfBoundsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					driver.findElement(
							By.id("com.kuaishoudan.financer:id/toolbar_back"))
							.click();
					driver.findElement(
							By.id("com.kuaishoudan.financer:id/toolbar_back"))
							.click();
				}
		
					driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	
				driver.findElement(
						By.id("com.kuaishoudan.financer:id/edit_price")).sendKeys(""+ksd.getCarprice());// 车辆价格
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	
				driver.findElement(
						By.id("com.kuaishoudan.financer:id/edit_loan")).sendKeys(""+ksd.getSqdk());// 申请贷款
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				driver.findElement(
						By.id("com.kuaishoudan.financer:id/text_periods"))
						.click();// 还款期数 / 融资期限
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				driver.findElements(
						By.id("com.kuaishoudan.financer:id/text_select"))
						.get(ksd.getHkqs()).click();// 还款期数周期 /融资期限
				driver.manage().timeouts().implicitlyWait(18, TimeUnit.SECONDS);
				driver.findElement(
						By.id("com.kuaishoudan.financer:id/text_product"))
						.click();// 金融产品
				Thread.sleep(500);
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				try {
					List<AndroidElement> producs = driver.findElements(By
							.id("com.kuaishoudan.financer:id/text_product"));
					for (AndroidElement product : producs) {
						if (!product.getText().contains("平安租赁")) {
							product.click();// 第一个产品
							break;
						}
					}

					Thread.sleep(300);
					ksd.setProduct(driver
							.findElement(
									By.id("com.kuaishoudan.financer:id/text_product"))
							.getText().trim());
				} catch (java.lang.IndexOutOfBoundsException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
					driver.findElement(
							By.id("com.kuaishoudan.financer:id/toolbar_back"))
							.click();
				}
				// _________
				Thread.sleep(1000);
				AppUtil.swipeToUp(driver, 800);// 向上滑动
				Thread.sleep(2200);
				driver.findElement(
						By.id("com.kuaishoudan.financer:id/text_feilv"))
						.click();// 费率
				driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
				try {
					List<AndroidElement> rates = driver.findElements(By
							.id("com.kuaishoudan.financer:id/text_select"));
					driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
					rates.get(0).click();// 费率选项
					driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
					String rate=driver.findElement(By.id("com.kuaishoudan.financer:id/text_feilv")).getText();
					ksd.setRate(rate);
				

				} catch (java.lang.IndexOutOfBoundsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					driver.findElement(
							By.id("com.kuaishoudan.financer:id/toolbar_back"))
							.click();
				}

				driver.findElement(
						By.id("com.kuaishoudan.financer:id/edit_remark")).sendKeys(ksd.getRemark())
					;// 备注
				driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);

				WebUtil.login(webdriver, ksd);// 登录
				List<Integer> list = WebOrgan.getImge1(webdriver, ksd);

				WebUtil.logout(webdriver);

				driver.findElement(
						By.id("com.kuaishoudan.financer:id/toolbar_next"))
						.click();// 下一步
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

				// if (ran == 1) { // 二手车
				int havesystem = UserDaoImpl.gethave_system(ksd.getProduct()
						.trim().split("-")[0]);// 产品名称查是否有常规甩单
				System.out.println(ksd.getProduct().trim().split("-")[0] + ","
						+ havesystem);
				if (havesystem == 0) {
					driver.findElement(
							By.id("com.kuaishoudan.financer:id/dialog_custom_confirm"))
							.click();// 订单常规

				}
				// }
				Thread.sleep(2000);
				driver.manage().timeouts().implicitlyWait(55, TimeUnit.SECONDS);
				int aa = 0, countImg = 0;

				List<Integer> list2 = new ArrayList<Integer>();
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i) < 9) {
						List<Integer> list3 = UserDaoImpl.getImgType(
								list.get(i) + 1, list);
						list2.addAll(list3);
						aa = list3.size();
						countImg = aa + countImg;
					}
				}
				Collections.sort(list);
				if (countImg == 0) {
					for (Integer type : list) {
						if (type > 99) {
							list2.add(type);
							break;
						}
					}
				}

				ksd.setImgtypes(list2);
				System.out.println(list2.size() + "$$$" + countImg);

				ksd.setImgcount(countImg);
				driver.findElement(By.id("com.kuaishoudan.financer:id/btn_add"))
						.click();// 上传照片

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (org.openqa.selenium.NoSuchElementException ex) {
				ex.printStackTrace();
				/*
				 * System.out.println(k + "NoSuchElementException");
				 * driver.findElement(
				 * By.id("com.kuaishoudan.financer:id/toolbar_back")) .click();
				 * driver.manage().timeouts().implicitlyWait(5,
				 * TimeUnit.SECONDS); driver.findElement(
				 * By.id("com.kuaishoudan.financer:id/dialog_custom_confirm"))
				 * .click(); driver.manage().timeouts().implicitlyWait(5,
				 * TimeUnit.SECONDS); driver.findElement(
				 * By.id("com.kuaishoudan.financer:id/toolbar_back")) .click();
				 */

			}

			actualstatue = upload(driver, ksd.getImgcount());
			// System.out.println("####"+actualstatue);
			// ksd.setStatue(actualstatue);
		}
		return ksd;
	}

	/**
	 * 企业贷款
	 * 
	 * @param driver
	 * @param devicename
	 * @param k
	 */
	public static KSDCase addQy(AppiumDriver<AndroidElement> driver,
			WebDriver webdriver, String devicename, int k, KSDCase ksd) {

		String actualstatue = "";
		boolean flag = false;
		boolean cx = false;

		try {
			Thread.sleep(500);
			driver.manage().timeouts().implicitlyWait(158, TimeUnit.SECONDS);
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/btn_select_order_type_company"))
					.click();// 去申请
		} catch (org.openqa.selenium.NoSuchElementException | InterruptedException ex) {
			// TODO Auto-generated catch block
			System.out.println(ex);
			flag = true;
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/toolbar_back")).click();
		}
		if (!flag) {
			try {

				driver.manage().timeouts().implicitlyWait(55, TimeUnit.SECONDS);
		
				driver.findElement(
						By.id("com.kuaishoudan.financer:id/edit_company_name")).sendKeys(ksd.getBusinessname())
						 ;// 企业名称
				driver.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);
			
				driver.findElement(
						By.id("com.kuaishoudan.financer:id/edit_company_business_license"))
						.sendKeys(ksd.getBusinessid());// 营业执照号
				driver.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);
				
				int ran = ksd.getCartype();
				// ------------
				if (ran == 1) {
					driver.findElement(
							By.id("com.kuaishoudan.financer:id/check_old_car"))
							.click();// 二手车
				} else {
					driver.findElement(
							By.id("com.kuaishoudan.financer:id/layout_new_car"))
							.click();// 新车
				}
				// ___________

				driver.findElement(
						By.id("com.kuaishoudan.financer:id/text_supplier"))
						.click();// 所属商户
				try {
					AndroidElement supplier = driver.findElements(
							By.id("com.kuaishoudan.financer:id/tv_name"))
							.get(0);
					ksd.setSssh(supplier.getText());
					supplier.click();// 所属商户列表

				} catch (java.lang.IndexOutOfBoundsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				}
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				driver.findElement(
						By.id("com.kuaishoudan.financer:id/text_brand"))
						.click();// 品牌车系
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				try {
					List<AndroidElement> clpps = driver.findElements(By
							.id("com.kuaishoudan.financer:id/item_brand_item"));// 车辆品牌（奥迪）
					for (int i = 0; i < clpps.size(); i++) {
						String brand = clpps
								.get(i)
								.findElement(
										By.id("com.kuaishoudan.financer:id/text_brand"))
								.getText();

						if (ksd.getCarbrand().equals(brand)) {
							clpps.get(i).click();
							break;
						}

					}
					// clpps.get(2).click();//车辆品牌点
					cx = true;
				} catch (java.lang.IndexOutOfBoundsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					driver.findElement(
							By.id("com.kuaishoudan.financer:id/toolbar_back"))
							.click();
				}
				try {
					if (cx) {
						/*
						 * driver.findElements(
						 * By.id("com.kuaishoudan.financer:id/item_series_item"
						 * )) .get(1).click();// 车辆型号
						 */
						List<AndroidElement> seriess = driver.findElements(By
								.id("com.kuaishoudan.financer:id/text_series"));// 车辆品牌（奥迪）
						for (int i = 0; i < seriess.size(); i++) {
							String series = seriess.get(i).getText();
							if (ksd.getCarseries().equals(series)) {
								seriess.get(i).click();// 车辆型号
								break;
							}

						}
					}
				} catch (java.lang.IndexOutOfBoundsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					driver.findElement(
							By.id("com.kuaishoudan.financer:id/toolbar_back"))
							.click();
					driver.findElement(
							By.id("com.kuaishoudan.financer:id/toolbar_back"))
							.click();
				}
				
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			
				driver.findElement(
						By.id("com.kuaishoudan.financer:id/edit_price")).sendKeys(""+ksd.getCarprice())
						 ;// 车辆价格
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	
				driver.findElement(
						By.id("com.kuaishoudan.financer:id/edit_loan")).sendKeys(""+ksd.getSqdk());// 申请贷款
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

				driver.findElement(
						By.id("com.kuaishoudan.financer:id/text_periods"))
						.click();// 还款期数 / 融资期限
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				driver.findElements(
						By.id("com.kuaishoudan.financer:id/text_select"))
						.get(ksd.getHkqs()).click();// 还款期数周期 /融资期限
				// _________

				Thread.sleep(300);
				AppUtil.swipeToUp(driver, 800);// 向上滑动
				Thread.sleep(2200);
				driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
				driver.findElement(
						By.id("com.kuaishoudan.financer:id/text_product"))
						.click();// 金融产品
				Thread.sleep(500);
				driver.manage().timeouts().implicitlyWait(55, TimeUnit.SECONDS);
				try {
					List<AndroidElement> producs = driver.findElements(By
							.id("com.kuaishoudan.financer:id/text_product"));
					for (AndroidElement product : producs) {
						if (!product.getText().contains("平安租赁")) {
							product.click();// 第一个产品
							break;
						}
					}

					Thread.sleep(500);
					ksd.setProduct(driver
							.findElement(
									By.id("com.kuaishoudan.financer:id/text_product"))
							.getText().trim());
				} catch (java.lang.IndexOutOfBoundsException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
					driver.findElement(
							By.id("com.kuaishoudan.financer:id/toolbar_back"))
							.click();
				}
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				driver.findElement(
						By.id("com.kuaishoudan.financer:id/text_feilv"))
						.click();// 费率
				driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
				try {
					List<AndroidElement> rates = driver.findElements(By
							.id("com.kuaishoudan.financer:id/text_select"));
					driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
					rates.get(0).click();// 费率选项
					driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
					String rate=driver.findElement(By.id("com.kuaishoudan.financer:id/text_feilv")).getText();
					ksd.setRate(rate);

				} catch (java.lang.IndexOutOfBoundsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					driver.findElement(
							By.id("com.kuaishoudan.financer:id/toolbar_back"))
							.click();
				}

		
				driver.findElement(
						By.id("com.kuaishoudan.financer:id/edit_remark")).sendKeys(ksd.getRemark())
						 ;// 备注
				driver.manage().timeouts().implicitlyWait(125, TimeUnit.SECONDS);

				WebUtil.login(webdriver, ksd);// 登录
				List<Integer> list = WebOrgan.getImge1(webdriver, ksd);
				ksd.setImgtypes(list);
				WebUtil.logout(webdriver);

				driver.findElement(
						By.id("com.kuaishoudan.financer:id/toolbar_next"))
						.click();// 下一步
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

				// if (ran == 1) { // 二手车
				int havesystem = UserDaoImpl.gethave_system(ksd.getProduct()
						.trim().split("-")[0]);// 产品名称查是否有常规甩单

				System.out.println(ksd.getProduct().trim().split("-")[0] + ","
						+ havesystem);

				if (havesystem == 0) {
					driver.findElement(
							By.id("com.kuaishoudan.financer:id/dialog_custom_confirm"))
							.click();// 订单常规

				}
				// }
				Thread.sleep(1000);
				driver.manage().timeouts().implicitlyWait(55, TimeUnit.SECONDS);
				List<Integer> list2 = new ArrayList<Integer>();
				int aa = 0, countImg = 0;

				for (int i = 0; i < list.size(); i++) {
					if (list.get(i) < 9) {
						List<Integer> list3 = UserDaoImpl.getImgType(
								list.get(i) + 1, list);
						list2.addAll(list3);
						aa = list3.size();
						countImg = aa + countImg;
					}
				}
				Collections.sort(list);
				if (countImg == 0) {
					for (Integer type : list) {
						if (type > 99) {
							list2.add(type);
							break;
						}
					}
				}
				ksd.setImgtypes(list2);
				System.out.println(list2.size() + "$$$" + countImg);

				ksd.setImgcount(countImg);
				driver.findElement(By.id("com.kuaishoudan.financer:id/btn_add"))
						.click();// 上传照片
			}  catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("InterruptedException");
				// e.printStackTrace();

			} catch (org.openqa.selenium.NoSuchElementException ex) {
				System.out.println(k + "NoSuchElementException");
				ex.printStackTrace();
				/*
				 * driver.findElement(
				 * By.id("com.kuaishoudan.financer:id/toolbar_back")) .click();
				 * driver.manage().timeouts().implicitlyWait(5,
				 * TimeUnit.SECONDS); driver.findElement(
				 * By.id("com.kuaishoudan.financer:id/dialog_custom_confirm"))
				 * .click(); driver.manage().timeouts().implicitlyWait(5,
				 * TimeUnit.SECONDS); driver.findElement(
				 * By.id("com.kuaishoudan.financer:id/toolbar_back")) .click();
				 */

			} catch (org.openqa.selenium.WebDriverException e) {
				System.out.println(k + "WebDriverException");
				e.printStackTrace();
				/*
				 * driver.findElement(
				 * By.id("com.kuaishoudan.financer:id/toolbar_cancel"))
				 * .click(); driver.manage().timeouts().implicitlyWait(5,
				 * TimeUnit.SECONDS); driver.findElement(
				 * By.id("com.kuaishoudan.financer:id/dialog_custom_confirm"))
				 * .click(); driver.manage().timeouts().implicitlyWait(5,
				 * TimeUnit.SECONDS); driver.findElement(
				 * By.id("com.kuaishoudan.financer:id/toolbar_back")) .click();
				 */
			}

			actualstatue = upload(driver, ksd.getImgcount());
			// System.out.println("####=="+actualstatue);
			// ksd.setStatue(actualstatue);

		}
		return ksd;
	}

	public static KSDCase addTest(AppiumDriver<AndroidElement> driver,
			WebDriver webdriver, String devicename, int i) {

		KSDCase ksd = RandomValue.getRandom();
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
		int gq = ksd.getQygr();
		boolean flag = createUser(driver, devicename, i, ksd);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (flag) {
			if (gq == 2) {// 企业贷款
				ksd = addQy(driver, webdriver, devicename, i, ksd);
			} else {// 个人贷款
				ksd = addGr(driver, webdriver, devicename, i, ksd);
				//
			}
		}
		return ksd;

	}

	// 再次进件
	public static int zcjj(AppiumDriver<AndroidElement> driver) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_name"))
				.get(0).click();// 首页列表
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String titletext = driver
				.findElement(By.id("com.kuaishoudan.financer:id/toolbar_title"))
				.getText().trim();// 标题文本
		System.out.println(titletext);
		int a=0;
		if ("贷款详情".equals(titletext)) {
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/toolbar_loan_status"))
					.click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/text_customer_algin_jinjian"))
					.click(); // 大于1次进件
			a=1;
		} else {
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/btn_add_loan")).click();// 第3次进件3
			a=2;
		}
		
		return a;
	}

	/**
	 * 上传照片
	 * 
	 * @param driver
	 * @return
	 */
	public static String upload(AppiumDriver<AndroidElement> driver,
			int imgcount) {
		String acstatue = "";

		int count1 = imgcount / 20;
		int count2 = imgcount % 20;
		try {
		 

			for (int j = 0; j < count1; j++) {// 20

				if (j > 0) {
					driver.findElement(
							By.id("com.kuaishoudan.financer:id/btn_add"))
							.click();// 上传照片
				}
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				driver.findElement(
						By.id("com.kuaishoudan.financer:id/dialog_photo_select_btn_gallery"))
						.click();// 从相册选择
				for (int i = 0; i < 12; i++) {
					if (i == 0&& j>0) {
						for (int k = 0; k < (j * 20); k++)
							driver.findElements(
									By.id("com.kuaishoudan.financer:id/iv_thumb"))
									.get(i).click();// 添加图片（驾驶证）
					}else if(i==0&&j==0){
						
					}else {
						driver.findElements(
								By.id("com.kuaishoudan.financer:id/iv_thumb"))
								.get(i).click();// 添加图片（驾驶证）第一次
					}
					driver.findElements(
							By.id("com.kuaishoudan.financer:id/iv_thumb"))
							.get(i).click();// 添加图片（驾驶证）
				}
				AppUtil.swipeToUp3(driver, 800);// 向上滑动

				Thread.sleep(2000);
				List<AndroidElement> ivts = driver.findElements(By
						.id("com.kuaishoudan.financer:id/iv_check"));
				System.out.println("度" + ivts.size());
				int m = 0;
				for (int i = 0; i < ivts.size(); i++) {
					if (m == 8) {
						break;
					}
					if (ivts.get(i).getAttribute("selected").equals("false")) {

						driver.findElements(
								By.id("com.kuaishoudan.financer:id/iv_thumb"))
								.get(i).click();// 添加图片（驾驶证）
						driver.findElements(
								By.id("com.kuaishoudan.financer:id/iv_thumb"))
								.get(i).click();// 添加图片（驾驶证）
						m++;

					}
				}

				driver.findElement(By.id("com.kuaishoudan.financer:id/btn_ok"))
						.click();// 两种证上传——确定按钮
				Thread.sleep(2000);

				AppUtil.swipeToUp2(driver, 1000);// 向上滑动
				Thread.sleep(8500);

			}

			if (count2 == 0 && count1 > 0) {

			} else {

				Thread.sleep(2000);
				driver.manage().timeouts().implicitlyWait(48, TimeUnit.SECONDS);
				if (count1 != 0) {
					driver.findElement(
							By.id("com.kuaishoudan.financer:id/btn_add"))
							.click();// 上传照片
				}
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				driver.findElement(
						By.id("com.kuaishoudan.financer:id/dialog_photo_select_btn_gallery"))
						.click();// 从相册选择

				if (count2 == 0 && count1 == 0) {

					driver.findElements(
							By.id("com.kuaishoudan.financer:id/iv_thumb"))
							.get(0).click();// 添加图片（驾驶证）
				} else if (count2 > 0 && count2 < 12) {

					for (int i = 0; i < count2; i++) {
						if (i == 0) {
							for (int k = 0; k < (count1 * 20); k++)
								driver.findElements(
										By.id("com.kuaishoudan.financer:id/iv_thumb"))
										.get(i).click();// 添加图片（驾驶证）
						} else {
							driver.findElements(
									By.id("com.kuaishoudan.financer:id/iv_thumb"))
									.get(i).click();// 添加图片（驾驶证）第一次
						}
						driver.findElements(
								By.id("com.kuaishoudan.financer:id/iv_thumb"))
								.get(i).click();// 添加图片（驾驶证）
					}
				} else if (count2 > 11 && count2 < 21) {

					for (int i = 0; i < 12; i++) {
						if (i != 0)
							driver.findElements(
									By.id("com.kuaishoudan.financer:id/iv_thumb"))
									.get(i).click();// 添加图片（驾驶证）
						driver.findElements(
								By.id("com.kuaishoudan.financer:id/iv_thumb"))
								.get(i).click();// 添加图片（驾驶证）
					}
					AppUtil.swipeToUp3(driver, 800);// 向上滑动

					Thread.sleep(2000);
					List<AndroidElement> ivts = driver.findElements(By
							.id("com.kuaishoudan.financer:id/iv_check"));
					System.out.println("度" + ivts.size());
					int n = 0;
					for (int i = 0; i < ivts.size(); i++) {
						if (n == (count2 - 12)) {
							break;
						}
						if (ivts.get(i).getAttribute("selected")
								.equals("false")) {

							driver.findElements(
									By.id("com.kuaishoudan.financer:id/iv_thumb"))
									.get(i).click();// 添加图片（驾驶证）
							driver.findElements(
									By.id("com.kuaishoudan.financer:id/iv_thumb"))
									.get(i).click();// 添加图片（驾驶证）
							n++;
						}
					}

				}

				driver.manage().timeouts().implicitlyWait(18, TimeUnit.SECONDS);
				//
				driver.findElement(By.id("com.kuaishoudan.financer:id/btn_ok"))
						.click();// 两种证上传——确定按钮

				Thread.sleep(5000 + count2 * 2000);
			}
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/toolbar_confirm"))
					.click();// 上传完照片-确认按钮
			driver.manage().timeouts().implicitlyWait(58, TimeUnit.SECONDS);
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/dialog_custom_confirm"))
					.click();// 提醒确定是

			// driver.findElement(By.id("com.kuaishoudan.financer:id/tv_guide_know")).click();//我知道了
			// driver.findElement(By.id("com.kuaishoudan.financer:id/tv_guide_know")).click();//我知道了
			// driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_back")).click();//返回按钮
			driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
			/*
			 * acstatue = driver .findElement(
			 * By.id("com.kuaishoudan.financer:id/item_status"))
			 * .getText().trim();
			 */

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (org.openqa.selenium.WebDriverException e) {
			e.printStackTrace();
			/*
			 * System.out.println(e);
			 * driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
			 * driver.findElement(
			 * By.id("com.kuaishoudan.financer:id/toolbar_back")).click();// 返回
			 * driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
			 * driver.findElement(
			 * By.id("com.kuaishoudan.financer:id/dialog_custom_confirm"))
			 * .click();// 是 driver.manage().timeouts().implicitlyWait(8,
			 * TimeUnit.SECONDS); driver.findElement(
			 * By.id("com.kuaishoudan.financer:id/toolbar_back")).click();//
			 * 从客户页面返回
			 */}

		try {
			Thread.sleep(500);
			driver.manage().timeouts().implicitlyWait(38, TimeUnit.SECONDS);
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/toolbar_back")).click();// 返回按钮
			Thread.sleep(1500);
			// acstatue = AppSPUtil.getActstatue(driver);// 状态值
		} catch (org.openqa.selenium.WebDriverException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return acstatue;
	}

	public static KSDCase addZjjtest(AppiumDriver<AndroidElement> driver,WebDriver webdriver,
			String devicename, int i) {
 
		KSDCase ksd = RandomValue.getRandom();
		System.out.println("名称" + ksd.getUsername() + "手机" + ksd.getPhone()
				+ "身份证号" + ksd.getIdentitynum() + "身份类型"
				+ ksd.getIdentitytype() + "军官" + ksd.getJgid() + "企业个人"
				+ ksd.getQygr() + "车类型" + ksd.getCartype() + "车品牌"
				+ ksd.getCarbrand() + "车系" + ksd.getCarseries() + "车价格"
				+ ksd.getCarprice() + "贷款价格" + ksd.getSqdk() + "融资期限"
				+ ksd.getHkqs());
		int gq = ksd.getQygr();
		AppUtil.zcjj(driver);
		if (gq == 0) {// 企业贷款
			ksd = addQy(driver, webdriver, devicename, i, ksd);
		} else {// 个人贷款
			ksd = addGr(driver, webdriver, devicename, i, ksd);
		}
		return ksd;
	}

	public static void login(AppiumDriver<AndroidElement> driver,
			String devicename, KSDCase ksd) {

		try {
			driver.manage().timeouts().implicitlyWait(18, TimeUnit.SECONDS);
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/edit_account")).clear();
			Runtime.getRuntime().exec(
					"adb -s " + devicename + " shell input text "
							+ ksd.getLoginemail());
			Thread.sleep(600);
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/edit_account")).click();
			Thread.sleep(2500);
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/edit_password")).click();
			Runtime.getRuntime().exec(
					"adb -s " + devicename + " shell input text "
							+ ksd.getPwd());
			Thread.sleep(600);
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/edit_password")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("com.kuaishoudan.financer:id/btn_login"))
					.click();
			Thread.sleep(3000);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// driver.findElement(By.id("com.kuaishoudan.financer:id/edit_account")).sendKeys(username);
		// driver.findElement(By.id("com.kuaishoudan.financer:id/edit_password")).sendKeys("@123456");

	}

	// 登出
	public static void logout(AppiumDriver<AndroidElement> driver) {
		// System.out.println("logout");
		driver.manage().timeouts().implicitlyWait(18, TimeUnit.SECONDS);
		driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_menu"))
				.click();// 菜单
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.findElement(By.id("com.kuaishoudan.financer:id/header_img"))
				.click();// 头像
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.findElement(By.id("com.kuaishoudan.financer:id/account_logout"))
				.click();// 退出登录
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/dialog_custom_confirm"))
				.click();// 确定)
	}

	public static boolean ElementExist(AppiumDriver<AndroidElement> driver,
			By locator) {
		try {
			driver.findElement(locator);
			return true;
		} catch (org.openqa.selenium.NoSuchElementException ex) {
			return false;
		}

	}

	/**
	 * 已请款-----返回查看状态
	 * 
	 * @param driver
	 */
	public static String getStatue(AppiumDriver<AndroidElement> driver) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_back"))
				.click();// 返回
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_back"))
				.click();// 返回
		String statue = AppSPUtil.getActstatue(driver);// 状态值
		return statue;

	}

	/**
	 * (未使用) 查看进度
	 * 
	 * @param driver
	 */
	public static void look_status(AppiumDriver<AndroidElement> driver) {
		driver.manage().timeouts().implicitlyWait(18, TimeUnit.SECONDS);
		// driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_back")).click();//返回按钮
		/*
		 * driver.findElements(By.id("com.kuaishoudan.financer:id/text_name"))
		 * .get(0).click();// 第一个客户
		 * driver.manage().timeouts().implicitlyWait(28, TimeUnit.SECONDS);
		 * driver.findElement(
		 * By.id("com.kuaishoudan.financer:id/toolbar_loan_status")) .click();//
		 * 流程。。。 driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		 * driver.findElement(
		 * By.id("com.kuaishoudan.financer:id/text_customer_look_status"))
		 * .click();// 查看进度
		 */

	}

	/**
	 * 两次返回
	 * 
	 * @param driver
	 */
	public static void goBack1(AppiumDriver<AndroidElement> driver) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_back"))
				.click();// 返回按钮
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_back"))
				.click();// 返回按钮
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 一次返回
	 * 
	 * @param driver
	 */
	public static void goBack0(AppiumDriver<AndroidElement> driver) {
		driver.manage().timeouts().implicitlyWait(18, TimeUnit.SECONDS);
		driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_back"))
				.click();// 返回按钮
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String getIndexname(AppiumDriver<AndroidElement> driver) {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String name = driver
				.findElements(By.id("com.kuaishoudan.financer:id/text_name"))
				.get(0).getText().trim();
		return name;
	}

	/**
	 * 上传照片
	 * 
	 * @param driver
	 * @return
	 */
	public static String uploadQk(AppiumDriver<AndroidElement> driver,
			int imgcount) {
		String acstatue = "";

		int count1 = imgcount / 10;
		int count2 = imgcount % 10;
		try {

			for (int j = 0; j < count1; j++) {// 10

				driver.findElement(By.id("com.kuaishoudan.financer:id/btn_add"))
						.click();// 上传照片

				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				driver.findElement(
						By.id("com.kuaishoudan.financer:id/dialog_photo_select_btn_gallery"))
						.click();// 从相册选择
				for (int i = 0; i < 10; i++) {
					if (i == 0&&j>0) {
						for (int k = 0; k < (j * 10); k++)
							driver.findElements(
									By.id("com.kuaishoudan.financer:id/iv_thumb"))
									.get(i).click();// 添加图片（驾驶证）
					}else if(i==0&&j==0){
						
					}else {
						driver.findElements(
								By.id("com.kuaishoudan.financer:id/iv_thumb"))
								.get(i).click();// 添加图片（驾驶证）第一次
					}
					driver.findElements(
							By.id("com.kuaishoudan.financer:id/iv_thumb"))
							.get(i).click();// 添加图片（驾驶证）
				}

				driver.findElement(By.id("com.kuaishoudan.financer:id/btn_ok"))
						.click();// 两种证上传——确定按钮
				Thread.sleep(2000);

				AppUtil.swipeToUp2(driver, 1000);// 向上滑动
				Thread.sleep(8500);

			}

			if (count2 == 0 && count1 > 0) {

			} else {

				Thread.sleep(2000);
				driver.manage().timeouts().implicitlyWait(48, TimeUnit.SECONDS);

				driver.findElement(By.id("com.kuaishoudan.financer:id/btn_add"))
						.click();// 上传照片

				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				driver.findElement(
						By.id("com.kuaishoudan.financer:id/dialog_photo_select_btn_gallery"))
						.click();// 从相册选择

				if (count2 == 0 && count1 == 0) {

					driver.findElements(
							By.id("com.kuaishoudan.financer:id/iv_thumb"))
							.get(0).click();// 添加图片（驾驶证）
				} else if (count2 > 0 && count2 < 11) {

					for (int i = 0; i < count2; i++) {
						if (i == 0) {
							for (int k = 0; k < (count1 * 10); k++)
								driver.findElements(
										By.id("com.kuaishoudan.financer:id/iv_thumb"))
										.get(i).click();// 添加图片（驾驶证）
						} else {
							driver.findElements(
									By.id("com.kuaishoudan.financer:id/iv_thumb"))
									.get(i).click();// 添加图片（驾驶证）第一次
						}
						driver.findElements(
								By.id("com.kuaishoudan.financer:id/iv_thumb"))
								.get(i).click();// 添加图片（驾驶证）
					}
				}

				driver.manage().timeouts().implicitlyWait(18, TimeUnit.SECONDS);
				//
				driver.findElement(By.id("com.kuaishoudan.financer:id/btn_ok"))
						.click();// 两种证上传——确定按钮

				Thread.sleep(5000 + count2 * 2000);
			}

			driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (org.openqa.selenium.WebDriverException e) {
			e.printStackTrace();

		}


		return acstatue;
	}

	// 返点费用支出
	public static void testFd(AppiumDriver<AndroidElement> driver,
			String devicename, RequestPayout RequestPyout)
			throws InterruptedException, IOException {

		AppUtil.swipeToUp3(driver, 1000);// 向上滑动
		Thread.sleep(1000);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElements(By.id("com.kuaishoudan.financer:id/iv_is_show"))
				.get(0).click();
		AppUtil.swipeToUp(driver, 1000);// 向上滑动
		// 车款融资额返点

		Runtime.getRuntime().exec(
				"adb -s " + devicename + " shell input text "
						+ RequestPyout.getFinancing_back_point());
		Thread.sleep(500);
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_content"))
				.get(0).click();
		Thread.sleep(1500);
		// GPS返点
		Runtime.getRuntime().exec(
				"adb -s " + devicename + " shell input text "
						+ RequestPyout.getGps_back_point());
		Thread.sleep(500);
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_content"))
				.get(1).click();
		Thread.sleep(1500);
		// 保险返点
		Runtime.getRuntime().exec(
				"adb -s " + devicename + " shell input text "
						+ RequestPyout.getInsurance_back_point());
		Thread.sleep(500);
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_content"))
				.get(2).click();
		Thread.sleep(1500);
		// 服务费返点
		Runtime.getRuntime().exec(
				"adb -s " + devicename + " shell input text "
						+ RequestPyout.getService_back_point());
		Thread.sleep(500);
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_content"))
				.get(3).click();
		Thread.sleep(1000);
		driver.findElements(By.id("com.kuaishoudan.financer:id/iv_is_show"))
				.get(0).click();
	}

	// 新车抵押费用支出

	public static void testDy(AppiumDriver<AndroidElement> driver,
			String devicename, RequestPayout RequestPyout)
			throws InterruptedException, IOException {

		AppUtil.swipeToUp3(driver, 1000);// 向上滑动
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElements(By.id("com.kuaishoudan.financer:id/iv_is_show"))
				.get(1).click();
		AppUtil.swipeToUp(driver, 1000);// 向上滑动
		Thread.sleep(2000);

		// 抵押费
		Runtime.getRuntime().exec(
				"adb -s " + devicename + " shell input text "
						+ RequestPyout.getMortgage_free());
		Thread.sleep(500);
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_content"))
				.get(0).click();
		Thread.sleep(1500);
		// 解押费
		Runtime.getRuntime().exec(
				"adb -s " + devicename + " shell input text "
						+ RequestPyout.getSign_free());
		Thread.sleep(500);
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_content"))
				.get(1).click();
		Thread.sleep(1000);
		// 上牌抵押地
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/tv_chekuan_shangpaidiya"))
				.click();
		driver.findElement(By.id("com.kuaishoudan.financer:id/options3"))
				.click();// 城市
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;
		driver.swipe(width * 2 / 3, height - 80, width * 2 / 3, height - 280,
				800);
		driver.findElement(By.id("com.kuaishoudan.financer:id/btnSubmit"))
				.click();// 城市确定
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		// 上牌方
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/tv_chekuan_shangpaifang"))
				.click();
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_select"))
				.get(RequestPyout.getRegistration_party()).click();

		// 抵押方
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/tv_chekuan_diyafang"))
				.click();
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_select"))
				.get(RequestPyout.getRegistration_party()).click();

		driver.findElements(By.id("com.kuaishoudan.financer:id/iv_is_show"))
				.get(1).click();

	}

	// 新车杂项费用支出

	public static void testZx(AppiumDriver<AndroidElement> driver,
			String devicename, RequestPayout RequestPyout)
			throws InterruptedException, IOException {

		AppUtil.swipeToUp3(driver, 1000);// 向上滑动
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElements(By.id("com.kuaishoudan.financer:id/iv_is_show"))
				.get(2).click();
		AppUtil.swipeToUp(driver, 1000);// 向上滑动
		// GPS安装费
		Runtime.getRuntime().exec(
				"adb -s " + devicename + " shell input text "
						+ RequestPyout.getGps_installation());
		Thread.sleep(500);
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_content"))
				.get(0).click();
		Thread.sleep(1500);
		// 前置利息
		Runtime.getRuntime().exec(
				"adb -s " + devicename + " shell input text "
						+ RequestPyout.getInterest_on_pre());
		Thread.sleep(500);
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_content"))
				.get(1).click();
		Thread.sleep(1500);
		// 退款
		Runtime.getRuntime().exec(
				"adb -s " + devicename + " shell input text "
						+ RequestPyout.getRefund());
		Thread.sleep(500);
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_content"))
				.get(2).click();
		Thread.sleep(1500);
		// 车价贷款(返款)
		Runtime.getRuntime().exec(
				"adb -s " + devicename + " shell input text "
						+ RequestPyout.getThe_car_loan());
		Thread.sleep(500);
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_content"))
				.get(3).click();
		Thread.sleep(1500);
		driver.findElements(By.id("com.kuaishoudan.financer:id/iv_is_show"))
				.get(2).click();

	}

}
