package com.jk.mapper;

import com.jk.model.Job;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * Created by chang on 2018/5/22.
 */
@Mapper
public interface ICompanycltMapper {
    @Select(" select t1.usergrxxid,t1.grxxname,t1.sex,t1.phone,t1.email,t1.hkadr,t1.birthdate,t2.jybjxl from t_grxx t1,t_jybj t2 where t1.usergrxxid=t2.jybjid and usergrxxid=#{str}")
    List<Map<String, Object>> getjlinfo(@Param("str") String str);

    @Select(" select id from t_job where companyphone=#{companyid}")
    List<String> getjoblistclt01(@Param("companyid") String companyid);

    @Select(" select userid from t_job_user where jobid=#{str}")
    List<String> getjoblistclt02(@Param("str") String str);

    @InsertProvider(type = ICompanycltMapper.JobDaoProvider.class, method = "insertJobById")
    void addzhiwei(Job job);
    @Select(" select * from t_job where companyphone=#{companyid}")
    List<Map<String,Object>> getzhiweilist(@Param("companyid")String companyid);
    @Select(" select * from t_job where id= #{str}")
    Map<String,Object> selectjobbyid(@Param("str")String str);
    @Update(" update t_job set showstatus=#{i} where id=#{ids}  ")
    void updatejobstatus(@Param("i")int i, @Param("ids")String ids);
    @Select(" select * from t_job where companyphone= #{companyid} and showstatus=2")
    List<Map<String,Object>> getzhiweilistfor2(String companyid);
    @SelectProvider(type = ICompanycltMapper.JobDaoProvider.class, method = "selectJob")
    List<Map<String,Object>> selectjiobclt2(@Param("edu")String edu,@Param("wspa")String wspa);
    @SelectProvider(type = ICompanycltMapper.JobDaoProvider.class, method = "selectalljob")
    List<Job> selectalljob(@Param("job") Job job);
    @Insert("insert into t_job_user(id,jobid,userid)values(#{uuid},#{jobid},#{userid}) ")
    void toudijianli(@Param("jobid") String jobid, @Param("userid")String userid, @Param("uuid")String uuid);


    class JobDaoProvider {
        public String insertJobById(Job job) {
            String sql = "insert into t_job(";
            StringBuffer sql1 = new StringBuffer();
            StringBuffer sql2 = new StringBuffer(")values(");
            if (job.getId() != null && !"".equals(job.getId() )) {
                sql1.append("id,");
                sql2.append("#{id},");
            }
            if (job.getSalary() != null && !"".equals(job.getSalary())) {
                sql1.append("salary,");
                sql2.append("#{salary},");
            }
            if (job.getCompanyphone() != null && !"".equals(job.getCompanyphone())) {
                sql1.append("companyphone,");
                sql2.append("#{companyphone},");
            }
            if (job.getWorkexp() != null && !"".equals(job.getWorkexp())) {
                sql1.append("workexp,");
                sql2.append("#{workexp},");
            }
            if (job.getWorkinfo() != null &&!"".equals(job.getWorkinfo())) {
                sql1.append("workinfo,");
                sql2.append("#{workinfo},");
            }
            if (job.getEduback() != null && !"".equals(job.getEduback())) {
                sql1.append("eduback,");
                sql2.append("#{eduback},");
            }
            if (job.getCreatetime() != null && !"".equals(job.getCreatetime())) {
                sql1.append("createtime,");
                sql2.append("#{createtime},");
            }
            if (job.getWorkname() != null && !"".equals(job.getWorkname())) {
                sql1.append("workname,");
                sql2.append("#{workname},");
            }
            if (job.getWorknum() != null) {
                sql1.append("worknum,");
                sql2.append("#{worknum},");
            }
            if (job.getWorkspace() != null && !"".equals(job.getWorkspace())) {
                sql1.append("workspace,");
                sql2.append("#{workspace},");
            }
            if (job.getWorktype() != null && !"".equals(job.getWorktype())) {
                sql1.append("worktype,");
                sql2.append("#{worktype},");
            }
            if (job.getWorkpro() != null && !"".equals(job.getWorkpro())) {
                sql1.append("workpro,");
                sql2.append("#{workpro},");
            }
            if (job.getShowstatus() != null) {
                sql1.append("showstatus,");
                sql2.append("#{showstatus},");
            }
            String sqlstr= sql1.toString().substring(0,sql1.toString().lastIndexOf(","));
            String sqlstr1= sql2.toString().substring(0,sql2.toString().lastIndexOf(","));
            String sqlend=sql+sqlstr+sqlstr1+")";
            return sqlend;
        }
      public String selectJob(@Param("edu")String edu,@Param("wspa")String wspa){
            String sql = "select t1.usergrxxid,t1.grxxname,t1.sex,t1.phone,t1.email,t1.hkadr,t1.birthdate,t2.jybjxl from t_grxx t1,t_jybj t2 where t1.usergrxxid=t2.jybjid";
            if(edu!=null&&!"".equals(edu)){
                sql+=" and t2.jybjxl like '%"+edu+"%'";
            }
            if(wspa!=null&&!"".equals(wspa)){
                sql+=" and t1.hkadr like '%"+wspa+"%'";
            }
            return sql;
        }
        public String selectalljob(@Param("job")Job job){

            String sql ="select * from t_job where showstatus=2";
            if(job.getWorkname()!=null&&!"".equals(job.getWorkname())){
                sql+=" and workname like '%"+job.getWorkname()+"%'";
            }
          return sql;
        }
    }
}
