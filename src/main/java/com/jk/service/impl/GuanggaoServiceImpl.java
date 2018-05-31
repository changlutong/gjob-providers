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

import com.jk.mapper.IBankTypeMapper;
import com.jk.mapper.IGuanggaoMapper;
import com.jk.model.Company;
import com.jk.model.Finance;
import com.jk.model.Guanggao;
import com.jk.service.IGuanggaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

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

    //自动注入
    @Autowired
    private IBankTypeMapper bankTypeMapper;



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
    public Map<String ,Object> saveguanggao(Guanggao guanggao) {
        Map<String ,Object> map = new HashMap<String ,Object>();
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        guanggao.setId(uuid);
        guanggao.setStatus("1");//1是未审核  2是已审核
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        //积分扣款

        //先查询余额
        String comid = guanggao.getCompanyid();
        Integer yue = bankTypeMapper.querycomyue(comid);
        if(yue == null ){
            yue=0;
        }
        String price = guanggao.getPrice();
        if(Long.parseLong(price) > Long.parseLong(String.valueOf(yue))){
            map.put("money","success");
        }else {
            guanggaoMapper.saveguanggao(guanggao);
            bankTypeMapper.updatejifendown(comid, Long.parseLong(price));
            map.put("money","fail");
            //将数据添加到统计表中
            //获取当前时间
            Date date = new Date();
            //获取消费金额
            String jine = guanggao.getPrice();
            String sdfformat = sdf.format(date);
            Finance finace = bankTypeMapper.querytongjibiao(sdfformat);
            if(finace == null){
                bankTypeMapper.addtongjibiao(sdfformat,Integer.valueOf(jine));
            }else {
                bankTypeMapper.updatetongjibiao(sdfformat,Integer.valueOf(jine));
            }
        }

        return map;
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