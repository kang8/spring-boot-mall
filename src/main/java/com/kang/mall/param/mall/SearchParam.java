package com.kang.mall.param.mall;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author kang
 * ClassName: SearchParam
 * Create Date: 2021/3/22 14:44
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchParam {
    /**
     * 查询关键词
     */
    private String keyword;
    /**
     * 分类 ID
     */
    private Long categoryId;
    /**
     * 排序规则
     */
    private String sortRule;
    /**
     * 分页中每页展示的个数
     */
    private Integer size;
    /**
     * 分页中页面的当前页数
     */
    private Integer page;
}
