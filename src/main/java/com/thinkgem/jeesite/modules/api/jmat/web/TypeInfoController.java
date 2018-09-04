/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.api.jmat.entity.TypeInfo;
import com.thinkgem.jeesite.modules.api.jmat.service.TypeInfoService;

/**
 * 分类表Controller
 * @author ljc
 * @version 2018-04-12
 */
@Controller
@RequestMapping(value = "${adminPath}/jmat/typeInfo")
public class TypeInfoController extends BaseController {

	@Autowired
	private TypeInfoService typeInfoService;
	
	@ModelAttribute
	public TypeInfo get(@RequestParam(required=false) String id) {
		TypeInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = typeInfoService.get(id);
		}
		if (entity == null){
			entity = new TypeInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("jmat:typeInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(TypeInfo typeInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TypeInfo> page = typeInfoService.findPage(new Page<TypeInfo>(request, response), typeInfo); 
		model.addAttribute("page", page);
		return "modules/jmat/typeInfoList";
	}

	@RequiresPermissions("jmat:typeInfo:view")
	@RequestMapping(value = "form")
	public String form(TypeInfo typeInfo, Model model) {
		model.addAttribute("typeInfo", typeInfo);
		return "modules/jmat/typeInfoForm";
	}

	@RequiresPermissions("jmat:typeInfo:edit")
	@RequestMapping(value = "save")
	public String save(TypeInfo typeInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, typeInfo)){
			return form(typeInfo, model);
		}
		typeInfoService.save(typeInfo);
		addMessage(redirectAttributes, "保存分类表成功");
		return "redirect:"+Global.getAdminPath()+"/jmat/typeInfo/?repage";
	}
	
	@RequiresPermissions("jmat:typeInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(TypeInfo typeInfo, RedirectAttributes redirectAttributes) {
		typeInfoService.delete(typeInfo);
		addMessage(redirectAttributes, "删除分类表成功");
		return "redirect:"+Global.getAdminPath()+"/jmat/typeInfo/?repage";
	}

}