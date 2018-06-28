package com.kuaishoudan.financer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kuaishoudan.financer.bean.Finance;
import com.kuaishoudan.financer.bean.FinanceAdvence;
import com.kuaishoudan.financer.bean.KSDCase;
import com.kuaishoudan.financer.util.DBUtil;
import com.kuaishoudan.financer.util.RandomValue;

public class UserDaoImpl {

	public static void main(String[] args) {

		/*
		 * Finance f=UserDaoImpl.getFinance("71238");
		 * System.out.println(f.getFinanceid
		 * ()+f.getUsername()+f.getBrandid()+f.getRate()+f.getProductname());
		 */
		// FinanceAdvence f=UserDaoImpl.getAdvence("1062474");
		// System.out.println(f.getFinanceid()+f.getUsername());
		/*
		 * int a=gethave_system("易鑫融资租赁"); System.out.println(a);
		 */
		/*
		 * KSDCase ksd=RandomValue.getRandom();; ksd.setIdentitytype(1);
		 * ksd.setIdentitynum("620921195909158870"); Map<String,String> map=
		 * getCustomer(ksd);
		 * 
		 * System.out.println(map.get("phone")+" "+map.get("address")+map.get(
		 * "status"));
		 */

	/*	KSDCase ksd = getCustomer_KSD("弓心");
		System.out.println("@#$" + ksd.getIdentitytype());*/
	/*	List<Integer> list1=new ArrayList<Integer>();
		list1.add(100);list1.add(103);
		int aa=getImgType(0+1,list1);
System.out.println(aa);*/
		KSDCase ksd = RandomValue.getRandom();;
		ksd.setIdentitynum("1528967079239");
		ksd.setIdentitytype(1);
	//	getLoanname(ksd);
		
		int aa=getRisk_type(ksd);
		System.out.println("@@@"+aa);
	}

	public static KSDCase getCustomer_KSD(String name) {

		KSDCase ksd = null;
		String sql = "select * from tb_customer where name=? order by id desc limit 1 ; ";
		DBUtil util = new DBUtil();
		Connection conn = util.openConnection();
		try {

			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, name);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				ksd = new KSDCase();

				ksd.setUsername(rs.getString("name"));
				ksd.setPhone(rs.getString("phone"));
				ksd.setAddress("address2");
				int idtype = rs.getInt("id_type");
				ksd.setIdentitytype(idtype);
				if (idtype == 1) {
					ksd.setIdentitynum(rs.getString("id_num"));// 身份证.
				} else {
					ksd.setJgid(rs.getString("id_num"));// 军官证
				}

				/*
				 * ksd.setCarbrand(rs.getString("brand_name"));//车辆类
				 * 
				 * ksd.setCarseries(rs.getString("series_name"));//车系类
				 * 
				 * ksd.setCarprice(rs.getDouble("car_price"));
				 * 
				 * ksd.setSqdk(rs.getDouble("loan_amount"));// 申请贷款
				 * ksd.setProduct(rs.getString("product_name"));
				 * 
				 * int loan_type= rs.getInt("loan_type");
				 * ksd.setQygr(loan_type); if(loan_type==2){
				 * ksd.setBusinessname("qiyemc");// 企业名称
				 * ksd.setBusinessid("yingyezzh");// 企业执照 }
				 * ksd.setCartype(rs.getInt("car_type"));// 0新车 1 二手车
				 * ksd.setHkqs(rs.getInt("pay_periods"));// 融资期限
				 * ksd.setRemark("beizhu");// 备注
				 * ksd.setPurchase_tax(rs.getString("purchase_tax"));// 购置税
				 * ksd.setGps_charge(rs.getString("gps_charge"));// gps费
				 * ksd.setInsurance(rs.getString("insurance"));// 保险费
				 * ksd.setService_charge(rs.getString("service_charge"));// 服务费
				 * ksd.setVin(rs.getString("vin"));// 车架号
				 * ksd.setRegisttype(rs.getInt("registtype"));// 上牌方1,2,3
				 * ksd.setPledge(rs.getInt("pledge"));// 抵押方1,2,3
				 * ksd.setImgcount(4);// 图片数量1,2,3
				 * 
				 * ksd.setSssh(rs.getString("supplier_name"));//所属商户
				 * ksd.setStatue(rs.getString("status"));
				 * ksd.setRate(rs.getString("rate"));//费率 //
				 * f.setOrganizationname(rs.getString("organization_name"));
				 * ksd.setPurchase_tax(rs.getString("purchase_tax"));
				 * ksd.setGps_charge(rs.getString("gps_charge"));
				 * ksd.setInsurance(rs.getString("insurance"));
				 * ksd.setService_charge(rs.getString("service_charge"));
				 */

			}
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
		return ksd;
	}

	public static FinanceAdvence getAdvence(String finance_id) {

		FinanceAdvence f = null;
		String sql = " select *from tb_finance_advance where  finance_id= ?  order by id desc; ";
		DBUtil util = new DBUtil();
		Connection conn = util.openConnection();
		try {

			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, finance_id);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				f = new FinanceAdvence();
				f.setPledgecity(rs.getString("pledge_city"));
				f.setPledgecityid(rs.getInt("pledge_city_id"));
				f.setFinanceid(rs.getInt("finance_id"));
				f.setUsername(rs.getString("user_name"));
				f.setTotalcharge(rs.getDouble("total_charge"));

				f.setSuppliername(rs.getString("supplier_name"));
				f.setPayname(rs.getString("pay_name"));
				f.setPayaccount(rs.getString("pay_account"));
				f.setPayopenbank(rs.getString("pay_open_bank"));
				f.setPurchasetax(rs.getDouble("purchase_tax"));
				f.setGpscharge(rs.getDouble("gps_charge"));
				f.setInsurance(rs.getDouble("insurance"));
				f.setServicecharge(rs.getDouble("service_charge"));

			}
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
		return f;
	}

	public static int gethave_system(String organization_name) {
		int have_system = 1;

		String sql = " select min( have_system) from tb_organization where name =? and company_id=1000 ";
		DBUtil util = new DBUtil();
		Connection conn = util.openConnection();
		try {

			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, organization_name);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				have_system = rs.getInt(1);

			}
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
		return have_system;
	}

	public static Map<String, String> getCustomer(KSDCase ksd) {
		String ff = "";

		String sql = " select * from tb_customer where id_num=?; ";
		DBUtil util = new DBUtil();
		Connection conn = util.openConnection();
		Map<String, String> map = new HashMap<String, String>();
		try {

			PreparedStatement pstmt = conn.prepareStatement(sql);
			if (ksd.getIdentitytype() == 1) {
				pstmt.setString(1, ksd.getIdentitynum());
			} else if (ksd.getIdentitytype() == 2) {
				pstmt.setString(1, ksd.getJgid());
			}
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				map.put("name", rs.getString("name"));
				map.put("status", rs.getString("status"));
				map.put("phone", rs.getString("phone"));
				map.put("id_type", rs.getString("id_type"));
				map.put("address", rs.getString("address"));
				// System.out.println(rs.getString("name")+rs.getString("status")+rs.getString("phone")+rs.getString("id_type")+rs.getString("address"));
			}
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
		System.out.println(ff);
		return map;
	}

	public static Map<String, String> getFinance(KSDCase ksd) {
		String ff = "";
		DecimalFormat decimalFormat = new DecimalFormat(
				"###################.###########");
		String sql = " select * from tb_finance where customer_id=(select id from tb_customer where id_num=?) order by id desc  limit 1 ";
		DBUtil util = new DBUtil();
		Connection conn = util.openConnection();
		Map<String, String> map = new HashMap<String, String>();
		try {

			PreparedStatement pstmt = conn.prepareStatement(sql);

			if (ksd.getIdentitytype() == 1) {
				pstmt.setString(1, ksd.getIdentitynum());
			} else if (ksd.getIdentitytype() == 2) {
				pstmt.setString(1, ksd.getJgid());
			}

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				map.put("name", rs.getString("user_name"));
				map.put("status", rs.getString("status"));
				map.put("phone", rs.getString("phone"));
				map.put("car_type", rs.getString("car_type"));
				map.put("brand_name", rs.getString("brand_name"));
				map.put("series_name", rs.getString("series_name"));
				map.put("product_name", rs.getString("product_name"));
				map.put("car_price", "" + rs.getDouble("car_price"));// decimalFormat.format(rs.getDouble("car_price"))
				System.out.println("car_price" + rs.getString("car_price"));
				System.out.println("car_price" + rs.getDouble("car_price"));
				map.put("loan_amount", "" + rs.getDouble("loan_amount"));
				map.put("pay_periods", rs.getString("pay_periods"));
				map.put("supplier_name", rs.getString("supplier_name"));
				map.put("remark", rs.getString("remark"));
				map.put("loan_type", rs.getString("loan_type"));
				if (ksd.getQygr() == 2) {
					map.put("business_name", rs.getString("business_name"));
					map.put("business_license",
							rs.getString("business_license"));
				}

				map.put("rate", rs.getString("rate"));
				// map.put("vin", rs.getString("vin"));
				map.put("purchase_tax", decimalFormat.format(Double
						.parseDouble(rs.getString("purchase_tax"))));
				map.put("gps_charge", decimalFormat.format(Double
						.parseDouble(rs.getString("gps_charge"))));
				map.put("insurance", decimalFormat.format(Double.parseDouble(rs
						.getString("insurance"))));
				System.out.println("===" + rs.getDouble("insurance"));
				System.out.println("===" + "" + rs.getString("insurance"));
				System.out.println("===ddd"
						+ decimalFormat.format(Double.parseDouble(rs
								.getString("insurance"))));
				map.put("service_charge", decimalFormat.format(Double
						.parseDouble(rs.getString("service_charge"))));
				// System.out.println(rs.getString("name")+rs.getString("status")+rs.getString("phone")+rs.getString("id_type")+rs.getString("address"));
			}
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
		System.out.println(ff);
		return map;
	}

	public static Map<String, String> getAdvance(KSDCase ksd) {
		String ff = "";
		DecimalFormat decimalFormat = new DecimalFormat(
				"###################.###########");
		String sql = " select * from tb_finance_advance where finance_id=(select id from tb_finance where customer_id=(select id from tb_customer where id_num=?)order by id desc  limit 1)  ; ";
		DBUtil util = new DBUtil();
		Connection conn = util.openConnection();
		Map<String, String> map = new HashMap<String, String>();
		try {

			PreparedStatement pstmt = conn.prepareStatement(sql);

			if (ksd.getIdentitytype() == 1) {
				pstmt.setString(1, ksd.getIdentitynum());
			} else if (ksd.getIdentitytype() == 2) {
				pstmt.setString(1, ksd.getJgid());
			}

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				map.put("name", rs.getString("user_name"));
				map.put("car_type", rs.getString("car_type"));
				map.put("series_name", rs.getString("series_name"));
				map.put("product_name", rs.getString("product_name"));
				map.put("supplier_name", rs.getString("supplier_name"));
				map.put("vin", rs.getString("vin"));

				map.put("purchase_tax", decimalFormat.format(Double
						.parseDouble(rs.getString("purchase_tax"))));
				// map.put("gps_charge",
				// ""+decimalFormat.format(Double.parseDouble(rs.getString("gps_charge"))));
				// System.out.println("dao==============="+""+decimalFormat.format(Double.parseDouble(rs.getString("gps_charge"))));
				map.put("insurance", decimalFormat.format(Double.parseDouble(rs
						.getString("insurance"))));
				// map.put("service_charge",""+
				// decimalFormat.format(Double.parseDouble(rs.getString("service_charge"))));
				map.put("deduction", decimalFormat.format(Double.parseDouble(rs
						.getString("deduction"))));
				double toalcharge=rs.getDouble("car_loan_charge")+rs.getDouble("insurance")+rs.getDouble("purchase_tax")-rs.getDouble("deduction");
				map.put("toalcharge", decimalFormat.format(toalcharge));
			}
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
		System.out.println(ff);
		return map;
	}

	public static int getstatus_id(String name) {
		int have_system = 1;

		String sql = "select id from tb_status where name=? ";
		DBUtil util = new DBUtil();
		Connection conn = util.openConnection();
		try {

			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, name);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				have_system = rs.getInt(1);

			}
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
		return have_system;
	}

	public static int getFinanstatue_id(KSDCase ksd) {
		int statue = 0;

		String sql = " select status from tb_finance where customer_id=(select id from tb_customer where id_num=?) order by id desc  limit 1 ";
		DBUtil util = new DBUtil();
		Connection conn = util.openConnection();
		Map<String, String> map = new HashMap<String, String>();
		try {

			PreparedStatement pstmt = conn.prepareStatement(sql);

			if (ksd.getIdentitytype() == 1) {
				pstmt.setString(1, ksd.getIdentitynum());
			} else if (ksd.getIdentitytype() == 2) {
				pstmt.setString(1, ksd.getJgid());
			}

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				statue = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
		System.out.println(statue);
		return statue;
	}

	public static List<Integer> getLoanname(KSDCase ksd) {

		List<Integer> list = new ArrayList<Integer>();
		String sql = "select * from tb_loan_file  tlf where tlf.finance_id= ( select max(tf.id) from tb_finance tf ,tb_customer tc where  tc.id=tf.customer_id and tc.id_num=?); ";
		DBUtil util = new DBUtil();
		Connection conn = util.openConnection();
		try {

			PreparedStatement pstmt = conn.prepareStatement(sql);
			if (ksd.getIdentitytype() == 1) {
				pstmt.setString(1, ksd.getIdentitynum());
			} else if (ksd.getIdentitytype() == 2) {
				pstmt.setString(1, ksd.getJgid());
			}
		
	
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

			
		System.out.println(rs.getInt("file_type"));
				list.add(rs.getInt("file_type"));
			}
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
		return list;
	}

	public static List<Integer>  getImgType( int type,List<Integer> list1) {
		List<Integer> list2=new ArrayList<Integer>();
		KSDCase ksd = null;
		String sql = "select * from tb_material_data where   type=?;";
		DBUtil util = new DBUtil();
		Connection conn = util.openConnection();
		try {

			PreparedStatement pstmt = conn.prepareStatement(sql);

	
			pstmt.setInt(1, type);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

			int ff=	rs.getInt("id");
			list2.add(ff);
			
			}
	
			list2.retainAll(list1);
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
		return list2;

	}
	
	
	public static int getRisk_type( KSDCase ksd) {
		int have_system = 1;

		String sql = " select risk_type from tb_finance_advance where finance_id=(select id from tb_finance where customer_id=(select id from tb_customer where id_num=?)order by id desc  limit 1) ; ";
		DBUtil util = new DBUtil();
		Connection conn = util.openConnection();
		try {

			PreparedStatement pstmt = conn.prepareStatement(sql);

			if (ksd.getIdentitytype() == 1) {
				pstmt.setString(1, ksd.getIdentitynum());
			} else if (ksd.getIdentitytype() == 2) {
				pstmt.setString(1, ksd.getJgid());
			}

			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				have_system = rs.getInt(1);

			}
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
		return have_system;
	}

}