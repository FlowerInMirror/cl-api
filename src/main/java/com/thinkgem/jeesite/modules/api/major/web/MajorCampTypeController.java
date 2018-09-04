/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.major.web;

import javax.servlet.http.HttpServletRequest;

import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.InformationBody;
import com.thinkgem.jeesite.modules.api.major.entity.vo.MajorCampTypeVo;
import com.thinkgem.jeesite.modules.web.mms.utils.MMSUtils;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.api.major.service.MajorCampTypeService;

/**
 * 主营属性类别表Controller
 * @author ljc
 * @version 2018-08-17
 */
@Controller
@RequestMapping(value = "major_type-api")
public class MajorCampTypeController extends BaseController {

	@Autowired
	private MajorCampTypeService majorCampTypeService;

    @RequestMapping(value = "add")
    @ApiOperation(value="主营管理:添加(主营属性类别)API",httpMethod="POST",notes="保存内容:主营二级科目ID/名称/排序<br/>@author  ljc<br/>@version 2018-8-18 11:02:13",response = InformationBody.class)
    public @ResponseBody InformationBody addCategoryByEntityVo(@RequestBody MajorCampTypeVo majorCampTypeVo, HttpServletRequest request) {
        return majorCampTypeService.addCategoryByEntityVo(majorCampTypeVo.setDbIP(MMSUtils.getRequestUserIP(request)));
    }

    @RequestMapping(value = "update")
    @ApiOperation(value="主营管理:更新(主营属性类别)API",httpMethod="POST",notes="@author  ljc<br/>@version 2018-8-18 13:14:10",response = InformationBody.class)
    public @ResponseBody InformationBody updateCategoryByEntityVo(@RequestBody MajorCampTypeVo majorCampTypeVo, HttpServletRequest request) {
        return majorCampTypeService.updateCategoryByEntityVo(majorCampTypeVo.setDbIP(MMSUtils.getRequestUserIP(request)));
    }

    @RequestMapping(value = "delete")
    @ApiOperation(value="主营管理:删除(主营属性类别)API",httpMethod="POST",notes="@author  ljc<br/>@version 2018-8-18 13:16:38",response = InformationBody.class)
    public @ResponseBody InformationBody deleteCategoryByEntityVo(@RequestBody MajorCampTypeVo majorCampTypeVo, HttpServletRequest request) {
        return majorCampTypeService.deleteCategoryByEntityVo(majorCampTypeVo.setDbIP(MMSUtils.getRequestUserIP(request)));
    }

	


}