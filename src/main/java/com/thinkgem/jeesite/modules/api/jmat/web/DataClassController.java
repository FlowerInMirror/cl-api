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
import com.thinkgem.jeesite.modules.api.jmat.entity.DataClass;
import com.thinkgem.jeesite.modules.api.jmat.service.DataClassService;

/**
 * 数据行为分类Controller
 * @author ljc
 * @version 2018-04-09
 */
@Controller
@RequestMapping(value = "${adminPath}/jmat/dataClass")
public class DataClassController extends BaseController {

	@Autowired
	private DataClassService dataClassService;
	
	@ModelAttribute
	public DataClass get(@RequestParam(required=false) String id) {
		DataClass entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dataClassService.get(id);
		}
		if (entity == null){
			entity = new DataClass();
		}
		return entity;
	}
	
	@RequiresPermissions("jmat:dataClass:view")
	@RequestMapping(value = {"list", ""})
	public String list(DataClass dataClass, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DataClass> page = dataClassService.findPage(new Page<DataClass>(request, response), dataClass); 
		model.addAttribute("page", page);
		return "modules/jmat/dataClassList";
	}

	@RequiresPermissions("jmat:dataClass:view")
	@RequestMapping(value = "form")
	public String form(DataClass dataClass, Model model) {
		model.addAttribute("dataClass", dataClass);
		return "modules/jmat/dataClassForm";
	}

	@RequiresPermissions("jmat:dataClass:edit")
	@RequestMapping(value = "save")
	public String save(DataClass dataClass, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dataClass)){
			return form(dataClass, model);
		}
		dataClassService.save(dataClass);
		addMessage(redirectAttributes, "保存数据行为分类成功");
		return "redirect:"+Global.getAdminPath()+"/jmat/dataClass/?repage";
	}
	
	@RequiresPermissions("jmat:dataClass:edit")
	@RequestMapping(value = "delete")
	public String delete(DataClass dataClass, RedirectAttributes redirectAttributes) {
		dataClassService.delete(dataClass);
		addMessage(redirectAttributes, "删除数据行为分类成功");
		return "redirect:"+Global.getAdminPath()+"/jmat/dataClass/?repage";
	}

}