package com.john.rod.business.controller;


import com.john.rod.booting.common.Context;
import com.john.rod.business.Service.UserService;
import com.john.rod.business.model.UserEditVo;
import com.john.rod.business.model.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("user")
@Api("user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    @ApiOperation("add a new user")
    public UserVo add(@Valid @RequestBody UserEditVo user) {
        userService.add(user);
        return new UserVo();
    }

    @DeleteMapping("{id}")
    @ApiOperation("delete the user by id")
    public void deleteById(@PathVariable Long id) {
        System.out.println("threadid：" + Thread.currentThread().getId() + " do user" + Context.getCurUser());
    }

    @PutMapping("{id}")
    @ApiOperation("update the user by id ")
    public void update(@PathVariable Long id, @RequestBody UserEditVo user) {
    }

    @GetMapping("{id}")
    @ApiOperation("find user by id")
    public UserVo findById(@PathVariable Long id) {
        return userService.findById(id);
    }

}
