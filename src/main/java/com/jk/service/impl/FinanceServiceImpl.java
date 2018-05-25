package com.jk.service.impl;

import com.jk.mapper.FinanceMapper;
import com.jk.model.Finance;
import com.jk.service.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/05/24.
 */
@Service("financeService")
public class FinanceServiceImpl implements FinanceService{

    @Autowired
    private FinanceMapper financeMapper;
    @Override
    public List<Finance> queryfinance(Finance finance) {

        return financeMapper.queryfinance(finance);
    }
}
