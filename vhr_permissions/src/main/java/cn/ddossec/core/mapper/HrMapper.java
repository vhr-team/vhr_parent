package cn.ddossec.core.mapper;

import cn.ddossec.core.entity.Hr;
import cn.ddossec.core.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author 30315
 * @title: HrMapper
 * @projectName vhr_parent
 * @description: TODO
 * @date 2020-04-0615:09
 */
public interface HrMapper extends BaseMapper<Hr> {

    /**
     * 根据登录用户ID，查询用户角色
     *
     * @param id
     * @return
     */
    List<Role> getHrRolesById(Integer id);
}
