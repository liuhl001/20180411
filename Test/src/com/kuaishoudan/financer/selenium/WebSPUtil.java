package com.kuaishoudan.financer.selenium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.kuaishoudan.financer.bean.KSDCase;
import com.kuaishoudan.financer.dao.UserDaoImpl;
import com.kuaishoudan.financer.util.IdCardGenerator;

public class WebSPUtil {

	public static void login2(WebDriver driver, String username, String pwd) {
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		driver.findElement(By.id("login_userName")).sendKeys(username);
		driver.findElement(By.id("login_passWord")).sendKeys(pwd);
		driver.findElement(By.id("login_submit")).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Assert.assertEquals("456", "456");
	}

	public static String nameToemail(String username) {
		String email = "";
		Map<String, String> tempMap = new HashMap<String, String>();
		tempMap.put("牛娜", "niun@jizhicar.com");
		tempMap.put("黄淑贤", "huangsx@jizhicar.com");
		tempMap.put("李倩杰", "liqj@jizhicar.com");
		tempMap.put("孔令星", "konglx@jizhicar.com");
		tempMap.put("辛颖", "xiny@jizhicar.com");
		tempMap.put("沈月", "sheny@jizhicar.com");
		tempMap.put("刘浩亮", "liuhl@jizhicar.com");
		tempMap.put("白正华", "baizh@jizhicar.com");
		for (String key : tempMap.keySet()) {
			// System.out.println("key= "+ key + " and value= " +
			// tempMap.get(key));
			if (username.contains(key)) {
				email = tempMap.get(key);
				// System.out.println(email);

			}
		}
		return email;
	}

	// 请款审批同意专员

	public static boolean testSP1(WebDriver driver, String email, String itename,KSDCase ksd) {
		// String username="niun@jizhicar.com";
		boolean flag = false;
	
		if(email.equals(ksd.getLoginemail())||email.equals("")){
			login2(driver, ksd.getLoginemail(),ksd.getPwd());
			driver.findElement(By.linkText("客户")).click();
			driver.findElement(By.linkText("请款管理")).click();
			clickItemorder(driver, itename);
	
			driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);

			driver.findElement(By.linkText("确认提交")).click();
			driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
			driver.findElement(By.name("remark")).sendKeys("同意");
			driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
			driver.findElement(By.id("confirm_sub_t")).click();
			
		}else{
			login2(driver, email, "@123456");
		clickItem(driver, itename);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);

		driver.findElement(By.linkText("同意")).click();
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		driver.findElement(By.name("remark")).sendKeys("同意");
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		driver.findElement(By.id("argee_sub")).click();// 确认
		flag = true;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		WebUtil.logout(driver);
		return flag;
	}

	// 请款审批同意数据运营

	public static boolean testSP2(WebDriver driver, String email, String itename) {
		// String username = "huangsx@jizhicar.com";
		boolean flag = false;
		login2(driver, email, "@123456");

		clickItem(driver, itename);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		driver.manage().timeouts().implicitlyWait(23, TimeUnit.SECONDS);
	WebElement agree=	driver.findElement(
				By.xpath("//div[@class='details_content']/div[2]/div/a"));
		if(agree.getText().equals("同意")){
			agree.click();// 同意
			driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
			driver.findElement(By.name("remark")).sendKeys("同意");
			driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
			driver.findElement(By.id("argee_sub")).click();// 确认
		}else{
			agree.click();// 确认提交
			driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
			driver.findElement(By.id("risk_type1")).click();
			driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
			driver.findElement(By.name("remark")).sendKeys("同意");
			driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//div[@class='cashed_mark']/div/a")).click();//确认
		}
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

	// 请款审批审核组长

	public static boolean testSP3(WebDriver driver, String email, String itename) {
		// String username = "xiny@jizhicar.com";
		boolean flag = false;
		login2(driver, email, "@123456");

		clickItem(driver, itename);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		driver.manage().timeouts().implicitlyWait(33, TimeUnit.SECONDS);
			driver.findElement(By.linkText("同意")).click();
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		driver.findElement(By.id("content")).sendKeys("同意");
		driver.findElement(By.id("argee_sub")).click();// 确认
		flag = true;
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebUtil.logout(driver);
		return flag;
	}

	// 财务专员 审批

	public static boolean testSP4(WebDriver driver, String email, String itename,KSDCase ksd) {
		// / String username = "sheny@jizhicar.com";
		boolean flag = false;
		login2(driver, email, "@123456");

		clickItem(driver, itename);
		int height = driver.manage().window().getSize().height;
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,"
				+ (height * 2 + 500) + ")"); // 向下滑动
		WebShop.clickShop(driver, ksd);

		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		Select userSelect = new Select(
				driver.findElement(By.id("orderby_type")));
		userSelect.selectByVisibleText("按贷款时间倒序排列");
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		driver.findElement(By.linkText("筛选")).click();
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);

		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,"
		+ (height * 2 + 200) + ")"); // 向下滑动

		driver.findElement(By.linkText("同意")).click();
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		driver.findElement(By.name("remark")).sendKeys("同意");
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		
		driver.findElement(By.linkText("确认")).click();
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		driver.findElement(By.className("cancel")).click();// 稍后再说

		flag = true;
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebUtil.logout(driver);
		return flag;
	}

	// 财务专员 审批-已放款

	public static boolean testSP5(WebDriver driver, String email, String itename,KSDCase ksd) {
		// String username = "sheny@jizhicar.com";
		boolean flag = false;
		login2(driver, email, "@123456");
		driver.findElement(By.linkText("客户")).click();
		driver.findElement(By.linkText("回款管理")).click();
		driver.findElement(By.linkText("待回款")).click();
		clickItemorder(driver, ksd.getLoginname());
		int height = driver.manage().window().getSize().height;
		// System.out.println("height" + height);
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,"
				+ (height * 2 +200) + ")"); // 向下滑动
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
	/*	driver.findElements(
				By.xpath("//div[@class='requestpayout_detail_btn_box']/a/div"))
				.get(0).click();// 确认回款
*/		// driver.findElement(By.xpath("//div[@class='requestpayout_detail_btn_box']/a/div")).click();//确认回款
		driver.findElement(By.linkText("确认回款")).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
	//	driver.findElement(By.linkText("确认回款")).click();//===
		driver.findElement(By.className("confirm")).click();
		flag = true;
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebUtil.logout(driver);
		return flag;
	}

	// 财务专员 审批-已回款-归档待处理

	public static KSDCase testSP6(WebDriver driver,  KSDCase ksd) {
		// String username = "liuhl@jizhicar.com";
		boolean flag = false;
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		WebUtil.login(driver, ksd);// 登录
		List<Integer> list = WebOrgan.getImge3(driver, ksd);
		List<Integer> list2=	ksd.getImgtypes();
			/*int aa=0,countImg=0;
		for(int i=0;i<list.size();i++){
			if(list.get(i)<9){
				List<Integer> list3=UserDaoImpl.getImgType(list.get(i)+14,list);
				for(int p=0;p<list3.size();p++){
				System.out.println("!!!!!!!!"+list3.get(p));}
				list2.addAll(list3);
				aa=list3.size();
				countImg=aa+countImg;
			}
		}
		if(countImg==0){
			for(Integer type:list){
				if(type>99){
					list2.add(type);countImg=1;
					break;
				}
			}
		}*/
	
	//	System.out.println("********$$$"+countImg);
		

		
		
		for(int i=0;i<list.size();i++){
			if(list.get(i)<99){
				list.remove(i);
				i--;
			}
		}
		
		ksd.setImgcount(list.size());
		list2.addAll(list);
	
		for(int i=0;i<list2.size();i++){
			if(list2.get(i)<99){
				list2.remove(i);			
				i--;
			}
		}
		 Collections.sort(list2); ;
		ksd.setImgtypes(list2);
		WebUtil.logout(driver);
		

		return ksd;
	}
	
	// 财务专员 审批-已回款-归档待处理

		public static KSDCase testSP7(WebDriver driver,  KSDCase ksd) {
			// String username = "liuhl@jizhicar.com";
			boolean flag = false;
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			login2(driver, ksd.getLoginemail(), ksd.getPwd());
			driver.findElement(By.linkText("客户")).click();
			driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//ul[@class='slide_nav_bar']/li[6]/a"))
					.click();// 归档管理

			driver.manage().timeouts().implicitlyWait(23, TimeUnit.SECONDS);
	//		driver.findElement(By.linkText("待处理")).click();
			clickItemorder(driver, ksd.getLoginname());

			driver.manage().timeouts().implicitlyWait(23, TimeUnit.SECONDS);

			driver.findElement(By.linkText("同意")).click();
			driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
			driver.findElement(By.name("remark")).sendKeys("同意");//归档确认
			driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
			driver.findElement(By.linkText("确认")).click();//归档确认

			flag = true;
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			WebUtil.logout(driver);
			

			return ksd;
		}

	// 审批待办

	public static void clickItem(WebDriver driver, String name) {
		List<WebElement> items = driver.findElements(By.className("list_item"));// className("list_item")
		// List<WebElement>
		// items=driver.findElements(By.xpath("//div[@class='list_item']/div[2]/div[3]/dl[6]/dd"));//className("list_item")

		// System.out.println("项目数" + items.size());
		for (int i = 1; i <= items.size(); i++) {
			// System.out.println(i);
			WebElement item = items.get(i - 1).findElement(
					By.xpath("//ul[@class='todo_list']/li[" + i + "]/div/div"));
			// WebElement item= items.get(i);
			// System.out.println("==" + item.getText());
			if (item.getText().contains(name)) {
				// System.out.println("@" + item.getText());
				item.click();
				break;
			}
		}
	}

	// 待办订单列表
	public static void clickItemorder(WebDriver driver, String name) {
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

}
