# chapter5

### th:attr

一次性设置多个值

### th:xx

给特定的属性设置

### th:xx-yy

组合设置相同值

### append   preAppend

在头部或尾部添加

### th：whatever

whatever 可以替换未任何自定义属性以便使用${}

# cahpter6:迭代

th:each

```html
<table>
    <tr  th:each="item : ${items}">
        <td th:text="${item.name}"></td>
        <td  th:text="${item.desc}"></td>
    </tr>
</table>
```

以下java类型都可以用于迭代

- list
- Any object implementing java.util.Iterable
- Any object implementing java.util.Enumeration .
- Any object implementing java.util.Iterator , whose values will be used as they are returned by the iterator,
  without the need to cache all values in memory.
- Any object implementing java.util.Map . When iterating maps, iter variables will be of class java.util.Map.Entry .
- Any array.
- Any other object will be treated as if it were a single-valued list containing the object itself.

迭代时会有一些属性：

- The current iteration index, starting with 0. This is the index property.
- The current iteration index, starting with 1. This is the count property.
- The total amount of elements in the iterated variable. This is the size property.
- The iter variable for each iteration. This is the current property.
- Whether the current iteration is even or odd. These are the even/odd boolean properties.
- Whether the current iteration is the first one. This is the first boolean property.
- Whether the current iteration is the last one. This is the last boolean property.

# chapter7 condition



## th:if

为true时会显示该dom

不仅仅只是boolean的true代表正确

- 非null情况
  - 非0数字
  - 非‘0‘字符
  - 非 false off  no  字符串
  - 其他所有类型
- null情况
  - 统统为false

## th:unless

if 的对立面  即等价于  th:if="not ${boolean}"

## switch case

```html
<div th:switch="${user.role}">
    <p th:case="'admin'">User is an administrator</p>
    <p th:case="#{roles.manager}">User is a manager</p>
    <p th:case="*">User is some other thing</p>    //默认值
</div>
```

# chapter8 layout

```html
<div th:insert="~{footer :: copy}"></div> 等价于
<div th:insert="footer :: copy"></div>
```

### 语法

```html
~{templatename::selector} 	   //引入特定页面的特定片段
~{templatename}					//引入整个页面
~{::selector}" or "~{this::selector}"  //引入当前页或者父页的特定片段
~{footer :: #copy-section}  //引入指定页面指定id的dom元素
```

### defferent

- th:insert  直接插入，即保留宿主标签也保留自身标签
- th:replace 使用自身替换宿主
- ~~th:include 宿主包含去除标签的自身~~

```html
<footer th:fragment="copy">
&copy; 2011 The Good Thymes Virtual Grocery
</footer>
```

```html
<body>
...
    <div th:insert="footer :: copy"></div>
    <div th:replace="footer :: copy"></div>
    <div th:include="footer :: copy"></div>
</body>
```

```html
<body>
...
    <div>
        <footer>
        &copy; 2011 The Good Thymes Virtual Grocery
        </footer>
    </div>
    <footer>
        &copy; 2011 The Good Thymes Virtual Grocery
     </footer>
    <div>
   		 &copy; 2011 The Good Thymes Virtual Grocery
    </div>
</body>
```

### 替换传参

```html
<footer th:fragment="copy(p1,p2)">
    <p th:text="${p1}+${p2}">
        
    </p>
</footer>
```

```html
<div th:replace="::fragment ('1','2')">...</div>
<div th:replace="::fragment (p2='2',p1='1')">...</div>
```

```html
<footer>
    <p >
        3
    </p>
</footer>
```

### th:assert

断言，



### 替换替换

### th:reomve

- all 全部移除
- body 保留最外层
- tag 保留子代 移除最外层
- all-but-first 只保留第一个子代和（外层？？？）
- none 不做任何事

# chapter9 local variables

### th:with

声明一个局部本地变量使用

# chapter10 优先权

# chapter11

### 注释

<!-- -->会被直接保留

<!--/*  ssasda */ -->/**/之间内容被清除

```html
<!--/*/  ssasda /*/ --> /*/   /*/之间的解析之后才会显示？？？
```

### 区块

th:block



# chapter12  inline

直接写在文本中

```html
<span>
	sasd [[${name}]]
</span>
等价于
<span  th:text="${name}">
</span>
```



### [[]]等价于th:text

### [()]等价于th:utext

### th:inline='none'

禁用[[]],[()]

### js

```js
<script th:inline="javascript">
...
var username = [(${session.user.name})];
...
</script>
```

### css

```css
<style th:inline="css">
.[[${classname}]] {
text-align: [[${align}]];
}
</style> 
```



