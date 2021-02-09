package com.kang.mall.util;

import com.kang.mall.common.Constants;
import com.kang.mall.config.properties.MallCategoryProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yikang
 * ClassName: CategoryUtils
 * Description: category 工具类
 * Create Date: 2021/2/7 20:53
 */
@Component
public class CategoryUtils {

    @Autowired
    private MallCategoryProperties categoryProperties;

    public boolean hasFirstLevel(Byte level) {
        return Constants.FIRST_LEVEL.equals(level);
    }

    public boolean hasSecondLevel(Byte level) {
        return Constants.SECOND_LEVEL.equals(level);
    }

    public boolean hasLastLevel(Byte level) {
        return categoryProperties.getLevel().equals(level);
    }

    public boolean hasLastSecondLevel(Byte level) {
        return categoryProperties.getLevel() == level + 1;
    }

    public boolean hasGreaterThanToLastLevel(Byte level) {
        return level > categoryProperties.getLevel();
    }

    public boolean hasLessThanOrEqualToLastLevel(Byte level) {
        return !hasGreaterThanToLastLevel(level);
    }
}
