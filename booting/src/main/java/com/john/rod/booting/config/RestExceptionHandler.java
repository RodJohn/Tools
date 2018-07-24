package com.john.rod.booting.config;


import com.john.rod.booting.common.Results;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@ControllerAdvice(annotations = RestController.class)
public class RestExceptionHandler {

    //逻辑校验
    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private Results<String> runtimeExceptionHandler(Exception e) {
        log.error("throw a exception {}",e);
        return new Results<>();
    }




}
