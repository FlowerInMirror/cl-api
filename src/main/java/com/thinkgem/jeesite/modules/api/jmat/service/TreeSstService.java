/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.service;

import java.util.List;

import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.InformationBody;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.api.jmat.entity.TreeSst;
import com.thinkgem.jeesite.modules.api.jmat.dao.TreeSstDao;

/**
 * 科目树设置Service
 * @author ljc
 * @version 2018-03-17
 */
@Service
@Transactional(readOnly = true)
public class TreeSstService extends CrudService<TreeSstDao, TreeSst> {

	public TreeSst get(String id) {
		return super.get(id);
	}
	
	public List<TreeSst> findList(TreeSst treeSst) {
		return super.findList(treeSst);
	}
	
	public Page<TreeSst> findPage(Page<TreeSst> page, TreeSst treeSst) {
		return super.findPage(page, treeSst);
	}
	

	
	@Transactional(readOnly = false)
	public void delete(TreeSst treeSst) {
		super.delete(treeSst);
	}

    // 获取材料分类
    /**
     * @author ljc
     * @param  treeFourID 材料规格ID
     * @return body       材料分类 1成品，2非成品 (默认0)
     * @createTime 2018-7-9 11:47:39
     */
    public InformationBody getMatClassify(String treeFourID) {
        InformationBody informationBody = new InformationBody();
        try {
            TreeSst treeSst = dao.findTreeSstByTreeID(treeFourID);
            informationBody.setBody(treeSst.getTsTypestate());
            logger.debug("科目树设置:获取材料分类,接口成功!MAIN-ID:"+treeFourID);
        } catch (Exception e) {
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("科目树设置:获取材料分类,接口调用失败!");
            logger.error("科目树设置:获取材料分类,接口调用失败!", e);
        }
        return informationBody;
    }
}