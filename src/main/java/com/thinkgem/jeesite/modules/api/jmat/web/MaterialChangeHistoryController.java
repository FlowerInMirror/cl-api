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
import com.thinkgem.jeesite.modules.api.jmat.entity.MaterialChangeHistory;
import com.thinkgem.jeesite.modules.api.jmat.service.MaterialChangeHistoryService;

/**
 * 子库更新审核表Controller
 * @author ljc
 * @version 2018-05-09
 */
@Controller
@RequestMapping(value = "${adminPath}/jmat/materialChangeHistory")
public class MaterialChangeHistoryController extends BaseController {

	@Autowired
	private MaterialChangeHistoryService materialChangeHistoryService;
	
	@ModelAttribute
	public MaterialChangeHistory get(@RequestParam(required=false) String id) {
		MaterialChangeHistory entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = materialChangeHistoryService.get(id);
		}
		if (entity == null){
			entity = new MaterialChangeHistory();
		}
		return entity;
	}
	
	@RequiresPermissions("jmat:materialChangeHistory:view")
	@RequestMapping(value = {"list", ""})
	public String list(MaterialChangeHistory materialChangeHistory, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MaterialChangeHistory> page = materialChangeHistoryService.findPage(new Page<MaterialChangeHistory>(request, response), materialChangeHistory); 
		model.addAttribute("page", page);
		return "modules/jmat/materialChangeHistoryList";
	}

	@RequiresPermissions("jmat:materialChangeHistory:view")
	@RequestMapping(value = "form")
	public String form(MaterialChangeHistory materialChangeHistory, Model model) {
		model.addAttribute("materialChangeHistory", materialChangeHistory);
		return "modules/jmat/materialChangeHistoryForm";
	}

	@RequiresPermissions("jmat:materialChangeHistory:edit")
	@RequestMapping(value = "save")
	public String save(MaterialChangeHistory materialChangeHistory, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, materialChangeHistory)){
			return form(materialChangeHistory, model);
		}
		materialChangeHistoryService.save(materialChangeHistory);
		addMessage(redirectAttributes, "保存子库更新审核表成功");
		return "redirect:"+Global.getAdminPath()+"/jmat/materialChangeHistory/?repage";
	}
	
	@RequiresPermissions("jmat:materialChangeHistory:edit")
	@RequestMapping(value = "delete")
	public String delete(MaterialChangeHistory materialChangeHistory, RedirectAttributes redirectAttributes) {
		materialChangeHistoryService.delete(materialChangeHistory);
		addMessage(redirectAttributes, "删除子库更新审核表成功");
		return "redirect:"+Global.getAdminPath()+"/jmat/materialChangeHistory/?repage";
	}

}