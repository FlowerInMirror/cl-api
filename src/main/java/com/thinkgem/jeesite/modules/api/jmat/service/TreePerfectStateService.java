/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.api.jmat.entity.TreePerfectState;
import com.thinkgem.jeesite.modules.api.jmat.dao.TreePerfectStateDao;

/**
 * 材料母库完善状态(保存档次信息)Service
 * @author ljc
 * @version 2018-03-29
 */
@Service
@Transactional(readOnly = true)
public class TreePerfectStateService extends CrudService<TreePerfectStateDao, TreePerfectState> {

	public TreePerfectState get(String id) {
		return super.get(id);
	}
	
	public List<TreePerfectState> findList(TreePerfectState treePerfectState) {
		return super.findList(treePerfectState);
	}
	
	public Page<TreePerfectState> findPage(Page<TreePerfectState> page, TreePerfectState treePerfectState) {
		return super.findPage(page, treePerfectState);
	}
	

	@Transactional(readOnly = false)
	public void delete(TreePerfectState treePerfectState) {
		super.delete(treePerfectState);
	}
	
}