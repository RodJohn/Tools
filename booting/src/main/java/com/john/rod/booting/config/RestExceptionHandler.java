package com.john.rod.booting.config;


import com.john.rod.booting.common.Results;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice(annotations = RestController.class)
public class RestExceptionHandler {

    //逻辑校验
    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private Results<String> runtimeExceptionHandler(Exception e) {
        log.error("throw a exception {}", e);
        return new Results<>();
    }

    //入参物理校验
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Results<List<String>> handleException(MethodArgumentNotValidException e) {
        log.debug("throw a exception {}", e);
        List<String> msgs = e.getBindingResult().getAllErrors().stream()
                .map(error -> error.getDefaultMessage())
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        return Results.argumentNotValid(msgs);
    }

    //入参物理校验
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Results<List<String>> handle1Exception(ConstraintViolationException e) {
        log.debug("throw a exception {}", e);
        List<String> msgs = e.getConstraintViolations().stream()
                .map(violation -> violation.getMessage())
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        return Results.argumentNotValid(msgs);
    }

}
