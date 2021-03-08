package com.kang.mall.service.mall;

import com.kang.mall.entity.Carousel;

import java.util.List;

/**
 * @author yikang
 * ClassName: CarouselService
 * Create Date: 2021/3/8 10:48
 */
public interface CarouselService {
    /**
     * 从数据库中查找全部的轮播图
     *
     * @return Result
     */
    List<Carousel> list();
}
