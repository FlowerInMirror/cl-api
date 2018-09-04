/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.common.persistence.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.api.jmat.entity.TabkeRelation;
import com.thinkgem.jeesite.modules.api.jmat.service.TabkeRelationService;

/**
 * 材料表关联性汇总表Controller
 * @author ljc
 * @version 2018-06-16
 */
@Controller
@RequestMapping(value = "${adminPath}/jmat/tabkeRelation")
public class TabkeRelationController extends BaseController {

	@Autowired
	private TabkeRelationService tabkeRelationService;
	
	@ModelAttribute
	public TabkeRelation get(@RequestParam(required=false) String id) {
		TabkeRelation entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tabkeRelationService.get(id);
		}
		if (entity == null){
			entity = new TabkeRelation();
		}
		return entity;
	}
	
	@RequiresPermissions("jmat:tabkeRelation:view")
	@RequestMapping(value = {"list", ""})
	public String list(TabkeRelation tabkeRelation, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TabkeRelation> page = tabkeRelationService.findPage(new Page<TabkeRelation>(request, response), tabkeRelation);
		model.addAttribute("page", page);
		return "modules/jmat/tabkeRelationList";
	}

	@RequiresPermissions("jmat:tabkeRelation:view")
	@RequestMapping(value = "form")
	public String form(TabkeRelation tabkeRelation, Model model) {
		model.addAttribute("tabkeRelation", tabkeRelation);
		return "modules/jmat/tabkeRelationForm";
	}

	@RequiresPermissions("jmat:tabkeRelation:edit")
	@RequestMapping(value = "save")
	public String save(TabkeRelation tabkeRelation, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tabkeRelation)){
			return form(tabkeRelation, model);
		}
		tabkeRelationService.save(tabkeRelation);
		addMessage(redirectAttributes, "保存材料表关联性汇总表成功");
		return "redirect:"+Global.getAdminPath()+"/jmat/tabkeRelation/?repage";
	}
	
	@RequiresPermissions("jmat:tabkeRelation:edit")
	@RequestMapping(value = "delete")
	public String delete(TabkeRelation tabkeRelation, RedirectAttributes redirectAttributes) {
		tabkeRelationService.delete(tabkeRelation);
		addMessage(redirectAttributes, "删除材料表关联性汇总表成功");
		return "redirect:"+Global.getAdminPath()+"/jmat/tabkeRelation/?repage";
	}

}