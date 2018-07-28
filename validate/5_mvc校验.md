

# 1 概述

    在springmvc中,
    使用@Valid注释requestbody中的入参对象,
    对入参对象进行Validate校验,
    并使用ControllerAdvice捕捉校验失败的异常


# 3 启用校验
   

## 3.1 定义实体校验规则  

方法

    对入参对象添加校验注解

示例    

    @Data
    @NoArgsConstructor
    public class UserEditVo {
    
        @NotNull(message="name不能为空")
        private String name ;
    
        @NotNull(message="age不能为空")
        @Min(value = 1,message = "age不能小于1")
        private Integer age;
    
    }
 
## 3.2 启用校验  

方法

    对入参对象添加@Valid.
    启用校验之后,如果参数不符合Model中定义的话，程序中就回抛出400异常，并提示错误信息。

示例    

    @RestController
    @RequestMapping("user")
    public class UserController {
    
        @PutMapping
        public void update(@Valid@RequestBody UserEditVo user ) {
            // do 
        }
        
    }
 

# 5 处理检验信息  


## 5.1 Errors封装

方案

    在Controller的方法中使用Errors的对象封装检验结果

示例

    @RestController  
    @RequestMapping("task")  
    public class TaskController {  
      
        @PostMapping
        public ResponseEntity post(@Valid @RequestBody Task task, Errors errors) {  
            if (errors.hasErrors()) {  
                return new ResponseEntity(new ApiErrors(errors), HttpStatus.BAD_REQUEST);  
            }  
            return new ResponseEntity(task.save(), HttpStatus.CREATED);  
        }  
    }
   

## 5.2 ControllerAdvice拦截


方法

    1.使用ControllerAdvice捕捉Controller中的异常
        可以指定Controller类型
    2.使用ExceptionHandler拦截@Valid检验失败的异常
        异常类型MethodArgumentNotValidException
    3.解析异常消息
        e.getBindingResult().getAllErrors()                              
        

示例    


    @Slf4j
    @ControllerAdvice(annotations = RestController.class)
    public class RestExceptionHandler {
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
    }


# 7  使用 切面

    将验证逻辑封装为一个AOP，当需验证的对象前有@valid标注和@RequestBody标注时开始验证 

    public class CustomerValidatorAOP {  
      private Validator validator;  
       
      @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")  
      private void controllerInvocation() {  
      }  
       
      @Around("controllerInvocation()")  
      public Object aroundController(ProceedingJoinPoint joinPoint) throws Throwable {  
       
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();  
        Method method = methodSignature.getMethod();  
        Annotation[] annotationList = method.getAnnotations();  
       /* for(Annotation anno:annotationList){ 
            System.out.println(ResponseBody.class.isInstance(anno)); 
        } 
    */  
        Annotation[][] argAnnotations = method.getParameterAnnotations();  
        String[] argNames = methodSignature.getParameterNames();  
        Object[] args = joinPoint.getArgs();  
       
        for (int i = 0; i < args.length; i++) {  
          if (hasRequestBodyAndValidAnnotations(argAnnotations[i])) {  
            Object ret = validateArg(args[i], argNames[i]);  
            if(ret != null){  
                return ret;  
            }  
          }  
        }  
       
        return joinPoint.proceed(args);  
      }  
       
      private boolean hasRequestBodyAndValidAnnotations(Annotation[] annotations) {  
        if (annotations.length < 2)  
          return false;  
       
        boolean hasValid = false;  
        boolean hasRequestBody = false;  
       
        for (Annotation annotation : annotations) {  
          if (Valid.class.isInstance(annotation))  
            hasValid = true;  
          else if (RequestBody.class.isInstance(annotation))  
            hasRequestBody = true;  
       
          if (hasValid && hasRequestBody)  
            return true;  
        }  
        return false;  
      }  
       
      
      private JsonResult validateArg(Object arg, String argName) {  
        BindingResult result = getBindingResult(arg, argName);  
        validator.validate(arg, result);  
        if (result.hasErrors()) {  
          JsonResult jsonresult = new JsonResult();  
          jsonresult.setSuccess(false);  
          jsonresult.setMsg("fail");  
          return jsonresult;  
        }  
        return null;  
      }  
       
      private BindingResult getBindingResult(Object target, String targetName) {  
        return new BeanPropertyBindingResult(target, targetName);  
      }  
       
      @Required  
      public void setValidator(Validator validator) {  
        this.validator = validator;  
      }  
    

# 8 

概述

    Spring validator 方法级别的校验
    JSR和Hibernate validator的校验只能对Object的属性进行校验，不能对单个的参数进行校验，spring 在此基础上进行了扩展，添加了MethodValidationPostProcessor拦截器，可以实现对方法参数的校验，实现如下:


示例


    1、实例化MethodValidationPostProcessor
    
    @Bean
        public MethodValidationPostProcessor methodValidationPostProcessor() {
            return new MethodValidationPostProcessor();
        }
     
    
    2、在所要实现方法参数校验的类上面添加@Validated，如下
    
    @RestController
    @Validated
    public class ValidateController {
    }
     
    
    3、在方法上面添加校验规则:
    
      @RequestMapping(value = "/test", method = RequestMethod.GET)
        public String paramCheck(@Length(min = 10) @RequestParam String name) {
            System.out.println(name);
            return null;
        }
     
    
    当方法上面的参数校验失败,spring 框架就回抛出异常
    
    {
      "timestamp": 1476108200558,
      "status": 500,
      "error": "Internal Server Error",
      "exception": "javax.validation.ConstraintViolationException",
      "message": "No message available",
      "path": "/test"
    }        
    
