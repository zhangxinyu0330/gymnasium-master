package com.example.gymnasium.controller;

import com.example.gymnasium.tools.Result;
import com.example.gymnasium.entity.student;
import com.example.gymnasium.entity.user;
import com.example.gymnasium.exception.CustomException;
import com.example.gymnasium.mapper.userMapper;
import com.example.gymnasium.service.studentService;
import com.example.gymnasium.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping(value = "/user", produces = "application/json; charset=UTF-8")
public class userController {
    @Autowired
    private userService userService;
    @Autowired
    private studentService studentService;

    @ResponseBody
    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)//获取所有用户
    public Result<List<user>> getUsers() {
        return Result.success(userService.findall());
    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)//用户通过密码和用户名登陆
    public Result<String> login(@RequestParam String name,@RequestParam String password){
        String userName = name;
        String passWord = password;
        System.out.println("用户名：" + userName + " " + "密码：" + passWord);
        user u1 =userService.login(userName,passWord);
        user u2 =userService.selectUserByName(userName);
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

    //注册操作
    @ResponseBody
    @RequestMapping(value="/register",method = RequestMethod.POST)
    public Result<user> register(@RequestBody  user user, HttpServletResponse response, RedirectAttributes attr) throws IOException {
        user result = userService.register(user);
        if(result!=null){
            System.out.println("用户ID：" + result.getUserid());
            Integer status=result.getStatus();
            Integer userid=result.getUserid();
            if(status==1)
            {
                attr.addAttribute("userid", userid);
                System.out.println(userid);
                return Result.success(user, "redirect:student/register"); // 跳转到学生注册界面

            }
            if(status==2)
            {
                attr.addAttribute("userid", userid);
                System.out.println(userid);
                return Result.success(user, "redirect:teacher/register"); // 跳转到教师注册界面
            }
            if(status==3)
            {
                attr.addAttribute("userid", userid);
                System.out.println(userid);
                return Result.success(user, "redirect:ordinary/register"); // 跳转到普通用户注册界面
            }
            return Result.success(user,"注册成功！");
        }
        else
        {
            throw new CustomException("500","该用户名被占用");
        }

    }

    @ResponseBody
    @RequestMapping(value = "/toSturegister", method = RequestMethod.POST)//获取用户状态
    public Result<Integer> toSturegister(@RequestParam Integer userid) {
        return Result.success(userid);
    }



    @ResponseBody
    @RequestMapping(value = "/getUserRole", method = RequestMethod.POST)//获取用户状态
    public Result<Integer> getUserStateById(@RequestParam Integer userid) {
        return Result.success(userService.getUserStateById(userid));
    }

    @ResponseBody
    @RequestMapping(value = "/getUserPhonenumber", method = RequestMethod.POST)//获取用户状态
    public Result<Integer>  getUserPhonenumberById(@RequestParam Integer userid) {
        return Result.success(userService.getUserPhonenumberById(userid));
    }

    @ResponseBody
    @RequestMapping(value = "/getUserNameById", method = RequestMethod.POST)//获取用户状态
    public Result<String>  getUserNameById(@RequestParam Integer userid) {
        return Result.success(userService.getUserNameById(userid));
    }

    @ResponseBody
    @RequestMapping(value = "/getUserPasswordById", method = RequestMethod.POST)//获取用户状态
    public Result<String>  getUserPasswordById(@RequestParam Integer userid) {
        return Result.success(userService.getUserPasswordById(userid));
    }

    @ResponseBody
    @RequestMapping(value = "/getUserHeadpicById", method = RequestMethod.POST)//获取用户状态
    public Result<byte[]>  getUserHeadpicById(@RequestParam Integer userid) {
        return Result.success(userService.getUserHeadpicById(userid));
    }

    @ResponseBody
    @RequestMapping(value = "/getUserCardidById", method = RequestMethod.POST)//获取用户状态
    public Result<Integer>  getUserCardidById(@RequestParam Integer userid) {
        return Result.success(userService.getUserCardidById(userid));
    }

    @ResponseBody
    @RequestMapping(value = "/selectUserById", method = RequestMethod.POST)//获取用户状态
    public Result<user>  selectUserById(@RequestParam Integer userid) {
        return Result.success(userService.selectUserById(userid));
    }

    @ResponseBody
    @RequestMapping(value = "/selectUserByName", method = RequestMethod.POST)//获取用户状态
    public Result<user> selectUserByName(@RequestParam String username) {
        return Result.success(userService.selectUserByName(username));
    }

    @ResponseBody
    @RequestMapping(value = "/delUserById", method = RequestMethod.POST)//获取用户状态
    public Result<Integer> delUserById(@RequestParam Integer userid) {
        return Result.success(userService.delUserById(userid));
    }

    @ResponseBody
    @RequestMapping(value = "/delUserByName", method = RequestMethod.POST)//获取用户状态
    public Result<Integer> delUserByName(@RequestParam String username) {
        return Result.success(userService.delUserByName(username));
    }

    @ResponseBody
    @RequestMapping(value = "/uptUserNameById", method = RequestMethod.POST)//获取用户状态
    public Result<Integer> uptUserNameById(@RequestParam Integer userid,@RequestParam String username) {
        user user= userService.selectUserById(userid);
        user.setUsername(username);
        System.out.println(username);
        return Result.success(userService.uptUserNameById(userid,username));
    }

    @ResponseBody
    @RequestMapping(value = "/uptUserPwdById", method = RequestMethod.POST)//获取用户状态
    public Result<Integer> uptUserPwdById(@RequestParam Integer userid,@RequestParam String password) {
        return Result.success(userService.uptUserPwdById(userid,password));
    }

    @ResponseBody
    @RequestMapping(value = "/uptUserStatusById", method = RequestMethod.POST)//获取用户状态
    public Result<Integer> uptUserStatusById(@RequestParam Integer userid,@RequestParam Integer status) {
        return Result.success(userService.uptUserStatusById(userid,status));
    }

    @ResponseBody
    @RequestMapping(value = "/uptUserCardidById", method = RequestMethod.POST)//获取用户状态
    public Result<Integer> uptUserCardidById(@RequestParam Integer userid,@RequestParam Integer cardid) {
        return Result.success(userService.uptUserCardidById(userid,cardid));
    }

    @ResponseBody
    @RequestMapping(value = "/uptUserPhonenumberById", method = RequestMethod.POST)//获取用户状态
    public Result<Integer> uptUserPhonenumberById(@RequestParam Integer userid,@RequestParam Integer phonenumber) {
        return Result.success(userService.uptUserPhonenumberById(userid,phonenumber));
    }

    @ResponseBody
    @RequestMapping(value = "/updateByHeadpic", method = RequestMethod.POST)//获取用户状态
    public Result<Integer> updateByHeadpic(@RequestParam Integer userid,@RequestParam byte[] headpic) {
        return Result.success(userService.updateByHeadpic(userid,headpic));
    }


    @RequestMapping(value = "/getUserById",method = RequestMethod.GET)//获取用户状态
    public user getUserById(Integer id){
        return userService.getUserById(id);
    }

}

