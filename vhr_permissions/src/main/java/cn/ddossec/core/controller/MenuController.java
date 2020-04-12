package cn.ddossec.core.controller;

import cn.ddossec.core.entity.Hr;
import cn.ddossec.core.entity.Menu;
import cn.ddossec.core.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 30315
 * @title: MenuController
 * @projectName vhr_parent
 * @description: TODO
 * @date 2020-04-089:53
 */
@RestController
@RequestMapping("/system")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/cfg/loadLeftMenuJsonByUid")
    public List<Menu> loadLeftMenuJsonByUid() {
        return menuService.loadLeftMenuJsonByUid(((Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
    }
}
