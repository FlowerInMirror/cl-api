/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.InformationBody;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.api.jmat.entity.Tree;
import com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.city.SubLibraryCityOneSection;
import com.thinkgem.jeesite.modules.api.jmat.dao.TreeDao;

/**
 * 科目树Service
 * @author ljc
 * @version 2018-03-15
 */
@Service
@Transactional(readOnly = true)
public class TreeService extends CrudService<TreeDao, Tree> {


	public Tree get(String id) {
		return super.get(id);
	}

	public List<Tree> findList(Tree tree) {
		return super.findList(tree);
	}

	public Page<Tree> findPage(Page<Tree> page, Tree tree) {
		return super.findPage(page, tree);
	}



	@Transactional(readOnly = false)
	public void delete(Tree tree) {
		super.delete(tree);
	}

	/**
	 * 通过城市ID查找子库地区一段
	 * @author  ljc
	 * @version 2018-03-15
	 * @param   city 城市ID
	 * @return  子库信息一段结果集
	 */
	public List<SubLibraryCityOneSection> findCityOneSectionByCity(Integer city) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("city", city);
		return dao.findCityOneSectionByMap(map);
	}

    /**
     * 获取科目树名称集API
     * @author     ljc
     * @version    2018-5-8 16:36:05
     * @param      treeParentID        科目树父级ID
     * @return     InformationBody     Body:科目树名称/科目树ID
     */
    public InformationBody findTreeNamesByParentID(String treeParentID) {
        InformationBody informationBody = new InformationBody();
        try {
            informationBody.setBody(dao.findTreeNamesByParentID(treeParentID));//设置响应体
            informationBody.setStatusCode(0);
            informationBody.setStatusMsg("success");
            logger.debug("获取科目树名称集API,响应成功!", informationBody);
        } catch (Exception e) {
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("fail!");
            informationBody.setBody(e);
            logger.error("获取科目树名称集API,调用失败!", e);
        }
        return informationBody;
    }
}