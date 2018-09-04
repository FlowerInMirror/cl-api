/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.api.jmat.entity.MaterialSmallSample;
import org.apache.ibatis.annotations.Param;

/**
 * 材料小样表DAO接口
 * @author ljc
 * @version 2018-07-06
 */
@MyBatisDao
public interface MaterialSmallSampleDao extends CrudDao<MaterialSmallSample> {

    // 获取 材料小样对象
    /**
     * @author ljc
     * @param  matID
     * @return 材料样品状态
     * @createTime 2018-7-6 16:59:18
     */
    MaterialSmallSample getMaterialSampleByMatID(@Param("matID") String matID);

    // 获取 材料样品状态 (子库>地区>档次>品牌项三段)
    /**
     * @author ljc
     * @param  matID
     * @return 材料样品状态
     * @createTime 2018-7-6 16:59:18
     */
    Integer getMaterialSampleStatusByMatID(@Param("matID") String matID);

}