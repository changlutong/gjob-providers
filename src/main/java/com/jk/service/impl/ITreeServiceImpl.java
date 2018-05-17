package com.jk.service.impl;

import com.jk.mapper.TreeMapper;
import com.jk.model.Tree;
import com.jk.service.ITreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/05/14.
 */
@Service("treeService")
public class ITreeServiceImpl implements ITreeService{
    @Autowired
    private TreeMapper treeMapper;



  /*  public List<Tree> menulist() {
        return treeMapper.menulist();
    }
*/

    @Override
    public List<Tree> menulist(Integer rid) {
        // 查询出一级菜单
        List<Tree> firstMenu = treeMapper.menuList(0,rid);
        // 查询一级菜单的子菜单
        recursionMenu(firstMenu,rid);
        return firstMenu;
    }
    /**
     * 递归加载子菜单
     */
    public void recursionMenu(List<Tree> firstMenu,Integer   rid) {
        //通过循环去除父节点的id，
        for (Tree menu : firstMenu) {

            // 查询菜单是否有子菜单
            List<Tree> menuList2 = treeMapper.menuList(menu.getId(),rid);
            //判断菜单是否有子节点，有的话放到children
            if (menuList2.size() > 0) {

                menu.setChildren(menuList2);

                recursionMenu(menuList2,rid);
            }

        }

    }

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
    public void deletelist(Integer id) {
        treeMapper.deletelist(id);
    }
    @Override
    public void deletelist2(Integer id) {
        treeMapper.deletelist2(id);
    }
    @Override
    public void updatelist(Tree menu) {
        treeMapper.updatelist(menu);
    }



}
