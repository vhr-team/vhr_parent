package cn.ddossec.core.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 30315
 * @title: HelloController
 * @projectName vhr_parent
 * @description: TODO
 * @date 2020-04-1221:33
 */
@RestController
public class HelloController {

    @GetMapping("/employee/basic/hello")
    public String hello1(){
        return "/employee/basic/hello";
    }

    @GetMapping("/employee/advanced/hello")
    public String hello2(){
        return "/employee/advanced/hello";
    }

}
