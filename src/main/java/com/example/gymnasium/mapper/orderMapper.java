package com.example.gymnasium.mapper;

import com.example.gymnasium.entity.order;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface orderMapper {
    @Select("SELECT * FROM `order` order by maketime desc")
    List<order> returnAll();

    @Select("SELECT * FROM `order` where state=#{state}")
    List<order> returnByState(Integer state);
    @Select("SELECT * FROM `order` where userid=#{userid}")
    List<order> returnByUserId(Integer userid);

    @Select("SELECT * FROM `order` where userid=#{userid} and state=#{state}")
    List<order> returnByUserState(@Param("userid")Integer userid,@Param("state")Integer state);

    @Select("SELECT * FROM `order` where orderid=#{orderid}")
    order returnById(Integer orderid);

    @Delete("DELETE FROM `order` where orderid=#{id}")
    void delOrder(Integer id);

    @Update("UPDATE `order` set state=1 where orderid=#{orderid}")
    void changeToPaid(Integer orderid);

    @Update("UPDATE `order` set state=2 where orderid=#{orderid}")
    void toCancelled(Integer orderid);

    @Update("UPDATE `order` set state=3 where orderid=#{orderid}")
    void toFinished(Integer orderid);


    @Insert("insert into `order` ( venueid, courtid, ordernumber, userid, date,   begintime, endtime, state,  maketime, price)" +
            "values ( #{venueid}, #{courtid},  #{ordernumber}, #{userid}, #{date},  #{begintime}, #{endtime}, #{state} ,#{maketime},#{price})")
    int addOrder(order record);

    @Select("SELECT state FROM `order` WHERE courtid = #{courtid}" +
            "    and date = #{date}" +
            "     and begintime = #{begintime}" +
            "     and endtime= #{endtime}")
    Integer getOrderState(@Param("courtid")int courtid, @Param("date")String date, @Param("begintime")Integer begintime, @Param("endtime")Integer endtime);
}