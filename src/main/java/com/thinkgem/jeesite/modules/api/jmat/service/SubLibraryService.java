/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSON;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.JedisUtils;
import com.thinkgem.jeesite.common.web.Constants;
import com.thinkgem.jeesite.modules.api.jmat.dao.*;
import com.thinkgem.jeesite.modules.api.jmat.entity.*;
import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.InformationBody;
import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.PageBean;
import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.TreeVo;
import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.SubLibraryRecentData;
import com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.city.*;
import com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.city.status.MMSLoopVO;
import com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.list.*;
import com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.origin.CostOverallRanking;
import com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.origin.OperationNoteMaterial;
import com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.origin.OperationNotePhoto;
import com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.origin.OperationNotePrice;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.utils.StringUtils;

/**
 * 科目树Service
 * @author ljc
 * @version 2018-03-15
 */
@Service
@Transactional(readOnly = true)
public class SubLibraryService extends BaseService {

    public static final String KEY_PREFIX = Global.getConfig("redis.keyPrefix"); // REDIS 键前缀
	
	//---数据访问对象---
    @Autowired//平台-子库
    private SublibraryDao sublibraryDao;
	@Autowired//科目树
	private TreeDao treeDao;
	@Autowired//材料基础信息
	private TreeBaseInfoDao treeBaseInfoDao;
	@Autowired//科目树设置
	private TreeSstDao treeSstDao;
	@Autowired//材料地区信息
	private TreeCityInfoDao treeCityInfoDao;
	@Autowired//材料子库
	private MaterialDao materialDao;
	@Autowired//材料标准信息
	private TreeStandardItmeDao treeStandardItmeDao;
	@Autowired//属性对比（对比图）
	private MaterialComparedAttributesDao  materialComparedAttributesDao;
	@Autowired//材料标准参数
	private StandardParaDao standardParaDao;
	@Autowired// 材料参数项
    private ParameterItemDao parameterItemDao;
	@Autowired//材料搜索|用途
	private SearchItemDao searchItemDao;
	@Autowired//材料母库完善状态表 (基本信息 - 档次总计)
	private TreePerfectStateDao treePerfectStateDao;
	@Autowired//材料子库完善状态表 (规格地区保存)
	private MaterialPerfectStateDao materialPerfectStateDao;
	@Autowired//地区材料涨幅关联表(上月材料地区排行数据)
	private MatRegionalIncreaseDao matRegionalIncreaseDao;
	@Autowired//数据行为
	private DataBehaviorDao dataBehaviorDao;
	@Autowired//材料母库照片
	private TreeStandardPhotoDao treeStandardPhotoDao;
	@Autowired//分类表
	private TypeInfoDao typeInfoDao;
	@Autowired// 配送单明细 (用于成本模块使用量查询)
	private ProOrderDetailDao proOrderDetailDao;
	@Autowired// 材料商材料关联表 (供货商相关)
    private MaterialToSuppliersDao materialToSuppliersDao;
	@Autowired// 材料小样表DAO
    private MaterialSmallSampleDao materialSmallSampleDao;
    @Autowired
    private MaterialHandleDao materialHandleDao; // 材料处理表

    //-------------------------获取子库起始页一段数据 begin-------------------------
    /**
     * 获取子库起始页一段
     * @author     ljc
     * @version    1.0
     * @createTime 2018-4-23 11:31:50
     *@return      InformationBody     body:地区/种类/一审-二审-总数
     */
    public InformationBody getIndexOneSection() {
        InformationBody informationBody = new InformationBody();
        try {
            // Redis缓存中获取子库起始页一段列表
            String indexOneListJSON = JedisUtils.get(KEY_PREFIX + Constants.INDEX_LIST);
            // 判断 缓存是否命中
            if (StringUtils.isNotBlank(indexOneListJSON)){
                // 命中 设值返回
                informationBody.setBody(JSONArray.fromObject(indexOneListJSON));
                logger.debug("Reids缓存获取 -- 子库起始页一段列表", informationBody);
            } else {
                // 未命中  从数据库获取子库起始页一段列表
                List<Map<String, Object>> indexOneSectionArr = materialPerfectStateDao.getIndexOneSection();
                // 保存    Redis中缓存并设置保存时限24小时
                JedisUtils.set(KEY_PREFIX + Constants.INDEX_LIST, JSON.toJSONString(indexOneSectionArr),60 * 60 * 24);
                // 设值    返回
                informationBody.setBody(indexOneSectionArr);
                logger.debug("Reids缓存设值 -- 子库起始页一段列表,失效24小时", informationBody);
            }
        } catch (Exception e) {
            informationBody.setBody(e);
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("fail");
            logger.error("子库起始页一段列表API,调用失败!", e);
        }
        return informationBody;
    }


    // ========== 子庫 - 起始頁 開始


	// ========== 子庫 - 地區 開始
	/**
	 * 通过城市ID与四级科目ID,查找地区二段状态.
	 * 
	 * @author  ljc
	 * @version 2018-03-16 
	 * @param   city
	 * @param   treeFourID
	 * @return  子库地区二段(状态)
	 */
	public Map<String, Object> findCityTwoSectionStatusByCityAndTreeFourID(Integer city,String treeFourID) {
        long startTime = System.currentTimeMillis();   //获取开始时间
        Map<String, Object> bodyMap = new HashMap<>();
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> status = new HashMap<String, Object>();
        Map<String, Object> platform = new HashMap<String, Object>();
        Map<String, Object> mall = new HashMap<String, Object>();
        Map<String, Object> aLevel = new HashMap<String, Object>();
        Map<String, Object> bLevel = new HashMap<String, Object>();
        Map<String, Object> cLevel = new HashMap<String, Object>();

        try {
            // 安全处理
            if (StringUtils.isBlank(treeFourID))
                treeFourID = "00000000-0000-0000-0000-000000000000";

            // --- 数据区 ---
            MaterialHandle materialHandle = materialHandleDao.findMaterialHandleByTreeFourIDAndCityID(treeFourID,city);

			//---状态(status)---
//			bodyMap.put("status", status);//<状态>


			//---平台(platform)---
			parameterMap.put("city", city);
			parameterMap.put("treeFourID", treeFourID);
			List<SubLibraryCityOneSection> scos = treeDao.findCityOneSectionByMap(parameterMap);
			if (null!=scos&&0!=scos.size()) {
				SubLibraryCityOneSection subLibraryCityOneSection = scos.get(0);
				platform.put("treeOneName", subLibraryCityOneSection.getTreeOneName());//一级
				platform.put("treeTwoName", subLibraryCityOneSection.getTreeTwoName());//二级
				platform.put("matName", subLibraryCityOneSection.getMatName());//名称
				platform.put("matSpec", subLibraryCityOneSection.getMatSpec());//规格
			}
			TreeBaseInfo treeBaseInfo = treeBaseInfoDao.findTreeBaseInfoByTreeID(treeFourID);
			if (null!=treeBaseInfo)
				platform.put("unit",treeBaseInfo.getUnitName());//单位
			TreeSst treeSst = treeSstDao.findTreeSstByTreeID(treeFourID);
			if (null!=treeSst)
				platform.put("classify", treeSst.getTsTypestate());//分类
			bodyMap.put("platform", platform);//<平台>

			//---商城(mall)---
			TreeCityInfo treeCityInfo = treeCityInfoDao.findTreeCityInfoByCityAndTreeID(city,treeFourID);
			if (null!=treeCityInfo) {
				//置顶位置
				mall.put("topPosition", treeCityInfo.getTciPageindex()==0 && treeCityInfo.getTciPagenum() == 0 ? "--" : treeCityInfo.getTciPageindex()+"-"+treeCityInfo.getTciPagenum());
				mall.put("homeHostState", treeCityInfo.getTciHomehoststate()==1?"是":"否");//是否推荐首页
			}else{//无法定位到数据设置默认值.
				mall.put("topPosition", "-");
				mall.put("homeHostState", "否");
			}
			bodyMap.put("mall", mall);//<商城>

			// 规格下所有档次材料
            // 总共/完善/价高/价低/缺供
//            List<Map<String, Object>> allLevelData = materialDao.findAllLevelDataByCityAndTreeID(city,treeFourID);
//			for (Map<String, Object> map : allLevelData) {
//                Integer mLever = (Integer) map.get("mLevel");// 当前档次
//                Integer matCount = (Integer)map.get("matCount");// 总共
//                Integer perfectNumber = (Integer) map.get("perfectNumber");//完善
//
//                // 完善的材料已经有了,但是还没有材料商.
//                Integer	lackSupply = perfectNumber - (Integer) map.get("lackSupply");// 缺供 = 所有完善材料 - 完善且有供应商的材料数量
//
//                Integer levelContrastCount = (Integer)map.get("levelContrastCount");// 档次对比项数量(为null返回-1)
//                Integer levelContrastNotCount = (Integer)map.get("levelContrastNotCount");// 档次对比图未完成项(为null返回-1),大于0为未完成的数量
//                Integer levelStaCount = (Integer)map.get("levelStaCount");// 档次质量标准数量(为null返回-1)
//                Integer levelStaNotCount = (Integer)map.get("levelStaNotCount");// 档次质量属性值未完成数量(为null返回-1)
//
//
//                // 状态标识:1.完成(默认)/0.未完成
//                Integer levelState = 1;
//                // 状态标识设值 校验 开始,满足以下条件设值状态标识为未完成.
//
//                if (matCount != perfectNumber || // 材料总数 不等于 材料完善数量
//                        1 > levelContrastCount || // 档次对比项数量 小于 1
//                        0 < levelContrastNotCount || // 档次对比图未完成数量 大于 0
//                        1 > levelContrastCount || // 档次质量标准数量 小于 1
//                        0 < levelStaNotCount // 档次质量属性值未完成数量 大于 0
//                        )
//                    // 设置档次状态标识为 0未完成
//                    levelState = 0;
//
//                switch (mLever) {
//				case 1://A档
//                    setLevelMap(aLevel, levelState, map, perfectNumber, lackSupply);
//					break;
//				case 2://B档
//                    setLevelMap(bLevel, levelState, map, perfectNumber, lackSupply);
//					break;
//				case 4://C档
//                    setLevelMap(cLevel, levelState, map, perfectNumber, lackSupply);
//					break;
//				}
//			}
			//---A档(aLevel)---
			bodyMap.put("aLevel", getCityTwoSectionLevels(new SubLibraryCityQuery(city, treeFourID, 1)).getBody());//<A档>
			//---B档(bLevel)---
			bodyMap.put("bLevel", getCityTwoSectionLevels(new SubLibraryCityQuery(city, treeFourID, 2)).getBody());//<B档>
			//---C档(cLevel)---
			bodyMap.put("cLevel", getCityTwoSectionLevels(new SubLibraryCityQuery(city, treeFourID, 4)).getBody());//<C档>

            // 处理
            Integer vmhType = materialHandle == null ? null : materialHandle.getVmhType(); // 处理类型：1正常，2问题，3异常,4.下架
            bodyMap.put("vmhType",vmhType);

			//设置响应体(body)
			resultMap.put("body", bodyMap);

			long endTime = System.currentTimeMillis();    //获取结束时间
			logger.debug("子库地区二段(状态),响应成功!程序运行时间:"+(endTime - startTime) + "ms", resultMap);
		} catch (Exception e) {
			long endTime = System.currentTimeMillis();    //获取结束时间
			//设置异常响应状态(statusCode/statusMsg)
			resultMap.put("statusCode", -1);
			resultMap.put("statusMsg", "失败!");
			logger.error("子库地区二段(状态),接口调用失败!程序运行时间:"+(endTime - startTime) + "ms", e);
		}
		return resultMap;
	}
    /**
     * 重复代码: 设置档次数据 (子库地区二段 状态)
     * @param levelMap       需要设置的档次map
     * @param flag           状态标识
     * @param map            待组织的map
     * @param perfectNumber  完善数量
     * @param lackSupply     完善且有供应商的材料数量
     */
    private void setLevelMap(Map<String, Object> levelMap, Integer flag, Map<String, Object> map, Integer perfectNumber, Integer lackSupply) {
        levelMap.put("lever", map.get("mLevel"));//档次
        levelMap.put("matCount", map.get("matCount"));//总共
        levelMap.put("perfectNumber", perfectNumber);//完善
        levelMap.put("highPrice", map.get("highPrice"));//价高
        levelMap.put("lowPrice", map.get("lowPrice"));//价低
        levelMap.put("lackSupply", lackSupply);//缺供
        levelMap.put("stateFlag",flag);
    }

    /**
	 * 通过城市ID与四级科目ID,查找城市二段平台结果集.
	 * @author  ljc
	 * @version 2018-5-19 15:30:55
	 * @param   city          城市ID
	 * @param   treeFourID    四级科目ID
	 * @return  城市二段平台结果集
	 */ 
	public Map<String, Object> findCityTwoSectionPlatformByCityAndTreeFourID(Integer city, String treeFourID) {
		long startTime = System.currentTimeMillis();   //获取开始时间
		Map<String, Object> parameterMap = new HashMap<String, Object>();//入参
		Map<String, Object> resultMap = new HashMap<String, Object>();//响应结果集
		Map<String, Object> bodyMap = new HashMap<String, Object>();//响应体
		//>>>平台二段
        Map<String, Object> qualityStandard = new HashMap<String, Object>();//平台-质量标准
		Map<String, Object> sampleStandard = new HashMap<String, Object>();//平台-小样标准
		Map<String, Object> packagingStandard  = new HashMap<String, Object>();//平台-包装标准
		Map<String, Object> contrastStandard = new HashMap<String, Object>();//平台-对比标准
		Map<String, Object> searchTerm = new HashMap<String, Object>();//平台-搜索词
		//>>>平台三段
		Map<String, Object> platformThreeSections = new HashMap<String, Object>();//平台-三段
		//基础信息
		Map<String, Object> basicInformation = new HashMap<String, Object>();//基础信息
		List<Map<String, Object>> materialParameterList = null;//材料参数List
		Map<String, Object> baseMap = new HashMap<String, Object>();//三段-基础信息
		//质量标准
		List<Map<String, Object>> qualityList = new ArrayList<Map<String,Object>>();  //检验标准list
		Map<String, Object> qualityMap = new HashMap<String, Object>();//三段-检验标准
		//小样标准
		List<Map<String, Object>> standardList = new ArrayList<Map<String,Object>>();//标准list
		Map<String, Object> sampleMap = new HashMap<String, Object>();//三段-小样标准
		//包装标准
		List<Map<String, Object>> packAttributeList = new ArrayList<Map<String,Object>>();//包装属性list
		Map<String, Object> packStandard  = new HashMap<String, Object>();//包装标准
		Map<String, Object> packMap = new HashMap<String, Object>();//三段-包装标准
		//对比标准
		List<Map<String, Object>> contrastList = new ArrayList<Map<String,Object>>();//三段-对比标准
		//搜索词
		List<Map<String, Object>>  searchTermList = new ArrayList<Map<String, Object>>();//搜索词集合
		List<Map<String, Object>> useList = new ArrayList<Map<String, Object>>();//用途集合
		Map<String, Object> searchTermMap = new HashMap<String, Object>();//三段-搜索词
		try {
			parameterMap.put("city", city);
			parameterMap.put("treeFourID", treeFourID);
			TreeBaseInfo treeBaseInfo = treeBaseInfoDao.findTreeBaseInfoByTreeID(treeFourID);//<材料基础信息>
			TreeSst treeSst = treeSstDao.findTreeSstByTreeID(treeFourID);//<科目树设置(材料分类)>
			materialParameterList = standardParaDao.findMaterialParameterByTreeFourID(treeFourID);//<材料参数:参数ID/参数名称/参数值/参数单位>
			List<TreeStandardItme> quality = treeStandardItmeDao.findTreeStandardItmesByTypeAndTreeFourID(1,treeFourID);//<质量-检验标准>
			List<TreeStandardItme> packaging = treeStandardItmeDao.findTreeStandardItmesByTypeAndTreeFourID(8,treeFourID);//<包装-检验标准>
			List<MaterialComparedAttributes> mca = materialComparedAttributesDao.findEntitysByTreeFourID(treeFourID);//<属性对比表（对比图）>
            List<TreeStandardItme> sample = treeStandardItmeDao.findTreeStandardItmesByTypeAndTreeFourID(4,treeFourID);//<小样-检验标准>
            // 类型：1搜索，2用途，4搜索自动生成内容（可位运算|）
            List<SearchItem>  searchTerms = searchItemDao.findSearchItemsByTreeFourID(treeFourID,1);//<材料搜索词list>
            List<SearchItem>  uses = searchItemDao.findSearchItemsByTreeFourID(treeFourID,2);//<材料用途list>

            // 基础
            PlatformLoopStatus loopStatus = new PlatformLoopStatus(materialParameterList, treeBaseInfo, treeSst, quality, mca, sample, searchTerms, uses).invoke();
            int statusFlag = loopStatus.getStatusFlag();
            int baseFlag = loopStatus.getBaseFlag();
            bodyMap.put("statusFlag",statusFlag);

            // --- 校验 end

            // 平台-基础
            bodyMap.put("matBase",sublibraryDao.findMatBaseDataByTreeFourID(treeFourID));
            bodyMap.put("matBaseFalg",baseFlag);// 基础标识状态

            //平台-质量标准(qualityStandard)
            qualityStandard.put("qualitySize", quality.size());//检验标准
            bodyMap.put("qualityStandard",qualityStandard);

            //平台-小样标准(sampleStandard)
            sampleStandard.put("samplingMethod",null!=treeBaseInfo&&null!=treeBaseInfo.getTbiMockupsamplingremark()?"有":"无");//取样方法

            Object acceptanceStandard = null;
            if (null != sample && sample.size() != 0)
                acceptanceStandard = sample.size();
            else
                acceptanceStandard = "无";
			sampleStandard.put("acceptanceStandard", acceptanceStandard);//检验标准

			bodyMap.put("sampleStandard", sampleStandard);

			//平台-包装标准(packagingStandard)
            int packStaLoop = 1; // 包装标准回路标识 1正(默认)0未
            String packagingStandardStr = null;
            if (null!=treeBaseInfo) {
                // 包装类型/包装材料/包装说明为空设值回路标识为0未的状态,反之回路综合总积分+1.
                if (null == treeBaseInfo.getTbiPstype() || StringUtils.isBlank(treeBaseInfo.getTbiPsmaterialquality()) || StringUtils.isBlank(treeBaseInfo.getTbiPsmarkremark()))
                    packStaLoop = 0;
                // 包装标准 str : 装包类别/材质/说明不为空,代表 有包装保准, 无则 代表没有.
                if (null != treeBaseInfo.getTbiPstype() && StringUtils.isNotBlank(treeBaseInfo.getTbiPsmaterialquality()) && StringUtils.isNotBlank(treeBaseInfo.getTbiPsmarkremark())){
                    packagingStandardStr = "有";
                }
                else {
                    packagingStandardStr = "无";
                    packStaLoop = 0;
                }
            }else {
                packagingStandardStr = "无";
                packStaLoop = 0;
            }
            int packAttrSize = packaging.size(); // 包装属性数量
            if (packAttrSize == 0)
                packStaLoop = 0;
            packagingStandard.put("packagingStandard",packagingStandardStr);//包装标准
			packagingStandard.put("packagingPhoto", null!=treeBaseInfo&&StringUtils.isNotBlank(treeBaseInfo.getTbiPspackageremark())?"有":"无");//包装照片
			packagingStandard.put("packagingAttribute", null!=packaging&&packaging.size()!=0?packaging.size():"无");//包装属性
			bodyMap.put("packagingStandard", packagingStandard);
			bodyMap.put("packStaLoop", packStaLoop);

			//平台-对比标准(contrastStandard)
			contrastStandard.put("contrastItem", null!=mca&&mca.size()!=0?mca.size():"无");//对比项
			bodyMap.put("contrastStandard", contrastStandard);

			//平台-搜索词(searchTerm)
			searchTerm.put("searchTerm", null!=searchTerms&&searchTerms.size()!=0?searchTerms.size():"无");//搜索词
			searchTerm.put("use", null!=uses&&uses.size()!=0?uses.size():"无");//用途
			bodyMap.put("searchTerm", searchTerm);

			//>>>平台三段
			//基础信息
			if (null!=treeBaseInfo){
				basicInformation.put("unitID", treeBaseInfo.getTbiUnit());//单位ID
				basicInformation.put("unitName", treeBaseInfo.getUnitName());//单位
				basicInformation.put("function", treeBaseInfo.getTbiMatfunction());//功能
				basicInformation.put("describe", treeBaseInfo.getTbiMatdescription());//描述
			}
			if (null!=treeSst)
				basicInformation.put("classify", treeSst.getTsTypestate()==1?"成品类":"非成品类");//分类
			baseMap.put("basicInformation", basicInformation);//基础信息
			baseMap.put("materialParameterList", materialParameterList);//材料参数(paraID 参数ID/paraName 参数名称/paraValue 参数值/unitName 参数单位)
			platformThreeSections.put("baseMap", baseMap);
			//质量标准
			for (TreeStandardItme treeStandardItme : quality) {
				Map<String, Object> qualityItem = new HashMap<String, Object>();
				qualityItem.put("standardID", treeStandardItme.getTsiId());//标准ID
				qualityItem.put("attribute", treeStandardItme.getTsiName());// attribute 属性
				qualityItem.put("specialRequirements", treeStandardItme.getTsiSpecialclaim());// specialRequirements 特殊要求
				qualityItem.put("testMethod", treeStandardItme.getTsiDetectmethod());// testMethod 检测方法
				qualityList.add(qualityItem);
			}
			qualityMap.put("qualityList", qualityList);//检查标准List
			platformThreeSections.put("qualityMap", qualityMap);
			//小样标准
			for (TreeStandardItme treeStandardItme : sample) {
				Map<String, Object> standardItem = new HashMap<String, Object>();
				standardItem.put("standardID", treeStandardItme.getTsiId());//标准ID
				standardItem.put("attributeName", treeStandardItme.getTsiName());//属性名称
				standardItem.put("attributeValue", treeStandardItme.getTsiValue());//属性值
				standardItem.put("attributeUnit", treeStandardItme.getTsiUnitname());//属性单位
				standardItem.put("maximumRange", treeStandardItme.getTsiStandardmax());//范围：最大
				standardItem.put("minimumRange", treeStandardItme.getTsiStandardmin());//范围：最小
				standardList.add(standardItem);
			}
			sampleMap.put("standardList", standardList);//标准list
			sampleMap.put("samplingMethod",null!=treeBaseInfo?treeBaseInfo.getTbiMockupsamplingremark():null);//取样方法
			platformThreeSections.put("sampleMap", sampleMap);
			//包装标准
			for (TreeStandardItme treeStandardItme : packaging) {
				Map<String, Object> packAttributeItem = new HashMap<String, Object>();
				packAttributeItem.put("attributeName", treeStandardItme.getTsiName());//属性名称
				packAttributeItem.put("attributeValue", treeStandardItme.getTsiValue());//属性值
				packAttributeItem.put("attributeUnit", treeStandardItme.getTsiUnitname());//属性单位
				packAttributeList.add(packAttributeItem);
			}
			if (null!=treeBaseInfo) {
				packStandard.put("PackCategory", treeBaseInfo.getTbiPstype());//包装类别
				packStandard.put("packMatQuality ", treeBaseInfo.getTbiPsmaterialquality());//包装材质
				packStandard.put("packExplain", treeBaseInfo.getTbiPsmarkremark());//包装说明
			}
			packMap.put("packAttributeList", packAttributeList);//包装属性
			packMap.put("packStandard", packStandard);//包装标准
			packMap.put("packPhoto",null!=treeBaseInfo&&null!=treeBaseInfo.getTbiPspackageremark()?treeBaseInfo.getTbiPspackageremark():null);//包装照片
			platformThreeSections.put("packMap", packMap);
			//对比标准
			for (MaterialComparedAttributes materialComparedAttributes : mca) {
				Map<String, Object> contrastItem = new HashMap<String, Object>();
				contrastItem.put("mcaID", materialComparedAttributes.getMcaId());//对比标准ID
				contrastItem.put("attributeName", materialComparedAttributes.getMcaName());//对比标准名称
				contrastList.add(contrastItem);//对比名称
			}
			platformThreeSections.put("contrastMap", contrastList);

			//搜索词
			for (SearchItem searchItem : searchTerms) {//组织搜索词数据
				Map<String, Object> itemMap = new HashMap<String, Object>();
				itemMap.put("searchItemID", searchItem.getSeId());//材料搜索ID
				itemMap.put("searchItemContent", searchItem.getSeContent());//材料搜索内容
				itemMap.put("type", searchItem.getSeType());//类型：1搜索，2用途，4搜索自动生成内容（可位运算|）
				searchTermList.add(itemMap);
			}
			for (SearchItem searchItem : uses) {//组织用途数据
				Map<String, Object> useItem = new HashMap<String, Object>();
				useItem.put("searchItemID", searchItem.getSeId());//材料用途ID
				useItem.put("searchItemContent", searchItem.getSeContent());//材料用途内容
				useItem.put("type", searchItem.getSeType());//类型：1搜索，2用途，4搜索自动生成内容（可位运算|）
				useList.add(useItem);
			}
			searchTermMap.put("searchTermList", searchTermList);//搜索词List
			searchTermMap.put("useList", useList);//用途List
			platformThreeSections.put("searchTermMap", searchTermMap);

			//设置平台三段数据
			bodyMap.put("platformThreeSections", platformThreeSections);
			//设置响应体(body)
			resultMap.put("body", bodyMap);
			//设置正常响应状态 (statusCode/statusMsg)
			resultMap.put("statusCode", 0);
			resultMap.put("statusMsg", "成功!");
			long endTime = System.currentTimeMillis();    //获取结束时间
			logger.debug("子库地区二段(平台),响应成功!程序运行时间:"+(endTime - startTime) + "ms", resultMap);
		} catch (Exception e) {
			//设置异常响应状态(statusCode/statusMsg)
			resultMap.put("statusCode", -1);
			resultMap.put("statusMsg", "失败!");
			long endTime = System.currentTimeMillis();    //获取结束时间
			logger.error("子库地区二段(平台),接口调用失败!程序运行时间:"+(endTime - startTime) + "ms", e);
		}
		return resultMap;
	}

    /**
	 * 科目树文本框键入相关数据回显(子库地区新增标准)API
	 * @author    ljc
	 * @version   2018-03-29
	 * @param     treeName        键入的科目树"名称"
	 * @param     treeLevel       键入的科目树"级别"
	 * @param     treeParentID    键入的科目树"父级ID"
	 * @return    InformationBody 响应数据:根据入参检索出的"科目树名称(回显所需数据)","科目树级别(检索所需条件)","科目树ID(添加所需字段)","科目树父级ID(添加所需字段)","科目树编码(添加所需字段)"
	 */
	public InformationBody cityNewlyIncreasedCorrelationEcho(String treeName, Integer treeLevel, String treeParentID) {
		InformationBody informationBody = new InformationBody();
		try {
			//组织MyVatis输入映射
			Map<String,Object> paraMap = new HashMap<String,Object>();
			paraMap.put("treeName", treeName);
			paraMap.put("treeLevel", treeLevel);
			paraMap.put("treeParentID", treeParentID);
			
			//设置响应体
			List<CityNewlyIncreasedCorrelationEcho> findRelevantContentByParaMap = treeDao.findRelevantContentByParaMap(paraMap);
			if (0!=findRelevantContentByParaMap.size()) {
				informationBody.setBody(findRelevantContentByParaMap);
				//设置响应状态
				informationBody.setStatusCode(0);
				informationBody.setStatusMsg("成功!");
			}else{
				informationBody.setBody(null);
				informationBody.setStatusCode(1);// 0 正常 1没有 -1失败
				informationBody.setStatusMsg("没有数据哦!");
			}
			logger.debug("科目树文本框键入相关数据回显(子库地区新增标准),响应成功!数量:"+findRelevantContentByParaMap.size());
		} catch (Exception e) {
			informationBody.setStatusCode(-1);
			informationBody.setStatusMsg("失败!");
			logger.error("科目树文本框键入相关数据回显(子库地区新增标准),调用失败!", e);
		}
		return informationBody;
	}
	
	@Autowired//手动事务控制
	private DataSourceTransactionManager transactionManager;
	
	/**
	 * 添加子库地区新增标准
	 * @author  ljc
	 * @version 2018-03-31
	 * @param   cityNewlyIncreased  地区新增标准(添加模型)
	 * @return  InformationBody     添加响应状态类
	 */
	public InformationBody saveCityNewlyIncreased(CityNewlyIncreased cityNewlyIncreased) {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();  
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 事物隔离级别，开启新事务，这样会比较安全些。  
		TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态  
		
		InformationBody informationBody = new InformationBody();
		String treeOneID   = UUID.randomUUID().toString();// 一级科目ID   
		String treeTwoID   = UUID.randomUUID().toString();// 二级科目ID   
		String treeThreeID = UUID.randomUUID().toString();// 三级科目ID 
		String treeFourID  = UUID.randomUUID().toString();// 四级科目ID
		TreeVo treeOne   = cityNewlyIncreased.getTreeOne();//一级分类
		TreeVo treeTwo   = cityNewlyIncreased.getTreeTwo();//二级分类
		TreeVo treeThree = cityNewlyIncreased.getTreeThree();//材料名称(科目树三级)
		TreeVo treeFour  = cityNewlyIncreased.getTreeFour();//材料规格(科目树四级)
		String treeOneCode  = null;// 一级科目编码
		String treeTwoCode  = null;// 二级科目编码
		String treeThreeCode = null;// 三级科目编码
		String treeFourCode = null;// 四级科目编码
		try {

			//---科目数数据添加---
			//插入字段:ID,编码,名称,父级ID,级别,完善状态
			//前台传入字段:名称,父级ID.
			//设置字段:ID,编码,级别,完善状态
			
			//一级分类 
			if (null!=treeOne) {
				//设置ID
				treeOne.setTreeId(treeOneID);
				//生成一级编码
				String treeOneDESCTop1TreeCode = treeDao.findDESCTop1TreeCodeByTreeParentID("00000000-0000-0000-0000-000000000000");//一级科目降序第一个编码
				int treeCodeLength = treeOneDESCTop1TreeCode.length();
				char lastCharacter = treeOneDESCTop1TreeCode.charAt(treeCodeLength-1);//获取最后一个字符
				if (lastCharacter != 'Z')
					//如果获取的字母不是"Z",那就获得该字符的ASIIC码+1,获得新的字符替换.自增
					treeOneCode = treeOneDESCTop1TreeCode.substring(0,treeCodeLength-1) + (char)((int)lastCharacter+1);
				else
					//如果获取的最后的字母是"Z",那就追加  生成"A"追加.
					treeOneCode = treeOneDESCTop1TreeCode += "A";
				//设置一级编码
				treeOne.setTreeCode(treeOneCode);
				//设置父级ID
				treeOne.setTreeParentid("00000000-0000-0000-0000-000000000000");
				//设置级别
				treeOne.setTreeLevel(1);
				//插入新增一级分类
				treeDao.saveCityNewlyIncreased(treeOne);
			}
			
			//二级分类
			if (null!=treeTwo) {
				//设置ID
				treeTwo.setTreeId(treeTwoID);
				//生成二级编码
				//判断是否传入父级ID
				if (StringUtils.isNotBlank(treeTwo.getTreeParentid())) {
					//true(传入父级ID):代表父级已存在,直接根据父级ID获得编码,自增; 
					String treeTwoDESCTop1TreeCode = treeDao.findDESCTop1TreeCodeByTreeParentID(treeTwo.getTreeParentid());
					//按 '-' 切分,获得最后一个转为int + 1,自增后拼装.
					String[] split = treeTwoDESCTop1TreeCode.split("-");
					int splitLength = split.length-1;
					split[splitLength] = Integer.parseInt(split[splitLength]) + 1 + "";//自增
					treeTwoCode = StringUtils.join(split,"-");//设值
				}else{
					//false:(没有传入父级ID):代表父级为新增项,使用代码上方,刚刚新增插入的一级分类编码 treeOneCode + '-1',拼装二级分类初始编码.并设置父级ID
					treeTwoCode = treeOneCode + "-1";
					//设置二级父级
					treeTwo.setTreeParentid(treeOneID);
				}
				//设置二级编码
				treeTwo.setTreeCode(treeTwoCode);
				//设置级别
				treeTwo.setTreeLevel(2);
				//插入二级分类
				treeDao.saveCityNewlyIncreased(treeTwo);
			}
			
			//材料名称
			if (null!=treeThree) {
				//设置ID
				treeThree.setTreeId(treeThreeID);
				//生成三级编码
				if (StringUtils.isNotBlank(treeThree.getTreeParentid())) {
					String treeThreeDESCTop1TreeCode = treeDao.findDESCTop1TreeCodeByTreeParentID(treeThree.getTreeParentid());
					String[] split = treeThreeDESCTop1TreeCode.split("-");
					int splitLength = split.length-1;
					split[splitLength] = Integer.parseInt(split[splitLength]) + 1 + "";//自增
					treeThreeCode = StringUtils.join(split,"-");//设值
				}else{
					//false:(没有传入父级ID):代表父级为新增项,使用代码上方,刚刚新增插入的一级分类编码 treeTwoCode + '-1',拼装三级分类初始编码.并设置父级ID
					treeThreeCode = treeTwoCode + "-1";
					//设置三级父级
					treeThree.setTreeParentid(treeTwoID);
				}
				//设置三级编码
				treeThree.setTreeCode(treeThreeCode);
				//设置级别
				treeThree.setTreeLevel(3);
				//插入材料名称(三级分类)
				treeDao.saveCityNewlyIncreased(treeThree);
			}
			
			//材料规格
			if (null!=treeFour) {
				//设置ID
				treeFour.setTreeId(treeFourID);
				//生成四级编码
				if (StringUtils.isNotBlank(treeFour.getTreeParentid())) {
					String treeFourDESCTop1TreeCode = treeDao.findDESCTop1TreeCodeByTreeParentID(treeFour.getTreeParentid());
					String[] split = treeFourDESCTop1TreeCode.split("-");
					int splitLength = split.length-1;
					split[splitLength] = Integer.parseInt(split[splitLength]) + 1 + "";//自增
					treeFourCode = StringUtils.join(split,"-");//设值
				}else{
					//false:(没有传入父级ID):代表父级为新增项,使用代码上方,刚刚新增插入的一级分类编码 treeThreeCode + '-1',拼装四级分类初始编码.并设置父级ID
					treeFourCode = treeThreeCode + "-1";
					//设置四级父级
					treeFour.setTreeParentid(treeThreeID);
				}
				//设置四级编码
				treeFour.setTreeCode(treeFourCode);
				//设置级别
				treeFour.setTreeLevel(4);
				Integer levelCount = cityNewlyIncreased.getLevelCount();//档次总计
				//设置 完善状态
				treeFour.setTreePerfectstate(levelCount);
				treeDao.saveCityNewlyIncreased(treeFour);
				
				//---基础数据添加---
				
				//单位
				//插入字段:ID,四级ID,单位ID
				//前台传入字段:单位ID
				treeBaseInfoDao.saveCityNewlyIncreased(UUID.randomUUID().toString(),treeFourID,cityNewlyIncreased.getUnitID());
				
				//类别
				//插入字段:ID,四级ID,16类型,状态,树等级
				//前台传入字段:状态
				treeSstDao.saveCityNewlyIncreased(UUID.randomUUID().toString(),treeFourID,3,16,cityNewlyIncreased.getClassify());
				
				//档次
				//插入字段:ID,四级ID,基本信息(档次总计)
				//前台传入字段:基本信息(档次总计)
				//设置 完善状态：-1未完善，1A档，2B档，4C档（位运算处理|） （默认-1） 
				treePerfectStateDao.saveCityNewlyIncreased(treeFourID,levelCount);
				
				//---地区添加---
				//插入字段:四级规格ID,地区 (插入多条),基本信息
				//前台传入字段: 地区ID
				String cityIds = cityNewlyIncreased.getCityIds();
				String[] split = cityIds.split(",");
				if (null!=split&&0!=split.length) {
					for (String cityId : split) {
						materialPerfectStateDao.saveCityNewlyIncreased(treeFourID,Integer.parseInt(cityId),-1);
					}
				}
			}
			
			//设置响应体
			informationBody.setBody(null);
			//设置响应状态
			informationBody.setStatusCode(0);
			informationBody.setStatusMsg("成功!");
			logger.debug("添加子库地区新增标准,响应成功!");
			transactionManager.commit(status); //提交 
		} catch (Exception e) {
			transactionManager.rollback(status);  //回滚
			informationBody.setStatusCode(-1);
			informationBody.setStatusMsg("失败!");
			informationBody.setBody(e);
			logger.error("添加子库地区新增标准,调用失败!", e);
		}
		return informationBody;
	}
	
	/**
	 * 获取子库默认页成本三段
	 * @author     ljc
	 * @version    1.0
	 * @createTime 2018-04-04 15:16:01
	 * @return     InformationBody     响应数据:排名/地区/材料总价/近期涨幅/近期排名
	 */
	public InformationBody getCastThreeSection() {
		InformationBody informationBody = new InformationBody();
		try{
			List<CostOverallRanking> castThreeSection = matRegionalIncreaseDao.getCastThreeSection();
			//设置涨幅数据
			for (CostOverallRanking costOverallRanking : castThreeSection) {
				//1.设置近期涨幅(涨幅材料总价) 上月材料总价 - 当前材料总价
				if (null!=costOverallRanking.getLmTotalPrice()&&null!=costOverallRanking.getCmTotalPrice()) 
					costOverallRanking.setRecentGains(new BigDecimal(costOverallRanking.getLmTotalPrice() - costOverallRanking.getCmTotalPrice()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
				//2.设置近期排名 =        上月整体排名 - 当前整体排名
				if (null!=costOverallRanking.getLmRanking()&&null!=costOverallRanking.getCmRanking()) 
				    costOverallRanking.setRecentRanking(costOverallRanking.getLmRanking() - costOverallRanking.getCmRanking());
			}
			//设置响应体
			informationBody.setBody(castThreeSection);
			//设置响应状态
			informationBody.setStatusCode(0);
			informationBody.setStatusMsg("成功!");
			logger.debug("获取子库默认页成本三段,响应成功!");
		} catch (Exception e) {
			informationBody.setBody(e);
			informationBody.setStatusCode(-1);
			informationBody.setStatusMsg("失败!");
			logger.error("获取子库默认页成本三段,调用失败!", e);
		}
		return informationBody;
	}
	
	/**
	 * 校验科目树是否存在,通过科目树名称与科目树级别
	 * @author      ljc
	 * @version     1.0
	 * @createTime  2018-4-8 16:03:22 
	 * @param       treeName           科目树名称
	 * @param       treeLevel 		       科目树级别
	 * @param       parentID           父级ID
	 * @return      InformationBody    statusMsg:存在 success,不存在 fail.
	 */
	public InformationBody verificationTreeExistByTreeNameAndTreeLevelAndParentID(String treeName, Integer treeLevel, String parentID) {
		InformationBody informationBody = new InformationBody();
		try{
			Integer treeCount = treeDao.verificationTreeExistByTreeNameAndTreeLevelAndParentID(treeName,treeLevel,parentID);
			if ( 0 == treeCount) {//未重名设置 success 状态信息
				informationBody.setStatusMsg("success");
			}else{//重名了,设置 fail 状态信息
				informationBody.setStatusMsg("fail");
			}
			//设置响应体=通过名称与级别,检索到的科目树数量.
			informationBody.setBody(treeCount);
			informationBody.setStatusCode(0);
			logger.debug("校验科目树是否存在,通过科目树名称与科目树级别,响应成功!");
		} catch (Exception e) {
			informationBody.setBody(e);
			informationBody.setStatusCode(-1);
			informationBody.setStatusMsg("error");
			logger.error("校验科目树是否存在,通过科目树名称与科目树级别,调用失败!", e);
		}
		return informationBody;
	}
	
	/**
	 * 子库起始页二段,状态与材料统计API
	 * @author     ljc
	 * @version    1.0
	 * @param      cityID             城市ID
	 * @return     InformationBody    状态/材料统计-页面展示数据
     * @createTime 2018-4-9 10:06:17
     * @updateTime 2018-8-3 13:59:03
     */
	public InformationBody findIndexTwoSectionStatusAndStatisticsByCityID(Integer cityID) {
		Map<String,Object> result = new HashMap<String,Object>();//结果集
		//---状态---
		//材料统计
		Map<String,Object> s_theLastMonth = new HashMap<String,Object>();//最近一月
		Map<String,Object> s_cost = new HashMap<String,Object>();//成本
		
		//---材料统计---
		Map<String,Object> m_platform = new HashMap<String,Object>();//平台
		
		InformationBody informationBody = new InformationBody();
		try{
			//===材料统计===
            Map<String,Object> brands = materialDao.findBrandStatisticsByCityID(cityID); // <品牌统计> : Map brand 品牌数量,imperfect 未完善数量
            Map<String,Object> mallBrand = treeCityInfoDao.findPlatformStatisticsByCityID(cityID);//<商城(圈内)统计 : Map mallBrandCount 商城品牌数量,mallBrandNotCount 未完善数量

            // === 处理 ===
            Map<String,Object> handel = materialHandleDao.findHandleDetailCountByCityID(cityID); //  处理总数 handelCount/正 zCount/异 yCount/问 wCount/下架总数 xCount

			
			//===最近一月===
			SubLibraryRecentData subLibraryRecentData = new SubLibraryRecentData();
			subLibraryRecentData.setCityID(cityID);
			Integer matAdjust  = dataBehaviorDao.findMatAdjustBySubLibraryRecentData(subLibraryRecentData);//<材料调整>
			s_theLastMonth.put("mat", matAdjust);//材料调整
			s_theLastMonth.put("photo", dataBehaviorDao.findPhotoAdjustBySubLibraryRecentData(subLibraryRecentData));//照片调整
			s_theLastMonth.put("price", dataBehaviorDao.findPriceAdjustBySubLibraryRecentData(subLibraryRecentData));//价格调整
			
			//===成本===
			s_cost = materialDao.findCostHighPriceAndLowPriceByCityID(cityID);//<成本价高价低:Map total 总计,highPrice 价高,lowPrice 价低>
			s_cost.put("cityRanking", materialDao.findCityRankingByCityID(cityID));//整体排名
			
			
			//组织二段材料统计数据
			//===种类===

			//===品牌===
			
			//===平台===
			m_platform.put("topCount", treeCityInfoDao.findTopCountByCityID(cityID));//置顶
			
			//组织完毕,设置响应数据.

			//状态
            result.put("brands", brands); // 材料统计 -- 品牌
            result.put("mallBrand", mallBrand);// 材料统计 -- 商城品牌

			result.put("s_theLastMonth", s_theLastMonth);// 最近一月项

			result.put("s_cost", s_cost);//成本项

            result.put("handle",handel); // 处理项

			//材料统计
			result.put("m_platform", m_platform);//平台
			informationBody.setBody(result);
			informationBody.setStatusCode(0);
			informationBody.setStatusMsg("success");
			logger.debug("子库起始页二段,状态与材料统计数据API,响应成功!");
		} catch (Exception e) {
			informationBody.setStatusCode(-1);
			informationBody.setStatusMsg(e);
			logger.error("子库起始页二段,状态与材料统计数据API,调用失败!", e);
		}
		return informationBody;
	}
	
	
	/**
	 * 查找子库起始页二段-操作记录数据,通过城市ID
	 * @author     ljc
	 * @version    1.0
	 * @createTime 2018-4-11 19:45:49
	 * @param      subLibraryRecentData  城市ID
	 * @return     InformationBody       Body:materialCount 材料总数,photoCount 照片总数,priceCount 价格总数
	 */
	public InformationBody findIndexTwoSectionOperationNoteByCityID(SubLibraryRecentData subLibraryRecentData) {
		InformationBody informationBody = new InformationBody();
		try{
			//设置响应体
			informationBody.setBody(dataBehaviorDao.findIndexTwoSectionOperationNoteByCityID(subLibraryRecentData));
			//设置响应状态
			informationBody.setStatusCode(0);
			informationBody.setStatusMsg("success");
			logger.debug("查找材料操作记录,响应成功!");
		} catch (Exception e) {
			informationBody.setBody(e);
			informationBody.setStatusCode(-1);
			informationBody.setStatusMsg("error");
			logger.error("查找材料操作记录,调用失败!", e);
		}
		return informationBody;
	}
	
	/**
	 * 查找材料操作记录,通过子库最近操作数据模型对象.
	 * @author     ljc
	 * @version    1.0
	 * @createTime 2018-4-11 17:26:04
	 * @param      subLibraryRecentData 子库最近操作数据模型对象
	 * @return     InformationBody      Body:材料操作记录List,总记录数
	 */ 
	public InformationBody findMaterialOperationNoteBySubLibraryRecentData(SubLibraryRecentData subLibraryRecentData) {
		InformationBody informationBody = new InformationBody();
		PageBean<OperationNoteMaterial> pageBean = new PageBean<OperationNoteMaterial>();
		try{
			//获取材料操作记录总记录数
			Integer totalRecord = dataBehaviorDao.findMaterialOperationNoteCountBySubLibraryRecentData(subLibraryRecentData);
			//获取材料操作记录
			List<OperationNoteMaterial> operationNoteMaterials =  dataBehaviorDao.findMaterialOperationNoteBySubLibraryRecentData(subLibraryRecentData);
			
			//组织数据：
			//1.组织结果集总记录数（用于前台分页实现）；
			pageBean.setTotalRecord(totalRecord);
			
			//2.组织四级科目树名称字段：获取classCode 判断对应行为类型.得到四级ID组织四级科目树名称.
			if (null!=operationNoteMaterials) {
				for (OperationNoteMaterial operationNoteMaterial : operationNoteMaterials) {
					String mainID = operationNoteMaterial.getMainID();//主ID
					String classCode = operationNoteMaterial.getClassCode();//分类编码
					String content = operationNoteMaterial.getContent();//操作内容
                    //classCode 添加小样= M_B_SL_SAMPLE_SAVE：16.

					//classCode M_A_SL_BRAND_ADD=添加品牌/M_A_SL_BRAND_SAVE=更新品牌/M_B_SL_SAMPLE_*=编辑小样 材料ID：1、2，
					if ("M_A_SL_BRAND_ADD".equals(classCode)
                        ||"M_A_SL_BRAND_SAVE".equals(classCode)
                        ||"M_B_SL_SAMPLE_ADD".equals(classCode)
                        ||"M_B_SL_SAMPLE_UPDATE".equals(classCode)
                        ||"M_B_SL_SAMPLE_DELETE".equals(classCode)) {
						Material material = materialDao.get(mainID);
						if (null!=material){
							String brandName = StringUtils.isBlank(material.getMBrandname())?"-":material.getMBrandname();//品牌名称
							String brandtype = StringUtils.isBlank(material.getMBrandtype())?"-":material.getMBrandtype();//品牌型号
							Integer mLevel = material.getMLevel();//档次级别
							switch (mLevel) {
							case 1://A档
								operationNoteMaterial.setContent("A:"+brandName+"("+content+")");
								break;
							case 2://B档
								operationNoteMaterial.setContent("B:"+brandName+"("+content+")");
								break;
							case 4://C档
								operationNoteMaterial.setContent("C:"+brandName+"("+content+")");
								break;
							}
							operationNoteMaterial.setBrandName(brandName);//设置=>品牌名称
							//调用公用方法,设置四级科目名称.
							operationNoteMaterial = setTreeNameAndOtherFieldByTreeFourID(operationNoteMaterial,material.getMTreefour());
						}
					} 
					//classCode = 规格ID：10-13/19，
					if ("M_A_TS_MAT_SEARCH".equals(classCode)
							||"M_A_TS_BASE_SAVE".equals(classCode)
							||"M_A_TS_PARA_SAVE".equals(classCode)
							||"M_A_TS_STANDARD_INFO_SAVE".equals(classCode)
                            ||"M_B_SL_TREE_DELETE".equals(classCode)) {
						operationNoteMaterial = setTreeNameAndOtherFieldByTreeFourID(operationNoteMaterial,mainID);
					}
					//classCode = 科目树标准项:14
					if ("M_A_TS_STANDARD_ITEM_SAVE".equals(classCode)) {
						TreeStandardItme treeStandardItme = treeStandardItmeDao.get(mainID);
						if (null!=treeStandardItme )
							operationNoteMaterial = setTreeNameAndOtherFieldByTreeFourID(operationNoteMaterial,treeStandardItme.getTsiTreefour());
					}
					//classCode = 对比属性ID：15.
					if ("M_A_TS_ATTR_ITEM".equals(classCode)){
						MaterialComparedAttributes materialComparedAttributes = materialComparedAttributesDao.get(mainID);
						operationNoteMaterial.setContent(content+":"+materialComparedAttributes.getMcaName());
						operationNoteMaterial = setTreeNameAndOtherFieldByTreeFourID(operationNoteMaterial,materialComparedAttributes.getMcaTreeid());
					}

				}
				pageBean.setResult(operationNoteMaterials);
			}
			//组织完毕，设置响应体
			informationBody.setBody(pageBean);
			//设置响应状态
			informationBody.setStatusCode(0);
			informationBody.setStatusMsg("success");
			logger.debug("查找材料操作记录,响应成功!");
		} catch (Exception e) {
			informationBody.setBody(e);
			informationBody.setStatusCode(-1);
			informationBody.setStatusMsg("error");
			logger.error("查找材料操作记录,调用失败!", e);
		}
		return informationBody;
	}
	
	/**
	 * 查找"照片"操作记录,通过子库最近操作数据模型对象.
	 * @author     ljc
	 * @version    1.0
	 * @createTime 2018-4-11 17:26:04
	 * @param      subLibraryRecentData 子库最近操作数据模型对象
	 * @return     InformationBody      Body:照片操作记录List,总记录数
	 */ 
	public InformationBody findPhotoOperationNoteBySubLibraryRecentData(SubLibraryRecentData subLibraryRecentData) {
		InformationBody informationBody = new InformationBody();
		PageBean<OperationNotePhoto> pageBean = new PageBean<OperationNotePhoto>();
		try{
			//获取操作记录总记录数
			Integer totalRecord = dataBehaviorDao.findOperationNotePhotoCountBySubLibraryRecentData(subLibraryRecentData);
			//获取材料操作记录
			List<OperationNotePhoto> operationNoteMaterials =  dataBehaviorDao.findOperationNotePhotoBySubLibraryRecentData(subLibraryRecentData);
			
			//组织数据
			//1.组织结果集总记录数（用于前台分页实现);
			pageBean.setTotalRecord(totalRecord);
			//2.组织 各级科目名称,操作内容,图片类型: MainID = 材料母库照片表ID ,根据照片类型,
			if (null!=operationNoteMaterials) {
				for (OperationNotePhoto operationNotePhoto : operationNoteMaterials) {
					String mainID = operationNotePhoto.getMainID();//材料图片ID
					String content = operationNotePhoto.getContent();//操作内容
					TreeStandardPhoto treeStandardPhoto = treeStandardPhotoDao.get(mainID);//<材料母库照片对象>
                    if (null != treeStandardPhoto){
                        //图片类型：1品牌LOGO，2品牌型号，3材料对比图，4产品主图，10照片展示自定义类.
                        Integer tspType = treeStandardPhoto.getTspType();

                        // 设置 各级科目名称
                        operationNotePhoto = setTreeNameAndOtherFieldByTreeFourID(operationNotePhoto, treeStandardPhoto.getTspTreefour());

                        // 设置"图片类型"以及"操作内容"

                        // 关于材料图片 1 - 2 - 4,设定.
                        if (1 == tspType || 2 == tspType || 4 == tspType) {//设置"图片类型" , "操作内容:档次:品牌(操作内容)"
                            //图片类型设定
                            switch (tspType) {
                                case 1://1品牌LOGO，
                                    operationNotePhoto.setClassName("品牌LOGO");
                                    break;
                                case 2://2品牌型号
                                    operationNotePhoto.setClassName("品牌型号");
                                    break;
                                case 4://4产品主图
                                    operationNotePhoto.setClassName("产品主图");
                                    break;
                            }
                            //操作内容设定
                            Material material = materialDao.get(treeStandardPhoto.getTspTsid());//<材料对象>
                            if (null!=material){
                                String brandName = StringUtils.isBlank(material.getMBrandname())?"-":material.getMBrandname();//品牌名称
                                Integer mLevel = material.getMLevel();//档次级别
                                switch (mLevel) {
                                    case 1://A档
                                        operationNotePhoto.setContent("A:"+brandName+"("+content+")");
                                        break;
                                    case 2://B档
                                        operationNotePhoto.setContent("B:"+brandName+"("+content+")");
                                        break;
                                    case 4://C档
                                        operationNotePhoto.setContent("C:"+brandName+"("+content+")");
                                        break;
                                }
                            }
                        }

                        // 关于材料对比图 3 设定
                        if (3 == tspType) {
                            // 图片类型设定
                            operationNotePhoto.setClassName("材料对比图");

                            // 操作内容设定
                            MaterialComparedAttributes materialComparedAttributes = materialComparedAttributesDao.get(treeStandardPhoto.getTspParaid()+"");
                            String mcaName = materialComparedAttributes.getMcaName();//对比名称
                            Material material = materialDao.get(treeStandardPhoto.getTspTsid());//<材料对象>
                            if (null!=material){
                                Integer mLevel = material.getMLevel();//档次级别
                                switch (mLevel) {
                                    case 1://A档
                                        operationNotePhoto.setContent("A:"+mcaName+"("+content+")");
                                        break;
                                    case 2://B档
                                        operationNotePhoto.setContent("B:"+mcaName+"("+content+")");
                                        break;
                                    case 4://C档
                                        operationNotePhoto.setContent("C:"+mcaName+"("+content+")");
                                        break;
                                }
                            }
                        }

                        // 关于照片展示自定义类 10 设定
                        if (10 == tspType) {
                            // 图片类型设定
                            operationNotePhoto.setClassName("照片展示自定义类");
                            // 操作内容设定
                            TypeInfo typeInfo = typeInfoDao.get(treeStandardPhoto.getTspBrandid());//<分类对象>
                            operationNotePhoto.setContent(typeInfo.getTiTitle()+":("+content+")");
                        }
                    }

				}
				pageBean.setResult(operationNoteMaterials);
			}
			//设置响应体
			informationBody.setBody(pageBean);
			//设置响应状态
			informationBody.setStatusCode(0);
			informationBody.setStatusMsg("success");
			logger.debug("查找照片操作记录,响应成功!");
		} catch (Exception e) {
			informationBody.setBody(e);
			informationBody.setStatusCode(-1);
			informationBody.setStatusMsg("error");
			logger.error("查找照片操作记录,调用失败!", e);
		}
		return informationBody;
	}
	
	/**
	 * 查找"价格"操作记录,通过子库最近操作数据模型对象.
	 * @author     ljc
	 * @version    1.0
	 * @createTime 2018-4-12 20:27:47
	 * @param      subLibraryRecentData 子库最近操作数据模型对象
	 * @return     InformationBody      Body:"价格"操作记录List,总记录数
	 */ 
	public InformationBody findPriceOperationNoteBySubLibraryRecentData(SubLibraryRecentData subLibraryRecentData) {
		InformationBody informationBody = new InformationBody();
		PageBean<OperationNotePrice> pageBean = new PageBean<OperationNotePrice>();
		try{
			//获取价钱操作记录总记录数
			Integer totalRecord = dataBehaviorDao.findOperationNotePriceCountBySubLibraryRecentData(subLibraryRecentData);
			//获取价钱操作记录
			List<OperationNotePrice> operationNotePrices =  dataBehaviorDao.findOperationNotePriceBySubLibraryRecentData(subLibraryRecentData);
			
			//组织数据：
			//1.组织结果集总记录数（用于前台分页实现）；
			pageBean.setTotalRecord(totalRecord);
			//2.组织各级科目树名称,品牌名称数据:MainID = 材料ID,通过材料ID组织数据.
			if (null!=operationNotePrices) {
				for (OperationNotePrice operationNotePrice : operationNotePrices) {
					String mainID = operationNotePrice.getMainID();//材料ID => 用于组织各级科目树名称,以及品牌名称数据.
					String content = operationNotePrice.getContent();
					Material material = materialDao.get(mainID);
					if (null!=material){
						String brandName = StringUtils.isBlank(material.getMBrandname())?"-":material.getMBrandname();//品牌名称
						Integer mLevel = material.getMLevel();//档次级别
						switch (mLevel) {
						case 1://A档
							operationNotePrice.setContent("A:"+brandName+"("+content+")");
							break;
						case 2://B档
							operationNotePrice.setContent("B:"+brandName+"("+content+")");
							break;
						case 4://C档
							operationNotePrice.setContent("C:"+brandName+"("+content+")");
							break;
						}
						operationNotePrice.setBrandName(brandName);//设置=>品牌名称
						//调用公用方法,设置四级科目名称.
						operationNotePrice = setTreeNameAndOtherFieldByTreeFourID(operationNotePrice,material.getMTreefour());
					}
				}
				pageBean.setResult(operationNotePrices);
			}
			
			//设置响应体
			informationBody.setBody(pageBean);
			//设置响应状态
			informationBody.setStatusCode(0);
			informationBody.setStatusMsg("success");
			logger.debug("查找价钱操作记录,响应成功!");
		} catch (Exception e) {
			informationBody.setBody(e);
			informationBody.setStatusCode(-1);
			informationBody.setStatusMsg("error");
			logger.error("查找价钱操作记录,调用异常!", e);
		}
		return informationBody;
	}
	
	/**
	 * 材料操作记录：通过四级科目ID获取，设置四级科目名称，通过四级科目ID。
	 * @author     ljc
	 * @version    1.0
	 * @createTime 2018-4-12 15:56:08
	 * @param      model                    需要设置数据的材料操作记录POJO
	 * @param      treeFourID               四级科目ID
	 * @return 
	 * @return     OperationNoteMaterial
	 */
	public <T> T setTreeNameAndOtherFieldByTreeFourID(T model,String treeFourID ){
		try{
			SubLibraryCityOneSection result  =  treeDao.findAllTreeNnameByTreeFourID(treeFourID);
			if (null != result) {
				if (model instanceof OperationNoteMaterial) {//实例为：材料操作记录POJO
					OperationNoteMaterial operationNoteMaterial = (OperationNoteMaterial)model;
					operationNoteMaterial.setTreeOneName(result.getTreeOneName());
					operationNoteMaterial.setTreeTwoName(result.getTreeTwoName());
					operationNoteMaterial.setTreeThreeName(result.getMatName());
					operationNoteMaterial.setTreeFourName(result.getMatSpec());
					return (T) operationNoteMaterial;
				}
				if (model instanceof OperationNotePhoto) {//实例为：照片操作记录POJO
					OperationNotePhoto operationNotePhoto = (OperationNotePhoto)model;
					operationNotePhoto.setTreeOneName(result.getTreeOneName());
					operationNotePhoto.setTreeTwoName(result.getTreeTwoName());
					operationNotePhoto.setTreeThreeName(result.getMatName());
					operationNotePhoto.setTreeFourName(result.getMatSpec());
					return (T) operationNotePhoto;
				}
				if (model instanceof OperationNotePrice) {//实例为：价格操作记录POJO
					OperationNotePrice operationNotePrice = (OperationNotePrice)model;
					operationNotePrice.setTreeOneName(result.getTreeOneName());
					operationNotePrice.setTreeTwoName(result.getTreeTwoName());
					operationNotePrice.setTreeThreeName(result.getMatName());
					operationNotePrice.setTreeFourName(result.getMatSpec());
					return (T) operationNotePrice;
				}
				logger.debug("设置四级科目名称，成功!");
			}
			logger.debug("设置四级科目名称，失败!");
		} catch (Exception e) {
			logger.error("设置四级科目名称，异常!", e);
		}
		return model;
	}
	
	/**
	 * 查找"实际"材料使用量集合,通过城市ID与数据量.
	 * @author                      ljc
	 * @version                     1.0
	 * @createTime                  2018-4-14 18:58:12
	 * @param       cityID          城市ID
	 * @param       topNum          数据量
	 * @return      InformationBody Body:使用量排名List
	 */
	public InformationBody findMatApplicationAmountsByCityIDAndTopNum(Integer cityID, Integer topNum) {
		InformationBody informationBody = new InformationBody();
		try {
			informationBody.setBody(proOrderDetailDao.findMatApplicationAmountsByCityIDAndTopNum(cityID,topNum));
			logger.debug("库起始页成本三段-'实际'材料使用量排名API,响应成功!", informationBody);
		} catch (Exception e) {
			informationBody.setBody(e);
			informationBody.setStatusCode(-1);
			informationBody.setStatusMsg("fail");
			logger.error("库起始页成本三段-'实际'材料使用量排名API,调用失败!", e);
		}
		return informationBody;
	}

    /**
     * 查找"计划"材料使用量集合,通过城市ID与数据量.
     * @author                      ljc
     * @version                     1.0
     * @createTime                  2018-5-10 14:07:50
     * @param       cityID          城市ID
     * @param       topNum          数据量
     * @return      InformationBody Body:使用量排名List
     */
    public InformationBody findPlanMatApplicationAmountsByCityIDAndTopNum(Integer cityID, Integer topNum) {
        InformationBody informationBody = new InformationBody();
        try {
            informationBody.setBody(sublibraryDao.findPlanMatApplicationAmountsByCityIDAndTopNum(cityID,topNum));
            informationBody.setStatusCode(0);
            informationBody.setStatusMsg("success");
            logger.debug("库起始页成本三段-'计划'材料使用量排名API,响应成功!", informationBody);
        } catch (Exception e) {
            informationBody.setBody(e);
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("fail");
            logger.error("库起始页成本三段-'计划'材料使用量排名API,调用失败!", e);
        }
        return informationBody;
    }

    /**
     *获取子库地区一段
     * @author     ljc
     * @version    1.0
     * @createTime 2018-5-9 19:00:22
     * @param      cityID             城市ID
     * @return     informationBody    Body:子库地区一段列表
     */
    public InformationBody findCityOneSectionByCity(Integer cityID) {
        InformationBody informationBody = new InformationBody();
        try {
            // 获取子库一段列表,通过城市ID.
            List<SublibrariesAList> sublibrariesALists = sublibraryDao.findSublibrariesAListsByCityID(cityID);
            informationBody.setBody(sublibrariesALists);
            logger.debug("获取子库地区一段API,响应成功!", informationBody);
        } catch (Exception e) {
            informationBody.setBody(e);
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("fail");
            logger.error("获取子库地区一段API,调用失败!", e);
        }
        return informationBody;
    }

    /**
     * 获取子库地区二段数据
     *
     * @author   ljc
     * @version  2018-5-16 16:53:48
     * @param    subLibraryCityTwoSectionQuery 子库地区二段 检索包装类
     */

    //-------------------------获取子库地区二段数据 begin-------------------------
    // 状态
    public InformationBody getCityTwoSectionStatus(SubLibraryCityQuery subLibraryCityTwoSectionQuery) {
        InformationBody informationBody = new InformationBody();
        try {
            //通过 城市,四级科目ID 获取子库二段(状态)数据.
            informationBody.setBody(findCityTwoSectionStatusByCityAndTreeFourID(subLibraryCityTwoSectionQuery.getCityID(), subLibraryCityTwoSectionQuery.getTreeFourID()).get("body"));
            logger.debug("状态-子库地区二段,数据抓取成功!", informationBody);
        } catch (Exception e) {
            informationBody.setBody(e);
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("fail");
            logger.error("状态-子库地区二段,数据抓取失败!", e);
        }
        return informationBody;
    }

    // 平台
    public InformationBody getCityTwoSectionPlatform(SubLibraryCityQuery subLibraryCityTwoSectionQuery) {
        InformationBody informationBody = new InformationBody();
        try {
            informationBody.setBody(findCityTwoSectionPlatformByCityAndTreeFourID(subLibraryCityTwoSectionQuery.getCityID(), subLibraryCityTwoSectionQuery.getTreeFourID()));
            logger.debug("平台-子库地区二段,数据抓取成功!", informationBody);
        } catch (Exception e) {
            informationBody.setBody(e);
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("fail");
            logger.error("平台-子库地区二段,数据抓取失败!", e);
        }
        return informationBody;
    }

    /**
     * 商城 - 子库地区二段
     * @author ljc
     * @version 2018-5-19 21:05:25
     */
    public InformationBody getCityTwoSectionMall(SubLibraryCityQuery subLibraryCityTwoSectionQuery) {
        InformationBody informationBody = new InformationBody();
        try {
            String treeFourID = subLibraryCityTwoSectionQuery.getTreeFourID();// 四级科目ID
            Integer cityID = subLibraryCityTwoSectionQuery.getCityID();// 城市ID
            Material mallMaterial = materialDao.findMallMatByCityIDAndTreeFourID(cityID,treeFourID);// 商城中首推的材料类
            List<Material> perfectMat = materialDao.findPerfectMatByCityIDOrTreeFourIDOrLevel(cityID,treeFourID,null);// 完善材料
            MatBaseData matBaseData = sublibraryDao.findMatBaseDataByTreeFourID(treeFourID);// <基础>
            TreeCityInfo treeCityInfo = treeCityInfoDao.findTreeCityInfoByCityAndTreeID(cityID,treeFourID);// <商城>
            List<SearchItem>  searchTerms = searchItemDao.findSearchItemsByTreeFourID(treeFourID,1);// <搜索词>
            List<SearchItem>  uses = searchItemDao.findSearchItemsByTreeFourID(treeFourID,2);// <用途>
            List<TreeStandardPhoto> treeStandardPhotos = treeStandardPhotoDao.findMatPhotoByCityIDAndTreeFourOrPhotoType(cityID,treeFourID,null);// <照片类/对比图>
            List<TreeStandardItme> qualityStandards =  treeStandardItmeDao.findTreeStandardItmesByTypeAndTreeFourID(1, treeFourID);// <质量标准>

            // 设置品牌LOGO照片总计/品牌型号照片总计
            int brandLogoPhotoNum = 0;// 1品牌LOGO
            int brandVersionPhotoNum = 0;// 2品牌型号
            for (Material material : perfectMat) {
                boolean flag1=false;// 品牌LOGO标识
                boolean flag2=false;// 品牌型号标识
                // 有多少材料
                String matID = material.getMId();// 完善材料ID
                for (TreeStandardPhoto treeStandardPhoto : treeStandardPhotos) {
                    String tspTsid = treeStandardPhoto.getTspTsid();// 照片材料ID
                    int tspType = treeStandardPhoto.getTspType();// 图片类型
                    // 判断 完善材料ID 照片材料ID 与 照片类型 是否相等,满足条件,设置对应标识状态,并跳出循环.
                    if (matID.equals(tspTsid)&& tspType == 1){
                        flag1 = true;break;
                    }
                    if (matID.equals(tspTsid)&& tspType == 2){
                        flag2 = true;break;
                    }
                }
                // 根据完善材料数量,执行对应次数的自增.例:品牌logo标识变为true,这时材料品牌LOGO照片数量+1.反之,不变.
                if (flag1 == true)
                    brandLogoPhotoNum ++;
                if (flag2 == true)
                    brandVersionPhotoNum ++;
            }
            // 设置 材料对比图/主图/自定义模块 图片数量
            int collationMapPhotoNum = 0;// 3材料对比图
            int mainPhotoNum = 0;// 4产品主图
            int userDefinedPhotoNum = 0;// 10照片展示自定义类
            for (TreeStandardPhoto treeStandardPhoto : treeStandardPhotos) {
                // 图片类型：1品牌LOGO，2品牌型号，3材料对比图，4产品主图，5材料资质，6小样图，7产品效果图，8品质细节图，9材料工艺；10照片展示自定义类，11材料约定
                Integer tspType = treeStandardPhoto.getTspType();
                switch (tspType){
                    case 3: collationMapPhotoNum ++ ;break;
                    case 4: mainPhotoNum ++ ;break;
                    case 10: brandLogoPhotoNum ++ ;break;
                }
            }

            // 响应体Map
            Map<String, Object> bodyMap = new HashMap<>();

            // 基础
            bodyMap.put("matBase",matBaseData);

            // 商城
            if (null != treeCityInfo){
                bodyMap.put("topPageIndex",treeCityInfo.getTciPageindex());// 置顶位置 第几页 （默认0）
                bodyMap.put("topPageNum",treeCityInfo.getTciPagenum());// 置顶位置 第几号 （默认0）
                bodyMap.put("homeHostState",treeCityInfo.getTciHomehoststate());// 是否推荐首页 0.不推荐(默认)/1.推荐
            }
            bodyMap.put("getIntoMatID",null != mallMaterial?mallMaterial.getMId() : null);// 进入商城种材料ID

            // 搜索词
            bodyMap.put("searchTerm",null != searchTerms && 0 != searchTerms.size()? searchTerms.size() : -1);// 搜索词
            bodyMap.put("use",null != uses && 0 != uses.size()? uses.size() : -1);// 用途

            // 照片类
            bodyMap.put("perfectMatCount",perfectMat.size());// 完善材料数据(规格/地区)
            bodyMap.put("brandLogoPhotoNum",brandLogoPhotoNum);// 品牌LOGO照片数
            bodyMap.put("brandTypePhotoNum",brandVersionPhotoNum);// 品牌型号照片数
            bodyMap.put("mainPhotoNum",mainPhotoNum);// 参数主图照片数
            bodyMap.put("userDefinedPhotoNum",userDefinedPhotoNum);// 照片展示自定义类照片数

            // 对比图
            bodyMap.put("matCompared",collationMapPhotoNum);// 材料对比图数

            // 质量标准
            bodyMap.put("qualityStandard",null != qualityStandards ? qualityStandards.size() : -1);// 质量标准

            informationBody.setBody(bodyMap);
            logger.debug("商城-子库地区二段,数据抓取成功!", informationBody);
        } catch (Exception e) {
            informationBody.setBody(e);
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("fail");
            logger.error("商城-子库地区二段,数据抓取失败!", e);
        }
        return informationBody;
    }

    /**
     * 档次(A or B or C) 子库地区二段
     * 根据子库地区二段查询实体 - 档次标识 levelFlag 抓取不同段位所需数据.
     * @author     ljc
     * @version    2018-5-22 10:57:18
     * @updateTime 2018-7-18 15:08:45
     * @return     子库地区二段 A|B|C档次数据
     */
    public InformationBody getCityTwoSectionLevels(SubLibraryCityQuery subLibraryCityTwoSectionQuery) {
        long startTime = System.currentTimeMillis();// 获取开始时间

        InformationBody informationBody = new InformationBody();
        Map<String, Object> bodyMap = new HashMap<>();
        String treeFourID = subLibraryCityTwoSectionQuery.getTreeFourID();// 四级科目ID
        Integer cityID = subLibraryCityTwoSectionQuery.getCityID();// 城市ID
        Integer levelFlag = subLibraryCityTwoSectionQuery.getLevelFlag();// 档次标识(1.A档/2.B档/4.C档)
        try {

            // --- 数据区 ---
            MatBaseData matBaseData = sublibraryDao.findMatBaseDataByTreeFourID(treeFourID);// <基础>
            List<TreeStandardItme> qualityStandard = treeStandardItmeDao.findLevelQualityStandardItemsByTreeFourIDAndLevelFlag(treeFourID, levelFlag);// <质量标准项 > 质量标准>
            List<ContrastItemWithPhoto> contrastItem = materialComparedAttributesDao.findContrastItemWithPhotoByCityIDAndLevelFlagAndTreeFourID(cityID,levelFlag,treeFourID);// <质量标准项 > 对比项>
            List<Material> materials = materialDao.findAllMaterialsByCityIDAndTreeFourIDOrLevel(cityID,treeFourID,levelFlag);// <品牌项>

            // --- 变量区 ---
            double levelTotalLoopScore = 1.0; // 状态项 > 档次总状态回路积分(默认1整分,在校验后不符合校验条件改变为0.5半分)
            int qualityStandardStatus = 1; // 状态项 > 质量标准状态(1完成,0未 默认:1)
            int contrastItemStatus = 1; // 状态项 > 对比项状态(1完成,0未 默认:1)
            int brandDetailNotCount = 0; // 状态项 > 品牌详情未总数(品牌项集未完成的数量,抓取品牌项回路积分标识++ 默认:0)
            int supplierNotCount = 0; // 状态项 > 供货商未总数(抓取品牌项没有供应商的++ 默认:0)

            double qualityStandardLoopScore = 1.0; // 质量标准项 > 质量标准半分制回路积分(默认:1整分,在校验后不符合校验条件改变为0.5半分)
            boolean massTermFlag = false; // 质量标准项 > 质量标准校验字段(默认:false,在检测到质量标准值为控制改变为true)
            boolean contrastItemFlag = false; // 质量标准项 > 对比项校验字段(默认:false,在检测到没有对比图 或 没有对比图描述时改变为true)

            List<Map<String, Object>> brandMaps = new ArrayList<>(); // 多品牌项 > 品牌项数据集
            Map<String, Object> brandMap = new HashMap<>(); // 多品牌项 > 品牌项(默认:新的堆栈地址,用于装载数据)
            double brandLooopScore = 1.0; // 多品牌项 > 品牌回路半分制积分(得分逻辑:品牌名称不为空/型号名称不为空/LOGO张片不为空/主图照片不为空/型号照片不为空,反之:则记0.5分)
            int matPerfectFlag = 1;// 多品牌项 > 材料完善标识0未完善,1已完善(默认:1已完善)
            int matLogoPhoto = 0;// 多品牌项 > 类型1品牌LOGO(默认:0)
            int matTyepPhoto = 0;// 多品牌项 > 类型2品牌型号图(默认:0)
            int matMainPhoto = 0;// 多品牌项 > 类型4产品主图(默认:0)


            // --- 业务区 ---

            // 基础项
            bodyMap.put("matBase",matBaseData);

            // 质量标准项

            // 1.设值 质量标准/对比项数量到响应体
            int qualityStandardSize = qualityStandard.size(); // 质量标准数量
            int contrastItemSize = contrastItem.size(); // 对比项数量
            bodyMap.put("qualityStandardSize",qualityStandardSize);
            bodyMap.put("contrastItemSize",contrastItemSize);

            // 2.遍历 校验 设值 质量标准校验字段&对比项校验字段
            for (TreeStandardItme treeStandardItme : qualityStandard) {
                if (massTermFlag)
                    break;
                if (StringUtils.isBlank(treeStandardItme.getTsiValue()))
                    massTermFlag = true;
            }
            for (ContrastItemWithPhoto contrastItemWithPhoto : contrastItem) {
                if (contrastItemFlag)
                    break;
                if (StringUtils.isBlank(contrastItemWithPhoto.getMcaPhotoURL()) || StringUtils.isBlank(contrastItemWithPhoto.getMcaDescribe()))
                    contrastItemFlag = true;
            }

            // 3.校验 设值 质量标准项&总回路标识 半分制回路标识
            if (0 == qualityStandardSize || 0 == contrastItemSize || massTermFlag || contrastItemFlag){
                // 质量标准或对比项数量为0时 或 多质量标准值只要有一个为空时 或 多对比项只要有一个没有对比图片 或 没有照片描述时
                qualityStandardLoopScore = 0.5;
            }

            // 4.设置 质量标准半分制回路积分到响应体中
            bodyMap.put("qualityStandardLoopScore",qualityStandardLoopScore);

            // 档次项
            // 1.遍历品牌集,开始组织页面所需数据
            for (Material material : materials) {

                // 2.赋值调用 材料ID/品牌名称/品牌描述/型号名称/是否已编辑：1是，-1代表地方提交审核 （默认0）/ 材料总成本价(成本+安装)
                String mId = material.getMId();
                String mBrandname = material.getMBrandname();
                String mMatdescription = material.getMMatdescription();
                String mBrandtype = material.getMBrandtype();
                Integer mUpdatestate = material.getMUpdatestate();
                double matCost = Double.parseDouble(material.getMatCost());

                // 3.查找 品牌相关数据:相关图片集/供货商集
                List<TreeStandardPhoto> matPhotos = treeStandardPhotoDao.findMatPhotoByMatIDOrPhotoType(mId,null); // <材料相关图片集>
                List<Map<String,Object>> suppliers = materialToSuppliersDao.findMatSupplierByMatID(mId); // <供货商集>
                int supplierSize = suppliers.size(); // 供货商数量

                // 4.设置 基本数据到品牌项中:品牌/型号/编号
                brandMap.put("matID",mId);// 材料ID
                brandMap.put("brandName",mBrandname);// 品牌
                brandMap.put("brandType",mBrandtype);// 型号
                brandMap.put("matCode",material.getMCode());// 编号
                brandMap.put("matUpdatestate",material.getMUpdatestate()); // 入库状态 是否已编辑：1是，-1代表地方提交审核       （默认0）（为0时：母库更新数据同步子库内容）
                brandMap.put("matHoststate",material.getMHoststate()); // 是否主推

                // 5.遍历材料相关图集 设值 组织 材料相关图片字段到品牌项
                for (TreeStandardPhoto matPhoto : matPhotos) {
                    //图片类型：1品牌LOGO，2品牌型号，4产品主图   ++
                    Integer tspType = matPhoto.getTspType();
                    switch (tspType){
                        case 1:matLogoPhoto++;break;
                        case 2:matTyepPhoto++;break;
                        case 4:matMainPhoto++;break;
                    }
                }
                brandMap.put("matLogoPhoto",matLogoPhoto);// LOGO照片数
                brandMap.put("matMainPhoto",matMainPhoto);// 主图数
                brandMap.put("matTyepPhoto",matTyepPhoto);// 型号照片数

                // 6.设置 供货商字段到品牌项
                brandMap.put("supplierSize",supplierSize);

                // 7.校验 设值 品牌回路半分制积分
                if(StringUtils.isBlank(mBrandname) || StringUtils.isBlank(mBrandtype) || matLogoPhoto == 0 || matMainPhoto == 0 || matTyepPhoto == 0 ){
                    // 品牌名称为空 或 型号名称为空 或 LOGO张片为0 或 主图照片为0 或 型号照片为0时
                    brandLooopScore = 0.5;
                }
                brandMap.put("brandLooopScore",brandLooopScore);

                // 8.校验 设置 预留字段 品牌完善标识 到品牌项中
                if (matCost <= 0 || mUpdatestate != 1){
                    // 总成本大于等于0 或 编辑状态不为已编辑时
                    matPerfectFlag = 0;
                }
                brandMap.put("matPerfectFlag",matPerfectFlag);

                // 9.校验 设值 设置 品牌详情未完成数量&供货商数量没有的数量
                if (brandLooopScore == 0.5)
                    // 品牌回路积分为半分0.5未的状态时
                    brandDetailNotCount ++;
                if (supplierSize == 0)
                    // 供货商数量为0时
                    supplierNotCount ++;
                // 10.设置 组织好数据的品牌项到品牌项集中
                brandMaps.add(brandMap);

                // 11.初始化变量 品牌项 默认值
                brandMap = new HashMap<>(); // 多品牌项 > 品牌项(默认:新的堆栈地址,用于装载数据)
                brandLooopScore = 1.0; // 多品牌项 > 品牌回路半分制积分(得分逻辑:品牌名称不为空/型号名称不为空/LOGO张片不为空/主图照片不为空/型号照片不为空/供货商不为空,反之:则记0.5分)
                matPerfectFlag = 1;// 多品牌项 > 材料完善标识0未完善,1已完善(默认:1已完善)
                matLogoPhoto = 0;// 多品牌项 > 类型1品牌LOGO(默认:0)
                matTyepPhoto = 0;// 多品牌项 > 类型2品牌型号图(默认:0)
                matMainPhoto = 0;// 多品牌项 > 类型4产品主图(默认:0)
            }
            // 12.设置 品牌项集到响应体Map中
            bodyMap.put("brandMaps",brandMaps);

            // 状态项
            // 1.品牌总数
            int brandSize = brandMaps.size();
            bodyMap.put("brandSize",brandSize);

            // 2.质量标准(多 标准值不为空 或 质量标准数量不为0 = 完成)
            if (massTermFlag || qualityStandardSize == 0)
                qualityStandardStatus = 0;
            bodyMap.put("qualityStandardStatus",qualityStandardStatus);

            // 3.对比项(多 对比图照片不为空/照片描述不为空 或 对比项数量不为0 = 完成)
            if (contrastItemFlag || contrastItemSize == 0)
                contrastItemStatus = 0;
            bodyMap.put("contrastItemStatus",contrastItemStatus);

            // 4.品牌详情 未完数量(多 品牌名称不为空/型号名称不为空/LOGO张片不为空/主图照片不为空/型号照片不为空/供货商不为空 = 完成)
            bodyMap.put("brandDetailNotCount",brandDetailNotCount);

            // 5.供应商 没有供应商的材料数量(多 供货商数不为0 = 未x种)
            bodyMap.put("supplierNotCount",supplierNotCount);

            // 6.总回路标识(品牌详情数量大于0 或 质量标准标识为问题项 或 对比项标识为问题项 或 材料总数为0 = 0.5)
            if (brandDetailNotCount != 0 || qualityStandardStatus == 0 || contrastItemStatus == 0 || brandSize == 0)
                levelTotalLoopScore = 0.5;
            bodyMap.put("levelTotalLoopScore",levelTotalLoopScore);

            // 其他功能项 bigin
            bodyMap.put("currentLevel",levelFlag);// 当前档次标识(判断当前是否是B档)

            informationBody.setBody(bodyMap);

            long endTime = System.currentTimeMillis(); // 获取结束时间
            logger.debug("档次"+levelFlag+"子库地区二段,数据抓取成功!程序运行时间:" + (endTime - startTime) + "ms");
        } catch (Exception e) {
            informationBody.setBody(e);
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("fail");
            logger.error("档次"+levelFlag+"子库地区二段,数据抓取失败!", e);
        }
        return informationBody;
    }


    /**
     * 价格 - 子库地区二段
     * @author ljc
     * @version 2018-5-19 21:50:44
     */
    public InformationBody getCityTwoSectionPrice(SubLibraryCityQuery subLibraryCityTwoSectionQuery) {
        InformationBody informationBody = new InformationBody();
        try {
            String treeFourID = subLibraryCityTwoSectionQuery.getTreeFourID();// 四级科目ID
            Integer cityID = subLibraryCityTwoSectionQuery.getCityID();// 城市ID
            MatBaseData matBaseData = sublibraryDao.findMatBaseDataByTreeFourID(treeFourID);// <基础>

            // 响应体Map
            Map<String, Object> bodyMap = new HashMap<>();

            // 基础
            bodyMap.put("matBase",matBaseData);

            // A档/B档/C档
            List<Map<String, Object>> levels = materialDao.findLevelPricesByCityIDAndTreeFourID(cityID, treeFourID);// 档次:品牌数量/成本/交易价
            List<Map<String,Object>> resultLevels = new ArrayList<>();
            Map<String, Object> defaultALevel = new HashMap<>();
            Map<String, Object> defaultBLevel = new HashMap<>();
            Map<String, Object> defaultCLevel = new HashMap<>();

            // 设置档次初始值
            setPriceLevelInitialValue(defaultALevel,1);
            setPriceLevelInitialValue(defaultBLevel,2);
            setPriceLevelInitialValue(defaultCLevel,4);

            for (Map<String, Object> level : levels) {
                // 把对象转为字符串保留精度问题.
                level.put("costMin",level.get("costMin").toString());
                level.put("costMax",level.get("costMax").toString());
                level.put("dealMin",level.get("dealMin").toString());
                level.put("dealMax",level.get("dealMax").toString());
                Integer matLevel = (Integer) level.get("matLevel");// 档次 1 A档,2 B档,4 C档.
                // 根据存在档次MAP,改变默认MAP引用地址.
                switch (matLevel){
                    case 1 : defaultALevel = level; break;
                    case 2 : defaultBLevel = level; break;
                    case 4 : defaultCLevel = level; break;
                }
            }

            resultLevels.add(defaultALevel);
            resultLevels.add(defaultBLevel);
            resultLevels.add(defaultCLevel);
            bodyMap.put("levels",resultLevels);

            informationBody.setBody(bodyMap);
            logger.debug("价格-子库地区二段,数据抓取成功!", informationBody);
        } catch (Exception e) {
            informationBody.setBody(e);
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("fail");
            logger.error("价格-子库地区二段,数据抓取失败!", e);
        }
        return informationBody;
    }

    // 设置 价格 档次初始值
    private Map<String,Object> setPriceLevelInitialValue(Map<String,Object> levelMap,Integer matLevel) {
        levelMap.put("matLevel",matLevel);
        levelMap.put("levelCount",0);
        levelMap.put("costMin","0.00");
        levelMap.put("costMax","0.00");
        levelMap.put("dealMin","0.00");
        levelMap.put("dealMax","0.00");
        return levelMap;
    }

    // 成本
    public InformationBody getCityTwoSectionCost(SubLibraryCityQuery subLibraryCityTwoSectionQuery) {
        InformationBody informationBody = new InformationBody();
        try {
            String treeFourID = subLibraryCityTwoSectionQuery.getTreeFourID();// 四级科目ID
            Integer cityID = subLibraryCityTwoSectionQuery.getCityID();// 城市ID
            MatBaseData matBaseData = sublibraryDao.findMatBaseDataByTreeFourID(treeFourID);// <基础>
            List<Material> materials = materialDao.findPerfectMatByCityIDOrTreeFourIDOrLevel(cityID, treeFourID, null);// 完善材料结果集(地区/规格下)
            Map<String, Object> bodyMap = new HashMap<>();  // 响应体Map

            // 基础
            bodyMap.put("matBase",matBaseData);

            // 档次项 品牌数需求:完善状态,成本+安装价>0
            int levelCountA = 0;// A档 品牌数
            int levelCountB = 0;// B档 品牌数
            int levelCountC = 0;// C档 品牌数
            for (Material material : materials) {
                Integer mLevel = material.getMLevel();// 完善材料 档次标识:1 A档,2 B档,4 C档.
                switch (mLevel){
                    case 1 : levelCountA ++; break;
                    case 2 : levelCountB ++; break;
                    case 4 : levelCountC ++; break;
                }
            }
            bodyMap.put("levelCountA",levelCountA);
            bodyMap.put("levelCountB",levelCountB);
            bodyMap.put("levelCountC",levelCountC);

            informationBody.setBody(bodyMap);
            logger.debug("成本-子库地区二段,数据抓取成功!", informationBody);
        } catch (Exception e) {
            informationBody.setBody(e);
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("fail");
            logger.error("成本-子库地区二段,数据抓取失败!", e);
        }
        return informationBody;
    }

    // 应用
    public InformationBody getCityTwoSectionApply(SubLibraryCityQuery subLibraryCityTwoSectionQuery) {
        InformationBody informationBody = new InformationBody();
        try {
            String treeFourID = subLibraryCityTwoSectionQuery.getTreeFourID();// 四级科目ID
            Integer cityID = subLibraryCityTwoSectionQuery.getCityID();// 城市ID
            MatBaseData matBaseData = sublibraryDao.findMatBaseDataByTreeFourID(treeFourID);// <基础>
            List<Map<String,Object>> apps = materialDao.findAppBrandCountWithEnterByCityIDAndTreeFourID(cityID,treeFourID);
            Map<String, Object> bodyMap = new HashMap<>();  // 响应体Map

            // 基础
            bodyMap.put("matBase",matBaseData);

            // 档次
            Map<String, Object> defaultALevel = new HashMap<>();
            Map<String, Object> defaultBLevel = new HashMap<>();
            Map<String, Object> defaultCLevel = new HashMap<>();
            // 初始默认值
            setAppLevelInitialValue(defaultALevel,1);
            setAppLevelInitialValue(defaultBLevel,2);
            setAppLevelInitialValue(defaultCLevel,4);

            for (Map<String, Object> app : apps) {
                Integer matLevel = (Integer) app.get("matLevel");// 档次 1 A档,2 B档,4 C档.
                // 根据存在档次MAP,改变默认MAP引用地址.
                switch (matLevel){
                    case 1 : defaultALevel = app; break;
                    case 2 : defaultBLevel = app; break;
                    case 4 : defaultCLevel = app; break;
                }
            }

            bodyMap.put("aLevel",defaultALevel);
            bodyMap.put("bLevel",defaultBLevel);
            bodyMap.put("cLevel",defaultCLevel);
            informationBody.setBody(bodyMap);
            logger.debug("应用-子库地区二段,数据抓取成功!", informationBody);
        } catch (Exception e) {
            informationBody.setBody(e);
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("fail");
            logger.error("应用-子库地区二段,数据抓取失败!", e);
        }
        return informationBody;
    }
    private Map<String,Object> setAppLevelInitialValue(Map<String,Object> levelMap,Integer levelFlag) {
        levelMap.put("matID","00000000-0000-0000-0000-000000000000");
        levelMap.put("matLevel",levelFlag);
        levelMap.put("matCount",0);
        levelMap.put("busCount",0);
        return levelMap;
    }

    /**
     * 子库地区二段 - 档次显示标识
     * @author  ljc
     * @version 2018-5-23 16:35:36
     * @param   subLibraryCityTwoSectionQuery 子库地区检索对象
     * @return  档次显示标识
     */
    public InformationBody getLevelShowFlag(SubLibraryCityQuery subLibraryCityTwoSectionQuery) {
        InformationBody informationBody = new InformationBody();
        try {
            String treeFourID = subLibraryCityTwoSectionQuery.getTreeFourID();// 四级科目ID
            Integer cityID = subLibraryCityTwoSectionQuery.getCityID();// 城市ID
            Map<String, Object> bodyMap = new HashMap<>();  // 响应体Map

           TreePerfectState treePerfectState =  treePerfectStateDao.findTreePerfectStateByTreeFourID(treeFourID);
           int tpsBasestate = 0;
           if (null != treePerfectState){
               tpsBasestate = treePerfectState.getTpsBasestate(); // 基本信息：-1未完善，1A档，2B档，4C档（位运算处理|） （默认-1）
           }

            // A - C档次显示标识,默认显示状态 '0' 不显示.
            int aLevel = 0;
            int bLevel = 0;
            int cLevel = 0;

            if ((tpsBasestate | 1) == tpsBasestate)
                aLevel ++;
            if ((tpsBasestate | 2) == tpsBasestate)
                bLevel ++;
            if ((tpsBasestate | 4) == tpsBasestate)
                cLevel ++ ;

            bodyMap.put("aLevel",aLevel);
            bodyMap.put("bLevel",bLevel);
            bodyMap.put("cLevel",cLevel);

            informationBody.setBody(bodyMap);
            logger.debug("子库地区二段 - 档次显示标识,数据抓取成功!", informationBody);
        } catch (Exception e) {
            informationBody.setBody(e);
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("fail");
            logger.error("子库地区二段 - 档次显示标识,数据抓取失败!", e);
        }
        return informationBody;
    }

    //-------------------------状态三段弹出层 begin-------------------------
    /**
     * @author     ljc
     * @param      treeFourID  四级科目ID
     * @return     地区状态三段  处理 JSON
     * @createTime 2018-7-24 20:51:24
     */
    //处理
    public InformationBody getCityThreeStatusHandle(Integer cityID,String treeFourID) {
        InformationBody informationBody = new InformationBody();
        Map<String, Object> bodyMap = new HashMap<>();
        try {
            // ---- 数据区 ---
            MMSLoopVO mmsLoopVO = sublibraryDao.matGetMMSLoopFindOne(cityID,treeFourID);// <基础>

            // ---- 变量区 ---

            // ---- 组织区 ---
            // 平台项
            bodyMap.put("platform",mmsLoopVO);

            // A档项
            bodyMap.put("aLevel", getCityTwoSectionLevels(new SubLibraryCityQuery(cityID, treeFourID, 1)).getBody());//<A档>

            // B档项
            bodyMap.put("bLevel", getCityTwoSectionLevels(new SubLibraryCityQuery(cityID, treeFourID, 2)).getBody());//<B档>

            // C档项
            bodyMap.put("cLevel", getCityTwoSectionLevels(new SubLibraryCityQuery(cityID, treeFourID, 4)).getBody());//<C档>

            // 综合项

            informationBody.setBody(bodyMap);
            logger.debug("子库地区三段-状态-处理,数据抓取成功!", informationBody);
        } catch (Exception e) {
            informationBody.setBody(e);
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("fail");
            logger.error("子库地区三段-状态-处理,数据抓取失败!", e);
        }
        return informationBody;

    }

    //-------------------------平台三段弹出层 begin-------------------------
    /**
     * @author  ljc
     * @version 2018-5-28 11:22:26
     * @param   treeFourID  四级科目ID
     * @return  地区平台三段弹出层JSON数据
     */
    // 基础
    public InformationBody getCityThreePlatformBase(String treeFourID) {
        InformationBody informationBody = new InformationBody();
        try {
            // <基础信息>
            TreeBaseInfo treeBaseInfo = treeBaseInfoDao.findTreeBaseInfoByTreeID(treeFourID);
            TreeSst treeSst = treeSstDao.findTreeSstByTreeID(treeFourID);//<科目树设置(材料分类)>
            // <材料参数>
            List<Map<String, Object>> materialParameters = standardParaDao.findMaterialParameterByTreeFourID(treeFourID);//<材料参数:参数ID/参数名称/参数值/参数单位/参数类型(类型：1文本参数，2下拉参数)>

            Map<String, Object> bodyMap = new HashMap<>();  // 响应体Map

            // 积分项(默认0分)
            int scoreBase = 0;// 基础信息分
            int scorePara = 0;// 基础参数分
            int scoreTotal = 0;// 综合总分

            // === 基础信息 begin
            Integer tbiUnit = treeBaseInfo == null ? null : treeBaseInfo.getTbiUnit();//单位ID
            String unitName = treeBaseInfo == null ? null : treeBaseInfo.getUnitName();//单位
            String tbiMatfunction = treeBaseInfo == null ? null : treeBaseInfo.getTbiMatfunction();//功能
            String tbiMatdescription = treeBaseInfo == null ? null : treeBaseInfo.getTbiMatdescription();//描述
            Integer tsTypestate = treeSst == null ? null : treeSst.getTsTypestate();// 分类 (1成品，2非成品
            // 设值基础信息 & 综合分.
            if (null != tbiUnit && null != unitName && null != tbiMatdescription && null != tsTypestate ){
                scoreBase ++;
                scoreTotal ++;
            }
            bodyMap.put("unitID", tbiUnit);
            bodyMap.put("unitName", unitName);
            bodyMap.put("function", tbiMatfunction);
            bodyMap.put("describe", tbiMatdescription);
            bodyMap.put("classify", tsTypestate);

            // === 材料参数 begin
            // 获取材料参数 类型为下拉选的参数ID 设置 材料参数项
            for (Map<String, Object> materialParameter : materialParameters) {
                Integer paraType =(Integer)materialParameter.get("paraType");// 参数类型：1文本参数，2下拉参数
                Integer paraID =(Integer)materialParameter.get("paraID");
                // 为下拉参数.组织下拉选项到当前参数中.
                if (paraType == 2){
                    List<Map<String, Object>> paramItemsByParamID = parameterItemDao.findParamItemsByParamID(paraID);
                    materialParameter.put("paraItems",paramItemsByParamID);
                }
            }
            // 设值基础参数 & 综合分.
            if (0 < materialParameters.size()){
                scorePara ++;
                scoreTotal ++;
            }
            bodyMap.put("matParas",materialParameters);

            // 设值积分项到响应体中
            bodyMap.put("scoreBase", scoreBase);
            bodyMap.put("scorePara", scorePara);
            bodyMap.put("scoreTotal", scoreTotal);

            informationBody.setBody(bodyMap);
            logger.debug("基础-子库地区'平台'三段,数据抓取成功!", informationBody);
        } catch (Exception e) {
            informationBody.setBody(e);
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("fail");
            logger.error("基础-子库地区'平台'三段,数据抓取失败!", e);
        }
        return informationBody;
    }
    // 质量标准
    public InformationBody getCityThreePlatformStaQuality(String treeFourID) {
        InformationBody informationBody = new InformationBody();
        try {
            List<TreeStandardItme> quality = treeStandardItmeDao.findTreeStandardItmesByTypeAndTreeFourID(1,treeFourID);//<质量-检验标准>
            Map<String, Object> bodyMap = new HashMap<>();  // 响应体Map

            List<Map<String, Object>> qualityList = new ArrayList<Map<String,Object>>();  //检验标准list
            // 组织数据 => 质量标准
            for (TreeStandardItme treeStandardItme : quality) {
                Map<String, Object> qualityItem = new HashMap<String, Object>();
                int itemScore = 1;// 积分项
                String  tsiId = treeStandardItme.getTsiId();// 标准ID
                String  tsiName = treeStandardItme.getTsiName();// 标准属性名(检验项)
                String  tsiValue = treeStandardItme.getTsiValue();// 标准属性值
                Integer tsiUnit = treeStandardItme.getTsiUnit();// 标准单位ID
                String  tsiUnitname = treeStandardItme.getTsiUnitname();// 标准单位名称
                String  tsiStandardmin = treeStandardItme.getTsiStandardmin();// 范围：最小
                String  tsiStandardmax = treeStandardItme.getTsiStandardmax();// 范围：最大
                Integer tsiMatlevel = treeStandardItme.getTsiMatlevel();// 标准档次（1:A档，2:B档，4:C档）（可位运算|）
                String  tsiExteriorname = treeStandardItme.getTsiExteriorname();// 检验方法1
                String  tsiExteriorsc = treeStandardItme.getTsiExteriorsc();// 检验方法2
                String  tsiSpecialclaim = treeStandardItme.getTsiSpecialclaim();// 方法1描述
                String  tsiDetectmethod = treeStandardItme.getTsiDetectmethod();// 方法2描述
                qualityItem.put("tsiId", tsiId);
                qualityItem.put("tsiName", tsiName);
                qualityItem.put("tsiValue", tsiValue);
                qualityItem.put("tsiUnit", tsiUnit);
                qualityItem.put("tsiUnitname", tsiUnitname);
                qualityItem.put("tsiStandardmin", tsiStandardmin);
                qualityItem.put("tsiStandardmax", tsiStandardmax);
                qualityItem.put("tsiMatlevel", tsiMatlevel);
                qualityItem.put("tsiExteriorname", tsiExteriorname);
                qualityItem.put("tsiExteriorsc", tsiExteriorsc);
                qualityItem.put("tsiSpecialclaim", tsiSpecialclaim);
                qualityItem.put("tsiDetectmethod", tsiDetectmethod);
                qualityItem.put("itemScore", itemScore);
                qualityList.add(qualityItem);
            }

            bodyMap.put("qualitys",qualityList);// 设置 质量标准到响应体中.
            bodyMap.put("totalScore",qualityList.size());// 综合总积分

            informationBody.setBody(bodyMap);
            logger.debug("质量标准-子库地区'平台'三段,数据抓取成功!", informationBody);
        } catch (Exception e) {
            informationBody.setBody(e);
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("fail");
            logger.error("质量标准-子库地区'平台'三段,数据抓取失败!", e);
        }
        return informationBody;
    }
    // 小样标准
    public InformationBody getCityThreePlatformStaHue(String treeFourID,Integer cityID) {
        InformationBody informationBody = new InformationBody();
        try {
            List<TreeStandardItme> sample = treeStandardItmeDao.findTreeStandardItmesByTypeAndTreeFourID(4,treeFourID); // <小样标准(材料标准信息表结果集)>
            TreeBaseInfo treeBaseInfo = treeBaseInfoDao.findTreeBaseInfoByTreeID(treeFourID);// <取样方法(材料基础信息表实体)>

            Map<String, Object> bodyMap = new HashMap<>();  // 响应体Map
            int totalScore = sample.size();// 综合积分项(默认:小样标准项数量)
            int loopStatus = 1;// 回路标识 1.完成/0.未完成
            int sampleLoopStatus = 1;//小样回路标识 1.完成/0.未完成.

            // 小样标准
            List<Map<String, Object>> standards = new ArrayList<>();  // 响应体Map
            for (TreeStandardItme treeStandardItme : sample) {
                Map<String, Object> standardItem = new HashMap<String, Object>();
                standardItem.put("standardID", treeStandardItme.getTsiId());//标准ID
                standardItem.put("attributeName", treeStandardItme.getTsiName());//属性名称
                standardItem.put("attributeValue", treeStandardItme.getTsiValue());//属性值
                standardItem.put("attributeUnit", treeStandardItme.getTsiUnitname());//属性单位
                standardItem.put("attributeUnitID", treeStandardItme.getTsiUnit());//属性ID
                standardItem.put("maxRange", treeStandardItme.getTsiStandardmax());//范围：最大
                standardItem.put("minRange", treeStandardItme.getTsiStandardmin());//范围：最小

                standardItem.put("tsiSpecialClaim",treeStandardItme.getTsiSpecialclaim());// 特殊要求
                standardItem.put("tsiDetectMethod",treeStandardItme.getTsiDetectmethod());// 检验方法


                standards.add(standardItem);
            }
            bodyMap.put("standards", standards);//标准list

            // 取样方法
            String samplingMethod = null;
            if (null != treeBaseInfo){
                samplingMethod = treeBaseInfo.getTbiMockupsamplingremark();
            }
            bodyMap.put("samplingMethod",samplingMethod);

            // 综合积分/回路标识设值 开始(校验并设值回路标识:1.判断综合积分初始值是否为0;2.判断是否存在取样方法.)

            // 1.综合积分为0时,设值回路状态为 0 未完成.
            if (0 == totalScore)
                loopStatus = 0;
            // 2.取样方法为null时执行
            if (null == samplingMethod){
                // 设置小样回路标识为0未完成状态
                sampleLoopStatus = 0;
                // 取样方法为null,回路状态还在完成状态下执行
                if (0 != loopStatus)
                    loopStatus = 0;
            } else {
                // 取样方法不为null,综合积分+1.
                totalScore ++;
            }

            // 小样列表(条件:1.正常进程/2.需要小样/3.入库/4.品牌与型号名称不为空/5.报价与成本都不为0/6.有LOGO图,至少一张主图,至少一张型号图)
            bodyMap.put("sampleList",sublibraryDao.findSampleListByCityIDAndTreeFourID(cityID,treeFourID));

            bodyMap.put("standardSize",standards.size());// 小样质量标准数量
            bodyMap.put("sampleLoopStatus",sampleLoopStatus);// 小样回路标识
            bodyMap.put("loopStatus",loopStatus);// 回路总标识
            bodyMap.put("totalScore",totalScore);// 综合总积分


            informationBody.setBody(bodyMap);
            logger.debug("小样标准-子库地区'平台'三段,数据抓取成功!", informationBody);
        } catch (Exception e) {
            informationBody.setBody(e);
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("fail");
            logger.error("小样标准-子库地区'平台'三段,数据抓取失败!", e);
        }
        return informationBody;
    }
    // 包装标准
    /**
     * @author  ljc
     * @param   treeFourID 四级科目ID
     * @return  子库地区平台 三段弹出层 '包装标准'JSON数据
     * @updateTime 2018-6-19 17:58:58
     */
    public InformationBody getCityThreePlatformStaPack(String treeFourID) {
        InformationBody informationBody = new InformationBody();
        try {
            List<TreeStandardItme> packaging = treeStandardItmeDao.findTreeStandardItmesByTypeAndTreeFourID(8,treeFourID);// <包装基础标准>
            TreeBaseInfo treeBaseInfo = treeBaseInfoDao.findTreeBaseInfoByTreeID(treeFourID);// <包装基础参数>
            Map<String, Object> bodyMap = new HashMap<>();  // 响应体Map

            int totalLoopScore = packaging.size(); // 总积分
            int packStaLoopFlag = 1; // 包装标准回路标识 1完(默认)/0未

            // 属性
            List<Map<String,Object>> staPacks = new ArrayList<>();
            for (TreeStandardItme treeStandardItme : packaging) {
                Map<String, Object> packAttributeItem = new HashMap<String, Object>();

                packAttributeItem.put("tsiId", treeStandardItme.getTsiId());// 标准ID
                packAttributeItem.put("attributeName", treeStandardItme.getTsiName());//属性名称
                packAttributeItem.put("attributeValue", treeStandardItme.getTsiValue());//属性值
                packAttributeItem.put("tsiUnit", treeStandardItme.getTsiUnit());// 属性单位ID
                packAttributeItem.put("attributeUnit", treeStandardItme.getTsiUnitname());//属性单位
                packAttributeItem.put("tsiStandardmax", treeStandardItme.getTsiStandardmax());// 范围:最大
                packAttributeItem.put("tsiStandardmin", treeStandardItme.getTsiStandardmin());// 范围:最小

                packAttributeItem.put("tsiSpecialclaim", treeStandardItme.getTsiSpecialclaim());// 特殊要求
                packAttributeItem.put("tsiDetectmethod", treeStandardItme.getTsiDetectmethod());// 检验方法

                staPacks.add(packAttributeItem);
            }
            bodyMap.put("packAttrs",staPacks);
            bodyMap.put("packAttrSize",packaging.size());// 包装标准属性长度
            // 包装标准
            int packStaScore = 0; // 包装标准积分 默认0
            bodyMap.put("packCategory", null!=treeBaseInfo?treeBaseInfo.getTbiPstype():null);//包装类别 包装标准-类别：1瑞祥标准，2合作商标准
            bodyMap.put("packMatQuality", null!=treeBaseInfo?treeBaseInfo.getTbiPsmaterialquality():null);//包装材质
            bodyMap.put("packExplain", null!=treeBaseInfo?treeBaseInfo.getTbiPsmarkremark():null);//包装说明

            // 包装照片
            bodyMap.put("packPhoto",null!=treeBaseInfo&&null!=treeBaseInfo.getTbiPspackageremark()?treeBaseInfo.getTbiPspackageremark():null);//包装照片

            // 回路相关
            // 校验设值:回路标识以及回路总积分
            if (0 == totalLoopScore){
                packStaLoopFlag = 0;
            }
            if (null != treeBaseInfo){
                // 包装类型/包装材料/包装说明为空设值回路标识为0未的状态,反之回路综合总积分+1.
                if (null == treeBaseInfo.getTbiPstype() || StringUtils.isBlank(treeBaseInfo.getTbiPsmaterialquality()) || StringUtils.isBlank(treeBaseInfo.getTbiPsmarkremark())){
                    packStaLoopFlag = 0;
                } else {
                    totalLoopScore ++;
                    packStaScore = 1;
                }
                // 包装照片为空设值回路标识为0未的状态,反之回路综合总积分+1.
                if (StringUtils.isBlank(treeBaseInfo.getTbiPspackageremark())){
                    packStaLoopFlag = 0;
                } else {
                    totalLoopScore ++;
                }
            } else {
                packStaLoopFlag = 0;
            }
            bodyMap.put("packStaLoopFlag",packStaLoopFlag); // 综合总回路标识
            bodyMap.put("totalLoopScore",totalLoopScore); // 综合回路总积分
            bodyMap.put("packStaScore",packStaScore); // 包装标准积分

            informationBody.setBody(bodyMap);
            logger.debug("包装标准-子库地区'平台'三段,数据抓取成功!", informationBody);
        } catch (Exception e) {
            informationBody.setBody(e);
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("fail");
            logger.error("包装标准-子库地区'平台'三段,数据抓取失败!", e);
        }
        return informationBody;
    }
    // 对比标准
    public InformationBody getCityThreePlatformStaContrast(String treeFourID) {
        InformationBody informationBody = new InformationBody();
        try {
            List<MaterialComparedAttributes> mca = materialComparedAttributesDao.findEntitysByTreeFourID(treeFourID);// <标准-对比项(属性对比表（对比图）)>
            Map<String, Object> bodyMap = new HashMap<>();  // 响应体Map
            // 综合
            int totalScore = mca.size();// 积分总计(因无他项,同时做回路标识使用.)
            bodyMap.put("totalScore", totalScore);

            // 属性(对比标准)
            List<Map<String,Object>> compareds =  new ArrayList<>();
            for (MaterialComparedAttributes materialComparedAttributes : mca) {
                Map<String, Object> contrastItem = new HashMap<String, Object>();
                contrastItem.put("mcaID", materialComparedAttributes.getMcaId());//对比标准ID
                contrastItem.put("attributeName", materialComparedAttributes.getMcaName());//对比标准名称
                compareds.add(contrastItem);//对比名称
            }
            bodyMap.put("compareds", compareds);

            informationBody.setBody(bodyMap);
            logger.debug("对比标准-子库地区'平台'三段,数据抓取成功!", informationBody);
        } catch (Exception e) {
            informationBody.setBody(e);
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("fail");
            logger.error("对比标准-子库地区'平台'三段,数据抓取失败!", e);
        }
        return informationBody;
    }
    // 搜索词
    public InformationBody getCityThreePlatformSearch(String treeFourID) {
        InformationBody informationBody = new InformationBody();
        try {
            List<SearchItem>  searchTerms = searchItemDao.findSearchItemsByTreeFourID(treeFourID,1);//<材料搜索词list>
            List<SearchItem>  uses = searchItemDao.findSearchItemsByTreeFourID(treeFourID,2);//<材料用途list>

            Map<String, Object> bodyMap = new HashMap<>();  // 响应体Map

            // 搜索词
            StringBuilder searchItemStringBuilder = new StringBuilder();
            for (SearchItem searchItem : searchTerms) {//组织搜索词数据
                String seContent = searchItem.getSeContent();// 材料搜索内容
                searchItemStringBuilder.append(seContent).append(",");
            }
            int searchItemScore = searchTerms.size() == 0 ? 0 : 1;// 搜索词积分项(不为0记一分)
            String searchItemsStr = searchItemStringBuilder.toString();// 搜索词字符串 格式:'搜索词,搜索词,'
            String searchItemsEchoStr = searchItemsStr.replace(",", "&nbsp;&nbsp;");// 搜索词回显字符串 格式:'搜索词&nbsp;&nbsp;搜索词&nbsp;&nbsp;'
            bodyMap.put("searchItemsStr",searchItemsStr);
            bodyMap.put("searchItemsEchoStr",searchItemsEchoStr);
            bodyMap.put("searchItemScore",searchItemScore);

            // 用途
            StringBuilder usesStringBuilder = new StringBuilder();
            for (SearchItem searchItem : uses) {//组织用途数据
                String seContent = searchItem.getSeContent();//材料用途内容
                usesStringBuilder.append(seContent).append(",");
            }
            int useScore = uses.size() == 0 ? 0 : 1;// 用途积分项(不为0记一分)
            String useItemsStr = usesStringBuilder.toString();// 用途字符串 格式:'用途,用途,'
            String useItemsEchoStr = useItemsStr.replace(",", "&nbsp;&nbsp;");// 用途回显字符串 格式:'用途&nbsp;&nbsp;用途&nbsp;&nbsp;'
            bodyMap.put("useItemsStr",useItemsStr);
            bodyMap.put("useItemsEchoStr",useItemsEchoStr);
            bodyMap.put("useScore",useScore);

            // 综合(无其他验证项,综合总积分做回路总标识使用)
            int totalScore = searchItemScore + useScore;// 综合总积分(搜索词积分 + 用途积分)
            bodyMap.put("totalScore",totalScore);

            informationBody.setBody(bodyMap);
            logger.debug("搜索词-子库地区'平台'三段,数据抓取成功!", informationBody);
        } catch (Exception e) {
            informationBody.setBody(e);
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("fail");
            logger.error("搜索词-子库地区'平台'三段,数据抓取失败!", e);
        }
        return informationBody;
    }

    //-------------------------档次三段弹出层 begin-------------------------
    /**
     * @author     ljc
     * @param      treeFourID          四级分类ID
     * @param      cityID              城市ID
     * @param      levelFlag           档次标识
     * @createTime 2018-5-30 21:37:47
     * @updateTime 2018-7-19 14:04:52
     */
    // 标准
    public InformationBody getCityThreeLevelStandard(String treeFourID, Integer cityID, Integer levelFlag) {
        InformationBody informationBody = new InformationBody();
        Map<String, Object> bodyMap = new HashMap<>();
        try {
            // --- 数据区 ---
            List<TreeStandardItme> standards = treeStandardItmeDao.findLevelQualityStandardItemsByTreeFourIDAndLevelFlag(treeFourID, levelFlag);// <统一质量标准>

            List<ContrastItemWithPhoto> mca = materialComparedAttributesDao.findContrastItemWithPhotoByCityIDAndLevelFlagAndTreeFourID(cityID,levelFlag,treeFourID);// <对比项>

            // --- 变量区 ---
            int levelStaLoopStatus = 1;// 回路总状态 1.完成(默认)/0.未完成
            int levelStandardsScore = 0; // 回路总积分 (默认:0分)

            // --- 组织区 ---

            // 质量标准项
            bodyMap.put("levelStaItems",standards);

            // 对比项
            bodyMap.put("contrastItems",mca);

            // 设置综合回路状态
            // 1.遍历 设值回路总积分
            for (TreeStandardItme standard : standards) {
                if (1 == standard.getLoopStatus())
                    levelStandardsScore ++;
            }
            for (ContrastItemWithPhoto contrastItemWithPhoto : mca) {
                if (1 == contrastItemWithPhoto.getLoopStatus())
                    levelStandardsScore ++;
            }

            // 2.遍历 设置回路总状态标识
            for (TreeStandardItme standard : standards) {
                String tsiValue = standard.getTsiValue();
                if (StringUtils.isBlank(tsiValue)){
                    // 质量标准检验项属性值为空时
                    levelStaLoopStatus = 0;
                    break;
                }
            }
            for (ContrastItemWithPhoto contrastItemWithPhoto : mca) {
                String mcaPhotoURL = contrastItemWithPhoto.getMcaPhotoURL();// 对比图地址
                String mcaDescribe = contrastItemWithPhoto.getMcaDescribe(); // 对比描述
                if(StringUtils.isBlank(mcaPhotoURL) || StringUtils.isBlank(mcaDescribe)){
                    // 对比图为null 或 对比描述为null时
                    levelStaLoopStatus = 0;
                    break;
                }
            }

            // 综合项
            bodyMap.put("totalScore",levelStandardsScore);
            bodyMap.put("levelStaLoopStatus",levelStaLoopStatus);

            informationBody.setBody(bodyMap);
            logger.debug("子库地区'档次'三段弹层 - 标准,数据抓取成功!", informationBody);
        } catch (Exception e) {
            informationBody.setBody(e);
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("fail");
            logger.error("子库地区'档次'三段弹层 - 标准,数据抓取失败!", e);
        }
        return informationBody;
    }

    // 品牌项
    public InformationBody getCityThreeLevelBrands(String matID) {
        InformationBody informationBody = new InformationBody();
        try {
            Map<String, Object> bodyMap = new HashMap<>();
            Material material = materialDao.get(matID);// <材料>
            MaterialSmallSample materialSmallSample = materialSmallSampleDao.getMaterialSampleByMatID(matID);// <材料小样>
            MatBaseData matBaseData = sublibraryDao.findMatBaseDetailDataByTreeFourID(material.getMTreefour(),material.getMCityid());// <材料详细数据基础 - 作用表现层>
            TreeSst treeSst = treeSstDao.findTreeSstByTreeID(material.getMTreefour());//<科目树设置(材料分类)>
            List<TreeStandardPhoto> brandPhotos = treeStandardPhotoDao.findMatPhotoByMatIDOrPhotoType(matID, 2);// 材料(品牌)照片 2子库材料

            String brandLOGOPhotoID = null;// 品牌LOGO图片ID
            String brandLOGOPhotoURL =null;// 品牌LOGO图片地址
            List<Map<String,String>> branMainPhotos = new ArrayList<>();// 产品主图集
            List<Map<String,String>> branTypePhotos = new ArrayList<>();// 品牌型号图集(要求数量4)
            ArrayList<Integer> branTypePhotoLackArr = new ArrayList<>();// 品牌型号图缺少数量集
            // 设值 品牌信息相关照片
            for (TreeStandardPhoto treeStandardPhoto : brandPhotos) {
                Integer tspType = treeStandardPhoto.getTspType();// 图片类型：1品牌LOGO，2品牌型号，3材料对比图，4产品主图，5材料资质，6小样图，7产品效果图，8品质细节图，9材料工艺；10照片展示自定义类，11材料约定
                String tspId = treeStandardPhoto.getTspId();// 图片ID
                String tspPhotourl = treeStandardPhoto.getTspPhotourl();// 图片地址
                String tspTitle = treeStandardPhoto.getTspTitle();// 品牌型号名称(针对 2品牌型号)

                // 设置 品牌LOGO相关
                if (1 == tspType){
                    brandLOGOPhotoID = tspId;
                    brandLOGOPhotoURL = tspPhotourl;
                }
                // 设置 产品主图相关
                if (4 == tspType){
                    Map<String, String> branMainPhoto = new HashMap<>();
                    branMainPhoto.put("branMainPhotoID",tspId);
                    branMainPhoto.put("branMainPhotoURL",tspPhotourl);
                    branMainPhotos.add(branMainPhoto);
                }
                // 设置 品牌型号图相关
                if (2 == tspType){
                    Map<String, String> branTypePhoto = new HashMap<>();
                    branTypePhoto.put("branTypePhotoID",tspId);
                    branTypePhoto.put("branTypePhotoURL",tspPhotourl);
                    branTypePhoto.put("branTypePhotoName",tspTitle);
                    branTypePhotos.add(branTypePhoto);
                }
            }

            // 回路标识 1完/0未 默认1
            int brandInfoLoop = 1; // 品牌信息
            int typeInfoLoop = 1; // 型号信息

            // 展示层:商城详情处品牌缩略
            bodyMap.put("mallBrand",matBaseData);

            // 编辑层:品牌信息
            String mBrandname = material.getMBrandname(); // 品牌名称
            String mMatdescription = material.getMMatdescription(); // 品牌描述
            String mBrandtype = material.getMBrandtype(); // 型号名称

            bodyMap.put("brandName",mBrandname); // 品牌名称
            bodyMap.put("brandDescription", mMatdescription);
            bodyMap.put("brandLOGOPhotoID",brandLOGOPhotoID);// 品牌LOGO图片ID
            bodyMap.put("brandLOGOPhotoURL",brandLOGOPhotoURL);// 品牌LOGO图片地址
            bodyMap.put("branMainPhotos",branMainPhotos);// 产品主图集
            Map<String, String> firstBrandMainPhotos = null;
            if (0 != branMainPhotos.size()){
                firstBrandMainPhotos = branMainPhotos.get(0);
            }
            bodyMap.put("firstBrandMainPhotos",firstBrandMainPhotos); // 第一个品牌主图
            // 校验设值:品牌信息回路状态(未:品牌名称为空|品牌LOGO图片为空|产品主图数量小于1)
            if (StringUtils.isBlank(mBrandname) ||  StringUtils.isBlank(brandLOGOPhotoURL) || branMainPhotos.size() < 1)
                brandInfoLoop = 0;
            bodyMap.put("brandInfoLoop",brandInfoLoop);


            // 编辑层:型号信息
            bodyMap.put("branTypePhotos",branTypePhotos);// 品牌型号图集
            bodyMap.put("branTypePhotoSize",branTypePhotos.size()); // 型号图片数量


            // 品牌型号缺少的数组, 用于vue遍历带添加按钮的图片展示框
            if (4 != branTypePhotos.size()){
                for (int i= 0; i < 4 - branTypePhotos.size(); i ++)
                    branTypePhotoLackArr.add(i);
            }
            bodyMap.put("branTypePhotoAddArr",branTypePhotoLackArr);
            // 校验设值:型号信息回路状态(未:型号图片小于1|材料型号名称为null)
            if (1 > branTypePhotos.size() || StringUtils.isBlank(mBrandtype))
                typeInfoLoop = 0;
            bodyMap.put("typeInfoLoop",typeInfoLoop);

            bodyMap.put("matID",material.getMId());// 品牌ID
            bodyMap.put("mLevel",material.getMLevel());// 品牌档次
            bodyMap.put("mBrandType",material.getMBrandtype());// 品牌型号
            bodyMap.put("mHomeHostState",material.getMHomehoststate());// 是否推荐首页 1是
            bodyMap.put("mHostState",material.getMHoststate());// 是否主推 1是
            bodyMap.put("mMatType",material.getMMattype());// 材料类型：1品牌材料，2瑞祥专供材料    （默认0）
            bodyMap.put("mCode",material.getMCode());// 材料编号

            // 价格
            int brandPriceLoopFlag = 1; // 品牌价格回路标识
            double mQuotesprice = material.getMQuotesprice(); // 报价
            double mCostprice = material.getMCostprice(); // 成本
            int classify = null == treeSst ? 0 : treeSst.getTsTypestate();// 材料分类 1成品，2非成品  （默认0）
            double mInstallprice = material.getMInstallprice(); // 安装/加工费

            // 设值 品牌价格回路标识
            if (0 > mQuotesprice||0 > mCostprice)
                brandPriceLoopFlag = 0;

            bodyMap.put("brandPriceLoopFlag",brandPriceLoopFlag);
            bodyMap.put("classify",classify); // 品牌粉分类
            bodyMap.put("mQuotesPrice",mQuotesprice); // 报价
            bodyMap.put("mCostPrice",mCostprice); // 成本
            bodyMap.put("mInstallPrice",mInstallprice); // 安装价

            // 其他
            bodyMap.put("mUpdateState",material.getMUpdatestate()); // 是否已编辑：1是，-1代表地方提交审核       （默认0）（为0时：母库更新数据同步子库内容）
            bodyMap.put("mShoppingState",material.getMShoppingstate()); // 是否已上架：1已上架，2预下架    （默认0）

            informationBody.setBody(bodyMap);
            logger.debug("子库>地区>档次>品牌项(三段),数据抓取成功!", informationBody);
        } catch (Exception e) {
            informationBody.setBody(e);
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("fail");
            logger.error("子库>地区>档次>品牌项(三段),数据抓取失败!", e);
        }
        return informationBody;
    }

    //-------------------------报价(原成本)三段弹出层 begin-------------------------
    /**
     * @author     ljc
     * @param      treeFourID          四级分类ID
     * @param      cityID              城市ID
     * @param      levelFlag           档次标识
     * @createTime 2018-6-25 09:34:36
     */
    // 档次项
    public InformationBody getcityThreeCostLevels(String treeFourID, Integer cityID, Integer levelFlag) {
        InformationBody informationBody = new InformationBody();
        try {
            List<Material> perfectMats = materialDao.findPerfectMatByCityIDOrTreeFourIDOrLevel(cityID, treeFourID, levelFlag); // 完善材料集
            Map<String, Object> bodyMap = new HashMap<>();

            // 设置材料图片相关数据
            for (Material perfectMat : perfectMats) {
                String matID = perfectMat.getMId(); // 材料ID
                String mBrandtype = perfectMat.getMBrandtype(); // 材料型号
                List<TreeStandardPhoto> brandPhotos = treeStandardPhotoDao.findMatPhotoByMatIDOrPhotoType(matID, 2);// 材料(品牌)照片 2子库材料
                String brandLOGOPhotoID = null;// 品牌LOGO图片ID
                String brandLOGOPhotoURL =null;// 品牌LOGO图片地址
                List<TreeStandardPhoto> branTypePhotos = new ArrayList<>();// 品牌型号图集(要求数量4)
                for (TreeStandardPhoto treeStandardPhoto : brandPhotos) {
                    Integer tspType = treeStandardPhoto.getTspType();// 图片类型：1品牌LOGO，2品牌型号，3材料对比图，4产品主图，5材料资质，6小样图，7产品效果图，8品质细节图，9材料工艺；10照片展示自定义类，11材料约定
                    String tspId = treeStandardPhoto.getTspId();// 图片ID
                    String tspPhotourl = treeStandardPhoto.getTspPhotourl();// 图片地址
                    String tspTitle = treeStandardPhoto.getTspTitle();// 品牌型号名称(针对 2品牌型号)
                    // 设置 品牌LOGO相关 1
                    if (1 == tspType){
                        perfectMat.setBrandLOGOPhotoID(tspId);
                        perfectMat.setBrandLOGOPhotoURL(tspPhotourl);
                    }
                    // 设置 品牌型号图相关 2(型号名称等于材料型号名称)
                    if (2 == tspType){
                        branTypePhotos.add(treeStandardPhoto);
                    }
                    // 设置 产品主图相关 4(当前材料品牌主图为null时,设置材料产品主图 )
                    if (StringUtils.isBlank(perfectMat.getBranMainPhotoURL()) && 4 == tspType){
                        perfectMat.setBranMainPhotoID(tspId);
                        perfectMat.setBranMainPhotoURL(tspPhotourl);
                    }
                }
                // 设值 取第一张做材料品牌型号相片
                if (0 < branTypePhotos.size()){
                    TreeStandardPhoto treeStandardPhoto = branTypePhotos.get(0);
                    perfectMat.setBranTypePhotoID(treeStandardPhoto.getTspId());
                    perfectMat.setBranTypePhotoURL(treeStandardPhoto.getTspPhotourl());
                }

            }

            // 完善材料集
            bodyMap.put("perfectMats",perfectMats); // 完善材料集
            int perfectMatSize = perfectMats.size();// 完善材料数量
            bodyMap.put("perfectMatSize",perfectMatSize); // 完善材料数量

            // 缺乏的完善材料集,用于vue遍历无录入的品牌项.
            List<Integer> LackPerfectMats = new ArrayList<>();
            if (10 != perfectMatSize){
                for (int i= 0; i < 10 - perfectMatSize; i ++)
                    LackPerfectMats.add(i);
            }
            bodyMap.put("lackPerfectMats",LackPerfectMats);// 缺乏的完善材料集

            bodyMap.put("currentLevelFlag",levelFlag); // 当前档次标识 1.A 2.B 4.C

            informationBody.setBody(bodyMap);
            logger.debug("子库>地区>报价>档次项(三段弹出层),数据抓取成功!", informationBody);
        } catch (Exception e) {
            informationBody.setBody(e);
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("fail");
            logger.error("子库>地区>报价>档次项(三段弹出层),数据抓取失败!", e);
        }
        return informationBody;
    }

    // 基础
    /**
     * 子库>地区>报价>基础(三段弹出层)
     * @author     ljc
     * @createTime 2018-7-3 17:59:14
     * @describe   应用Lambda表达式,操作集合.
     */
    public InformationBody getCityThreeCostBase(String treeFourID, Integer cityID) {
        InformationBody informationBody = new InformationBody();
        try {
            MatBaseData matBaseData = sublibraryDao.findMatBaseDetailDataByTreeFourID(treeFourID,cityID);// <基础>
            List<SearchItem>  searchTerms = searchItemDao.findSearchItemsByTreeFourID(treeFourID,1);//<搜索词>
            List<SearchItem>  uses = searchItemDao.findSearchItemsByTreeFourID(treeFourID,2);//<用途>
            List<TreeStandardItme> quality = treeStandardItmeDao.findTreeStandardItmesByTypeAndTreeFourID(1,treeFourID); // <材料标准集>

            Map<String, Object> bodyMap = new HashMap<>();
            // 基础 & 详情
            bodyMap.put("baseData",matBaseData);

            int costBaseLoopFlag = 1; // 成本 综合总 回路标识:1完(默认)/0未

            // 详情相关 >
            // 功能
            int matFunctionScore = 1;
            if (StringUtils.isBlank(matBaseData.getMatFunction()))
                matFunctionScore = 0;
            bodyMap.put("matFunctionScore",matFunctionScore); // '功能'回路积分项(默认1分,为空0分)

            // 描述
            int matDescriptionScore = 1;
            if (StringUtils.isBlank(matBaseData.getMatDescription()))
                matDescriptionScore = 0;
            bodyMap.put("matDescriptionScore",matDescriptionScore); // '描述'回路积分项(默认1分,为空0分)

            // 搜索词
            StringBuilder searchStr = new StringBuilder();
            searchTerms.forEach(item->{ searchStr.append(item.getSeContent()).append(" "); });
            bodyMap.put("searchStr",searchStr.toString().trim());

            int searchTermsScore = 1;
            if(0 == searchTerms.size())
                searchTermsScore = 0;
            bodyMap.put("searchTermsScore",searchTermsScore); // '搜索词'回路积分(默认0分,数量大于0时1分)

            // 用途
            StringBuilder usesStr = new StringBuilder();
            uses.forEach(item->{ usesStr.append(item.getSeContent()).append(" "); });
            bodyMap.put("usesStr",usesStr.toString().trim());

            int usesScore = 1;
            if(0 ==uses.size())
                usesScore = 0;
            bodyMap.put("usesScore",usesScore); // '用途'回路积分(默认0分,数量大于0时1分)
            // 设值 综合总回路标识(功能/描述/搜错词/用途回路积分,有一项不为正常分值1,设值回路总标识为'0 -- 未'的状态)
            if (matFunctionScore != 1 || matDescriptionScore != 1 || searchTermsScore != 1 || usesScore != 1)
                costBaseLoopFlag = 0;

            // 材料标准 >
            List<TreeStandardItme> newQuality = quality.stream().filter(item -> item.getTsiMatlevel().equals(0)).collect(Collectors.toList());
            int matStandardSize = newQuality.size();
            bodyMap.put("matStandards",newQuality);
            bodyMap.put("matStandardSize",matStandardSize); // 材料标准数量(做回路标识积分)
            // 设值 综合总回路标识(材料标准数量为0,设值回路总标识为'0 -- 未'的状态)
            if (costBaseLoopFlag != 0 && matStandardSize == 0)
                costBaseLoopFlag = 0;


            // 综合 >
            bodyMap.put("totalScore",matFunctionScore + matDescriptionScore + searchTermsScore + usesScore + matStandardSize); // 综合总积分
            bodyMap.put("costBaseLoopFlag",costBaseLoopFlag); // 综合总 回路标识1完 0未
            informationBody.setBody(bodyMap);
            logger.debug("子库>地区>成本>档次项(三段弹出层) 数据抓取成功");
        } catch (Exception e) {
            informationBody.setBody(e);
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("fail");
            logger.error("子库>地区>报价>基础(三段弹出层),数据抓取失败!", e);
        }
        return informationBody;
    }

    //-------------------------报价(原成本)四段段弹出层 begin-------------------------
    /**
     * @author ljc
     * @param  matID 材料ID
     */
    // 地区 > 报价 > 档次项 > 需求-品牌
    public InformationBody getCityFourCostBrand(String matID) {
        InformationBody informationBody = new InformationBody();
        try {
            Material perfectMat = materialDao.get(matID); // <品牌材料>
            List<TreeStandardPhoto> brandPhotos = treeStandardPhotoDao.findMatPhotoByMatIDOrPhotoType(matID, 2);// <材料(品牌)照片 2子库材料>

            Map<String, Object> bodyMap = new HashMap<>();

            String brandLOGOPhotoID = null;// 品牌LOGO图片ID
            String brandLOGOPhotoURL =null;// 品牌LOGO图片地址
            List<TreeStandardPhoto> branTypePhotos = new ArrayList<>();// 品牌型号图集(要求数量4)

            // 组织数据 到 材料实体 附加属性
            for (TreeStandardPhoto treeStandardPhoto : brandPhotos) {
                // 赋值调用
                Integer tspType = treeStandardPhoto.getTspType();// 图片类型：1品牌LOGO，2品牌型号，3材料对比图，4产品主图，5材料资质，6小样图，7产品效果图，8品质细节图，9材料工艺；10照片展示自定义类，11材料约定
                String tspId = treeStandardPhoto.getTspId();// 图片ID
                String tspPhotourl = treeStandardPhoto.getTspPhotourl();// 图片地址
                String tspTitle = treeStandardPhoto.getTspTitle();// 品牌型号名称(针对 2品牌型号)

                // 设置 品牌LOGO相关 1
                if (1 == tspType){
                    perfectMat.setBrandLOGOPhotoID(tspId);
                    perfectMat.setBrandLOGOPhotoURL(tspPhotourl);
                }
                // 设置 品牌型号图相关 2(型号名称等于材料型号名称)
                if (2 == tspType){
                    branTypePhotos.add(treeStandardPhoto);
                }
                // 设置 产品主图相关 4(当前材料品牌主图为null时,设置材料产品主图 )
                if (StringUtils.isBlank(perfectMat.getBranMainPhotoURL()) && 4 == tspType){
                    perfectMat.setBranMainPhotoID(tspId);
                    perfectMat.setBranMainPhotoURL(tspPhotourl);
                }
            }
            // 设值 取第一张做材料品牌型号相片
            if (0 < branTypePhotos.size()){
                TreeStandardPhoto treeStandardPhoto = branTypePhotos.get(0);
                perfectMat.setBranTypePhotoID(treeStandardPhoto.getTspId());
                perfectMat.setBranTypePhotoURL(treeStandardPhoto.getTspPhotourl());
            }

            informationBody.setBody(perfectMat); // 品牌材料数据
            logger.debug("地区>报价>档次项>需求-品牌(四段弹出层),数据抓取成功!");

        } catch (Exception e) {
            informationBody.setBody(e);
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("fail");
            logger.error("地区>报价>档次项>需求-品牌(四段弹出层),数据抓取失败!", e);
        }
        return informationBody;
    }

    //-------------------------子库列表:二维码打印 begin-------------------------
    /**
     * 子库列表:查找二维码打印列表 通过 子库地区检索包装类
     * @author ljc
     * @param  subLibraryCityQuery 子库地区检索包装类
     * @return 二维码打印列表
     */
    public InformationBody findQRCodePrintListBySubLibraryCityQuery(SubLibraryCityQuery subLibraryCityQuery) {
        InformationBody informationBody = new InformationBody();
        try {
            // 判断 是否多材料名称检索
            String matName = subLibraryCityQuery.getMatName();
            if (StringUtils.isNotBlank(matName)){
                String[] split = matName.split(",");
                // 前台传入 材料名称包含','分隔符且按','分隔后的数组数量不等于1 进行多材料名称检索
                subLibraryCityQuery.setMatNames(matName.indexOf(",") == - 1 && split.length == 1 ? null : Arrays.asList(split));
                // 前台传入 多个材料名称 不再执行 材料名称/品牌名称模糊检索
                subLibraryCityQuery.setMatName(split.length != 1 ? null : split[0]);
            }
            List<QRCodePrintList> qrCodePrintLists = sublibraryDao.findQRCodePrintListBySubLibraryCityQuery(subLibraryCityQuery); // <二维码打印列表>
            Map<String, Object> bodyMap = new HashMap<>();
            bodyMap.put("qrCodePrintLists",qrCodePrintLists);
            bodyMap.put("qrCodePrintListSize",qrCodePrintLists.size());
            informationBody.setBody(bodyMap);
            logger.debug("子库列表:查找二维码打印列表,通过子库地区检索包装类,数据抓取成功!");
        } catch (Exception e) {
            informationBody.setBody(e);
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("fail");
            logger.error("子库列表:查找二维码打印列表,通过子库地区检索包装类,数据抓取失败!", e);
        }
        return informationBody;
    }

    /**
     * 导出 二维码打印列表EXCEL
     * @author ljc
     * @param  subLibraryCityQuery 子库地区检索包装类
     * @return
     * @createTime 2018-7-12 11:23:43
     */
    public List<QRCodePrintList> exportQRCodePrintListExcel(SubLibraryCityQuery subLibraryCityQuery) {
        return sublibraryDao.findQRCodePrintListBySubLibraryCityQuery(subLibraryCityQuery);
    }

    /**
     * 子库列表:'检索'材料回路列表
     * @author     ljc
     * @param      matLoopQuery  材料回路检索包装类
     * @return     材料回路列表
     * @createTime 2018-7-31 19:11:04
     */
    public InformationBody findMatLoopListByMatLoopQuery(MatLoopQuery matLoopQuery) {
        long startTime = System.currentTimeMillis();
        InformationBody informationBody = new InformationBody();
        Map<String, Object> body = new HashMap<>();
        try {
            // --- 赋值调用 ---
            Integer handleTimeFlag = matLoopQuery.getHandleTimeFlag(); // 处理日期(1当天2本周3本季4本年)

            // --- 变量区 ---
            Date startTimeQuery = null; // 处理时间 开始检索时间
            Date endTimeQuery = null; // 处理时间 结束检索时间

            if (null != handleTimeFlag){
                // 1.判断 处理时间检索类型(字段不为空时进行)
                switch (handleTimeFlag){
                    case 1: // 当天
                        startTimeQuery = DateUtils.getTimesmorning();
                        endTimeQuery = DateUtils.getTimesnight();
                        break;
                    case 2: // 本周
                        startTimeQuery = DateUtils.getTimesWeekmorning();
                        endTimeQuery = DateUtils.getTimesWeeknight();
                        break;
                    case 3: // 本季
                        startTimeQuery = DateUtils.getCurrentQuarterStartTime();
                        endTimeQuery = DateUtils.getCurrentQuarterEndTime();
                        break;
                    case 4: // 本年
                        startTimeQuery = DateUtils.getCurrentYearStartTime();
                        endTimeQuery = DateUtils.getCurrentYearEndTime();
                        break;
                }
                // 2.设置 处理时间检索维度
                matLoopQuery.setStartTime(startTimeQuery);
                matLoopQuery.setEndTime(endTimeQuery);
            }

            // 根据是否传入地区ID 判断是否获取全国数据
            if (matLoopQuery.getCityID() == null){
                List<QGMatLoopList> qgLoopMatList = sublibraryDao.getQGLoopMatList();
                body.put("qgLoopMatList", qgLoopMatList);
                informationBody.setBody(body);
                long endTime = System.currentTimeMillis();
                logger.debug("子库列表:获取全国材料回路列表,程序运行时间:" + (endTime - startTime) + "ms");
                return informationBody;
            }

            List<MatLoopList> matLoopLists = sublibraryDao.findMatLoopListsByMatLoopQuery(matLoopQuery);
            informationBody.setBody(matLoopLists);
            long endTime = System.currentTimeMillis();
            logger.debug("子库列表:查找检索'材料回路列表,通过子库地区检索包装类,数据抓取成功!程序运行时间:" + (endTime - startTime) + "ms");
        } catch (Exception e) {
            informationBody.setBody(e);
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("fail");
            logger.error("子库列表:查找检索'材料回路列表,通过子库地区检索包装类,数据抓取失败!", e);
        }
        return informationBody;
    }


    // 平台回路状态 内部类
    private class PlatformLoopStatus<T> {
        private List<Map<String, Object>> materialParameterList;
        private TreeBaseInfo treeBaseInfo;
        private TreeSst treeSst;
        private List<TreeStandardItme> quality;
        private List<T> mca;
        private List<TreeStandardItme> sample;
        private List<SearchItem> searchTerms;
        private List<SearchItem> uses;
        private int baseFlag;
        private int statusFlag;

        public PlatformLoopStatus(List<Map<String, Object>> materialParameterList, TreeBaseInfo treeBaseInfo, TreeSst treeSst, List<TreeStandardItme> quality, List<T> mca, List<TreeStandardItme> sample, List<SearchItem> searchTerms, List<SearchItem> uses) {
            this.materialParameterList = materialParameterList;
            this.treeBaseInfo = treeBaseInfo;
            this.treeSst = treeSst;
            this.quality = quality;
            this.mca = mca;
            this.sample = sample;
            this.searchTerms = searchTerms;
            this.uses = uses;
        }

        public int getBaseFlag() {
            return baseFlag;
        }

        public int getStatusFlag() {
            return statusFlag;
        }

        public PlatformLoopStatus invoke() {
            Integer tbiUnit = null;
            String  unitName = null;
            String  tbiMatfunction = null;
            String  tbiMatdescription = null;
            Integer typeState = null == treeSst ? 0 : treeSst.getTsTypestate();// 材料分类 1成品，2非成品  （默认0）
            Integer materialParameterSize = materialParameterList.size();// 材料参数数
            // 质量标准
            Integer qualitySize = quality.size();// 质量标准数
            // 小样标准
            Integer sampleSize = sample.size();// 小样标准数
            String samplingMethod = null == treeBaseInfo ? null : treeBaseInfo.getTbiMockupsamplingremark();
            // 取样方法
            // 对比标准
            Integer  comparerSize = mca.size();// 对比标准数
            // 搜索词
            Integer searchSize = searchTerms.size();// 搜索词数
            Integer useSize = uses.size();// 用途数

            //>>>平台二段
            baseFlag = 1;

            // 状态(针对平台下'基础/质量标准/小样标准/对比标准/搜索词'是否完成)
            statusFlag = 1;

            // --- 校验 begin
            // 基础: 基础信息未完善 | 材料参数数小于1,设值 未完成状态
            if (null != treeBaseInfo) {
                tbiUnit = treeBaseInfo.getTbiUnit();
                unitName = treeBaseInfo.getUnitName();
                tbiMatfunction = treeBaseInfo.getTbiMatfunction();
                tbiMatdescription = treeBaseInfo.getTbiMatdescription();
            } else {
                statusFlag = 0;
                baseFlag = 0;
            }
            if (null == tbiUnit || null == unitName || null == tbiMatfunction || null == tbiMatdescription || 0 == typeState || 0 == materialParameterSize ){
                statusFlag = 0;
                baseFlag = 0;
            }

            // 质量标准: 标准为小于4,设值 未完成状态
            if (0 == qualitySize || 4 > qualitySize)
                statusFlag = 0;

            // 小样标准: 标准数|没有取样方法,设值 未完成状态
            if (0 == qualitySize || null == samplingMethod)
                statusFlag = 0;

            // 对比标准: 对比项数据等于0,设值 未完成
            if (0 == comparerSize)
                statusFlag = 0;

            // 搜索词: 搜索词数|用途数等于0,设值 未完成
            if (0 == searchSize || 0 == useSize)
                statusFlag = 0;
            return this;
        }
    }
}