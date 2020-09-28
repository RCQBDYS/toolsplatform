package com.lyentech.toolsplatform.Analysis.service.impl;

import com.lyentech.toolsplatform.Analysis.entity.User;
import com.lyentech.toolsplatform.Analysis.mapper.UserMapper;
import com.lyentech.toolsplatform.Analysis.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 672025
 * @since 2020-09-18
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired(required = false)
    UserMapper userMapper;

    @Override
    public User matchAccount(User user) {
        return userMapper.matchAccount(user);
    }

    @Override
    public User checkAccount(Integer userAccount) {
        return userMapper.checkAccount(userAccount);
    }


}
