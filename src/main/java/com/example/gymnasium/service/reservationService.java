package com.example.gymnasium.service;

import com.example.gymnasium.entity.reservation;
import com.example.gymnasium.mapper.reservationMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class reservationService {
    @Autowired
    private reservationMapper mapper;

    public List<reservation> findAll(){
        return mapper.findAll();
    }

    public reservation findById(Integer reservationid){
        return mapper.findById(reservationid);
    }

    public List<reservation> findByVenueid(Integer venueid){
        return mapper.findByVenueid(venueid);
    }

    public List<reservation> findByCourtid(Integer courtid){
        return mapper.findByCourtid(courtid);
    }

    public List<reservation> findByVenueDate(Integer venueid, Date date){
        return mapper.findByVenueDate(venueid,date);
    }

    public List<reservation> findByCourtDate(Integer courtid,Date date){
        return mapper.findByCourtDate(courtid,date);
    }

    public Integer addReservation(Integer reservationid, Integer venueid,Integer courtid, Date date,
                                  String state){
        return mapper.addReservation(reservationid,venueid,courtid,date,state);
    }

    public Integer updateReservationState(Integer reservationid,String state){
        return mapper.updateReservationState(reservationid,state);
    }

    public reservation findState(int id,String date){
        return mapper.findState(id,date);
    }
    public Integer saveState(String state, Integer courtid, String date){
        return mapper.saveState(state,courtid,date);
    }
}
