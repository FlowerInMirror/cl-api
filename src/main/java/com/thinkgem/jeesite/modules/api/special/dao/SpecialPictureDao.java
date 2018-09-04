/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.special.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.api.special.entity.SpecialPicture;
import com.thinkgem.jeesite.modules.api.special.entity.vo.SpecialPictureVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 专项图片表DAO接口
 * @author ljc
 * @version 2018-08-17
 */
@MyBatisDao
public interface SpecialPictureDao extends CrudDao<SpecialPicture> {

    // "=========================C U D=============================="

    // 添加-专项相关照片 @author ljc @Depict 添加内容:专项产品ID/专项图片类型:1主图/图片地址 @createTime 2018-8-20 11:34:53
    int addSpecialPictureByEntityVo(SpecialPictureVo specialPictureVo);

    // 添加-专项产品主图集(专项产品ID/图片类型/图片地址) @author ljc @createTime 2018-8-26 17:41:15
    int addSpecialPicturesByEntitys(List<SpecialPicture> specialPictures);

    // 更新-专项产品照片 @author ljc @createTime 2018-8-20 11:42:27
    int updateSpecialPictureByEntityVo(SpecialPictureVo specialPictureVo);

    // 删除-专项产品照片 @author ljc @createTime 2018-8-20 11:42:39
    int deleteSpecialPictureByEntityVo(SpecialPictureVo specialPictureVo);

    // "=========================FIND=============================="

    // 专项图片图集,通过产品ID以及类型检索 @author ljc @createTime 2018-8-26 21:27:56
    List<SpecialPicture> findSpecialPicturesByProIDAndType(@Param("proID") String proID,@Param("type") Integer type);
}