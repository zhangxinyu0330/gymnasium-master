package com.example.gymnasium.controller;

import com.example.gymnasium.tools.Result;
import com.example.gymnasium.entity.ordinary;
import com.example.gymnasium.entity.teacher;
import com.example.gymnasium.entity.user;
import com.example.gymnasium.exception.CustomException;
import com.example.gymnasium.service.ordinaryService;
import com.example.gymnasium.service.teacherService;
import com.example.gymnasium.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/ordinary", produces = "application/json; charset=UTF-8")

public class ordinaryController {

    @Autowired
    private ordinaryService ordinaryService;
    @Autowired
    private userService userService;

    @ResponseBody
    @RequestMapping(value = "/getOrdinarys", method = RequestMethod.GET)//获取所有管理员信息
    public Result<List<ordinary>> getOrdinarys() {
        return Result.success(ordinaryService.findall());
    }


    @ResponseBody
    @RequestMapping(value = "/getOrdinarynumById", method = RequestMethod.POST)//获取用户状态
    public Result<String> getOrdinarynumById(@RequestParam Integer userid) {
        return Result.success(ordinaryService.getOrdinarynumById(userid));
    }

    @ResponseBody
    @RequestMapping(value = "/getIdByOrdnum", method = RequestMethod.POST)//获取用户状态
    public Result<Integer> getIdByOrdnum(@RequestParam String ordinarynum) {
        return Result.success(ordinaryService.getIdByOrdnum(ordinarynum));
    }

    @ResponseBody
    @RequestMapping(value="/register",method = {RequestMethod.POST,RequestMethod.GET})
    public Result<ordinary> register(@RequestBody ordinary ordinary, @RequestParam Integer userid){
        ordinary result = ordinaryService.register(ordinary,userid);
        user user= userService.selectUserById(userid);
        Integer status= user.getStatus();
        System.out.println("状态：" + status);
        if(status==3)
        {
            if (result != null) {
                System.out.println("用户ID：" + result.getOrdinarynum());
                return Result.success(ordinary, "注册成功！");
            } else {
                throw new CustomException("500", "该游客已注册");
            }
        }
        else
        {
            throw new CustomException("500","注册的不是普通游客");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/uptOrdnumberById", method = RequestMethod.POST)//获取用户状态
    public Result<Integer> uptOrdnumberById(@RequestParam Integer userid, @RequestParam String ordinarynum) {
        return Result.success(ordinaryService.uptOrdnumberById(userid,ordinarynum));
    }


    @ResponseBody
    @RequestMapping(value = "/deleteById", method = RequestMethod.POST)//获取用户状态
    public Result<Integer> deleteById(@RequestParam Integer userid) {
        return Result.success(ordinaryService.deleteById(userid));
    }
}
