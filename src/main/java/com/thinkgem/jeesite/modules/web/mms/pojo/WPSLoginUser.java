package com.thinkgem.jeesite.modules.web.mms.pojo;

import java.io.Serializable;

/**
 * 人事接口返回JSON序列化实体
 * @author     ljc
 * @version    1.0
 * @createTime 2018-4-25 18:13:28
 * http://api.p.rx/api/price/GetUserInfoJson?card=01010314&password=YJXR45fe6312eda27cd5ac19ddee510f4ed1&GM=1433
 * [{"dq_name":"集团","xinming":"马小二","bumenId":1995,"bumenName":"工程业务","zhiwuId":387,"zhiwuName":"经理","juese":818,"juesename":"材料经理","touxiang":"http://up.rs.rx/UploadFile/rs/File_dd33c299771d44548935d5682211df1e.png","u_diqu":39,"flag":0}]
 */
public class WPSLoginUser implements Serializable{

    private static final long serialVersionUID = 9143370048748273241L;

    private String dq_name;//地区名称
    private String xinming;//姓名
    private Integer bumenId;//部门ID
    private String bumenName;//部门名称
    private Integer zhiwuId;//职务ID
    private String zhiwuName;//职务名称
    private Integer juese;//角色ID
    private String  juesename;//角色名称
    private String  touxiang;//头像URL地址
    private String u_diqu;//地区ID
    private Integer flag;//标识

    private String ciphertext; // 加密密码

    public String getCiphertext() {
        return ciphertext;
    }

    public void setCiphertext(String ciphertext) {
        this.ciphertext = ciphertext;
    }

    public WPSLoginUser() {
        super();
    }

    public WPSLoginUser(String xinming, String bumenName,  Integer juese, String cardNumber, String password,String u_diqu) {
        this.xinming = xinming;
        this.bumenName = bumenName;
        this.juese = juese;
        this.cardNumber = cardNumber;
        this.password = password;
        this.u_diqu = u_diqu;
    }

    public void setDq_name(String dq_name) {
        this.dq_name = dq_name;
    }

    public void setXinming(String xinming) {
        this.xinming = xinming;
    }

    public void setBumenId(Integer bumenId) {
        this.bumenId = bumenId;
    }

    public void setBumenName(String bumenName) {
        this.bumenName = bumenName;
    }

    public void setZhiwuId(Integer zhiwuId) {
        this.zhiwuId = zhiwuId;
    }

    public void setZhiwuName(String zhiwuName) {
        this.zhiwuName = zhiwuName;
    }

    public void setJuese(Integer juese) {
        this.juese = juese;
    }

    public void setJuesename(String juesename) {
        this.juesename = juesename;
    }

    public void setTouxiang(String touxiang) {
        this.touxiang = touxiang;
    }

    public void setU_diqu(String u_diqu) {
        this.u_diqu = u_diqu;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getDq_name() {

        return dq_name;
    }

    public String getXinming() {
        return xinming;
    }

    public Integer getBumenId() {
        return bumenId;
    }

    public String getBumenName() {
        return bumenName;
    }

    public Integer getZhiwuId() {
        return zhiwuId;
    }

    public String getZhiwuName() {
        return zhiwuName;
    }

    public Integer getJuese() {
        return juese;
    }

    public String getJuesename() {
        return juesename;
    }

    public String getTouxiang() {
        return touxiang;
    }

    public String getU_diqu() {
        return u_diqu;
    }

    public Integer getFlag() {
        return flag;
    }

    //--- 附加属性 ---
    private String cardNumber;// 用户卡号
    private String password;// 用户密码

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardNumber() {

        return cardNumber;
    }
}
