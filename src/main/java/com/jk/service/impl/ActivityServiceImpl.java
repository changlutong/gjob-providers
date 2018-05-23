/**
 * Copyright (C), 2015-2018, 龙盾技术科技有限公司
 * FileName: ActivityServiceImpl
 * Author:   ASUS
 * Date:     2018/5/18 10:23
 * Description: 实现类
 * History:
 * <author>          <e_mail>
 * 孙国锦          getElements@outlook.com
 */
package com.jk.service.impl;

import com.jk.mapper.IActivityMapper;
import com.jk.model.Activity;
import com.jk.model.BankType;
import com.jk.service.IActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈实现类〉
 *
 * @author ASUS
 * @create 2018/5/18
 * @since 1.0.0
 */
@Service("activityService")
public class ActivityServiceImpl implements IActivityService {

    @Autowired
    private IActivityMapper activityMapper;

    @Override
    public List<Activity> queryActivityList(Integer page, Integer rows) {
        int start = (page-1)*rows;
        return activityMapper.queryActivityList(start,rows);
    }

    @Override
    public long queryActivitycount() {
        return activityMapper.queryActivityCount();
    }

    @Override
    public void saveActivity(Activity activity) {
        activityMapper.saveActivity(activity);
    }

    @Override
    public void deleteActivity(int i) {
        activityMapper.deleteActivity(i);
    }
}