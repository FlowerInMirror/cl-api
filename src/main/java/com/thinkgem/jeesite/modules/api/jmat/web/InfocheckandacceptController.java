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
import com.thinkgem.jeesite.modules.api.jmat.entity.Infocheckandaccept;
import com.thinkgem.jeesite.modules.api.jmat.service.InfocheckandacceptService;

/**
 * 信息审核验收表Controller
 * @author ljc
 * @version 2018-06-16
 */
@Controller
@RequestMapping(value = "${adminPath}/jmat/infocheckandaccept")
public class InfocheckandacceptController extends BaseController {

	@Autowired
	private InfocheckandacceptService infocheckandacceptService;
	
	@ModelAttribute
	public Infocheckandaccept get(@RequestParam(required=false) String id) {
		Infocheckandaccept entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = infocheckandacceptService.get(id);
		}
		if (entity == null){
			entity = new Infocheckandaccept();
		}
		return entity;
	}
	
	@RequiresPermissions("jmat:infocheckandaccept:view")
	@RequestMapping(value = {"list", ""})
	public String list(Infocheckandaccept infocheckandaccept, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Infocheckandaccept> page = infocheckandacceptService.findPage(new Page<Infocheckandaccept>(request, response), infocheckandaccept); 
		model.addAttribute("page", page);
		return "modules/jmat/infocheckandacceptList";
	}

	@RequiresPermissions("jmat:infocheckandaccept:view")
	@RequestMapping(value = "form")
	public String form(Infocheckandaccept infocheckandaccept, Model model) {
		model.addAttribute("infocheckandaccept", infocheckandaccept);
		return "modules/jmat/infocheckandacceptForm";
	}

	@RequiresPermissions("jmat:infocheckandaccept:edit")
	@RequestMapping(value = "save")
	public String save(Infocheckandaccept infocheckandaccept, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, infocheckandaccept)){
			return form(infocheckandaccept, model);
		}
		infocheckandacceptService.save(infocheckandaccept);
		addMessage(redirectAttributes, "保存信息审核验收表成功");
		return "redirect:"+Global.getAdminPath()+"/jmat/infocheckandaccept/?repage";
	}
	
	@RequiresPermissions("jmat:infocheckandaccept:edit")
	@RequestMapping(value = "delete")
	public String delete(Infocheckandaccept infocheckandaccept, RedirectAttributes redirectAttributes) {
		infocheckandacceptService.delete(infocheckandaccept);
		addMessage(redirectAttributes, "删除信息审核验收表成功");
		return "redirect:"+Global.getAdminPath()+"/jmat/infocheckandaccept/?repage";
	}

}