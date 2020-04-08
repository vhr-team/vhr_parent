package cn.ddossec.core.service.Impl;

import cn.ddossec.core.service.HrService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ddossec.core.mapper.HrMapper;
import cn.ddossec.core.entity.Hr;

import java.util.List;

/**
 * @author 30315
 * @title: HrService
 * @projectName vhr_parent
 * @description: TODO
 * @date 2020-04-0615:09
 */
@Service
public class HrServiceImpl extends ServiceImpl<HrMapper, Hr> implements HrService {

    @Autowired
    private HrMapper hrMapper;

    @Override
    public Hr loadUserByUserName(String username) {
        QueryWrapper<Hr> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        return hrMapper.selectOne(wrapper);
    }
}
