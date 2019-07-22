package com.zfr.aaron.spring.entity;

import com.zfr.aaron.spring.annountaion.Column;
import com.zfr.aaron.spring.annountaion.Table;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@Table("t_area_import")
public class AreaImport {

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
}
