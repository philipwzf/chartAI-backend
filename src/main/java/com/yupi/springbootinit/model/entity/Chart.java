package com.yupi.springbootinit.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * Chart Table
 * @TableName chart
 */
@TableName(value ="chart")
@Data
public class Chart implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * Goal for analysis
     */
    private String goal;

    /**
     * Chart Name
     */
    private String name;

    /**
     * Data for chart
     */
    private String chartData;

    /**
     * Chart Type
     */
    private String chartType;

    /**
     * Goal for analysis
     */
    private String genChart;

    /**
     * Data for chart
     */
    private String genResult;

    /**
     * User id
     */
    private Long userId;

    /**
     * Create Time
     */
    private Date createTime;

    /**
     * Update Time
     */
    private Date updateTime;

    /**
     * Is Delete
     */
    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}