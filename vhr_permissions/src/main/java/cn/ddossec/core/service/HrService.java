package cn.ddossec.core.service;

import cn.ddossec.core.entity.Hr;

/**
 * @author 30315
 * @title: HrService
 * @projectName vhr_parent
 * @description: TODO
 * @date 2020-04-0615:20
 */
public interface HrService {

    Hr loadUserByUserName(String username);
}
