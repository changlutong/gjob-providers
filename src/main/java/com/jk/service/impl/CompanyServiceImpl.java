package com.jk.service.impl;

import com.jk.mapper.ICompanyDao;
import com.jk.model.Company;

import com.jk.service.ICompanyService;
import com.jk.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

import java.io.UnsupportedEncodingException;
import java.util.List;


/**
 * Created by yuankang on 2018/5/15.
 */
@Service("companyService")
public class CompanyServiceImpl implements ICompanyService {

    @Autowired
    private ICompanyDao companyDao;

    @Override
    public String tosavecompany(Company company) {
        String cphone = company.getId();
        String username = company.getUsername();
        String pp = cphone.substring(0, 2);
        if ("13".equals(pp)) {
            //选表
            company.setCompanytablename("t_company13");
            String companytablename = company.getCompanytablename();
            //判断下手机号存在不存在
            List<Company> companyList = companyDao.quaryphonenum(cphone, companytablename);

            if (companyList == null || companyList.size() <= 0) {
                List<Company> list = companyDao.quaryusername(username, companytablename);
                if (list != null && list.size() > 0) {
                    return "6";//6为用户名已经存在
                }
            } else {
                return "4";//4为手机号已经存在
            }
            companyDao.tosavecompany(company);
            return "1";//1为注册成功

        } else if ("15".equals(pp)) {
//选表
            company.setCompanytablename("t_company15");
            String companytablename = company.getCompanytablename();
            //判断下手机号存在不存在
            List<Company> companyList = companyDao.quaryphonenum(cphone, companytablename);
            if (companyList != null && companyList.size() > 0) {
                return "4";//4为手机号已经存在
            } else {
                List<Company> list = companyDao.quaryusername(username, companytablename);
                if (list != null && list.size() > 0) {
                    return "6";//6为用户名已经存在
                }
            }
            companyDao.tosavecompany(company);
            return "1"; //1为注册成功

        } else if ("17".equals(pp)) {
            company.setCompanytablename("t_company17");
            String companytablename = company.getCompanytablename();
            //判断下手机号存在不存在
            List<Company> companyList = companyDao.quaryphonenum(cphone, companytablename);
            if (companyList == null || companyList.size() <= 0) {
                List<Company> list = companyDao.quaryusername(username, companytablename);
                if (list != null && list.size() > 0) {
                    return "6";//6为用户名已经存在
                }
            } else {
                return "4";//4为手机号已经存在
            }
            companyDao.tosavecompany(company);
            return "1";//1为注册成功

        } else if (!"".equals(pp)) {
            company.setCompanytablename("t_company");
            String companytablename = company.getCompanytablename();
            //判断下手机号存在不存在
            List<Company> companyList = companyDao.quaryphonenum(cphone, companytablename);
            if (companyList != null && companyList.size() > 0) {
                return "4";//4为手机号已经存在
            } else {
                List<Company> list = companyDao.quaryusername(username, companytablename);
                if (list != null && list.size() > 0) {
                    return "6";//6为用户名已经存在
                }
            }
            companyDao.tosavecompany(company);
            return "1";//1为注册成功

        }


        return "3";//3为手机号不符合规则
    }

    @Override
    public String querycompanylogin(Company company) {
        String phone = company.getId();
        String password = company.getPassword();
        String cphone = phone.substring(0, 2);
        if ("13".equals(cphone)) {

            company.setCompanytablename("t_company13");
            String companytablename = company.getCompanytablename();
            List<Company> list = companyDao.querycompanylogin(phone, password, companytablename);
            if (list != null && list.size() > 0) {

                return "2";//2为成功
            }
        } else if ("15".equals(cphone)) {
            company.setCompanytablename("t_company15");
            String companytablename = company.getCompanytablename();
            List<Company> list = companyDao.querycompanylogin(phone, password, companytablename);
            if (list != null && list.size() > 0) {

                return "2";//2为成功
            }
        } else if ("17".equals(cphone)) {
            company.setCompanytablename("t_company17");
            String companytablename = company.getCompanytablename();
            List<Company> list = companyDao.querycompanylogin(phone, password, companytablename);
            if (list != null && list.size() > 0) {

                return "2";//2为成功
            }
        } else {
            company.setCompanytablename("t_company");
            String companytablename = company.getCompanytablename();
            List<Company> list = companyDao.querycompanylogin(phone, password, companytablename);
            if (list != null && list.size() > 0) {

                return "2"; //2为成功
            }
        }
        return "1";//1为账号密码错

    }

    @Override
    public String queryregisteremail(Company company) {

        String cphone = company.getId();
        String email = company.getEmail();
        String phone = cphone.substring(0,2);
        if ("13".equals(phone)) {

            company.setCompanytablename("t_company13");
            String companytablename = company.getCompanytablename();


            List<Company> list = companyDao.queryregisteremail(cphone, email, companytablename);
            if (list != null && list.size() > 0) {

                try {

   EmailUtil.sendHtmlMail(email, "密码修改信息","<a href='http://localhost:8087/company/findpassword.jsp?id='"+cphone+">aaa</a>");

                } catch (MessagingException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }


                return "2";//2为成功
            }
        }else if("15".equals(phone)){
            company.setCompanytablename("t_company15");
            String companytablename = company.getCompanytablename();
            List<Company> list = companyDao.queryregisteremail(cphone, email, companytablename);
            if (list != null && list.size() > 0) {

                try {

                    EmailUtil.sendHtmlMail(email, "密码修改信息","<a href='http://localhost:8087/company/findpassword.jsp?id='"+cphone+">aaa</a>");

                } catch (MessagingException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }


                return "2";//2为成功
            }

        }else if("17".equals(phone)){
            company.setCompanytablename("t_company17");
            String companytablename = company.getCompanytablename();
            List<Company> list = companyDao.queryregisteremail(cphone, email, companytablename);
            if (list != null && list.size() > 0) {

                try {

                    EmailUtil.sendHtmlMail(email, "密码修改信息","http://localhost:8087/company/findpassword.jsp?id="+cphone);

                } catch (MessagingException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }


                return "2";//2为成功
            }

        }else{

            company.setCompanytablename("t_company");
            String companytablename = company.getCompanytablename();
            List<Company> list = companyDao.queryregisteremail(cphone, email, companytablename);
            if (list != null && list.size() > 0) {

                try {

                    EmailUtil.sendHtmlMail(email, "密码修改信息","http://localhost:8087/company/findpassword.jsp?id="+cphone);

                } catch (MessagingException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }


                return "2";//2为成功
            }

        }


        return "1";
    }

    @Override
    public void updateCompanyPassword(Company company) {
        String id = company.getId();
        String password = company.getPassword();
        String phone = id.substring(0, 2);
        if("13".equals(phone)){

            company.setCompanytablename("t_company13");
            String companytablename = company.getCompanytablename();
            companyDao.updateCompanyPassword(id,password,companytablename);

        }  if("15".equals(phone)){

            company.setCompanytablename("t_company15");
            String companytablename = company.getCompanytablename();
            companyDao.updateCompanyPassword(id,password,companytablename);

        }  if("17".equals(phone)){

            company.setCompanytablename("t_company17");
            String companytablename = company.getCompanytablename();
            companyDao.updateCompanyPassword(id,password,companytablename);

        }else{
            company.setCompanytablename("t_company");
            String companytablename = company.getCompanytablename();
            companyDao.updateCompanyPassword(id,password,companytablename);

        }




    }


/*
    @Override
    public void sentEmailInfo(Company company)  {

        String id = company.getId();
        String phone = id.substring(0, 2);
        if("13".equals(phone)){
            company.setCompanytablename("t_company13");
            String companytablename = company.getCompanytablename();
            String email = company.getEmail();

                try {

    EmailUtil.sendHtmlMail("email", "密码修改信息","http://localhost:8087/company/findpassword.jsp?="+id);


                } catch (MessagingException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }


            }







        }*/


   // }



}