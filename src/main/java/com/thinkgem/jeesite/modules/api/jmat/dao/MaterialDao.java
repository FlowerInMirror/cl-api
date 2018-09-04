/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.dao;

import java.util.List;
import java.util.Map;

import com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.city.MaterialBO;
import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.api.jmat.entity.Material;

/**
 * 材料子库DAO接口
 * @author ljc
 * @version 2018-03-16
 */
@MyBatisDao
public interface MaterialDao extends CrudDao<Material> {
	// "=========================== CRUD ============================="

    /**
     * @authro    ljc
     * @param     matEntity 材料实体
     * @return    int       受影响行数
     * @createTime 2018-6-22 14:35:14
     */
    // 保存材料基本信息分项（品牌|规格）（子库）
    int saveBrandItemByMaterialEntity(Material matEntity);

    // 添加品牌项(子库>地区>档次)
    /**
     * @author ljc
     * @param materialBO 材料业务更新实体业务对象
     * @return 受影响行数
     * @createTime 2018-6-26 16:16:09
     */
    int addBrandItem(MaterialBO materialBO);

    // 删除品牌项(子库>地区>档次)
    /**
     * @author  ljc
     * @param   materialBO      材料相关信息业务对象(数据更新实体)
     * @return  InformationBody 响应信息实体
     * @createTime 2018-6-27 15:39:32
     */
    int deleteBrandItem(MaterialBO materialBO);

    // 子库:入库或审核品牌项
    /**
     * @author  ljc
     * @param   materialBO      材料相关信息业务对象(数据更新实体)
     * @return  InformationBody 响应信息实体
     * @createTime 2018-6-29 11:51:10
     */
    int intoOrAuditingBrandItem(MaterialBO materialBO);

    // 更新小样类型
    /**
     * @author ljc
     * @param  materialBO
     * @return 更新首影响行数
     * @createTime 2018-7-7 20:43:08
     */
    int updateSampleType(MaterialBO materialBO);

    /**
     * 材料子库表(规格下的所有材料): 根据四级ID/城市ID技,检索所有材料指定条件下的材料 下架
     * @author ljc
     * @param  treeFourID 四级科目ID
     * @param  cityID     城市ID
     * @return 受影响行数
     * @createTime 2018-7-26 20:46:54
     */
    int deleteMatsByTreeFourIDAndCityID(@Param("treeFourID")String treeFourID,@Param("cityID") Integer cityID);


    // "========================= FIND/GET ==========================="

   /**
    * 查找所有档次总计/价高/价低...数据,通过城市ID与四级科目树ID,
    * @author  ljc
    * @version 2018-03-17
    * @param   city                      城市ID
    * @param   treeFourID                四级科目ID
    * @return  List<Map<String, Object>> 所有档次数据
    */
    public List<Map<String, Object>> findAllLevelDataByCityAndTreeID(@Param("city") Integer city, @Param("treeFourID") String treeFourID);

   /**
    * 品牌统计(子库起始页状态,材料统计),通过城市ID获得.
    * @author     ljc
    * @createTime 2018-4-9 17:12:27
    * @param      cityID
    * @return     Map<String, Object>  brand 品牌数量,Imperfect 未完善数量.
    * @updateTime 2018-8-4 15:16:29    更新:根据回路总计品牌完成状态
    */
	public Map<String, Object> findBrandStatisticsByCityID(@Param("cityID") Integer cityID);

	/**
	 * 查找城市材料排名,条件城市ID.
	 * @author      ljc
	 * @version     1.0
	 * @createTime  2018-4-10 10:15:10
	 * @param       cityID               城市ID
	 * @return      Integer              城市排名
	 */
	public Integer findCityRankingByCityID(@Param("cityID") Integer cityID);

	/**
	 * 查找城市材料成本:价高/价低/总计,通过城市ID
	 * @author      ljc
	 * @version     1.0
	 * @createTime  2018-4-10 13:22:05
	 * @param       cityID               城市ID
	 * @return      Map<String, Object>  total 总计,highPrice 价高,lowPrice 价低
	 */
	public Map<String, Object> findCostHighPriceAndLowPriceByCityID(@Param("cityID") Integer cityID);

    /**
     * 查找商城中首推材料类,通过城市ID与四级科目(材料规格)ID.
     * @author  ljc
     * @version 2018-5-20 19:47:33
     * @param   cityID               城市ID
     * @param   treeFourID           四级科目(材料规格)ID
     * @return  Material             商城中首推材料类
     */
    public Material findMallMatByCityIDAndTreeFourID(@Param("cityID") Integer cityID, @Param("treeFourID") String treeFourID);

    /**
     * 查找'完善'的材料,通过 城市ID 或 四级科目ID 或 档次类别标识
     * @author   ljc
     * @version  2018-5-21 09:51:50
     * @param    cityID               城市ID
     * @param    treeFourID           四级科目ID
     * @param    level                档次类别标识 1.A/2.B/4.C
     * @return   完善材料数量
     */
    public List<Material> findPerfectMatByCityIDOrTreeFourIDOrLevel(@Param("cityID") Integer cityID, @Param("treeFourID") String treeFourID, @Param("level") Integer level);


    /**
     * 查找 档次价格统计(=>品牌),通过城市ID与四级科目ID.
     * 子库地区 二段 价格:档次的针对品牌级别,完善材料的成本区间,以及材料商提供的材料价格区间.
     * @author ljc
     * @version 2018-5-21 14:49:38
     * @param cityID      城市ID
     * @param treeFourID  四级科目ID
     * @return 子库地区 二段 价格:档次的针对品牌级别,完善材料的成本区间,以及材料商提供的材料价格区间.
     */
    public List<Map<String,Object>> findLevelPricesByCityIDAndTreeFourID(@Param("cityID") Integer cityID, @Param("treeFourID") String treeFourID);

    /**
     * 查找 地区/规格|档次下的所有材料,By 城市ID/四级科目ID|档次flag
     * @author  ljc
     * @version 2018-5-22 14:15:30
     * @param   cityID              城市ID
     * @param   treeFourID          四级分类ID
     * @param   levelFlag           档次标识
     * @return  地区/规格|档次下的所有材料
     */
    List<Material> findAllMaterialsByCityIDAndTreeFourIDOrLevel(@Param("cityID") Integer cityID, @Param("treeFourID") String treeFourID, @Param("levelFlag") Integer levelFlag);

    /**
     * 查找 '应用'品牌数量和入驻数量,通过城市ID和四级科目ID.
     * @author  ljc
     * @version 2018-5-23 20:18:09
     * @param   cityID              城市ID
     * @param   treeFourID          四级分类ID
     * @return  '应用'品牌数量和入驻数量
     */
    List<Map<String,Object>> findAppBrandCountWithEnterByCityIDAndTreeFourID(@Param("cityID") Integer cityID, @Param("treeFourID") String treeFourID);

    // 获取材料编码,通过四级科目ID与材料编码尾部.
    /**
     * @author ljc
     * @param treeFourID   四级科目ID
     * @param matCodeTail  材料编码尾部
     * @return String      新生成的材料编码
     * 2018-6-26 16:16:12
     */
    String getNewMatCodeByTreeFourIDAndMatCodeTail(@Param("treeFourID")String treeFourID,@Param("matCodeTail") String matCodeTail);

    // 子库>地区>档次>添加 当前规格下不所有档次材料 价格相关数据
    /**
     * 当前规格下不所有档次材料 价格相关数据
     * 注: 囊括 子库更新审核表(审核状态：0待审核，1待审核)
     * @author ljc
     * @param  cityID      城市ID
     * @param  treeFourID  四级科目ID
     * @return 当前规格下不所有档次材料 价格相关数据
     * @createTime 2018-7-9 12:33:46
     */
    List<MaterialBO> findAllMaterialPriceBeInterrelatedInfoByCityIDAndTreeFourID(@Param("cityID")Integer cityID,@Param("treeFourID") String treeFourID);

    /**
     * 查找二级类与城市下的所有材料
     * 对接:集团联盟
     * @author     ljc
     * @param      treeTwoIDs 二级类ID
     * @param      cityID    城市ID
     * @createTime 2018-7-17 15:45:41
     */
    List<Map<String,Object>> findAllBrandByTreeTwoIDsAndCityID(@Param("treeTwoIDs")List<String> treeTwoIDs,@Param("cityID") Integer cityID);

    //  预操作:预下架与取消预下架(mShoppingState 类型:0取消预下架/2预下架) @author ljc @createTiem 2018-9-3 18:30:06
    int matPreOperationByMaterialBO(MaterialBO materialBO);
}