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
public class Person  implements Serializable{
    private static final long serialVersionUID = 580496172880843752L;
    private String name;
    private int age;
    private String code;
}
