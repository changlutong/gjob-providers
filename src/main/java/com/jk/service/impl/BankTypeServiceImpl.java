/**
 * Copyright (C), 2015-2018, 龙盾技术科技有限公司
 * FileName: BankTypeServiceImpl
 * Author:   ASUS
 * Date:     2018/5/17 9:55
 * Description:
 * History:
 * <author>          <e_mail>
 * 孙国锦          getElements@outlook.com
 */
package com.jk.service.impl;

import com.jk.mapper.IBankTypeMapper;
import com.jk.model.BankType;
import com.jk.model.CompanyCard;
import com.jk.model.Score;
import com.jk.model.Tree;
import com.jk.service.BankTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author ASUS
 * @create 2018/5/17
 * @since 1.0.0
 */
@Service("bankTypeService")
public class BankTypeServiceImpl implements BankTypeService {


    @Autowired
    private IBankTypeMapper bankTypeMapper;

    @Override
    public List<BankType> queryBankTypeList(Integer page, Integer rows) {
        int start = (page-1)*rows;
        return bankTypeMapper.queryBankTypeList(start,rows);
    }

    @Override
    public long queryBankTypecount() {
        return bankTypeMapper.queryBankTypecount();
    }

    @Override
    public void addBankTypeList(BankType bankType) {
    bankTypeMapper.addBankTypeList(bankType);
    }

    @Override
    public void addBankTypeList(int i) {
        bankTypeMapper.deleteBankType(i);
    }

    /**
     * 属性菜单查询
     * @return
     */
    @Override
    public List<Tree> queryTreeList() {
        return bankTypeMapper.queryTreeList();
    }

    @Override
    public List<BankType> selectBankTypeList() {
        return bankTypeMapper.selectBankTypeList();
    }

    @Override
    public void savebankcard(CompanyCard companyCard) {
        bankTypeMapper.savebankcard(companyCard);
    }

    @Override
    public List<CompanyCard> querybankcard() {
        return bankTypeMapper.querybankcard();
    }

    @Override
    public CompanyCard queryBankCardById(Integer cid) {
        return bankTypeMapper.queryBankCardById(cid);
    }

    @Override
    public void deleteBankCardById(Integer cid) {
        bankTypeMapper.deleteBankCardById(cid);
    }

    @Override
    public Score queryComScore(String comid) {
        return bankTypeMapper.queryComScore(comid);
    }

    @Override
    public String addcompanychongzhi(String comid, String money) {
        Score score = bankTypeMapper.queryscoreById(comid);
        if(score==null){
            Score score1 = new Score();
            score.setScore(Long.valueOf(money));
            score.setComid(comid);
            Integer i = bankTypeMapper.addcompanychongzhi(score1);
            if(i!=null || !i.equals("")){
                return "success";
            }else {
                return "fail";
            }
        }else {
            Integer a = bankTypeMapper.updatejifen(comid,Long.valueOf(money));
            if(a!=null || !a.equals("")){
                return "success";
            }else {
                return "fail";
            }
        }
    }

    @Override
    public void updatebankcard(Long aLong, String valll) {
        bankTypeMapper.updatebankcard(aLong,valll);
    }

}