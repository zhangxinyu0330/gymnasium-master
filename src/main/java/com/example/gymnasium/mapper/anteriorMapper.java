package com.example.gymnasium.mapper;

import com.example.gymnasium.entity.anterior;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface anteriorMapper {
    int deleteByPrimaryKey(Integer anteriorid);

    int insert(anterior record);

    int insertSelective(anterior record);

    anterior selectByPrimaryKey(Integer anteriorid);

    anterior selectByName(String name);

    int updateByPrimaryKeySelective(anterior record);

    int updateByPrimaryKey(anterior record);

    int register(anterior anterior);

    int uptAnNameById(anterior anterior);

    int uptAnPwdById(anterior anterior);

    List<anterior> findall();

    anterior login(@RequestParam("name") String name, @RequestParam("password") String password);
}