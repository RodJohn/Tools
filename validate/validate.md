

使用 断言和validate进行校验


参考

    http://jinnianshilongnian.iteye.com/blog/1495594
    
    
    
    
    
    @RequestMapping("/validJson1")  
    @ResponseBody  
    public JsonResult processSubmitjson(@RequestBody ValidModel vm,  
            HttpServletRequest request) {  
        JsonResult jsonRst = new JsonResult();  
          
        Set<ConstraintViolation<ValidModel>> set = validator.validate(vm);  
        for (ConstraintViolation<ValidModel> violation : set) {  
      
            String propertyPath = violation.getPropertyPath().toString();  
            ;  
            String message = violation.getMessage();  
            log.error("invalid value for: '" + propertyPath + "': "  
                    + message);  
        }         
        if (!set.isEmpty()){  
            jsonRst.setSuccess(false);  
            jsonRst.setMsg("输入有误!");  
            return jsonRst;  
        }  
      
        jsonRst.setSuccess(true);  
        jsonRst.setMsg("输入成功!");  
        return jsonRst;  
    }     
    
    
    
Bean Validate和Method Validate

方法级别的校验

    http://jinnianshilongnian.iteye.com/blog/1495594     