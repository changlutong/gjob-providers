package com.jk.service.impl;

import com.jk.mapper.TreeMapper;
import com.jk.model.Tree;
import com.jk.service.ITreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Administrator on 2018/05/14.
 */
@Service("treeService")
public class ITreeServiceImpl implements ITreeService{
    @Autowired
    private TreeMapper treeMapper;




/*
 @Override
    public List<Tree> menulist(Integer rid) {
        return null;
    }
*/


   /*
    public List<Tree> menulist(Integer rid) {
        // 查询出一级菜单
        List<Tree> firstMenu = treeMapper.menuList(0,rid);
        // 查询一级菜单的子菜单
         recursionMenu(firstMenu,rid);
        return firstMenu;
    }
*//**
     * 递归加载子菜单
     *//*

    public void recursionMenu(List<Tree> firstMenu,Integer   rid) {
        //通过循环去除父节点的id，
        for (Tree tree : firstMenu) {
            // 查询菜单是否有子菜单
            List<Tree> menuList2 = treeMapper.menuList(tree.getId(),rid);
            //判断菜单是否有子节点，有的话放到children
            if (menuList2.size() > 0) {

                tree.setChildren(menuList2);

                recursionMenu(menuList2,rid);
            }

        }
    }*/

    @Override
    public String savelist(Tree menu , Integer rid) throws Exception {
        treeMapper.savelist(menu);
        Tree  mm=   treeMapper.addchalist(menu);
        List<Integer>  roid=   treeMapper.addcharole(mm.getPid());
        Integer   ro=0;
        for (int i = 0; i < roid.size(); i++) {
            ro=roid.get(i);
        }
        treeMapper.saverolemenulist(mm.getId(),ro);
        return "true";
    }


    @Override
    public Set<Map<String, Object>> menulist(Integer id) {
        //定义list集合接受查询的参数
        List<Map<String,Object>> list =new ArrayList<Map<String,Object>>();
        list = treeMapper.menulist(id);
        Set<Map<String,Object>> obj =new HashSet<Map<String,Object>>();

        for (Map<String, Object> map : list) {
            obj.add(map);
        }

        return obj;
    }

    @Override
    public void deletelist(Integer id) {
        treeMapper.deletelist(id);
    }
    public void deletelist2(Integer id) {
        treeMapper.deletelist2(id);
    }

  /*  @Override
    public List<Tree> queryMenu() {
        return treeMapper.queryMenu();
    }*/

    @Override
    public void updatelist(Tree menu) {
        treeMapper.updatelist(menu);
    }

   /* //查询树
    @Override
    public List<Tree> queryTree(Integer rid) throws Exception {
        List<Tree> firstMenu = treeMapper.menuListtree(0,rid);
        recursionTree(firstMenu,rid);
        return firstMenu;
    }

    private void recursionTree(List<Tree> firstMenu, Integer rid) {
        for (Tree menu : firstMenu) {
            // 查询菜单是否有子菜单
            List<Tree> menuList2 = null;
            try {
                menuList2 = treeMapper.menuListtree(menu.getId(),rid);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (menuList2.size() > 0) {
                menu.setChildren(menuList2);
                recursionTree(menuList2,rid);
            }
        }
    }*/


}
