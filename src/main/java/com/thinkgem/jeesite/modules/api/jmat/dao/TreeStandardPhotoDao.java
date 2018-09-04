/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.api.jmat.entity.TreeStandardPhoto;
import com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.PhotoUpload;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 材料母库照片DAO接口
 * @author ljc
 * @version 2018-04-12
 */
@MyBatisDao
public interface TreeStandardPhotoDao extends CrudDao<TreeStandardPhoto> {

    /**
     * 查找 材料'规格级'照片, 通过城市ID 与 科目树ID 或 照片类型
     * @author  ljc
     * @version 2018-5-21 10:14:00
     * @param   cityID              城市ID
     * @param   treeFourID          四级科目ID
     * @param   photoType           图片类型：1品牌LOGO，2品牌型号，3材料对比图，4产品主图，5材料资质，6小样图，7产品效果图，8品质细节图，9材料工艺；10照片展示自定义类，11材料约定
     * @return  材料照片
     */
    public List<TreeStandardPhoto> findMatPhotoByCityIDAndTreeFourOrPhotoType(@Param("cityID") Integer cityID, @Param("treeFourID") String treeFourID, @Param("photoType") Integer photoType);


    /**
     * 查找材料所有相关照片,根据材料ID.
     * @author    ljc
     * @version   2018-5-22 15:08:40
     * @param     mId  材料ID
     * @param     tspClass  分类：1母库规格，2子库材料
     * @return    查找材料'材料级别'所有相关照片
     * @updateTime 2018-6-21 16:01:39
     */
    List<TreeStandardPhoto> findMatPhotoByMatIDOrPhotoType(@Param("mId") String mId, @Param("tspClass") Integer tspClass);

    /**
     * 添加材料照片
     * @author    ljc
     * @version   2018-6-15 16:45:40
     * @param     photoUpload          图片上传实体
     * @return    响应体
     */
    int addMaterialPhotoByPhotoUpload(PhotoUpload photoUpload);

    // 更新母库照片库(更新照片路径) 更新材料照片
    /**
     * @author       ljc
     * @createTime   2018-6-16 14:08:57
     * @param        photoUpload          图片上传POJO
     * @return       InformationBody      响应状态信息
     */
    int updateTreeStandardPhotoURLByPhotoUpload(PhotoUpload photoUpload);

    // 更新材料照片参数/描述 保存照片附加信息
    /**
     * @author       ljc
     * @createTime   2018-6-16 15:32:07
     * @param        photoUpload          图片上传POJO
     * @return       InformationBody      响应状态信息
     */
    int updateMaterialPhotoOterTextByPhotoUpload(PhotoUpload photoUpload);

    //  删除材料照片  母库：删除母库照片
    /**
     * @author       ljc
     * @createTime   2018-6-22 09:43:39
     * @param        photoUpload          图片上传POJO
     * @return       InformationBody      响应状态信息
     */
    int deleteTreeStandardPhotoURLByPhotoUpload(PhotoUpload photoUpload);

    /**
     * 材料母库照片表(规格下的所有材料的所有照片): 根据四级ID/城市ID,检索满足条件的记录 下架
     * @author  ljc
     * @param   treeFourID 四级科目ID
     * @param   cityID     城市ID
     * @return  受影响行数
     * @createTime 2018-7-26 20:51:01
     */
    int deleteMatPhotosByTreeFourIDAndCityID(@Param("treeFourID")String treeFourID,@Param("cityID") Integer cityID);
}