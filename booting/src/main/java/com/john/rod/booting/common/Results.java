package com.john.rod.booting.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Results<T> {

    private Integer code ;

    private List<String> errerMsgs;

    private T data;


    public static Results<List<String>> argumentNotValid(List<String> msgs){
        return new Results<List<String>>(400,msgs,null);
    }
}
