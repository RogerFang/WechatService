package edu.whu.irlab.client.entity;

/**
 * Created by Roger on 2016/5/22.
 */
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "base_user")
public class APPUser {
    public static APPUser DEFAULTUSER = null;
    private Integer id;
    private String userCode;
    private String userName;
    private String password;
    private Integer sex;
    private String mobile;
    private String address;
    private Date loginTime;
    private String loginIP;
    private Integer enabled;
    private Integer roleID;
    private Integer deptID;
    private Date createTime;
    private String text1;
    private String text2;
    private String text3;
    private String text4;
    private String userPhoto;
    private String userSign;
    private Integer userAge;
    private String userStar;
    private String userCareer;
    private String userArea;
    private String userInviteCode;
    private String userIntegral;
    private String userType;
    private String userLevel;
    private Date userBirthDay;
    private Integer totalSign;
    private Integer pinknetCoin;
    private Integer statusProcess;
    private Integer storeID;
    private Integer productID;
    private String extPic1;
    private String extPic2;
    private String businessNo;
    private String businessPic;
    private String headPersonName;
    private String headPersonMobile;
    private String spStreet;
    private String spLat;
    private String userAgeDesc;
    private String plainPassword;

    static {
        synchronized (APPUser.class) {
            DEFAULTUSER = new APPUser();
            DEFAULTUSER.setId(0);
            DEFAULTUSER.setUserName("匿名");
            DEFAULTUSER.setAddress("隐藏地址");
            DEFAULTUSER.setSex(1);
        }
    }

    public APPUser() {
    }

    public APPUser(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // 不持久化到数据库，也不显示在Restful接口的属性.
    @Transient
    @JsonIgnore
    public String getPlainPassword() {
        return plainPassword;
    }

    public void setPlainPassword(String plainPassword) {
        this.plainPassword = plainPassword;
    }

    @Column(name = "user_code")
    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    @NotBlank
    @Column(name = "user_name")
    public String getUserName() {
        if (userName == null) {
            return "匿名";
        }
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    @Column(name = "login_time")
    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    @Column(name = "login_ip")
    public String getLoginIP() {
        return loginIP;
    }

    public void setLoginIP(String loginIP) {
        this.loginIP = loginIP;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    @Column(name = "role_id")
    public Integer getRoleID() {
        return roleID;
    }

    public void setRoleID(Integer roleID) {
        this.roleID = roleID;
    }

    @Column(name = "dept_id")
    public Integer getDeptID() {
        return deptID;
    }

    public void setDeptID(Integer deptID) {
        this.deptID = deptID;
    }

    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    public String getText3() {
        return text3;
    }

    public void setText3(String text3) {
        this.text3 = text3;
    }

    public String getText4() {
        return text4;
    }

    public void setText4(String text4) {
        this.text4 = text4;
    }

    @Column(name = "user_photo")
    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    @Column(name = "user_sign")
    public String getUserSign() {
        return userSign;
    }

    public void setUserSign(String userSign) {
        this.userSign = userSign;
    }

    @Column(name = "user_age")
    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    @Column(name = "user_star")
    public String getUserStar() {
        return userStar;
    }

    public void setUserStar(String userStar) {
        this.userStar = userStar;
    }

    @Column(name = "user_career")
    public String getUserCareer() {
        return userCareer;
    }

    public void setUserCareer(String userCareer) {
        this.userCareer = userCareer;
    }

    @Column(name = "user_area")
    public String getUserArea() {
        return userArea;
    }

    public void setUserArea(String userArea) {
        this.userArea = userArea;
    }

    @Column(name = "user_invite_code")
    public String getUserInviteCode() {
        return userInviteCode;
    }

    public void setUserInviteCode(String userInviteCode) {
        this.userInviteCode = userInviteCode;
    }

    @Column(name = "user_integral")
    public String getUserIntegral() {
        return userIntegral;
    }

    public void setUserIntegral(String userIntegral) {
        this.userIntegral = userIntegral;
    }

    @Column(name = "user_type")
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Column(name = "user_level")
    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    @Column(name = "user_birthdate")
    public Date getUserBirthDay() {
        return userBirthDay;
    }

    public void setUserBirthDay(Date userBirthDay) {
        this.userBirthDay = userBirthDay;
    }

    @Column(name = "total_sign")
    public Integer getTotalSign() {
        return totalSign;
    }

    public void setTotalSign(Integer totalSign) {
        this.totalSign = totalSign;
    }

    @Column(name = "pinknet_coin")
    public Integer getPinknetCoin() {
        return pinknetCoin;
    }

    public void setPinknetCoin(Integer pinknetCoin) {
        this.pinknetCoin = pinknetCoin;
    }

    @Column(name = "status_process")
    public Integer getStatusProcess() {
        return statusProcess;
    }

    public void setStatusProcess(Integer statusProcess) {
        this.statusProcess = statusProcess;
    }

    @Column(name = "store_id")
    public Integer getStoreID() {
        return storeID;
    }

    public void setStoreID(Integer storeID) {
        this.storeID = storeID;
    }

    @Column(name = "product_id")
    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    @Column(name = "ext_pic1")
    public String getExtPic1() {
        return extPic1;
    }

    public void setExtPic1(String extPic1) {
        this.extPic1 = extPic1;
    }

    @Column(name = "ext_pic2")
    public String getExtPic2() {
        return extPic2;
    }

    public void setExtPic2(String extPic2) {
        this.extPic2 = extPic2;
    }

    @Column(name = "business_no")
    public String getBusinessNo() {
        return businessNo;
    }

    public void setBusinessNo(String businessNo) {
        this.businessNo = businessNo;
    }

    @Column(name = "business_pic")
    public String getBusinessPic() {
        return businessPic;
    }

    public void setBusinessPic(String businessPic) {
        this.businessPic = businessPic;
    }

    @Column(name = "head_person_name")
    public String getHeadPersonName() {
        return headPersonName;
    }

    public void setHeadPersonName(String headPersonName) {
        this.headPersonName = headPersonName;
    }

    @Column(name = "head_person_mobile")
    public String getHeadPersonMobile() {
        return headPersonMobile;
    }

    public void setHeadPersonMobile(String headPersonMobile) {
        this.headPersonMobile = headPersonMobile;
    }

    @Column(name = "sp_Street")
    public String getSpStreet() {
        return spStreet;
    }

    public void setSpStreet(String spStreet) {
        this.spStreet = spStreet;
    }

    @Column(name = "sp_Lat")
    public String getSpLat() {
        return spLat;
    }

    public void setSpLat(String spLat) {
        this.spLat = spLat;
    }

    @Column(name = "user_age_desc")
    public String getUserAgeDesc() {
        return userAgeDesc;
    }

    public void setUserAgeDesc(String userAgeDesc) {
        this.userAgeDesc = userAgeDesc;
    }

    @Transient
    public Collection<String> getRoleList() {
        List<String> list = new ArrayList<String>();
        list.add("user");
        return list;
    }

    public void setRoles(String string) {
        this.roleID = Integer.parseInt(string);
    }

}
