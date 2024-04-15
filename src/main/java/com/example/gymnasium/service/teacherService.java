package com.example.gymnasium.service;

import com.example.gymnasium.entity.student;
import com.example.gymnasium.entity.teacher;
import com.example.gymnasium.mapper.teacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class teacherService {
    @Autowired
    teacherMapper teacherMapper;

    public List<teacher> findall() {
        return teacherMapper.findall();
    }

    public teacher register(teacher teacher,Integer id) {
        teacher.setUserid(id);
        String teachnumber=teacher.getTeachnumber();
        teacher teacher1=teacherMapper.selectByPrimaryKey(teachnumber);
        // 判断查询结果是否不为null
        if (teacher1 != null ){
            return null;//表示该教师已经注册过
        }
        else{
            if(teachnumber != null && id!=null)
            {
                // 用户名已经没有被占用 必填数据不是空
                teacherMapper.register(teacher);//创建一个新用户
                System.out.println("用户名：" + teachnumber);
            }

        }
        return teacher;
    }


    public String getTeanumById(Integer userid) {
        teacher teacher=teacherMapper.selectByuserid(userid);
        String teachnumber=teacher.getTeachnumber();
        return teachnumber;
    }

    public int getIdByTeanum(String teachnumber) {
        teacher teacher=teacherMapper.selectByPrimaryKey(teachnumber);
        Integer id=teacher.getUserid();
        return id;
    }

    public teacher selectByPrimaryKey(String teachnumber) {
        return teacherMapper.selectByPrimaryKey(teachnumber);
    }

    public int uptTeachnumberById(Integer userid, String teachnumber) {
        teacher teacher=teacherMapper.selectByuserid(userid);
        teacher.setTeachnumber(teachnumber);
        return teacherMapper.uptTeachnumberById(teacher);
    }

    public int uptTeaColleagueById(Integer userid, String colleague) {
        teacher teacher=teacherMapper.selectByuserid(userid);
        teacher.setColleague(colleague);
        return teacherMapper.uptTeaColleagueById(teacher);
    }

    public int uptTeaColleagueByKey(String teachnumber, String colleague) {
        teacher teacher=teacherMapper.selectByPrimaryKey(teachnumber);
        teacher.setColleague(colleague);
        return teacherMapper.uptTeaColleagueByKey(teacher);
    }

    public int deleteById(Integer userid) {
        return teacherMapper.deleteById(userid);
    }
}
