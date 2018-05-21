/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: IBankTypeMapper
 * Author:   ASUS
 * Date:     2018/5/17 9:56
 * Description:
 * History:
 * <author>          <e_mail>
 * 孙国进             getElements@outlook.com
 */
package com.jk.mapper;

import com.jk.model.BankType;
import com.jk.model.Tree;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author ASUS
 * @create 2018/5/17
 * @since 1.0.0
 */
public interface IBankTypeMapper {

    @Select("select * from t_banktype limit #{start},#{rows}")
    List<BankType> queryBankTypeList(@Param("start") int start,@Param("rows") Integer rows);

    @Select("select count(*) from t_banktype")
    long queryBankTypecount();

    @Insert("insert into t_banktype values(#{bankType.id},#{bankType.bankname},#{bankType.imageurl})")
    void addBankTypeList(@Param("bankType") BankType bankType);

    @Delete("delete from t_banktype where id = #{i}")
    void deleteBankType(@Param("i")int i);

    @Select("select * from t_tree")
    List<Tree> queryTreeList();
}