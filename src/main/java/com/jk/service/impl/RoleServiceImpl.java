package com.jk.service.impl;

import com.jk.mapper.RoleMapper;
import com.jk.model.Role;
import com.jk.model.Tree;
import com.jk.service.RoleService;
import com.jk.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/05/15.
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public PageUtil addcharolelist(PageUtil pageUtil) throws Exception {
        pageUtil.setTotal(roleMapper.querycount());
        pageUtil.setRows(roleMapper.getList(pageUtil.getStart(),pageUtil.getPageSize()) ) ;


        return pageUtil;
    }

    @Override
    public List<Tree> addcharolemenulist(Integer roid) {
        // 查询出一级菜单
        List<Tree> firstMenu = roleMapper.addcharolemenulist(0,roid);
        // 查询一级菜单的子菜单
        return firstMenu;
    }
    @Override
    public List<Tree> addchamenulist() {
        // 查询出一级菜单
        List<Tree> firstMenu = roleMapper.addchamenulist();
        // 查询一级菜单的子菜单
        return firstMenu;
    }

    @Override
    public void deleterolelist(Integer roid) {
        roleMapper.deleterolelist(roid);
    }

    @Override
    public void saverolemenulist(int mid, Integer roid) {
        roleMapper.saverolemenulist(mid,roid);
    }

    @Override
    public List<Role> charolelist(Role role) {
        return roleMapper.charolelist(role);
    }


}
