package com.jk.service;

import com.jk.model.Role;
import com.jk.model.Tree;
import com.jk.util.PageUtil;

import java.util.List;

/**
 * Created by Administrator on 2018/05/15.
 */
public interface RoleService {


    PageUtil addcharolelist(PageUtil pageUtil) throws  Exception;


    List<Tree> addcharolemenulist(Integer roid);

    List<Tree> addchamenulist();
    void deleterolelist(Integer roid);
    void saverolemenulist(int mid, Integer roid);

    List<Role> charolelist(Role role);

}
