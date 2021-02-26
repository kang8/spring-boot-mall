package com.kang.mall.service.admin.impl;

import com.kang.mall.common.Result;
import com.kang.mall.entity.Goods;
import com.kang.mall.service.admin.GoodsService;
import com.kang.mall.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * @author yikang
 * ClassName: GoodsServiceImpl
 * Create Date: 2021/2/22 17:26
 */
@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private HttpSession session;

    @Override
    public Result list(Integer page, Integer size) {
        return null;
    }

    @Override
    public Result get(Long id) {
        Long adminUserId = CommonUtils.getAdminUserId(session);
        return Result.ok(adminUserId);
    }

    @Override
    public Result create(Goods goods) {
        return null;
    }

    @Override
    public Result update(Long id, Goods goods) {
        return null;
    }

    @Override
    public Result remove(Long id) {
        return null;
    }
}
