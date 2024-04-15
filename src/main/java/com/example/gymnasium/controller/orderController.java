
package com.example.gymnasium.controller;

import com.example.gymnasium.entity.court;
import com.example.gymnasium.entity.order;
import com.example.gymnasium.entity.rule;
import com.example.gymnasium.entity.user;
import com.example.gymnasium.service.*;
import com.example.gymnasium.tools.ResponseBody;
import com.example.gymnasium.utils.SnowFlake;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
        value = {"/order"},
        produces = {"application/json; charset=UTF-8"}
)
public class orderController {
    private SnowFlake snowFlake = new SnowFlake(2L, 3L);
    @Autowired
    private orderService orderService;
    @Autowired
    private ruleService ruleService;
    @Autowired
    private courtService courtService;
    @Autowired
    private venueService vs;
    @Autowired
    private userService us;

    public orderController() {
    }

    @GetMapping({"/findAll"})
    public ResponseBody<List<order>> getOrder() {
        return new ResponseBody(200, "success", this.orderService.returnAll());
    }

    @GetMapping({"/findByState"})
    public ResponseBody<List<order>> getOrderByState(Integer state) {
        return new ResponseBody(200, "success", this.orderService.returnByState(state));
    }
    @GetMapping({"/findByUserId"})
    public ResponseBody<List<order>> getOrderByUserId(Integer userid) {
        return new ResponseBody(200, "success", this.orderService.returnByUserId(userid));
    }
    @GetMapping({"/findUserOrderByState"})
    public ResponseBody<List<order>> getUserOrderByState(Integer userid ,Integer state) {
        return new ResponseBody(200, "success", this.orderService.returnByUserState(userid,state));
    }


    @GetMapping({"/findById"})
    public ResponseBody<order> getOrderById(Integer orderid) {
        return new ResponseBody(200, "success", this.orderService.returnById(orderid));
    }

    @GetMapping({"/del"})
    public ResponseBody<String> delOrder(Integer id) {
        this.orderService.delOrder(id);
        return new ResponseBody(200, "success", "delete successfully");
    }

    @PostMapping({"/add"})
    public ResponseBody<String> add(order record) {
        int i = this.orderService.addOrder(record);
        String s = Integer.toString(i);
        return new ResponseBody(200, "success", s);
    }

    @GetMapping({"/getstate"})
    public ResponseBody<String> getstate(int courtid, String date, Integer st, Integer ed) {
        int i = this.orderService.getOrderState(courtid, date, st, ed);
        String s = String.valueOf(i);
        return new ResponseBody(200, "success", s);
    }


    @GetMapping("/changeToPaid")
    public ResponseBody<String>  changeToPaid(Integer orderid){
        order order=this.orderService.returnById(orderid);
        if(order.getState()==0) {
        this.orderService.changeToPaid(orderid);
        return new ResponseBody(200, "success", "change to paid successfully");
        }
        else
            return new ResponseBody(200, "success", "Status change failed: The status of the order is not booked");


    }

    @GetMapping("/toCancelled")
    public ResponseBody<String>  toCancelled(Integer orderid){
        order order=this.orderService.returnById(orderid);
        if(order.getState()==0) {
            this.orderService.toCancelled(orderid);
            this.orderService.changeReservation(order);
            return new ResponseBody(200, "success", "change to cancelled successfully");
        }
        else
            return new ResponseBody(200, "success", "Status change failed: The status of the order is not booked");
    }

    @GetMapping("/toFinished")
    public ResponseBody<String>  toFinished(Integer orderid){
        order order=this.orderService.returnById(orderid);
        if(order.getState()==1) {
            this.orderService.toFinished(orderid);
            this.orderService.changeReservation(order);
            return new ResponseBody(200, "success", "change to finished successfully");
        }
        else
            return new ResponseBody(200, "success", "Status change failed: The status of the order is not paid");

    }



    @PostMapping({"/confirm"})
    public ResponseBody<Map<String, String>> confirmOrder(@RequestBody order order, @RequestParam int courtid) {
        Map<String, String> map = new HashMap();
        order.setOrdernumber(String.valueOf(this.snowFlake.nextId()));
        order.setCourtid(courtid);
        user user = this.us.getUserById(order.getUserid());
        order.setUserid(user.getUserid());
        order.setState(0);
        order.setMaketime(new Timestamp((new Date()).getTime()));
        //计算订单价格
        String money="10.25";
        int cid=order.getCourtid();
        List<court> courts=courtService.findAllByCourtid(cid);
        Integer ctype=courts.get(0).getCourttype();
        List<rule> rules=ruleService.findByCourttypeTime(ctype,order.getBegintime(),order.getEndtime());
        int span=order.getEndtime()-order.getBegintime();

        int p=18;

        for(int i=0;i<rules.size();i++){
            if(rules.get(i).getBegintime()>=p||rules.get(i).getHolidayornot()==1){
                //是节假日或者18点之后
                money=String.valueOf(rules.get(i).getPrice().multiply(rules.get(i).getDiscount()).multiply(BigDecimal.valueOf(span)));
            }
            else{
                //第1位为学生有卡，第2位为学生无卡，第3位为教师有卡，第4位为教师无卡，第5位为校外有卡，第6位为校外无卡
                String userStatus=rules.get(i).getUserstatus();
               int status=user.getStatus();  //1代表学生，2代表教职工，3代表校外用户
               if(status==1){
                   //如果order是学生生成的
                   if(userStatus.equals("100000") || userStatus.equals("010000")){ //是学生的规则
                       money=String.valueOf(rules.get(i).getPrice().multiply(BigDecimal.valueOf(span)));
                       break;
                   }
                   else
                       continue;
               }
               else if(status==2){
                   //如果order是教职工生成的
                   if(userStatus.equals("001000") || userStatus.equals("000100")){
                       money=String.valueOf(rules.get(i).getPrice().multiply(BigDecimal.valueOf(span)));
                       break;
                   }
                   else continue;
               }
               else if(status==3){
                   //如果order是校外用户生成的
                   if(userStatus.equals("000010") || userStatus.equals("000001")){
                       money=String.valueOf(rules.get(i).getPrice().multiply(BigDecimal.valueOf(span)));
                       break;
                   }
                   else continue;
               }



            }

        }


        order.setPrice(new BigDecimal(money));

        boolean res = this.orderService.isAddOrderSuccess(order);
        if (res) {
            map.put("info", "ok");
        } else {
            map.put("info", "error");
        }

        return new ResponseBody(200, "success", map);
    }
}

