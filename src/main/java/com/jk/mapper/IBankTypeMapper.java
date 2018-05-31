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

import com.jk.model.*;
import org.apache.ibatis.annotations.*;

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

    @Select("select * from t_banktype")
    List<BankType> selectBankTypeList();

    @Insert("insert into t_comcard (comid,cardnum,cardname,cardid,money) values(#{companyCard.comid},#{companyCard.cardnum},#{companyCard.cardname},#{companyCard.cardid},#{companyCard.money})")
    void savebankcard(@Param("companyCard") CompanyCard companyCard);

    @Select("select t1.*,t2.imageurl as bankimg,t2.bankname as bankcardname from t_comcard t1,t_banktype t2 where t1.cardid = t2.id")
    List<CompanyCard> querybankcard();

    @Select("select * from t_comcard where cid=#{cid}")
    CompanyCard queryBankCardById(@Param("cid") Integer cid);

    @Delete("delete from t_comcard where cid=#{cid}")
    void deleteBankCardById(@Param("cid") Integer cid);

    @Select("select * from t_score where comid=#{comid}")
    Score queryComScore(@Param("comid") String comid);

    @Select("select * from t_score where comid=#{comid}")
    Score queryscoreById(@Param("comid") String comid);

    @Insert("insert into t_score (comid,score) values(#{score1.comid},#{score1.score})")
    Integer addcompanychongzhi(@Param("score1") Score score1);

    @Update("update t_score set score=(score+#{aLong}) where comid=#{comid}")
    Integer updatejifen(@Param("comid") String comid,@Param("aLong") Long aLong);

    @Update("update t_comcard set money=(money-#{aLong}) where cardid=#{valll}")
    void updatebankcard(@Param("aLong") Long aLong,@Param("valll") String valll);

    @Update("update t_score set score=(score-#{l}) where comid=#{comid}")
    void updatejifendown(@Param("comid") String comid,@Param("l") long l);

    @Select("select score from t_score where comid=#{comid}")
    long querycomyue(@Param("comid") String comid);

    @Select("select * from finance where dates=#{sdfformat}")
    Finance querytongjibiao(@Param("sdfformat") String sdfformat);

    @Insert("insert into finance(dates,financetotal) values(#{sdfformat},#{jine})")
    void addtongjibiao(@Param("sdfformat") String sdfformat,@Param("jine") Integer jine);

    @Update("update finance set financetotal=(financetotal+#{jine}) where dates=#{sdfformat}")
    void updatetongjibiao(@Param("sdfformat") String sdfformat, @Param("jine") Integer jine);
}