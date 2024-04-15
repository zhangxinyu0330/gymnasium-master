package com.example.gymnasium.mapper;

import com.example.gymnasium.entity.court;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface courtMapper {
    int deleteByPrimaryKey(Integer courtid);

    int insert(court record);

    int insertSelective(court record);

    court selectByPrimaryKey(Integer courtid);

    int updateByPrimaryKeySelective(court record);

    int updateByPrimaryKeyWithBLOBs(court record);

    int updateByPrimaryKey(court record);

    @Select("select * from court where courtisdel=0")
    List<court> findAll();

    @Select("select * from court where venueid=#{venueid} and courtisdel=0")
    List<court> findAllByVenueid(Integer venueid);

    @Select("select * from court where courtid=#{courtid} and courtisdel=0")
    List<court> findAllByCourtid(Integer courtid);

    @Update("update court set courtisdel=1 where courtid=#{courtid}")
    Integer delCourt(Integer courtid);

    @Update("update court set name=#{name} where courtid=#{courtid}")
    Integer updCourtName(@Param("courtid")Integer courtid, @Param("name")String name);

    @Insert("insert into court values(#{courtid},#{venueid},#{courttype},#{name},0," +
            "#{courtx1},#{courty1},#{courtx2},#{courty2},#{picture})")
    Integer addCourt(@Param("courtid")Integer courtid, @Param("venueid")Integer venueid,
                     @Param("courttype")Integer courttype, @Param("name")String name,
                     @Param("courtx1")BigDecimal courtx1,@Param("courty1")BigDecimal courty1,
                     @Param("courtx2")BigDecimal courtx2, @Param("courty2")BigDecimal courty2,
                     @Param("picture")byte[] picture);

    @Update("update court set courtx1=#{courtx1},courty1=#{courty1},courtx2=#{courtx2},courty2=#{courty2} " +
            "where courtid=#{courtid}")
    Integer updCourtLocation(@Param("courtid")Integer courtid,
                             @Param("courtx1")BigDecimal courtx1,@Param("courty1")BigDecimal courty1,
                             @Param("courtx2")BigDecimal courtx2, @Param("courty2")BigDecimal courty2);

    @Update("update court set picture=#{picture} where courtid=#{courtid}")
    Integer updCourtPicture(@Param("courtid")Integer courtid,@Param("picture")byte[] picture);

    @Update("update court set venueid=#{venueid},courttype=#{courttype},name=#{name}," +
            "courtx1=#{courtx1},courty1=#{courty1},courtx2=#{courtx2},courty2=#{courty2}, " +
            "picture=#{picture} where courtid=#{courtid}")
    Integer updCourt(@Param("courtid")Integer courtid,@Param("venueid")Integer venueid,
                     @Param("courttype")Integer courttype,@Param("name")String name,
                     @Param("courtx1")BigDecimal courtx1,@Param("courty1")BigDecimal courty1,
                     @Param("courtx2")BigDecimal courtx2, @Param("courty2")BigDecimal courty2,
                     @Param("picture")byte[] picture);
}