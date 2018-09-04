package com.thinkgem.jeesite.modules.api.major.entity.po.one;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 主营管理:一段列表
 *
 * @author ljc
 * @createTime 2018-8-17 16:34:08
 */
@ApiModel(value = "主营管理:一段列表")
public class AList implements Serializable {

    private static final long serialVersionUID = -458712265654914921L;

    @ApiModelProperty("一级科目ID")
    private String treeOneID;
    @ApiModelProperty("二级科目ID")
    private String treeTwoID;
    @ApiModelProperty("一级科目编码")
    private String treeOneCode;
    @ApiModelProperty("二级科目编码")
    private String treeTwoCode;
    @ApiModelProperty("一级科目名称")
    private String treeOneName;
    @ApiModelProperty("二级科目名称")
    private String treeTwoName;
    @ApiModelProperty("主营管理回路")
    private String majorLoopStatus;

    public AList() {
        super();
    }

    public String getMajorLoopStatus() {
        return majorLoopStatus;
    }

    public AList setMajorLoopStatus(String majorLoopStatus) {
        this.majorLoopStatus = majorLoopStatus;
        return this;
    }

    public String getTreeOneID() {
        return treeOneID;
    }

    public AList setTreeOneID(String treeOneID) {
        this.treeOneID = treeOneID;
        return this;
    }

    public String getTreeTwoID() {
        return treeTwoID;
    }

    public AList setTreeTwoID(String treeTwoID) {
        this.treeTwoID = treeTwoID;
        return this;
    }

    public String getTreeOneCode() {
        return treeOneCode;
    }

    public AList setTreeOneCode(String treeOneCode) {
        this.treeOneCode = treeOneCode;
        return this;
    }

    public String getTreeTwoCode() {
        return treeTwoCode;
    }

    public AList setTreeTwoCode(String treeTwoCode) {
        this.treeTwoCode = treeTwoCode;
        return this;
    }

    public String getTreeOneName() {
        return treeOneName;
    }

    public AList setTreeOneName(String treeOneName) {
        this.treeOneName = treeOneName;
        return this;
    }

    public String getTreeTwoName() {
        return treeTwoName;
    }

    public AList setTreeTwoName(String treeTwoName) {
        this.treeTwoName = treeTwoName;
        return this;
    }
}
