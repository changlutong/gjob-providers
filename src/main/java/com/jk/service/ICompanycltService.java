package com.jk.service;

import com.jk.model.Job;

import java.util.List;
import java.util.Map;

/**
 * Created by chang on 2018/5/22.
 */
public interface ICompanycltService {
    List<Map<String,Object>> getjlinfo(String companyid);

    void addzhiwei(Job job);

    List<Map<String,Object>> getzhiweilist(String companyid);

    Map<String,Object> selectjobbyid(String str);

    void updatejobstatus(Integer showstatus, String ids);

    List<Map<String,Object>> getzhiweilistfor2(String companyid);

    List<Map<String,Object>> selectjiobclt2(String companyid, Job job);
}
