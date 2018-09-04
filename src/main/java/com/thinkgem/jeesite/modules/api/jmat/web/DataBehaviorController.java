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
import com.thinkgem.jeesite.modules.api.jmat.entity.DataBehavior;
import com.thinkgem.jeesite.modules.api.jmat.service.DataBehaviorService;

/**
 * 数据行为Controller
 * @author ljc
 * @version 2018-04-09
 */
@Controller
@RequestMapping(value = "${adminPath}/jmat/dataBehavior")
public class DataBehaviorController extends BaseController {

	@Autowired
	private DataBehaviorService dataBehaviorService;
	
	@ModelAttribute
	public DataBehavior get(@RequestParam(required=false) String id) {
		DataBehavior entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dataBehaviorService.get(id);
		}
		if (entity == null){
			entity = new DataBehavior();
		}
		return entity;
	}
	
	@RequiresPermissions("jmat:dataBehavior:view")
	@RequestMapping(value = {"list", ""})
	public String list(DataBehavior dataBehavior, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DataBehavior> page = dataBehaviorService.findPage(new Page<DataBehavior>(request, response), dataBehavior); 
		model.addAttribute("page", page);
		return "modules/jmat/dataBehaviorList";
	}

	@RequiresPermissions("jmat:dataBehavior:view")
	@RequestMapping(value = "form")
	public String form(DataBehavior dataBehavior, Model model) {
		model.addAttribute("dataBehavior", dataBehavior);
		return "modules/jmat/dataBehaviorForm";
	}

	@RequiresPermissions("jmat:dataBehavior:edit")
	@RequestMapping(value = "save")
	public String save(DataBehavior dataBehavior, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dataBehavior)){
			return form(dataBehavior, model);
		}
		dataBehaviorService.save(dataBehavior);
		addMessage(redirectAttributes, "保存数据行为成功");
		return "redirect:"+Global.getAdminPath()+"/jmat/dataBehavior/?repage";
	}
	
	@RequiresPermissions("jmat:dataBehavior:edit")
	@RequestMapping(value = "delete")
	public String delete(DataBehavior dataBehavior, RedirectAttributes redirectAttributes) {
		dataBehaviorService.delete(dataBehavior);
		addMessage(redirectAttributes, "删除数据行为成功");
		return "redirect:"+Global.getAdminPath()+"/jmat/dataBehavior/?repage";
	}

}