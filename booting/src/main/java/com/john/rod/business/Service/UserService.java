package com.john.rod.business.Service;

import com.john.rod.business.model.UserEditVo;
import com.john.rod.business.model.UserVo;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;


@Validated
public interface UserService {


    void add(UserEditVo user);

     UserVo findById( Long id);
}
