package com.example.gymnasium.service;

import com.example.gymnasium.entity.notice;
import com.example.gymnasium.mapper.noticeMapper;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class noticeService {
    @Autowired
    private noticeMapper noticemapper;

    public List<notice> findAll(){
        return noticemapper.findAll();
    }

    public Integer addNotice(Integer noticeid, Date noticetime, String content, String title,
                             Integer state, Integer venueid, Integer noticeisdel){
        return noticemapper.addNotice(noticeid,noticetime,content,title,state,venueid,noticeisdel);
    }

    public Integer delNotice(Integer noticeid){
        return noticemapper.delNotice(noticeid);
    }

    public Integer updNoticeContent(Integer noticeid,String content){
        return noticemapper.updNoticeContent(noticeid,content);
    }

    public Integer updNoticeTitle(Integer noticeid,String title){
        return noticemapper.updNoticeTitle(noticeid,title);
    }

    public Integer updNoticeState(Integer noticeid,Integer state){
        return noticemapper.updNoticeState(noticeid,state);
    }

    public Integer updNoticeLocation(Integer noticeid,Integer venueid){
        Integer state = noticemapper.findNoticeState(noticeid);
        if(state==0) noticemapper.updNoticeState(noticeid,1);
        return noticemapper.updNoticeVenueid(noticeid,venueid);
    }

}
