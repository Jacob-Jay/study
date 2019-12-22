package com.jq.service.impl;

import com.jq.dao.Person;
import com.jq.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019/12/22 14:31
 */
public class UserServiceImpl implements UserService {
    private static final List<Person> PERSON_LIST = new ArrayList<Person>();

    static {
        PERSON_LIST.add(new Person("1","jq"));
        PERSON_LIST.add(new Person("2","jq2"));
        PERSON_LIST.add(new Person("3","jq3"));
        PERSON_LIST.add(new Person("4","jq4"));
    }

    public List<Person> personList() {
        return PERSON_LIST;
    }
}
