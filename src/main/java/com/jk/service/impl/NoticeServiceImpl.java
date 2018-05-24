package com.jk.service.impl;

import com.jk.mapper.NoticeMapper;
import com.jk.model.Notice;
import com.jk.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/05/17.
 */
@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;
    Date date=new Date();
    @Override
    public List<Notice> getnotice(Notice notice) {
        return noticeMapper.getnotice(notice);
    }

    @Override
    public List<Notice> querylistnotice(Integer page, Integer rows, Notice notice) {
        page = (page-1)*rows;
        return noticeMapper.querylistnotice(  page,   rows,   notice);
    }

    @Override
    public int addnotice(Notice notice) {

        return noticeMapper.addnotice(notice.getNoticeid(),notice.getNoticename(),notice.getNoticedate(),notice.getNoticebulletin());

    }


    public void deletenotice(String ids) {
        String[] arr = 	ids.split(",");

        for (int i = 0; i < arr.length; i++) {

            noticeMapper.deletenotice(Integer.parseInt(arr[i]));
        }
    }

    @Override
    public Notice upNotice(Integer noticeid) {
        return noticeMapper.upNotice(noticeid);
    }

    @Override
    public int updateNotice(Notice notice) {
        return noticeMapper.updateNotice(notice);
    }
    //==============================================================================================================
    @Override
    public List<Notice> querynotices(Notice notice) {
        return noticeMapper.querynotices(notice);
    }

    @Override
    public List<Notice> noticesids(Integer noticeid) {
        return noticeMapper.noticesids(noticeid);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
