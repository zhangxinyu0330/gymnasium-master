package com.example.gymnasium.service;

import com.example.gymnasium.entity.admin;
import com.example.gymnasium.mapper.adminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class adminService {
    @Autowired
    adminMapper adminMapper;

    public List<admin> findall() {
        return adminMapper.findall();
    }

    public admin register(admin admin) {
        String name = admin.getName();
        String password = admin.getPassword();
        admin result1 = adminMapper.selectByName(name);
        // 判断查询结果是否不为null
        if (result1 != null ){
            return null;//表示用户名被占用
        }
        else{
            if(password != null && name!=null)
            {
                // 用户名已经没有被占用 必填数据不是空
                adminMapper.register(admin);//创建一个新用户
                System.out.println("用户名：" + name);
            }

        }
        return admin;
    }

    public admin login(String name, String password) {
        return adminMapper.login(name,password);
    }

    public admin selectUserByName(String name) {
        return adminMapper.selectByName(name);
    }

    public int uptAdNameById(Integer adminid, String name) {
        admin admin=adminMapper.selectByPrimaryKey(adminid);
        admin.setName(name);
        return adminMapper.uptAdNameById(admin);
    }

    public int uptAdPwdById(Integer adminid, String password) {
        admin admin=adminMapper.selectByPrimaryKey(adminid);
        admin.setPassword(password);
        return adminMapper.uptAdPwdById(admin);
    }

    public Integer delUserById(Integer adminid) {
        return adminMapper.deleteByPrimaryKey(adminid);
    }

}
