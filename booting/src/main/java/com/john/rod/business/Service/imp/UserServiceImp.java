package com.john.rod.business.Service.imp;

import com.john.rod.business.Service.UserService;
import com.john.rod.business.model.UserEditVo;
import com.john.rod.business.model.UserVo;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;


@Service
public class UserServiceImp implements UserService {


    @Override
    public void add(UserEditVo user) {

    }

    @Override
    public @NotNull(message = " 不存在相应用户 ") UserVo findById(@NotNull(message = "id 不能为空 ")Long id) {
        return null;
    }
}
