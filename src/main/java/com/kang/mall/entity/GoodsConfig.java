package com.kang.mall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.kang.mall.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author yikang
 * ClassName: GoodsConfigResult
 * Create Date: 2021/3/8 19:27
 */
@EqualsAndHashCode(callSuper = true)
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GoodsConfig extends BaseEntity implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long configId;
    /**
     * 配置类型
     */
    private Byte configType;
    /**
     * 商品 ID
     */
    private Long goodsId;
    /**
     * 配置排序值
     */
    private Integer configRank;
    /**
     * 是否删除
     */
    @TableLogic(value = "0", delval = "1")
    private Byte isDeleted;
}
