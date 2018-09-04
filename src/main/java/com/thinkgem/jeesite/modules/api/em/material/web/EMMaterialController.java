/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.em.material.web;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.api.em.material.entity.vo.EMMaterialListVo;
import com.thinkgem.jeesite.modules.api.em.material.service.EMMaterialService;
import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.InformationBody;
import com.thinkgem.jeesite.modules.api.special.entity.SpecialProduct;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 工程经理.材料Controller
 * @author ljc
 * @version 2018-8-30 09:55:53
 */
@Controller
@RequestMapping(value = "em/material-api")
public class EMMaterialController extends BaseController {

    // "==================== 业务服务对象 ===================="
	@Autowired
	private EMMaterialService emMaterialService; // 工程经理.材料



    // "======================= 列表 ========================"

    // 材料列表
	@RequestMapping(value = "list/material")
    @ApiOperation(value="列表:材料列表API",httpMethod="GET",notes="检索条件:城市ID<br/>@author  ljc<br/>@version 2018-8-30 10:13:51",response = EMMaterialListVo.class)
	public@ResponseBody InformationBody  listMateiral(
	        @ApiParam(required=false,value="城市ID",name="cityID")@RequestParam(required = false) Integer cityID) {
        return emMaterialService.findEMMaterialListVoByCityID(cityID);
	}


}