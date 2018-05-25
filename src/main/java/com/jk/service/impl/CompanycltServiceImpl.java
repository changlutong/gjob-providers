/**
 * Copyright (C), 2018-2018, jk有限公司
 * FileName: CompanycltServiceImpl
 * Author:  常路通
 * Date:     2018/5/22 15:36
 * Description: 公司业务层实现类
 * /**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: CompanycltServiceImpl
 * Author:   chang
 * Date:     2018/5/22 15:36
 * Description: 公司业务层实现类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jk.service.impl;

import com.jk.mapper.ICompanycltMapper;
import com.jk.model.Company;
import com.jk.model.Job;
import com.jk.service.ICompanycltService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 〈一句话功能简述〉<br> 
 * 〈公司业务层实现类〉
 *
 * @author chang
 * @create 2018/5/22
 * @since 1.0.0
 */
@Service("companycltService")
public class CompanycltServiceImpl implements ICompanycltService{
    @Autowired
    private ICompanycltMapper companycltMapper;

    @Override
    public List<Map<String, Object>> getjlinfo(String companyid) {
        List<String>list01=companycltMapper.getjoblistclt01(companyid);
        List<String>list03=new ArrayList<>();
        List<Map<String, Object>>list04=new ArrayList<>();
        for (String str:list01
                ) {
            List<String>list02=companycltMapper.getjoblistclt02(str);
            for (String str1:list02
                    ) {
                list03.add(str1);
            }
        }
        for (String str2:list03
                ) {
            List<Map<String, Object>>list05=companycltMapper.getjlinfo(str2);
            for (Map<String, Object> map:list05
                    ) {
                list04.add(map);
            }
        }
        return list04;
    }

    @Override
    public void addzhiwei(Job job) {
        job.setId(UUID.randomUUID().toString().replace("-",""));
        job.setCreatetime(new Date());
        job.setSalary(job.getSalary().replace(",","-"));
        job.setShowstatus(1);
        companycltMapper.addzhiwei(job);
    }
    public List<Map<String,Object>> getzhiweilist(String companyid){
        List<Map<String,Object>>list=  companycltMapper.getzhiweilist(companyid);

        return list;
    }

    @Override
    public Map<String, Object> selectjobbyid(String str) {
        Map<String, Object>map=companycltMapper.selectjobbyid(str);
        return map;
    }

    @Override
    public void updatejobstatus(Integer showstatus, String ids) {
        if(showstatus==1){
            companycltMapper.updatejobstatus(2,ids);
        }
        if(showstatus==2){
            companycltMapper.updatejobstatus(1,ids);
        }
    }

    @Override
    public List<Map<String, Object>> getzhiweilistfor2(String companyid) {
        return companycltMapper.getzhiweilistfor2(companyid);
    }

    @Override
    public List<Map<String, Object>> selectjiobclt2(String companyid, Job job) {
        List<Map<String, Object>> list=companycltMapper.selectjiobclt2(job.getEduback(),job.getWorkspace());
        return list;
    }

    @Override
    public List<Job> selectalljob(Job job) {

        List<Job>  list = companycltMapper.selectalljob(job);

        return list;
    }

    @Override
    public void toudijianli(String jobid, String userid) {


        String uuid = UUID.randomUUID().toString().replaceAll("-","");

        companycltMapper.toudijianli(jobid,userid,uuid);

    }

    @Override
    public Job selectalljobbyid(String zpid) {
        return companycltMapper.selectalljobbyid(zpid);
    }

    @Override
    public Company selectcompanybyid(String gongsiid) {

        String [] arr ={"t_company","t_company13","t_company15","t_company17"};
        Company company=null;
        for (String biaoid: arr) {
          company = companycltMapper.selectcompanybyid(biaoid,gongsiid);

            if(company!=null){

               break;

            }

        }
        return  company;
    }

    @Override
    public List<Job> selectjobbygongsiid(String id) {
        return companycltMapper.selectjobbygongsiid(id);
    }
}