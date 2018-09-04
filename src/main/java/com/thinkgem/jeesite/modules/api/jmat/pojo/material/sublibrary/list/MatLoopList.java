package com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.list;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Date;

/**
 * 材料回路列表
 * @author ljc
 * @createTime 2018-7-31 18:58:41
 */
public class MatLoopList implements Serializable {

    private static final long serialVersionUID = -2409400186174362261L;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String treeFourID;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer rowNum;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String treeOneName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected String treeTwoName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String matName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String matSpec;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer visitMark;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date visitTime;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String visitContent;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer loopStatus;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer platformLoopStatus;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer aBrandNotCount;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer bBrandNotCount;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer cBrandNotCount;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer tciFlag; // 圈中相关

    public MatLoopList() {
        super();
    }

    public Integer getaBrandNotCount() {
        return aBrandNotCount;
    }

    public void setaBrandNotCount(Integer aBrandNotCount) {
        this.aBrandNotCount = aBrandNotCount;
    }

    public Integer getbBrandNotCount() {
        return bBrandNotCount;
    }

    public void setbBrandNotCount(Integer bBrandNotCount) {
        this.bBrandNotCount = bBrandNotCount;
    }

    public Integer getcBrandNotCount() {
        return cBrandNotCount;
    }

    public void setcBrandNotCount(Integer cBrandNotCount) {
        this.cBrandNotCount = cBrandNotCount;
    }

    public Integer getTciFlag() {
        return tciFlag;
    }

    public void setTciFlag(Integer tciFlag) {
        this.tciFlag = tciFlag;
    }

    public String getVisitContent() {
        return visitContent;
    }

    public void setVisitContent(String visitContent) {
        this.visitContent = visitContent;
    }

    public String getTreeFourID() {
        return treeFourID;
    }

    public void setTreeFourID(String treeFourID) {
        this.treeFourID = treeFourID;
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
        return matName;
    }

    public void setMatName(String matName) {
        this.matName = matName;
    }

    public String getMatSpec() {
        return matSpec;
    }

    public void setMatSpec(String matSpec) {
        this.matSpec = matSpec;
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

    public Integer getLoopStatus() {
        return loopStatus;
    }

    public void setLoopStatus(Integer loopStatus) {
        this.loopStatus = loopStatus;
    }

    public Integer getPlatformLoopStatus() {
        return platformLoopStatus;
    }

    public void setPlatformLoopStatus(Integer platformLoopStatus) {
        this.platformLoopStatus = platformLoopStatus;
    }



}
