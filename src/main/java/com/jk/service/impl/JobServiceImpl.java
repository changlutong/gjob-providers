/**
 * Copyright (C), 2018-2018, jk有限公司
 * FileName: JobServiceImpl
 * Author:  常路通
 * Date:     2018/5/15 10:54
 * Description: 职位逻辑实现
 * /**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: JobServiceImpl
 * Author:   chang
 * Date:     2018/5/15 10:54
 * Description: 职位逻辑实现
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jk.service.impl;

import com.jk.mapper.IJobMapper;
import com.jk.service.IJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.management.snmp.jvmmib.JvmOSMBean;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈职位逻辑实现〉
 *
 * @author chang
 * @create 2018/5/15
 * @since 1.0.0
 */
@Service("jobService")
public class JobServiceImpl implements IJobService {
    @Autowired
    private IJobMapper jobMapper;

    @Override
    public List<String> getJobName() {
        List<String> list= jobMapper.getJobName();
        List<String> list01=jobMapper.getJobName01();
        for (String str:list01
             ) {
            list.add(str);
        }
        return list;
    }
}