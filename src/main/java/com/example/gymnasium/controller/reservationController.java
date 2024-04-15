package com.example.gymnasium.controller;

import com.example.gymnasium.entity.reservation;
import com.example.gymnasium.service.reservationService;
import com.example.gymnasium.tools.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.GetMapping;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/reservation", produces = "application/json; charset=UTF-8")
public class reservationController {
    @Autowired
    private reservationService service;

    @RequestMapping(value = "/getAllReservation",method = RequestMethod.GET)
    public ResponseBody<List<reservation>> findAll(){
        return new ResponseBody<>(200,"success",service.findAll());
    }

    @RequestMapping(value = "/getAllReservationById",method = RequestMethod.GET)
    public ResponseBody<reservation> findById(Integer reservationid){
        return new ResponseBody<>(200,"success",service.findById(reservationid));
    }

    @RequestMapping(value = "/getAllReservationByVenueid",method = RequestMethod.GET)
    public ResponseBody<List<reservation>> findByVenueid(Integer venueid){
        return new ResponseBody<>(200,"success",service.findByVenueid(venueid));
    }

    @RequestMapping(value = "/getAllReservationByCourtid",method = RequestMethod.GET)
    public ResponseBody<List<reservation>> findByCourtid(Integer courtid){
        return new ResponseBody<>(200,"success",service.findByCourtid(courtid));
    }

    @RequestMapping(value = "/getAllReservationByVenueDate",method = RequestMethod.GET)
    public ResponseBody<List<reservation>> findByVenueDate(Integer venueid, Date date){
        return new ResponseBody<>(200,"success",service.findByVenueDate(venueid,date));
    }

    @RequestMapping(value = "/getAllReservationByCourtDate",method = RequestMethod.GET)
    public ResponseBody<List<reservation>> findByCourtDate(Integer courtid, Date date){
        return new ResponseBody<>(200,"success",service.findByCourtDate(courtid,date));
    }

    @RequestMapping(value = "/getAllReservationByCourtDate",method = RequestMethod.POST)
    public ResponseBody<Integer> addReservation(@RequestBody reservation reservation){
        Integer reservationid=reservation.getReservationid();
        Integer venueid=reservation.getVenueid();
        Integer courtid=reservation.getCourtid();
        Date date=reservation.getDate();
        String state=reservation.getState();
        return new ResponseBody<>(200,"success",service.addReservation(reservationid,
                venueid,courtid,date,state));
    }

    @RequestMapping(value = "/updateReservationState",method = RequestMethod.POST)
    public ResponseBody<Integer> updateReservationState(Integer reservationid,String state){
        return new ResponseBody<>(200,"success",
                service.updateReservationState(reservationid,state));
    }

    @GetMapping("/findState")
    public ResponseBody<reservation >findState(int courtid,String date){

        return new ResponseBody<>(200,"success",service.findState(courtid,date));
    }

    @GetMapping("/saveState")
    public ResponseBody<Integer >saveState(String state, Integer courtid, String date){

        return new ResponseBody<>(200,"success",service.saveState(state,courtid,date));
    }


}
