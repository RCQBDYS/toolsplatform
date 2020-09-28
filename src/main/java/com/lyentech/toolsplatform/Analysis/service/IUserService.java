package com.lyentech.toolsplatform.Analysis.service;

import com.lyentech.toolsplatform.Analysis.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 672025
 * @since 2020-09-18
 */
public interface IUserService extends IService<User> {
    /**
     *匹配用户信息
     * @param user
     * @return user
     */
    User matchAccount(User user);

    /**
     *检验数据库中用户的存在性
     * @Param null
     * @return list
     */
    User checkAccount(Integer userAccount);
}
