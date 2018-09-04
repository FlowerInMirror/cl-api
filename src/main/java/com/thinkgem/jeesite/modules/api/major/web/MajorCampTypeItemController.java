/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.major.web;

import javax.servlet.http.HttpServletRequest;

import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.InformationBody;
import com.thinkgem.jeesite.modules.api.major.entity.vo.MajorCampTypeItemVo;
import com.thinkgem.jeesite.modules.web.mms.utils.MMSUtils;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.api.major.service.MajorCampTypeItemService;

/**
 * 主营属性类别项表Controller
 * @author ljc
 * @version 2018-08-17
 */
@Controller
@RequestMapping(value = "major_type_item-api")
public class MajorCampTypeItemController extends BaseController {

	@Autowired
	private MajorCampTypeItemService majorCampTypeItemService;

    @RequestMapping(value = "add")
    @ApiOperation(value="主营管理:添加主营属性(类别项)API",httpMethod="POST",notes="保存内容:属性类别ID/名称/排序<br/>@author  ljc<br/>@version 2018-8-18 13:59:20",response = InformationBody.class)
    public @ResponseBody
    InformationBody addCategoryItemByEntityVo(@RequestBody MajorCampTypeItemVo majorCampTypeItemVo, HttpServletRequest request) {
        return majorCampTypeItemService.addCategoryItemByEntityVo(majorCampTypeItemVo.setDbIP(MMSUtils.getRequestUserIP(request)));
    }

    @RequestMapping(value = "update")
    @ApiOperation(value="主营管理:更新主营属性(类别项)API",httpMethod="POST",notes="@author  ljc<br/>@version 2018-8-18 14:00:12",response = InformationBody.class)
    public @ResponseBody InformationBody updateCategoryItemByEntityVo(@RequestBody MajorCampTypeItemVo majorCampTypeItemVo, HttpServletRequest request) {
        return majorCampTypeItemService.updateCategoryItemByEntityVo(majorCampTypeItemVo.setDbIP(MMSUtils.getRequestUserIP(request)));
    }

    @RequestMapping(value = "delete")
    @ApiOperation(value="主营管理:删除主营属性(类别项)API",httpMethod="POST",notes="@author  ljc<br/>@version 2018-8-18 14:00:17",response = InformationBody.class)
    public @ResponseBody InformationBody deleteCategoryItemByEntityVo(@RequestBody MajorCampTypeItemVo majorCampTypeItemVo, HttpServletRequest request) {
        return majorCampTypeItemService.deleteCategoryItemByEntityVo(majorCampTypeItemVo.setDbIP(MMSUtils.getRequestUserIP(request)));
    }

}