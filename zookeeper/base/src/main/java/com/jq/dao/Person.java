package com.jq.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019/12/22 14:16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Person implements Serializable {

    private static final long serialVersionUID = 731052543380882446L;
    private String id;
    private String name;



}
