/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.alliance.service;

import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.api.jmat.dao.*;
import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.InformationBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 集团联盟Service
 * @author ljc
 * @createTime 2018-7-17 15:41:59
 */
@Service
@Transactional(readOnly = true)
public class AllianceService extends BaseService {
	
	//---数据访问对象---
    @Autowired//平台-子库
    private SublibraryDao sublibraryDao;
	@Autowired//科目树
	private TreeDao treeDao;
	@Autowired//材料基础信息
	private TreeBaseInfoDao treeBaseInfoDao;
	@Autowired//科目树设置
	private TreeSstDao treeSstDao;
	@Autowired//材料地区信息
	private TreeCityInfoDao treeCityInfoDao;
	@Autowired//材料子库
	private MaterialDao materialDao;
	@Autowired//材料标准信息
	private TreeStandardItmeDao treeStandardItmeDao;
	@Autowired//属性对比（对比图）
	private MaterialComparedAttributesDao  materialComparedAttributesDao;
	@Autowired//材料标准参数
	private StandardParaDao standardParaDao;
	@Autowired// 材料参数项
    private ParameterItemDao parameterItemDao;
	@Autowired//材料搜索|用途
	private SearchItemDao searchItemDao;
	@Autowired//材料母库完善状态表 (基本信息 - 档次总计)
	private TreePerfectStateDao treePerfectStateDao;
	@Autowired//材料子库完善状态表 (规格地区保存)
	private MaterialPerfectStateDao materialPerfectStateDao;
	@Autowired//地区材料涨幅关联表(上月材料地区排行数据)
	private MatRegionalIncreaseDao matRegionalIncreaseDao;
	@Autowired//数据行为
	private DataBehaviorDao dataBehaviorDao;
	@Autowired//材料母库照片
	private TreeStandardPhotoDao treeStandardPhotoDao;
	@Autowired//分类表
	private TypeInfoDao typeInfoDao;
	@Autowired// 配送单明细 (用于成本模块使用量查询)
	private ProOrderDetailDao proOrderDetailDao;
	@Autowired// 材料商材料关联表 (供货商相关)
    private MaterialToSuppliersDao materialToSuppliersDao;
	@Autowired// 材料小样表DAO
    private MaterialSmallSampleDao materialSmallSampleDao;

    /**
     * 集团联盟:获取全部二级分类
     * @author     ljc
     * @createTime 2018-7-17 15:45:41
     */
    public InformationBody getAllTreeTwoData() {
        InformationBody informationBody = new InformationBody();
        try{
            informationBody.setBody(treeDao.getAllTreeTwoData());
        } catch (Exception e) {
            informationBody.setBody(e);
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("fail");
            logger.error("集团联盟:获取全部二级分类,接口调用失败!", e);
        }
        return informationBody;
    }

    /**
     * 集团联盟:查找二级类与城市下的所有材料
     * @author     ljc
     * @param      treeTwoIDs 二级类ID
     * @param      cityID    城市ID
     * @createTime 2018-7-17 15:45:41
     */
    public InformationBody findAllBrandByTreeTwoIDAndCityID(String treeTwoIDs, Integer cityID) {
        InformationBody informationBody = new InformationBody();
        try{
            // 安全判断 确认入参是否为空,为空退出方法,给予接口调用方提示
            if (StringUtils.isBlank(treeTwoIDs)){
                informationBody.setStatusCode(-1);
                informationBody.setStatusMsg("error:400,入参treeTwoIDs不能为空!");
                return informationBody;
            }
            informationBody.setBody(materialDao.findAllBrandByTreeTwoIDsAndCityID(Arrays.asList(treeTwoIDs.split(",")),cityID));
        } catch (Exception e) {
            informationBody.setBody(e);
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("fail");
            logger.error("集团联盟:获取全部二级分类,接口调用失败!", e);
        }
        return informationBody;
    }
}