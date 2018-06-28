package com.kuaishoudan.financer.ys;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.kuaishoudan.financer.bean.KSDCase;
import com.kuaishoudan.financer.dao.UserDaoImpl;
import com.kuaishoudan.financer.selenium.AppUtil;
import com.kuaishoudan.financer.selenium.WebOrgan;
import com.kuaishoudan.financer.selenium.WebUtil;
import com.kuaishoudan.financer.util.RandomValue;

/**
 * 预审
 * 
 * @author Administrator
 * 
 */
public class testYs1 {

	public AppiumDriver<AndroidElement> driver;
	String devicename = "";
	public WebDriver webdriver;
	KSDCase ksd = null;

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("**1");
		testYs1 ty = new testYs1();

		// ty.setUp();// app启动
		ty.setUp2();// web启动
		ty.testFp();// 开始录入
		System.out.println("finish");
		Thread.sleep(23000);
		ty.tearDown();
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
		Thread.sleep(3000);

	}

	// web
	public void setUp2() throws IOException, InterruptedException {
		webdriver = WebUtil.getdriver();
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

	public void tearDown() throws Exception {

		// driver.quit();
		webdriver.quit();
	}

	public void testFp() {
		WebUtil.login(webdriver, ksd);// 登录
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		webdriver.findElement(By.linkText("客户")).click();
		webdriver.findElement(By.linkText("已分配")).click();
		clickItem(webdriver, ksd.getLoginname());
		webdriver.findElement(By.linkText("开始录入")).click();

		webdriver.findElement(
				By.xpath("//div[@id='main']/div[2]/ul/li/a/div/span")).click();// 车辆信息
		webdriver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		Select userSelect = new Select(webdriver.findElement(By.id("brand")));
		userSelect.selectByVisibleText("奥迪汽车-奥迪");

		Select userSelect2 = new Select(webdriver.findElement(By
				.id("carSeries")));
		userSelect2.selectByVisibleText("A1");
		
		Select userSelect3 = new Select(webdriver.findElement(By
				.id("carYear")));
		userSelect3.selectByVisibleText("2017");
		
		Select userSelect4 = new Select(webdriver.findElement(By
				.id("carModel")));
		userSelect4.selectByVisibleText("A1-1.4TFSI 双离合 30TFSI 时尚型");
		
		webdriver.findElement(By.name("carColor")).sendKeys("白色");
		webdriver.findElement(By.id("buyTax")).sendKeys("2");
		webdriver.findElement(By.id("otherFee")).sendKeys("0");

		webdriver.findElement(By.id("assure")).sendKeys("0");//保险
		webdriver.findElement(By.name("contactWay")).sendKeys(ksd.getPhone());
		webdriver.findElement(By.name("salesName")).sendKeys(ksd.getUsername());
		
		webdriver.findElement(By.id("rentModel2")).click();
		
	/*	Select userSelect5 = new Select(webdriver.findElement(By
				.id("carModel")));
		userSelect5.selectByValue("【极致定制】新车融330-A");*/
		
		
	//	webdriver.findElement(By.id("productName")).click();
		webdriver.findElement(By.xpath("//div[@class='column_val']/select/option[2]")).click();
		
	/*	Select userSelect6= new Select(webdriver.findElement(By
				.id("rentDue")));
		userSelect6.selectByVisibleText("12");*/
		
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
			driver.manage().timeouts().implicitlyWait(18, TimeUnit.SECONDS);

			driver.findElement(
					By.id("com.kuaishoudan.financer:id/btn_select_order_type_individual"))
					.click();// 去申请
		} catch (org.openqa.selenium.NoSuchElementException ex) {
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
				// Thread.sleep(1000);
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				Runtime.getRuntime().exec(
						"adb -s " + devicename + " shell input text "
								+ ksd.getCarprice());
				Thread.sleep(500);
				driver.findElement(
						By.id("com.kuaishoudan.financer:id/edit_price"))
						.click();// 车辆价格
				Thread.sleep(2500);
				Runtime.getRuntime().exec(
						"adb -s " + devicename + " shell input text "
								+ ksd.getSqdk());
				Thread.sleep(500);
				driver.findElement(
						By.id("com.kuaishoudan.financer:id/edit_loan")).click();// 申请贷款
				Thread.sleep(2000);
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
				Thread.sleep(1000);
				try {
					List<AndroidElement> rates = driver.findElements(By
							.id("com.kuaishoudan.financer:id/text_select"));
					Thread.sleep(500);
					ksd.setRate(rates.get(0).getText());
					rates.get(0).click();// 费率选项

				} catch (java.lang.IndexOutOfBoundsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					driver.findElement(
							By.id("com.kuaishoudan.financer:id/toolbar_back"))
							.click();
				}

				Thread.sleep(500);

				Runtime.getRuntime().exec(
						"adb -s " + devicename + " shell input text "
								+ ksd.getRemark());
				Thread.sleep(200);
				driver.findElement(
						By.id("com.kuaishoudan.financer:id/edit_remark"))
						.click();// 备注
				Thread.sleep(1000);

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
			} catch (IOException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				System.out.println("IOException");
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

			actualstatue = AppUtil.upload(driver, ksd.getImgcount());
			// System.out.println("####"+actualstatue);
			// ksd.setStatue(actualstatue);
		}
		return ksd;
	}

	// 首页待办订单列表
	public static void clickItem(WebDriver driver, String name) {
		List<WebElement> items = driver.findElements(By.className("list_item"));// className("list_item")

		for (int i = 1; i <= items.size(); i++) {

			WebElement item = items.get(i - 1).findElement(
					By.xpath("//ul[@class='finance_list']/li[" + i
							+ "]/div[2]/div[3]/dl[6]/dd"));

			if (item.getText().equals(name)) {
				WebElement itempro = items.get(i - 1).findElement(
						By.xpath("//ul[@class='finance_list']/li[" + i
								+ "]/div[2]/div[3]/dl/dd"));
				// System.out.println(itempro.getText());
				if (itempro.getText().contains("平安租赁")) {
					item.click();

					break;
				}
			}
		}
	}
}
