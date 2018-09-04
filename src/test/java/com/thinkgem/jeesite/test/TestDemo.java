package com.thinkgem.jeesite.test;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.HttpRequestUtils;
import com.thinkgem.jeesite.common.utils.JedisUtils;
import com.thinkgem.jeesite.common.web.Constants;
import com.thinkgem.jeesite.modules.api.jmat.dao.SublibraryDao;
import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.InformationBody;
import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.PageBean;
import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.SubLibraryRecentData;
import com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.list.QGMatLoopList;
import com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.origin.OperationNoteMaterial;
import com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.origin.OperationNotePrice;
import com.thinkgem.jeesite.modules.api.jmat.service.SubLibraryService;
import com.thinkgem.jeesite.modules.web.mms.pojo.WPSLoginUser;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

/**
 * 测试用例
 *
 * @author ljc
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-context.xml" })
public class TestDemo {

    /**
     * 日志对象
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * REDIS 键前缀
     */
    public static final String KEY_PREFIX = Global.getConfig("redis.keyPrefix");


    /**
     * 平台-子库
     */
    @Autowired
    private SublibraryDao sublibraryDao;

    /**
     * 子库列表:设置全国回路材料列表到Redis缓存
     * @author     ljc
     * @createTime 2018-8-5 19:11:55
     * @cron 从1日开始每1天执行一次
     */
    @Test
    public void setQGLoopMatListCache() {
        long startTime = System.currentTimeMillis();
        // 未命中  从数据库获取子库起始页一段列表
        List<QGMatLoopList> qgLoopMatList = sublibraryDao.getQGLoopMatList();
        // 保存    Redis中缓存并设置保存时限三天
        JedisUtils.set(KEY_PREFIX + Constants.QG_LOOP_MAT_LIST, JSONArray.fromObject(qgLoopMatList).toString(),60 * 60 * 24 * 3);
        long endTime = System.currentTimeMillis();
        logger.debug("子库列表:设置全国回路材料列表到Redis缓存,程序运行时间"+ (endTime - startTime) + "ms");
    }



    /**
     * 材料平台WPS登录接口相关
     *
     * @author ljc
     * @version 2018-5-4 14:51:34
     */
    @Test
    public void testHttpClient(){
        JSONArray jsonArray = HttpRequestUtils.httpGetList("http://api.p.rx/api/price/GetUserInfoJson?card=01000378&password=YJXRb407d810d03916d96da0c5bd5ba60e88&GM=1433");
        if(jsonArray.size()!=0){
            WPSLoginUser o1 = (WPSLoginUser)JSONObject.toBean((JSONObject)jsonArray.get(0), WPSLoginUser.class);
            System.out.println("o = " + o1);
        }

            WPSLoginUser loginUser = HttpRequestUtils.sendHttpGetGetBean("http://api.p.rx/api/price/GetUserInfoJson?card=01010314&password=YJXR8c97799c4cc4c08e8b3153e9e8c15cf9&GM=1433", WPSLoginUser.class);
        System.out.println("loginUser = " + loginUser);

    }


    /**
     * 小数点經確到俩位
     * @author ljc
     * @version 2018-5-4 14:52:19
     * @throws Exception
     */
	@Test
	public void testName2() throws Exception {
		Double d = 114.1000;
		BigDecimal b = new BigDecimal(d);
		d = b.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
		System.out.println(d);

        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        String format = decimalFormat.format(d);
        System.out.println("format = " + format);
    }

	@Autowired
	private SubLibraryService ss;
//	@Autowired
//    private CommonController commonController;

	@Test
    public void  testname3(){
//        InformationBody city = commonController.getAllCity();
//        System.out.print(city);
    }

    /**
     * 操作记录相关接口测试
     * @throws Exception
     */
	@Test
	public void testName() throws Exception {
		OperationNoteMaterial operationNoteMaterial = new OperationNoteMaterial();
		operationNoteMaterial.setClassCode("11111");
		operationNoteMaterial.setOperateTime("shijian");
		OperationNoteMaterial setTreeNameByTreeFourID = ss.setTreeNameAndOtherFieldByTreeFourID(operationNoteMaterial,
				"A5B3FF3D-C4D8-409E-AA25-5DB8E134F4AA");
		System.out.println(setTreeNameByTreeFourID);
	}

	@Test
    public void test1() throws Exception {
        SubLibraryRecentData sd = new SubLibraryRecentData();
        sd.setCityID(12);
        sd.setBeginRowNum(1);
        sd.setEndRowNum(20);
        InformationBody findMaterialOperationNoteBySubLibraryRecentData = ss
                .findPriceOperationNoteBySubLibraryRecentData(sd);
        PageBean<OperationNotePrice> page = (PageBean<OperationNotePrice>) findMaterialOperationNoteBySubLibraryRecentData
                .getBody();
        int size = page.getResult().size();
        List<OperationNotePrice> result = page.getResult();
        for (OperationNotePrice operationNoteMaterial : result) {
            System.out.println(operationNoteMaterial);
        }
        System.out.println(size);
    }

    // 判断当前操作系统 进行不同 path 拼接
    @Test
    public void test2() throws Exception {
        String os = System.getProperty("os.name");
    }

}
