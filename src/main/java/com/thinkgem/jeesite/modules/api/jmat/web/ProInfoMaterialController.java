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
import com.thinkgem.jeesite.modules.api.jmat.entity.ProInfoMaterial;
import com.thinkgem.jeesite.modules.api.jmat.service.ProInfoMaterialService;

/**
 * 项目材料表Controller
 * @author ljc
 * @version 2018-07-26
 */
@Controller
@RequestMapping(value = "${adminPath}/jmat/proInfoMaterial")
public class ProInfoMaterialController extends BaseController {

	@Autowired
	private ProInfoMaterialService proInfoMaterialService;
	
	@ModelAttribute
	public ProInfoMaterial get(@RequestParam(required=false) String id) {
		ProInfoMaterial entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = proInfoMaterialService.get(id);
		}
		if (entity == null){
			entity = new ProInfoMaterial();
		}
		return entity;
	}
	
	@RequiresPermissions("jmat:proInfoMaterial:view")
	@RequestMapping(value = {"list", ""})
	public String list(ProInfoMaterial proInfoMaterial, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ProInfoMaterial> page = proInfoMaterialService.findPage(new Page<ProInfoMaterial>(request, response), proInfoMaterial); 
		model.addAttribute("page", page);
		return "modules/jmat/proInfoMaterialList";
	}

	@RequiresPermissions("jmat:proInfoMaterial:view")
	@RequestMapping(value = "form")
	public String form(ProInfoMaterial proInfoMaterial, Model model) {
		model.addAttribute("proInfoMaterial", proInfoMaterial);
		return "modules/jmat/proInfoMaterialForm";
	}

	@RequiresPermissions("jmat:proInfoMaterial:edit")
	@RequestMapping(value = "save")
	public String save(ProInfoMaterial proInfoMaterial, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, proInfoMaterial)){
			return form(proInfoMaterial, model);
		}
		proInfoMaterialService.save(proInfoMaterial);
		addMessage(redirectAttributes, "保存项目材料成功");
		return "redirect:"+Global.getAdminPath()+"/jmat/proInfoMaterial/?repage";
	}
	
	@RequiresPermissions("jmat:proInfoMaterial:edit")
	@RequestMapping(value = "delete")
	public String delete(ProInfoMaterial proInfoMaterial, RedirectAttributes redirectAttributes) {
		proInfoMaterialService.delete(proInfoMaterial);
		addMessage(redirectAttributes, "删除项目材料成功");
		return "redirect:"+Global.getAdminPath()+"/jmat/proInfoMaterial/?repage";
	}

}