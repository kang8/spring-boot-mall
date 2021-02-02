package com.kang.mall.result.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yikang
 * ClassName: Value
 * Description: 级联选择框中 Value 状态维护
 * Create Date: 2021/2/2 11:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Value {
    private Long parentId;

    private Long categoryId;

    private Byte categoryLevel;
}
