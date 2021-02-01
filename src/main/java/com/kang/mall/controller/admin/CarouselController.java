package com.kang.mall.controller.admin;

import com.kang.mall.common.Result;
import com.kang.mall.param.admin.CarouselParam;
import com.kang.mall.service.admin.CarouselService;
import com.kang.mall.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @author yikang
 * ClassName: CarouselAdminController
 * Create Date: 2021/1/26 16:33
 */
@RestController("CarouselAdminController")
@RequestMapping("/admin")
public class CarouselController {

    @Autowired
    private CarouselService carouselService;

    @GetMapping("/carousel")
    public Result list() {
        return carouselService.list();
    }

    @GetMapping("/carousel/{id}")
    public Result get(@PathVariable("id") Long id) {
        return carouselService.get(id);
    }

    @PostMapping("/carousel")
    public Result create(@RequestBody @Valid CarouselParam carousel,
                         HttpSession session) {
        Long userId = CommonUtil.getAdminUserId(session);
        carousel.setCreateUser(userId);
        carousel.setUpdateUser(userId);
        return carouselService.create(carousel);
    }

    @RequestMapping(value = "/carousel/{id}", method = RequestMethod.PUT)
    public Result update(@PathVariable("id") Long id,
                         @RequestBody @Valid CarouselParam carousel,
                         HttpSession session) {
        Long userId = CommonUtil.getAdminUserId(session);
        carousel.setUpdateUser(userId);
        return carouselService.update(id, carousel);
    }

    @RequestMapping(value = "/carousel/{id}", method = RequestMethod.DELETE)
    public Result remove(@PathVariable("id") Long id) {
        return carouselService.remove(id);
    }
}
