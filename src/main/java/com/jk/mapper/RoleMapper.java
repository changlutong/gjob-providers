package com.jk.mapper;

import com.jk.model.Role;
import com.jk.model.Tree;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Administrator on 2018/05/15.
 */
@Mapper
public interface RoleMapper {
@Select(" select   count(*)   from   t_role  where   1=1")
    Integer querycount();
@Select(" select    *   from   t_role  where  1=1 " +
        " limit #{start},#{pageSize} ")
    List<Role> getList(@Param("start")Integer start,@Param("pageSize") Integer pageSize);
@Select("select * from rel_role_menu,t_tree" +
        " where rel_role_menu.mid= t_tree.id " +
        " and rel_role_menu.roid=#{roid}")
    List<Tree> addcharolemenulist(@Param("pid")int pid,@Param("roid") Integer roid);
@Select("select   *    from   t_tree ")
    List<Tree> addchamenulist();
@Delete("  delete  from  rel_role_menu   where  roid= #{roid}")
    void deleterolelist(Integer roid);
@Insert("INSERT  into   rel_role_menu  (mid,roid)  value (#{mid},#{roid})")
    void saverolemenulist(@Param("mid")int mid,@Param("roid") Integer roid);
@Select("select   *   from   t_role")
    List<Role> charolelist(Role role);
}
