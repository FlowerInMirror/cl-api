package com.thinkgem.jeesite.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class TestDemo1 {


    /** 长度 */
    private static final int s=6;
    /**补位字符串*/
    private static final String e="WGAZBM";

    public static void main(String[] args) {

        ArrayList<String> arrayList = new ArrayList<>();
        Set<String> hashSet = new HashSet<>();
        for (int i = 1; i < 10000000; i++) {
            String stringId = Long.toString(i, 36);
            if(stringId.length()<s) {
                StringBuilder sb=new StringBuilder();
                sb.append(e.subSequence(0, s-stringId.length()));
                stringId=sb.toString()+stringId;
            }
            arrayList.add(stringId);
            hashSet.add(stringId);
            System.out.println(arrayList.size());
            System.out.println(hashSet.size());
            System.out.println(stringId);
            System.out.println("---------------------------------");
        }
    }



	//WARN level表明会出现潜在错误的情形。
	//ERROR level指出虽然发生错误事件，但仍然不影响系统的继续运行。
	//FATAL level指出每个严重的错误事件将会导致应用程序的退出。
	//ALL Level是最低等级的，用于打开所有日志记录。
	//OFF Level是最高等级的，用于关闭所有日志记录。
	// DEBUG Level指出细粒度信息事件对调试应用程序是非常有帮助的。
	//INFO level表明 消息在粗粒度级别上突出强调应用程序的运行过程。


	private static Logger logger = LoggerFactory.getLogger(TestDemo1.class);
//	public static void main(String[] args) {
//
//
//		IndexTwoSectionStatusAndStatistics.Status status = new IndexTwoSectionStatusAndStatistics().new Status();
//
//		 SubLibraryRecentData srd = new SubLibraryRecentData();
//		System.out.println(srd.M_A_SL_BRAND_ADD);
//
//
////		try {
////			InformationBody a=null;
////			System.out.println(a.getBody());
////			System.out.println(1/0);
////		} catch (Exception e) {
////			logger.debug("错误", e);
////		}
////		InformationBody r = new InformationBody();
////		r.setStatusCode(1);
////		r.setBody(new ArrayList<SubLibraryCityOneSection>());
////		System.out.println(r.getStatusCode());
//
////		String sql = "SELECT '46' paraID,'1' paraValue UNION ALL SELECT '35','2' UNION ALL SELECT '41','2' UNION ALL ";
////		String substringBeforeLast = StringUtils.substringBeforeLast(sql, "UNION ALL ");
////		System.err.println(substringBeforeLast);
//
////		String a = null;
////		System.out.println(a);
////
////		//字母自增 - 添加科目树 编码设值
////		char charAt = str.charAt(str.length()-1);
////		char cahrAta = 'B';
//////		System.out.println(str+charAt);
//////		new String();
//////		int indexOf = str.
//////		System.out.println(str);
//////
//////		String valueOf = String.valueOf(charAt);
//////		str.replace(str,valueOf);
//////		System.out.println(valueOf);
//		//替换 - 追加 判断条件
//
////		String substring = str.substring(str.length()-1,1);
//
////		String str = "AAA-1";
////		//如果获取的最后的字母是"Z",那就追加  生成"A"追加.
////		//如果获取的字母不是"Z",那就获得该字符的ASIIC码+1,获得新的字符替换.
////		System.out.println("转换前"+str);
////		//获取最后一个字符
////		char last = str.charAt(str.length()-1);
////		System.out.println(str.indexOf('B')!=-1);
////		if (last == 'Z') {
//////			String codeSrc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
//////			char[] charArray = codeSrc.toCharArray();
//////			for (char c : charArray) {
//////				if (str.indexOf(c)!=-1) {
////					str  += 'A';
////					System.out.println("追加"+str);
//////					break;
//////				}
//////			}
////
////			//判断当前字符串是否包含追加字符,如果包含使用下一个.
////			//追加
////		}else{
////			System.out.println("替换");
////			char newChar =  (char)((int)last+1);
////			str =  str.substring(0,str.length()-1) + newChar;
////
////		}
////		System.out.println("最终"+str);
//
//
//
////		String replace = str.replace(str.charAt(str.length()-1), charAt);
////		System.out.println(replace);
//
////		System.out.println(charAt);
////		System.out.println(substring);
//
//		//###################################
////		int result = (int) charAt;
////		System.out.println(result+1);
////		char zh = (char) (result+2);
////		System.out.println(zh);
////
////		String str = "A-12";
////		String[] split = str.split("-");
////		System.out.println(Arrays.toString(split));
////		String a = split[split.length-1];
////		System.out.println(Integer.parseInt(a)+1);
////
////		split[split.length-1] = Integer.parseInt(a)+1+ "";
////
////
////		System.out.println(StringUtils.join(split,"-"));
//
////		String str1 = "12,,,";
////		String[] split2 = str1.split(",");
////		System.out.println(split2.length);
////		for (String string : split2) {
////			System.out.println(string);
////		}
//
//		System.out.println("厚度≤10mm");
//
//	}
    // 判断系统
	@Test
	public void testName() throws Exception {
        String os = System.getProperty("os.name");

    }

    // 指定位置后的字符串
    @Test
    public void testAppointLoactionBehindStr(){
        String str = "picture/M00/11/ED/wKgBtFtuhLuAVQTtAAFUBU_vwY4185.png";
        int i = str.indexOf("/",str.indexOf("/") + 1);
        String substring = str.substring(i+1).replace("/","");
    }

	  
	
}
