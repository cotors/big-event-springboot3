package com.chen.service;

import com.chen.pojo.User;

/**
 * @author: Cotors
 * @create 2024-03-15-10:44
 */
public interface UserService {
    //根据用户名查询用户
    User findByUsername(String username);

    //注册用户
    void register(String username, String password);

    //更新用户信息
    void update(User user);

    //更新头像
    void updateAvatar(String avatarUrl);

    //修改密码
    void updatePwd(String new_pwd);
}
