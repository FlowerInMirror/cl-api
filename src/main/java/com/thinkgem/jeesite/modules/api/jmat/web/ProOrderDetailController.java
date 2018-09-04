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
import com.thinkgem.jeesite.modules.api.jmat.entity.ProOrderDetail;
import com.thinkgem.jeesite.modules.api.jmat.service.ProOrderDetailService;

/**
 * 配送单明细Controller
 * @author ljc
 * @version 2018-04-14
 */
@Controller
@RequestMapping(value = "${adminPath}/jmat/proOrderDetail")
public class ProOrderDetailController extends BaseController {

	@Autowired
	private ProOrderDetailService proOrderDetailService;
	
	@ModelAttribute
	public ProOrderDetail get(@RequestParam(required=false) String id) {
		ProOrderDetail entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = proOrderDetailService.get(id);
		}
		if (entity == null){
			entity = new ProOrderDetail();
		}
		return entity;
	}
	
	@RequiresPermissions("jmat:proOrderDetail:view")
	@RequestMapping(value = {"list", ""})
	public String list(ProOrderDetail proOrderDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ProOrderDetail> page = proOrderDetailService.findPage(new Page<ProOrderDetail>(request, response), proOrderDetail); 
		model.addAttribute("page", page);
		return "modules/jmat/proOrderDetailList";
	}

	@RequiresPermissions("jmat:proOrderDetail:view")
	@RequestMapping(value = "form")
	public String form(ProOrderDetail proOrderDetail, Model model) {
		model.addAttribute("proOrderDetail", proOrderDetail);
		return "modules/jmat/proOrderDetailForm";
	}

	@RequiresPermissions("jmat:proOrderDetail:edit")
	@RequestMapping(value = "save")
	public String save(ProOrderDetail proOrderDetail, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, proOrderDetail)){
			return form(proOrderDetail, model);
		}
		proOrderDetailService.save(proOrderDetail);
		addMessage(redirectAttributes, "保存配送单明细成功");
		return "redirect:"+Global.getAdminPath()+"/jmat/proOrderDetail/?repage";
	}
	
	@RequiresPermissions("jmat:proOrderDetail:edit")
	@RequestMapping(value = "delete")
	public String delete(ProOrderDetail proOrderDetail, RedirectAttributes redirectAttributes) {
		proOrderDetailService.delete(proOrderDetail);
		addMessage(redirectAttributes, "删除配送单明细成功");
		return "redirect:"+Global.getAdminPath()+"/jmat/proOrderDetail/?repage";
	}

}