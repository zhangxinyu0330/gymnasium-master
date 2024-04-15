package com.example.gymnasium.service;

import com.example.gymnasium.entity.student;
import com.example.gymnasium.mapper.studentMapper;
import com.example.gymnasium.mapper.userMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class studentService {

    @Autowired
    studentMapper studentMapper;
    @Autowired
    userMapper userMapper;

    public List<student> findall() {
        return studentMapper.findall();
    }

    public student register(student student,Integer id) {
        student.setUserid(id);
        String studentnumber=student.getStudentnumber();
        student student1=studentMapper.selectByPrimaryKey(studentnumber);
        // 判断查询结果是否不为null
        if (student1 != null ){
            return null;//表示该学生已经注册过
        }
        else{
            if(studentnumber != null && id!=null)
            {
                // 用户名已经没有被占用 必填数据不是空
                studentMapper.register(student);//创建一个新用户
                System.out.println("用户名：" + studentnumber);
            }

        }
        return student;
    }

    public String getStunumById(Integer Id) {
        student student=studentMapper.selectByuserid(Id);
        String studentnumber=student.getStudentnumber();
        return studentnumber;
    }

    public int getIdByStunum(String studentnumber) {
        student student=studentMapper.selectByPrimaryKey(studentnumber);
        Integer id=student.getUserid();
        return id;
    }

    public student selectByPrimaryKey(String studentnumber) {
        return studentMapper.selectByPrimaryKey(studentnumber);
    }

    public int uptStuGardeByKey(String studentnumber, Integer grade) {
        student student=studentMapper.selectByPrimaryKey(studentnumber);
        student.setGrade(grade);
        return studentMapper.uptStuGardeByKey(student);
    }

    public int uptStuPeriodByKey(String studentnumber, Integer period) {
        student student=studentMapper.selectByPrimaryKey(studentnumber);
        student.setPeriod(period);
        return studentMapper.uptStuPeriodByKey(student);
    }

    public int uptStuColledgeByKey(String studentnumber, String colleague) {
        student student=studentMapper.selectByPrimaryKey(studentnumber);
        student.setColleague(colleague);
        return studentMapper.uptStuColledgeByKey(student);
    }

    public int uptStuSubByKey(String studentnumber, String subject) {
        student student=studentMapper.selectByPrimaryKey(studentnumber);
        student.setSubject(subject);
        return studentMapper.uptStuSubByKey(student);
    }

    public int delUserById(Integer userid) {
        return studentMapper.deleteById(userid);
    }

}
