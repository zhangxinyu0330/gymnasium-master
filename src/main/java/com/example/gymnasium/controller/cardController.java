package com.example.gymnasium.controller;

import com.example.gymnasium.entity.card;
import com.example.gymnasium.service.cardService;
import com.example.gymnasium.tools.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping(value = "/card", produces = "application/json; charset=UTF-8")
public class cardController {
    @Autowired
    private cardService service;

    @RequestMapping(value = "/findByCardNum",method = RequestMethod.GET)
    public ResponseBody<card> findByCardNum(Integer cardnumber){
        return new ResponseBody<>(200,"success",service.findByCardNum(cardnumber));
    }

    @RequestMapping(value = "/findByCardId",method = RequestMethod.GET)
    public ResponseBody<card> findByCardId(Integer cardid){
        return new ResponseBody<>(200,"success",service.findByCardId(cardid));
    }

    @RequestMapping(value = "/updCardUser",method = RequestMethod.POST)
    public ResponseBody<Integer> updCardUser(Integer cardid,Integer userid){
        return new ResponseBody<>(200,"success",service.updCardUser(cardid,userid));
    }

    @RequestMapping(value = "/updCardAccount",method = RequestMethod.POST)
    public ResponseBody<Integer> updCardAccount(Integer cardid, BigDecimal account){
        return new ResponseBody<>(200,"success",service.updCardAccount(cardid,account));
    }

    @RequestMapping(value = "/updCard",method = RequestMethod.POST)
    public ResponseBody<Integer> updCard(@RequestBody card card){
        Integer cardid=card.getCardid();
        BigDecimal account=card.getAccount();
        Integer point=card.getPoint();
        Integer grade=card.getGrade();
        return new ResponseBody<>(200,"success",service.updCard(cardid,account,point,grade));
    }

}
