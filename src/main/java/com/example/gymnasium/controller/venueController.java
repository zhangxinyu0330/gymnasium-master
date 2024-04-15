package com.example.gymnasium.controller;

import com.example.gymnasium.entity.venue;
import com.example.gymnasium.service.venueService;
import com.example.gymnasium.tools.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/venue", produces = "application/json; charset=UTF-8")
public class venueController {
    @Autowired
    private venueService venueservice;

    @RequestMapping(value = "/getAllVenue",method = RequestMethod.GET)
    public ResponseBody<List<venue>> findAll(){
        List<venue> venueList=venueservice.findAll();
        return new ResponseBody<>(200,"success",venueList);
    }

    @RequestMapping(value = "/delVenue",method = RequestMethod.POST)
    public ResponseBody<Integer> delVenue(Integer venueid){
        return new ResponseBody<>(200,"success",venueservice.delVenue(venueid));
    }

    @RequestMapping(value = "/addVenue",method = RequestMethod.POST)
    public ResponseBody<Integer> addVenue(Integer venueid,String name,String introduction, MultipartFile pic) throws IOException {
        byte[] picture= pic.getBytes();
        return new ResponseBody<>(200,"success",venueservice.addVenue(venueid,name,introduction,picture));
    }

    @RequestMapping(value = "/findVenueById",method = RequestMethod.GET)
    public ResponseBody<venue> findVenueById(Integer venueid){
        venue temp=venueservice.findVenueById(venueid);
        return new ResponseBody<>(200,"success",temp);
    }

    @RequestMapping(value = "/updVenueName",method = RequestMethod.POST)
    public ResponseBody<Integer> updVenueName(Integer venueid,String name){
        return new ResponseBody<>(200,"success",venueservice.updVenueName(venueid,name));
    }

    @RequestMapping(value = "/updVenueIntroduction",method = RequestMethod.POST)
    public ResponseBody<Integer> updVenueIntroduction(Integer venueid,String introduction){
        return new ResponseBody<>(200,"success",venueservice.updVenueIntroduction(venueid,introduction));
    }

    @RequestMapping(value = "/updVenuePicture",method = RequestMethod.POST)
    public ResponseBody<Integer> updVenuePicture(Integer venueid,MultipartFile pic) throws IOException {
        byte[] picture= pic.getBytes();
        return new ResponseBody<>(200,"success",venueservice.updVenuePicture(venueid,picture));
    }
}
