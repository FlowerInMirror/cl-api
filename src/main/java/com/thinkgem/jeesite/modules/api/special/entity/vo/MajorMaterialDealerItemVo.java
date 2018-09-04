/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.special.entity.vo;

import com.thinkgem.jeesite.modules.api.special.entity.MajorMaterialDealerItem;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 材料商自定义分项表Entity包装类
 * @author ljc
 * @version 2018-08-23
 */
public class MajorMaterialDealerItemVo extends MajorMaterialDealerItem implements Serializable {


    private static final long serialVersionUID = -1982810058723037914L;

    @ApiModelProperty("当前操作用户卡号")
    private String userNo;

    @ApiModelProperty("数据行为操作人IP")
    private String dbIP;

    public MajorMaterialDealerItemVo() {
        super();
    }



    public MajorMaterialDealerItemVo(String dbIP) {
        this.dbIP = dbIP;
    }

    public String getUserNo() {
        return userNo;
    }

    public MajorMaterialDealerItemVo setUserNo(String userNo) {
        this.userNo = userNo;
        return this;
    }

    public String getDbIP() {
        return dbIP;
    }

    public MajorMaterialDealerItemVo setDbIP(String dbIP) {
        this.dbIP = dbIP;
        return this;
    }
}