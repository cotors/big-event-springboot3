package com.chen.controller;

import com.auth0.jwt.JWT;
import com.chen.pojo.Result;
import com.chen.pojo.User;
import com.chen.service.UserService;
import com.chen.utils.JwtUtil;
import com.chen.utils.Md5Util;
import com.chen.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author: Cotors
 * @create 2024-03-15-10:42
 */
@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //注册功能
    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username,
                           @Pattern(regexp = "^\\S{5,16}$") String password)
    {
        //查询用户名是否存在
        User user = userService.findByUsername(username);
        if (user == null){
            //不存在用户名,注册
            userService.register(username,password);
            return Result.success();
        }else {
            return Result.error("用户名已存在");
        }

    }

    //登录功能
    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$") String username,
                                @Pattern(regexp = "^\\S{5,16}$") String password)
    {
        //查询用户名是否存在
        User loginuser = userService.findByUsername(username);
        if (loginuser == null) {
            return Result.error("用户名不存在");
        }
        //判断密码是否正确
        if (Md5Util.getMD5String(password).equals(loginuser.getPassword())) {
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",loginuser.getId());
            claims.put("username",loginuser.getUsername());
            String token = JwtUtil.genToken(claims);

            //把token存入redis
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            operations.set(token,token,12, TimeUnit.HOURS);

            return Result.success(token);
        }
        return Result.error("密码错误");
    }

    //获取用户详细信息
    @GetMapping("/userInfo")
    public Result<User> userInfo(/*@RequestHeader(name = "Authorization") String token*/){
//        Map<String, Object> map = JwtUtil.parseToken(token);
//        String username = (String) map.get("username");
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.findByUsername(username);
        return Result.success(user);
    }

    //更新用户
    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user){
        userService.update(user);
        return Result.success();
    }

    //更新头像
    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl){
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    //修改密码
    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String,String> params,@RequestHeader("Authorization") String token){
        //校验参数 获取原密码
        String old_pwd = params.get("old_pwd");

        String new_pwd = params.get("new_pwd");
        String re_pwd = params.get("re_pwd");

        //判断三个密码是否为空
        if (!StringUtils.hasLength(old_pwd) || !StringUtils.hasLength(new_pwd) || !StringUtils.hasLength(re_pwd)){
            return Result.error("存在参数为空");
        }

        //获取数据库密码
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User loginUser = userService.findByUsername(username);
        //判断原密码是否正确
        if (!loginUser.getPassword().equals(Md5Util.getMD5String(old_pwd))){
            return Result.error("原密码不正确");
        }

        //校验修改的密码是否一致
        if (!re_pwd.equals(new_pwd)){
            return Result.error("两次密码不一致");
        }

        //调用userService,修改密码
        userService.updatePwd(new_pwd);

        //密码修改成功后，删除Redis中对应的token
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.getOperations().delete(token);

        return Result.success();
    }
}
