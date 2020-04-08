package cn.ddossec.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 唐颖豪
 * @title: permissionApp
 * @projectName vhr_parent
 * @description: 权限模块的启动类
 * @date 2020-04-0613:52
 */
@SpringBootApplication
@MapperScan(basePackages = {"cn.ddossec.core.mapper"})
public class permissionApp {

    public static void main(String[] args) {
        SpringApplication.run(permissionApp.class, args);
    }

}