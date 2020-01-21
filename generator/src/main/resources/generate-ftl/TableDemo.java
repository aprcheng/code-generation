package generate-ftl;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

@Data
public class TableDemo {
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

