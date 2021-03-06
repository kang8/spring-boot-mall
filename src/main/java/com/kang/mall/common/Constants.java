package com.kang.mall.common;

/**
 * @author yikang
 * ClassName: Constants
 * Description: 常量类
 * Create Date: 2021/1/15 21:34
 */
public class Constants {
    /**
     * 轮播图 carousel
     */
    public final static Integer CAROUSEL_MAX = 10;
    public final static String TABLE_FIELD_CAROUSEL_URL = "carousel_url";
    public final static String ENTITY_FIELD_CAROUSEL_URL = "carouselUrl";

    /**
     * 商品分类 category
     */
    public final static Long ROOT_PARENT_ID = -1L;
    public final static Byte ROOT_LEVEL = 0;
    public final static Long ROOT_CATEGORY_ID = 0L;
    public final static String ROOT_CATEGORY_NAME = "根节点";

    public final static Byte FIRST_LEVEL = 1;
    public final static Byte SECOND_LEVEL = 2;
    public final static Byte THIRD_LEVEL = 3;

    public final static String CATEGORY_LIST = "category_list";
    public final static String CATEGORY_OPTION = "category_option";
    public final static String CATEGORY_OPTION_PARENT = "category_option_parent";

    /**
     * 文件上传后请求文件的路径
     */
    public final static String PATH_PATTERN_FOR_ACCESS_UPLOAD_FILE = "/file/**";
    public final static String PATH_FOR_ACCESS_UPLOAD_FILE = "/file";

    /**
     * 商品 goods
     */
    public final static String TABLE_FIELD_GOODS_COVER_IMAGE = "goods_cover_image";
    public final static String ENTITY_FIELD_GOODS_COVER_IMAGE = "goodsCoverImage";

    public final static boolean SELLING = true;
    public final static boolean NOT_SELLING = false;

    /**
     * 商品配置 goods_config
     */
    public final static Byte TYPE_FOR_NEW = 1;
    public final static Byte TYPE_FOR_HOT = 2;
    public final static Byte TYPE_FOR_RECOMMENDATION = 3;
    public final static String NEW = "new";
    public final static String HOT = "hot";
    public final static String RECOMMENDATION = "recommendation";

    /**
     * Session for login
     */
    public final static String ADMIN_LOGIN_CREDENTIAL = "adminLoginId";
    public final static String MALL_LOGIN_CREDENTIAL = "mallLoginId";

    /**
     * 商品排序规则
     */
    public final static String SORT_RULE_NORMAL = "normal";
    public final static String SORT_RULE_NEW = "new";
    public final static String SORT_RULE_PRICE = "price";
}
