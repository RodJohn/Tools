package com.john.rod.booting.common;

import lombok.Data;

import java.util.List;

@Data
public class Results<T> {

    private Long code ;

    private List<String> msgs;

    private T data;

}
