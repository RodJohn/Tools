package com.john.rod.booting.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

@Slf4j
public class Context {


    private static ThreadLocal<CurUser> curUser = new ThreadLocal<>();

    public static void setCurUser(CurUser user) {
        log.debug("threadid：{} before set user: {}",Thread.currentThread().getId(),curUser.get());
        curUser.set(user);
        log.debug("threadid：{} set user: {}",Thread.currentThread().getId(),curUser.get());
    }

    public static CurUser getCurUser() {
        CurUser user = curUser.get();
        Assert.notNull(user," no token is presidented ");
        return user;
    }
    public static void clearCurUser() {
        curUser.remove();
        log.debug("threadid：{} after remove user: {}",Thread.currentThread().getId(),curUser.get());
    }

}
