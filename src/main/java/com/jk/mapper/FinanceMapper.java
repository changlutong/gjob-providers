package com.jk.mapper;

import com.jk.model.Finance;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Administrator on 2018/05/24.
 */
@Mapper
public interface FinanceMapper {

@Select("select * from Finance group by financetotal")
    List<Finance> queryfinance(Finance finance);

}
