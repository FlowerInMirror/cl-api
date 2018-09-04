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
import com.thinkgem.jeesite.modules.api.jmat.entity.ParameterItem;
import com.thinkgem.jeesite.modules.api.jmat.dao.ParameterItemDao;

/**
 * 材料参数项Service
 * @author ljc
 * @version 2018-05-29
 */
@Service
@Transactional(readOnly = true)
public class ParameterItemService extends CrudService<ParameterItemDao, ParameterItem> {

	public ParameterItem get(String id) {
		return super.get(id);
	}
	
	public List<ParameterItem> findList(ParameterItem parameterItem) {
		return super.findList(parameterItem);
	}
	
	public Page<ParameterItem> findPage(Page<ParameterItem> page, ParameterItem parameterItem) {
		return super.findPage(page, parameterItem);
	}
	

	
	@Transactional(readOnly = false)
	public void delete(ParameterItem parameterItem) {
		super.delete(parameterItem);
	}

    /**
     * 获取全部参数项,通过材料参数ID.
     * @author  ljc
     * @version 2018-5-29 14:46:03
     * @param   paramID            参数ID
     * @return  参数项
     */
    public InformationBody getAllParameterItems(Integer paramID) {
        InformationBody informationBody = new InformationBody();
        try {
            informationBody.setBody(dao.findParamItemsByParamID(paramID));//设置响应体
            logger.debug("获取全部材料参数项API,响应成功!", informationBody);
        } catch (Exception e) {
            informationBody.setBody(e);
            informationBody.setStatusCode(1);
            informationBody.setStatusMsg("失败!");
            logger.error("获取全部材料参数项API,调用失败!", e);
        }
        return informationBody;
    }
}