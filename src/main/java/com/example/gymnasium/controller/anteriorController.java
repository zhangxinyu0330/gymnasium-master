package com.example.gymnasium.controller;

import com.example.gymnasium.tools.Result;
import com.example.gymnasium.entity.anterior;
import com.example.gymnasium.exception.CustomException;
import com.example.gymnasium.service.antService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(value = "/anterior", produces = "application/json; charset=UTF-8")
public class anteriorController {

    @Autowired
    antService antService;

    @ResponseBody
    @RequestMapping(value = "/getAnterior", method = RequestMethod.GET)//获取所有管理员信息
    public Result<List<anterior>> getAnterior() {
        return Result.success(antService.findall());
    }

    @ResponseBody
    @RequestMapping(value="/register",method = RequestMethod.POST)
    public Result<anterior> register(@RequestBody anterior anterior){
        anterior result = antService.register(anterior);
        if(result!=null){
            System.out.println("用户ID：" + result.getAnteriorid());
            return Result.success(anterior,"注册成功！");
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
        anterior u1 =antService.login(userName,passWord);
        anterior u2 =antService.selectUserByName(userName);
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
    public Result<Integer> uptAdPwdById(@RequestParam Integer anteriorid, @RequestParam String password) {
        return Result.success(antService.uptAnPwdById(anteriorid,password));
    }

    @ResponseBody
    @RequestMapping(value = "/uptAdNameById", method = RequestMethod.POST)//获取用户状态
    public Result<Integer> uptAdNameById(@RequestParam Integer anteriorid,@RequestParam String name) {
        return Result.success(antService.uptAnNameById(anteriorid,name));
    }

    @ResponseBody
    @RequestMapping(value = "/delUserById", method = RequestMethod.POST)//获取用户状态
    public Result<Integer> delUserById(@RequestParam Integer anteriorid) {
        return Result.success(antService.delUserById(anteriorid));
    }

}
