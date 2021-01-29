package com.kang.mall.service.admin;

import com.kang.mall.common.Result;
import com.kang.mall.param.admin.CarouselParam;

/**
 * @author yikang
 * ClassName: CarouselService
 * Create Date: 2021/1/28 9:53
 */
public interface CarouselService {

    /**
     * 获取全部的轮播图信息
     *
     * @return Result
     */
    Result list();

    /**
     * 根据 ID 拿到轮播图信息
     *
     * @param id 轮播图 ID
     * @return Result
     */
    Result get(Long id);

    /**
     * 添加轮播图
     *
     * @param carouselParam 轮播图入参
     * @return Result
     */
    Result create(CarouselParam carouselParam);

    /**
     * 根据 ID 和入参更新轮播图信息
     *
     * @param id       轮播图 ID
     * @param carouselParam 轮播图参数
     * @return Result
     */
    Result update(Long id, CarouselParam carouselParam);

    /**
     * 根据 ID 删除轮播图
     *
     * @param id 轮播图 ID
     * @return Result
     */
    Result remove(Long id);

}
