package com.base.life;

import static com.base.life.StaticImport.*;
//import static com.base.life.StaticIMport2.*;
/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-08-02 16:21
 */
public class life {

    @Override
    protected void finalize() throws Throwable {
        System.out.println("回收了");
    }

    public static void main(String[] args) {
        System.out.println(name);
    }
}
