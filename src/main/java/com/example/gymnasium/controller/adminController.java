package com.example.gymnasium.controller;

import com.example.gymnasium.tools.Result;
import com.example.gymnasium.entity.admin;
import com.example.gymnasium.entity.user;
import com.example.gymnasium.exception.CustomException;
import com.example.gymnasium.service.adminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/admin", produces = "application/json; charset=UTF-8")
public class adminController {
    @Autowired
    private adminService adminService;

    @ResponseBody
    @RequestMapping(value = "/getAdmins", method = RequestMethod.GET)//获取所有管理员信息
    public Result<List<admin>> getAdmins() {
        return Result.success(adminService.findall());
    }

    @ResponseBody
    @RequestMapping(value="/register",method = RequestMethod.POST)
    public Result<admin> register(@RequestBody admin admin){
        admin result = adminService.register(admin);
        if(result!=null){
            System.out.println("用户ID：" + result.getAdminid());
            return Result.success(admin,"注册成功！");
        }
        else
        {
            throw new CustomException("500","该用户名被占用");
        }

    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)//用户通过密码和用户名登陆
    public Result<String> login(@RequestParam String name,@RequestParam String password){
        String userName = name;
        String passWord = password;
        System.out.println("用户名：" + userName + " " + "密码：" + passWord);
        admin u1 =adminService.login(userName,passWord);
        admin u2 =adminService.selectUserByName(userName);
        //int id=u1.getUserid();

        if(u2!=null)//存在这样的用户名,规定用户名唯一
        {
            if(!u2.getPassword().equals(passWord))
            {
                throw new CustomException("500","密码不正确");
            }
            else
            {
                return Result.success(password,"登录成功");
            }

        }
        else if(u1==null)
        {
            throw new CustomException("500","用户不存在");
        }
        else
        {
            throw new CustomException("500","登陆失败");
        }

    }

    @ResponseBody
    @RequestMapping(value = "/uptAdPwdById", method = RequestMethod.POST)//获取用户状态
    public Result<Integer> uptAdPwdById(@RequestParam Integer adminid,@RequestParam String password) {
        return Result.success(adminService.uptAdPwdById(adminid,password));
    }

    @ResponseBody
    @RequestMapping(value = "/uptAdNameById", method = RequestMethod.POST)//获取用户状态
    public Result<Integer> uptAdNameById(@RequestParam Integer adminid,@RequestParam String name) {
        return Result.success(adminService.uptAdNameById(adminid,name));
    }

    @ResponseBody
    @RequestMapping(value = "/delUserById", method = RequestMethod.POST)//获取用户状态
    public Result<Integer> delUserById(@RequestParam Integer adminid) {
        return Result.success(adminService.delUserById(adminid));
    }


}
