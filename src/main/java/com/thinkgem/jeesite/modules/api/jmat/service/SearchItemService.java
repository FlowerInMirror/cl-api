/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.thinkgem.jeesite.modules.api.jmat.dao.DataBehaviorDao;
import com.thinkgem.jeesite.modules.api.jmat.entity.DataBehavior;
import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.SubLibraryRecentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.api.jmat.entity.SearchItem;
import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.InformationBody;
import com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.city.platform.PlatformSearchTerm;
import com.thinkgem.jeesite.modules.api.jmat.dao.SearchItemDao;

/**
 * 材料搜索|用途Service
 * @author ljc
 * @version 2018-03-28
 */
@Service
@Transactional(readOnly = true)
public class SearchItemService extends CrudService<SearchItemDao, SearchItem> {

	public SearchItem get(String id) {
		return super.get(id);
	}
	
	public List<SearchItem> findList(SearchItem searchItem) {
		return super.findList(searchItem);
	}
	
	public Page<SearchItem> findPage(Page<SearchItem> page, SearchItem searchItem) {
		return super.findPage(page, searchItem);
	}
	

	
	@Transactional(readOnly = false)
	public void delete(SearchItem searchItem) {
		super.delete(searchItem);
	}

    /**
     * 数据访问对象
     */
    @Autowired// 数据行为表
    private DataBehaviorDao dataBehaviorDao;
	
	/**
	 * 搜索词编辑(删除|新增)
	 * @author     ljc
	 * @version    2018-03-28
	 * @param      platformSearchTerm  平台搜索词POJO
	 * @param      cardNum             操作人卡号
     * @updateTime 2018-6-15 11:09:50
	 * @return     InformationBody     响应状态信息
	 */
	public InformationBody searchTermEdit(PlatformSearchTerm platformSearchTerm,String cardNum) {
		InformationBody informationBody = new InformationBody();
		int numberOfAffectedRows = 0;// 受影响行数
		try {
			if (null!=platformSearchTerm&&0!=platformSearchTerm.getItems().size()) {
				StringBuilder stringBuilder = new StringBuilder();
				List<Map<Integer,String>> items = platformSearchTerm.getItems();
				//SELECT ''aaa'' sContent,1 sType UNION ALL SELECT ''aaaa'',1 UNION ALL SELECT ''aa'',1 UNION ALL SELECT ''aadsfs'',1
				for (Map<Integer, String> map : items) {
					Set<Entry<Integer,String>> entrySet = map.entrySet();
					for (Entry<Integer, String> entry : entrySet) {
						Integer type = entry.getKey();// 参数IP
						String content = entry.getValue();// 参数值
                        // 校验当前 内容不为null 且 不为空串再进行 UNION 拼接
                        if (StringUtils.isNotBlank(content))
						    stringBuilder.append("SELECT ''").append(content).append("'' sContent,''").append(type).append("'' sType UNION ALL ");////UNION 操作符用于合并两个或多个 SELECT 语句的结果集。
					}
				}
				String sql = StringUtils.substringBeforeLast(stringBuilder.toString(), "UNION ALL ");//子查询部分
				numberOfAffectedRows = dao.searchTermEdit(platformSearchTerm.getTreeFourID(),platformSearchTerm.getType(),sql);//搜索词编辑(删除|新增)
                logger.debug("子库：保存搜索词|用途信息。树ID："+ platformSearchTerm.getTreeFourID() +"搜索词编辑(删除|新增)接口,调用成功!受影响行数:"+numberOfAffectedRows);
                // 保存数据行为
                DataBehavior dataBehavior = new DataBehavior();
                dataBehavior.setDbOperattype(6);
                dataBehavior.setDbOperatuser(cardNum);
                dataBehavior.setDbClass(SubLibraryRecentData.M_A_TS_MAT_SEARCH);
                dataBehavior.setDbMainid(platformSearchTerm.getTreeFourID());
                dataBehavior.setDbContent("调整材料搜索词|用途");
                dataBehaviorDao.insert(dataBehavior);
            }else
                informationBody.setStatusMsg("入参异常");
		} catch (Exception e) {
			informationBody.setStatusCode(-1);
			informationBody.setStatusMsg("失败!");
			logger.error("搜索词编辑(删除|新增)接口,调用失败!", e);
		}
		return informationBody;
	}
	
}