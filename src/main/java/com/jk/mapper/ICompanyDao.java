package com.jk.mapper;

import com.jk.model.Company;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by yuankang on 2018/5/15.
 */
@Mapper
public interface ICompanyDao {

 //查手机号看重复不
    @Select("select * from ${companytablename} where id=#{cphone}")
    List<Company> quaryphonenum(@Param("cphone") String cphone, @Param("companytablename") String companytablename);
    //查用户名看重复不
    @Select("select * from ${companytablename} where username=#{username}")
    List<Company> quaryusername(@Param("username") String username,@Param("companytablename") String companytablename);
//注册
    @Insert("insert into ${companytablename} (id,companyname,companyprovince,companycity,contacts,sex,companyfixedphone,username,password,email,tradinglicense,legalpersonname,checkstatus,companytablename,category ,idcardpicture,idcardpicturefan)  values (#{id},#{companyname},#{companyprovince},#{companycity},#{contacts},#{sex},#{companyfixedphone},#{username}, #{password}, #{email},#{tradinglicense},#{legalpersonname},  #{checkstatus},#{companytablename}, #{category}, #{idcardpicture},#{idcardpicture})")
    void tosavecompany(Company company);

    //登陆
    @Select("select * from ${companytablename} where id=#{phone} and password=#{password}")
    List<Company> querycompanylogin(@Param("phone") String phone, @Param("password") String password,@Param("companytablename") String companytablename);

    //查询手机号和邮箱有没有找回密码时候
    @Select("select * from ${companytablename} where id=#{cphone} and email=#{email}")
    List<Company> queryregisteremail(@Param("cphone")String phone,@Param("email") String email, @Param("companytablename")String companytablename);

    //修改密码
    @Update("update ${companytablename} set password=#{password} where id=#{id} ")
    void updateCompanyPassword(@Param("id") String id, @Param("password") String password, @Param("companytablename") String companytablename);

    @Select("select * from ${biaoid} where checkstatus=1 limit #{start},#{rows}")
    List<Company> queryCompangList(@Param("start") int start, @Param("rows") Integer rows,@Param("biaoid") String biaoid);

    @Select("select count(*) from ${biaoid} where checkstatus=1")
    long queryCompanyCount(@Param("biaoid") String biaoid);

    @Update("update ${biaoid} set checkstatus = 2 where id=#{id}")
    void updateCompanyStatus(@Param("id") String id,@Param("biaoid") String biaoid);

    @Select("select * from ${biaoid} where checkstatus=2 limit #{start},#{rows}")
    List<Company> selectCompangTwoList(@Param("start") int start,@Param("rows") Integer rows,@Param("biaoid") String biaoid);

    @Select("select count(*) from ${biaoid} where checkstatus=2")
    long selectCompanyCount(@Param("biaoid") String biaoid);

    @Select("select * from ${companytablename} where id=#{phone}")
    List <Company>querycompanyloginstatus(@Param("phone") String phone,@Param("companytablename") String companytablename);

    @Delete("delete from ${s} where id = #{idss}")
    void deletecompany(@Param("s") String s,@Param("idss") String idss);

/*    @Update("update from ${s} set checkstatus=2 where id = #{s1}")
    void updateCompanyStatus(@Param("s1") String s1, @Param("s") String s);*/
}
