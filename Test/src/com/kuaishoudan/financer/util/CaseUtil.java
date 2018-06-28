package com.kuaishoudan.financer.util;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import jxl.Cell;
import jxl.Range;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Alignment;
import jxl.write.Colour;
import jxl.write.Label;
import jxl.write.VerticalAlignment;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import com.kuaishoudan.financer.bean.*;
import com.kuaishoudan.financer.dao.UserDaoImpl;

public class CaseUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
	}

	public static Map<String,String> getCustomer(KSDCase ksd){
		  Map<String,String> map=new HashMap<String,String>();
			map.put("name",ksd.getUsername());
			map.put("status","1");
			map.put("phone", ksd.getPhone());
			map.put("id_type",	ksd.getIdentitytype()+"");
			map.put("address",	ksd.getAddress());
		  return map;
	}
	public static Map<String,String> getFinance(KSDCase ksd){
		  Map<String,String> map=new HashMap<String,String>();
	       DecimalFormat decimalFormat = new DecimalFormat("###################.###########"); 
		  map.put("name",	ksd.getUsername());
			map.put("status",""+UserDaoImpl.getstatus_id("已通过"));//statue"已分配"
			map.put("phone", ksd.getPhone());
			map.put("car_type",	ksd.getCartype()+"");
			map.put("brand_name",	ksd.getCarbrand());
			map.put("series_name",	ksd.getCarseries());
			map.put("product_name",	ksd.getProduct());
			map.put("car_price",  ""+ksd.getCarprice());//decimalFormat.format(ksd.getCarprice())
			System.out.println("@@"+ksd.getCarprice());
			map.put("loan_amount",""+ksd.getSqdk()	);
			map.put("pay_periods",	""+(ksd.getHkqs()*6+6));
			map.put("supplier_name",ksd.getSssh());
			map.put("remark",	ksd.getRemark());
			map.put("loan_type",	ksd.getQygr()+"");
			if(ksd.getQygr()==2){//企业
			map.put("business_name",ksd.getBusinessname()	);
			map.put("business_license",	ksd.getBusinessid());
			}
			map.put("rate",decimalFormat.format(Double.parseDouble((ksd.getRate().split("%")[0]))));
		//	map.put("vin", ksd.getVin());
			map.put("purchase_tax",decimalFormat.format(Double.parseDouble(ksd.getPurchase_tax())));
			map.put("gps_charge",decimalFormat.format(Double.parseDouble( ksd.getGps_charge())));
			map.put("insurance",decimalFormat.format(Double.parseDouble(ksd.getInsurance())));
			map.put("service_charge",decimalFormat.format(Double.parseDouble(ksd.getService_charge())));
			System.out.println("case="+ksd.getInsurance());
		  return map;
	}
	public static Map<String,String> getAdvance(KSDCase ksd){
		  Map<String,String> map=new HashMap<String,String>();
	       DecimalFormat decimalFormat = new DecimalFormat("###################.###########"); 
		  map.put("name",	ksd.getUsername());
			map.put("car_type",	ksd.getCartype()+"");
			map.put("series_name",	ksd.getCarseries());
			map.put("product_name",	ksd.getProduct());
			map.put("supplier_name",ksd.getSssh());
			map.put("vin", ksd.getVin().toUpperCase());//大写车架号
			map.put("purchase_tax",decimalFormat.format(Double.parseDouble(ksd.getPurchase_tax())));
	//		map.put("gps_charge",decimalFormat.format(Double.parseDouble( ksd.getGps_charge())));
			map.put("insurance",decimalFormat.format(Double.parseDouble(ksd.getInsurance())));
	//		map.put("service_charge",decimalFormat.format(Double.parseDouble(ksd.getService_charge())));
			map.put("deduction", decimalFormat.format(ksd.getDeduction()));
			double toalcharge=ksd.getSqdk()+Double.parseDouble(ksd.getPurchase_tax())+Double.parseDouble(ksd.getInsurance())-ksd.getDeduction();
			map.put("toalcharge", ""+toalcharge);
		  return map;
	}
	public static KSDCase getCaseByid(String caseid) {

		KSDCase ksd = new KSDCase();

		return ksd;
	}

	public static List<KSDCase> getCases(int n) {

		List<KSDCase> cells1 = new ArrayList<KSDCase>();

		return cells1;
	}

}
