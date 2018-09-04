/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.web;



import com.thinkgem.jeesite.modules.web.mms.utils.MMSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.InformationBody;
import com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.city.platform.PlatformBasicInformation;
import com.thinkgem.jeesite.modules.api.jmat.service.TreeBaseInfoService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import javax.servlet.http.HttpSession;

/**
 * 材料基础信息Controller
 * @author ljc
 * @version 2018-03-16
 */
@Controller
@RequestMapping(value = "tree_base_info-api")
@Api(value="材料基础信息Controller",description="材料基础信息API")
public class TreeBaseInfoController extends BaseController {

	@Autowired
	private TreeBaseInfoService treeBaseInfoService;
	
	/**
	 * 更新材料基础信息API
	 * @author   ljc
	 * @version  2018-03-23
	 * @return   响应状态信息
	 */
	@RequestMapping(value={"update/base_info"})
	@ApiOperation(value="更新材料基础信息API",httpMethod="PUT",notes="更新材料基础信息<br/>@author  ljc<br/>@version 2018-03-23<br/>",response=InformationBody.class)
	public@ResponseBody InformationBody update(@ApiParam(required=true,value="平台基础信息POJO",name="platformBasicInformation")@RequestBody PlatformBasicInformation platformBasicInformation){
		return treeBaseInfoService.update(platformBasicInformation);
	}
	
	/**
	 * 材料基础信息(基础信息-新增或更新)API
	 * @author  ljc
	 * @version 2018-03-27 
	 * @param   unitID           单位ID
	 * @param   treeFourID       四级科目ID
	 * @param   matFunction      功能
	 * @param   depict           描述
	 * @param   type             分类
	 * @return  InformationBody  响应状态信息
	 */
	@RequestMapping(value={"save_or_update/base_info"})
	@ApiOperation(value="基础信息-新增或更新API",httpMethod="PUT",notes="基础信息-新增或更新<br/>@author  ljc<br/>@version 2018-03-27<br/>"
			+ "入参:unitID 单位ID,treeFourID 四级科目ID,function 功能,depict 描述,type 分类(1成品/2非成品)",response=InformationBody.class)
	public@ResponseBody InformationBody saveOrUpdateBaseInfo(
            @ApiParam(required=true,value="单位ID",name="unitID")@RequestParam Integer unitID,
            @ApiParam(required=true,value="四级科目ID",name="treeFourID")@RequestParam String treeFourID,
            @ApiParam(required=true,value="功能",name="matFunction")@RequestParam String matFunction,
            @ApiParam(required=true,value="描述",name="depict")@RequestParam String depict,
            @ApiParam(required=true,value="分类(1成品/2非成品)",name="type")@RequestParam Integer type,
            HttpSession session){
        return treeBaseInfoService.saveOrUpdateBaseInfo(unitID,treeFourID,matFunction,depict,type, MMSUtils.getCardNumber(session));
	}
	
	/**
	 * 取样方法(小样标准-新增或更新)API
	 * @author   ljc
	 * @version  2018-03-27 
	 * @param    treeFourID      四级科目ID
	 * @param    samplingMethod  取样方法
	 * @return   InformationBody 响应状态信息
	 */
	@RequestMapping(value={"save_or_update/sampling_method"})
	@ApiOperation(value="取样方法-新增或更新API",httpMethod="PUT",notes="取样方法-新增或更新<br/>@author  ljc<br/>@version 2018-03-27<br/>"
			+ "入参:treeFourID 四级科目ID,samplingMethod 取样方法",response=InformationBody.class)
	public@ResponseBody InformationBody saveOrUpdateSamplingMethod (
			@ApiParam(required=true,value="四级科目ID",name="treeFourID")@RequestParam String treeFourID,
			@ApiParam(required=true,value="取样方法",name="samplingMethod")@RequestParam String samplingMethod,
            HttpSession session){
		return treeBaseInfoService.saveOrUpdateSamplingMethod(treeFourID,samplingMethod,MMSUtils.getCardNumber(session));
	}
	
	/**
	 * 新增或更新包装标准API
	 * @author   ljc
	 * @version  2018-03-27 
	 * @param    treeFourID       四级科目ID
	 * @param    packGenre        包装类别(类别：1瑞祥标准，2合作商标准)
	 * @param    packQuality      包装材质
	 * @param    packExplain      包装说明
	 * @return   InformationBody  响应状态信息
	 */
	@RequestMapping(value={"save_or_update/pack_standard"})
	@ApiOperation(value="包装标准-新增或更新API",httpMethod="PUT",notes="包装标准-新增或更新<br/>@author  ljc<br/>@version 2018-03-27<br/>"
			+ "入参:treeFourID 四级科目ID,packGenre 包装类别,packQuality 包装材质,packExplain 包装说明",response=InformationBody.class)
	public@ResponseBody InformationBody saveOrUpdatePackStandard (
			@ApiParam(required=true,value="四级科目ID",name="treeFourID")@RequestParam String treeFourID,
			@ApiParam(required=true,value="包装类别(类别：1瑞祥标准，2合作商标准)",name="packGenre")@RequestParam Integer packGenre,
			@ApiParam(required=true,value="包装材质",name="packQuality")@RequestParam String packQuality,
			@ApiParam(required=true,value="包装说明",name="packExplain")@RequestParam String packExplain,
            HttpSession session){
		return treeBaseInfoService.saveOrUpdatePackStandard(treeFourID,packGenre,packQuality,packExplain,MMSUtils.getCardNumber(session));
	}
	
	
	/**
	 * 更新包装照片API
	 * @author   ljc
	 * @version  2018-03-28 
	 * @param    treeFourID       四级科目ID
	 * @param    photoUrl         照片路径
	 * @return   InformationBody  接口调用状态信息
	 */
	@RequestMapping(value={"update_pack_photo"})
	@ApiOperation(value="更新包装照片API",httpMethod="PUT",notes="更新包装照片<br/>@author  ljc<br/>@version 2018-03-28<br/>"
			+ "入参:treeFourID 四级科目ID,photoUrl 照片路径",response=InformationBody.class)
	public@ResponseBody InformationBody updatePackPhoto (
			@ApiParam(required=true,value="四级科目ID",name="treeFourID")@RequestParam String treeFourID,
			@ApiParam(required=true,value="照片路径",name="photoUrl")@RequestParam String photoUrl,
            HttpSession session){
		return treeBaseInfoService.updatePackPhoto(treeFourID,photoUrl,MMSUtils.getCardNumber(session));
	}
	
	
}