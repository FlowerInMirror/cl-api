/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.api.jmat.dao.DataBehaviorDao;
import com.thinkgem.jeesite.modules.api.jmat.dao.TreeStandardItmeDao;
import com.thinkgem.jeesite.modules.api.jmat.entity.DataBehavior;
import com.thinkgem.jeesite.modules.api.jmat.entity.TreeStandardItme;
import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.InformationBody;
import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.SubLibraryRecentData;
import com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.city.platform.PlatformStandardInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * 材料标准信息Service
 *
 * @author ljc
 * @version 2018-03-19
 */
@Service
@Transactional(readOnly = true)
public class TreeStandardItmeService extends CrudService<TreeStandardItmeDao, TreeStandardItme> {

    public TreeStandardItme get(String id) {
        return super.get(id);
    }

    public List<TreeStandardItme> findList(TreeStandardItme treeStandardItme) {
        return super.findList(treeStandardItme);
    }

    public Page<TreeStandardItme> findPage(Page<TreeStandardItme> page, TreeStandardItme treeStandardItme) {
        return super.findPage(page, treeStandardItme);
    }



    @Transactional(readOnly = false)
    public void delete(TreeStandardItme treeStandardItme) {
        super.delete(treeStandardItme);
    }

    /**
     * 数据访问对象
     */
    @Autowired// 数据行为表
    private DataBehaviorDao dataBehaviorDao;

    /**
     * 移除材料标准信息.
     *
     * @param standardID 标准ID
     * @param cardNum    操作人卡号
     * @return InformationBody 响应信息
     * @author ljc
     * @version 2018-03-26
     * @updateTime 2018-6-14 13:43:12
     */
    public InformationBody delete(String standardID, String cardNum) {
        InformationBody informationBody = new InformationBody();
        try {
            int numberOfAffectedRows = dao.deleteTreeStandardItmeByStandardID(standardID);
            logger.debug("材料后台：删除标准项。标准ID：:" + standardID);

            // 保存数据行为
            DataBehavior dataBehavior = new DataBehavior();
            dataBehavior.setDbOperattype(6);// 操作人类型（0地方监理，1地方工程经理，2集团监理，3集团工程经理，4地方行政主管,5集团客服，6集团材料，7集团项目人事，101项目经理，102材料商，200集团主案，201地方主案）
            dataBehavior.setDbOperatuser(cardNum);// 操作人卡号
            dataBehavior.setDbClass(SubLibraryRecentData.M_A_TS_STANDARD_ITEM_SAVE);// 操作行为分类编码
            dataBehavior.setDbMainid(standardID);// 主ID（项目ID | 材料ID | 材料树ID | 或者其他）
            dataBehavior.setDbContent("删除材料（规格级）的标准项");// 行为内容
            dataBehaviorDao.insert(dataBehavior);

        } catch (Exception e) {
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("失败!");
            logger.error("移除材料标准信息接口,调用失败!", e);
        }
        return informationBody;
    }

    /**
     * 新增或更新,材料标准信息.
     *
     * @param platformStandardInfo 平台材料标准信息(新增/更新)POJO
     * @param cardNum
     * @return InformationBody       响应信息
     * @author ljc
     * @version 2018-03-27
     * @updateTime 2018-6-14 14:38:02
     */
    public InformationBody saveOrUpdate(PlatformStandardInfo platformStandardInfo, String cardNum) {
        InformationBody informationBody = new InformationBody();
        try {

            // 档次 质量标准 更新或添加 组织参数
            if (platformStandardInfo.getLevelStaFlag() == 1){
                platformStandardInfo.setTsiMatlevel(platformStandardInfo.getCurrentLevel());
                if (platformStandardInfo.getTsiId() != null){
                    TreeStandardItme treeStandardItme = dao.get(platformStandardInfo.getTsiId());
                    platformStandardInfo.setTsiName(treeStandardItme.getTsiName());// 检验项名称
                    platformStandardInfo.setTsiExteriorname(treeStandardItme.getTsiExteriorname());// 检验方法1
                    platformStandardInfo.setTsiExteriorsc(treeStandardItme.getTsiExteriorsc());// 检验方法2
                    platformStandardInfo.setTsiSpecialclaim(treeStandardItme.getTsiSpecialclaim());// 检验方法值1
                    platformStandardInfo.setTsiDetectmethod(treeStandardItme.getTsiDetectmethod());// 检验方法值2
                }
            }

            // 新增添加标识(默认1更新状态):当入参 没携带质量标准项ID(为null 或 为空串),设置 saveType 状态为 0新增状态
            int saveType = 1;

            if (StringUtils.isBlank(platformStandardInfo.getTsiId()))
                saveType = 0;
            String newTsiID = UUID.randomUUID().toString();// 材料标准信息ID(设置更新/新增-判断条件).
            if (null != platformStandardInfo && StringUtils.isBlank(platformStandardInfo.getTsiId())) {
                platformStandardInfo.setTsiId(newTsiID);
            }
            int numberOfAffectedRows = dao.saveOrUpdate(platformStandardInfo);
            //getTsiType  类型：1瑞祥标准，2质量标准，4小样标准，8包装标准（可位运算|）
            logger.debug("材料后台：保存标准项。标准：" + platformStandardInfo.getTsiType() + " 属性名称：" + platformStandardInfo.getTsiName() + "新增/更新,材料标准信息接口,调用成功!受影响行数:" + numberOfAffectedRows);

            // 保存数据行为
            DataBehavior dataBehavior = new DataBehavior();
            dataBehavior.setDbOperattype(6);
            dataBehavior.setDbOperatuser(cardNum);
            dataBehavior.setDbClass(SubLibraryRecentData.M_A_TS_STANDARD_ITEM_SAVE);
            dataBehavior.setDbMainid(platformStandardInfo.getTsiId() == null ? newTsiID : platformStandardInfo.getTsiId());
            if (saveType == 0) {
                dataBehavior.setDbContent("新增材料（规格级）的标准项");
            } else {
                dataBehavior.setDbContent("更新材料（规格级）的标准项");
            }
            dataBehaviorDao.insert(dataBehavior);

        } catch (Exception e) {
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("失败!");
            logger.error("新增/更新,材料标准信息接口,调用失败!", e);
        }
        return informationBody;
    }

}