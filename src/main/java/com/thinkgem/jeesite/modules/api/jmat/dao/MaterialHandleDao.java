/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.api.jmat.entity.MaterialHandle;
import com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.city.status.MatHandleVo;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 材料处理表DAO接口
 * @author ljc
 * @version 2018-07-26
 */
@MyBatisDao
public interface MaterialHandleDao extends CrudDao<MaterialHandle> {

    /**
     * 材料处理:添加处理()
     * @author ljc
     * @param  matHandleVo      处理记录模型
     * @return InformationBody  响应体
     * @createTime 2018-7-26 13:57:18
     */
    int saveMatHandle(MatHandleVo matHandleVo);

    /**
     * 材料处理对象,通过四级ID与城市ID检索
     * @author ljc
     * @param  treeFourID 四级ID
     * @param  city       城市ID
     * @return 材料处理对象
     * @createTime 2018-7-27 19:55:55
     */
    MaterialHandle findMaterialHandleByTreeFourIDAndCityID(@Param("treeFourID")String treeFourID,@Param("city") Integer city);

    /**
     * 处理总计相关数据,通过城市ID
     * @author  ljc
     * @param   cityID
     * @return  处理总数 handelCount/正 zCount/异 yCount/问 wCount/下架总数 xCount
     * @createTime 2018-8-4 15:53:44
     */
    Map<String,Object> findHandleDetailCountByCityID(@Param("cityID")Integer cityID);
}