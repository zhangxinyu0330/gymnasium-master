package com.example.gymnasium.controller;

import com.example.gymnasium.tools.Result;
import com.example.gymnasium.entity.student;
import com.example.gymnasium.entity.user;
import com.example.gymnasium.exception.CustomException;
import com.example.gymnasium.mapper.studentMapper;
import com.example.gymnasium.mapper.userMapper;
import com.example.gymnasium.service.studentService;
import com.example.gymnasium.service.userService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@SpringBootApplication(scanBasePackages = {"com.example.gymnasium"})
@RequestMapping(value = "/student", produces = "application/json; charset=UTF-8")
public class studentController {


    @Autowired
    private studentService studentService;
    @Autowired
    private userService userService;

    @ResponseBody
    @RequestMapping(value = "/getStudents", method = RequestMethod.GET)//获取所有管理员信息
    public Result<List<student>> getStudents() {
        return Result.success(this.studentService.findall());
    }

    @ResponseBody
    @RequestMapping(value = "/getStunumById", method = RequestMethod.POST)//获取用户状态
    public Result<String> getStunumById(@RequestParam Integer userid) {
        return Result.success(this.studentService.getStunumById(userid));
    }

    @ResponseBody
    @RequestMapping(value = "/getIdByStunum", method = RequestMethod.POST)//获取用户状态
    public Result<Integer> getIdByStunum(@RequestParam String studentnumber) {
        return Result.success(this.studentService.getIdByStunum(studentnumber));
    }


    @ResponseBody
    @RequestMapping(value="/register",method = {RequestMethod.POST,RequestMethod.GET})
    public Result<student> register(@RequestBody student student,@RequestParam Integer userid){
        System.out.println("11111");
        user user=userService.selectUserById(userid);
        Integer status=user.getStatus();
        System.out.println("状态：" + status);
        if(status==1)
        {
            student result = this.studentService.register(student,userid);
            if(result!=null){
                System.out.println("用户ID：" + result.getStudentnumber());
                return Result.success(student,"注册成功！");
            }
            else
            {
                throw new CustomException("500","该学生已注册");
            }
        }
        else
        {
            throw new CustomException("500","注册的不是学生");
        }
    }



    @ResponseBody
    @RequestMapping(value = "/uptStuGardeByKey", method = RequestMethod.POST)//获取用户状态
    public Result<Integer> uptStuGardeByKey(@RequestParam String studentnumber, @RequestParam Integer grade) {
        return Result.success(this.studentService.uptStuGardeByKey(studentnumber,grade));
    }

    @ResponseBody
    @RequestMapping(value = "/uptStuPeriodByKey", method = RequestMethod.POST)//获取用户状态
    public Result<Integer> uptStuPeriodByKey(@RequestParam String studentnumber,@RequestParam Integer Integer) {
        return Result.success(this.studentService.uptStuPeriodByKey(studentnumber,Integer));
    }

    @ResponseBody
    @RequestMapping(value = "/uptStuColledgeByKey", method = RequestMethod.POST)//获取用户状态
    public Result<Integer> uptStuColledgeByKey(@RequestParam String studentnumber, @RequestParam String colleague) {
        return Result.success(this.studentService.uptStuColledgeByKey(studentnumber,colleague));
    }

    @ResponseBody
    @RequestMapping(value = "/uptStuSubByKey", method = RequestMethod.POST)//获取用户状态
    public Result<Integer> uptStuSubByKey(@RequestParam String studentnumber,@RequestParam String subject) {
        return Result.success(this.studentService.uptStuSubByKey(studentnumber,subject));
    }

    @ResponseBody
    @RequestMapping(value = "/delUserById", method = RequestMethod.POST)//获取用户状态
    public Result<Integer> delUserById(@RequestParam Integer userid) {
        return Result.success(this.studentService.delUserById(userid));
    }



}

