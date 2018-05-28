/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: IGuanggaoMapper
 * Author:   ASUS
 * Date:     2018/5/14 19:31
 * Description:
 * History:
 * <author>          <e_mail>
 * 孙国进             getElements@outlook.com
 */
package com.jk.mapper;

import com.jk.model.Company;
import com.jk.model.Guanggao;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author ASUS
 * @create 2018/5/14
 * @since 1.0.0
 */
@Mapper
public interface IGuanggaoMapper {

    @Select("select count(*) from t_guanggao where status='2'")
    long selectCount();
    @Select("select * from t_guanggao where status='2' limit #{start},#{rows}")
    List<Guanggao> userList(@Param("start") int start,@Param("rows") Integer rows);
    @Insert("insert into t_guanggao values(#{guanggao.id},#{guanggao.imageurl},#{guanggao.pid},#{guanggao.info},#{guanggao.companyid},#{guanggao.status},#{guanggao.price})")
    void saveguanggao(@Param("guanggao") Guanggao guanggao);
    @Update("updata from t_guanggao set status ='2' where gid = #{ggid}")
    void updatestatus(@Param("ggid") String ggid);
    @Delete("delete from t_guanggao where id = #{gid}")
    void deleteguanggao(@Param("gid") String gid);

    @Select("select id,companyname from ${biaoid}")
    List<Company> querycompany(@Param("biaoid") String biaoid);

    @Select("select * from t_guanggao where pid =1 and status=2 order by price desc")
    List<Guanggao> selectallguanggao();
    @Select("select * from t_guanggao where pid =2 and status=2 order by price desc")
    List<Guanggao> selecterhaoguanggao();
    @Select("select * from t_guanggao where pid =3 and status=2 order by price desc")
    List<Guanggao> selectsanhaoguanggao();

    @Select("select * from t_guanggao where pid =4 and status=2 order by price desc")
    List<Guanggao> selectsihaoguanggao();

    @Select("select * from t_guanggao where pid =5 and status=2 order by price desc")
    List<Guanggao> selectwuhaoguanggao();


    @Select("select * from t_guanggao where status='1' limit #{start},#{rows}")
    List<Guanggao> queryguanggaotwo(@Param("start") int start,@Param("rows") Integer rows);

    @Select("select count(*) from t_guanggao where status='1'")
    long queryCountTwo();

    @Update("update t_guanggao set status = 2 where id=#{s}")
    void updateguanggaoStatus(@Param("s") String s);
}