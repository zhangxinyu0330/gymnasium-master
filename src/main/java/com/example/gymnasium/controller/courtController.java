package com.example.gymnasium.controller;

import com.example.gymnasium.entity.court;
import com.example.gymnasium.entity.venue;
import com.example.gymnasium.service.courtService;
import com.example.gymnasium.tools.ImageConversion;
import com.example.gymnasium.tools.ResponseBody;
import lombok.val;
import org.apache.ibatis.javassist.bytecode.ByteArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/court", produces = "application/json; charset=UTF-8")
public class courtController {
    @Autowired
    private courtService service;

    @RequestMapping(value = "/getAllCourt",method = RequestMethod.GET)
    public ResponseBody<List<court>> findAll(){
        List<court> courtList=service.findAll();
        return new ResponseBody<>(200,"success",courtList);
    }

    @RequestMapping(value = "/getAllCourtByVenueid",method = RequestMethod.GET)
    public ResponseBody<List<court>> findAllByVenueid(Integer venueid){
        List<court> courtList=service.findAllByVenueid(venueid);
        return new ResponseBody<>(200,"success",courtList);
    }

    @RequestMapping(value = "/getAllCourtByCourtid",method = RequestMethod.GET)
    public ResponseBody<List<court>> findAllByCourtid(Integer courtid){
        List<court> courtList=service.findAllByCourtid(courtid);
        return new ResponseBody<>(200,"success",courtList);
    }

    @RequestMapping(value = "/delCourt",method = RequestMethod.POST)
    public ResponseBody<Integer> delCourt(Integer courtid){
        return new ResponseBody<>(200,"success",service.delCourt(courtid));
    }

    @RequestMapping(value = "/updCourtName",method = RequestMethod.POST)
    public ResponseBody<Integer> updCourtName(Integer courtid,String name){
        return new ResponseBody<>(200,"success",service.updCourtName(courtid,name));
    }

    @RequestMapping(value = "/addCourt",method = RequestMethod.POST)
    public ResponseBody<Integer> addCourt(Integer courtid, Integer venueid, Integer courttype, String name,
                                          BigDecimal courtx1, BigDecimal courty1, BigDecimal courtx2, BigDecimal courty2,
                                          MultipartFile pic) throws IOException {
        byte[] picture= pic.getBytes();
        return new ResponseBody<>(200,"success",service.addCourt(courtid,venueid,courttype,name,courtx1,
                courty1,courtx2,courty2,picture));
    }

    @RequestMapping(value = "/updCourtLocation",method = RequestMethod.POST)
    public ResponseBody<Integer> updCourtLocation(Integer courtid,
                                                  BigDecimal courtx1,BigDecimal courty1,BigDecimal courtx2, BigDecimal courty2){
        return new ResponseBody<>(200,"success",service.updCourtLocation(courtid,courtx1,courty1,courtx2,courty2));
    }

    @RequestMapping(value = "/updCourtPicture",method = RequestMethod.POST)
    public ResponseBody<Integer> updCourtPicture(Integer courtid, MultipartFile pic) throws IOException {
        byte[] picture= pic.getBytes();
        return new ResponseBody<>(200,"success",service.updCourtPicture(courtid,picture));
    }


}
