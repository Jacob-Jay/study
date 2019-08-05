package com.base.thread.firstsee;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-07-30 16:04
 * 通过继承创建线程
 */
public class CreateByExtends {

    public static void main(String[] args) {
        ThreadExtends threadExtends = new ThreadExtends(100000);
        threadExtends.start();
        for (int i = 0; i < 100; i++) {
            System.out.println("main========="+i);
        }

//        Runtime.getRuntime().exit(1);
    }

}

/**
 *
 *
 * <ul>
 *     <li>
 *         properties
 *         <ul>
 *             <li>
 *                 优先级：高优先级几率更大，但不绝对，初始值与创建线程一致
 *             </li>
 *             <li>
 *                 守护线程：只有守护线程创建的才是守护线程
 *             </li>
 *             <li>
 *                 主线程：即Java虚拟机通过main方法启动的线程，在以下情况销毁
 *                 <ul>
 *                     <li>
 *                         调用exit方法
 *                         <pre>
 *                             <code>
 *                                  Runtime.getRuntime().exit(1); //非0代表不是正常退出
 *                             </code>
 *                         </pre>
 *                     </li>
 *                     <li>
 *                         所有非后台线程已死亡，即任务执行完毕
 *                     </li>
 *                 </ul>
 *             </li>
 *         </ul>
 *     </li>
 *
 *      <li>
 *          创建方式
 *          <ul>
 *              <li>
 *                  继承thread   启用方式调用<code>thread.start()</code>
 *              </li>
 *
 *              <li></li>
 *          </ul>
 *      </li>
 *
 * </ul>
 */
class ThreadExtends extends Thread{

    private  int size;

    ThreadExtends(int size){
        this.size = size;
    }

    @Override
    public void run() {
        int i = 0;
        while (i < size) {
            i++;
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("获取数据完毕");
    }
}
