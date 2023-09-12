package com.yupi.springbootinit.model.dto.chart;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 更新请求
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@Data
public class ChartUpdateRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    private String goal;

    private String chartData;

    private String chartType;

    private String genChart;

    private String genResult;

    private Date createTime;

    private Date updateTime;
    //todo: isdelete necessary?
    private Integer isDelete;

    private static final long serialVersionUID = 1L;
}