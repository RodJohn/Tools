package com.john.rod.business.controller;


import com.john.rod.booting.common.Context;
import com.john.rod.business.model.UserEditVo;
import com.john.rod.business.model.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("contact")
@Api("contact")
public class ContactController {


    @PostMapping
    @ApiOperation("add a new user")
    @ResponseBody
    public UserVo add(@Valid@RequestBody UserEditVo user) {
        return new UserVo();
    }


}
