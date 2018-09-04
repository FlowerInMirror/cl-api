/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.special.web;

import javax.servlet.http.HttpServletRequest;

import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.InformationBody;
import com.thinkgem.jeesite.modules.web.mms.utils.MMSUtils;
import com.thinkgem.jeesite.modules.api.special.entity.vo.SpecialSetMealVo;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.api.special.service.SpecialSetMealService;

import java.util.List;

/**
 * 专项套餐表Controller
 * @author ljc
 * @version 2018-08-08
 */
@Controller
@RequestMapping(value = "special_setmeal-api")
public class SpecialSetMealController extends BaseController {

	@Autowired
	private SpecialSetMealService specialSetMealService;

	// 专项: 删除套餐
    /**
     * ssm_Status 进程：0正常、1删除、2下架  （默认0）
     * @author ljc
     * @createTime 2018-8-9 11:58:24
     */
    @RequestMapping(value = "delete")
    @ApiOperation(value="专项: 删除套餐API",httpMethod="POST",notes="通过专项套餐实体<br/>@author  ljc<br/>@version  2018-8-9 11:08:38",response = InformationBody.class)
    public@ResponseBody
    InformationBody  deleteSetMeal(
            @ApiParam(required=true,value="专项套餐实体包装类",name="specialSetMealVo")@RequestBody SpecialSetMealVo specialSetMealVo,HttpServletRequest request) {
        specialSetMealVo.setDbIP(MMSUtils.getRequestUserIP(request));
        return specialSetMealService.deleteSetMeal(specialSetMealVo);
    }

    // 专项: 更新套餐(更新字段/下架功能)
    /**
     * ssm_Status 进程：0正常、1删除、2下架  （默认0）
     * @author ljc
     * @createTime 2018-8-9 11:58:32
     */
    @RequestMapping(value = "update")
    @ApiOperation(value="专项: 更新套餐API",httpMethod="GET",notes="通过专项套餐实体<br/>@author  ljc<br/>@version  2018-8-9 11:08:38",response = InformationBody.class)
    public@ResponseBody
    InformationBody  updateSetMeal(
            @ApiParam(required=true,value="专项套餐实体包装类",name="specialSetMealVo")@RequestBody SpecialSetMealVo specialSetMealVo,HttpServletRequest request) {
        specialSetMealVo.setDbIP(MMSUtils.getRequestUserIP(request));
        return specialSetMealService.updateSetMeal(specialSetMealVo);
    }

    // 专项: 批量更新套餐集(含括新增套餐)
    /**
     * @author ljc
     * @createTime 2018-8-13 16:57:25
     */
    @RequestMapping(value = "updates")
    @ApiOperation(value="专项: 更新套餐集(含括新增套餐)API",httpMethod="GET",notes="通过专项套餐实体集(含括新增套餐集)<br/>@author  ljc<br/>@version  2018-8-13 16:57:25",response = InformationBody.class)
    public@ResponseBody
    InformationBody  updateSetMeals(
            @ApiParam(required=true,value="专项套餐实体包装类",name="specialSetMealVo")@RequestBody List<SpecialSetMealVo> specialSetMealVos, HttpServletRequest request) {
        return specialSetMealService.updateSetMeals(specialSetMealVos,MMSUtils.getRequestUserIP(request));
    }



	


}