package com.kang.mall.service.admin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kang.mall.common.Constants;
import com.kang.mall.common.Result;
import com.kang.mall.entity.Carousel;
import com.kang.mall.mapper.CarouselMapper;
import com.kang.mall.param.admin.CarouselParam;
import com.kang.mall.service.admin.CarouselService;
import com.kang.mall.util.ClassUtils;
import com.kang.mall.util.CommonUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author yikang
 * ClassName: CarouselServiceImpl
 * Create Date: 2021/1/28 10:18
 */
@Service("CarouselAdminService")
public class CarouselServiceImpl implements CarouselService {

    @Autowired
    private CarouselMapper carouselMapper;

    @Autowired
    private HttpSession session;

    @Override
    public Result list() {
        QueryWrapper<Carousel> query = new QueryWrapper<>();

        query.orderByDesc("carousel_rank")
                .select("carousel_id", getCarouselUrl(), "redirect_url", "carousel_rank", "create_time");
        List<Carousel> carousels = carouselMapper.selectList(query);

        return carousels != null ?
                Result.ok(carousels) :
                Result.error("查询失败");
    }

    private String getCarouselUrl() {
        return CommonUtils.queryUrl(Constants.TABLE_FIELD_CAROUSEL_URL, Constants.ENTITY_FIELD_CAROUSEL_URL);
    }


    @Override
    public Result get(Long id) {
        Carousel carousel = carouselMapper.selectById(id);
        return carousel != null ?
                Result.ok("查询成功", carousel) :
                Result.error("查询失败");
    }

    @Override
    public Result create(CarouselParam carouselParam) {
        Integer count = carouselMapper.selectCount(new QueryWrapper<>());
        if (count >= Constants.CAROUSEL_MAX) {
            return Result.error("轮播图数量不能超过 10 个！！！");
        }

        Carousel carousel = ClassUtils.copyProperties(carouselParam, new Carousel());
        carousel.setCreateUserAndUpdateUser(session);
        String originCarouselUrl = carousel.getCarouselUrl();
        carousel.setCarouselUrl(CommonUtils.removeUploadFilePath(carousel.getCarouselUrl()));
        int isInsert = carouselMapper.insert(carousel);

        carousel.setCarouselUrl(originCarouselUrl);
        return isInsert > 0 ?
                Result.ok("添加成功", carousel) :
                Result.error("添加失败");
    }

    @Override
    public Result update(Long id, CarouselParam carouselParam) {
        Carousel queryCarousel = carouselMapper.selectById(id);

        BeanUtils.copyProperties(carouselParam, queryCarousel, "createTime", "createUser");
        queryCarousel.setUpdateUser(session);

        queryCarousel.setUpdateTime(LocalDateTime.now());
        String originCarouselUrl = queryCarousel.getCarouselUrl();
        queryCarousel.setCarouselUrl(CommonUtils.removeUploadFilePath(originCarouselUrl));
        int isUpdate = carouselMapper.updateById(queryCarousel);

        queryCarousel.setCarouselUrl(originCarouselUrl);
        return isUpdate > 0 ?
                Result.ok("更新成功", queryCarousel) :
                Result.error("更新失败");
    }

    @Override
    public Result remove(Long id) {
        int isDelete = carouselMapper.deleteById(id);
        return isDelete > 0 ?
                Result.ok("删除成功") :
                Result.error("删除失败");
    }
}
