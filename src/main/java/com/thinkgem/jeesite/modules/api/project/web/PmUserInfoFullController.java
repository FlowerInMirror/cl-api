/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.project.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.api.project.entity.PmUserInfoFull;
import com.thinkgem.jeesite.modules.api.project.service.PmUserInfoFullService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 项目人事(新)Controller
 * @author ljc
 * @version 2018-06-21
 */
@Controller
@RequestMapping(value = "${adminPath}/gcgl/pmUserInfoFull")
public class PmUserInfoFullController extends BaseController {

	@Autowired
	private PmUserInfoFullService pmUserInfoFullService;
	
	@ModelAttribute
	public PmUserInfoFull get(@RequestParam(required=false) String id) {
		PmUserInfoFull entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pmUserInfoFullService.get(id);
		}
		if (entity == null){
			entity = new PmUserInfoFull();
		}
		return entity;
	}
	
	@RequiresPermissions("gcgl:pmUserInfoFull:view")
	@RequestMapping(value = {"list", ""})
	public String list(PmUserInfoFull pmUserInfoFull, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PmUserInfoFull> page = pmUserInfoFullService.findPage(new Page<PmUserInfoFull>(request, response), pmUserInfoFull);
		model.addAttribute("page", page);
		return "modules/gcgl/pmUserInfoFullList";
	}

	@RequiresPermissions("gcgl:pmUserInfoFull:view")
	@RequestMapping(value = "form")
	public String form(PmUserInfoFull pmUserInfoFull, Model model) {
		model.addAttribute("pmUserInfoFull", pmUserInfoFull);
		return "modules/gcgl/pmUserInfoFullForm";
	}

	@RequiresPermissions("gcgl:pmUserInfoFull:edit")
	@RequestMapping(value = "save")
	public String save(PmUserInfoFull pmUserInfoFull, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pmUserInfoFull)){
			return form(pmUserInfoFull, model);
		}
		pmUserInfoFullService.save(pmUserInfoFull);
		addMessage(redirectAttributes, "保存项目人事(全)成功");
		return "redirect:"+ Global.getAdminPath()+"/gcgl/pmUserInfoFull/?repage";
	}
	
	@RequiresPermissions("gcgl:pmUserInfoFull:edit")
	@RequestMapping(value = "delete")
	public String delete(PmUserInfoFull pmUserInfoFull, RedirectAttributes redirectAttributes) {
		pmUserInfoFullService.delete(pmUserInfoFull);
		addMessage(redirectAttributes, "删除项目人事(全)成功");
		return "redirect:"+ Global.getAdminPath()+"/gcgl/pmUserInfoFull/?repage";
	}

}