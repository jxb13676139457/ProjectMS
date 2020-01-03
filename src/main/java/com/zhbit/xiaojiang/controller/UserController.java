package com.zhbit.xiaojiang.controller;

import com.zhbit.xiaojiang.entity.User;
import com.zhbit.xiaojiang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Iterator;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/admin")
    public String userList(Model model){
        List<User> userList = userService.findAllUser();
        System.out.println(userList);
        model.addAttribute("userList",userList);
        return "admin/userList";
    }
}
