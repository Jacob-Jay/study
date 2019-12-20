package com.jq.seriliza.marsharlling;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-12-20 10:22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable{

    private static final long serialVersionUID = 2387087800113857726L;
    private String name;
    private int age;
}
