package com.example.gymnasium.mapper;

import com.example.gymnasium.entity.reservation;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.*;

import java.sql.Date;
import java.util.List;

@Mapper
public interface reservationMapper {
    int deleteByPrimaryKey(Integer reservationid);

    int insert(reservation record);

    int insertSelective(reservation record);

    reservation selectByPrimaryKey(Integer reservationid);

    int updateByPrimaryKeySelective(reservation record);

    int updateByPrimaryKey(reservation record);

    @Select("select * from reservation")
    List<reservation> findAll();

    @Select("select * from reservation where reservationid=#{reservationid}")
    reservation findById(Integer reservationid);

    @Select("select * from reservation where venueid=#{venueid}")
    List<reservation> findByVenueid(Integer venueid);

    @Select("select * from reservation where courtid=#{courtid}")
    List<reservation> findByCourtid(Integer courtid);

    @Select("select * from reservation where courtid=#{courtid} and date<=#{date}")
    List<reservation> findByCourtDate(@Param("courtid")Integer courtid, @Param("date")Date date);

    @Select("select * from reservation where venueid=#{venueid} and date<=#{date}")
    List<reservation> findByVenueDate(@Param("venueid")Integer venueid, @Param("date")Date date);

    @Insert("insert into reservation values(#{reservationid},#{venueid},#{courtid},#{date},#{state})")
    Integer addReservation(@Param("reservationid")Integer reservationid,@Param("venueid")Integer venueid,
                           @Param("courtid")Integer courtid,@Param("date")Date date,
                           @Param("state")String state);

    @Update("update reservation set state=#{state} where reservationid=#{reservationid}")
    Integer updateReservationState(@Param("reservationid")Integer reservationid,@Param("state")String state);

    @Select("select * from reservation where courtid=#{id} and date=#{date}")
    reservation findState(@Param("id")int id, @Param("date")String date);

    //对vdstate进行更新
    @Update("update reservation set state=#{state}  where courtid=#{courtid} and date=#{date}")
    Integer saveState(@Param("state") String state, @Param("courtid")Integer courtid, @Param("date") String date);

}