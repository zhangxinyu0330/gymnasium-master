package com.example.gymnasium.service;

import com.example.gymnasium.entity.card;
import com.example.gymnasium.mapper.cardMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class cardService {
    @Autowired
    private cardMapper mapper;

    public card findByCardNum(Integer cardnumber){
        return mapper.findByCardNum(cardnumber);
    }

    public card findByCardId(Integer cardid){
        return mapper.findByCardId(cardid);
    }

    public Integer updCardUser(Integer cardid,Integer userid){
        card temp=mapper.findByCardId(cardid);
        if(temp.getUserid()==null) return mapper.updCardUser(cardid,userid);
        else return 0;
    }

    public Integer updCardAccount(Integer cardid, BigDecimal account){
        return mapper.updCardAccount(cardid,account);
    }

    public Integer updCard(Integer cardid, BigDecimal account,Integer point, Integer grade)
    {
        return mapper.updCard(cardid,account,point,grade);
    }
}
