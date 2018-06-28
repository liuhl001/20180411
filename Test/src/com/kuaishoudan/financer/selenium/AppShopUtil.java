package com.kuaishoudan.financer.selenium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import com.kuaishoudan.financer.bean.ShopBeanCase;

public class AppShopUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// 新建商户以及备案信息
	public static ShopBeanCase createShop(AppiumDriver<AndroidElement> driver,
			ShopBeanCase shopBeanCase, String devicename) throws Exception {

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_menu"))
				.click();// 点击出左边的弹框
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		driver.findElements(
				By.id("com.kuaishoudan.financer:id/design_menu_item_text"))
				.get(1).click();// 点击商户
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/tv_create_supplier"))
				.click();// 新建商户
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/edit_supplier_name"))
				.sendKeys(shopBeanCase.getShopname());// 名称
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/text_supplier_yewu_type"))
				.click();// 点击业务类型
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		if (shopBeanCase.getBusinessType() == 0) {
			driver.findElements(
					By.id("com.kuaishoudan.financer:id/text_select")).get(0)
					.click();// 新车
		} else if (shopBeanCase.getBusinessType() == 1) {
			driver.findElements(
					By.id("com.kuaishoudan.financer:id/text_select")).get(1)
					.click();// 二手车
		} else if (shopBeanCase.getBusinessType() == 2) {
			driver.findElements(
					By.id("com.kuaishoudan.financer:id/text_select")).get(2)
					.click();// 新车&二手车
		}
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/text_supplier_type"))
				.click();// 点击店铺类型
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		if (shopBeanCase.getBusinessType() == 0) {
			driver.findElements(
					By.id("com.kuaishoudan.financer:id/text_select")).get(0)
					.click();// 二级店
		} else if (shopBeanCase.getBusinessType() == 1) {
			driver.findElements(
					By.id("com.kuaishoudan.financer:id/text_select")).get(1)
					.click();// 4s店
		} else if (shopBeanCase.getBusinessType() == 2) {
			driver.findElements(
					By.id("com.kuaishoudan.financer:id/text_select")).get(2)
					.click();// 直营店
		} else if (shopBeanCase.getBusinessType() == 3) {
			driver.findElements(
					By.id("com.kuaishoudan.financer:id/text_select")).get(3)
					.click();// 担保公司
		}
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/text_supplier_address"))
				.click();// 地址点击
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);

		driver.findElement(By.id("com.kuaishoudan.financer:id/ll_search"))
				.click();
		driver.findElement(By.id("com.kuaishoudan.financer:id/input_edittext"))
				.sendKeys("北京南");
		driver.findElements(By.id("com.kuaishoudan.financer:id/tv_address"))
				.get(0).click();
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/toolbar_right_tv")).click();// 地图选址
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		// 找到封面图相对应的textView，进行点击
		WebElement supp = driver.findElement(By
				.id("com.kuaishoudan.financer:id/btn_supplier_cover"));
		supp.findElements(By.className("android.widget.ImageView")).get(1)
				.click();

		driver.findElement(
				By.id("com.kuaishoudan.financer:id/dialog_photo_select_btn_gallery"))
				.click();// 从相册中选择
		driver.findElements(By.id("com.kuaishoudan.financer:id/iv_thumb"))
				.get(0).click();// 选择图片
		driver.findElement(By.id("com.kuaishoudan.financer:id/btn_ok")).click();// 点击确定

		driver.findElement(
				By.id("com.kuaishoudan.financer:id/text_supplier_jiesuan_type"))
				.click();// 款项结算方式选择
		if (shopBeanCase.getPaymentMethod() == 0) {
			driver.findElements(
					By.id("com.kuaishoudan.financer:id/text_select")).get(0)
					.click();
		} else if (shopBeanCase.getPaymentMethod() == 1) {
			driver.findElements(
					By.id("com.kuaishoudan.financer:id/text_select")).get(1)
					.click();
		}

		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
 
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/et_company_count")).sendKeys(shopBeanCase.getCompanyNumber());// 公司人数
		Thread.sleep(1000);
		AppUtil.swipeToUp(driver, 1000);// 向上滑动
		Thread.sleep(1000);
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/text_mouth_sales")).click();// 月销量
		if (shopBeanCase.getMouthSale() == 0) {
			driver.findElements(
					By.id("com.kuaishoudan.financer:id/text_select")).get(0)
					.click();
		} else if (shopBeanCase.getMouthSale() == 1) {
			driver.findElements(
					By.id("com.kuaishoudan.financer:id/text_select")).get(1)
					.click();
		} else if (shopBeanCase.getMouthSale() == 2) {
			driver.findElements(
					By.id("com.kuaishoudan.financer:id/text_select")).get(2)
					.click();
		} else if (shopBeanCase.getMouthSale() == 3) {
			driver.findElements(
					By.id("com.kuaishoudan.financer:id/text_select")).get(3)
					.click();
		} else if (shopBeanCase.getMouthSale() == 4) {
			driver.findElements(
					By.id("com.kuaishoudan.financer:id/text_select")).get(4)
					.click();
		}

		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.findElement(By.id("com.kuaishoudan.financer:id/edit_name"))
				.sendKeys(shopBeanCase.getName());// 姓名

		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.findElement(By.id("com.kuaishoudan.financer:id/edit_phone")).sendKeys(shopBeanCase.getPhone())
				 ;// 手机
 

		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.findElement(By.id("com.kuaishoudan.financer:id/text_position"))
		.click();// 职务

		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		// 选择职务
		if (shopBeanCase.getDuty() == 0) {
			driver.findElements(
					By.id("com.kuaishoudan.financer:id/text_select")).get(0)
					.click();
		} else if (shopBeanCase.getDuty() == 1) {
			driver.findElements(
					By.id("com.kuaishoudan.financer:id/text_select")).get(1)
					.click();
		} else if (shopBeanCase.getDuty() == 2) {
			driver.findElements(
					By.id("com.kuaishoudan.financer:id/text_select")).get(2)
					.click();
		} else if (shopBeanCase.getDuty() == 3) {
			driver.findElements(
					By.id("com.kuaishoudan.financer:id/text_select")).get(3)
					.click();
		}
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		// 联系人
		// 不是必选的没写****************************************************************************************************************
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/toolbar_right_tv")).click();// 点击确认
		driver.manage().timeouts().implicitlyWait(18, TimeUnit.SECONDS);
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/dialog_custom_confirm"))
				.click();// 马上填写备案信息
		// 备案信息 填写
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/text_supplier_record_type"))
				.click();// 证件类型
		// 选择证件类型
		if (shopBeanCase.getDocumentType() == 0) {
			driver.findElements(
					By.id("com.kuaishoudan.financer:id/text_select")).get(0)
					.click();
		} else if (shopBeanCase.getDocumentType() == 1) {
			driver.findElements(
					By.id("com.kuaishoudan.financer:id/text_select")).get(1)
					.click();
		}

		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/et_record_number")).sendKeys(shopBeanCase.getBusinessLicense());// 证件号码
																				// *****
		Thread.sleep(2000);
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/text_supplier_account_type"))
				.click();// 账户类型
		// 账户类型
		// 选择账户类型
		if (shopBeanCase.getAccountType() == 0) {
			driver.findElements(
					By.id("com.kuaishoudan.financer:id/text_select")).get(0)
					.click();
		} else if (shopBeanCase.getAccountType() == 1) {
			driver.findElements(
					By.id("com.kuaishoudan.financer:id/text_select")).get(1)
					.click();
		} else if (shopBeanCase.getAccountType() == 2) {
			driver.findElements(
					By.id("com.kuaishoudan.financer:id/text_select")).get(2)
					.click();
		} else if (shopBeanCase.getAccountType() == 3) {
			driver.findElements(
					By.id("com.kuaishoudan.financer:id/text_select")).get(3)
					.click();
		}
		// 账户用途
		driver.findElement(By.id("com.kuaishoudan.financer:id/cb_chekuan"))
				.click();
		driver.findElement(By.id("com.kuaishoudan.financer:id/cb_fandian"))
				.click();
		driver.findElement(By.id("com.kuaishoudan.financer:id/cb_zafei"))
				.click();
		// 账户名

		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/edit_supplier_account_name"))
				.sendKeys(shopBeanCase.getAccountName());
		// 开户行

		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/edit_supplier_open_bank"))
				.sendKeys(shopBeanCase.getOpeningBack());
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
  
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/edit_supplier_bank_card")).sendKeys(shopBeanCase.getBackCardNumber())
			 ;
		AppUtil.swipeToUp(driver, 1000);// 向上滑动
		// 上传图片
		driver.findElement(By.id("com.kuaishoudan.financer:id/iv_add")).click();
		upload(driver, shopBeanCase.getImageCount());

		// 备案信息提交
		driver.manage().timeouts().implicitlyWait(58, TimeUnit.SECONDS);
		driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_confirm"))
				.click();
		driver.manage().timeouts().implicitlyWait(58, TimeUnit.SECONDS);

		driver.findElement(
				By.id("com.kuaishoudan.financer:id/dialog_custom_confirm"))
				.click();
		Thread.sleep(2000);
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/dialog_custom_confirm"))
				.click();
		System.out.println("&&&&&&&&&&&&&&&&&");
		return shopBeanCase;
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

		try {
			/*
			 * driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			 * driver.findElement(By.id("com.kuaishoudan.financer:id/btn_add"))
			 * .click();// 上传照片
			 */
			Thread.sleep(500);
			driver.manage().timeouts().implicitlyWait(48, TimeUnit.SECONDS);
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/dialog_photo_select_btn_gallery"))
					.click();// 从相册选择
			driver.findElements(By.id("com.kuaishoudan.financer:id/iv_thumb"))
					.get(0).click();// 添加图片（身份证）
			for (int i = 1; i < imgcount; i++) {
				driver.findElements(
						By.id("com.kuaishoudan.financer:id/iv_thumb")).get(i)
						.click();// 添加图片 银行卡，展厅，营业执照，联系人名片，等等
				driver.findElements(
						By.id("com.kuaishoudan.financer:id/iv_thumb")).get(i)
						.click();// 添加图片（驾驶证）

			}

			driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
			//
			driver.findElement(By.id("com.kuaishoudan.financer:id/btn_ok"))
					.click();// 两种证上传——确定按钮
			Thread.sleep(5000);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		} catch (org.openqa.selenium.WebDriverException e) {
			System.out.println(e);
			driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
			driver.findElement(By.id("com.kuaishoudan.financer:id/iv_back"))
					.click();// 返回
			driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/dialog_custom_confirm"))
					.click();// 是
			driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
			// driver.findElement(
			// By.id("com.kuaishoudan.financer:id/iv_back")).click();// 从客户页面返回
		}

		return acstatue;
	}

}
