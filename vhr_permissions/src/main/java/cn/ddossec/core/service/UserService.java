package cn.ddossec.core.service;

import cn.ddossec.core.entity.Hr;
import cn.ddossec.core.mapper.HrMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 30315
 * @title: UserService
 * @projectName vhr_parent
 * @description: TODO
 * @date 2020-04-0615:28
 */
@Service
@Transactional
@Slf4j
public class UserService implements UserDetailsService {

    @Autowired
    private HrService hrService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Hr hr = hrService.loadUserByUserName(username);
        if(hr == null){
            throw new UsernameNotFoundException("用户名不存在!");
        }
        log.debug("用户存在");
        return hr;
    }
}
