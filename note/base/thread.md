## 创建

- runnable
  - 实现runnable，重写run方法
  - 使用runnable构建一个thread
  - thread.start()

- thread
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

- 静态条件

- 锁对象 ReentrantLock  

  - 不能使用带资源的try（）{}  由于 try和finally的执行顺序以及异常？？？
  - 公平锁 ReentrantLock（true）q倾向于等待时间长的
  - 可重入
  - lock方法不会中止或打断所以使用tryLock防止等待期间被打断造成死锁
  - 在一定时间尝试锁定

- 条件对象condition

  - 调用await会释放lock？？？？？
  - sign随机叫醒同一条件等待的线程
  - signall叫醒同一条件所有等待的线程
  - 必须由lock创建不能自己new
  - 等待一定时间
  - 中断会结结束等待并抛出异常

- synchronized

  - 使用对象内部的锁锁定某个资源
  - 只能使用锁订对象的wait与notify

- volatile

- final

  - 保证多线程之间看见的构造完成的对象而不是null

- 原子性

- 死锁，

  - 比如两个线程互相等待对方的资源，圆桌效应

- 线程局部变量

- 读写锁

  - ```java
    static java.util.concurrent.locks.ReadWriteLock lock = new ReentrantReadWriteLock();
    static Lock readLock = lock.readLock();
    static Lock writeLock = lock.writeLock();
    ```

  -  writeLock.lock(); //排斥读写锁

  - readLock.lock();  //共享读锁，排斥写锁，所以读出来都是10的整数

## 阻塞队列

## 线程安全的集合

## callable与future

## 执行器

- 线程池
  - 为了减少开销
  - 控制线程数量
  - ![1565168124965](/1565168124965.png)

- fork-join
  - 根据任务的耗时情况决定分割任务的度

## 同步器

![1565225727207](/1565225727207.png)

- 信号量
- 倒计时门栓CountDownLatch
  - 检测什么时候完成
- 栅栏CyclicBarrier
  - 设定一个目标数，达到目标数则放行
  - 可以给栅栏设置一个任务执行
  - 任意一个等待线程离开其他线程都会抛出BrokenBarrierException
- 交换器
- 同步队列