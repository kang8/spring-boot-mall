package com.kang.mall.service.admin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kang.mall.common.Result;
import com.kang.mall.param.admin.CategoryParam;

/**
 * @author yikang
 * ClassName: CategoryService
 * Create Date: 2021/2/1 16:37
 */
public interface CategoryService {


    /**
     * 获取商品分类信息
     *
     * @return Result
     */
    Result list() ;

    /**
     * 根据商品分类 Id 获取商品分类信息
     *
     * @param id 商品分类 ID
     * @return Result
     */
    Result get(Long id);

    /**
     * 创建商品分类信息
     *
     * @param categoryParam 商品分类参数
     * @return Result
     */
    Result create(CategoryParam categoryParam);

    /**
     * 更新商品分类信息
     *
     * @param id            商品分类 ID
     * @param categoryParam 商品分类参数
     * @return Result
     */
    Result update(Long id, CategoryParam categoryParam);

    /**
     * 删除商品分类信息
     *
     * @param id 商品分类 ID
     * @return Result
     */
    Result remove(Long id);

    /**
     * 得到分类表中的全部父级，用于分类管理中的表单选择
     * 并自己加上不存在的根节点
     *
     * @return Result
     * @throws JsonProcessingException  json 解析异常
     */
    Result optionOfParent() throws JsonProcessingException;

    /**
     * 获取全部分类，用于商品管理中的表单选择
     *
     * @return Result
     */
    Result option();
}
