package com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.city;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 子库地区 检索包装类
 *
 * @author ljc
 * @version 2018-5-16 16:24:08
 */
@ApiModel(value = "子库地区检索包装类")
public class SubLibraryCityQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("城市ID")
    private Integer cityID;
    @ApiModelProperty("一级科目ID")
    private String treeOneID;
    @ApiModelProperty("二级科目ID")
    private String treeTwoID;
    @ApiModelProperty("三级科目ID")
    private String treeThreeID;
    @ApiModelProperty("四级科目ID")
    private String treeFourID;
    @ApiModelProperty("材料名称(子库列表检索:1.材料名称;2品牌名称)")
    private String matName;
    @ApiModelProperty("页面类型(0.状态(默认)/1.平台/2.商城/3-5.A-C档次/6.价格/7.成本/8.应用)--检索不同结果集返回 9.二维码列表圈中材料")
    private Integer pageType = 0;
    @ApiModelProperty("档次标识(1.A档/2.B档/4.C档)")
    private Integer levelFlag;

    // TODO 子库列表分页待优化 分页 相关(加载更多)
    @ApiModelProperty("开始行数")
    private Integer beginPageNum;
    @ApiModelProperty("结束行数")
    private Integer endPageNum;

    @ApiModelProperty("材料ID集")
    private List<String> matIDs;

    @ApiModelProperty("材料名称集")
    private List<String> matNames;

    // --- 构造方法区 ---

    // 无参
    public SubLibraryCityQuery() {
        super();
    }

    // 子库地区 > 状态(二段)
    public SubLibraryCityQuery(Integer cityID, String treeFourID, Integer levelFlag) {
        this.cityID = cityID;
        this.treeFourID = treeFourID;
        this.levelFlag = levelFlag;
    }

    // 子库列表:条码打印
    public SubLibraryCityQuery(Integer cityID, String treeOneID, String treeTwoID, String treeThreeID, String treeFourID, String matName, Integer pageType, List<String> matIDs,Integer levelFlag,List<String> matNames) {
        this.cityID = cityID;
        this.treeOneID = treeOneID;
        this.treeTwoID = treeTwoID;
        this.treeThreeID = treeThreeID;
        this.treeFourID = treeFourID;
        this.matName = matName;
        this.pageType = pageType;
        this.matIDs = matIDs;
        this.levelFlag = levelFlag;
        this.matNames = matNames;
    }

    public List<String> getMatNames() {
        return matNames;
    }

    public void setMatNames(List<String> matNames) {
        this.matNames = matNames;
    }

    public List<String> getMatIDs() {
        return matIDs;
    }
    public void setMatIDs(List<String> matIDs) {
        this.matIDs = matIDs;
    }


    public Integer getBeginPageNum() {
        return beginPageNum;
    }

    public void setBeginPageNum(Integer beginPageNum) {
        this.beginPageNum = beginPageNum;
    }

    public Integer getEndPageNum() {
        return endPageNum;
    }

    public void setEndPageNum(Integer endPageNum) {
        this.endPageNum = endPageNum;
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

    public String getMatName() {
        return matName;
    }

    public void setMatName(String matName) {
        this.matName = matName;
    }

    public Integer getLevelFlag() {
        return levelFlag;
    }

    public void setLevelFlag(Integer levelFlag) {
        this.levelFlag = levelFlag;
    }

    public Integer getCityID() {
        return cityID;
    }

    public void setCityID(Integer cityID) {
        this.cityID = cityID;
    }

    public String getTreeFourID() {
        return treeFourID;
    }

    public void setTreeFourID(String treeFourID) {
        this.treeFourID = treeFourID;
    }

    /**
     * 请求对应数据,页面类型(0.状态(默认)/1.平台/2.商城/3-5.A-C档次/6.价格/7.成本/8.应用)
     */
    public Integer getPageType() {
        return pageType;
    }

    public void setPageType(Integer pageType) {
        this.pageType = pageType;
    }
}
