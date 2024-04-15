package com.example.gymnasium.service;

import com.example.gymnasium.entity.anterior;
import com.example.gymnasium.mapper.anteriorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class antService {
    @Autowired
    anteriorMapper anteriorMapper;

    public List<anterior> findall() {
        return anteriorMapper.findall();
    }

    public anterior register(anterior anterior) {
        String name = anterior.getName();
        String password = anterior.getPassword();
        anterior result1 = anteriorMapper.selectByName(name);
        // 判断查询结果是否不为null
        if (result1 != null ){
            return null;//表示用户名被占用
        }
        else{
            if(password != null && name!=null)
            {
                // 用户名已经没有被占用 必填数据不是空
                anteriorMapper.register(anterior);//创建一个新用户
                System.out.println("用户名：" + name);
            }

        }
        return anterior;
    }

    public anterior login(String name, String password) {
        return anteriorMapper.login(name,password);
    }

    public anterior selectUserByName(String name) {
        return anteriorMapper.selectByName(name);
    }

    public int uptAnPwdById(Integer anteriorid, String name) {
        anterior anterior=anteriorMapper.selectByPrimaryKey(anteriorid);
        anterior.setName(name);
        return anteriorMapper.uptAnNameById(anterior);
    }

    public int uptAnNameById(Integer anteriorid, String password) {
        anterior anterior=anteriorMapper.selectByPrimaryKey(anteriorid);
        anterior.setPassword(password);
        return anteriorMapper.uptAnPwdById(anterior);
    }

    public Integer delUserById(Integer anteriorid) {
        return anteriorMapper.deleteByPrimaryKey(anteriorid);
    }
}
