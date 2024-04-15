package com.example.gymnasium.service;

import com.example.gymnasium.entity.order;
import com.example.gymnasium.entity.reservation;
import com.example.gymnasium.mapper.orderMapper;
import com.example.gymnasium.mapper.reservationMapper;
import com.example.gymnasium.mapper.userMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class orderService {
    @Autowired
    private userMapper userMapper;

    @Autowired
    private orderMapper orderMapper;

    @Autowired
    private reservationMapper reservationMapper;

    private static final ReentrantLock lock = new ReentrantLock();

    public List<order> returnAll(){

        return orderMapper.returnAll();
    }

    public List<order> returnByState(Integer state){
        return orderMapper.returnByState(state);
    }
    public List<order> returnByUserId(Integer userid){
        return orderMapper.returnByUserId(userid);
    }
    public List<order> returnByUserState(Integer userid,Integer state){
        return orderMapper.returnByUserState(userid,state);
    }

    public order returnById(Integer orderid){
        return orderMapper.returnById(orderid);
    }
    public void delOrder(Integer id){
        orderMapper.delOrder(id);
    }

    public void changeToPaid(Integer orderid){
        orderMapper.changeToPaid(orderid);
    }
    public void toCancelled(Integer orderid){
        orderMapper.toCancelled(orderid);
    }
    public void toFinished(Integer orderid){
        orderMapper.toFinished(orderid);
    }

    public int addOrder(order record){

        return orderMapper.addOrder(record);
    }
    public int getOrderState(int courtid,String date, Integer st,Integer ed){

        return orderMapper.getOrderState(courtid,date,st,ed);
    }

    public void changeReservation(order order){
        Boolean flag = false;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String  currentDate =  format.format( order.getDate());
        //1.修改数据库库状态 可以抽取出来这里不抽取
        reservation reservation = reservationMapper.findState(order.getCourtid(), currentDate);
        //获取开始结束时间
        Integer order_st = order.getBegintime();
        Integer order_ed=order.getEndtime();
        int gap=order_ed-order_st;
        String vdstate_st = reservation.getState();

        char[] ch = vdstate_st.toCharArray();
        for(int k=0;k<gap;k++) {
            for (int i = 0; i < ch.length; i++) {
                if (i == order_st+k) {
                    //将当前状态改为未被预约
                    ch[i] = '0';
                }
            }
        }
        String new_vdstate_st = String.valueOf(ch);

       reservationMapper.saveState(new_vdstate_st,order.getCourtid(), currentDate);
    }



    /*
     * 加同步锁判断是否订单生成成功  加事务保证写入订单和修改状态同时执行
     */
    @Transactional
    public boolean isAddOrderSuccess(order order){
        Boolean flag = false;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String  currentDate =  format.format( order.getDate());


        //判断当前场馆状态是否是可预约状态
        Integer orderState = orderMapper.getOrderState(order.getCourtid(), currentDate, order.getBegintime(), order.getEndtime());



        if(orderState != null){
            if(orderState==0||orderState==1||orderState==3)
            return false;
        }

        //如果可预约，表示一部分线程通过上面的限制，加锁
        lock.lock();
        try{
            ///双重检验锁，因为前面只检查了一部分线程
            //判断当前场馆状态是否是可预约状态
            Integer orderState1 = orderMapper.getOrderState(order.getCourtid(), currentDate, order.getBegintime(), order.getEndtime());
            //如果当前这个场馆的订单已经生成
            if(orderState1 != null){
                if(orderState==0||orderState==1||orderState==3)
                return false;
            }

            int res = orderMapper.addOrder(order);

            //表示预约成功
            if(res > 0){
                //1.修改数据库库状态 可以抽取出来这里不抽取
                reservation reservation = reservationMapper.findState(order.getCourtid(), currentDate);
                //获取开始结束时间
                Integer order_st = order.getBegintime();
                Integer order_ed=order.getEndtime();
                int gap=order_ed-order_st;
                String vdstate_st = reservation.getState();

                     char[] ch = vdstate_st.toCharArray();
                for(int k=0;k<gap;k++) {
                    for (int i = 0; i < ch.length; i++) {
                        if (i == order_st+k) {
                            //将当前状态改为黄色，表示已预约
                            ch[i] = '2';
                        }
                    }
                }
                     String new_vdstate_st = String.valueOf(ch);

                //vdstate.setVdstate_St(new_vdstate_st);
                //保存分时状态
                Integer saveVdstate = reservationMapper.saveState(new_vdstate_st,order.getCourtid(), currentDate);
                if(saveVdstate > 0){
                    flag = true;
                }
            }else{
                //订单生成失败，直接返回
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("订单生成异常");
        }finally {
            lock.unlock();
        }

        return flag;
    }


}
