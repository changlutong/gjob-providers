package com.jk.mapper;

import com.jk.model.*;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * Created by ASUS on 2018/5/15.
 */
@Mapper
public interface IUserdatumMapper {

    /**
     * 查询 有没有当前用户
     * @param usergDatum
     * @return
     */
    @Select("select count(*) from t_grxx where usergrxxid = #{datumid}")
    int userdatumgrxxSize(UsergDatum usergDatum);
    /**
     * 小天才 !!　新增个人信息
     * @param usergDatum
     */
    @Insert("insert into t_grxx(usergrxxid,grxxname,sex,birthdate,vintage,phone,email,marriage,gationality,experience,visage,hkadr)values(#{datumid},#{grxxname},#{grxxXB},#{grxxcsrq},#{grxxgznf},#{grxxlxfs},#{grxxdzyj},#{grxxhy},#{grxxgj},#{grxxjl},#{grjlzzmm},#{grxxdz})")
    void userdatumgrxx(UsergDatum usergDatum);

    /**
     * 小天才 !!　修改个人信息
     * @param usergDatum
     */
    @Update("update t_grxx set grxxname = #{grxxname},sex = #{grxxXB},birthdate = #{grxxcsrq},vintage = #{grxxgznf},phone = #{grxxlxfs},email = #{grxxdzyj},marriage = #{grxxhy},gationality = #{grxxgj},experience = #{grxxjl},visage = #{grjlzzmm},hkadr = #{grxxdz} where usergrxxid =#{datumid}")
    void updateuserdatumgrxx(UsergDatum usergDatum);
    /**
     * 小天才 !! 新增自我评价
     * @param zwpjzt
     * @param zwpjnr
     * @param datumid
     */
    @Insert("insert into t_zwpj(theme,content,userzwpjid)values(#{zwpjzt},#{zwpjnr},#{datumid})")
    void adduserzwpj(@Param("zwpjzt") Integer zwpjzt,@Param("zwpjnr")String zwpjnr, @Param("datumid")String datumid);

    /**
     * xiaoyiancai求职意向
     * @param qzyx
     */
    @Insert("insert into t_qzyx(userqzyxid,nature,site,occupation,salary,statust)values(#{userqzyxid},#{qzyxxz},#{qzyxdd},#{qzyxcszy},#{qzyxyx},#{qzyxgzzt})")
    void adduserqzyx(Userqzyx qzyx);

    /**
     *小天才 工作经验
     * @param usergzjy
     */
    @Insert("insert into t_gzjy(usergzjyid,qyname,zylb,kssj,jssj,zwyx,nr)values(#{gzjyid},#{gzjyqymc},#{gzjyzylb},#{queryBugDateStart},#{queryBugDateEnd},#{gzjyzwyx},#{gzjynr})")
    void addusergzjy(Usergzjy usergzjy);

    /**
     * 小天才 教育背景
     * @param jybjfrom
     */
    @Insert("insert into t_jybj(jybjid,jybjkssj,jybjjssj,jybjxxmc,jybjsftz,jybjxl)values(#{jybjid},#{queryBugDateStart},#{queryBugDateEnd},#{jybjname},#{jybjtz},#{jybjxl})")
    void adduserjybj(Userjybj jybjfrom);

    /** 小天才
     * 新增培训经历
     * @param pxjlfrom
     */
    @Insert("insert into t_pxjl(pxjlid,pxjlkssj,pxjljssj,psjlpxjg,pxjldd,pxjlsftz,pxjljs)values(#{pxjlid},#{queryBugDateStart},#{queryBugDateEnd},#{pxjljg},#{pxjlddian},#{pxjlsftz},#{pxjlxqjs})")
    void adduserpxjl(Userpxjl pxjlfrom);

    /**
     * 小天才
     * 新增语言能力
     * @param yynlform
     */
    @Insert("insert into t_yynl(yynlid,yynlpxjg,yynldxnl,yynltsnl)values(#{yynlid},#{yynlpxjg},#{yynldxnl},#{yynltsnl})")
    void adduseryynl(Useryynl yynlform);

    /**
     * 小天才 新增其他
     * @param qtxxform
     */
    @Insert("insert into t_qita(qitauserid,qitazt,qitajj)values(#{qitaid},#{qitazhuti},#{qitams})")
    void adduserqita(Userqt qtxxform);

    /**
     * 新增oss上传
     * @param up
     */
   /* @Insert("insert into t_oss(zpfjid,zpfjfilenc,zpfjurl,zpfjjs)values(fileid,fileName,fileUrl,filenr)")
    void addossFile(UserFile up);
*/
    /**
     * 查询回显 小天才
     ** @param userid
     * @return
     */
    @Select("select * from t_grxx where usergrxxid = #{userid}")
    List<Map<String,Object>>  queryusergrxx(String userid);

    /**
     * 查询回显 小天才 刘沁鑫
     * @param userid
     * @return
     */
    @Select("select * from t_zwpj where userzwpjid = #{userid}")
    List<Map<String,Object>> queryuserzwpj(String userid);

    /**
     * 小天才  求职意向
     * @param userid
     * @return
     */
    @Select("select * from t_qzyx where userqzyxid = #{userid}")
    List<Map<String,Object>> queryuserqzyx(String userid);

    /**
     *小天才 刘沁鑫 工作经验
     * @param userid
     * @return
     */
    @Select("select * from t_gzjy where usergzjyid = #{userid}")
    List<Map<String,Object>> queryusergzjy(String userid);

    /**
     * 教育背景
     * @param userid
     * @return
     */
    @Select("select * from t_jybj where jybjid = #{userid}")
    List<Map<String,Object>> queryuserjybj(String userid);

    /**
     * 小天才 刘沁鑫 培训经历
     * @param userid
     * @return
     */
    @Select("select * from t_pxjl where pxjlid = #{userid}")
    List<Map<String,Object>> queryuserpxjl(String userid);

    /***
     * 语言能力 小天才 刘沁鑫
     * @param userid
     * @return
     */
    @Select("select * from t_yynl where yynlid = #{userid}")
    List<Map<String,Object>> queryuseryynl(String userid);

    /**
     * 删除 教育背景
     * @param userid
     * @param id
     */
    @Delete("delete from t_jybj where id = #{id} and jybjid = #{userid}")
    void deleteuserjybj(@Param("userid") String userid, @Param("id") Integer id);

    /**
     * 删除 培训经历
     * @param userid
     * @param id
     */
    @Delete("delete from t_pxjl where id = #{id} and pxjlid = #{userid}")
    void deleteuserpxjl(@Param("userid") String userid, @Param("id") Integer id);

    /**
     * 删除 语言能力
     * @param userid
     * @param id
     */
    @Delete("delete from t_yynl where id = #{id} and yynlid = #{userid}")
    void deleteuseryynl(@Param("userid") String userid, @Param("id") Integer id);

    /**
     * 新增图片路经包括简介
     * * @param userid
     * @param url
     * @param userjs
     */
    @Insert("insert into t_oss(zpuserid,zpfjurl,zpfjjs)values(#{userid},#{url},#{userjs})")
    void addusertp(@Param("userid") String userid, @Param("url") String url, @Param("userjs") String userjs);

    /**
     * 查询 其他
     * @param userid
     * @return
     */
    @Select("select * from t_qita where qitauserid = #{userid}")
    List<Map<String,Object>> queryuserqita(String userid);
    /**
     * 新增个人图片
     * * @param userid
     * @param url
     *
     */
    @Insert("insert into t_gephoto(gruserid,grfjurl)values(#{userid},#{url})")
    void addPhoto(@Param("userid")String userid, @Param("url")String url);
    /**
     * 查询 个人照片
     * @param userid
     * @return
     */
    @Select("select zpfjurl from t_oss where zpuserid = #{userid}")
    List<Map<String,Object>> queryuseryhzp(String userid);

    /**
     * 登录 查是否存在
     * @param loginname
     * @param password
     * @return
     */
    @Select("select count(*) from t_personal where phone = #{loginname} and password = #{password}")
    int selectUsercount(@Param("loginname")String loginname, @Param("password")String password);

    /**
     * 存在 之后  再查 再放到 session 里
     * @param loginname
     * @param password
     * @return
     */
    @Select("select * from t_personal where phone = #{loginname} and password = #{password}")
    List<Tpersonal> selectUserlogin(@Param("loginname")String loginname, @Param("password")String password);

    /**
     *查询 当前用户收投递的 职业
     *
     */

    @Select("select t2.workname,t2.workspace,t2.workinfo,t2.salary,t2.id from t_job_user t1,t_job t2 where t1.jobid = t2.id and t1.userid = #{userid}")
    List<Map<String,Object>> selectalljobto(@Param("userid")String userid);
}
