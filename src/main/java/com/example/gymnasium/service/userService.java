package com.example.gymnasium.service;

import com.example.gymnasium.entity.student;
import com.example.gymnasium.entity.user;
import com.example.gymnasium.mapper.studentMapper;
import com.example.gymnasium.mapper.teacherMapper;
import com.example.gymnasium.mapper.userMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userService {

    @Autowired
    userMapper userMapper;
    @Autowired
    studentMapper studentMapper;
    @Autowired
    teacherMapper teacherMapper;

    public List<user> findall() {
        return userMapper.findall();
    }

    public user login(String username, String password) {
        return userMapper.login(username,password);
    }

    public user selectUserByName(String username) {
        return userMapper.selectUserByName(username);
    }

    public user selectUserById(Integer Id) {
        return userMapper.selectUserById(Id);
    }

    public int getUserStateById(Integer Id) {
        user user=userMapper.selectUserById(Id);
        int status=user.getStatus();
        return status;
    }

    public String getUserNameById(Integer Id) {
        user user=userMapper.selectUserById(Id);
        String username=user.getUsername();
        return username;
    }

    public String getUserPasswordById(Integer Id) {
        user user=userMapper.selectUserById(Id);
        String password=user.getPassword();
        return password;
    }

    public int getUserPhonenumberById(Integer Id) {
        user user=userMapper.selectUserById(Id);
        Integer phonenumber=user.getPhonenumber();
        return phonenumber;
    }

    public int getUserCardidById(Integer Id) {
        user user=userMapper.selectUserById(Id);
        Integer cardid=user.getCardid();
        return cardid;
    }

    public byte[] getUserHeadpicById(Integer Id) {
        user user=userMapper.selectUserById(Id);
        byte[] headpic=user.getHeadpic();
        return headpic;
    }

    public int uptUserNameById(Integer Id, String username) {
        user user=userMapper.selectUserById(Id);
        user.setUsername(username);
        return userMapper.updateByName(user);
    }

    public int uptUserPwdById(Integer Id, String password) {
        user user=userMapper.selectUserById(Id);
        user.setPassword(password);
        return userMapper.updateByPwd(user);
    }

    public int uptUserPhonenumberById(Integer Id, Integer phonenumber) {
        user user=userMapper.selectUserById(Id);
        user.setPhonenumber(phonenumber);
        return userMapper.updateByPhonenumber(user);
    }

    public int uptUserCardidById(Integer Id, Integer cardid) {
        user user=userMapper.selectUserById(Id);
        user.setCardid(cardid);
        return userMapper.updateByCardid(user);
    }

    public int uptUserStatusById(Integer Id, Integer status) {
        user user=userMapper.selectUserById(Id);
        user.setStatus(status);
        return userMapper.updateByStatus(user);
    }

    public int updateByHeadpic(Integer Id, byte[] headpic) {
        user user=userMapper.selectUserById(Id);
        user.setHeadpic(headpic);
        return userMapper.updateByHeadpic(user);
    }

    public int delUserById(Integer Id) {
        user user=userMapper.selectUserById(Id);
        Integer status=user.getStatus();
        if(status==1)
        {
            return userMapper.deleteByPrimaryKeyStu(Id);
        }
        else if(status==2)
        {
            return userMapper.deleteByPrimaryKeyTea(Id);
        }
        else if(status==3)
        {
            return userMapper.deleteByPrimaryKeyOrd(Id);
        }
        else
        {
            return 0;
        }
    }

    public Integer delUserByName(String username) {
        user user=userMapper.selectUserByName(username);
        int userid=user.getUserid();
        return userMapper.deleteByPrimaryKey(userid);
    }
    
    public user getUserById(int id){
        return userMapper.getUserById(id);
    }

    public user register(user user) {
        String username = user.getUsername();
        String password = user.getPassword();
        //Integer cardid = user.getCardid();
        Integer status = user.getStatus();
        Integer phonenumber =user.getPhonenumber();
        //byte[] headpic =user.getHeadpic();
        // 调用userMapper.findByUsername()方法执行查询
        user result1 = userMapper.selectUserByName(username);
        // 判断查询结果是否不为null
        if (result1 != null ){
            return null;//表示用户名被占用
        }
        else{
            if(password != null && username!=null && status!=null && phonenumber!=null )
            {
                // 用户名已经没有被占用 必填数据不是空
                userMapper.register(user);//创建一个新用户
                String name=user.getUsername();
                System.out.println("用户名：" + name);
            }

        }
        return user;
    }


/*
    public gms_user selectUserById(int id){
        return userMapper.selectUserById(id);
    }

    public gms_user selectUserByNameAndPwd(String name,String password){
        return userMapper.selectUserByNameAndPwd(name,password);
    }
*/

}









