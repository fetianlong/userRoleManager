package com.yuedi.entity;

import java.util.Date;

public class UserInfo2c {
    private Long id;

    private String username;

    private String fullname;

    private String email;

    private Integer state;

    private String pwd;

    private String salt;

    private Integer usertype;

    private Boolean isdeleteflag;

    private Long createrid;

    private String creatername;

    private Date createdatetime;

    private Date birthday;

    private Long customerinfoId;

    private String studentNo;

    private String tel;

    private String img;

    private String nickname;

    private Integer province;

    private Integer city;

    private Integer area;

    private String address;

    private Integer sex;

    private Integer hospid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname == null ? null : fullname.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public Integer getUsertype() {
        return usertype;
    }

    public void setUsertype(Integer usertype) {
        this.usertype = usertype;
    }

    public Boolean getIsdeleteflag() {
        return isdeleteflag;
    }

    public void setIsdeleteflag(Boolean isdeleteflag) {
        this.isdeleteflag = isdeleteflag;
    }

    public Long getCreaterid() {
        return createrid;
    }

    public void setCreaterid(Long createrid) {
        this.createrid = createrid;
    }

    public String getCreatername() {
        return creatername;
    }

    public void setCreatername(String creatername) {
        this.creatername = creatername == null ? null : creatername.trim();
    }

    public Date getCreatedatetime() {
        return createdatetime;
    }

    public void setCreatedatetime(Date createdatetime) {
        this.createdatetime = createdatetime;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Long getCustomerinfoId() {
        return customerinfoId;
    }

    public void setCustomerinfoId(Long customerinfoId) {
        this.customerinfoId = customerinfoId;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo == null ? null : studentNo.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public Integer getProvince() {
        return province;
    }

    public void setProvince(Integer province) {
        this.province = province;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getHospid() {
        return hospid;
    }

    public void setHospid(Integer hospid) {
        this.hospid = hospid;
    }
}