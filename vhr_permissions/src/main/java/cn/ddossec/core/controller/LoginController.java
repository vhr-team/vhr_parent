package cn.ddossec.core.controller;

import cn.ddossec.core.utils.ResponseResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 30315
 * @title: LoginController
 * @projectName vhr_parent
 * @description: TODO
 * @date 2020-04-0616:53
 */
@RestController
public class LoginController {

    @GetMapping("/login")
    public ResponseResult login() {
        return ResponseResult.error("尚未登录,请登录");
    }

}
