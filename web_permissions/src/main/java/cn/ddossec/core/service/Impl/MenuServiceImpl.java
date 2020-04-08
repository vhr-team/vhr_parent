package cn.ddossec.core.service.Impl;

import cn.ddossec.core.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ddossec.core.mapper.MenuMapper;
import cn.ddossec.core.entity.Menu;

import java.util.List;

/**
    * @title: MenuServiceImpl
    * @projectName vhr_parent
    * @description: TODO
    * @author 30315
    * @date 2020-04-088:40
    */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> loadLeftMenuJsonByUid(Integer id) {
        return menuMapper.loadLeftMenuJsonByUid(id);
    }
}
