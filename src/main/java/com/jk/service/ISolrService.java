package com.jk.service;

import com.jk.model.Job;
import com.jk.model.QueryJob;

import java.text.ParseException;
import java.util.List;

/**
 * Created by chang on 2018/5/15.
 */
public interface ISolrService {
    QueryJob getsolrjoblist(Integer page,Integer row, String queryname) throws ParseException;
}
