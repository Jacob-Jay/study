# 对象与类

- get方法返回引用成员变量应当返回clone对象，而不是直接返回，否者会存在线程安全

- final仅仅是指引用不可变，而对象的属性值是可以改变的

- 传递方式

  - 引用传递：可以改变传递值的值，即原对象可能会指向另一个对象

  - 值传递：不能改变指向的对象，但是可以改变指向对象的属性值

    java属于值传递，对于基础对象就是传递的本身，引用对象是传递的引用拷贝的值

- 重载：方法的签名包括方法名称，参数类型、参数位置，通过签名确定重载。即只能通过参数的类型与顺序确定，不能通过返回值重载，因为返回值不属于签名

- 成员变量会自动初始化，局部变量不会

- 初始化顺序     静态代码块   代码块   构造方法    赋值

- finalize方法回收前调用，清理资源，但是不知道什么时候才会执行，所以不建议使用

- ```java
  public static final String name = "sss";   //静态引用
  ```

## 建议

- 成员变量私有化
- 对成员变量初始化
- 不要过多使用基础类
- 职责尽量少
- 不是所有域都需要set或get方法
- 命名有意义

# 继承

- 构造方法中调用super必须放在第一句
- 是否使用继承使用 is -a 判断
- 子类重写方法权限不能必父类小
- 

# final

final类所有方法都是final的，单成员变量不是