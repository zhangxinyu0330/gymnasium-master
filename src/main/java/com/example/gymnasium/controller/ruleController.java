package com.example.gymnasium.controller;

import com.example.gymnasium.entity.reservation;
import com.example.gymnasium.entity.rule;
import com.example.gymnasium.service.ruleService;
import com.example.gymnasium.tools.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(value = "/rule", produces = "application/json; charset=UTF-8")
public class ruleController {
    @Autowired
    private ruleService service;

    @RequestMapping(value = "/findByCourttypeTime",method = RequestMethod.GET)
    public ResponseBody<List<rule>> findByCourttypeTime(Integer courttype, Integer begintime, Integer endtime){
        return new ResponseBody<>(200,"success",service.findByCourttypeTime(courttype,begintime,endtime));
    }

    @RequestMapping(value = "/addRule",method = RequestMethod.POST)
    public ResponseBody<Integer> addRule(@RequestBody rule rule){
        Integer ruleid=rule.getRuleid();
        Integer venueid=rule.getVenueid();
        Integer courttype=rule.getCourttype();
        Integer holidayornot=rule.getHolidayornot();
        Integer begintime=rule.getBegintime();
        Integer endtime=rule.getEndtime();
        Integer capacity=rule.getCapacity();
        BigDecimal price=rule.getPrice();
        BigDecimal discount=rule.getDiscount();
        String userstatus=rule.getUserstatus();
        Integer cardgrade=rule.getCardgrade();
        return new ResponseBody<>(200,"success",service.addRule(ruleid,venueid,courttype,holidayornot,begintime,endtime,capacity,
                price,discount,userstatus,cardgrade));
    }

    @RequestMapping(value = "/delRule",method = RequestMethod.POST)
    public ResponseBody<Integer> delRule(Integer ruleid){
        return new ResponseBody<>(200,"success",service.delRule(ruleid));
    }
}
