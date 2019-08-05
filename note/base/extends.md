# extend

## abstract

- 抽象类不一定包含抽抽象方法，但是包含抽象方法的一定是抽象类
- 抽象类的抽象方法其实时模板方法设计模式，具体实现交由子类实现

## equals and hashcode

- equals方法使用insetence时必须由父类提供并且时final的，否则使用getClass
- 方法实现通常需要五步

## 访问权限

public >protect>default>private

尽量使用private不破坏封装性，提供一个方法访问

## list

容量确定之后可以压缩空间

# 反射

## method

## filed

## constructor

# 接口

## Comparable

- 接口方法全为public 
- 常量：public static  final

## clone

拷贝会互相影响

### 浅克隆：

### 深克隆：递归克隆内部

# 内部类

内部类是为了隐藏实现，通常是为了协作实现一些功能，如果不需要访问外围类的属性时就应当声明为静态内部类

，非静态内部类含有外部类的引用所以可以访问任何外围类的资源，并且只有内部类可以用private和static修饰

Outer out = new Outer();

## 成员内部类



```java
Outer.Inner in = out.new Inner();
```



## 局部内部类

类似于局部变量，只在有限作用域可见

```java
public void inner(){
	private class LocalClass{
	//.....
	}
}
```



## 匿名内部类

直接实现，不定义

```java
new thread(
    //////////////////////////////////////////
	new Runnable(){
		public void run(){
			//....
		}
	}
    /////////////////////////////////////////
).start();
```



## 静态内部类

只是为了协助外部类进行功能的实现存放一些状态，而不会访问外围类的属性

```java
Outer.inner i = new Outer.Inner();
```

# 异常处理

- ​	throwble
  - error【未检查】
  - exception
    - runtimeException【未检查】
    - other
- catch(a|b){}
- 对于已检查异常最好抛出至合适地方处理
- 再次抛出建议包装原异常a.initCase(e)
- final中的renturn会覆盖try的return