/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.special.dao;

import com.thinkgem.jeesite.common.persistence.BaseDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.api.major.entity.MajorCampTypeItem;
import com.thinkgem.jeesite.modules.api.special.entity.vo.MajorCampCategorysVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 专项DAO接口
 * @author ljc
 * @version 2018-8-9 10:42:56
 */
@MyBatisDao
public interface SpecialDao extends BaseDao {

    // 添加专项产品:获取所有类别与类别项(含材料商专属类别项) @depict 根据主营二级科目ID/材料商ID检索 @author ljc @createTime 2018-8-22 14:24:43
    List<MajorCampCategorysVo> findAddCategorysByTreeTwoIDAndZXUserID(@Param("treeTwoID") String treeTwoID,@Param("zxUserID") Integer zxUserID);

    // 添加专项产品:类别项集,通过类别ID与材料商ID检索 @author ljc @createTime 2018-8-22 14:24:41
    List<MajorCampTypeItem> findCategoryItemsByCategoryIDAndZXUserID(@Param("mctId") Integer mctId,@Param("zxUserID") Integer zxUserID );

    // 浏览专项产品(浏览回显/编辑默认选中):针对专项产品获取类别相机,通过类别ID和产品ID @author ljc @createTime 2018-8-26 21:51:42
    List<MajorCampTypeItem> findCategoryItemsByCategoryIDAndProID(@Param("spcMctid") Integer spcMctid,@Param("proID") String proID);

    // 编辑专项产品(编辑默认类别项回显):针对专项产品获取类别相机,通过类别ID和产品ID @author ljc @createTime 2018-8-27 20:32:30
    List<MajorCampTypeItem> findEditCategoryItemsByCategoryIDAndProID(@Param("spcMctid")Integer mctId,@Param("proID") String proID);
}