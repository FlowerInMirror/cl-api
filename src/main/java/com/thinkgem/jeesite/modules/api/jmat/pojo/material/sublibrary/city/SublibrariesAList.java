package com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.city;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;


import java.io.Serializable;
import java.util.Date;

/**
 * 子库一段列表POJO
 *
 * @author ljc
 * @createTime 2018-5-10 09:27:08
 */
@ApiModel(value = "子库一段列表模型")
public class SublibrariesAList implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty("记录行号")
    private Integer showNum;
    @ApiModelProperty("排序（根据使用次数--项目）（针对第四级）-科目樹")
    private Integer rowNum;
    @ApiModelProperty("一级科目名称")
    private String treeOneName;
    @ApiModelProperty("二级科目名称")
    private String treeTwoName;
    @ApiModelProperty("三级科目名称(材料名称)")
    private String MatName;
    @ApiModelProperty("四级科目名称(材料规格名称)")
    private String MatSpec;
    @ApiModelProperty("科目编号")
    private String matSpecCode;
    @ApiModelProperty("一级科目ID")
    private String treeOneID;
    @ApiModelProperty("二级科目ID")
    private String treeTwoID;
    @ApiModelProperty("三级科目ID")
    private String treeThreeID;
    @ApiModelProperty("四级科目ID")
    private String treeFourID;
    @ApiModelProperty("地区档次flag总和(子库完善状态)")
    private Integer mpsBaseState;
    @ApiModelProperty("母库完善状态")
    private Integer perfectState;
    @ApiModelProperty("待审核 ：-1未完善，1A档，2B档，4C档（位运算处理|） （默认-1）")
    private Integer dshLevel;
    @ApiModelProperty("已打回：-1未完善，1A档，2B档，4C档（位运算处理|） （默认-1）")
    private Integer dhLevel;
    @ApiModelProperty("价格变动个数")
    private Integer priceCount;
    @ApiModelProperty("待审核个数")
    private Integer wCCount;
    @ApiModelProperty("打回个数")
    private Integer bCount;
    @ApiModelProperty("操作时间")
    private Date operateTime;
    @ApiModelProperty("标记：1正常，2异常，3问题(项目|人员回访记录)")
    private Integer visitMark;
    @ApiModelProperty("回访时间")
    private Date visitTime;
    @ApiModelProperty("供货商个数（材料的无材料商）")
    private Integer userCount;
    @ApiModelProperty("集团-问题材料（未入库，没有价格）")
    private Integer matState;
    @ApiModelProperty("业务任务时间")
    private Date taskTime;
    @ApiModelProperty("任务下达状态：1新建，2完成")
    private Integer sendTaskState;
    @ApiModelProperty("置顶(1置顶,0未置顶)")
    private Integer topPageIndex;



    // 材料管理系统回路
    @JsonIgnore
    @ApiModelProperty("平台基础未完总数 默认:0")
    private int platformBaseNotCount;
    @JsonIgnore
    @ApiModelProperty("平台基础参数总数 默认:0")
    private int platformBaseParaCount;
    @JsonIgnore
    @ApiModelProperty("平台质量标准总数 默认:0")
    private int platformQualityStaCount;
    @JsonIgnore
    @ApiModelProperty("平台小样标准总数 默认:0")
    private int platformSampleStaCount;
    @JsonIgnore
    @ApiModelProperty("平台搜索词总数 默认:0")
    private int platformSearchCount;
    @JsonIgnore
    @ApiModelProperty("平台用途总数 默认:0")
    private int platformUseCount;
    @JsonIgnore
    @ApiModelProperty("档次对比总数 默认:0")
    private int levelContrastCount;
    @JsonIgnore
    @ApiModelProperty("档次对比图未完总数 默认:0")
    private int levelContrastNotCount;
    @JsonIgnore
    @ApiModelProperty("档次标准总数 默认:0")
    private int levelStaCount;
    @JsonIgnore
    @ApiModelProperty("档次标准未完总数 默认:0")
    private int levelStaNotCount;
    @JsonIgnore
    @ApiModelProperty("材料档次数标识(确认指定档次是否录入完全)")
    private int matLevelNmFlag;
    @JsonIgnore
    @ApiModelProperty("材料档次总数")
    private int matCount;
    @JsonIgnore
    @ApiModelProperty("材料档次未完总数")
    private int matNotCount;

    @ApiModelProperty("回路总标识1.完成(默认)/0.未完成")
    private int loopStatus;

    @ApiModelProperty("平台回路标识1.完成(默认)/0.未完成")
    private int  platformLoopStatus;

    public int getPlatformLoopStatus() {
        return platformLoopStatus;
    }

    public int getLoopStatus() {
        return loopStatus;
    }

    public SublibrariesAList() {
        super();
    }

    public int getMatLevelNmFlag() {
        return matLevelNmFlag;
    }

    public void setMatLevelNmFlag(int matLevelNmFlag) {
        this.matLevelNmFlag = matLevelNmFlag;
    }

    public int getMatCount() {
        return matCount;
    }

    public void setMatCount(int matCount) {
        this.matCount = matCount;
    }

    public int getMatNotCount() {
        return matNotCount;
    }

    public void setMatNotCount(int matNotCount) {
        this.matNotCount = matNotCount;
    }

    public void setLoopStatus(int loopStatus) {
        this.loopStatus = loopStatus;
    }

    public void setPlatformLoopStatus(int platformLoopStatus) {
        this.platformLoopStatus = platformLoopStatus;
    }

    public Integer getShowNum() {
        return showNum;
    }

    public void setShowNum(Integer showNum) {
        this.showNum = showNum;
    }

    public Integer getRowNum() {
        return rowNum;
    }

    public void setRowNum(Integer rowNum) {
        this.rowNum = rowNum;
    }

    public String getTreeOneName() {
        return treeOneName;
    }

    public void setTreeOneName(String treeOneName) {
        this.treeOneName = treeOneName;
    }

    public String getTreeTwoName() {
        return treeTwoName;
    }

    public void setTreeTwoName(String treeTwoName) {
        this.treeTwoName = treeTwoName;
    }

    public String getMatName() {
        return MatName;
    }

    public void setMatName(String matName) {
        MatName = matName;
    }

    public String getMatSpec() {
        return MatSpec;
    }

    public void setMatSpec(String matSpec) {
        MatSpec = matSpec;
    }

    public String getMatSpecCode() {
        return matSpecCode;
    }

    public void setMatSpecCode(String matSpecCode) {
        this.matSpecCode = matSpecCode;
    }

    public String getTreeOneID() {
        return treeOneID;
    }

    public void setTreeOneID(String treeOneID) {
        this.treeOneID = treeOneID;
    }

    public String getTreeTwoID() {
        return treeTwoID;
    }

    public void setTreeTwoID(String treeTwoID) {
        this.treeTwoID = treeTwoID;
    }

    public String getTreeThreeID() {
        return treeThreeID;
    }

    public void setTreeThreeID(String treeThreeID) {
        this.treeThreeID = treeThreeID;
    }

    public String getTreeFourID() {
        return treeFourID;
    }

    public void setTreeFourID(String treeFourID) {
        this.treeFourID = treeFourID;
    }

    public Integer getMpsBaseState() {
        return mpsBaseState;
    }

    public void setMpsBaseState(Integer mpsBaseState) {
        this.mpsBaseState = mpsBaseState;
    }

    public Integer getPerfectState() {
        return perfectState;
    }

    public void setPerfectState(Integer perfectState) {
        this.perfectState = perfectState;
    }

    public Integer getDshLevel() {
        return dshLevel;
    }

    public void setDshLevel(Integer dshLevel) {
        this.dshLevel = dshLevel;
    }

    public Integer getDhLevel() {
        return dhLevel;
    }

    public void setDhLevel(Integer dhLevel) {
        this.dhLevel = dhLevel;
    }

    public Integer getPriceCount() {
        return priceCount;
    }

    public void setPriceCount(Integer priceCount) {
        this.priceCount = priceCount;
    }

    public Integer getwCCount() {
        return wCCount;
    }

    public void setwCCount(Integer wCCount) {
        this.wCCount = wCCount;
    }

    public Integer getbCount() {
        return bCount;
    }

    public void setbCount(Integer bCount) {
        this.bCount = bCount;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public Integer getVisitMark() {
        return visitMark;
    }

    public void setVisitMark(Integer visitMark) {
        this.visitMark = visitMark;
    }

    public Date getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }

    public Integer getUserCount() {
        return userCount;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }

    public Integer getMatState() {
        return matState;
    }

    public void setMatState(Integer matState) {
        this.matState = matState;
    }

    public Date getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(Date taskTime) {
        this.taskTime = taskTime;
    }

    public Integer getSendTaskState() {
        return sendTaskState;
    }

    public void setSendTaskState(Integer sendTaskState) {
        this.sendTaskState = sendTaskState;
    }

    public Integer getTopPageIndex() {
        return topPageIndex;
    }

    public void setTopPageIndex(Integer topPageIndex) {
        this.topPageIndex = topPageIndex;
    }

    public int getPlatformBaseNotCount() {
        return platformBaseNotCount;
    }

    public void setPlatformBaseNotCount(int platformBaseNotCount) {
        this.platformBaseNotCount = platformBaseNotCount;
    }

    public int getPlatformBaseParaCount() {
        return platformBaseParaCount;
    }

    public void setPlatformBaseParaCount(int platformBaseParaCount) {
        this.platformBaseParaCount = platformBaseParaCount;
    }

    public int getPlatformQualityStaCount() {
        return platformQualityStaCount;
    }

    public void setPlatformQualityStaCount(int platformQualityStaCount) {
        this.platformQualityStaCount = platformQualityStaCount;
    }

    public int getPlatformSampleStaCount() {
        return platformSampleStaCount;
    }

    public void setPlatformSampleStaCount(int platformSampleStaCount) {
        this.platformSampleStaCount = platformSampleStaCount;
    }

    public int getPlatformSearchCount() {
        return platformSearchCount;
    }

    public void setPlatformSearchCount(int platformSearchCount) {
        this.platformSearchCount = platformSearchCount;
    }

    public int getPlatformUseCount() {
        return platformUseCount;
    }

    public void setPlatformUseCount(int platformUseCount) {
        this.platformUseCount = platformUseCount;
    }

    public int getLevelContrastCount() {
        return levelContrastCount;
    }

    public void setLevelContrastCount(int levelContrastCount) {
        this.levelContrastCount = levelContrastCount;
    }

    public int getLevelContrastNotCount() {
        return levelContrastNotCount;
    }

    public void setLevelContrastNotCount(int levelContrastNotCount) {
        this.levelContrastNotCount = levelContrastNotCount;
    }

    public int getLevelStaCount() {
        return levelStaCount;
    }

    public void setLevelStaCount(int levelStaCount) {
        this.levelStaCount = levelStaCount;
    }

    public int getLevelStaNotCount() {
        return levelStaNotCount;
    }

    public void setLevelStaNotCount(int levelStaNotCount) {
        this.levelStaNotCount = levelStaNotCount;
    }
}
