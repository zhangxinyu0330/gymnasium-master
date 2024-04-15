package com.example.gymnasium.service;

import com.example.gymnasium.entity.venue;
import com.example.gymnasium.mapper.venueMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class venueService {
    @Autowired
    venueMapper venuemapper;

    public List<venue> findAll(){
        return venuemapper.findAll();
    }

    public Integer delVenue(Integer venueid){
        return venuemapper.delVenue(venueid);
    }

    public Integer addVenue(Integer venueid,String name,String introduction, byte[] picture){
        return venuemapper.addVenue(venueid,name,introduction,picture);
    }

    public venue findVenueById(Integer venueid){
        return venuemapper.findVenueById(venueid);
    }

    public Integer updVenueName(Integer venueid,String name){
        return venuemapper.updVenueName(venueid,name);
    }

    public Integer updVenueIntroduction(Integer venueid,String introduction){
        return venuemapper.updVenueIntoduction(venueid,introduction);
    }

    public Integer updVenuePicture(Integer venueid,byte[] picture){
        return venuemapper.updVenuePicture(venueid,picture);
    }
}
