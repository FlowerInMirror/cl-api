/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.api.jmat.entity.ProInfoMaterial;
import org.apache.ibatis.annotations.Param;

/**
 * 项目材料表DAO接口
 * @author ljc
 * @version 2018-07-26
 */
@MyBatisDao
public interface ProInfoMaterialDao extends CrudDao<ProInfoMaterial> {

    /**
     * 查找:项目四级科目材料使用量,通过四级科目ID与城市ID
     * @author ljc
     * @param   treeFourID  四级科目ID
     * @param   cityID      城市ID
     * @return  四级科目下项目使用量 (默认0)
     * @createTime 2018-7-26 15:50:28
     */
    int findFourTreeMatCountByTreeFourIDAndCityID(@Param("treeFourID")String treeFourID,@Param("cityID")Integer cityID);
}