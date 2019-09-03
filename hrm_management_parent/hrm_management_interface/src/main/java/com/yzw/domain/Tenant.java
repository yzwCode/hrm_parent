package com.yzw.domain;

import com.baomidou.mybatisplus.enums.IdType;

import java.util.*;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author yzw
 * @since 2019-09-02
 */
@TableName("t_tenant")
public class Tenant extends Model<Tenant> {

    private static final long serialVersionUID = 1L;

    @TableField("tenant_type")
    private Long tenantType;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String companyName;
    private String companyNum;
    private Date registerTime;
    private Boolean state;
    private String address;
    private String logo;
    //租户管理员
    @TableField(exist = false)
    private Employee adminUser;
    //套餐
    @TableField(exist = false)
    private List<Meal> meals=new ArrayList<>();

    public Employee getAdminUser() {
        return adminUser;
    }

    public void setAdminUser(Employee adminUser) {
        this.adminUser = adminUser;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    public Long getTenantType() {
        return tenantType;
    }

    public void setTenantType(Long tenantType) {
        this.tenantType = tenantType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyNum() {
        return companyNum;
    }

    public void setCompanyNum(String companyNum) {
        this.companyNum = companyNum;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Tenant{" +
        ", tenantType=" + tenantType +
        ", id=" + id +
        ", companyName=" + companyName +
        ", companyNum=" + companyNum +
        ", registerTime=" + registerTime +
        ", state=" + state +
        ", address=" + address +
        ", logo=" + logo +
        "}";
    }

    public List<Map<String,Long>> getMealsMap() {
        //准备一个list
        ArrayList<Map<String,Long>> arrayMap = new ArrayList<>();
        //判断前提传入的meals是否存在
        if(meals.size()>0){
            for (Meal meal : meals) {
                Map<String, Long> map = new HashMap<>();
                //设置中间表的tenantId为当前机构的id主键
                map.put("tenantId",this.getId());
                //设置中间表的mealId为当前机构所对应的套餐id
                map.put("mealId",meal.getId());
                //将map添加到arrayMap中
                arrayMap.add(map);
            }
        }
        return arrayMap;
    }
}
