



# expression syntax

## simple 表达式

### 域对象

可以通过以下对象调用方法

```java
#ctx : the context object.  //
#vars: the context variables.
#locale : the context locale.
#request : (only in Web Contexts) the HttpServletRequest object.
#response : (only in Web Contexts) the HttpServletResponse object.
#session : (only in Web Contexts) the HttpSession object.
#servletContext : (only in Web Contexts) the ServletContext object
```

### 帮助对象

thymeleaf内置的一些方便使用的帮助对象

```html
#execInfo : information about the template being processed.

#messages : methods for obtaining externalized messages inside variables expressions, in the same way as they

would be obtained using #{…} syntax.

#uris : methods for escaping parts of URLs/URIs

#conversions : methods for executing the configured conversion service (if any).

#dates : methods for java.util.Date objects: formatting, component extraction, etc.

#calendars : analogous to #dates , but for java.util.Calendar objects.

#numbers : methods for formatting numeric objects.

#strings : methods for String objects: contains, startsWith, prepending/appending, etc.

#objects : methods for objects in general.
#bools : methods for boolean evaluation.
#arrays : methods for arrays.
#lists : methods for lists.
#sets : methods for sets.
#maps : methods for maps.
#aggregates : methods for creating aggregates on arrays or collections.
#ids : methods for dealing with id attributes that might be repeated (for example, as a result of an iteration).
```



### ${}

值表达式可以通过

- ```
  ${name}
  ```

  等同于

  ```
  ctx.getVariable("name");
  ```

  

- ```java
  ${session.user.name}   //.代表使用get
  ${session['user']['name']}  //['']也代表使用get
  ```

  等同于

  ```java
  ((User) ctx.getVariable("session").get("user")).getName();
  ```

- ```java
  ${personsArray[0].name}  //对象是一个集合可以使用下标获取该位置的对象来使用
  ```

- ```java
  ${person.createCompleteName()}   //可以使用对象的方法，不带参数
  ${person.createCompleteNameWithSeparator('-')} //可以使用对象的方法，带参数
  ```

  

### #{}

消息表达式

### *{}

选择表达式

```html
<p th:text="${order.id}">orderId</p>
<p th:object="${order}"  th:text="*{id}"> //可以使用替换.直接使用*{param}获取属性
<p th:object="${order}"  th:text="${#object.id}">
```

### @{}

链接表达式,根据当前页、上下文、服务器、协议拼接相对路径，以及拼接参数或者替换路径参数{}

#### pageRelative

```
@{aaa/xxx/}
```



#### contextRelative

```
@{/aaa/xx}
```



#### serverRelative

```
@{~/aaa/xx}
```



#### protocla

```
@{//aaa/xx}
```

```html
<a th:href="@{/order(id=${order.id},name='jq')}">/ab/order?id=10&name=jq</a><br>
<a th:href="@{/order/{id}(id=${order.id},name='jq')}">/ab/10/order?name=jq</a><br>
<a th:href="@{~/order(id=${order.id},name='jq')}">/order?id=10&name=jq</a><br>
<a th:href="@{//order(id=${order.id},name='jq')}">http://order?id=10&name=jq</a><br>
```

### ~{}

片段

## literals 字面量

### text

使用单引号包围的文本


```html
<p th:text="'文本常量使用单引号抱起来'">sds</p>
```
### number

数字直接写，还可以进行运算
```html
<p th:text="11">10</p>
<p th:text="11+40">15</p>
```
### boolean

if结果为真才显示该元素

```html
<p th:if="${bool}==true"> it is true</p>
<p th:if="${bool==true}==true"> it is true</p>
```

### null
```html
<p th:if="${none==null}==true"> it is none</p>
<p th:if="${none}==null"> it is none</p>
```
### Literal tokens

## 运算

### 文本

连接运算符  	+
```html
  <p th:text="'welcome'+${bool}">  append</p>
```
替换运算符	

简化了连接操作，等价于上面的操作

```html
<p th:text="|welcome${bool}|">  replace</p>
```

### 算术

 运算符  + - * / %
```html
 		 <div th:with="pro=(3%3==0)">赋值给pro
    		<p  th:if="${pro}==true">赋值正确显示</p>   //th:with生成局部变量只在该div内有效
        </div>
```
### 布尔

and   or  

! not 


```html
 	<p th:if=" 9  ge 3">
            <span th:text="'1 和 3 相比'+( !(1 lt 3) ? '<':'>')"></span>
    </p>
```
### 比较

大小比较： >	<	 >= 	 <=	(  gt	  lt	 ge  	le )

相等比较： =	!=  	(eq	ne)

### 条件

if  then  

if then  else

### default

```html
<span th:text="${stw}?:'not this name'">

</span>
<span th:text="${stw}?:_">							//表达式为null时使用默认值，_代表静态值
        not this name
</span>
```

