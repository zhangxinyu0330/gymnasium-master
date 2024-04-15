package com.example.gymnasium.mapper;

import com.example.gymnasium.entity.venue;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface venueMapper {
    int deleteByPrimaryKey(Integer venueid);

    int insert(venue record);

    int insertSelective(venue record);

    venue selectByPrimaryKey(Integer venueid);

    int updateByPrimaryKeySelective(venue record);

    int updateByPrimaryKeyWithBLOBs(venue record);

    int updateByPrimaryKey(venue record);

    @Select("select * from venue where venueisdel=0")
    List<venue> findAll();

    @Insert("insert into venue values(#{venueid},#{name},#{introduction},0,#{picture})")
    Integer addVenue(@Param("venueid")Integer venueid, @Param("name")String name,
                     @Param("introduction")String introduction, @Param("picture")byte[] picture);

    @Update("update venue set venueisdel=1 where venueid=#{venueid}")
    Integer delVenue(Integer venueid);

    @Select("select * from venue where venueid=#{venueid}")
    venue findVenueById(Integer venueid);

    @Update("update venue set name=#{name} where venueid=#{venueid}")
    Integer updVenueName(@Param("venueid")Integer venueid, @Param("name")String name);

    @Update("update venue set introduction=#{introduction} where venueid=#{venueid}")
    Integer updVenueIntoduction(@Param("venueid")Integer venueid, @Param("introduction")String introduction);

    @Update("update venue set picture=#{picture} where venueid=#{venueid}")
    Integer updVenuePicture(@Param("venueid")Integer venueid, @Param("picture")byte[] picture);
}