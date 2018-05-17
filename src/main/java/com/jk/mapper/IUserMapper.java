package com.jk.mapper;

import com.jk.model.Role;
import com.jk.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by chang on 2018/5/14.
 */
@Mapper
public interface IUserMapper {
    /*@Select(" select * from t_user ")
    List<User> getUserList();*/
    @Select("  select  *  from t_user  where  name=#{name}   and   password=#{password}")
    User login(User ren) throws  Exception;
@Select(" select   count(*)   from   t_user  where   1=1")
    Integer querycount();
@Select("  select    *   from   t_user where  1=1" +
        " limit #{start},#{pageSize} ")
    List getList(@Param("start")Integer start,@Param("pageSize") Integer pageSize);
@Select("select   t_role.*   from  t_user,t_role,rel_ren_role where   t_user.rid=rel_ren_role.rid  and  " +
        " t_role.roid=rel_ren_role.roid   and  t_user.rid=#{rid}")
    List<Role> addcharolelist(Integer rid);
@Select(" SELECT  *  from   t_role")
    List<Role> charolelist();

    void updaterolelist(@Param("roid")int roid,@Param("rid") Integer rid);
@Delete("delete  from  rel_ren_role   where  rid= #{rid}")
    void deletelist(@Param("rid")Integer rid);
@Insert("INSERT  into   rel_ren_role  (rid,roid)  value (#{rid},#{roid})")
    void savelist22(@Param("roid")int roid,@Param("rid") Integer rid);
@Select("select * from t_user")
    List<User> getuser(@Param("user")User user);
@Select("select * from t_user " +
        "LIMIT #{page},#{rows}")
    List<User> querylist(@Param("page")Integer page,@Param("rows") Integer rows,@Param("user") User user);


}
