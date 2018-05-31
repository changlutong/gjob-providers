package com.jk.service.impl;

import com.jk.model.Logs;
import com.jk.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yuankang on 2018/5/24.
 */
@Service("logService")
public class LogServiceImpl implements LogService{

    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public Map<String, Object> logsList( Logs logs, Integer page, Integer rows) {

          Query query = new Query();

        //第一页查询的起始位置,比如第一页是从0开始,第二页是从10开始查
        page=(page-1)*rows;
          //分页
        query.skip(page);
        //rows每页条数
        query.limit(rows);//以上private 将 mongodbtemplate导入 ，，通过.  点出 find属性进行查数据
        List <Logs> list=mongoTemplate.find(query, Logs.class);
        //查询总记录数  //同理
        long count=mongoTemplate.count(query, Logs.class);
        //放进map
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("total", count);
        map.put("rows", list);
        return map;

    }

    @Override
    public List selecttongji() {

        List list = new ArrayList();
        Query query = new Query();

        Query query1 = new Query();
        Query query2 = new Query();
        Query query3 = new Query();

        //查总数量
        long countz =mongoTemplate.count(query ,Logs.class);
        //初始化一个方法
        String methodName ="methodName";
        String isexception ="isexception";
        query.addCriteria(Criteria.where(isexception).regex(".*?异常.*"));
        query1.addCriteria(Criteria.where(methodName).regex("add.*"));
        query2.addCriteria(Criteria.where(methodName).regex("delete.*"));
        query3.addCriteria(Criteria.where(methodName).regex("update.*"));
        long county = mongoTemplate.count(query, Logs.class);//异常数量
        long countzeng = mongoTemplate.count(query1, Logs.class);
        long countshan = mongoTemplate.count(query2, Logs.class);
        long countgai = mongoTemplate.count(query3, Logs.class);
        long countcha = countz-countzeng-countshan-countgai;
        long countr = countz-county;
        list.add(countr);
        list.add(countz);
        list.add(county);
        list.add(countzeng);
        list.add(countshan);
        list.add(countgai);
        list.add(countcha);
        return list;

    }
}
