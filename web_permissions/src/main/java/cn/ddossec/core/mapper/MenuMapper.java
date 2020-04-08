package cn.ddossec.core.mapper;

import cn.ddossec.core.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
    * @title: MenuMapper
    * @projectName vhr_parent
    * @description: TODO
    * @author 30315
    * @date 2020-04-088:40
    */
public interface MenuMapper extends BaseMapper<Menu> {
    List<Menu> loadLeftMenuJsonByUid(Integer id);
}
