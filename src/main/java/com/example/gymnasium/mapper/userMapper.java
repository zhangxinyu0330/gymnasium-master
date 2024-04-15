package com.example.gymnasium.mapper;

import com.example.gymnasium.entity.user;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface userMapper {
    int deleteByPrimaryKeyStu(Integer userid);

    int deleteByPrimaryKeyTea(Integer userid);

    int deleteByPrimaryKeyOrd(Integer userid);

    int deleteByPrimaryKey(Integer userid);

    int insert(user user);

    int insertSelective(user user);

    user selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(user user);

    int updateByPrimaryKeyWithBLOBs(user user);

    int updateByPrimaryKey(user user);

    int updateByName(user user);

    int updateByPwd(user user);

    int updateByPhonenumber(user user);

    int updateByCardid(user user);

    int updateByStatus(user user);

    int updateByHeadpic(user user);

    List<user> findall();//查询所有用户

    //通过账号密码来查询信息（登录）
    user login(@RequestParam("username") String username, @RequestParam("password") String password);

    //增加用户
    int addUser(@RequestParam("user") user user);

    //通过用户名来进行查询
    user selectUserByName(@RequestParam("username") String username);

    user selectUserById(Integer userid);

    int register(user user);

    @Select("SELECT * FROM user where userid=#{id}")
    user getUserById (int id);
}
