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
import com.thinkgem.jeesite.modules.api.jmat.entity.TreePerfectState;
import com.thinkgem.jeesite.modules.api.jmat.service.TreePerfectStateService;

/**
 * 材料母库完善状态(保存档次信息)Controller
 * @author ljc
 * @version 2018-03-29
 */
@Controller
@RequestMapping(value = "${adminPath}/jmat/treePerfectState")
public class TreePerfectStateController extends BaseController {

	@Autowired
	private TreePerfectStateService treePerfectStateService;
	
	@ModelAttribute
	public TreePerfectState get(@RequestParam(required=false) String id) {
		TreePerfectState entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = treePerfectStateService.get(id);
		}
		if (entity == null){
			entity = new TreePerfectState();
		}
		return entity;
	}
	
	@RequiresPermissions("jmat:treePerfectState:view")
	@RequestMapping(value = {"list", ""})
	public String list(TreePerfectState treePerfectState, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TreePerfectState> page = treePerfectStateService.findPage(new Page<TreePerfectState>(request, response), treePerfectState); 
		model.addAttribute("page", page);
		return "modules/jmat/treePerfectStateList";
	}

	@RequiresPermissions("jmat:treePerfectState:view")
	@RequestMapping(value = "form")
	public String form(TreePerfectState treePerfectState, Model model) {
		model.addAttribute("treePerfectState", treePerfectState);
		return "modules/jmat/treePerfectStateForm";
	}

	@RequiresPermissions("jmat:treePerfectState:edit")
	@RequestMapping(value = "save")
	public String save(TreePerfectState treePerfectState, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, treePerfectState)){
			return form(treePerfectState, model);
		}
		treePerfectStateService.save(treePerfectState);
		addMessage(redirectAttributes, "保存材料母库完善状态成功");
		return "redirect:"+Global.getAdminPath()+"/jmat/treePerfectState/?repage";
	}
	
	@RequiresPermissions("jmat:treePerfectState:edit")
	@RequestMapping(value = "delete")
	public String delete(TreePerfectState treePerfectState, RedirectAttributes redirectAttributes) {
		treePerfectStateService.delete(treePerfectState);
		addMessage(redirectAttributes, "删除材料母库完善状态成功");
		return "redirect:"+Global.getAdminPath()+"/jmat/treePerfectState/?repage";
	}

}