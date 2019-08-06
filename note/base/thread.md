## 创建

### runnable

- 实现runnable，重写run方法
- 使用runnable构建一个thread
- thread.start()

### thread

- 继承thread重写run方法
- thread.start()

## 中断

- 通过调用interrupt来中止，但是线程内部需要循环检测,并且不能阻塞

  ```java
  //检测是否中断
  
  Thread.currentThread().isInterrupted()  //不会清除中断状态
  Thread.interrupted（）;//会清除中断状态    
  ```

- 在中断后调用sleep方法会清除中断状态，并会抛出异常

- 对中断异常的处理

  - 将线程标记为中断
  - 抛出中断异常由调用者处理

## 线程状态

- new		新建状态
  - 创建出来但是还没有调用strat方法
- runnable  可运行
  - 调用了start方法就是可运行状态，既包括等带cpu时间片也包括得到机会在运行的
- blocked
- waiting
- timeWaited  
  - 具有时间参数的等待
- determinated
  - 执行完成或者抛出异常

## 线程属性

- 线程优先级
  - 优先级越高cpu调度的几率越大
  - 默认为创建他的父线程的优先级，从1到10递增
  - yield方法会让出执行权由cpu重新选择比当前线程优先级高或等于的线程，包括自身
- 守护线程
  - 不要访问任何资源，因为不知道什么时候会中断没有机制检测
- 异常处理
  - 不能抛出一检查异常
  - 抛出未检查异常可以为线程设置一个UncaughtExceptionHandler处理器处理

## 同步

同步是为了解决多个线程对同一资源的修改

