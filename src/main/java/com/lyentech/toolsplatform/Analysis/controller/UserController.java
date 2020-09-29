package com.lyentech.toolsplatform.Analysis.controller;


import com.lyentech.toolsplatform.Analysis.entity.User;
import com.lyentech.toolsplatform.Analysis.service.IUserService;
import com.lyentech.toolsplatform.utils.JWT.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.HashMap;
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
     * @param user user
     * @return object
     * 用户登录
     */
    @ResponseBody
    @PostMapping("/login")
    public Object analysisLogin(@RequestBody User user)  {
        log.info("/login 用户登录");
        log.info("/login password = " + user.getPassWord());
        User result = iUserService.matchAccount(user);
        log.info("/login 匹配的用户结果" + result);
        Map<String, Object> response = new HashMap<>();
        if (result == null) {
            log.error("/login 匹配用户失败,用户不存在或密码错误");
            response.put("msg","密码错误");
            response.put("code",50001);
            return response;
        } else {
            log.info("/login 用户匹配成功！");
            Map<String, Object> data = new HashMap<>();
//            生成token值
            String token = JwtUtils.getJwtToken(user);
            log.info("/login token = " + token);
            data.put("token", token);
            response.put("msg", "登录成功");
            response.put("data", data);
            response.put("code", 20000);
            return response;
        }
    }

    /**
     * @param token token
     * @return object
     * 用户登录成功之后获取用户的个人详细信息
     */
    @ResponseBody
    @GetMapping("/info")
    public Object analysisInfo(@RequestParam("token") String token) {
        String roles;
        log.info("/info 根据token值，调用用户信息");
        Map<String, Object> data = new HashMap<>();
        Map<String, Object> reponse = new HashMap<>();
//        这里需要注意，token失效之后进行checkAccount会产生错误
        String userAccount = JwtUtils.checkTokenAccount(token);
        log.info("/info " + userAccount);
//        token失效之后，user Account为null
        User user = new User();
        if (userAccount != null){
//            用户名称、用户类型、用户单位
            user = iUserService.checkAccount(Integer.parseInt(userAccount));
            data.put("name", user.getUserName());
//            根据type判断角色类型
            if (user.getUserType() == 0) {
                roles = "superAdmin";
            } else if (user.getUserType() == 1) {
                roles = "测试人员";
            } else {
                roles = "开发人员";
            }
            data.put("roles",roles);
            data.put("unit",user.getUserUnit());
            reponse.put("msg", "成功获取用户信息");
            reponse.put("code", 20000);
            reponse.put("data", data);
        } else {
            reponse.put("msg","token失效");
            reponse.put("code",50002);
        }

        return reponse;
    }

    /**
     * @param userAccount userAccount
     * @return object
     * 登录之前的账号存在性检验
     */
    @ResponseBody
    @GetMapping("/userAccountListAll")
    public Object UserAccountList(@RequestParam("userAccount") Integer userAccount) {
        log.info("/userAccountListAll 登录表单输入的账户" + userAccount);
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

    /**
     *用户退出
     * @return Object
     */
    @ResponseBody
    @PostMapping("/logout")
    public Object analysisLogout(){
        log.info("/logout 用户登出");
        Map<String,Object> response = new HashMap<>();
        response.put("msg","用户登出成功");
        response.put("code",20000);
        return response;
    }


}
