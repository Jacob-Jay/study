package com.base.Widly;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019/8/5 22:18
 */
public class  Demoa<T> {
    private T a;

    public Demoa(T a) {

        this.a = a;
    }

    public static void main(String[] args) {
      Demoa<Integer> demoa = new Demoa<>(1);
//      Demoa<Integer>[]demoas = new Demoa<Integer>[10]; //
        Collections.nCopies(100, "");
        Arrays.asList("", "");
    }

}
