package com.thinkgem.jeesite.modules.api.special.entity.qo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 浏览专项产品:类别相关检索类
 * @author ljc
 * @createTime 2018-8-27 15:07:32
 */
@ApiModel(value = "类别相关检索类")
public class CategoryCorrelationQo implements Serializable {

    private static final long serialVersionUID = -5196721705773658397L;

    @ApiModelProperty("检索类别:1.检索不可被选中项组;2.检索组合套餐价格")
    private Integer searchType;

    @ApiModelProperty("专项产品ID")
    private String proID;

    @ApiModelProperty("类别项组")
    private List<Integer> categoryItems;

    public CategoryCorrelationQo() {
        super();
    }

    public CategoryCorrelationQo(Integer searchType, String proID, List<Integer> categoryItems) {
        this.searchType = searchType;
        this.proID = proID;
        this.categoryItems = categoryItems;
    }

    public Integer getSearchType() {
        return searchType;
    }

    public CategoryCorrelationQo setSearchType(Integer searchType) {
        this.searchType = searchType;
        return this;
    }

    public String getProID() {
        return proID;
    }

    public CategoryCorrelationQo setProID(String proID) {
        this.proID = proID;
        return this;
    }

    public List<Integer> getCategoryItems() {
        return categoryItems;
    }

    public CategoryCorrelationQo setCategoryItems(List<Integer> categoryItems) {
        this.categoryItems = categoryItems;
        return this;
    }
}
