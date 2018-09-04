/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;


import com.thinkgem.jeesite.common.db.DynamicDataSource;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.Encodes;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.api.project.dao.PmUserInfoFullDao;
import com.thinkgem.jeesite.modules.api.project.entity.PmUserInfoFull;
import com.thinkgem.jeesite.modules.api.jmat.dao.MaterialToSuppliersDao;
import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.InformationBody;
import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.SubLibraryRecentData;
import com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.city.CityNewlyIncreased;
import com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.city.CityNewlyIncreasedCorrelationEcho;
import com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.city.SubLibraryCityQuery;
import com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.city.SublibrariesAList;
import com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.list.MatLoopQuery;
import com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.list.QGMatLoopList;
import com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.list.QGMatLoopListSUM;
import com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.list.QRCodePrintList;
import com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.origin.*;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.JedisUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.web.Constants;
import com.thinkgem.jeesite.modules.api.jmat.dao.CityToMatCityDao;
import com.thinkgem.jeesite.modules.api.jmat.dao.UnitDao;
import com.thinkgem.jeesite.modules.api.jmat.service.SubLibraryService;
import com.thinkgem.jeesite.modules.api.jmat.service.TreeService;
import com.thinkgem.jeesite.modules.api.project.dao.RsglDiQuDao;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import javax.servlet.http.HttpServletResponse;


/**
 * 子库Controller
 * @author ljc
 * @version 2018-03-15
 */
@Controller
@RequestMapping(value = "sublibrary-api")
@Api(value="子库Controller",description="子库API")
public class SubLibraryController extends BaseController {
	
	//Redis 键前缀
	public static final String KEY_PREFIX = Global.getConfig("redis.keyPrefix");
	
	//---控制层对象---
	@Autowired
	private CommonController commonController;
	
	//---业务层对象---
	@Autowired//子库业务类
	private SubLibraryService subLibraryService;
	@Autowired//科目树业务类
	private TreeService treeService;
	
	
	//---数据访问对象---
	@Autowired//地区关联
	private CityToMatCityDao cityToMatCityDao;
	@Autowired//单位
	private UnitDao unitDao;
    @Autowired// 材料商材料关联表 (供货商相关)
    private MaterialToSuppliersDao materialToSuppliersDao;

    // 工程
    @Autowired// 项目人事(全)
    private PmUserInfoFullDao pmUserInfoFullDao;
    @Autowired//地区(人事管理)
    private RsglDiQuDao rsglDiQuDao;
	
	//-------------------------子库起始页 begin-------------------------
    /**
     *子库起始页一段
     * @author     ljc
     * @version    1.0
     * @createTime 2018-4-23 11:31:50
     *@return      InformationBody     body:地区/种类/完成总数
     */
    @RequestMapping(value = {"index_one_section"})
    @ApiOperation(value="子库起始页:一段API",httpMethod="GET",notes="获取子库起始页一段数据I<br/>@author ljc<br/>@version 2018-4-23<br/>",response=InformationBody.class)
    public @ResponseBody InformationBody indexOneSection(){
        List<Map<String,Object>> finalBody = new ArrayList<>(); // 最终响应体
        // 1.获取 子库起始页一段(Redis缓存中获取城市结果集)
        InformationBody informationBody = subLibraryService.getIndexOneSection();
        // 2.遍历 处理冗余城市,设置城市名称到最终响应体
        for (Map<String, Object> map : (List<Map<String,Object>>)informationBody.getBody()) {
            Integer resultCityID = (Integer) map.get("cityID");
            commonController.getCitys().forEach(item -> { Integer appointCityID = (Integer)item.get("cityID");if (appointCityID.equals(resultCityID)){ map.put("cityName",commonController.getCityNameByCityID(resultCityID));finalBody.add(map); } });
        }
        informationBody.setBody(finalBody);
        return informationBody;
    }

    /**
     * 子库起始页:销毁起始页一段列表缓存
     * @author     ljc
     * @return     InformationBody
     * @createTime 2018-8-4 11:26:04
     */
    @RequestMapping(value = {"destroy_index_list_cache"})
    @ApiOperation(value="子库起始页:销毁起始页一段列表缓存API",httpMethod="GET",notes="<br/>@author ljc<br/>@version 2018-8-4 11:31:45<br/>")
    public void destroyIndexListCache(HttpServletResponse response){
        try {
            response.getWriter().print("库起始页:销毁起始页一段列表缓存,受影响行数:" + JedisUtils.del(KEY_PREFIX + Constants.INDEX_LIST));
        } catch (IOException e) {
            logger.error("子库起始页:销毁起始页一段列表缓存,失败!",e);
        }
    }




    /**
	 * 
	 * 子库起始页二段-状态与材料统计API
	 * @author     ljc
	 * @version    1.0
	 * @param      cityID             城市ID
	 * @return     InformationBody    状态/材料统计-页面展示数据
     * @createTime 2018-4-9 10:06:17
     * @updateTime 2018-8-4 14:52:35  更新:所有完善与未完善修改逻辑 为 回路相关总计
     */
	@RequestMapping(value={"index_two_section/status_and_statistics"})
	@ApiOperation(value="子库起始页二段-状态与材料统计API",httpMethod="GET",notes="子库起始页-状态与材料统计二段,根据城市ID<br/>@author ljc<br/>@version 2018-4-10<br/>"
			+ "二段状态:<br/>"
			+ "s_materialStatistics 材料统计:{<br/>classs 种类:{class 种类数量,perfect 完善数量,Imperfect 未完善数量},<br/>brands 品牌:{brand 品牌数量,perfect 完善数量,Imperfect 未完善数量.},<br/>platforms 平台:{platform 平台数量,perfect 完善数量,Imperfect 未完善数量}}<br/><br/>"
			+ "s_theLastMonth 最近一月:{mat 材料调整,photo 照片调整,price 价钱调整}<br/>"
			+ "s_cost 成本:{cityRanking 城市排名,total 总计,highPrice 价高,lowPrice 价低}<br/><br/>"
			+ "二段材料统计:<br/>"
			+ "m_class 种类:{class 种类数量,perfect 完善数量,Imperfect 未完善数量} - (二段状态抓取)<br/>"
			+ "m_brand 品牌:{brand 品牌数量,perfect 完善数量,Imperfect 未完善数量,highPrice 价高,lowPrice 价低,matAdjust 近月调整} - (二段状态抓取)<br/>"
			+ "m_platform 平台:{platform 平台数量,perfect 完善数量,Imperfect 未完善数量,topCount 置顶数量} - (二段状态抓取,置顶数量除外)",response=InformationBody.class)
	public @ResponseBody InformationBody indexTwoSectionStatusAndStatistics(
			@ApiParam(required=true,value="城市ID",name="cityID")@RequestParam Integer cityID){
		return subLibraryService.findIndexTwoSectionStatusAndStatisticsByCityID(cityID);
	}
	
	/**
	 * 子库起始页二段-操作记录API
	 * @author     ljc
	 * @version    1.0
	 * @param      subLibraryRecentData   cityID 城市ID,startTime 开始时间,terminalTime 结束时间
	 * @createTime 2018-4-11 19:41:51 
	 * @return     InformationBody        材料统计-页面展示数据
	 */
	@RequestMapping(value="index_two_section/operation_note")
	@ApiOperation(value="子库起始页二段-操作记录API",httpMethod="POST",notes="子库起始页二段-材料统计<br/>@author ljc<br/>@version 2018-4-11<br/>"
			+ "需要字段:cityID 城市ID,startTime 开始时间,terminalTime 结束时间<br/>"
			+ "body:materialCount 材料总数,photoCount 照片总数(该项为调整数量,包含删除操作.),priceCount 价格总数<br/>",response=InformationBody.class)
	public @ResponseBody InformationBody indexTwoSectionOperationNote(
			@ApiParam(required=true,value="子库最近操作模型",name="subLibraryRecentData")@RequestBody SubLibraryRecentData subLibraryRecentData){
		return subLibraryService.findIndexTwoSectionOperationNoteByCityID(subLibraryRecentData);
	}
	
	
	
	/**
	 * 子库起始页三段-操作记录-材料API
	 * @version    1.0
	 * @createTime 2018-4-11 13:08:41
	 * @return     
	 */
	@RequestMapping(value="index_three_section/operation_note/material")
	@ApiOperation(value="子库起始页三段-操作记录-材料API",httpMethod="POST",notes=""
			+ "查找材料操作记录,需要字段:<br/>cityID 城市ID,startTime 开始时间,terminalTime 结束时间,beginRowNum 开始行数,endRowNum 结束行数.<br/>@author  ljc<br/>@version 2018-4-11<br/>"
			+ "body:响应数据:一级分类/二级分类/材料名称/材料规格/分类名称/品牌名称/行为内容/操作时间<br/>详见响应模型<br/>",response=OperationNoteMaterial.class)
	public @ResponseBody InformationBody operationNoteMaterial(
			@ApiParam(required=true,value="子库最近操作模型",name="subLibraryRecentData")@RequestBody SubLibraryRecentData subLibraryRecentData){
		return subLibraryService.findMaterialOperationNoteBySubLibraryRecentData(subLibraryRecentData);
	}
	
	/**
	 * 子库起始页三段-操作记录三段-照片API
	 * @version    1.0
	 * @createTime 2018-4-11 13:10:18
	 * @returnoperation_note/material
	 */
	@RequestMapping(value="index_three_section/operation_note/photo")
	@ApiOperation(value="子库起始页三段-操作记录-照片API",httpMethod="POST",notes=""
			+ "查找材料照片操作记录,需要字段:<br/>cityID 城市ID,startTime 开始时间,terminalTime 结束时间,beginRowNum 开始行数,endRowNum 结束行数.<br/>@author  ljc<br/>@version 2018-4-11<br/>"
			+ "body:响应数据:一级分类/二级分类/材料名称/材料规格/图片类型/行为内容/操作时间<br/>详见响应模型<br/>",response=OperationNotePhoto.class)
	public @ResponseBody InformationBody operationNotePhoto(
			@ApiParam(required=true,value="子库最近操作模型",name="subLibraryRecentData")@RequestBody SubLibraryRecentData subLibraryRecentData){
		return subLibraryService.findPhotoOperationNoteBySubLibraryRecentData(subLibraryRecentData);
	}
	
	/**
	 * 子库起始页-操作记录三段-价格API
	 * @version    1.0
	 * @createTime 2018-4-11 13:10:38
	 * @return
	 */
	@RequestMapping(value="index_three_section/operation_note/price")
	@ApiOperation(value="子库起始页三段-操作记录-价格API",httpMethod="POST",notes=""
			+ "查找材料价格操作记录,需要字段:<br/>cityID 城市ID,startTime 开始时间,terminalTime 结束时间,beginRowNum 开始行数,endRowNum 结束行数.<br/>@author  ljc<br/>@version 2018-4-11<br/>"
			+ "body:响应数据:一级分类/二级分类/材料名称/材料规格/品牌名称/行为内容/操作时间<br/>详见响应模型<br/>",response=OperationNotePrice.class)
	public @ResponseBody InformationBody operationNotePrice(
			@ApiParam(required=true,value="子库最近操作模型",name="subLibraryRecentData")@RequestBody SubLibraryRecentData subLibraryRecentData){
		return subLibraryService.findPriceOperationNoteBySubLibraryRecentData(subLibraryRecentData);
	}
	
	/**
	 * 子库起始页成本三段-成本API
	 * @author     ljc
	 * @version    1.0
	 * @createTime 2018-04-04 15:16:01
	 * @return     InformationBody     响应数据:排名/地区/材料总价/近期涨幅/近期排名
	 */
	@RequestMapping(value="index_three_section/cost/cost")
	@ApiOperation(value="子库起始页三段-成本API",httpMethod="GET",notes="材料子库各地区材料价格整体排名，和近一个月涨幅统计功能<br/>@author  ljc<br/>@version 2018-04-04<br/>body:响应数据:排名/地区/材料总价/近期涨幅/近期排名<br/>详见响应模型<br/>",response=CostOverallRanking.class)
	public @ResponseBody InformationBody costThreeSection(){
		InformationBody informationBody = subLibraryService.getCastThreeSection();
		Object body = informationBody.getBody();
		//设置城市名称
		if (null!=body) {
			List<CostOverallRanking> costOverallRankings = (List<CostOverallRanking>)body;
			//设置城市名称:获取当前城市ID,使用公用控制类获取方法.
			for (CostOverallRanking costOverallRanking : costOverallRankings) {
				costOverallRanking.setCityName(commonController.getCityNameByCityID(costOverallRanking.getCmCityID()));
			}
		}
		return informationBody;
	}

	/**
	 * '实际'材料使用量排名API(子库起始页成本三段)（项目配送材料明细表）
	 * @author     ljc
	 * @version    1.0
	 * @createTime 2018-4-14 18:48:51
	 * @return     InformationBody     Body: 材料使用量模型List
	 */
	@RequestMapping(value="index_three_section/cost/application_amount")
	@ApiOperation(value="'实际'材料使用量排名-子库起始页三段API",httpMethod="GET",notes="'实际'材料使用量排名列表数据<br/>需要入参:城市ID-cityID,需要查询的数据量-TopNum@author  ljc<br/>@version 2018-04-14<br/>body:详见响应模型<br/>",response=CostMaterialApplicationAmount.class)
	public @ResponseBody InformationBody castThreeSectionApplicationAmount(
			@ApiParam(required=true,value="城市ID",name="cityID")@RequestParam Integer cityID,
			@ApiParam(required=true,value="排名数",name="topNum")@RequestParam Integer topNum){
		return subLibraryService.findMatApplicationAmountsByCityIDAndTopNum(cityID,topNum);
	}

    /**
     * 子库起始页成本三段-'计划'材料使用量排名API（项目材料表）
     * @author     ljc
     * @version    1.0
     * @createTime 2018-5-10 14:08:00
     * @param      cityID              城市ID
     * @param      topNum              查询数
     * @return     InformationBody     Body: 材料使用量模型List
     */
    @RequestMapping(value="index_three_section/cost/application_amount/plan")
    @ApiOperation(value="'计划'材料使用量排名-子库起始页三段API",httpMethod="GET",notes="'计划'材料使用量排名列表数据<br/>需要入参:城市ID-cityID,需要查询的数据量-TopNum@author  ljc<br/>@version 2018-04-14<br/>body:详见响应模型<br/>",response=CostMaterialApplicationAmount.class)
    public @ResponseBody InformationBody castThreeSectionApplicationAmount2(
            @ApiParam(required=true,value="城市ID",name="cityID")@RequestParam Integer cityID,
            @ApiParam(required=true,value="排名数",name="topNum")@RequestParam Integer topNum){
        return subLibraryService.findPlanMatApplicationAmountsByCityIDAndTopNum(cityID,topNum);
    }
	//-------------------------子库起始页 end-------------------------

	//-------------------------子库地区 begin-------------------------
    /**
     * 子库地区二段 - 档次显示标识
     * @author  ljc
     * @version 2018-5-23 16:35:36
     * @param   subLibraryCityTwoSectionQuery 子库地区检索对象
     * @return  档次显示标识
     */
    @RequestMapping(value={"/city_two_section/level_flag"})
    @ApiOperation(value="子库地区二段-档次标识API",httpMethod="POST",notes="获取档次显示标识: 0为false,1为true <br/>@author  ljc<br/>@version 2018-5-23<br/>body:<br/>" +
            "aLevel : 0 不显示, !0 显示<br/>" +
            "bLevel : 0 不显示, !0 显示<br/>" +
            "cLevel : 0 不显示, !0 显示<br/>")
    public@ResponseBody InformationBody getLevelShowFlag(
            @ApiParam(required=true,value="子库地区二段 检索模型",name="subLibraryCityTwoSectionQuery")@RequestBody SubLibraryCityQuery subLibraryCityTwoSectionQuery){
        return subLibraryService.getLevelShowFlag(subLibraryCityTwoSectionQuery);
    }

	/**
	 * 子库地区一段API
	 * 
	 * @author      ljc
	 * @version     1.0
     * @createTime  2018-5-9 18:54:44
     * @updateTime  2018-6-1 11:43:22
	 * @param       cityID             城市ID
	 * @return      InformationBody    子库地区一段JSON
	 */
	@RequestMapping(value={"/city_one_section"})
	@ApiOperation(value="子库地区一段API",httpMethod="GET",notes="根据city-城市ID,获取子库一段.<br/>@author  ljc<br/>@version 2018-03-15<br/>body:详见响应模型<br/>",response=SublibrariesAList.class)
	public @ResponseBody InformationBody cityOneSection(
			@ApiParam(value="城市ID",name="cityID")@RequestParam(required=false) Integer cityID){
        return subLibraryService.findCityOneSectionByCity(cityID);
	}

    // 子库地区二段API
	/**
	 * 子库地区二段API
	 * @author  ljc
	 * @version 2018-5-16 16:15:44
	 * @return  InformationBody     子库地区二段-状态JSON
	 */
	@RequestMapping(value={"/city_two_section"})
    @ApiOperation(value="子库地区二段API",httpMethod="POST",notes="通过城市ID、四级科目ID、页面类型，查找地区二段数据返回<br/>@author  ljc<br/>@version 2018-03-16<br/>body:<br/>")
	public@ResponseBody InformationBody cityTwoSection(
            @ApiParam(required=true,value="子库地区二段 检索模型",name="subLibraryCityTwoSectionQuery")@RequestBody SubLibraryCityQuery subLibraryCityTwoSectionQuery){
        InformationBody informationBody = new InformationBody();
        // 根据页面类型,请求对应类型数据返回.
        switch (subLibraryCityTwoSectionQuery.getPageType()){
            // 状态
            case 0:informationBody = subLibraryService.getCityTwoSectionStatus(subLibraryCityTwoSectionQuery);break;
            // 平台
            case 1:informationBody = subLibraryService.getCityTwoSectionPlatform(subLibraryCityTwoSectionQuery);break;
            // 商城
            case 2:informationBody = subLibraryService.getCityTwoSectionMall(subLibraryCityTwoSectionQuery);break;
            // A档
            case 3:informationBody = subLibraryService.getCityTwoSectionLevels(subLibraryCityTwoSectionQuery);break;
            // B档
            case 4:informationBody = subLibraryService.getCityTwoSectionLevels(subLibraryCityTwoSectionQuery);break;
            // C档
            case 5:informationBody = subLibraryService.getCityTwoSectionLevels(subLibraryCityTwoSectionQuery);break;
            // 价格
            case 6:informationBody = subLibraryService.getCityTwoSectionPrice(subLibraryCityTwoSectionQuery);break;
            // 成本
            case 7:informationBody = subLibraryService.getCityTwoSectionCost(subLibraryCityTwoSectionQuery);break;
            // 应用
            case 8:informationBody = subLibraryService.getCityTwoSectionApply(subLibraryCityTwoSectionQuery);break;
        }
        return informationBody;
	}

    //-------------------------子库地区三段(状态)弹出层 begin-------------------------
    /**
     * 处理
     * @author     ljc
     * @param      treeFourID  四级科目ID
     * @param      cityID      城市ID
     * @return     地区状态三段  处理 JSON
     * @createTime 2018-7-24 20:45:37
     */
    @RequestMapping(value={"/city_three_section/status/handle"})
    @ApiOperation(value="子库地区三段-状态-处理API",httpMethod="GET",notes= "子库地区三段-状态-处理 JSON数据<br/>@author  ljc<br/>@version 2018-7-24 20:47:10<br/>body:平台/A档/B档/C档<br/>")
    public@ResponseBody InformationBody cityThreeStatusHandle (
            @ApiParam(required=true,value="城市ID",name="cityID")@RequestParam Integer cityID,
            @ApiParam(required=true,value="四级科目ID",name="treeFourID")@RequestParam String treeFourID){
        return subLibraryService.getCityThreeStatusHandle(cityID,treeFourID);
    }


    //-------------------------子库地区三段(平台)弹出层 begin-------------------------
    /**
     * 基础
     * @author  ljc
     * @version 2018-5-28 11:22:26
     * @param   treeFourID  四级科目ID
     * @return  地区平台三段  基础 JSON
     */
    @RequestMapping(value={"/city_three_section/platform/base"})
    @ApiOperation(value="子库地区三段-平台-基础API",httpMethod="GET",notes=
            "子库地区三段-平台-基础 JSON数据<br/>@author  ljc<br/>@version 2018-5-28<br/>body:<br/>" +
            "基础信息 单位ID unitID,单位 unitName,分类(1成品，2非成品)() classify,功能 function,描述 describe<br/>" +
            "材料参数List matParas:[{参数ID paraID,参数名称 paraName,参数值 paraValue,参数单位 unitName,参数类型(1文本参数，2下拉参数) paraTyep}... ]}")
    public@ResponseBody InformationBody cityThreePlatformBase (
            @ApiParam(required=true,value="四级科目ID",name="treeFourID")@RequestParam String treeFourID){
        return subLibraryService.getCityThreePlatformBase(treeFourID);
    }

    /**
     * 质量标准
     * @author  ljc
     * @version 2018-5-28 11:22:26
     * @param   treeFourID  四级科目ID
     * @return  地区平台三段  质量标准 JSON
     */
    @RequestMapping(value={"/city_three_section/platform/sta_quality"})
    @ApiOperation(value="子库地区三段-平台-质量标准API",httpMethod="GET",notes=
            "子库地区三段-平台-质量标准JSON<br/>@author  ljc<br/>@version 2018-5-28<br/>body:<br/>" +
            "检查标准List qualitys:[{标准ID standardID,属性 attribute,特殊要求 specialRequirements,检测方法 testMethod}... ]}<br/>")
    public@ResponseBody InformationBody cityThreePlatformStaQuality (
            @ApiParam(required=true,value="四级科目ID",name="treeFourID")@RequestParam String treeFourID){
        return subLibraryService.getCityThreePlatformStaQuality(treeFourID);
    }

    /**
     * 小样标准
     * @author  ljc
     * @version 2018-5-28 11:44:32
     * @param   treeFourID  四级科目ID
     * @return  地区平台三段  小样标准 JSON
     */
    @RequestMapping(value={"/city_three_section/platform/sta_hue"})
    @ApiOperation(value="子库地区三段-平台-小样标准API",httpMethod="GET",notes=
            "子库地区三段-平台-小样标准JSON<br/>@author  ljc<br/>@version 2018-5-28<br/>body:<br/" +
            "标准List standards:[{标准ID standardID,属性名称 attributeName,属性值 attributeValue,属性单位 attributeUnit,属性单位ID attributeUnitID,范围最大 maxRange,范围最小 minRange}... ]<br/>" +
            "取样方法 samplingMethod")
    public@ResponseBody InformationBody cityThreePlatformStaHue (
            @ApiParam(required=true,value="四级科目ID",name="treeFourID")@RequestParam String treeFourID,
            @ApiParam(required=true,value="城市ID",name="cityID")@RequestParam Integer cityID){
        return subLibraryService.getCityThreePlatformStaHue(treeFourID,cityID);
    }

    /**
     * 包装标准
     * @author  ljc
     * @version 2018-5-28 11:45:49
     * @param   treeFourID  四级科目ID
     * @return  地区平台三段  包装标准 JSON
     */
    @RequestMapping(value={"/city_three_section/platform/sta_package"})
    @ApiOperation(value="子库地区三段-平台-包装标准API",httpMethod="GET",notes=
            "子库地区三段-平台-包装标准JSON<br/>@author  ljc<br/>@version 2018-5-28<br/>body:<br/" +
            "包装属性 packAttrs [{属性名称 attributeName,属性值 attributeValue,属性单位 attributeUnit}... ]" +
            "包装标准 包装类别(1瑞祥标准，2合作商标准) PackCategory,包装材质 packMatQuality,包装说明 packExplain" +
            "包装照片 packPhoto")
    public@ResponseBody InformationBody cityThreePlatformStaPack (
            @ApiParam(required=true,value="四级科目ID",name="treeFourID")@RequestParam String treeFourID){
        return subLibraryService.getCityThreePlatformStaPack(treeFourID);
    }

    /**
     * 对比标准
     * @author  ljc
     * @version 2018-5-28 11:45:49
     * @param   treeFourID  四级科目ID
     * @return  地区平台三段  对比标准 JSON
     */
    @RequestMapping(value={"/city_three_section/platform/sta_contrast"})
    @ApiOperation(value="子库地区三段-平台-对比标准API",httpMethod="GET",notes=
            "子库地区三段-平台-对比标准JSON<br/>@author  ljc<br/>@version 2018-5-28<br/>body:<br/>"+
            "对比标准 compareds [{对比标准ID mcaID,对比标准名称 attributeName}...]")
    public@ResponseBody InformationBody cityThreePlatformStaContrast (
            @ApiParam(required=true,value="四级科目ID",name="treeFourID")@RequestParam String treeFourID){
        return subLibraryService.getCityThreePlatformStaContrast(treeFourID);
    }

    /**
     * 搜索词
     * @author  ljc
     * @version 2018-5-28 11:45:49
     * @param   treeFourID  四级科目ID
     * @return  地区平台三段  搜索词 JSON
     */
    @RequestMapping(value={"/city_three_section/platform/search"})
    @ApiOperation(value="子库地区三段-平台-搜索词API",httpMethod="GET",notes=
            "子库地区三段-平台-搜索词JSON<br/>@author  ljc<br/>@version 2018-5-28<br/>body:<br/>" +
            "搜索词List searchItems[{材料搜索ID searchItemID,材料搜索内容 searchItemContent,类型：1搜索，2用途，4搜索自动生成内容（可位运算|） type}... ]" +
            "用途List useItems[{材料用途ID searchItemID,材料用途内容 searchItemContent,类型：1搜索，2用途，4搜索自动生成内容（可位运算|） type}... ]" )
    public@ResponseBody InformationBody cityThreePlatformSearch (
            @ApiParam(required=true,value="四级科目ID",name="treeFourID")@RequestParam String treeFourID){
        return subLibraryService.getCityThreePlatformSearch(treeFourID);
    }

    //-------------------------子库地区三段(档次)弹出层 begin-------------------------
    /**
     * @author   ljc
     * @version  2018-5-30 21:37:47
     * @param   treeFourID 四级分类ID
     * @param   cityID     城市ID
     * @param   levelFlag  档次标识
     * @return  子库地区档次三段弹出层回显数据JSON
     */
    // 标准
    @RequestMapping(value={"/city_three_section/level/standard"})
    @ApiOperation(value="子库地区三段-档次-标准API",httpMethod="GET",notes = "子库地区三段-档次-标准JSON<br/>@author  ljc<br/>@version 2018-5-30<br/>body:<br/>")
    public@ResponseBody InformationBody cityThreeLevelStandard (
            @ApiParam(required=true,value="四级科目ID",name="treeFourID")@RequestParam String treeFourID,
            @ApiParam(required=true,value="城市ID",name="cityID")@RequestParam Integer cityID,
            @ApiParam(required=true,value="档次标识(1A,2B,4C)",name="levelFlag")@RequestParam Integer levelFlag){
        return subLibraryService.getCityThreeLevelStandard(treeFourID,cityID,levelFlag);
    }
    // 品牌项
    @RequestMapping(value={"/city_three_section/level/brands"})
    @ApiOperation(value="子库地区三段-档次-品牌项API",httpMethod="GET",notes = "子库地区三段-档次-品牌项回显JSON数据<br/>@author  ljc<br/>@version 2018-6-20 11:03:22<br/>body:<br/>")
    public@ResponseBody InformationBody cityThreeLevelBrands (
            @ApiParam(required=true,value="材料ID(品牌ID)",name="matID")@RequestParam String matID){

        InformationBody informationBody = subLibraryService.getCityThreeLevelBrands(matID);
        Map<String, Object> body = (Map<String, Object>)informationBody.getBody();
        try {
            // 供货商
            List<Map<String, Object>> matSupplier = materialToSuppliersDao.findMatSupplierByMatID(matID);// 材料供货商相关 msUserID 材料商ID/msPrice 材料商价格
            List<Map<String, Object>> suppliers = new ArrayList<>();// 供货商集
            if (0 != matSupplier.size()){
                // 切换数据源
                DynamicDataSource.setCurrentLookupKey("dataSourceGC");//切换"工程"数据源

                // 组织供货商相关数据
                for (Map<String, Object> map : matSupplier) {
                    Map<String, Object> supplierMap = new HashMap<>();// 材料商Map
                    // 关联表数据
                    Integer msUserID = (Integer)map.get("msUserID");// 材料供货商关联ID
                    Object msPrice = map.get("msPrice");// 材料供货商价格

                    // 材料商相关主表
                    String userName = null;// 材料商名称
                    String mobile = null;// 材料商电话
                    PmUserInfoFull userInfo = pmUserInfoFullDao.get(msUserID.toString());// 供货材料商
                    if (null != userInfo){
                        userName = userInfo.getUserName();
                        mobile = userInfo.getMobile();
                    }

                    // 组织材料商相关数据
                    supplierMap.put("userID",msUserID);// 材料商ID
                    supplierMap.put("userName",userName);// 材料商名称
                    supplierMap.put("userPrice",msPrice);// 材料商价格
                    supplierMap.put("userMobile",mobile);// 材料商电话
                    suppliers.add(supplierMap);
                }
                // 恢复数据源
                DynamicDataSource.clearCustomerType();//清除数据源类型
                DynamicDataSource.setCurrentLookupKey("dataSourceCL");//切回"材料"默认数据源
            }
            body.put("suppliers",suppliers);// 材料商
            body.put("suppliersSize",suppliers.size());// 材料商数量(做材料商回路标识)
            informationBody.setBody(body);
        } catch (Exception e) {
            informationBody.setBody(e);
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("fail");
            logger.error("品牌项-子库地区'档次'三段,数据抓取失败!", e);
        }
        return informationBody;
    }

    //-------------------------子库地区三段(报价)弹出层 begin-------------------------
    /**
     * @author ljc
     * @createTime 2018-7-3 17:51:49
     */
    //  基础
    @RequestMapping(value={"/city_three_section/cost/base"})
    @ApiOperation(value="子库地区三段-报价-基础API",httpMethod="GET",notes = "子库地区三段-报价-基础JSON<br/>@author  ljc<br/>@version 2018-7-3 17:51:49<br/>body:<br/>")
    public@ResponseBody InformationBody cityThreeCostBase (
            @ApiParam(required=true,value="四级科目ID",name="treeFourID")@RequestParam String treeFourID,
            @ApiParam(required=true,value="城市ID",name="cityID")@RequestParam Integer cityID){
        return subLibraryService.getCityThreeCostBase(treeFourID,cityID);
    }

    // 档次项
    /**
     * @author       ljc
     * @createTime   2018-6-25 09:24:47
     */
    @RequestMapping(value={"/city_three_section/cost/levels"})
    @ApiOperation(value="子库地区三段-报价-档次项API",httpMethod="GET",notes = "子库地区三段-报价-档次项JSON<br/>@author  ljc<br/>@version 2018-6-25 09:24:47<br/>body:<br/>")
    public@ResponseBody InformationBody cityThreeCostLevels (
            @ApiParam(required=true,value="四级科目ID",name="treeFourID")@RequestParam String treeFourID,
            @ApiParam(required=true,value="城市ID",name="cityID")@RequestParam Integer cityID,
            @ApiParam(required=true,value="档次标识(1A,2B,4C)",name="levelFlag")@RequestParam Integer levelFlag){
        return subLibraryService.getcityThreeCostLevels(treeFourID,cityID,levelFlag);
    }

    //-------------------------子库地区四段(平台)弹出层 begin-------------------------


    //-------------------------子库地区四段(报价)弹出层 begin-------------------------
    /**
     * 对接:报价部门
     * @author ljc
     * @param matID
     * @createTime 2018-7-10 09:53:47
     */
    // 档次项 > 需求品牌
    @RequestMapping(value={"/city_three_section/four/brand"})
    @ApiOperation(value="子库地区四段-报价-档次项-需求品牌API",httpMethod="GET",notes = "子库地区四段-报价-档次项-需求品牌JSON<br/>@author  ljc<br/>@version 2018-7-10 09:53:05<br/>body:<br/>")
    public@ResponseBody InformationBody cityFourCostBrnad (
            @ApiParam(required=true,value="材料ID",name="matID")@RequestParam String matID){
        return subLibraryService.getCityFourCostBrand(matID);
    }


    /**
     * 子库地区二段(状态)API
     *
     * @author  ljc
     * @version 2018-03-16
     * @param   city 城市ID
     * @param   treeFourID 科目树四级类ID
     * @return  子库地区二段-状态JSON
     */
    @RequestMapping(value={"/city_two_section/status"})
    @ApiOperation(value="子库地区二段-状态API",httpMethod="GET",notes="通过city-城市ID与treeFourID-四级科目ID,查找地区二段状态.<br/>"
            + "@author  ljc<br/>@version 2018-03-16<br/>body:<br/>"
            + "状态 status:{}<br/>"
            + "平台 platform:{一级 treeOneName,二级 treeTwoName,名称 matName,规格 matSpec,单位 unit,分类 classify}<br/>"
            + "商城 mall:{置顶位置 topPosition,是否推荐首页 homeHostState}<br/>"
            + "A档 aLevel:{档次 lever,总共 matCount,完善 perfectNumber,价高 highPrice,价低 lowPrice,缺供 lackSupply,状态标识(0 正常/1 待、问) stateFlag}<br/>"
            + "B档 bLevel:{档次 lever,总共 matCount,完善 perfectNumber,价高 highPrice,价低 lowPrice,缺供 lackSupply,状态标识(0 正常/1 待、问) stateFlag}<br/>"
            + "C档 cLevel:{档次 lever,总共 matCount,完善 perfectNumber,价高 highPrice,价低 lowPrice,缺供 lackSupply,状态标识(0 正常/1 待、问) stateFlag}")
    public@ResponseBody  Map<String,Object > cityTwoSectionStatus(
            @ApiParam(required=true,value="城市ID",name="city")@RequestParam Integer city,
            @ApiParam(required=true,value="四级科目ID",name="treeFourID")@RequestParam String treeFourID ){
        return subLibraryService.findCityTwoSectionStatusByCityAndTreeFourID(city,treeFourID);
    }
	
	/**
	 * 子库地区二段(平台)API
	 * 
	 * @author  ljc
	 * @version 2018-5-18 18:12:09
	 * @param   city 城市ID
	 * @param   treeFourID 科目树四级类ID
	 * @return  子库地区二段-平台JSON
	 */
	@RequestMapping(value={"/city_two_section/platform"})
    @ApiOperation(value="子库地区二段-平台API",httpMethod="GET",notes="通过城市ID与四级科目ID,查找城市二段平台（囊括平台三段数据）.<br/>"
    		+ "@author  ljc<br/>@version 2018-5-18<br/>body:<br/>"
    		+ " 状态 status:{}<br/>"
    		+ " statusFlag 状态总标识 1完成(默认);0未完成状态;(针对平台下'基础/质量标准/小样标准/对比标准/搜索词'是否完成)<br/>"
    		+ "基础 matBase:{一级 treeOneName,二级 treeTwoName,名称 matName,规格 matSpec,单位 unit,分类 classify}<br/>"
    		+ "基础标识: matBaseFalg 基础标识状态 1完成,2未完成 (默认1完成) 针对:平台-基础完成状态<br/>"
    		+ "质量标准 qualityStandard:{检验标准 acceptanceStandard}<br/>"
    		+ "小样标准 sampleStandard:{取样方法 samplingMethod,检验标准 acceptanceStandard}<br/>"
    		+ "包装标准 packagingStandard:{包装标准 packagingStandard,包装照片 packagingPhoto,包装属性 packagingAttribute}<br/>"
    		+ "对比标准 contrastStandard:{对比项 contrastItem}<br/>"
    		+ "搜索词 searchTerm:{搜索词 searchTerm,用途 use }<br/>"
    		+ "平台三段 platformThreeSections:{<br/>"
    		+ "&emsp;基础信息 baseMap:{<br/>"
    		+ "&emsp;&emsp;基础信息 basicInformation:{单位ID unitID,单位 unitName,分类 classify,功能 function,描述 describe}<br/>"
    		+ "&emsp;&emsp;材料参数List materialParameterList:[{参数ID paraID,参数名称 paraName,参数值 paraValue,参数单位 unitName}... ]}<br/>"
    		+ "&emsp;质量标准 qualityMap:{<br/>"
    		+ "&emsp;&emsp;检查标准List qualityList:[{标准ID standardID,属性 attribute,特殊要求 specialRequirements,检测方法 testMethod}... ]}<br/>"
    		+ "&emsp;小样标准  packMap:{<br/>"
    		+ "&emsp;&emsp;标准List standardList:[{标准ID standardID,属性名称 attributeName,属性值 attributeValue,属性单位 attributeUnit,范围最大 maximumRange,范围最小 minimumRange}... ]<br/>"
    		+ "&emsp;&emsp;取样方法 samplingMethod}<br/>"
    		+ "&emsp;包装标准 packMap:{<br/>"
    		+ "&emsp;&emsp;包装属性 packAttributeList[{属性名称 attributeName,属性值 attributeValue,属性单位 attributeUnit}... ]<br/>"
    		+ "&emsp;&emsp;包装标准 packStandard:{包装类别 PackCategory,包装材质 packMatQuality,包装说明 packExplain}<br/>"
    		+ "&emsp;&emsp;包装照片 packPhoto}<br/>"
    		+ "&emsp;对比标准 contrastMap[{对比标准ID mcaID,对比标准名称 attributeName}...]<br/>"
    		+ "&emsp;搜索词 searchTermMap{<br/>"
    		+ "&emsp;&emsp;搜索词List searchTermList[{材料搜索ID searchItemID,材料搜索内容 searchItemContent,类型：1搜索，2用途，4搜索自动生成内容（可位运算|） type}... ]<br/>"
    		+ "&emsp;&emsp;用途List useList[{材料用途ID searchItemID,材料用途内容 searchItemContent,类型：1搜索，2用途，4搜索自动生成内容（可位运算|） type}... ]}<br/>"
    		+ "}") 
	public@ResponseBody  Map<String,Object > cityTwoSectionPlatform(
			@ApiParam(required=true,value="城市ID",name="city")@RequestParam Integer city,
			@ApiParam(required=true,value="四级科目ID",name="treeFourID")@RequestParam String treeFourID ){
		return subLibraryService.findCityTwoSectionPlatformByCityAndTreeFourID(city,treeFourID);
	}

	

	/**
	 * 子库地区新增标准API
	 * @author   ljc
	 * @version  2018-03-29
	 * @describe 动态数据源切换:dataSourceRS 人事数据源
	 * @return   InformationBody 响应数据:单位List,地区List.
	 */
	@RequestMapping(value={"city_newly_increased"})
	@ApiOperation(value="子库地区新增标准:基础数据回显,单位与城市API",httpMethod="GET",notes="子库地区新增标准<br/>@author ljc<br/>@version 2018-03-29<br/>"
			+"body:<br/>"
			+ "单位 unitArray:[{单位ID unitID,单位名称 unitName }... ]<br/>"
			+ "城市 cityArray:[{地区ID cityID,地区名称 cityName,地区编码 cityCode}... ]<br/>",response=InformationBody.class)
	public @ResponseBody InformationBody cityNewlyIncreased(){
		InformationBody informationBody = new InformationBody();
		Map<String, Object> bodyValue = new HashMap<String, Object>();
		try {
			//组织响应数据:
			//单位
			bodyValue.put("unitArray",commonController.findUnitArrayByReidsKey(JedisUtils.get(KEY_PREFIX + Constants.UNIT)));
			
			//城市
			bodyValue.put("cityArray",commonController.findCityArrayByReidsKey(JedisUtils.get(KEY_PREFIX + Constants.AREA)));
 			//设置响应体
 			informationBody.setBody(bodyValue);
 			//设置响应状态
			informationBody.setStatusCode(0);
			informationBody.setStatusMsg("成功");
			logger.debug("子库地区新增标准接口,调用成功.");
		} catch (Exception e) {
			informationBody.setStatusCode(-1);
			informationBody.setStatusMsg("失败");
			logger.error("子库地区新增标准接口,调用失败!",e);
		}
		return informationBody;
	}

	/**
	 * 科目树文本框键入相关数据回显(子库地区新增标准)API
	 * 前端校验方案:
	 * 获取文本框内容,校验是否与返回对应结果集一样,true 绑定数据,false 不错操作.
	 * 
	 * $("input").keyup(fn) 键盘弹起事件触发
	 * $("p").focusin(fu)   获得焦点事件触发
	 * 
	 * @author    ljc
	 * @version   2018-03-29
	 * @param     treeName        键入的科目树"名称"
	 * @param     treeLevel       隐藏域科目树"级别"
	 * @param     treeParentID    隐藏域科目树"父级ID"
	 * @return    InformationBody 响应数据:根据入参检索出的"科目树名称(回显所需数据)","科目树级别(检索所需条件)","科目树ID(添加所需字段)","科目树父级ID(添加所需字段)","科目树编码(添加所需字段)"
	 */
	@RequestMapping(value={"city_newly_increased/correlation_echo"})
	@ApiOperation(value="子库地区新增标准:科目树文本框键入相关数据回显(子库地区新增标准)API",httpMethod="GET",notes="科目树文本框键入相关数据回显(子库地区新增标准)<br/>@author ljc<br/>@version 2018-03-29<br/>"
			+ "body:<br/>"
			+ "&emsp;treeID ID(添加所需字段),treeName 名称(回显所需数据),treeLevel 级别,treeCode 编码(添加所需字段),treeParentID 父级ID(添加所需字段)",response=CityNewlyIncreasedCorrelationEcho.class)
	public @ResponseBody InformationBody cityNewlyIncreasedCorrelationEcho(
			@ApiParam(required=true,value="科目树'名称'",name="treeName")@RequestParam String treeName,
			@ApiParam(required=true,value="科目树'级别'",name="treeLevel")@RequestParam Integer treeLevel,
			@ApiParam(value="科目树'父级ID'",name="treeParentID")@RequestParam(required=false) String treeParentID){
		return subLibraryService.cityNewlyIncreasedCorrelationEcho(treeName,treeLevel,treeParentID);
	}
	
	/**
	 * 添加子库地区新增标准API
	 * @author   ljc
	 * @version  2018-03-30 
	 * @return   InformationBody 响应状态信息
	 */
	@RequestMapping(value={"city_newly_increased/save"})
	@ApiOperation(value="子库地区新增标准:添加API",httpMethod="POST",notes="添加子库地区新增标准<br/>@author  ljc<br/>@version 2018-03-30<br/>"
			+ "保存 科目树<br/>"
			+ "插入字段:ID,编码,名称,父级ID,级别,完善状态<br/>"
			+ "前台传入字段:名称,父级ID.<br/>"
			+ "<br/>"
			+ "保存 材料基础信息 单位 <br/>"
			+ "插入字段:ID,四级ID,单位ID<br/>"
			+ "前台传入字段:单位ID<br/>"
			+ "<br/>"
			+ "保存 分类 <br/>"
			+ "插入字段:ID,四级ID,16类型,状态,树等级<br/>"
			+ "前台传入字段:状态 1成品 2非成品<br/>"
			+ "<br/>"
			+ "保存 档次 <br/>"
			+ "插入字段:ID,四级ID,基本信息(档次总计)<br/>"
			+ "前台传入字段:基本信息(档次总计)<br/>"
			+ "<br/>"
			+ "保存 地区 <br/>"
			+ "插入字段:ID,四级规格ID,地区 (插入多条)<br/>"
			+ "前台传入字段: 地区ID<br/>",response=InformationBody.class)
	public@ResponseBody InformationBody saveCityNewlyIncreased(
			@ApiParam(required=true,value="地区新增标准(添加模型)",name="cityNewlyIncreased")@RequestBody CityNewlyIncreased cityNewlyIncreased){
		return subLibraryService.saveCityNewlyIncreased(cityNewlyIncreased);
	}
	
	/**
	 * 校验科目树是否存在,通过科目树名称与科目树级别API
	 * @author      ljc
	 * @version     1.0
	 * @createTime  2018-4-8 16:03:22 
	 * @param       treeName           科目树名称
	 * @param       treeLevel 		       科目树级别
	 * @param       parentID 		       父级ID
	 * @return      InformationBody    statusMsg:存在 success,不存在 fail.
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value={"verification_tree_exist"})
	@ApiOperation(value="子库地区新增标准:校验科目树是否存在,通过科目树名称与科目树级和父级ID(可为null)API",httpMethod="GET",notes="通过传入的科目树名称与科目树级别和父级ID,校验当前科目树是否已存在.<br/>@author  ljc<br/>@version 2018-4-8<br/>"
			+ "statusMsg:未重名 success,重名 fail.<br/>"
			+ "body:根据科目树名称与科目树级别,查询到的结果集数量.<br/>")
	public@ResponseBody InformationBody checkTreeName(
			@ApiParam(required=true,value="科目树名称",name="treeName") @RequestParam String treeName, 
			@ApiParam(required=true,value="科目树级别",name="treeLevel")@RequestParam Integer treeLevel,
			@ApiParam(value="父级ID",name="parentID") @RequestParam(required=false) String parentID) throws UnsupportedEncodingException{
		return  subLibraryService.verificationTreeExistByTreeNameAndTreeLevelAndParentID(URLDecoder.decode(treeName, "UTF-8"), treeLevel,parentID);
	}

    //-------------------------子库列表 begin-------------------------
    /**
     * 子库列表:'检索'二维码打印列表
     * @author     ljc
     * @param      subLibraryCityQuery 子库地区检索包装类
     * @return     二维码打印列表
     * @createTime 2018-7-11 15:27:06
     */
    @RequestMapping(value={"list/qrcode_print/serarch"})
    @ApiOperation(value="子库列表:检索二维码打印列表API",httpMethod="POST",notes="子库列表:查找二维码打印列表 通过 子库地区检索包装类<br/>@author  ljc<br/>@version 2018-7-11 15:39:07<br/>")
    public@ResponseBody InformationBody searchQRCodePrintList(
            @ApiParam(required=true,value="子库地区检索包装类",name="subLibraryCityTwoSectionQuery")@RequestBody SubLibraryCityQuery subLibraryCityQuery){
        return  subLibraryService.findQRCodePrintListBySubLibraryCityQuery(subLibraryCityQuery);
    }

    /**
     * 子库列表:'检索'材料回路列表
     * @author     ljc
     * @param      matLoopQuery  材料回路检索包装类
     * @return     材料回路列表
     * @createTime 2018-7-31 19:11:04
     */
    @RequestMapping(value={"list/mat_loop/serarch"})
    @ApiOperation(value="子库列表:'检索'材料回路列表API",httpMethod="POST",notes="子库列表:查找材料回路列表 通过 材料回路检索包装类<br/>@author  ljc<br/>@version 2018-7-31 19:13:16<br/>")
    public@ResponseBody InformationBody searchMatLoopList(
            @ApiParam(required=true,value="子库地区检索包装类",name="subLibraryCityTwoSectionQuery")@RequestBody MatLoopQuery matLoopQuery){
        InformationBody matLoopListByMatLoopQuery = subLibraryService.findMatLoopListByMatLoopQuery(matLoopQuery);
        // 校验是否获取全国材料回路列表数据,是,设置城市名称并剔除冗余城市.
        if (null == matLoopQuery.getCityID()){
            Map<String,Object> body = (Map<String,Object>)matLoopListByMatLoopQuery.getBody();
            List<QGMatLoopList> qgLoopMatList = (List<QGMatLoopList>)body.get("qgLoopMatList");

            List<QGMatLoopList> finalQGLoopMatList = new ArrayList<>();
            for (QGMatLoopList qgMatLoopList : qgLoopMatList) {
                Integer resultCityID = qgMatLoopList.getCityID();
                commonController.getCitys().forEach(item -> {
                    Integer appointCityID = (Integer)item.get("cityID");
                    if (appointCityID.equals(resultCityID)){
                        qgMatLoopList.setCityName(commonController.getCityNameByCityID(resultCityID));
                        finalQGLoopMatList.add(qgMatLoopList);
                    }
                });
            }
            body.put("qgLoopMatList",finalQGLoopMatList);

            // 设置全国合计
            // === 计数器 开始===
            // 种类
            int totalNm = 0;
            int quanNm = 0;
            // 总状
            int loopTotalNmY = 0;
            int loopTotalNmN = 0;
            // 平台
            int loopPlatformNmY = 0;
            int loopPlatformNmN = 0;
            // A档
            int loopAlevelNmY = 0;
            int loopAlevelNmN = 0;
            // B档
            int loopBlevelNmY = 0;
            int loopBlevelNmN = 0;
            // C档
            int loopClevelNmY = 0;
            int loopClevelNmN = 0;
            // 处理
            int handleTypeNot = 0;
            int handleTypeZ = 0;
            int handleTypeY = 0;
            int handleTypeW = 0;

            for (QGMatLoopList item : finalQGLoopMatList) {
                totalNm += item.getTotalNm();
                quanNm += item.getQuanNm();
                loopTotalNmY += item.getLoopTotalNmY();
                loopTotalNmN += item.getLoopTotalNmN();
                loopPlatformNmY += item.getLoopPlatformNmY();
                loopPlatformNmN += item.getLoopPlatformNmN();
                loopAlevelNmY += item.getLoopAlevelNmY();
                loopAlevelNmN += item.getLoopAlevelNmN();
                loopBlevelNmY += item.getLoopBlevelNmY();
                loopBlevelNmN += item.getLoopBlevelNmN();
                loopClevelNmY += item.getLoopClevelNmY();
                loopClevelNmN += item.getLoopClevelNmN();
                handleTypeNot += item.getHandleTypeNot();
                handleTypeZ += item.getHandleTypeZ();
                handleTypeY += item.getHandleTypeY();
                handleTypeW += item.getHandleTypeW();
            }
            body.put("qgLoopMatListSUM",new QGMatLoopListSUM(totalNm, quanNm, loopTotalNmY, loopTotalNmN, loopPlatformNmY, loopPlatformNmN, loopAlevelNmY, loopAlevelNmN, loopBlevelNmY, loopBlevelNmN, loopClevelNmY, loopClevelNmN, handleTypeNot, handleTypeZ, handleTypeY, handleTypeW,finalQGLoopMatList.size()));
            matLoopListByMatLoopQuery.setBody(body);
        }
        return  matLoopListByMatLoopQuery;
    }

    /**
     * 子库列表:'导出'二维码打印列表
     * @author     ljc
     * @createTime 2018-7-12 11:23:43
     */
    @RequestMapping(value={"list/qrcode_print/export_excel"})
    @ApiOperation(value="子库列表:导出二维码打印列表API",httpMethod="GET",notes="子库列表:导出二维码打印列表,通过>库地区检索包装类<br/>@author  ljc<br/>@version 2018-7-12 11:23:43<br/>")
    public void exportQRCodePrintListExcel(
            @ApiParam(required=true,value="城市ID",name="cityID") @RequestParam Integer cityID,
            @ApiParam(required=false,value="一级科目ID",name="treeOneID")@RequestParam(required=false) String treeOneID,
            @ApiParam(required=false,value="二级科目ID",name="treeTwoID")@RequestParam(required=false) String treeTwoID,
            @ApiParam(required=false,value="三级科目ID",name="treeThreeID")@RequestParam(required=false) String treeThreeID,
            @ApiParam(required=false,value="四级科目ID",name="treeFourID")@RequestParam(required=false) String treeFourID,
            @ApiParam(required=false,value="材料名称",name="matName")@RequestParam(required=false) String matName,
            @ApiParam(required=false,value="页面类型 9.二维码列表圈中材料",name="pageType")@RequestParam(required=false) Integer pageType,
            @ApiParam(required=false,value="档次标识(1.A档/2.B档/4.C档)")@RequestParam(required=false) Integer levelFlag,
            @ApiParam(required=false,value="材料ID集(格式:材料ID1,材料ID2,材料ID3 ...)",name="matIDs")@RequestParam(required=false) String matIDs,
            HttpServletResponse response) {
        InformationBody informationBody = new InformationBody();
        try{
            // 1.调用Service找到所用的二维码打印列表结果集.
            List<QRCodePrintList> qrCodePrintLists = subLibraryService.exportQRCodePrintListExcel(new SubLibraryCityQuery(cityID,treeOneID,treeTwoID,treeThreeID,treeFourID,matName,pageType, StringUtils.isNotBlank(matIDs) ? Arrays.asList(matIDs.split(",")) : null,levelFlag,StringUtils.isBlank(matName) || matName.indexOf(",") == - 1  ? null : Arrays.asList(matName.split(","))));

            // 2.将分区记录转换为Excel
            // -----------------------------------------------------------------------------

            // 1>创建workboot 工作薄对象
            HSSFWorkbook wb = new HSSFWorkbook();

            // 2>创建sheet 工作簿中的表格对象
            HSSFSheet sheet = wb.createSheet("二维码打印列表");

            // 3>创建row行 表格中的行对象&行对象中的列对象
            HSSFRow titleRow = sheet.createRow(0);

            // 创建标题行,创建标题列单元格并制定内容.
            titleRow.createCell(0).setCellValue("材料名称");
            titleRow.createCell(1).setCellValue("材料编号");
            titleRow.createCell(2).setCellValue("类别");
            titleRow.createCell(3).setCellValue("品牌");
            titleRow.createCell(4).setCellValue("品牌拼音");
            titleRow.createCell(5).setCellValue("规格");
            titleRow.createCell(6).setCellValue("材料平台地址");
            titleRow.createCell(7).setCellValue("报价");
            titleRow.createCell(8).setCellValue("单位");

            // 创建结果集对应数量的行对象,并制定行对象的列内容.
            int i = 0;
            for (QRCodePrintList qrCodePrintList : qrCodePrintLists) {
                i ++;
                HSSFRow dataRow = sheet.createRow(i);
                dataRow.createCell(0).setCellValue(qrCodePrintList.getMatName());
                dataRow.createCell(1).setCellValue(qrCodePrintList.getMatCode());
                dataRow.createCell(2).setCellValue(qrCodePrintList.getMatLevel());
                dataRow.createCell(3).setCellValue(qrCodePrintList.getMatBrandName());
                dataRow.createCell(4).setCellValue(qrCodePrintList.getMatBrandNamePinYin());
                dataRow.createCell(5).setCellValue(qrCodePrintList.getMatSpecName());
                dataRow.createCell(6).setCellValue(qrCodePrintList.getMatMallUrl());
                dataRow.createCell(7).setCellValue(qrCodePrintList.getMatQuotedPrice());
                dataRow.createCell(8).setCellValue(qrCodePrintList.getMatUnit());
            }

            // -------------------------------------------------------------------------------

            // 3.将Excel响应到浏览器

            // >指定响应内容作为附件下载使用,并制定下载文件名.
            response.addHeader("Content-Disposition", "attachment;filename="+ Encodes.urlEncode("标签打印") + DateUtils.getTime()  + ".xls");

            // >指定响应内容类型为excel文档
            response.setContentType("application/vnd.ms-excel");

            // >获得向浏览器输出的输出流 ,将文档输出到浏览器
            wb.write(response.getOutputStream());

            logger.debug("子库列表:'导出'二维码打印列表,导出成功!");
        } catch (Exception e) {
            informationBody.setBody(e);
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("error");
            logger.error("子库列表:'导出'二维码打印列表,导出失败!", e);
        }


    }



}


