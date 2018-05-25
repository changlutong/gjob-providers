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

import com.jk.mapper.IGuanggaoMapper;
import com.jk.model.Company;
import com.jk.model.Guanggao;
import com.jk.service.IGuanggaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
@Service("guanggaoService")
public class GuanggaoServiceImpl implements IGuanggaoService {

    @Autowired
    private IGuanggaoMapper guanggaoMapper;



    @Override
    public List<Guanggao> selectGuanggao(Integer page, Integer rows) {
        //分页查询
        //查询总条数
       // long total = guanggaoMapper.selectCount();
        //计算开始位置和结束位置
        int start = (page-1)*rows;
        List<Guanggao> list=guanggaoMapper.userList(start,rows);

        /*JSONObject json =new JSONObject();
        json.put("total", total);
        json.put("rows", list);*/

        return list;
    }

    @Override
    public void saveguanggao(Guanggao guanggao) {
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        guanggao.setId(uuid);
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

    @Override
    public long querycount() {
        return guanggaoMapper.selectCount();
    }

    @Override
    public List<Company> querycompany() {
        String [] arr ={"t_company","t_company13","t_company15","t_company17"};
        List<Company> list = new ArrayList();

        for (String biaoid: arr) {

            List<Company> list1 = guanggaoMapper.querycompany(biaoid);
            for (Company company : list1) {
                list.add(company);
            }
        }
        return list;
    }

    @Override
    public List<Guanggao> selectallguanggao() {

        List<Guanggao> list =guanggaoMapper.selectallguanggao();
        return  list;
    }
    @Override
    public List<Guanggao> selecterhaoguanggao() {

        List<Guanggao> list =guanggaoMapper.selecterhaoguanggao();
        return  list;
    }

    @Override
    public List<Guanggao> selectsanhaoguanggao() {

        List<Guanggao> list =guanggaoMapper.selectsanhaoguanggao();
        return  list;
    }
    @Override
    public List<Guanggao> selectsihaoguanggao() {

        List<Guanggao> list =guanggaoMapper.selectsihaoguanggao();
        return  list;
    }
    @Override
    public List<Guanggao> selectwuhaoguanggao() {

        List<Guanggao> list =guanggaoMapper.selectwuhaoguanggao();
        return  list;
    }

    @Override
    public List<Guanggao> queryguanggaotwo(Integer page, Integer rows) {

        int start = (page-1)*rows;
        List<Guanggao> list=guanggaoMapper.queryguanggaotwo(start,rows);


        return list;
    }

    @Override
    public long querycounttwo() {
        return guanggaoMapper.queryCountTwo();
    }

    @Override
    public void updateguanggaoStatus(String s) {
        guanggaoMapper.updateguanggaoStatus(s);
    }
}