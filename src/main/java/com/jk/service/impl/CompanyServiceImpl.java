package com.jk.service.impl;

import com.jk.mapper.ICompanyDao;
import com.jk.model.Company;

import com.jk.service.ICompanyService;
import com.jk.util.EmailUtil;
import com.jk.util.HttpClient;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by yuankang on 2018/5/15.
 */
@Service("companyService")
@Component
public class CompanyServiceImpl implements ICompanyService {

    @Autowired
    private ICompanyDao companyDao;

    @Autowired
    private AmqpTemplate rabbitTemplate;


    @Override
    public String tosavecompany(Company company) {
        String cphone = company.getId();
        String username = company.getUsername();
        String pp = cphone.substring(0, 2);
        String zhaopian = company.getTradinglicense();
        String[] arr = zhaopian.split(",");


            company.setTradinglicense(arr[0]);
            company.setIdcardpicture(arr[1]);
            company.setIdcardpicturefan(arr[2]);




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

          /*  try {
                String code = HttpClient.togetString(cphone);
                rabbitTemplate.convertAndSend("messageskey",code);

            } catch (IOException e) {
                e.printStackTrace();
            }
*/
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

               List<Company> statuslist = companyDao.querycompanyloginstatus(phone,companytablename);

                String id = statuslist.get(0).getId();
               if(statuslist.get(0).getCheckstatus()==1){


                   return "3,"+id;

               }else if(statuslist.get(0).getCheckstatus()==2){

                   return "2,"+id;


               }
              return "4";

            }
        } else if ("15".equals(cphone)) {
            company.setCompanytablename("t_company15");
            String companytablename = company.getCompanytablename();
            List<Company> list = companyDao.querycompanylogin(phone, password, companytablename);
            if (list != null && list.size() > 0) {

                List<Company> statuslist = companyDao.querycompanyloginstatus(phone,companytablename);

                String id = statuslist.get(0).getId();
                if(statuslist.get(0).getCheckstatus()==1){


                    return "3,"+id;

                }else if(statuslist.get(0).getCheckstatus()==2){

                    return "2,"+id;


                }
                return "4";
            }
        } else if ("17".equals(cphone)) {
            company.setCompanytablename("t_company17");
            String companytablename = company.getCompanytablename();
            List<Company> list = companyDao.querycompanylogin(phone, password, companytablename);
            if (list != null && list.size() > 0) {
                List<Company> statuslist = companyDao.querycompanyloginstatus(phone,companytablename);

                String id = statuslist.get(0).getId();
                if(statuslist.get(0).getCheckstatus()==1){


                    return "3,"+id;

                }else if(statuslist.get(0).getCheckstatus()==2){

                    return "2,"+id;


                }
                return "4";
            }
        } else {
            company.setCompanytablename("t_company");
            String companytablename = company.getCompanytablename();
            List<Company> list = companyDao.querycompanylogin(phone, password, companytablename);
            if (list != null && list.size() > 0) {

                List<Company> statuslist = companyDao.querycompanyloginstatus(phone,companytablename);

                String id = statuslist.get(0).getId();
                if(statuslist.get(0).getCheckstatus()==1){


                    return "3,"+id;

                }else if(statuslist.get(0).getCheckstatus()==2){

                    return "2,"+id;


                }
                return "4";
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

    @Override
    public List<Company> queryCompanyeList(Integer page, Integer rows) {
        String [] arr ={"t_company","t_company13","t_company15","t_company17"};
        int start = (page-1)*rows;
        List<Company> companyList = new ArrayList<Company>();
        for (String biaoid: arr) {

            List<Company> list1 = companyDao.queryCompangList(start,rows,biaoid);
            for (Company company : list1) {
                companyList.add(company);
            }
        }
        return companyList;
    }

    @Override
    public long querycompanycount() {
        String [] arr ={"t_company","t_company13","t_company15","t_company17"};
        long count =0;
        long acount=0;
        for (String biaoid: arr) {
            acount = companyDao.queryCompanyCount(biaoid);
            count+=acount;
        }
        return count;
    }

    @Override
    public void updateCompanyStatus(String s) {
        String id = s;
        String [] arr ={"t_company","t_company13","t_company15","t_company17"};
        for (String biaoid: arr) {
            companyDao.updateCompanyStatus(id,biaoid);
        }

    }

    @Override
    public List<Company> selectCompanyeTwoList(Integer page, Integer rows) {
        String [] arr ={"t_company","t_company13","t_company15","t_company17"};
        int start = (page-1)*rows;
        List<Company> companyList = new ArrayList<Company>();
        for (String biaoid: arr) {

            List<Company> list1 = companyDao.selectCompangTwoList(start,rows,biaoid);
            for (Company company : list1) {
                companyList.add(company);
            }
        }
        return companyList;
    }

    @Override
    public long selectcompanyTwocount() {
        String [] arr ={"t_company","t_company13","t_company15","t_company17"};
        long count =0;
        long acount=0;
        for (String biaoid: arr) {
            acount = companyDao.selectCompanyCount(biaoid);
            count+=acount;
        }
        return count;
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