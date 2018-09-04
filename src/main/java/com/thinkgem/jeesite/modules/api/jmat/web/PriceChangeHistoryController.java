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
import com.thinkgem.jeesite.modules.api.jmat.entity.PriceChangeHistory;
import com.thinkgem.jeesite.modules.api.jmat.service.PriceChangeHistoryService;

/**
 * 价格变更记录Controller
 * @author ljc
 * @version 2018-05-09
 */
@Controller
@RequestMapping(value = "${adminPath}/jmat/priceChangeHistory")
public class PriceChangeHistoryController extends BaseController {

	@Autowired
	private PriceChangeHistoryService priceChangeHistoryService;
	
	@ModelAttribute
	public PriceChangeHistory get(@RequestParam(required=false) String id) {
		PriceChangeHistory entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = priceChangeHistoryService.get(id);
		}
		if (entity == null){
			entity = new PriceChangeHistory();
		}
		return entity;
	}
	
	@RequiresPermissions("jmat:priceChangeHistory:view")
	@RequestMapping(value = {"list", ""})
	public String list(PriceChangeHistory priceChangeHistory, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PriceChangeHistory> page = priceChangeHistoryService.findPage(new Page<PriceChangeHistory>(request, response), priceChangeHistory); 
		model.addAttribute("page", page);
		return "modules/jmat/priceChangeHistoryList";
	}

	@RequiresPermissions("jmat:priceChangeHistory:view")
	@RequestMapping(value = "form")
	public String form(PriceChangeHistory priceChangeHistory, Model model) {
		model.addAttribute("priceChangeHistory", priceChangeHistory);
		return "modules/jmat/priceChangeHistoryForm";
	}

	@RequiresPermissions("jmat:priceChangeHistory:edit")
	@RequestMapping(value = "save")
	public String save(PriceChangeHistory priceChangeHistory, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, priceChangeHistory)){
			return form(priceChangeHistory, model);
		}
		priceChangeHistoryService.save(priceChangeHistory);
		addMessage(redirectAttributes, "保存价格变更记录成功");
		return "redirect:"+Global.getAdminPath()+"/jmat/priceChangeHistory/?repage";
	}
	
	@RequiresPermissions("jmat:priceChangeHistory:edit")
	@RequestMapping(value = "delete")
	public String delete(PriceChangeHistory priceChangeHistory, RedirectAttributes redirectAttributes) {
		priceChangeHistoryService.delete(priceChangeHistory);
		addMessage(redirectAttributes, "删除价格变更记录成功");
		return "redirect:"+Global.getAdminPath()+"/jmat/priceChangeHistory/?repage";
	}

}