package com.zzp.service;

import com.zzp.entity.UserDO;

public interface UserService {

    /**
     * 添加新用户
     *
     * username 唯一， 默认 USER 权限
     */
    void insert(UserDO userDO);

    /**
     * 查询用户信息
     * @param username 账号
     * @return UserEntity
     */
    UserDO getByUsername(String username);

}
