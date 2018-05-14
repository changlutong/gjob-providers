/**
 * Copyright (C), 2018-2018, jk有限公司
 * FileName: UserService
 * Author:  常路通
 * Date:     2018/5/14 14:36
 * Description: 用户业务层
 * /**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: UserService
 * Author:   chang
 * Date:     2018/5/14 14:36
 * Description: 用户业务层
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jk.service.impl;

import com.jk.mapper.IUserMapper;
import com.jk.service.IUserService;
import com.jk.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈用户业务层〉
 *
 * @author chang
 * @create 2018/5/14
 * @since 1.0.0
 */
@Service("userService")
public class UserServiceImpl implements IUserService{
    @Autowired
    private IUserMapper userMapper;

    @Override
    public List<User> getUserList() {
        return userMapper.getUserList();
    }
}