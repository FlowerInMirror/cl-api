/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.web;


import com.thinkgem.jeesite.modules.web.mms.utils.MMSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.InformationBody;
import com.thinkgem.jeesite.modules.api.jmat.service.MaterialComparedAttributesService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import javax.servlet.http.HttpSession;

/**
 * 属性对比（对比图）Controller
 * @author ljc
 * @version 2018-03-19
 */
@Controller
@RequestMapping(value = "material_compared_attributes-api")
@Api(value="属性对比（对比图）Controller",description="属性对比（对比图）API")
public class MaterialComparedAttributesController extends BaseController {

	@Autowired
	private MaterialComparedAttributesService materialComparedAttributesService;
	
	/**
	 * 新增或更新对比属性API
	 * @author     ljc
	 * @version    2018-03-27
	 * @param      mcaID              属性对比ID
	 * @param      mcaName            对比名称
	 * @param      mcaType            类型（1对比，2材料约定）
	 * @param      treeFourID         四级科目ID
     * @updateTime 2018-6-14 19:00:57
	 * @return     InformationBody    响应状态信息
	 */
	@RequestMapping(value={"save_or_update"})
	@ApiOperation(value="新增或更新对比标准-属性API",httpMethod="PUT",notes="新增或更新对比标准-属性<br/>@author  ljc<br/>@version 2018-03-27<br/>"
			+ "入参:mcaID 属性对比ID,mcaName 对比名称,mcaType 类型（1对比，2材料约定),treeFourID 四级科目ID",response=InformationBody.class)
	public@ResponseBody InformationBody saveOrUpdate (
			@ApiParam(value="属性对比ID",name="mcaID")@RequestParam(required=false) Integer mcaID,
			@ApiParam(required=true,value="对比名称",name="mcaName")@RequestParam String mcaName,
			@ApiParam(required=true,value="类型（1对比，2材料约定）",name="mcaType")@RequestParam Integer mcaType,
			@ApiParam(required=true,value="四级科目ID",name="treeFourID")@RequestParam String treeFourID,
            HttpSession session){
		return materialComparedAttributesService.saveOrUpdate(mcaID,mcaName,mcaType,treeFourID,MMSUtils.getCardNumber(session));
	}
	
	/**
	 * 移除对比属性API
	 * @author     ljc
	 * @version    2018-03-27
	 * @param      mcaID           对比属性ID
     * @param      attrType        对比属性类型（1对比，2材料约定）
     * @updateTime 2018-6-14 18:51:55
	 * @return     InformationBody 响应信息
	 */
	@RequestMapping(value={"/delete"})
	@ApiOperation(value="移除对比标准-属性API",httpMethod="DELETE",notes="通过移除对比标准ID,移除对比标准属性.(需用户确认删除操作!)"
			+ "入参:mcaID <br/>@author  ljc<br/>@version 2018-03-25<br/>",response=InformationBody.class)
	public@ResponseBody InformationBody delete(
            @ApiParam(required=false,value="对比标准ID",name="mcaID")@RequestParam() Integer mcaID,
            @ApiParam(required=false,value="对比属性类型（1对比，2材料约定）",name="attrType")@RequestParam() Integer attrType,
            HttpSession session){
		return materialComparedAttributesService.delete(mcaID,attrType, MMSUtils.getCardNumber(session));
	}

}