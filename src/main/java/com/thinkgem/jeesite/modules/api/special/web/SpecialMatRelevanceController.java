/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.special.web;

import javax.servlet.http.HttpServletRequest;

import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.InformationBody;
import com.thinkgem.jeesite.modules.web.mms.utils.MMSUtils;
import com.thinkgem.jeesite.modules.api.special.entity.vo.SpecialMatRelevanceVo;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.api.special.service.SpecialMatRelevanceService;

/**
 * 专项材料关联表Controller
 * @author ljc
 * @version 2018-08-08
 */
@Controller
@RequestMapping(value = "special_matrelevance-api")
public class SpecialMatRelevanceController extends BaseController {

	@Autowired
	private SpecialMatRelevanceService specialMatRelevanceService;

    // 专项: 材料关联更新
    /**
     * 更新内容/专项关联材料ID/操作人卡号/事件地区
     * @author ljc
     * @createTime 2018-8-9 15:54:05
     */
    @RequestMapping(value = "update")
    @ApiOperation(value="专项: 材料关联更新API",httpMethod="POST",notes="更新内容/专项关联材料ID/操作人卡号/事件地区<br/>@author  ljc<br/>@version  2018-8-9 11:08:38",response = InformationBody.class)
    public@ResponseBody
    InformationBody  updateMatRelevance(
            @ApiParam(required=true,value="专项套餐实体包装类",name="specialSetMealVo")@RequestBody SpecialMatRelevanceVo specialMatRelevanceVo, HttpServletRequest request) {
        return specialMatRelevanceService.updateMatRelevance(specialMatRelevanceVo.setDbIP(MMSUtils.getRequestUserIP(request)));
    }
	

}