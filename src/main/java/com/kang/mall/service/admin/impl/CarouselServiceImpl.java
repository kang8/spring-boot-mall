package com.kang.mall.service.admin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kang.mall.common.Constants;
import com.kang.mall.common.Result;
import com.kang.mall.entity.Carousel;
import com.kang.mall.mapper.CarouselMapper;
import com.kang.mall.param.admin.CarouselParam;
import com.kang.mall.service.admin.CarouselService;
import com.kang.mall.util.ClassUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author yikang
 * ClassName: CarouselServiceImpl
 * Create Date: 2021/1/28 10:18
 */
@Service
public class CarouselServiceImpl implements CarouselService {
    @Autowired
    private CarouselMapper carouselMapper;

    @Override
    public Result list() {
        QueryWrapper<Carousel> query = new QueryWrapper<>();
        query.eq("is_deleted", 0)
                .orderByDesc("carousel_rank")
                .select("carousel_id", "carousel_url", "redirect_url", "carousel_rank", "create_time");
        List<Carousel> carousels = carouselMapper.selectList(query);

        return carousels != null ?
                Result.ok(carousels) :
                Result.error("查询失败");
    }

    @Override
    public Result get(Long id) {
        Carousel carousel = carouselMapper.selectById(id);
        return carousel != null ?
                Result.ok(carousel) :
                Result.error("查询失败");
    }

    @Override
    public Result create(CarouselParam carouselParam) {
        Integer count = carouselMapper.selectCount(new QueryWrapper<>());
        if (count >= Constants.CAROUSEL_MAX) {
            return Result.error("轮播图数量不能超过 10 个！！！");
        }

        Carousel copyCarousel = ClassUtil.copyProperties(carouselParam, new Carousel());
        int isInsert = carouselMapper.insert(copyCarousel);
        return isInsert > 0 ?
                Result.ok("添加成功") :
                Result.error("添加失败");
    }

    @Override
    public Result remove(Long id) {
        int isDelete = carouselMapper.deleteById(id);
        return isDelete > 0 ?
                Result.ok("删除成功") :
                Result.error("删除失败");
    }

    @Override
    public Result update(Long id, CarouselParam carouselParam) {
        Carousel queryCarousel = carouselMapper.selectById(id);

        BeanUtils.copyProperties(carouselParam, queryCarousel, "createTime");

        queryCarousel.setUpdateTime(LocalDateTime.now());
        int isUpdate = carouselMapper.updateById(queryCarousel);

        return isUpdate > 0 ?
                Result.ok("更新成功") :
                Result.error("更新失败");
    }
}
