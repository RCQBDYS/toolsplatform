package com.lyentech.toolsplatform.Analysis.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 672025
 * @since 2020-09-18
 */
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户账号
     */
    private String userAccount;

    /**
     * 用户密码
     */
    private String passWord;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户单位
     */
    private String userUnit;

    /**
     * 用户类型
     */
    private Integer userType;

    /**
     * 信息状态
     */
    private Integer userState;

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }
    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserUnit() {
        return userUnit;
    }

    public void setUserUnit(String userUnit) {
        this.userUnit = userUnit;
    }
    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }
    public Integer getUserState() {
        return userState;
    }

    public void setUserState(Integer userState) {
        this.userState = userState;
    }

    @Override
    protected Serializable pkVal() {
        return this.userAccount;
    }

    @Override
    public String toString() {
        return "User{" +
            "userAccount=" + userAccount +
            ", passWord=" + passWord +
            ", userName=" + userName +
            ", userUnit=" + userUnit +
            ", userType=" + userType +
            ", userState=" + userState +
        "}";
    }
}
