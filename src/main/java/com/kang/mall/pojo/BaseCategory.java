package com.kang.mall.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author yikang
 * ClassName: BaseCategory
 * Description: category 的基类
 * Create Date: 2021/2/7 21:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseCategory implements Serializable {
    @TableId(type = IdType.AUTO)
    protected Long categoryId;
    /**
     * 排序等级
     */
    protected Byte categoryLevel;
    /**
     * 父节点
     */
    protected Long parentId;
    /**
     * 分类名称
     */
    protected String categoryName;

    @TableField(exist = false)
    protected List<BaseCategory> children;
}
