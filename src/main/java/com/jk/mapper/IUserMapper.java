package com.jk.mapper;

import com.jk.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by chang on 2018/5/14.
 */
@Mapper
public interface IUserMapper {
    @Select(" select * from t_user ")
    List<User> getUserList();
}
