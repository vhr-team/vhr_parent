package cn.ddossec.core.service;

import cn.ddossec.core.entity.Hr;
import cn.ddossec.core.entity.Role;

import java.util.List;

/**
 * @author 30315
 * @title: HrService
 * @projectName vhr_parent
 * @description: TODO
 * @date 2020-04-0615:20
 */
public interface HrService {

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    Hr loadUserByUserName(String username);

    /**
     * 根据登录用户ID，查询用户角色
     * @param id
     * @return
     */
    List<Role> getHrRolesById(Integer id);
}
