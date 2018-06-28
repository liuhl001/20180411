package com.kuaishoudan.financer.util;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.kuaishoudan.financer.bean.KSDCase;
import com.kuaishoudan.financer.bean.RequestPayout;
import com.kuaishoudan.financer.bean.ShopBeanCase;
import com.kuaishoudan.financer.dao.UserDaoImpl;
import com.kuaishoudan.financer.selenium.AppUtil;

/**
 * 随机生成中文姓名，性别，Email，手机号，住址
 * 
 * @author X-rapido
 */
public class RandomValue {
	public static String base = "abcdefghijklmnopqrstuvwxyz0123456789";
	private static String firstName = "赵钱孙李周吴郑王冯陈褚卫蒋沈韩杨朱秦尤许何吕施张孔曹严华金魏陶姜戚谢邹喻柏水窦章云苏潘葛奚范彭郎鲁韦昌马苗凤花方俞任袁柳酆鲍史唐费廉岑薛雷贺倪汤滕殷罗毕郝邬安常乐于时傅皮卞齐康伍余元卜顾孟平黄和穆萧尹姚邵湛汪祁毛禹狄米贝明臧计伏成戴谈宋茅庞熊纪舒屈项祝董梁杜阮蓝闵席季麻强贾路娄危江童颜郭梅盛林刁钟徐邱骆高夏蔡田樊胡凌霍虞万支柯咎管卢莫经房裘缪干解应宗宣丁贲邓郁单杭洪包诸左石崔吉钮龚程嵇邢滑裴陆荣翁荀羊於惠甄魏加封芮羿储靳汲邴糜松井段富巫乌焦巴弓牧隗山谷车侯宓蓬全郗班仰秋仲伊宫宁仇栾暴甘钭厉戎祖武符刘姜詹束龙叶幸司韶郜黎蓟薄印宿白怀蒲台从鄂索咸籍赖卓蔺屠蒙池乔阴郁胥能苍双闻莘党翟谭贡劳逄姬申扶堵冉宰郦雍却璩桑桂濮牛寿通边扈燕冀郏浦尚农温别庄晏柴瞿阎充慕连茹习宦艾鱼容向古易慎戈廖庚终暨居衡步都耿满弘匡国文寇广禄阙东殴殳沃利蔚越夔隆师巩厍聂晁勾敖融冷訾辛阚那简饶空曾毋沙乜养鞠须丰巢关蒯相查后江红游竺权逯盖益桓公万俟司马上官欧阳夏侯诸葛闻人东方赫连皇甫尉迟公羊澹台公冶宗政濮阳淳于仲孙太叔申屠公孙乐正轩辕令狐钟离闾丘长孙慕容鲜于宇文司徒司空亓官司寇仉督子车颛孙端木巫马公西漆雕乐正壤驷公良拓拔夹谷宰父谷粱晋楚阎法汝鄢涂钦段干百里东郭南门呼延归海羊舌微生岳帅缑亢况后有琴梁丘左丘东门西门商牟佘佴伯赏南宫墨哈谯笪年爱阳佟第五言福百家姓续";
	private static String girl = "秀娟英华慧巧美娜静淑惠珠翠雅芝玉萍红娥玲芬芳燕彩春菊兰凤洁梅琳素云莲真环雪荣爱妹霞香月莺媛艳瑞凡佳嘉琼勤珍贞莉桂娣叶璧璐娅琦晶妍茜秋珊莎锦黛青倩婷姣婉娴瑾颖露瑶怡婵雁蓓纨仪荷丹蓉眉君琴蕊薇菁梦岚苑婕馨瑗琰韵融园艺咏卿聪澜纯毓悦昭冰爽琬茗羽希宁欣飘育滢馥筠柔竹霭凝晓欢霄枫芸菲寒伊亚宜可姬舒影荔枝思丽 ";
	private static String boy = "伟刚勇毅俊峰强军平保东文辉力明永健世广志义兴良海山仁波宁贵福生龙元全国胜学祥才发武新利清飞彬富顺信子杰涛昌成康星光天达安岩中茂进林有坚和彪博诚先敬震振壮会思群豪心邦承乐绍功松善厚庆磊民友裕河哲江超浩亮政谦亨奇固之轮翰朗伯宏言若鸣朋斌梁栋维启克伦翔旭鹏泽晨辰士以建家致树炎德行时泰盛雄琛钧冠策腾楠榕风航弘";
	private static String[] road = "重庆大厦,黑龙江路,十梅庵街,遵义路,湘潭街,瑞金广场,仙山街,仙山东路,仙山西大厦,白沙河路,赵红广场,机场路,民航街,长城南路,流亭立交桥,虹桥广场,长城大厦,礼阳路,风岗街,中川路,白塔广场,兴阳路,文阳街,绣城路,河城大厦,锦城广场,崇阳街,华城路,康城街,正阳路,和阳广场,中城路,江城大厦,顺城路,安城街,山城广场,春城街,国城路,泰城街,德阳路,明阳大厦,春阳路,艳阳街,秋阳路,硕阳街,青威高速,瑞阳街,丰海路,双元大厦,惜福镇街道,夏庄街道,古庙工业园,中山街,太平路,广西街,潍县广场,博山大厦,湖南路,济宁街,芝罘路,易州广场,荷泽四路,荷泽二街,荷泽一路,荷泽三大厦,观海二广场,广西支街,观海一路,济宁支街,莒县路,平度广场,明水路,蒙阴大厦,青岛路,湖北街,江宁广场,郯城街,天津路,保定街,安徽路,河北大厦,黄岛路,北京街,莘县路,济南街,宁阳广场,日照街,德县路,新泰大厦,荷泽路,山西广场,沂水路,肥城街,兰山路,四方街,平原广场,泗水大厦,浙江路,曲阜街,寿康路,河南广场,泰安路,大沽街,红山峡支路,西陵峡一大厦,台西纬一广场,台西纬四街,台西纬二路,西陵峡二街,西陵峡三路,台西纬三广场,台西纬五路,明月峡大厦,青铜峡路,台西二街,观音峡广场,瞿塘峡街,团岛二路,团岛一街,台西三路,台西一大厦,郓城南路,团岛三街,刘家峡路,西藏二街,西藏一广场,台西四街,三门峡路,城武支大厦,红山峡路,郓城北广场,龙羊峡路,西陵峡街,台西五路,团岛四街,石村广场,巫峡大厦,四川路,寿张街,嘉祥路,南村广场,范县路,西康街,云南路,巨野大厦,西江广场,鱼台街,单县路,定陶街,滕县路,钜野广场,观城路,汶上大厦,朝城路,滋阳街,邹县广场,濮县街,磁山路,汶水街,西藏路,城武大厦,团岛路,南阳街,广州路,东平街,枣庄广场,贵州街,费县路,南海大厦,登州路,文登广场,信号山支路,延安一街,信号山路,兴安支街,福山支广场,红岛支大厦,莱芜二路,吴县一街,金口三路,金口一广场,伏龙山路,鱼山支街,观象二路,吴县二大厦,莱芜一广场,金口二街,海阳路,龙口街,恒山路,鱼山广场,掖县路,福山大厦,红岛路,常州街,大学广场,龙华街,齐河路,莱阳街,黄县路,张店大厦,祚山路,苏州街,华山路,伏龙街,江苏广场,龙江街,王村路,琴屿大厦,齐东路,京山广场,龙山路,牟平街,延安三路,延吉街,南京广场,东海东大厦,银川西路,海口街,山东路,绍兴广场,芝泉路,东海中街,宁夏路,香港西大厦,隆德广场,扬州街,郧阳路,太平角一街,宁国二支路,太平角二广场,天台东一路,太平角三大厦,漳州路一路,漳州街二街,宁国一支广场,太平角六街,太平角四路,天台东二街,太平角五路,宁国三大厦,澳门三路,江西支街,澳门二路,宁国四街,大尧一广场,咸阳支街,洪泽湖路,吴兴二大厦,澄海三路,天台一广场,新湛二路,三明北街,新湛支路,湛山五街,泰州三广场,湛山四大厦,闽江三路,澳门四街,南海支路,吴兴三广场,三明南路,湛山二街,二轻新村镇,江南大厦,吴兴一广场,珠海二街,嘉峪关路,高邮湖街,湛山三路,澳门六广场,泰州二路,东海一大厦,天台二路,微山湖街,洞庭湖广场,珠海支街,福州南路,澄海二街,泰州四路,香港中大厦,澳门五路,新湛三街,澳门一路,正阳关街,宁武关广场,闽江四街,新湛一路,宁国一大厦,王家麦岛,澳门七广场,泰州一路,泰州六街,大尧二路,青大一街,闽江二广场,闽江一大厦,屏东支路,湛山一街,东海西路,徐家麦岛函谷关广场,大尧三路,晓望支街,秀湛二路,逍遥三大厦,澳门九广场,泰州五街,澄海一路,澳门八街,福州北路,珠海一广场,宁国二路,临淮关大厦,燕儿岛路,紫荆关街,武胜关广场,逍遥一街,秀湛四路,居庸关街,山海关路,鄱阳湖大厦,新湛路,漳州街,仙游路,花莲街,乐清广场,巢湖街,台南路,吴兴大厦,新田路,福清广场,澄海路,莆田街,海游路,镇江街,石岛广场,宜兴大厦,三明路,仰口街,沛县路,漳浦广场,大麦岛,台湾街,天台路,金湖大厦,高雄广场,海江街,岳阳路,善化街,荣成路,澳门广场,武昌路,闽江大厦,台北路,龙岩街,咸阳广场,宁德街,龙泉路,丽水街,海川路,彰化大厦,金田路,泰州街,太湖路,江西街,泰兴广场,青大街,金门路,南通大厦,旌德路,汇泉广场,宁国路,泉州街,如东路,奉化街,鹊山广场,莲岛大厦,华严路,嘉义街,古田路,南平广场,秀湛路,长汀街,湛山路,徐州大厦,丰县广场,汕头街,新竹路,黄海街,安庆路,基隆广场,韶关路,云霄大厦,新安路,仙居街,屏东广场,晓望街,海门路,珠海街,上杭路,永嘉大厦,漳平路,盐城街,新浦路,新昌街,高田广场,市场三街,金乡东路,市场二大厦,上海支路,李村支广场,惠民南路,市场纬街,长安南路,陵县支街,冠县支广场,小港一大厦,市场一路,小港二街,清平路,广东广场,新疆路,博平街,港通路,小港沿,福建广场,高唐街,茌平路,港青街,高密路,阳谷广场,平阴路,夏津大厦,邱县路,渤海街,恩县广场,旅顺街,堂邑路,李村街,即墨路,港华大厦,港环路,馆陶街,普集路,朝阳街,甘肃广场,港夏街,港联路,陵县大厦,上海路,宝山广场,武定路,长清街,长安路,惠民街,武城广场,聊城大厦,海泊路,沧口街,宁波路,胶州广场,莱州路,招远街,冠县路,六码头,金乡广场,禹城街,临清路,东阿街,吴淞路,大港沿,辽宁路,棣纬二大厦,大港纬一路,贮水山支街,无棣纬一广场,大港纬三街,大港纬五路,大港纬四街,大港纬二路,无棣二大厦,吉林支路,大港四街,普集支路,无棣三街,黄台支广场,大港三街,无棣一路,贮水山大厦,泰山支路,大港一广场,无棣四路,大连支街,大港二路,锦州支街,德平广场,高苑大厦,长山路,乐陵街,临邑路,嫩江广场,合江路,大连街,博兴路,蒲台大厦,黄台广场,城阳街,临淄路,安邱街,临朐路,青城广场,商河路,热河大厦,济阳路,承德街,淄川广场,辽北街,阳信路,益都街,松江路,流亭大厦,吉林路,恒台街,包头路,无棣街,铁山广场,锦州街,桓台路,兴安大厦,邹平路,胶东广场,章丘路,丹东街,华阳路,青海街,泰山广场,周村大厦,四平路,台东西七街,台东东二路,台东东七广场,台东西二路,东五街,云门二路,芙蓉山村,延安二广场,云门一街,台东四路,台东一街,台东二路,杭州支广场,内蒙古路,台东七大厦,台东六路,广饶支街,台东八广场,台东三街,四平支路,郭口东街,青海支路,沈阳支大厦,菜市二路,菜市一街,北仲三路,瑞云街,滨县广场,庆祥街,万寿路,大成大厦,芙蓉路,历城广场,大名路,昌平街,平定路,长兴街,浦口广场,诸城大厦,和兴路,德盛街,宁海路,威海广场,东山路,清和街,姜沟路,雒口大厦,松山广场,长春街,昆明路,顺兴街,利津路,阳明广场,人和路,郭口大厦,营口路,昌邑街,孟庄广场,丰盛街,埕口路,丹阳街,汉口路,洮南大厦,桑梓路,沾化街,山口路,沈阳街,南口广场,振兴街,通化路,福寺大厦,峄县路,寿光广场,曹县路,昌乐街,道口路,南九水街,台湛广场,东光大厦,驼峰路,太平山,标山路,云溪广场,太清路"
			.split(",");
	private static final String[] email_suffix = "@gmail.com,@yahoo.com,@msn.com,@hotmail.com,@aol.com,@ask.com,@live.com,@qq.com,@0355.net,@163.com,@163.net,@263.net,@3721.net,@yeah.net,@googlemail.com,@126.com,@sina.com,@sohu.com,@yahoo.com.cn"
			.split(",");

	public static int getNum(int start, int end) {
		return (int) (Math.random() * (end - start + 1) + start);
	}

	/**
	 * 返回Email
	 * 
	 * @param lMin
	 *            最小长度
	 * @param lMax
	 *            最大长度
	 * @return
	 */
	public static String getEmail(int lMin, int lMax) {
		int length = getNum(lMin, lMax);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = (int) (Math.random() * base.length());
			sb.append(base.charAt(number));
		}
		sb.append(email_suffix[(int) (Math.random() * email_suffix.length)]);
		return sb.toString();
	}

	/**
	 * 返回手机号码
	 */
	private static String[] telFirst = "134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153"
			.split(",");

	public static String getTel() {
		int index = getNum(0, telFirst.length - 1);
		String first = telFirst[index];
		String second = String.valueOf(getNum(1, 888) + 10000).substring(1);
		String third = String.valueOf(getNum(1, 9100) + 10000).substring(1);
		return first + second + third;
	}

	/**
	 * 返回中文姓名
	 */
	private static String name_sex = "";

	public static String getChineseName() {
		int index = getNum(0, firstName.length() - 1);
		String first = firstName.substring(index, index + 1);
		int sex = getNum(0, 1);
		String str = boy;
		int length = boy.length();
		if (sex == 0) {
			str = girl;
			length = girl.length();
			name_sex = "女";
		} else {
			name_sex = "男";
		}
		index = getNum(0, length - 1);
		String second = str.substring(index, index + 1);
		int hasThird = getNum(0, 1);
		String third = "";
		if (hasThird == 1) {
			index = getNum(0, length - 1);
			third = str.substring(index, index + 1);
		}
		return first + second + third;
	}

	/**
	 * 返回地址
	 * 
	 * @return
	 */
	private static String getRoad() {
		int index = getNum(0, road.length - 1);
		String first = road[index];
		String second = String.valueOf(getNum(11, 150)) + "号";
		String third = "-" + getNum(1, 20) + "-" + getNum(1, 10);
		return first + second + third;
	}

	/**
	 * 进件资料
	 */
	public static List getImg1(){
		int[][] arr={{0,1,2,3,4,5},{10,11,12,13,14,15},{100,101,103,104,113,128,129},{112,120,121,130},{106,117,119,124,131,132,133,134 }
		,{102,110,114,118,125,126,127,135},{136,137,138,139,140,141,142},{105,111,122,143,144,145,146,147,148,149}};
		List<Integer> list=new ArrayList<Integer>();

		for(int p=0;p<arr.length;p++){
			int length=(int)(Math.random()*arr[p].length);
			
			//System.out.println(length);
			if(p>1&&length==0){
				for(int i=0;i<list.size();i++){
					if(list.get(i)==(p-2)){
						list.remove(i);//如果没有勾选选项，删除必选
					}
				}
			}
			
			for(int k=0;k<length;k++){
				int index=(int)(Math.random()*arr[p].length);
		
				int rand = arr[p][index];
				
				list.add(rand);
				//System.out.println(rand);
				
				
			}
			list=	removeDuplicate(list);
			if(p>1){
				for(int i=0;i<list.size();i++){
					if(list.get(i)==(p+8)){
					//list.remove(i);//如果没有勾选选项，删除必选
						for(int q=0;q<arr[p].length;q++){
							 
							list.add( arr[p][q]);
						}
			//		System.out.println("增加"+Arrays.toString(arr[p]));
					}
				}
			}
			
		}
		list=	removeDuplicate(list);//删除重复
		
	/*	for(int type:list)
			System.out.println("==="+type);*/
		
	//	System.out.println("##="+list.size());
		return list;
	}
	/**
	 * 请款资料
	 */
	public static List getImg2(){
		int [][] arr={{0,1,2,3,4,5,6},{10,11,12,13,14,15,16},{1001},{1039,1054},{1002}
		,{1055,1056,1057,1058},{1059,1060},{1061,1062,1063},{1064}};
		List<Integer> list=new ArrayList<Integer>();


		for(int p=0;p<arr.length;p++){
			int length=(int)(Math.random()*arr[p].length);
			//System.out.println(length);
			if(p>1&&length==0){
				for(int i=0;i<list.size();i++){
					if(list.get(i)==(p-2)){
						System.out.println("删除"+list.get(i));
						list.remove(i);
					}
				}
			}
			for(int k=0;k<length;k++){
				int index=(int)(Math.random()*arr[p].length);
		
				int rand = arr[p][index];
		
				list.add(rand);
				//System.out.println(rand);
			}
			list=	removeDuplicate(list);
			if(p>1){
				for(int i=0;i<list.size();i++){
					if(list.get(i)==(p+8)){
					//list.remove(i);//如果没有勾选选项，删除必选
						for(int q=0;q<arr[p].length;q++){
							 
							list.add( arr[p][q]);
						}
					System.out.println("增加"+Arrays.toString(arr[p]));
					}
				}
			}
		}
		list=	removeDuplicate(list);
	/*	for(int type:list)
			System.out.println("==="+type);
		
		System.out.println("##"+list.size());*/
		return list;
	}
	
	/**
	 * 归档资料
	 */
	public static List getImg3(){
		int [][] arr={{0,1,2,3,4,5},{10,11,12,13,14,15},{3001,3002},{3003},{3004,3005,3006}
		,{3000,3007},{3008},{3009,3010}};
		List<Integer> list=new ArrayList<Integer>();


		for(int p=0;p<arr.length;p++){
			int length=(int)(Math.random()*arr[p].length);
	
			if(p>1&&length==0){
				for(int i=0;i<list.size();i++){
					if(list.get(i)==(p-2)){
					//	System.out.println("删除"+list.get(i));
						list.remove(i);
					}
				}
			}
			for(int k=0;k<length;k++){
				int index=(int)(Math.random()*arr[p].length);
				
				
				int rand = arr[p][index];
		
				list.add(rand);
	
			}
			list=	removeDuplicate(list);
			if(p>1){
				for(int i=0;i<list.size();i++){
					if(list.get(i)==(p+8)){
					//list.remove(i);//如果没有勾选选项，删除必选
						for(int q=0;q<arr[p].length;q++){
							 
							list.add( arr[p][q]);
						}
					System.out.println("增加"+Arrays.toString(arr[p]));
					}
				}
			}
		}
		list=	removeDuplicate(list);
	/*	for(int type:list)
			System.out.println("==="+type);
		System.out.println("##"+list.size());*/
		return list;
	}
	public   static   List  removeDuplicate(List list)  {       
		  for  ( int  i  =   0 ; i  <  list.size()  -   1 ; i ++ )  {       
		      for  ( int  j  =  list.size()  -   1 ; j  >  i; j -- )  {       
		           if  (list.get(j).equals(list.get(i)))  {       
		              list.remove(j);       
		            }        
		        }        
		      }        
		    return list;       
		}
	/**
	 * 数据封装
	 * 
	 * @return
	 */
	public static Map getAddress() {
		Map map = new HashMap();
		map.put("name", getChineseName());
		map.put("sex", name_sex);
		map.put("road", getRoad());
		map.put("tel", getTel());
		map.put("email", getEmail(6, 9));
		return map;
	}
	
	public static ShopBeanCase getShop() {
		ShopBeanCase shopBeanCase=new ShopBeanCase();
		
		System.out.println("getShop()");
		int businessType = (int) (Math.random() * 3);
		int storeType=(int)(Math.random() * 4);
		int paymentMethod=(int)(Math.random() * 2);
		int companyNumber=(int)(1+Math.random() * 9999);
		int mouthSale=(int)(Math.random() * 5);
		int dutyType=(int)(Math.random() * 4);
	int	documentType=(int)(Math.random() * 2);
	int accountType=(int)(Math.random() * 4);
	IdCardGenerator g = new IdCardGenerator();
		

	
		int businessLicense=(int)(Math.random() * 999999999);
		shopBeanCase.setShopname(getChineseName());// 商户名称
		shopBeanCase.setBusinessType(businessType);//业务类型
		shopBeanCase.setStoreType(storeType);//店面类型
		shopBeanCase.setShopAddress("address");// 地址
		shopBeanCase.setPaymentMethod(paymentMethod);//款项结算方式
		shopBeanCase.setCompanyNumber(companyNumber+"");//公司人数
		shopBeanCase.setMouthSale(mouthSale);// 月销量
		shopBeanCase.setName(getChineseName());//姓名
		shopBeanCase.setPhone(getTel());// 手机
		shopBeanCase.setDuty(dutyType);//职务
		shopBeanCase.setDocumentType(documentType);
		if (documentType==0) {
			//营业执照
			shopBeanCase.setBusinessLicense(""+ businessLicense);
		}else if (documentType==1) {
			//身份证
			shopBeanCase.setBusinessLicense(g.generate());
		}
		shopBeanCase.setAccountType(accountType);//账户类型
		shopBeanCase.setAccountName("zhanghao");// 账户名
		shopBeanCase.setOpeningBack("kaihuhang");// 开户行
		shopBeanCase.setBackCardNumber("62103432111");// 银行卡号
		shopBeanCase.setImageCount(1);
		
		return shopBeanCase;
	}
	public static RequestPayout getMoney(){
		RequestPayout requestPayout=new RequestPayout();
		DecimalFormat df2 = new DecimalFormat("#.00");
		String money = new BigDecimal(Double.parseDouble(df2
				.format(1 + Math.random() * 99)) + "").toString();
		String money1 = new BigDecimal(Double.parseDouble(df2
				.format(1 + Math.random() * 99)) + "").toString();
		String money2 = new BigDecimal(Double.parseDouble(df2
				.format(1 + Math.random() * 99)) + "").toString();
		String money3 = new BigDecimal(Double.parseDouble(df2
				.format(1 + Math.random() * 99)) + "").toString();
		String money4 = new BigDecimal(Double.parseDouble(df2
				.format(1 + Math.random() * 99)) + "").toString();
		String money5 = new BigDecimal(Double.parseDouble(df2
				.format(1 + Math.random() * 99)) + "").toString();
		String money6 = new BigDecimal(Double.parseDouble(df2
				.format(1 + Math.random() * 99)) + "").toString();
		String money7 = new BigDecimal(Double.parseDouble(df2
				.format(1 + Math.random() * 99)) + "").toString();
		String money8 = new BigDecimal(Double.parseDouble(df2
				.format(1 + Math.random() * 99)) + "").toString();
		String money9 = new BigDecimal(Double.parseDouble(df2
				.format(1 + Math.random() * 99)) + "").toString();
		int registration_party = (int) (Math.random() * 3);
		int mortgage_party = (int) (Math.random() * 3);
		int idtype1 = (int) (1+Math.random() * 20);
		int idtype2= (int) (1+Math.random() * 10);
		int idtype3= (int) (1+Math.random() * 10);
		requestPayout.setFinancing_back_point(money);
		requestPayout.setGps_back_point(money1);
		requestPayout.setInsurance_back_point(money2);
		requestPayout.setService_back_point(money3);
		requestPayout.setMortgage_free(money4);
		requestPayout.setSign_free(money5);
		requestPayout.setSheepishly1(idtype1);//上牌抵押地省份
		requestPayout.setSheepishly2(idtype2);//上牌抵押地市区
		requestPayout.setSheepishly3(idtype3);//上牌抵押地区域
		requestPayout.setRegistration_party(registration_party);//上牌地
		requestPayout.setMortgage_party(mortgage_party);//抵押方
		requestPayout.setGps_installation(money6);//gps安装费
		requestPayout.setInterest_on_pre(money7);//前置利息
		requestPayout.setRefund(money8);//退款
		requestPayout.setThe_car_loan(money9);//车价贷款	
		requestPayout.setType((int) (Math.random() * 3));
		return requestPayout;
		
	}

	public static void main(String[] args) {
		/*
		 * for (int i = 0; i < 100; i++) { System.out.println(getAddress()); //
		 * System.out.println(getTel()); }
		 */

		//
	
	new RandomValue().test();
		 
		/*
		 * Map map=new HashMap();; map.put("a", "a");
		 */
		// map.put("b", "b");

/*		String ff = "长安新生 标准贷";
		System.out.println(ff.split(" ")[0]);*/
	}
	public void test(){
			 KSDCase ksd=null;
	
			 ksd = getRandom();

			  System.out.println(ksd.getIdentitytype()+"名称" + ksd.getUsername() + "手机" + ksd.getPhone()
						+ "身份证号" + ksd.getIdentitynum() + "身份类型"
						+ ksd.getIdentitytype() + "军官" + ksd.getJgid() + "企业个人"
						+ ksd.getQygr() + "车类型" + ksd.getCartype() + "车品牌"
						+ ksd.getCarbrand() + "车系" + ksd.getCarseries() + "车价格"
						+ ksd.getCarprice() + "贷款价格" + ksd.getSqdk() + "融资期限"
						+ ksd.getHkqs() + "\n  " + ksd.getPurchase_tax() + "\n "
						+ ksd.getInsurance() + " \n" + ksd.getGps_charge() + "\n "
						+ ksd.getService_charge() + "," + ksd.getRegisttype() + ","
						+ ksd.getPledge()+","+ksd.getLoginname());
	
	
	}

	public static KSDCase getRandom() {
		System.out.println("@");

		IdCardGenerator g = new IdCardGenerator();
		Calendar calendar = Calendar.getInstance();
		String jgnum = calendar.getTime().getTime()
				+ (int) (Math.random() * 89 + 10) + "";// 军官证号
		double sqdk = 0;
		double cljg = 0;
		DecimalFormat df2 = new DecimalFormat("#.00");
		DecimalFormat df = new DecimalFormat("#.000");
		for (int i = 0; i < 200; i++) {
			sqdk = Double.parseDouble(df.format(2 + Math.random() * 28));//97 Math.random()
																			// *
																			// 97));//
																			// 997
			cljg = Double.parseDouble(df.format(2 + Math.random() * 57));
			// System.out.println(cljg+"="+sqdk);
			if (cljg >= sqdk) {
				break;
			}
		}

		String purchase_tax = new BigDecimal(Double.parseDouble(df2
				.format(1 + Math.random() * 999999)) + "").toString();//999999999
		String gps_charge = new BigDecimal(Double.parseDouble(df2
				.format(1 + Math.random() * 9999999)) + "").toString();
		String insurance = new BigDecimal(Double.parseDouble(df2
				.format(1 + Math.random() * 999999)) + "").toString();
		String service_charge = new BigDecimal(Double.parseDouble(df2
				.format(1 + Math.random() * 999999)) + "").toString();

		int idtype = (int) (1+Math.random() * 2);
		int loantype = (int) (1+Math.random() * 2);
		int cartype = (int) (Math.random() * 2);
		int rzqx = (int) (Math.random() * 4);
		int registtype = (int) (1 + Math.random() * 3);
		int pledge = (int) (1 + Math.random() * 3);
		
		KSDCase ksd = new KSDCase();
		RequestPayout	rp = RandomValue.getMoney();
		ksd.setUsername(getChineseName());// 名字
		ksd.setPhone(getTel());// 手机号
		ksd.setAddress("address2");// 地址
		ksd.setIdentitynum(g.generate());// 身份证
		ksd.setJgid(jgnum);// 军官id
		ksd.setIdentitytype(idtype);// 军官类型  1身份证 2军官证
		ksd.setQygr(loantype);// 2企业1个人
		ksd.setBusinessname("qiyemc");// 企业名称
		ksd.setBusinessid("yingyezzh");// 企业执照
		ksd.setCartype(cartype);// 0新车  1 二手车
		ksd.setCarbrand("奥迪汽车-奥迪");
		ksd.setCarseries("A1");
		ksd.setCarprice(cljg);// 车辆价格
		ksd.setSqdk(sqdk);// 申请贷款
		ksd.setHkqs(rzqx);// 融资期限
		ksd.setRemark("beizhu");// 备注
		ksd.setPurchase_tax(purchase_tax);// 购置税
		ksd.setGps_charge(gps_charge);// gps费
		ksd.setInsurance(insurance);// 保险费
		ksd.setService_charge(service_charge);// 服务费
		ksd.setVin(g.getItemID(17));// 车架号
		ksd.setRegisttype(registtype);// 上牌方1,2,3
		ksd.setPledge(pledge);// 抵押方1,2,3
		//ksd.setSssh("几节");//所属商户
		ksd.setImgcount(4);// 图片数量1,2,3
		ksd.setLoginname("刘浩亮");//登录邮箱徐翊峰
		ksd.setLoginemail("liuhl@jizhicar.com");
		ksd.setPwd("@123456");
		ksd.setRequestpayout(rp);
		ksd.setDeduction(	 Double.parseDouble(df2.format(2 + Math.random() * 100)));
		ksd.setZjtr((int) (Math.random() * 4));
		return ksd;
	}
	public static KSDCase getRandom(AppiumDriver<AndroidElement> driver) {
		System.out.println("@");

		IdCardGenerator g = new IdCardGenerator();
		Calendar calendar = Calendar.getInstance();
		String jgnum = calendar.getTime().getTime()
				+ (int) (Math.random() * 89 + 10) + "";// 军官证号
		double sqdk = 0;
		double cljg = 0;
		DecimalFormat df2 = new DecimalFormat("#.00");
		DecimalFormat df = new DecimalFormat("#.000");
		for (int i = 0; i < 200; i++) {
			sqdk = Double.parseDouble(df.format(2 + Math.random() * 28));//97 Math.random()
																			// *
																			// 97));//
																			// 997
			cljg = Double.parseDouble(df.format(2 + Math.random() * 57));
			// System.out.println(cljg+"="+sqdk);
			if (cljg >= sqdk) {
				break;
			}
		}

		String purchase_tax = new BigDecimal(Double.parseDouble(df2
				.format(1 + Math.random() * 999999)) + "").toString();//999999999
		String gps_charge = new BigDecimal(Double.parseDouble(df2
				.format(1 + Math.random() * 9999999)) + "").toString();
		String insurance = new BigDecimal(Double.parseDouble(df2
				.format(1 + Math.random() * 999999)) + "").toString();
		String service_charge = new BigDecimal(Double.parseDouble(df2
				.format(1 + Math.random() * 999999)) + "").toString();

		int idtype = (int) (1+Math.random() * 2);
		int loantype = (int) (1+Math.random() * 2);
		int cartype = (int) (Math.random() * 2);
		int rzqx = (int) (Math.random() * 4);
		int registtype = (int) (1 + Math.random() * 3);
		int pledge = (int) (1 + Math.random() * 3);
		KSDCase ksd =  UserDaoImpl.getCustomer_KSD(AppUtil.getIndexname(driver));
		RequestPayout	rp = RandomValue.getMoney();
		ksd.setQygr(loantype);// 2企业1个人
		ksd.setBusinessname("qiyemc");// 企业名称
		ksd.setBusinessid("yingyezzh");// 企业执照
		ksd.setCartype(cartype);// 0新车  1 二手车
		ksd.setCarbrand("奥迪汽车-奥迪");
		ksd.setCarseries("A1");
		ksd.setCarprice(cljg);// 车辆价格
		ksd.setSqdk(sqdk);// 申请贷款
		ksd.setHkqs(rzqx);// 融资期限
		ksd.setRemark("beizhu");// 备注
		ksd.setPurchase_tax(purchase_tax);// 购置税
		ksd.setGps_charge(gps_charge);// gps费
		ksd.setInsurance(insurance);// 保险费
		ksd.setService_charge(service_charge);// 服务费
		ksd.setVin(g.getItemID(17));// 车架号
		ksd.setRegisttype(registtype);// 上牌方1,2,3
		ksd.setPledge(pledge);// 抵押方1,2,3
		//ksd.setSssh("几节");//所属商户
		ksd.setImgcount(4);// 图片数量1,2,3
		ksd.setLoginname("刘浩亮");//登录邮箱
		ksd.setLoginemail("liuhl@jizhicar.com");
		ksd.setPwd("@123456");
		ksd.setRequestpayout(rp);
		ksd.setDeduction(	 Double.parseDouble(df2.format(2 + Math.random() * 100)));
		ksd.setZjtr( (int) (Math.random() * 4));
		return ksd;
	}
}
