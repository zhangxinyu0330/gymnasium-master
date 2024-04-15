package com.example.gymnasium.mapper;

import com.example.gymnasium.entity.rule;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface ruleMapper {
    int deleteByPrimaryKey(Integer ruleid);

    int insert(rule record);

    int insertSelective(rule record);

    rule selectByPrimaryKey(Integer ruleid);

    int updateByPrimaryKeySelective(rule record);

    int updateByPrimaryKey(rule record);

    @Select("select * from rule where courttype=#{courttype} and begintime<=#{begintime} and " +
            "endtime>=#{endtime}")
    List<rule> findByCourttypeTime(@Param("courttype")Integer courttype,
                                   @Param("begintime")Integer begintime, @Param("endtime")Integer endtime);

    @Insert("insert into rule values(#{ruleid},#{venueid},#{courttype},#{holidayornot}," +
            "#{begintime},#{endtime},#{capacity},#{price},#{discount},#{userstatus},#{cardgrade})")
    Integer addRule(@Param("ruleid")Integer ruleid,@Param("venueid")Integer venueid,
                    @Param("courttype")Integer courttype,@Param("holidayornot")Integer holidayornot,
                    @Param("begintime")Integer begintime,@Param("endtime")Integer endtime,
                    @Param("capacity")Integer capacity, @Param("price")BigDecimal price,
                    @Param("discount")BigDecimal discount,@Param("userstatus")String userstatus,
                    @Param("cardgrade")Integer cardgrade);

    @Delete("delete from rule where ruleid=#{ruleid}")
    Integer delRule(Integer ruleid);
}