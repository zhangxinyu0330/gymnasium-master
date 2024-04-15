package com.example.gymnasium.service;

import com.example.gymnasium.entity.rule;
import com.example.gymnasium.mapper.ruleMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ruleService {
    @Autowired
    private ruleMapper mapper;

    public List<rule> findByCourttypeTime(Integer courttype, Integer begintime, Integer endtime){
        return mapper.findByCourttypeTime(courttype,begintime,endtime);
    }

    public Integer addRule(Integer ruleid, Integer venueid,Integer courttype,Integer holidayornot,
                           Integer begintime, Integer endtime,Integer capacity,BigDecimal price,
                           BigDecimal discount,String userstatus,Integer cardgrade){
        return mapper.addRule(ruleid,venueid,courttype,holidayornot,begintime,endtime,capacity,
                price,discount,userstatus,cardgrade);
    }

    public Integer delRule(Integer ruleid){
        return mapper.delRule(ruleid);
    }
}
