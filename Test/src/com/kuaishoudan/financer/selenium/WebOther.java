package com.kuaishoudan.financer.selenium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.kuaishoudan.financer.bean.KSDCase;
import com.kuaishoudan.financer.dao.UserDaoImpl;
import com.kuaishoudan.financer.selenium.AppSPUtil;
import com.kuaishoudan.financer.selenium.AppUtil;
import com.kuaishoudan.financer.selenium.WebSPUtil;
import com.kuaishoudan.financer.selenium.WebShop;
import com.kuaishoudan.financer.selenium.WebUtil;
import com.kuaishoudan.financer.util.CaseUtil;
import com.kuaishoudan.financer.util.RandomValue;

/**
 * web端 转交
 * 
 * @author apple
 * 
 *         待办事项--找到相应的item，进行点击，找到转交，找人，再进行转交 app端 客户 --进件--二手车 同 新车 不出合同 数据运营
 *         审核组长 财务专员
 * 
 *         企业--进件 出合同 新车 同 二手车 不出合同 请款审批专员 数据运营 审核组长 财务专员
 */
public class WebOther {

	// 请款审批同意专员

	public static boolean testSP1(WebDriver driver, String email,
			String itename, KSDCase ksd) {
		// String username="niun@jizhicar.com";
		boolean flag = false;
		WebSPUtil.login2(driver, email, "@123456");

		WebSPUtil.clickItem(driver, itename);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);

		driver.findElement(By.linkText("转交他人")).click();
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		testZjtr(driver,email,itename);
		WebUtil.logout(driver);
		return flag;
	}

	// 请款审批同意数据运营

	public static boolean testSP2(WebDriver driver, String email, String itename) {
		// String username = "huangsx@jizhicar.com";
		boolean flag = false;
		WebSPUtil.login2(driver, email, "@123456");

		WebSPUtil.clickItem(driver, itename);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		WebElement agree = driver.findElement(By
				.xpath("//div[@class='details_content']/div[2]/div/a"));
		
 
		if (agree.getText().equals("同意")) {
			driver.findElement(By.linkText("转交他人")).click();
			driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
			testZjtr(driver,email,itename);
		} else {
			agree.click();// 确认提交
			driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
			driver.findElement(By.id("risk_type1")).click();
			driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
			driver.findElement(By.name("remark")).sendKeys("同意");
			driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//div[@class='cashed_mark']/div/a"))
					.click();// 确认
		}
		flag = true;
	
		WebUtil.logout(driver);
		return flag;
	}
	// 请款审批审核组长

	public static boolean testSP3(WebDriver driver, String email, String itename) {
		// String username = "xiny@jizhicar.com";
		boolean flag = false;
		WebSPUtil.login2(driver, email, "@123456");

		WebSPUtil.clickItem(driver, itename);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
			driver.findElement(By.linkText("转交他人")).click();
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
	
		testZjtr(driver,email,itename);
		flag = true;

		WebUtil.logout(driver);
		return flag;
	}
	
	
	public static boolean testSP4(WebDriver driver, String email, String itename,KSDCase ksd) {
		// / String username = "sheny@jizhicar.com";
		boolean flag = false;
		WebSPUtil.login2(driver, email, "@123456");

		WebSPUtil.clickItem(driver, itename);
		int height = driver.manage().window().getSize().height;
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,"
				+ (height * 2 + 500) + ")"); // 向下滑动
		WebShop.clickShop(driver, ksd);

	
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		Select userSelect = new Select(
				driver.findElement(By.id("orderby_type")));
		userSelect.selectByVisibleText("按贷款时间倒序排列");
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		driver.findElement(By.linkText("筛选")).click();
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);

		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,"
		+ (height * 2 + 200) + ")"); // 向下滑动

		driver.findElement(By.linkText("转交他人")).click();
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		testZjtr(driver,email,itename);
		flag = true;
	 
		WebUtil.logout(driver);
		return flag;
	}
	public static void testZjtr(WebDriver driver, String email, String itename) {
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//div[@class='content']/ul/li[14]"))
				.click();// 所有权限 这个没有id，有class
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<WebElement> ens = driver.findElements(By
				.className("employee_name"));
		for (WebElement en : ens) {
			if (en.getText().equals("刘浩亮")) {
				en.click();
				System.out.println(en.getText());
				break;
			}
		}
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		driver.findElement(By.id("transfer-remark")).sendKeys("转交原因");
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		driver.findElement(By.id("dialog_confirm")).click();// 确认
		
		try {
			Thread.sleep(13000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
