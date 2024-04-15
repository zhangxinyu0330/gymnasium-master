package com.example.gymnasium.service;


import com.example.gymnasium.entity.court;
import com.example.gymnasium.mapper.courtMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class courtService {
    @Autowired
    private courtMapper mapper;

    public List<court> findAll(){
        return mapper.findAll();
    }

    public List<court> findAllByVenueid(Integer venueid){
        return mapper.findAllByVenueid(venueid);
    }

    public List<court> findAllByCourtid(Integer courtid){
        return mapper.findAllByCourtid(courtid);
    }

    public Integer delCourt(Integer courtid){
        return mapper.delCourt(courtid);
    }

    public Integer updCourtName(Integer courtid,String name){
        return mapper.updCourtName(courtid,name);
    }

    public Integer addCourt(Integer courtid, Integer venueid,Integer courttype, String name,
                            BigDecimal courtx1,BigDecimal courty1,BigDecimal courtx2, BigDecimal courty2,
                            byte[] picture){
        return mapper.addCourt(courtid,venueid,courttype,name,courtx1,courty1,courtx2,courty2,picture);
    }

    public Integer updCourtLocation(Integer courtid,
                                    BigDecimal courtx1,BigDecimal courty1,BigDecimal courtx2, BigDecimal courty2)
    {
        return mapper.updCourtLocation(courtid,courtx1,courty1,courtx2,courty2);
    }

    public Integer updCourtPicture(Integer courtid,byte[] picture)
    {
        return mapper.updCourtPicture(courtid,picture);
    }

    public Integer updCourt(Integer courtid,Integer venueid,Integer courttype,String name,
                            BigDecimal courtx1,BigDecimal courty1,BigDecimal courtx2, BigDecimal courty2,
                            byte[] picture){
        return mapper.updCourt(courtid,venueid,courttype,name,courtx1,courty1,courtx2,courty2,picture);
    }
}
