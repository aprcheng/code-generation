package com.chengqiang.app.model;

import com.chengqiang.code.generate.test.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@ToString
public class TableDemo extends BaseModel implements Cloneable, Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 是否激活
     */
    private Boolean active;

    /**
     * 乐观锁version
     */
    private Integer version;

    /**
     * 新增时间
     */
    private Date rawAddTime;

    /**
     * 更新时间
     */
    private Date rawUpdateTime;

}
