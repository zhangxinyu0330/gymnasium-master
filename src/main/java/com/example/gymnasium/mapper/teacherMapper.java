package com.example.gymnasium.mapper;

import com.example.gymnasium.entity.teacher;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface teacherMapper {
    int deleteById(Integer userid);

    int insert(teacher record);

    int insertSelective(teacher record);

    teacher selectByPrimaryKey(String teachnumber);

    int updateByPrimaryKeySelective(teacher record);

    int updateByPrimaryKey(teacher record);

    teacher selectByuserid(Integer userid);

    int uptTeaColleagueByKey(teacher record);

    int uptTeaColleagueById(teacher record);

    int uptTeachnumberById(teacher record);

    List<teacher> findall();

    int register(teacher record);
}