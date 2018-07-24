package com.john.rod.business.controller;


import com.john.rod.business.model.UserEditVo;
import com.john.rod.business.model.UserVo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("dept")
public class DeptController {


    @PostMapping
    public UserVo add(@RequestBody UserEditVo user) {
        return new UserVo();
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
    }

    @PutMapping("{id}")
    public void update(@PathVariable Long id, @RequestBody UserEditVo user) {
    }

    @GetMapping("{id}")
    public UserVo findById(@PathVariable Long id) {
        return new UserVo();
    }

}
