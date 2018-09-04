/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.api.jmat.entity.Infocheckandaccept;

/**
 * 信息审核验收表DAO接口
 * @author ljc
 * @version 2018-06-16
 */
@MyBatisDao
public interface InfocheckandacceptDao extends CrudDao<Infocheckandaccept> {

    // 子库：重置审核验收状态未重新开始（审核过的重置）
    /**
     * @author ljc
     * @param  infoCheckAndAccept    更新实体
     * @return int                   受影响行数
     * @createTime 2018-6-16 12:17:11
     */
    int updateInfoCheckAndAcceptStateToReset(Infocheckandaccept infoCheckAndAccept);


    // 子库：新加品牌重新开始（审核重置）
    /**
     * @author ljc
     * @param infoCheckAndAccept 更新审核项实体
     * @return int               受影响行数
     * @createTime 2018-6-26 19:07:41
     */
    int updateInfoCheckToResetToAddNewBrand(Infocheckandaccept infoCheckAndAccept);

    // 子库(品牌入库)：重置材料验收状态为重新开始（审核过的重置）
    /**
     * @author ljc
     * @action 子库地区:品牌入库
     * @param  matID 材料ID
     * @return int   搜影响行数
     * @createTime 2018-6-29 13:25:20
     */
    int updateInfoCheckAndAcceptStateToResetMat(Infocheckandaccept matID);
}