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
import com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.city.platform.PlatformStandardInfo;
import com.thinkgem.jeesite.modules.api.jmat.service.TreeStandardItmeService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import javax.servlet.http.HttpSession;

/**
 * 材料标准信息Controller
 * @author ljc
 * @version 2018-03-19
 */
@Controller
@RequestMapping(value = "tree_standard_itme-api")
@Api(value="材料标准信息Controller",description="材料标准信息API")
public class TreeStandardItmeController extends BaseController {

	@Autowired
	private TreeStandardItmeService treeStandardItmeService;
	
	/**
	 * 新增或更新,材料标准信息.
	 * @author     ljc
	 * @version    2018-03-27
	 * @param      platformStandardInfo  平台材料标准信息(新增/更新)POJO
     * @createTime 2018-6-14 15:27:05
	 * @return     InformationBody       响应信息
	 */
	@RequestMapping(value={"/save_or_update"})
	@ApiOperation(value="新增/更新,材料标准信息API",httpMethod="POST",notes="新增或更新材料标准信息(质量/小样/包装通用)<br/>@author  ljc<br/>@version 2018-03-27<br/>",response=InformationBody.class)
	public@ResponseBody InformationBody saveOrUpdate(
			@ApiParam(required=true,value="平台材料标准信息(新增/更新)POJO",name="platformStandardInfo")@RequestBody PlatformStandardInfo platformStandardInfo,
            HttpSession session){
		return treeStandardItmeService.saveOrUpdate(platformStandardInfo,MMSUtils.getCardNumber(session));
	}
	
	/**
	 * 移除材料标准信息.
	 * @author   ljc
	 * @version  2018-03-26 
	 * @param    standardID      标准ID
	 * @return   InformationBody 响应信息
	 */
	@RequestMapping(value={"/delete"})
	@ApiOperation(value="移除材料标准信息API",httpMethod="DELETE",notes="通过标准ID,移除材料标准信息.(质量/小样/包装通用,需用户确认删除操作!)<br/>@author  ljc<br/>@version 2018-03-27<br/>",response=InformationBody.class)
	public@ResponseBody InformationBody delete(
            @ApiParam(required=true,value="标准ID",name="standardID")@RequestParam String standardID,
            HttpSession session){
		return treeStandardItmeService.delete(standardID, MMSUtils.getCardNumber(session));
	}
	

}