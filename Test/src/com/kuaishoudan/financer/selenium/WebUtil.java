package com.kuaishoudan.financer.selenium;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.kuaishoudan.financer.bean.KSDCase;
import com.kuaishoudan.financer.util.IdCardGenerator;

public class WebUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String username = "liuhl@jizhicar.com";
		WebUtil wt = new WebUtil();
		WebDriver driver = null;
		try {
			driver = wt.getdriver();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//wt.login(driver, username);
	}

	public static WebDriver getdriver() throws MalformedURLException {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();//

		String baseUrl = "http://test.kuaishoudan.cn/";

		System.out.println("++++++++" + baseUrl);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		return driver;

	}

	// 登录
	public static void login(WebDriver driver, KSDCase ksd) {

		driver.findElement(By.id("login_userName")).sendKeys(ksd.getLoginemail());
		driver.findElement(By.id("login_passWord")).sendKeys(ksd.getPwd());
		driver.findElement(By.id("login_submit")).click();
		
	}

	// 待分配
	public static void testDFP(WebDriver driver,KSDCase ksd) {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.linkText("首页")).click();
		// 首页待办
		List<WebElement> items = driver
				.findElements(By
						.xpath("//ul[@class='todo_list']/li/div[@class='item_detail']/div[@class='last_person']"));
		for (int i = 0; i < items.size(); i++) {
			String name = items.get(i).getText();
			// System.out.println(name);
			if (name.contains(ksd.getLoginname())) {
				items.get(i).click();
				break;
			}

		}
		//
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		driver.findElement(By.linkText("分配任务")).click(); 
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// List<WebElement>
/*		driver.findElement(
				By.xpath("//div[@class='requestpayout_detail_btn_box']/div[2]"))
				.click();// 分配任务
*/
		List<WebElement> ss = driver.findElements(By.className("personName"));
		for (int i = 0; i < ss.size(); i++) {
			// System.out.println(i + ss.get(i).getText());
			WebElement fpr = ss.get(i);
			if (fpr.getText().contains(ksd.getLoginname())) {
				System.out.println(i);
				if (i < 25) {
					// System.out.println("<25");
					fpr.click();
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					driver.manage().timeouts()
							.implicitlyWait(8, TimeUnit.SECONDS);
					((JavascriptExecutor) driver)
							.executeScript("window.scrollTo(0, document.body.scrollHeight)"); // 向下滑动

					break;
				} else {
					// System.out.println(">25");
					driver.manage().timeouts()
							.implicitlyWait(8, TimeUnit.SECONDS);
					((JavascriptExecutor) driver)
							.executeScript("window.scrollTo(0, document.body.scrollHeight)"); // 向下滑动
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					fpr.click();
					break;
				}

			}

		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		// System.out.println(driver.findElement(By.id("allotBtnY")).getAttribute("value"));
		driver.findElement(By.id("allotBtnY")).click();// 分配按钮
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.findElement(By.id("delQDBtn")).click();// 分配提醒确定
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("success_allot")).click();// 确定按钮

	}

	// 已分配
	public static void testYFP(WebDriver driver,KSDCase ksd) {
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.findElement(By.linkText("客户")).click();
		driver.findElement(By.linkText("已分配")).click();
	
		clickItem(driver, ksd.getLoginname());
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
 	
		driver.findElement(By.linkText("开始录入")).click();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.linkText("确认申请")).click();
	
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	//	driver.findElement(By.id("requestpayout_apply")).click();// 确认申请

	}

	// 已录入
	public static void testYLR(WebDriver driver, KSDCase ksd) {
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.findElement(By.linkText("客户")).click();
		driver.findElement(By.linkText("已录入")).click();
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		clickItem(driver, ksd.getLoginname());
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
/*		driver.findElement(By.className("requestpayout_detail_btn_box"))
				.findElement(By.xpath("//a/div")).click();// 通知审核结果
*/	
		driver.findElement(By.linkText("通知审核结果")).click();
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.findElement(By.name("purchase_tax")).sendKeys(
				ksd.getPurchase_tax());// 购置税
		driver.findElement(By.name("gps_charge")).sendKeys(ksd.getGps_charge());// GPS费
		driver.findElement(By.name("insurance")).sendKeys(ksd.getInsurance());// 保险费
		driver.findElement(By.name("service_charge")).sendKeys(
				ksd.getService_charge());// 服务费
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.findElement(By.id("review_sub")).click();// 确定按钮

	}

	// 已通过
	public void testYTG(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.findElement(By.linkText("客户")).click();
		driver.findElement(By.linkText("已通过")).click();

		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
	}

	// 首页待办订单列表
	public static void clickItem(WebDriver driver, String name) {
		List<WebElement> items = driver.findElements(By.className("list_item"));// className("list_item")
		// List<WebElement>
		// items=driver.findElements(By.xpath("//div[@class='list_item']/div[2]/div[3]/dl[6]/dd"));//className("list_item")

		// System.out.println("项目数" + items.size());
		for (int i = 1; i <= items.size(); i++) {
			// System.out.println(i);
			WebElement item = items.get(i - 1).findElement(
					By.xpath("//ul[@class='finance_list']/li[" + i
							+ "]/div[2]/div[3]/dl[6]/dd"));
			// WebElement item= items.get(i);
			// System.out.println("==" + item.getText());
			if (item.getText().contains(name)) {
				// System.out.println("@" + item.getText());
				item.click();
				break;
			}
		}
	}

	// 已申请合同 （上传图片还没做） 新车 车架号不能重复
	public static void testYSQHT(WebDriver driver, KSDCase ksd) {
		driver.manage().timeouts().implicitlyWait(18, TimeUnit.SECONDS);
		driver.findElement(By.linkText("客户")).click();
		driver.findElement(By.linkText("合同管理")).click();
		driver.findElement(By.xpath("//div[@class='operation_category']/a"))
				.click();// 待出合同
		driver.manage().timeouts().implicitlyWait(18, TimeUnit.SECONDS);
		clickItem(driver, ksd.getLoginname());
		driver.manage().timeouts().implicitlyWait(18, TimeUnit.SECONDS);

		int height = driver.manage().window().getSize().height;
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,1250)"); // 向下滑动

		/*
		 * driver.findElement( By.xpath(
		 * "//div[@class='requestpayout_detail_container'][3]/div/div[4]/a[2]/div"
		 * )) .click();// 同意按钮
		 */
		driver.manage().timeouts().implicitlyWait(14, TimeUnit.SECONDS);
		// driver.navigate().refresh();
		driver.manage().timeouts().implicitlyWait(14, TimeUnit.SECONDS);
		// driver.findElement(By.partialLinkText("取消")).click();//取消按钮

		driver.findElement(By.linkText("同意")).click();// 同意按钮
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.findElement(By.id("carVin")).sendKeys(ksd.getVin());// 车架号

		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.findElement(By.className("file_upload_layer")).click();///上传按钮


		 String path = System.getProperty("user.dir");
		 System.out.println(path+"/20180401.jpg");
//		 driver.findElement(By.name("file")).sendKeys( System.getProperty("user.dir")+"/20180401.jpg");

		 try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 try {
			Runtime.getRuntime().exec("autoit1.exe");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
	
		 try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			driver.manage().timeouts().implicitlyWait(48, TimeUnit.SECONDS);
			driver.findElement(By.linkText("确认")).click();// 确认按钮
	
			
	

	}

	public static void logout(WebDriver driver) {
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.linkText("首页")).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.findElement(By.id("header_username")).click();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(55, TimeUnit.SECONDS);
		driver.findElement(By.linkText("退出登录")).click();
	}
}
