package cn.ddossec.core.service;

import cn.ddossec.core.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author 30315
 * @title: MenuService
 * @projectName vhr_parent
 * @description: TODO
 * @date 2020-04-088:40
 */
public interface MenuService extends IService<Menu> {
    List<Menu> loadLeftMenuJsonByUid(Integer id);

    List<Menu> getAllMenusWithRole();
}
