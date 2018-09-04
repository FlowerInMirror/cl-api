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
import com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.city.platform.PlatformStandardParameters;
import com.thinkgem.jeesite.modules.api.jmat.service.StandardParaService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import javax.servlet.http.HttpSession;

/**
 * 材料标准参数Controller
 * @author ljc
 * @version 2018-03-21
 */
@Controller
@RequestMapping(value = "standard_para-api")
@Api(value="材料标准参数Controller",description="材料标准参数API")
public class StandardParaController extends BaseController {

	@Autowired
	private StandardParaService standardParaService;
	
	/**
	 * 移除材料参数.
	 * @author   ljc
	 * @version  2018-03-25 
	 * @param    paraID          参数ID
	 * @param    treeFourID      四级科目ID
	 * @return   InformationBody 响应信息
	 */
	@RequestMapping(value={"/delete"})
	@ApiOperation(value="移除材料参数API",httpMethod="POST",notes="通过参数ID与四级科目ID,移除材料参数.(需用户确认删除操作!)<br/>@author  ljc<br/>@version 2018-03-25<br/>",response=InformationBody.class)
	public@ResponseBody InformationBody delete(
			@ApiParam(required=true,value="参数ID",name="paraID")@RequestParam Integer paraID,
			@ApiParam(required=true,value="四级科目ID",name="treeFourID")@RequestParam String treeFourID,
            HttpSession session){
		return standardParaService.delete(paraID,treeFourID,MMSUtils.getCardNumber(session));
	}
	
	/**
	 * 新增或更新,材料参数.
	 * @author   ljc
	 * @version  2018-03-26 
	 * @param    平台标准参数(新增/更新)
	 * @return   InformationBody 响应信息
	 */
	@RequestMapping(value={"/save_or_update"})
	@ApiOperation(value="新增/更新,材料参数API",httpMethod="POST",notes="新增或更新材料参数,需要字段四级科目ID/参数ID/参数值.<br/>@author  ljc<br/>@version 2018-03-26<br/>",response=InformationBody.class)
	public@ResponseBody InformationBody saveOrUpdate(
			@ApiParam(required=true,value="treeFourID:四级科目ID,items:[参数ID:参数值... ]",name="platformStandardParameters")@RequestBody PlatformStandardParameters platformStandardParameters
    ,HttpSession session){
	    // 设置当前登录用户卡号
        platformStandardParameters.setCradNum(MMSUtils.getCardNumber(session));
		return standardParaService.saveOrUpdate(platformStandardParameters);
	}
	
}