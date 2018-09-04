/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.project.web;

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
import com.thinkgem.jeesite.modules.api.project.entity.RsglDiQu;
import com.thinkgem.jeesite.modules.api.project.service.RsglDiQuService;

/**
 * 地区(人事管理)Controller
 * @author ljc
 * @version 2018-03-29
 */
@Controller
@RequestMapping(value = "${adminPath}/rsgl/rsglDiQu")
public class RsglDiQuController extends BaseController {

	@Autowired
	private RsglDiQuService rsglDiQuService;
	
	@ModelAttribute
	public RsglDiQu get(@RequestParam(required=false) String id) {
		RsglDiQu entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = rsglDiQuService.get(id);
		}
		if (entity == null){
			entity = new RsglDiQu();
		}
		return entity;
	}
	
	@RequiresPermissions("rsgl:rsglDiQu:view")
	@RequestMapping(value = {"list", ""})
	public String list(RsglDiQu rsglDiQu, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RsglDiQu> page = rsglDiQuService.findPage(new Page<RsglDiQu>(request, response), rsglDiQu); 
		model.addAttribute("page", page);
		return "modules/rsgl/rsglDiQuList";
	}

	@RequiresPermissions("rsgl:rsglDiQu:view")
	@RequestMapping(value = "form")
	public String form(RsglDiQu rsglDiQu, Model model) {
		model.addAttribute("rsglDiQu", rsglDiQu);
		return "modules/rsgl/rsglDiQuForm";
	}

	@RequiresPermissions("rsgl:rsglDiQu:edit")
	@RequestMapping(value = "save")
	public String save(RsglDiQu rsglDiQu, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, rsglDiQu)){
			return form(rsglDiQu, model);
		}
		rsglDiQuService.save(rsglDiQu);
		addMessage(redirectAttributes, "保存地区((人事管理))成功");
		return "redirect:"+Global.getAdminPath()+"/rsgl/rsglDiQu/?repage";
	}
	
	@RequiresPermissions("rsgl:rsglDiQu:edit")
	@RequestMapping(value = "delete")
	public String delete(RsglDiQu rsglDiQu, RedirectAttributes redirectAttributes) {
		rsglDiQuService.delete(rsglDiQu);
		addMessage(redirectAttributes, "删除地区((人事管理))成功");
		return "redirect:"+Global.getAdminPath()+"/rsgl/rsglDiQu/?repage";
	}

}