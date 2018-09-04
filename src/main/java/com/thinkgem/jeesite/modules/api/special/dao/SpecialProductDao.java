/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.special.dao;

import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.api.special.entity.SpecialProduct;
import com.thinkgem.jeesite.modules.api.special.entity.bo.SpecialProductBo;
import com.thinkgem.jeesite.modules.api.special.entity.vo.SpecialProductVo;

import java.util.List;
import java.util.Map;

/**
 * 专项产品DAO接口
 * @author ljc
 * @version 2018-08-08
 */
@MyBatisDao
public interface SpecialProductDao  {

    // "=========================C U D=============================="

    // 添加:专项产品基础信息(材料商ID/材料商城市/所属主营二级科目ID/产品名称/单位/产品详细) @author ljc @createTime 2018-8-26 17:25:45
    int addSpecialProductByEntity(SpecialProduct specialProduct);

    // 专项:添加产品(ID/主营二级科目ID/材料商ID/名称/单位/详情描述)
    /**
     * @param      specialProduct 专项产品包装类
     * @return     int 受影响行数
     * @createTime 2018-8-8 16:47:23
     */
    int addSProBySpecialProductVo(SpecialProduct specialProduct);

    // 专项产品更新(产品字段更新/产品详情修改/产品上架/下架)
    /**
     * @author ljc
     * @param  specialProductBo 专项产品包装类
     * @return int              受影响行数
     * @createTime 2018-8-9 15:27:52
     */
    int updateSProBySpecialProductBo(SpecialProductBo specialProductBo);

    // 专项产品删除
    /**
     * @author ljc
     * @param  specialProductBo 专项产品包装类
     * @return int              受影响行数
     * @createTime 2018-8-9 15:27:52
     */
    int deleteSProBySpecialProductBo(SpecialProductBo specialProductBo);

    // "=========================GET FIND=============================="
    /**
     * @author     ljc
     * @param      specialProduct 专项产品是实体
     * @createTime 2018-8-26 19:29:41
     */
    // 专项产品集(通过实体包装类查找)
    List<Map<String,Object>> findSpecialProductALLByEntity(SpecialProduct specialProduct);

    // 专项产品(通过ID查找)
    /**
     * @author     ljc
     * @param      specialProduct 专项产品是实体
     * @createTime 2018-8-9 10:50:47
     */
    SpecialProductVo findSpecialProductOneByEntity(SpecialProduct specialProduct);



}