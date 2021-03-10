package com.kang.mall.result.category;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kang.mall.pojo.BaseCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author yikang
 * ClassName: Option
 * Description: 级联选择框返回类
 * Create Date: 2021/2/2 10:56
 */
@SuppressWarnings("AlibabaCommentsMustBeJavadocFormat")
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) // 添加该注解之后，返回值为 null 时，不输出
public class Option extends BaseCategory {
    /**
     * is a JSON.
     * 该字段为 JSON 字符串
     * 为 ID、level 和 parentId 的联合
     * 如下面这个例子：
     * {
     *     "categoryId": "1",
     *     "categoryLevel": "1",
     *     "parentId": "0"
     * }
     */
    private String value;
}
