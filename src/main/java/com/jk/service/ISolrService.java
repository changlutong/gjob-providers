package com.jk.service;

import com.jk.model.Job;
import com.jk.model.QueryJob;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * Created by chang on 2018/5/15.
 */
public interface ISolrService {
    QueryJob getsolrjoblist(Integer page,Integer row, String queryname) throws ParseException;

    void addjob(Job job);

    void deletejob(String ids);
}
