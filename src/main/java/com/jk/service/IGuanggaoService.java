/**
 * Copyright (C), 2015-2018, 龙盾技术科技有限公司
 * FileName: IGuanggaoService
 * Author:   ASUS
 * Date:     2018/5/14 19:29
 * Description:
 * History:
 * <author>          <e_mail>
 * 孙国锦          getElements@outlook.com
 */
package com.jk.service;

import com.alibaba.dubbo.common.json.JSONObject;
import com.jk.model.Guanggao;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author ASUS
 * @create 2018/5/14
 * @since 1.0.0
 */
public interface IGuanggaoService {

    /**
     * 广告分页查询    孙国锦
     */
    JSONObject selectGuanggao(Integer page, Integer rows);

    /**
     * 新增广告    孙国锦
     */
    void saveguanggao(Guanggao guanggao);

    /**
     * 广告审核   孙国锦
     */
    void updatastatus(String ggid);

    /**
     * 删除广告  孙国锦
     */
    void deleteguanggao(String gid);


}