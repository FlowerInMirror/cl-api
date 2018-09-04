package com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.city.platform;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * 平台标准参数(新增/更新)
 * 
 * @author  ljc
 * @version 2018-03-26
 * @action  入参适配数据载体
 */
@ApiModel(value = "平台标准参数(新增/更新)")
public class PlatformStandardParameters implements Serializable {

	private static final long serialVersionUID = 1L;

    @ApiModelProperty("参数ID/参数值")
    private List<Map<String, String>> paras = new ArrayList<Map<String,String>>() ;

    @ApiModelProperty("四级科目ID")
	private String treeFourID;

    @ApiModelProperty("用户卡号")
    private String cradNum;

    public String getCradNum() {
        return cradNum;
    }

    public void setCradNum(String cradNum) {
        this.cradNum = cradNum;
    }

    public List<Map<String, String>> getParas() {
        return paras;
    }

    public void setParas(List<Map<String, String>> paras) {
        this.paras = paras;
    }

    public String getTreeFourID() {
        return treeFourID;
    }

    public void setTreeFourID(String treeFourID) {
        this.treeFourID = treeFourID;
    }
}
