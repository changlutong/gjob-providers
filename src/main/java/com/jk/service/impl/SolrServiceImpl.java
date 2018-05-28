/**
 * Copyright (C), 2018-2018, jk有限公司
 * FileName: SolrServiceImpl
 * Author:  常路通
 * Date:     2018/5/15 11:32
 * Description: solr业务处理
 * /**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: SolrServiceImpl
 * Author:   chang
 * Date:     2018/5/15 11:32
 * Description: solr业务处理
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jk.service.impl;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.jk.model.Job;
import com.jk.model.QueryJob;
import com.jk.service.ISolrService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

/**
 * 〈一句话功能简述〉<br> 
 * 〈solr业务处理〉
 *
 * @author chang
 * @create 2018/5/15
 * @since 1.0.0
 */
@Service("solrService")

public class SolrServiceImpl implements ISolrService {

    @Autowired
    private SolrClient client;

    @Override
    public QueryJob getsolrjoblist(Integer page,Integer row, String queryname) throws ParseException {
        if(page==null){
            page=1;
        }
        if(row==null){
            row=10;
        }
        Map<String,Object> map= getsolrjoblisttool(page, row, queryname,"workname");
        if(map==null){
             map= getsolrjoblisttool(page, row, queryname,"worktype");
        }
        List<Job>list= (List<Job>) map.get("rows");
       Integer total = (Integer) map.get("total");
        QueryJob queryJob=new QueryJob();
        queryJob.setPage(page);
        queryJob.setRows(list);
        queryJob.setTotal(total);
        return queryJob;
    }
    private Map<String,Object> getsolrjoblisttool(Integer page, Integer row, String queryname, String ziduan) throws ParseException {
        //ModifiableSolrParams params =new ModifiableSolrParams();
        SolrQuery query = new SolrQuery();
        if (!StringUtils.isEmpty(queryname)) {
            if("workname".equals(ziduan)) {
              //  params.set("q", "workname:" + queryname);
                query.set("q", "workname:" + queryname);
               // params.add("q","workname:*"+queryname+"*");
            }else{
               // params.set("q", "worktype:" + queryname);
                query.set("q", "worktype:" + queryname);
               // params.add("q","workname:*"+queryname+"*");
            }
        } else {
            //params.set("q", "*:*");
            query.set("q", "*:*");
        }
       // params.add("start",page+"");

        //每页几条
       // params.add("rows",row+"");
        query.setStart(page);
        //每页几条
        query.setRows(row);
        query.setSort("createtime", SolrQuery.ORDER.desc);
        QueryResponse queryResponse = null;
        try {
            queryResponse = client.query(query, SolrRequest.METHOD.POST);
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //根据QueryResponse 对象获取结果集
        SolrDocumentList results = queryResponse.getResults();
        //查询总条数
        long count = results.getNumFound();
        Integer total=0;
        if(count>0)
        {
            total=Integer.parseInt(count+"");
        }else{
            return null;
        }
        List<Job>list =new ArrayList();

        for (SolrDocument solrDocument:results
             ) {
            Job job=new Job();
            job.setId(solrDocument.get("id").toString());
            job.setSalary(solrDocument.get("salary").toString());
            job.setCreatetime(new Date(solrDocument.get("createtime").toString()));
            job.setWorkexp(solrDocument.get("workexp").toString());
            job.setWorknum(Integer.valueOf(solrDocument.get("worknum").toString()));
            job.setWorkspace(solrDocument.get("workspace").toString());
            job.setWorkpro(solrDocument.get("workpro").toString());
            job.setEduback(solrDocument.get("eduback").toString());
            job.setWorktype(solrDocument.get("worktype").toString());
            job.setWorkname(solrDocument.get("workname").toString());
            job.setWorkinfo(solrDocument.get("workinfo").toString());
            job.setCompanyinfo(solrDocument.get("companyinfo").toString());
            job.setCompanyphone(solrDocument.get("companyphone").toString());
            list.add(job);
        }

            Map<String,Object> map=new HashMap<>();
            map.put("rows",list);
            map.put("total",total);
        return map;



    };


}