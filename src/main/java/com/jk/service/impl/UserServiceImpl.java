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
import com.jk.model.Role;
import com.jk.model.User;
import com.jk.service.IUserService;
import com.jk.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

  /*@Override
    public List<User> getUserList() {
        return userMapper.getUserList();
    }
*/
    @Override
    public User login(User ren) throws Exception {

        return    userMapper.login(ren);
    }

    @Override
    public PageUtil addcharenlist(PageUtil pageUtil) {
        pageUtil.setTotal(userMapper.querycount());
        pageUtil.setRows(userMapper.getList(pageUtil.getStart(),pageUtil.getPageSize()) ) ;
        return pageUtil;
    }

    @Override
    public List<Role> addcharolelist(Integer rid) {

        return 	userMapper.addcharolelist( rid);
    }

    @Override
    public List<Role> charolelist() throws Exception {

        return userMapper.charolelist();
    }

    @Override
    public void updaterolelist(User ren, String roid) {
        userMapper.deletelist(ren.getRid());
        String[] arr = 	roid.split(",");
        for (int i = 0; i < arr.length; i++) {
            userMapper.savelist22(Integer.parseInt(arr[i]),ren.getRid());
        }
    }


    public List<User> getuser(User user) {
        return userMapper.getuser(user);
    }


    public List<User> querylist(Integer page, Integer rows, User user) {
        page = (page-1)*rows;
        return userMapper.querylist(  page,   rows,   user);
    }


}