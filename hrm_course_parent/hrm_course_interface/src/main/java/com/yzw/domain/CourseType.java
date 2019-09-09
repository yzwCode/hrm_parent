package com.yzw.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程目录
 * </p>
 *
 * @author yzw
 * @since 2019-08-31
 */
@TableName("t_course_type")
public class CourseType extends Model<CourseType> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GTM-8")
    @DateTimeFormat(pattern = "yyyy-MM-dd" )
    private Long createTime;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GTM-8")
    @DateTimeFormat(pattern = "yyyy-MM-dd" )
    private Long updateTime;
    /**
     * 类型名
     */
    private String name;
    /**
     * 父ID
     */
    private Long pid;
    //表示该字段在数据库不存在
    @TableField(exist = false)
    private CourseType parent;
    /**
     * 图标
     */
    private String logo;
    /**
     * 描述
     */
    private String description;
    private Integer sortIndex;
    /**
     * 路径
     */
    private String path;
    /**
     * 商品数量
     */
    private Integer totalCount;

    /**
     * children树
     * @return
     */
    @TableField(exist = false)
    private List<CourseType> children=new ArrayList<>();

    public List<CourseType> getChildren() {
        return children;
    }

    public void setChildren(List<CourseType> children) {
        this.children = children;
    }

    public CourseType getParent() {
        return parent;
    }

    public void setParent(CourseType parent) {
        this.parent = parent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(Integer sortIndex) {
        this.sortIndex = sortIndex;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "CourseType{" +
        ", id=" + id +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", name=" + name +
        ", pid=" + pid +
        ", logo=" + logo +
        ", description=" + description +
        ", sortIndex=" + sortIndex +
        ", path=" + path +
        ", totalCount=" + totalCount +
        "}";
    }
}
