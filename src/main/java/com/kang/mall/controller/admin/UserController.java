package com.kang.mall.controller.admin;

import com.kang.mall.common.Result;
import org.springframework.web.bind.annotation.*;

/**
 * @author kang
 * ClassName: UserController
 * Create Date: 2021/3/14 18:15
 */
@RestController("User_AdminController")
@RequestMapping("/admin")
public class UserController {

    @GetMapping("/user")
    public Result list() {
        return null;
    }

    @GetMapping("/user/{id}")
    public Result get(@PathVariable String id) {
        return null;
    }

    @PostMapping("/user")
    public Result create() {
        return null;
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public Result update(@PathVariable String id) {
        return null;
    }

    @RequestMapping(value = "/user{id}", method = RequestMethod.DELETE)
    public Result remove(@PathVariable String id) {
        return null;
    }
}
