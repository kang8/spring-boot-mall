package com.kang.mall.param.admin;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author yikang
 * ClassName: GoodsParam
 * Create Date: 2021/2/26 20:25
 */
@Data
public class GoodsParam implements Serializable {
    @NotBlank(message = "请输入商品名称")
    @Size(max = 200, message = "商品名称不能超过 200 个字符")
    private String goodsName;

    @NotBlank(message = "请输入商品简介")
    @Size(max = 200, message = "商品简介不能超过 200 个字符")
    private String goodsIntroduce;

    @NotNull(message = "请传入分类 ID")
    @Range(min = 1L, message = "分类 ID 的范围为 Long类型，且不能为 0")
    private Long categoryId;

    @NotBlank(message = "请上传商品主图")
    @Size(max = 200, message = "商品主图文件名太长，请缩短再上传啊")
    private String goodsCoverImage;

    //@NotBlank(message = "请上传商品轮播图")
    @Size(max = 500, message = "商品轮播图文件名太长，请缩短再上传啊")
    private String goodsCarousel;

    @NotBlank(message = "请输入商品详情")
    private String goodsDetailContent;

    @NotNull(message = "请输入商品价格")
    @Range(min = 1, message = "商品价格不能为负数")
    @Digits(integer = 19, fraction = 2, message = "商品的价格只能有两位小数")
    private BigDecimal originalPrice;

    @NotNull(message = "请输入商品实际价格")
    @Range(min = 0, message = "商品实际价格不能为负数")
    @Digits(integer = 19, fraction = 2, message = "商品实际的价格只能有两位小数")
    private BigDecimal sellingPrice;

    @NotNull(message = "请输入商品库存")
    @Range(min = 0, max = Integer.MAX_VALUE, message = "商品库存的范围为 int，且不能为负数")
    private Integer stockNum;

    @NotBlank(message = "请输入商品标签")
    @Size(max = 20, message = "商品标签不能超过 20 个字符")
    private String tag;

    @NotNull(message = "请输入商品状态")
    @Range(min = 0, max = 1, message = "商品状态只有 0 和 1 两种状态")
    private Byte goodsSellStatus;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;
}
