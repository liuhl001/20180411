package com.kuaishoudan.financer.bean;

public class RequestPayout {
	
	String  financing_back_point;//车款融资额返点
	String  gps_back_point;//GPS返点
	String  insurance_back_point;//保险返点
	String  service_back_point;//服务费返点
	
	String mortgage_free;//抵押费
	String sign_free;//解押费
	 int sheepishly1;//上牌抵押地 省份
	int sheepishly2;//市
	int sheepishly3;//区域
	int registration_party;//上牌方
	int mortgage_party;//抵押方
	int type;
	String gps_installation;//GPS安装费
	String interest_on_pre;//前置利息
	String refund;//退款
	String the_car_loan;//车价贷款（返款）
	public  String getFinancing_back_point() {
		return financing_back_point;
	}
	public void setFinancing_back_point(String financing_back_point) {
		this.financing_back_point = financing_back_point;
	}
	public  String getGps_back_point() {
		return gps_back_point;
	}
	public void setGps_back_point(String gps_back_point) {
		this.gps_back_point = gps_back_point;
	}
	public  String getInsurance_back_point() {
		return insurance_back_point;
	}
	public void setInsurance_back_point(String insurance_back_point) {
		this.insurance_back_point = insurance_back_point;
	}
	public  String getService_back_point() {
		return service_back_point;
	}
	public void setService_back_point(String service_back_point) {
		this.service_back_point = service_back_point;
	}
	public  String getMortgage_free() {
		return mortgage_free;
	}
	public void setMortgage_free(String mortgage_free) {
		this.mortgage_free = mortgage_free;
	}
	public  String getSign_free() {
		return sign_free;
	}
	public void setSign_free(String sign_free) {
		this.sign_free = sign_free;
	}
	

	public  int getSheepishly1() {
		return sheepishly1;
	}
	public void setSheepishly1(int sheepishly1) {
		this.sheepishly1 = sheepishly1;
	}
	public  int getSheepishly2() {
		return sheepishly2;
	}
	public void setSheepishly2(int sheepishly2) {
		this.sheepishly2 = sheepishly2;
	}
	public  int getSheepishly3() {
		return sheepishly3;
	}
	public void setSheepishly3(int sheepishly3) {
		this.sheepishly3 = sheepishly3;
	}
	public int getRegistration_party() {
		return registration_party;
	}
	public void setRegistration_party(int registration_party) {
		this.registration_party = registration_party;
	}
	public int getMortgage_party() {
		return mortgage_party;
	}
	public void setMortgage_party(int mortgage_party) {
		this.mortgage_party = mortgage_party;
	}
	public String getGps_installation() {
		return gps_installation;
	}
	public void setGps_installation(String gps_installation) {
		this.gps_installation = gps_installation;
	}
	public String getInterest_on_pre() {
		return interest_on_pre;
	}
	public void setInterest_on_pre(String interest_on_pre) {
		this.interest_on_pre = interest_on_pre;
	}
	public String getRefund() {
		return refund;
	}
	public void setRefund(String refund) {
		this.refund = refund;
	}
	public String getThe_car_loan() {
		return the_car_loan;
	}
	public void setThe_car_loan(String the_car_loan) {
		this.the_car_loan = the_car_loan;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	

}
