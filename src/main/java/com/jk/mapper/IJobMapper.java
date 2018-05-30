package com.jk.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by chang on 2018/5/15.
 */
@Mapper
public interface IJobMapper {
    @Select(" select workname from t_job where showstatus=2")
    List<String> getJobName();
    @Select(" select worktype from t_job where showstatus=2")
    List<String> getJobName01();
}
