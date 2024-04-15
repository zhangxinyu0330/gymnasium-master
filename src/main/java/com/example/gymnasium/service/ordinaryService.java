package com.example.gymnasium.service;

import com.example.gymnasium.entity.ordinary;
import com.example.gymnasium.entity.teacher;
import com.example.gymnasium.mapper.ordinaryMapper;
import com.example.gymnasium.mapper.teacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ordinaryService {
    @Autowired
    ordinaryMapper ordinaryMapper;

    public List<ordinary> findall() {
        return ordinaryMapper.findall();
    }

    public ordinary register(ordinary ordinary,Integer id) {
        ordinary.setUserid(id);
        String ordinarynum=ordinary.getOrdinarynum();
        ordinary ordinary1=ordinaryMapper.selectByPrimaryKey(ordinarynum);
        // 判断查询结果是否不为null
        if (ordinary1 != null ){
            return null;//表示该教师已经注册过
        }
        else{
            if(ordinarynum != null && id!=null)
            {
                // 用户名已经没有被占用 必填数据不是空
                ordinaryMapper.register(ordinary);//创建一个新用户
                System.out.println("用户名：" + ordinarynum);
            }

        }
        return ordinary;
    }
    public String getOrdinarynumById(Integer userid) {
        ordinary ordinary=ordinaryMapper.selectByuserid(userid);
        String ordinarynum=ordinary.getOrdinarynum();
        return ordinarynum;
    }

    public int getIdByOrdnum(String ordinarynum) {
        ordinary ordinary=ordinaryMapper.selectByPrimaryKey(ordinarynum);
        Integer id=ordinary.getUserid();
        return id;
    }

    public ordinary selectByPrimaryKey(String ordinarynum) {
        return ordinaryMapper.selectByPrimaryKey(ordinarynum);
    }

    public int deleteById(Integer userid) {
        return ordinaryMapper.deleteById(userid);
    }

    public int uptOrdnumberById(Integer userid, String ordinarynum) {
        ordinary ordinary=ordinaryMapper.selectByuserid(userid);
        ordinary.setOrdinarynum(ordinarynum);
        return ordinaryMapper.uptOrdnumberById(ordinary);
    }
}
