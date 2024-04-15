package com.example.gymnasium.mapper;

import com.example.gymnasium.entity.ordinary;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ordinaryMapper {
    int deleteByPrimaryKey(String ordinarynum);

    int insert(ordinary record);

    int insertSelective(ordinary record);

    ordinary selectByPrimaryKey(String ordinarynum);

    ordinary selectByuserid(Integer userid);

    int register(ordinary record);

    List<ordinary> findall();

    int uptOrdnumberById(ordinary record);

    int deleteById(Integer userid);
}