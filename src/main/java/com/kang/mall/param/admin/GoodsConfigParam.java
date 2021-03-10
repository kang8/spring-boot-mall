package com.kang.mall.param.admin;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author yikang
 * ClassName: GoodsConfigParam
 * Create Date: 2021/3/9 14:47
 */
@Data
public class GoodsConfigParam implements Serializable {

    @NotNull(message = "请传入商品 ID")
    @Range(message = "商品 ID 为 Long 类型")
    private Long goodsId;

    @NotNull(message = "请传入排序值")
    @Range(min = 0, max = Integer.MAX_VALUE, message = "排序值为 Int 类型")
    private Integer configRank;
}
