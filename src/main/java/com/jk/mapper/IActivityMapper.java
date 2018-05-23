/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: IActivityMapper
 * Author:   ASUS
 * Date:     2018/5/18 10:31
 * Description:
 * History:
 * <author>          <e_mail>
 * 孙国进             getElements@outlook.com
 */
package com.jk.mapper;

import com.jk.model.Activity;
import com.jk.model.BankType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author ASUS
 * @create 2018/5/18
 * @since 1.0.0
 */
public interface IActivityMapper {

    @Select("select * from t_huodong limit #{start},#{rows}")
    List<Activity> queryActivityList(@Param("start") int start,@Param("rows") Integer rows);

    @Select("select count(*) from t_huodong")
    long queryActivityCount();
//(title,content,status,imageurl,url,horder,createtime,starttime,endtime)
    @Insert("insert into t_huodong(title,content,status,imageurl,url,horder,creattime,starttime,endtime)values(#{activity.title},#{activity.content},#{activity.status},#{activity.imageurl},#{activity.url},#{activity.horder},#{activity.createtime},#{activity.starttime},#{activity.endtime})")
    void saveActivity(@Param("activity") Activity activity);

    @Delete("delete from t_huodong where id = #{i}")
    void deleteActivity(@Param("i") int i);
}