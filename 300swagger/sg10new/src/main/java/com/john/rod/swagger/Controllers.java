package com.john.rod.swagger;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Api(tags="控制器显示名",description="控制器功能介绍")
@RestController
public class Controllers {


    @ApiOperation(value="方法名", notes="方法功能介绍")
    @GetMapping("/get")
    public String handleMessage(){
        return "12321";
    }


    @GetMapping("/get1")
    public String handleMessage1(
            @ApiParam(value="参数解释",required=false,defaultValue="默认值")
            @RequestParam(name="name",required=false,defaultValue="默认值") String name){
        return "";
    }


    @ApiOperation(value="RequestBody-simple", notes="wode备注")
    @PostMapping("/4")
    User hello4(
            @ApiParam("用户实体")
            @RequestBody User user) {
        return user;
    }
}
