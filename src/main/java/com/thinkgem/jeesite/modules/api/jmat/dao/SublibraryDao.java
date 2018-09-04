/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.dao;

import com.thinkgem.jeesite.common.persistence.BaseDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.city.SubLibraryCityQuery;
import com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.city.platform.StorageRack;
import com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.city.status.MMSLoopVO;
import com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.list.MatLoopList;
import com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.list.MatLoopQuery;
import com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.list.QGMatLoopList;
import com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.list.QRCodePrintList;
import com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.origin.CostMaterialApplicationAmount;
import com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.city.MatBaseData;
import com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.city.SublibrariesAList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 平台-子库DAO接口
 * @author ljc
 * @version 2018-03-29
 */
@MyBatisDao
public interface SublibraryDao extends BaseDao {

    /**
     * 获取子库一段列表,通过城市ID.
     * @author     ljc
     * @version    1.0
     * @createTime 2018-5-10 11:05:29
     * @param      cityID                   城市ID
     * @return     List<SublibrariesAList>  子库一段列表结果集
     */
    public List<SublibrariesAList> findSublibrariesAListsByCityID(@Param("cityID") Integer cityID);

    /**
     * 查找"计划"材料使用量集合,通过城市ID与数据量.
     * @author                      ljc
     * @version                     1.0
     * @createTime                  2018-5-10 14:07:40
     * @param       cityID          城市ID
     * @param       topNum          数据量
     * @return      List<CostMaterialApplicationAmount> 材料使用量模型List
     */
    public List<CostMaterialApplicationAmount> findPlanMatApplicationAmountsByCityIDAndTopNum(@Param("cityID") Integer cityID, @Param("topNum") Integer topNum);

    /**
     * 查找材料基础数据,通过四级科目ID.
     * @author   ljc
     * @version  2018-5-19 18:32:30
     * @param    treeFourID           四级科目ID
     * @return   MatBaseData          材料基础信息类
     */
    public MatBaseData findMatBaseDataByTreeFourID(@Param("treeFourID") String treeFourID);

    /**
     * 材料'详细'基础数据
     * @author   ljc
     * @version  2018-5-19 18:32:30
     * @param    treeFourID           四级科目ID
     *
     * @return   MatBaseData          材料基础信息类
     */
    public MatBaseData findMatBaseDetailDataByTreeFourID(@Param("treeFourID") String treeFourID,@Param("cityID") Integer cityID);

    /**
     * 子库列表:查找二维码打印列表 通过 子库地区检索包装类
     * @author ljc
     * @param  subLibraryCityQuery 子库地区检索包装类
     * @return 二维码打印列表
     */
    List<QRCodePrintList> findQRCodePrintListBySubLibraryCityQuery(SubLibraryCityQuery subLibraryCityQuery);

    /**
     * 小样列表:子库地区平台 > 小样信息(三段弹出)
     * @author  ljc
     * @param   cityID                城市ID
     * @param   treeFourID            四级科目ID
     * @return  List<QRCodePrintList> 小样列表
     * @createTime 2018-7-20 19:21:06
     */
    List<StorageRack> findSampleListByCityIDAndTreeFourID(@Param("cityID")Integer cityID, @Param("treeFourID") String treeFourID);

    /**
     * 材料回路数据,通过城市ID与四级科目ID检索(注:调用存储过程 -- PRO_MAT_GET_MMSLoop_FindOne)
     * @author     ljc
     * @param      cityID     城市ID
     * @param      treeFourID 四级科目ID
     * @return     MMSLoopVO  材料管理系统回路包装类
     * @createTime 2018-7-24 21:46:03
     */
    MMSLoopVO matGetMMSLoopFindOne(@Param("cityID")Integer cityID,@Param("treeFourID") String treeFourID);

    /**
     * 查找子库列表:'检索'材料回路列表
     * @author     ljc
     * @param      matLoopQuery  材料回路检索包装类
     * @return     材料回路列表
     * @createTime 2018-7-31 19:42:44
     */
    List<MatLoopList> findMatLoopListsByMatLoopQuery(MatLoopQuery matLoopQuery);


    /**
     * 子库列表:获取全国材料回路列表
     * @author ljc
     * @return 全国材料回路列表
     * @createTime 2018-8-6 16:42:57
     */
    List<QGMatLoopList> getQGLoopMatList();


}