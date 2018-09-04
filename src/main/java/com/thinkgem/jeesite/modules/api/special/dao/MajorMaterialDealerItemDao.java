/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.special.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.api.special.entity.MajorMaterialDealerItem;
import com.thinkgem.jeesite.modules.api.special.entity.vo.MajorMaterialDealerItemVo;

import java.util.List;

/**
 * 材料商自定义分项表DAO接口
 * @author ljc
 * @version 2018-08-23
 */
@MyBatisDao
public interface MajorMaterialDealerItemDao extends CrudDao<MajorMaterialDealerItem> {
    // "============================== CUD =============================="

    // 专项产品:添加材料商自定义(类别项),通过实体包装类 @auhtor ljc @createTime 2018-8-23 20:35:48
    int addDealerCategoryItemByEntityVo(MajorMaterialDealerItemVo majorMaterialDealerItemVo);

    // 专项产品:更新材料商自定义(类别项),通过实体包装类 @auhtor ljc @createTime 2018-8-23 20:35:51
    int updateDealerCategoryItemByEntityVo(MajorMaterialDealerItemVo majorMaterialDealerItemVo);

    // 专项产品:删除材料商自定义(类别项)API,通过实体包装类 @auhtor ljc @createTime 2018-8-23 20:35:54
    int deleteDealerCategoryItemByEntityVo(MajorMaterialDealerItemVo majorMaterialDealerItemVo);

    // 添加:材料商自定义项集(类别项ID/产品ID/名称) @author ljc @createTime 2018-8-26 17:50:01
    int addDealerCategoryItemsByEntitys(List<MajorMaterialDealerItem> majorMaterialDealerItems);

    // "============================ FIND/GET ==========================="
}