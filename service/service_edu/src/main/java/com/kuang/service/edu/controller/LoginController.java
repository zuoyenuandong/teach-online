package com.kuang.service.edu.controller;

import com.kuang.common.base.result.R;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class LoginController {
    @PostMapping("/login")
    public R login(String username, String password){

        return R.ok().data("token","admin");
    }

    @GetMapping("/info")
    public R info(String token){

        return R.ok()
                .data("name","admin")
                .data("roles","[admin]")
                .data("avatar","http://likeever.top/image/template.png");
    }

    @PostMapping("/logout")
    public R logout(String username, String password){

        return R.ok();
    }
}
