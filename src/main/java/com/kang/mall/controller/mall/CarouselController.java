package com.kang.mall.controller.mall;

import com.kang.mall.common.Result;
import com.kang.mall.entity.Carousel;
import com.kang.mall.service.mall.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yikang
 * ClassName: CarouselController
 * Create Date: 2021/3/8 10:37
 */
@RestController
public class CarouselController {

    @Autowired
    private CarouselService carouselService;

    @GetMapping("/carousel")
    public Result<List<Carousel>> list() {
        List<Carousel> carousels = carouselService.list();
        return Result.ok(carousels);
    }

}
