package com.john.rod.booting.common;


import com.john.rod.business.model.SysPojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Data
@Log4j
@NoArgsConstructor
@AllArgsConstructor
public class CurUser {

    public static final String CUR_USER_TOKEN = "curUserToken";

    Long userId;
    String token;

}
