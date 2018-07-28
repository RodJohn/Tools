

# 1 官方指导

    https://projectlombok.org/features/all


# 3 pojo简化


##  getter/setter

作用

    注释字段或者类
    自动生成相应属性的getter/setter

示例

    import lombok.AccessLevel;
    import lombok.Getter;
    import lombok.Setter;
    public class GetterSetterExample {
        @Getter @Setter private int age = 10;
        @Setter(AccessLevel.PROTECTED) private String name;
    }

    相当于

    public class GetterSetterExample {
        private int age = 10;
        private String name;
        public int getAge() {
            return age;
        }
        public void setAge(int age) {
            this.age = age;
        }
        protected void setName(String name) {
            this.name = name;
        }
    }


## 构造函数

    @NoArgsConstructor
        自动生成无参数构造函数。
    @AllArgsConstructor
        自动生成全参数构造函数。    
    
## ToString  
  
    按顺序（以“,”分隔）打印你的类名称以及每个字段。
    也可以设置不包含哪些字段/@ToString(exclude = {“id”,”name”})


## EqualsAndHashCode

    实现equals()方法和hashCode()方法
    
    
## Data

    注解在类上；
    提供类所有属性的 getting 和 setting 方法，
    此外还提供了equals、canEqual、hashCode、toString 方法

## 常用


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Person {
    
        private String id;
        private String name;
        private String identity;
            
    }


  