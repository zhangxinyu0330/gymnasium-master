package com.example.gymnasium.controller;

import com.example.gymnasium.tools.Result;
import com.example.gymnasium.entity.teacher;
import com.example.gymnasium.entity.user;
import com.example.gymnasium.exception.CustomException;
import com.example.gymnasium.service.teacherService;
import com.example.gymnasium.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/teacher", produces = "application/json; charset=UTF-8")
public class teacherController {

    @Autowired
    private teacherService teacherService;
    @Autowired
    private userService userService;

    @ResponseBody
    @RequestMapping(value = "/getTeacherss", method = RequestMethod.GET)//获取所有管理员信息
    public Result<List<teacher>> getTeacherss() {
        return Result.success(teacherService.findall());
    }

    @ResponseBody
    @RequestMapping(value = "/getTeanumById", method = RequestMethod.POST)//获取用户状态
    public Result<String> getTeanumById(@RequestParam Integer userid) {
        return Result.success(teacherService.getTeanumById(userid));
    }

    @ResponseBody
    @RequestMapping(value = "/getIdByTeanum", method = RequestMethod.POST)//获取用户状态
    public Result<Integer> getIdByTeanum(@RequestParam String teachnumber) {
        return Result.success(teacherService.getIdByTeanum(teachnumber));
    }

    @ResponseBody
    @RequestMapping(value="/register",method = {RequestMethod.POST,RequestMethod.GET})
    public Result<teacher> register(@RequestBody teacher teacher,@RequestParam Integer userid){
        teacher result = teacherService.register(teacher,userid);
        user user= userService.selectUserById(userid);
        Integer status= user.getStatus();
        System.out.println("状态：" + status);
        if(status==2)
        {
            if (result != null) {
                System.out.println("用户ID：" + result.getTeachnumber());
                return Result.success(teacher, "注册成功！");
            } else {
                throw new CustomException("500", "该老师已注册");
            }
        }
        else
        {
            throw new CustomException("500","注册的不是老师");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/uptTeachnumberById", method = RequestMethod.POST)//获取用户状态
    public Result<Integer> uptTeachnumberById(@RequestParam Integer userid, @RequestParam String teachnumber) {
        return Result.success(teacherService.uptTeachnumberById(userid,teachnumber));
    }

    @ResponseBody
    @RequestMapping(value = "/uptTeaColleagueById", method = RequestMethod.POST)//获取用户状态
    public Result<Integer> uptTeaColleagueById(@RequestParam Integer userid,@RequestParam String colleague) {
        return Result.success(teacherService.uptTeaColleagueById(userid,colleague));
    }

    @ResponseBody
    @RequestMapping(value = "/uptTeaColleagueByKey", method = RequestMethod.POST)//获取用户状态
    public Result<Integer> uptTeaColleagueByKey(@RequestParam String teachnumber,@RequestParam String colleague) {
        return Result.success(teacherService.uptTeaColleagueByKey(teachnumber,colleague));
    }

    @ResponseBody
    @RequestMapping(value = "/deleteById", method = RequestMethod.POST)//获取用户状态
    public Result<Integer> deleteById(@RequestParam Integer userid) {
        return Result.success(teacherService.deleteById(userid));
    }

}
