package com.jk.mapper;

import com.jk.model.Notice;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Administrator on 2018/05/17.
 */
@Mapper
public interface NoticeMapper {
    @Select("select * from t_notice")

    List<Notice> getnotice(Notice notice);
    @Select("select * from t_notice " +
            "LIMIT #{page},#{rows}")
    List<Notice> querylistnotice(@Param("page") Integer page, @Param("rows") Integer rows, @Param("notice") Notice notice);

    @Insert("   insert into t_notice ( noticename, noticedate,noticebulletin )" +
            "    values ( #{noticename}, #{noticedate}, #{noticebulletin})")
    int addnotice(@Param("noticeid") Integer noticeid,@Param("noticename") String noticename, @Param("noticedate") String noticedate,@Param("noticebulletin") String noticebulletin);

@Delete("   delete  from  t_notice where  noticeid= #{idss}")
    void deletenotice(@Param("idss")int idss);
@Select("select * from t_notice where noticeid = #{noticeid}")
    Notice upNotice(Integer noticeid);
@Update("update t_notice set noticename = #{noticename}," +
        "noticedate = #{noticedate},noticebulletin = #{noticebulletin} where noticeid = #{noticeid}")
    int updateNotice(Notice notice);
@Select("select * from t_notice")
    List<Notice> querynotices(Notice notice);

    @Select(" SELECT * from  t_notice where  noticeid=#{noticeid} ")
    Notice noticesids(@Param("noticeid") Integer noticeid);
}
