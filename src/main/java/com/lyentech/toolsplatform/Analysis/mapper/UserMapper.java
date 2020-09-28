package com.lyentech.toolsplatform.Analysis.mapper;

import com.lyentech.toolsplatform.Analysis.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 672025
 * @since 2020-09-18
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 登录时匹配用户信息
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
