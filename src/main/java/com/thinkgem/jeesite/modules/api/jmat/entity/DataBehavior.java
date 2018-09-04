/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 数据行为Entity
 *
 * @author ljc
 * @version 2018-04-09
 */
public class DataBehavior extends DataEntity<DataBehavior> {

    private static final long serialVersionUID = 1L;
    private Integer dbId;        // ID
    private Integer dbClass;        // 行为分类
    private Integer dbOperattype = 6;        // 操作人类型（0地方监理，1地方工程经理，2集团监理，3集团工程经理，4地方行政主管,5集团客服，6集团材料(默认)，7集团项目人事，101项目经理，102材料商）
    private String dbOperatuser;        // 操作人
    private String dbMainid;        // 主ID（项目ID | 材料ID | 材料树ID | 或者其他）
    private String dbEventid;        // 指定事件ID
    private String dbPosition;        // 定位
    private String dbContent;        // 行为内容
    private String dbData;        // 数据内容：Json 数据
    private Integer dbState = 1;        // 状态：1成功(默认)，2失败
    private Integer dbIfread;        // 是否已阅：1已阅  （默认0）
    private Date dbReadtime;        // 已阅时间
    private Integer dbJincheng;        // 进程：0正常、1删除  （默认0）
    private Date dbCreatetime;        // 创建时间    （默认getdate()）
    private Date dbUpdatetime;        // 更新时间
    private Integer dbOperatecityid = 0;        // 地区（操作人地区）--数据库作业每天填充  （默认0）
    private Integer dbCityid = 0;        // 地区（事件地区）--数据库作业每天填充  （默认0）

    private String dbIP; // 数据行为操作人IP


    /**
     * 操作人卡号/操作人类型/行为分类ID/主ID/行为内容
     *
     * @param dbOperatuser 操作人卡号
     * @param dbOperattype 操作人类型（0地方监理，1地方工程经理，2集团监理，3集团工程经理，4地方行政主管,5集团客服，6集团材料，7集团项目人事，101项目经理，102材料商）
     * @param dbClass      行为分类
     * @param dbMainid     主ID（项目ID | 材料ID | 材料树ID | 或者其他） 根据主ID设值行为内容
     * @param dbContent    行为内容
     * @author ljc
     * @action 保存数据行为
     * @createTime 2018-6-29 13:45:27
     */
    public DataBehavior(String dbOperatuser, Integer dbOperattype, Integer dbClass, String dbMainid, String dbContent) {
        this.dbOperatuser = dbOperatuser;
        this.dbOperattype = dbOperattype;
        this.dbClass = dbClass;
        this.dbMainid = dbMainid;
        this.dbContent = dbContent;
    }

    // 有参:操作人卡号/操作人类型/行为类型/主ID/事件ID/行为内容/操作用户IP
    public DataBehavior(String dbOperatuser, Integer dbOperattype, Integer dbClass, String dbMainid, String dbEventid, String dbContent, String dbIP) {
        this.dbOperatuser = dbOperatuser;
        this.dbOperattype = dbOperattype;
        this.dbClass = dbClass;
        this.dbMainid = dbMainid;

        this.dbEventid = dbEventid;
        this.dbContent = dbContent;
        this.dbIP = dbIP;
    }

    public String getDbIP() {
        return dbIP;
    }

    public DataBehavior setDbIP(String dbIP) {
        this.dbIP = dbIP;
        return this;
    }

    public DataBehavior() {
        super();
    }


    public DataBehavior(String id) {
        super(id);
    }

    @NotNull(message = "ID不能为空")
    public Integer getDbId() {
        return dbId;
    }

    public void setDbId(Integer dbId) {
        this.dbId = dbId;
    }

    public Integer getDbClass() {
        return dbClass;
    }

    public void setDbClass(Integer dbClass) {
        this.dbClass = dbClass;
    }

    public Integer getDbOperattype() {
        return dbOperattype;
    }

    public void setDbOperattype(Integer dbOperattype) {
        this.dbOperattype = dbOperattype;
    }

    @Length(min = 0, max = 50, message = "操作人长度必须介于 0 和 50 之间")
    public String getDbOperatuser() {
        return dbOperatuser;
    }

    public void setDbOperatuser(String dbOperatuser) {
        this.dbOperatuser = dbOperatuser;
    }

    @Length(min = 0, max = 50, message = "主ID（项目ID | 材料ID | 材料树ID | 或者其他）长度必须介于 0 和 50 之间")
    public String getDbMainid() {
        return dbMainid;
    }

    public void setDbMainid(String dbMainid) {
        this.dbMainid = dbMainid;
    }

    @Length(min = 0, max = 50, message = "指定事件ID长度必须介于 0 和 50 之间")
    public String getDbEventid() {
        return dbEventid;
    }

    public void setDbEventid(String dbEventid) {
        this.dbEventid = dbEventid;
    }

    @Length(min = 0, max = 255, message = "定位长度必须介于 0 和 255 之间")
    public String getDbPosition() {
        return dbPosition;
    }

    public void setDbPosition(String dbPosition) {
        this.dbPosition = dbPosition;
    }

    @Length(min = 0, max = 2000, message = "行为内容长度必须介于 0 和 2000 之间")
    public String getDbContent() {
        return dbContent;
    }

    public void setDbContent(String dbContent) {
        this.dbContent = dbContent;
    }

    @Length(min = 0, max = -1, message = "数据内容：Json 数据长度必须介于 0 和 -1 之间")
    public String getDbData() {
        return dbData;
    }

    public void setDbData(String dbData) {
        this.dbData = dbData;
    }

    public Integer getDbState() {
        return dbState;
    }

    public void setDbState(Integer dbState) {
        this.dbState = dbState;
    }

    public Integer getDbIfread() {
        return dbIfread;
    }

    public void setDbIfread(Integer dbIfread) {
        this.dbIfread = dbIfread;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getDbReadtime() {
        return dbReadtime;
    }

    public void setDbReadtime(Date dbReadtime) {
        this.dbReadtime = dbReadtime;
    }

    public Integer getDbJincheng() {
        return dbJincheng;
    }

    public void setDbJincheng(Integer dbJincheng) {
        this.dbJincheng = dbJincheng;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getDbCreatetime() {
        return dbCreatetime;
    }

    public void setDbCreatetime(Date dbCreatetime) {
        this.dbCreatetime = dbCreatetime;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getDbUpdatetime() {
        return dbUpdatetime;
    }

    public void setDbUpdatetime(Date dbUpdatetime) {
        this.dbUpdatetime = dbUpdatetime;
    }

    public Integer getDbOperatecityid() {
        return dbOperatecityid;
    }

    public void setDbOperatecityid(Integer dbOperatecityid) {
        this.dbOperatecityid = dbOperatecityid;
    }

    public Integer getDbCityid() {
        return dbCityid;
    }

    public void setDbCityid(Integer dbCityid) {
        this.dbCityid = dbCityid;
    }

    // ========== 附加属性
    private String classCode;// 分类Code（已此做查询条件）

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }
}