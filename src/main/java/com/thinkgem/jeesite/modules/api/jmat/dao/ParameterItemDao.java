/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.api.jmat.entity.ParameterItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 材料参数项DAO接口
 * @author ljc
 * @version 2018-05-29
 */
@MyBatisDao
public interface ParameterItemDao extends CrudDao<ParameterItem> {

    /**
     * 获取全部参数项,通过材料参数ID.
     * @author  ljc
     * @version 2018-5-29 14:46:03
     * @param   paramID            参数ID
     * @return  参数项
     */
    List<Map<String,Object>> findParamItemsByParamID(@Param("paramID") Integer paramID);
}