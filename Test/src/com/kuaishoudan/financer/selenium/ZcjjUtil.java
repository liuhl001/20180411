package com.kuaishoudan.financer.selenium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.kuaishoudan.financer.bean.KSDCase;
import com.kuaishoudan.financer.bean.RequestPayout;
import com.kuaishoudan.financer.dao.UserDaoImpl;
import com.kuaishoudan.financer.util.IdCardGenerator;

public class ZcjjUtil {

	public static KSDCase sqhtZCJJ(AppiumDriver<AndroidElement> driver,
			KSDCase ksd) {
		String actualstatue = "";
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		String titletext = driver
				.findElement(By.id("com.kuaishoudan.financer:id/toolbar_title"))
				.getText().trim();// 标题文本
		System.out.println(titletext);
		if ("客户".equals(titletext)) {
			driver.findElements(By.id("com.kuaishoudan.financer:id/text_name"))
					.get(0).click();// 首页列表
		}

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		driver.findElements(By.id("com.kuaishoudan.financer:id/text_product"))
				.get(0).click();// 常规产品列表

		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/tv_apply_compact")).click();// 申请合同
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		int gxs = driver.findElements(
				By.id("com.kuaishoudan.financer:id/check_group")).size();// 勾选数
		System.out.println("gxs" + gxs);
		driver.findElements(By.id("com.kuaishoudan.financer:id/check_group"))
				.get(gxs - 1).click();// 不安装 选择GPS安装方式
		
		driver.manage().timeouts().implicitlyWait(55, TimeUnit.SECONDS);
		driver.findElement(By.id("com.kuaishoudan.financer:id/edit_remark")).sendKeys("备注");
		driver.manage().timeouts().implicitlyWait(55, TimeUnit.SECONDS);
		AppUtil.swipeToUp(driver, 1000);
		driver.manage().timeouts().implicitlyWait(55, TimeUnit.SECONDS);
		driver.findElement(By.id("com.kuaishoudan.financer:id/btn_add"))
				.click();// 添加照片
		AppSPUtil.upload(driver);

		ksd.setStatue(	getActstatue(driver));//状态值
		List<Integer> list2=ksd.getImgtypes();
		if(ksd.getCartype()==0){
			list2.add(1049);
		}else{
		list2.add(1048);
		}	
		list2.add(1050);
		list2.add(1051); list2.add(1052);
		ksd.setImgtypes(list2);
 		return ksd;
	}

	public static KSDCase zcjjHTSQQK(AppiumDriver<AndroidElement> driver,WebDriver webdriver,KSDCase ksd,String devicename) {
		String actualstatue = "";
		WebUtil.login(webdriver, ksd );// 登录
		List<Integer> list = WebOrgan.getImge2(webdriver, ksd);
		WebUtil.logout(webdriver);
		List<Integer> list2=ksd.getImgtypes();
		int aa=0,countImg=0;
		 Collections.sort(list);  
		for(int i=0;i<list.size();i++){
			if(list.get(i)<9){
				List<Integer> list3=UserDaoImpl.getImgType(list.get(i)+7,list);
				list2.addAll(list3);
				aa=list3.size();
				countImg=aa+countImg;
			}
		}
	
		if(countImg==0){
			for(Integer type:list){
				if(type>99){
					list2.add(type);break;
				}
			}
		}
		ksd.setImgtypes(list2);
		System.out.println("$$$"+countImg);
		ksd.setImgcount(countImg);
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		String titletext = driver
				.findElement(By.id("com.kuaishoudan.financer:id/toolbar_title"))
				.getText().trim();// 标题文本
		System.out.println(titletext);
		if ("客户".equals(titletext)) {
			driver.findElements(By.id("com.kuaishoudan.financer:id/text_name"))
					.get(0).click();// 首页列表
		}
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_product"))
				.get(0).click();// 常规产品列表

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/btn_apply_request")).click();// 申请请款
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/text_request_pay_name"))
				.click();
		driver.findElement(By.id("com.kuaishoudan.financer:id/iv_is_show"))
				.click();// xia
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
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/tv_chekuan_shangpaifang"))
				.click();// 上牌方
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_select"))
				.get(0).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/tv_chekuan_diyafang"))
				.click();// 抵押方
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_select"))
				.get(0).click();
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		try {
			driver.findElement(By.id("com.kuaishoudan.financer:id/tv_chekuan_kouchuxiang")).sendKeys(""+ksd.getDeduction());//扣除款项

			Thread.sleep(500);
			AppUtil.swipeToUp(driver, 1000);// 向上滑动
			Thread.sleep(500);
		} catch (  InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
 	
		AppUtil.uploadQk(driver,ksd.getImgcount());
	//	RequestPayout requestPyout = ksd.getRequestpayout();
		/*try {
			AppUtil.testFd(driver, devicename,requestPyout);
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
				.click();// 信息确认按钮
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// actualstatue= AppUtil.getStatue(driver);
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
		actualstatue = ZcjjUtil.getActstatue(driver);//查值
		ksd.setStatue(actualstatue);
		return ksd;
	}

	public static Map<String, String> getSPname(
			AppiumDriver<AndroidElement> driver,KSDCase ksd) throws InterruptedException,
			IOException {
		Map<String, String> map = new HashMap<String, String>();
		;
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		String spname = "";
		String title = driver.findElement(
				By.id("com.kuaishoudan.financer:id/toolbar_title")).getText();
		if ("查看状态".equals(title)) {
			AppUtil.swipeToDown(driver, 1000);// 向下滑动
			System.out.println("下-----------" + title);

		} else {
			String titletext = driver
					.findElement(
							By.id("com.kuaishoudan.financer:id/toolbar_title"))
					.getText().trim();// 标题文本
			System.out.println(titletext);
			if ("客户".equals(titletext)) {
				driver.findElements(
						By.id("com.kuaishoudan.financer:id/text_name")).get(0)
						.click();// 首页列表
			}
			driver.findElements(
					By.id("com.kuaishoudan.financer:id/text_product")).get(0)
					.click();// 常规产品列表
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/toolbar_loan_status"))
					.click();// 状态审批流程

			// driver.findElement(By.id("com.kuaishoudan.financer:id/text_customer_look_status")).click();//查看进度
		}
		Thread.sleep(1000);
		List<AndroidElement> statueitems = driver.findElements(By
				.id("com.kuaishoudan.financer:id/ll_status"));
		for (int i = 0; i < statueitems.size(); i++) {
			String statue = statueitems
					.get(i)
					.findElement(
							By.id("com.kuaishoudan.financer:id/item_status"))
					.getText();
			System.out.println("@@" + statueitems.size());
			if ("正在处理".equals(statue)) {
				String name = statueitems
						.get(i)
						.findElement(
								By.id("com.kuaishoudan.financer:id/item_name"))
						.getText();
			//System.out.println(i+"!!!!"+name);
	
				String[] strs = name.split("-");
				if (strs[0].contains("BD")) {
					// ///////////////////////////////

					spname = strs[1];
					System.out.println("BD经理处理" + spname);
					map.put("name", spname);
					break;
				} else {
					String prename = statueitems
							.get(i + 1)
							.findElement(
									By.id("com.kuaishoudan.financer:id/item_name"))
							.getText();
					String[] strspre = prename.split("-");
					spname = strs[1] + "," + strspre[1];
					System.out.println("正在处理" + spname);
					map.put("name", strs[1]);
					map.put("prename", strspre[1]);
					break;
				}
			} else if ("放款审批/已放款".equals(statue)) {
				String name = statueitems
						.get(i)
						.findElement(
								By.id("com.kuaishoudan.financer:id/item_name"))
						.getText();
				String[] strs = name.split("-");
				spname = strs[1] + "," + ksd.getLoginname();
				map.put("name", strs[1]);
				map.put("prename", ksd.getLoginname());
			} else if ("回款结果/已回款".equals(statue)) {
				String name = statueitems
						.get(i)
						.findElement(
								By.id("com.kuaishoudan.financer:id/item_name"))
						.getText();
				String[] strs = name.split("-");
				spname = strs[1] + "," + ksd.getLoginname();
				map.put("name", strs[1]);
				map.put("prename", ksd.getLoginname());
			}
		}
		return map;
	}

	// 不出合同申请请款
	public static KSDCase testBCSQQK(AppiumDriver<AndroidElement> driver,WebDriver webdriver,
			KSDCase ksd,String devicename) {
		WebUtil.login(webdriver, ksd );// 登录
		List<Integer> list = WebOrgan.getImge2(webdriver, ksd);
		WebUtil.logout(webdriver);
		List<Integer> list2=ksd.getImgtypes(); 
		int aa=0,countImg=0;
		 Collections.sort(list);  
		for(int i=0;i<list.size();i++){
			if(list.get(i)<9){
			List<Integer> list3=UserDaoImpl.getImgType(list.get(i)+7,list);
			list2.addAll(list3);
			aa=list3.size();
				countImg=aa+countImg;
			}
		}
		if(countImg==0){
			for(Integer type:list){
				if(type>99){
					list2.add(type);break;
				}
			}
		}
		ksd.setImgtypes(list2);
		System.out.println(list2.size()+"$$$"+countImg);
		ksd.setImgcount(countImg);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		String titletext = driver
				.findElement(By.id("com.kuaishoudan.financer:id/toolbar_title"))
				.getText().trim();// 标题文本
		System.out.println(titletext);
		if ("客户".equals(titletext)) {
			driver.findElements(By.id("com.kuaishoudan.financer:id/text_name"))
					.get(0).click();// 首页列表
		}
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		driver.findElements(By.id("com.kuaishoudan.financer:id/text_product"))
				.get(0).click();// 常规产品列表
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

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
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/tv_chekuan_shangpaifang"))
				.click();// 上牌方
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_select"))
				.get(ksd.getRegisttype() - 1).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/tv_chekuan_diyafang"))
				.click();// 抵押方
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_select"))
				.get(ksd.getPledge() - 1).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		try {
			driver.findElement(By.id("com.kuaishoudan.financer:id/tv_chekuan_kouchuxiang")).sendKeys(""+ksd.getDeduction());//扣除款项

			Thread.sleep(500);
			AppUtil.swipeToUp(driver, 1000);// 向上滑动
			Thread.sleep(500);
		} catch (  InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	 	
		AppUtil.uploadQk(driver,ksd.getImgcount());
		
	//	RequestPayout requestPyout = ksd.getRequestpayout();
		/*try {
			AppUtil.testFd(driver, devicename,requestPyout);
	//		AppUtil.testDy(driver,devicename, requestPyout);
	//		AppUtil.testZx(driver,devicename, requestPyout);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
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
	String 	actualstatue = ZcjjUtil.getActstatue(driver);
	ksd.setStatue(actualstatue);
	return ksd;
	}

	// BD经理登录审批
	public static boolean loginBD(AppiumDriver<AndroidElement> driver,
			String username) {
		boolean flag = false;
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
	
		driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_back"))
				.click();// 返回
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_back"))
				.click();// 返回
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String titletext = driver
				.findElement(By.id("com.kuaishoudan.financer:id/toolbar_title"))
				.getText().trim();// 标题文本

		if ("贷款详情".equals(titletext)) {
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/toolbar_back")).click();// 返回
		}
		driver.manage().timeouts().implicitlyWait(18, TimeUnit.SECONDS);
	 titletext = driver
				.findElement(By.id("com.kuaishoudan.financer:id/toolbar_title"))
				.getText().trim();// 标题文本
		if(titletext.contains("客户—")){
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/toolbar_back")).click();// 返回
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		AppUtil.logout(driver);// 退出登录
		driver.findElement(By.id("com.kuaishoudan.financer:id/edit_account"))
				.clear();
		driver.findElement(By.id("com.kuaishoudan.financer:id/edit_password"))
				.clear();
		driver.findElement(By.id("com.kuaishoudan.financer:id/edit_account"))
				.sendKeys(username);
		driver.findElement(By.id("com.kuaishoudan.financer:id/edit_password"))
				.sendKeys("@123456");
		driver.findElement(By.id("com.kuaishoudan.financer:id/btn_login"))
				.click();
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_menu"))
				.click();// 菜单
		System.out
				.println("菜单选项数量"
						+ driver.findElements(
								By.id("com.kuaishoudan.financer:id/design_menu_item_text"))
								.size());
		driver.findElements(
				By.id("com.kuaishoudan.financer:id/design_menu_item_text"))
				.get(7).click();// 消息
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.findElements(By.id("com.kuaishoudan.financer:id/message_body"))
				.get(0).click();// 点进同意贷款详情
		driver.manage().timeouts().implicitlyWait(18, TimeUnit.SECONDS);

		driver.findElement(By.id("com.kuaishoudan.financer:id/btn_check_agree"))
				.click();// 同意按钮
		driver.manage().timeouts().implicitlyWait(18, TimeUnit.SECONDS);
		driver.findElement(By.id("com.kuaishoudan.financer:id/edit_reason")).sendKeys("同意");
		driver.manage().timeouts().implicitlyWait(18, TimeUnit.SECONDS);
		driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_finish"))
				.click();// 确定按钮
		driver.manage().timeouts().implicitlyWait(18, TimeUnit.SECONDS);
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/dialog_custom_confirm"))
				.click();// 提醒----确定按钮

		AppUtil.logout(driver);// 退出登录
		flag = true;
		return flag;

	}
	// 状态实际值
	public static String getActstatue(AppiumDriver<AndroidElement> driver) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AppUtil.swipeToDown(driver, 1000);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(18, TimeUnit.SECONDS);
		String actu = driver
				.findElements(
						By.id("com.kuaishoudan.financer:id/text_status")).get(0)
				.getText().trim();
		return actu;

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
		String statue = ZcjjUtil.getActstatue(driver);// 状态值
		return statue;

	}
	public static void sp6App(AppiumDriver<AndroidElement> driver,KSDCase ksd){
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		driver.findElements(By.id("com.kuaishoudan.financer:id/text_product"))
				.get(0).click();// 常规产品列表
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		driver.findElement(By.id("com.kuaishoudan.financer:id/btn_archive")).click();//归档
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
 
	//	driver.findElements(By.id("com.kuaishoudan.financer:id/check_group")).get(0).click();//当面交付
		driver.findElement(By.id("com.kuaishoudan.financer:id/tv_select_type")).click();//材料类型
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		driver.findElements(By.id("com.kuaishoudan.financer:id/tv_title")).get(2).click();//
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		driver.findElement(By.id("com.kuaishoudan.financer:id/cb_all")).click();
	/*	for(int i=0;i<ksd.getImgcount();i++){
			driver.findElements(By.id("com.kuaishoudan.financer:id/cb_check")).get(i).click();
		}*/
	//			
		AppUtil.swipeToUp(driver, 1000);
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		AppUtil.uploadQk(driver,ksd.getImgcount());
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		driver.findElement(By.id("com.kuaishoudan.financer:id/tv_toolbar_confirm")).click();//提交
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
	//	driver.findElement(By.id("com.kuaishoudan.financer:id/dialog_custom_confirm")).click();//是
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_back")).click();//返回
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_back")).click();//返回
		
	}
}
