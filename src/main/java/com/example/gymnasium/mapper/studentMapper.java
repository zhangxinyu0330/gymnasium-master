package com.example.gymnasium.mapper;

import com.example.gymnasium.entity.student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface studentMapper {
    int deleteById(Integer userid);

    int insert(student record);

    int insertSelective(student record);

    student selectByPrimaryKey(String studentnumber);

    student selectByuserid(Integer id);

    int updateByPrimaryKeySelective(student record);

    int updateByPrimaryKey(student record);

    int register(student student);

    List<student> findall();

    int uptStuGardeByKey(student student);

    int uptStuPeriodByKey(student student);

    int uptStuColledgeByKey(student student);

    int uptStuSubByKey(student student);
}