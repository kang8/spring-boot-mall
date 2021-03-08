package com.kang.mall.service.mall.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kang.mall.entity.Carousel;
import com.kang.mall.mapper.CarouselMapper;
import com.kang.mall.service.mall.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yikang
 * ClassName: CarouselServiceImpl
 * Create Date: 2021/3/8 10:48
 */
@Service
public class CarouselServiceImpl implements CarouselService {

    @Autowired
    private CarouselMapper carouselMapper;

    @Override
    public List<Carousel> list() {
        QueryWrapper<Carousel> query = new QueryWrapper<>();
        query.select("carousel_url", "redirect_url").orderByDesc("carousel_rank");
        return carouselMapper.selectList(query);
    }
}
