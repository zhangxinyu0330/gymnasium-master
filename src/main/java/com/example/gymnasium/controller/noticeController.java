package com.example.gymnasium.controller;

import com.example.gymnasium.entity.notice;
import com.example.gymnasium.service.noticeService;
import com.example.gymnasium.tools.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/notice", produces = "application/json; charset=UTF-8")
public class noticeController {
    @Autowired
    private noticeService noticeservice;

    @RequestMapping(value = "/getAllNotice",method = RequestMethod.GET)
    public ResponseBody<List<notice>> getAllType(){
        return new ResponseBody<>(200,"success",noticeservice.findAll());
    }

    @RequestMapping(value = "/addNotice",method = RequestMethod.POST)
    public ResponseBody<Integer> addNotice(@RequestBody notice notice){
        Integer noticeid=notice.getNoticeid();
        Date noticetime=notice.getNoticetime();
        String content=notice.getContent();
        String title=notice.getTitle();
        Integer state=notice.getState();
        Integer venueid=notice.getVenueid();
        return new ResponseBody<>(200,"success",noticeservice.addNotice(noticeid,
                noticetime,content,title,state,venueid,0));
    }

    @RequestMapping(value = "/delNotice",method = RequestMethod.POST)
    public ResponseBody<Integer> delNotice(Integer noticeid){
        return new ResponseBody<>(200,"success",noticeservice.delNotice(noticeid));
    }

    @RequestMapping(value = "/updNoticeContent",method = RequestMethod.POST)
    public ResponseBody<Integer> updNoticeContent(Integer noticeid, String content){
        return new ResponseBody<>(200,"success",noticeservice.updNoticeContent(noticeid,content));
    }

    @RequestMapping(value = "/updNoticeTitle",method = RequestMethod.POST)
    public ResponseBody<Integer> updNoticeTitle(Integer noticeid, String title){
        return new ResponseBody<>(200,"success",noticeservice.updNoticeTitle(noticeid,title));
    }

    @RequestMapping(value = "/updNoticeState",method = RequestMethod.POST)
    public ResponseBody<Integer> updNoticeState(Integer noticeid,Integer state) {
        return new ResponseBody<>(200, "success", noticeservice.updNoticeState(noticeid,state));
    }

    @RequestMapping(value = "/updNoticeLocation",method = RequestMethod.POST)
    public ResponseBody<Integer> updNoticeLocation(Integer noticeid, Integer venueid) {
        return new ResponseBody<>(200, "success", noticeservice.updNoticeLocation(noticeid, venueid));
    }

}
