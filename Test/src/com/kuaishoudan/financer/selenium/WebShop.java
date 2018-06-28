package com.kuaishoudan.financer.selenium;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.kuaishoudan.financer.bean.KSDCase;
import com.kuaishoudan.financer.util.RandomValue;

public class WebShop {

	/**
	 * 供应商机构管理
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {

			WebDriver driver = WebUtil.getdriver();
			KSDCase ksd = RandomValue.getRandom();
			ksd.setProduct("中安金控-那家店");// qita22-其他22产品1
			ksd.setCartype(1);
			ksd.setSssh("快快");
			WebUtil.login(driver, ksd );// 登录
			test1(driver, ksd);
			// WebUtil.logout(driver);
			Thread.sleep(25000);
			driver.quit();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void test1(WebDriver driver, KSDCase ksd)
			throws MalformedURLException, InterruptedException {
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		driver.findElement(By.linkText("商户")).click();
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//div[@class='operation_category']/a[2]"))
				.click();// 已通过
		String sss = ksd.getProduct().split("-")[0];

		clickShop(driver,ksd);
		driver.findElement(By.className("edit_sm_btn")).click();// 编辑

		// driver.navigate().refresh();
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		int height = driver.manage().window().getSize().height;
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,"
				+ (height + 400) + ")"); // 向下滑动
		Select userSelect = new Select(
				driver.findElement(By.id("businessCity")));

		userSelect.selectByVisibleText("天津市");

		driver.findElement(By.linkText("确定")).click();
		driver.findElement(By.id("businessAddress")).click();
		driver.findElement(By.id("mapSearch")).sendKeys("天津站");
		Thread.sleep(1000);
		List<WebElement> addr_names = driver.findElements(By
				.className("addr_name"));// 一
		System.out.println(addr_names.size());
		for (WebElement name : addr_names) {
			if (name.getText().equals("天津站")) {
				System.out.println(name);
				name.click();
				break;
			}
		}
		Thread.sleep(500);
		driver.findElement(By.className("requireTrue")).click();
		driver.findElement(By.id("isSign1")).click();// 是

		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,"
				+ (height * 12 + 800) + ")"); // 向下滑动

		Thread.sleep(2000);
		// driver.findElement(By.linkText("保存")).click();

	}

	public static void clickShop(WebDriver driver, KSDCase ksd){
		List<WebElement> names = driver.findElements(By
				.xpath("//div[@class='merchants_list']/table/tbody/tr/td"));

		for (WebElement name : names) {

			if (name.getText().equals(ksd.getSssh())) {
				name.click();
				break;
			}
		}
	}
}
