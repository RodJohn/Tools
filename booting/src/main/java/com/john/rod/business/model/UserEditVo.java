package com.john.rod.business.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@ApiModel
public class UserEditVo {

    @NotNull(message="name不能为空")
    @ApiModelProperty(notes = "用戶名",required = true,example="john")
    private String name ;

    @NotNull(message="age不能为空")
    @Min(value = 1,message = "age不能小于1")
    @ApiModelProperty(notes = "年龄",required = true,example="30")
    private Integer age;

}
