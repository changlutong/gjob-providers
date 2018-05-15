/**
 * Copyright (C), 2015-2018, 龙盾技术科技有限公司
 * FileName: GuanggaoServiceImpl
 * Author:   ASUS
 * Date:     2018/5/14 19:30
 * Description:
 * History:
 * <author>          <e_mail>
 * 孙国锦          getElements@outlook.com
 */
package com.jk.service.impl;

import com.alibaba.dubbo.common.json.JSONObject;
import com.jk.mapper.IGuanggaoMapper;
import com.jk.model.Guanggao;
import com.jk.service.IGuanggaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author ASUS
 * @create 2018/5/14
 * @since 1.0.0
 */
@Service("guanggaoservice")
public class GuanggaoServiceImpl implements IGuanggaoService {

    @Autowired
    private IGuanggaoMapper guanggaoMapper;



    @Override
    public JSONObject selectGuanggao(Integer page, Integer rows) {
        //分页查询
        //查询总条数
        long total = guanggaoMapper.selectCount();
        //计算开始位置和结束位置
        int start = (page-1)*rows;
        List list=guanggaoMapper.userList(start,rows);

        JSONObject json =new JSONObject();
        json.put("total", total);
        json.put("rows", list);

        return json;
    }

    @Override
    public void saveguanggao(Guanggao guanggao) {
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        guanggao.setGid(uuid);
        guanggao.setStatus("1");//1是未审核  2是已审核
        guanggaoMapper.saveguanggao(guanggao);

    }

    @Override
    public void updatastatus(String ggid) {
        guanggaoMapper.updatestatus(ggid);
    }

    @Override
    public void deleteguanggao(String gid) {
        guanggaoMapper.deleteguanggao(gid);
    }
}