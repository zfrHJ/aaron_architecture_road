package com.zfr.aaron.spring.entity;

import com.zfr.aaron.spring.annountaion.Column;
import com.zfr.aaron.spring.annountaion.Table;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

@Data
@Table("t_area_import")
public class AreaImport implements Serializable {

    @Id
    private Integer id;

    /**
     * 片区信息
     */
    @Column("area_code")
    private String areaCode;

    /**
     * 状态
     */
    @Column("state")
    private Integer state;

    /**
     * 创建时间
     */
    @Column("create_time")
    private Date createTime;
    /**
     * 更新
     */
    @Column("update_time")
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
