package com.example.gymnasium.mapper;

import com.example.gymnasium.entity.admin;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Mapper
public interface adminMapper {
    int deleteByPrimaryKey(Integer adminid);

    int insert(admin record);

    int insertSelective(admin record);

    admin selectByPrimaryKey(Integer adminid);

    admin selectByName(String name);

    int updateByPrimaryKeySelective(admin record);

    int updateByPrimaryKey(admin record);

    List<admin> findall();

    int register(admin admin);

    int uptAdNameById(admin admin);

    int uptAdPwdById(admin admin);

    admin login(@RequestParam("name") String name, @RequestParam("password") String password);
}