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
import com.thinkgem.jeesite.modules.api.jmat.entity.MaterialPerfectState;
import com.thinkgem.jeesite.modules.api.jmat.service.MaterialPerfectStateService;

/**
 * 材料子库完善状态(用于保存地区信息)Controller
 * @author ljc
 * @version 2018-03-29
 */
@Controller
@RequestMapping(value = "${adminPath}/jmat/materialPerfectState")
public class MaterialPerfectStateController extends BaseController {

	@Autowired
	private MaterialPerfectStateService materialPerfectStateService;
	
	@ModelAttribute
	public MaterialPerfectState get(@RequestParam(required=false) String id) {
		MaterialPerfectState entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = materialPerfectStateService.get(id);
		}
		if (entity == null){
			entity = new MaterialPerfectState();
		}
		return entity;
	}
	
	@RequiresPermissions("jmat:materialPerfectState:view")
	@RequestMapping(value = {"list", ""})
	public String list(MaterialPerfectState materialPerfectState, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MaterialPerfectState> page = materialPerfectStateService.findPage(new Page<MaterialPerfectState>(request, response), materialPerfectState); 
		model.addAttribute("page", page);
		return "modules/jmat/materialPerfectStateList";
	}

	@RequiresPermissions("jmat:materialPerfectState:view")
	@RequestMapping(value = "form")
	public String form(MaterialPerfectState materialPerfectState, Model model) {
		model.addAttribute("materialPerfectState", materialPerfectState);
		return "modules/jmat/materialPerfectStateForm";
	}

	@RequiresPermissions("jmat:materialPerfectState:edit")
	@RequestMapping(value = "save")
	public String save(MaterialPerfectState materialPerfectState, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, materialPerfectState)){
			return form(materialPerfectState, model);
		}
		materialPerfectStateService.save(materialPerfectState);
		addMessage(redirectAttributes, "保存材料子库完善状态成功");
		return "redirect:"+Global.getAdminPath()+"/jmat/materialPerfectState/?repage";
	}
	
	@RequiresPermissions("jmat:materialPerfectState:edit")
	@RequestMapping(value = "delete")
	public String delete(MaterialPerfectState materialPerfectState, RedirectAttributes redirectAttributes) {
		materialPerfectStateService.delete(materialPerfectState);
		addMessage(redirectAttributes, "删除材料子库完善状态成功");
		return "redirect:"+Global.getAdminPath()+"/jmat/materialPerfectState/?repage";
	}

}