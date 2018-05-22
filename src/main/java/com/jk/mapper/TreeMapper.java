package com.jk.mapper;

import com.jk.model.Tree;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/05/14.
 */
@Mapper
public interface TreeMapper {
   /* @Select(" select * from t_tree ")
    List<Tree> menulist();*/

/*   //select * from rel_ren_role ur,t_role r ,rel_role_menu rp,t_tree tt
   //where ur.roid = r.roid and r.roid=rp.roid and rp.mid = tt.id and ur.roid=1*/

                            //用戶角色關聯表
   /* @Select(" select * from  rel_ren_role ur,t_role r ,rel_role_menu rp,t_tree tt" +
            "where ur.roid = r.roid and r.roid=rp.roid and rp.mid = tt.id and ur.rid=#{rid}")
    List<Tree> menulist(@Param("rid") Integer rid);*/
/* List<Tree> menuList(@Param("pid")int pid,@Param("rid") Integer rid);*//*
*/
@Insert("  insert    into  t_tree(pid,text,href)  value(#{pid},#{text},#{href})")
 Integer savelist(Tree menu);
@Delete("  delete  from  t_tree where  id= #{id}")
 void deletelist(Integer id);
@Delete("  DELETE  from  rel_role_menu   where  mid= #{id}")
 void deletelist2(Integer id);
@Select("   select   *  from   t_tree  where  href=#{href}")
 Tree addchalist(Tree menu);
@Select(" select  rel_ren_role.roid from  rel_ren_role   where  rid= #{rid}  ")
 List<Integer> addcharole(@Param("rid")Integer rid);
@Insert(" insert    into  rel_role_menu (mid,roid)  value(#{id},#{ro})")
 void saverolemenulist(@Param("id")Integer id,@Param("ro") Integer ro);
@Update("update t_tree  set text= #{text},href= #{href}  where id= #{id}")
 void updatelist(Tree menu);


@Select("select tt.* from rel_ren_role ur,rel_role_menu rp,t_tree tt \n" +
        "where ur.roid = rp.roid and rp.mid=tt.id and ur.rid=#{id}")
    List<Map<String,Object>> menulist(@Param("id") Integer id);

   /* @Select(" SELECT t_tree.*,t_role.* FROM t_user,t_role,rel_ren_role,t_tree,rel_role_menu \n" +
            "       where t_user.rid =rel_ren_role.rid\n" +
            "        and t_role.roid = rel_ren_role.roid\n" +
            "        and t_tree.id = rel_role_menu.mid\n" +
            "        and t_role.roid= rel_role_menu.roid\n" +
            "        and t_user.rid=#{rid}  and  t_tree.pid = #{pid}")
    List<Tree> queryMenu();
*/
  /* @Select("")
    List<Tree> menuList(Integer id, Integer rid);*/
}
