package com.kang.mall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.kang.mall.entity.base.BaseTimeEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author kang
 * ClassName: Cart
 * Create Date: 2021/3/26 16:54
 */
@EqualsAndHashCode(callSuper = true)
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class Cart extends BaseTimeEntity implements Serializable {
    /**
     * 货物车主键 ID
     */
    @TableId(type = IdType.AUTO)
    private Long cartId;
    /**
     * 用户 ID
     */
    private Long userId;
    /**
     * 商品 ID
     */
    private Long goodsId;
    /**
     * 商品数量
     */
    private Integer goodsCount;

    public Cart(Long userId, Long goodsId) {
        this.userId = userId;
        this.goodsId = goodsId;
    }
}
