package com.lyentech.toolsplatform.Analysis.controller;


import com.fasterxml.jackson.databind.util.JSONPObject;
import com.lyentech.toolsplatform.Analysis.entity.User;
import com.lyentech.toolsplatform.Analysis.service.IUserService;
import com.lyentech.toolsplatform.Common.CommonResponse;
import com.lyentech.toolsplatform.utils.JWT.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 672025
 * @since 2020-09-18
 */
@Slf4j
@Controller
@RequestMapping("/Analysis/user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    public void setiUserService(IUserService iUserService) {
        this.iUserService = iUserService;
    }


    /**
     * @param user
     * @return object
     * 用户登录
     */
    @ResponseBody
    @PostMapping("/login")
    public Object analysisLogin(@RequestBody User user) throws JSONException {
        log.info("用户登录");
        User result = iUserService.matchAccount(user);
        log.info("匹配的用户结果" + result);
        Map<String, Object> response = new HashMap<>();
        if (result == null) {
            log.error("匹配用户失败,用户不存在或密码错误");
            response.put("msg","密码错误");
            response.put("code",50001);
            return response;
        } else {
            log.info("用户匹配成功！");
            Map<String, Object> data = new HashMap<>();
//            生成token值
            String token = JwtUtils.getJwtToken(user);
            log.info("token = " + token);
            data.put("token", token);
            data.put("tokenType", "admin");
            response.put("msg", "登录成功");
            response.put("data", data);
            response.put("code", 20000);
            return response;
        }
    }

    /**
     * @param token
     * @return object
     * 用户登录成功之后获取用户的个人详细信息
     */
    @ResponseBody
    @GetMapping("/info")
    public Object analysisInfo(@RequestParam("token") String token) {
        log.info("根据token值，调用用户信息");
        Map<String, Object> data = new HashMap<>();
        Map<String, Object> reponse = new HashMap<>();
        String userAccount = JwtUtils.checkTokenAccount(token);
        log.info(userAccount);
        data.put("roles", userAccount);
        data.put("name", "super admin");
        data.put("avatar", "url");
//        data.put("code",20000);
        reponse.put("msg", "成功获取用户信息");
        reponse.put("code", 20000);
        reponse.put("data", data);
        return reponse;
    }

    /**
     * @param userAccount
     * @return object
     * 登录之前的用户存在性检验
     */
    @ResponseBody
    @GetMapping("/userAccountListAll")
    public Object UserAccountList(@RequestParam("userAccount") Integer userAccount) {
        log.info("登录表单输入的账户" + userAccount);
        User result = iUserService.checkAccount(userAccount);
        Map<String, Object> reponse = new HashMap<>();
        if (result == null) {
            reponse.put("data",0);
            reponse.put("code",20000);
            reponse.put("msg","无此用户");
        } else {
            reponse.put("data",1);
            reponse.put("msg", "用户账号获取成功");
            reponse.put("code", 20000);
        }
        return reponse;
    }


}
