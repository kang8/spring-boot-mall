package com.kang.mall.param.admin;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author yikang
 * ClassName: CategoryParam
 * Create Date: 2021/2/1 16:59
 */
@Data
public class CategoryParam implements Serializable {

    @NotNull(message = "请传入父节点分类等级")
    @Range(max = 3, message = "父节点分类等级。只支持三级分类，请传入 0, 1, 2")
    private Byte parentLevel;

    @NotNull(message = "请传入父节点")
    @Min(value = 0,message = "父节点必须大于等于 0")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentId;

    @NotBlank(message = "请传入分类名称")
    @Size(max = 50, message = "分类名称的长度不能超过 100")
    private String categoryName;

    @NotNull(message = "请传入排序值")
    private Integer categoryRank;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;

    private Long createUser;

    private Long updateUser;
}
