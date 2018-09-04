/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.api.jmat.entity.MaterialToSuppliers;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 材料商材料关联表DAO接口
 * @author ljc
 * @version 2018-05-09
 */
@MyBatisDao
public interface MaterialToSuppliersDao extends CrudDao<MaterialToSuppliers> {

    // 查找材料供货商,通过材料ID
    /**
     * @author  ljc
     * @param   matID
     * @return  List<Map<String,Object>>  材料商相关(材料商ID/材料商价格)
     * @action  子库 地区 档次 三段 品牌项 供货商相关
     * @createTime 2018-6-20 21:58:37
     */
    List<Map<String,Object>> findMatSupplierByMatID(String matID);

    /**
     * 材料商材料关联表(材料的所有供货商): 根据四级ID/城市ID,检索满足条件的材料商关联 下架
     * @author ljc
     * @param  treeFourID 四级ID
     * @param  cityID     城市ID
     * @return 受影响行数
     * @createTime 2018-7-26 21:25:00
     */
    int deleteMatToSuppliersByTreeFourIDAndCityID(@Param("treeFourID")String treeFourID,@Param("cityID") Integer cityID);
}