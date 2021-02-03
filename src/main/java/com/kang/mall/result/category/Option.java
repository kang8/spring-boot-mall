package com.kang.mall.result.category;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author yikang
 * ClassName: Option
 * Description: 级联选择框返回类
 * Create Date: 2021/2/2 10:56
 */
@SuppressWarnings("AlibabaCommentsMustBeJavadocFormat")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) // 添加该注解之后，返回值为 null 时，不输出
public class Option implements Serializable {
    private Long categoryId;

    private String categoryName;

    private List<Option> children;
}
