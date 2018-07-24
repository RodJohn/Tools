package com.john.rod.business.controller;


import com.john.rod.booting.common.Context;
import com.john.rod.business.model.UserEditVo;
import com.john.rod.business.model.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@Api("user")
public class UserController {


    @PostMapping
    @ApiOperation("add a new user")
    public UserVo add(@RequestBody UserEditVo user) {
        return new UserVo();
    }

    @DeleteMapping("{id}")
    @ApiOperation("delete the user by id")
    public void deleteById(@PathVariable Long id) {
        System.out.println("threadid："+Thread.currentThread().getId()+" do user" + Context.getCurUser());
    }

    @PutMapping("{id}")
    @ApiOperation("update the user by id ")
    public void update(@PathVariable Long id, @RequestBody UserEditVo user) {
    }

    @GetMapping("{id}")
    @ApiOperation("find user by id")
    public UserVo findById(@PathVariable Long id) {
        return new UserVo();
    }

}
