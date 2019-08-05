### th:text

### th:if

进行逻辑判断，正确时显示当前元素

### th：with

定义一个局部变量，只在当前元素及其子代有效

### th:object

将模型对象声明为一个对象，可以使用*或者#object调用

### ~~th:attr~~

动态的修改指定属性的值,很少使用

```html
<form action="/ab/cs" th:attr="action=@{/cs}">
    //类似使用attr中的值替换action的值
</form>
<form action="/ab/cs" th:attr="action=@{/cs},title='asad',class='asdad'">
    //可以同时设置多个，使用都好分割
</form>
```

### th:alt-title 

等价于 th:alt+th:title一起使用

### th:attrappend

给指定属性追加值

th:classappend 等价于th:attrappend="class=${' ' + cssStyle}"

th:styleappend 等价于th:attrappend="style=${' ' + cssStyle}"

```html
<input type="button"  class="btn" th:attrappend="class=${' ' + cssStyle}"/>
<input type="button"  class="btn" th:classappend="${' ' + cssStyle}"/>  //等价于上面
<input type="button"  class="btn warning" />
```

