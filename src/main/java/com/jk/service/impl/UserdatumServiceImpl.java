package com.jk.service.impl;

import com.jk.mapper.IUserdatumMapper;
import com.jk.model.*;
import com.jk.service.IUserdatumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by ASUS on 2018/5/15.
 */
@Service("iUserdatum")
public class UserdatumServiceImpl implements IUserdatumService {
    // public static final Logger logger = LoggerFactory.getLogger(UserdatumServiceImpl.class);

    @Autowired
    private IUserdatumMapper iuserdatumMapper;

    @Override
    public void userdatumgrxx(UsergDatum usergDatum) {
        int count = iuserdatumMapper.userdatumgrxxSize(usergDatum);
        if (count > 0) {
            //如果当前用户存在则去修改
            iuserdatumMapper.updateuserdatumgrxx(usergDatum);
        } else {
            //反正新增
            iuserdatumMapper.userdatumgrxx(usergDatum);
        }
    }

    @Override
    public void adduserzwpj(Integer zwpjzt, String zwpjnr, String datumid) {
        iuserdatumMapper.adduserzwpj(zwpjzt, zwpjnr, datumid);
    }

    @Override
    public void adduserqzyx(Userqzyx qzyx) {
        //System.out.println(userqzyx.toString());
        iuserdatumMapper.adduserqzyx(qzyx);
    }

    @Override
    public void addusergzjy(Usergzjy usergzjy) {
        System.out.println(usergzjy.toString());
        iuserdatumMapper.addusergzjy(usergzjy);
    }

    @Override
    public void adduserjybj(Userjybj jybjfrom) {
        iuserdatumMapper.adduserjybj(jybjfrom);
    }

    @Override
    public void adduserpxjl(Userpxjl pxjlfrom) {
        iuserdatumMapper.adduserpxjl(pxjlfrom);
    }

    @Override
    public void adduseryynl(Useryynl yynlform) {
        iuserdatumMapper.adduseryynl(yynlform);
    }

    @Override
    public void adduserqita(Userqt qtxxform) {
        iuserdatumMapper.adduserqita(qtxxform);
    }

    @Override
    public List<Map<String, Object>> queryusergrxx(String userid) {
        List<Map<String, Object>> list = iuserdatumMapper.queryusergrxx(userid);
        return list;
    }

    @Override
    public List<Map<String, Object>> queryuserzwpj(String userid) {
        List<Map<String, Object>> list = iuserdatumMapper.queryuserzwpj(userid);
        return list;
    }

    @Override
    public List<Map<String, Object>> queryuserqzyx(String userid) {
        List<Map<String, Object>> list = iuserdatumMapper.queryuserqzyx(userid);
        return list;
    }

    @Override
    public List<Map<String, Object>> queryusergzjy(String userid) {
        List<Map<String, Object>> list = iuserdatumMapper.queryusergzjy(userid);
        return list;
    }

    @Override
    public List<Map<String, Object>> queryuserjybj(String userid) {
        List<Map<String, Object>> list = iuserdatumMapper.queryuserjybj(userid);
        return list;
    }

    @Override
    public List<Map<String, Object>> queryuserpxjl(String userid) {
        List<Map<String, Object>> list = iuserdatumMapper.queryuserpxjl(userid);
        return list;
    }

    @Override
    public List<Map<String, Object>> queryuseryynl(String userid) {
        List<Map<String, Object>> list = iuserdatumMapper.queryuseryynl(userid);
        return list;
    }

    @Override
    public void deleteuserjybj(String userid, Integer id) {
        iuserdatumMapper.deleteuserjybj(userid, id);
    }

    @Override
    public void deleteuserpxjl(String userid, Integer id) {
        iuserdatumMapper.deleteuserpxjl(userid, id);
    }

    @Override
    public void deleteuseryynl(String userid, Integer id) {
        iuserdatumMapper.deleteuseryynl(userid, id);
    }

    @Override
    public void addusertp(String userid, String url, String userjs) {
        iuserdatumMapper.addusertp(userid, url, userjs);
    }

    @Override
    public List<Map<String, Object>> queryuserqita(String userid) {
        List<Map<String, Object>> list = iuserdatumMapper.queryuserqita(userid);
        return list;
    }

    @Override
    public void addPhoto(String userid, String url) {
            iuserdatumMapper.addPhoto(userid, url);
    }

    @Override
    public List<Map<String, Object>> queryuseryhzp(String userid) {
        List<Map<String, Object>> list = iuserdatumMapper.queryuseryhzp(userid);
        return list;
    }

    @Override
    public int selectUsercount(String loginname, String password) {

        return iuserdatumMapper.selectUsercount(loginname,password);
    }

    @Override
    public List<Map<String, Object>> selectalljobto(String userid) {
        List<Map<String, Object>> list = iuserdatumMapper.selectalljobto(userid);
        return list;
    }

    @Override
    public List<Tpersonal> selectUserlogin(String loginname, String password) {
       List <Tpersonal> list = iuserdatumMapper.selectUserlogin(loginname,password);
        return list;
    }
}