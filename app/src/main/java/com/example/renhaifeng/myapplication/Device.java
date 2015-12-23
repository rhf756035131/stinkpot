package com.example.renhaifeng.myapplication;

import java.io.Serializable;

/**
 * Created by Rahul on 2015/11/9.
 */
public class Device implements Serializable {
    private static final long serialVersionUID = 1L;
    public int id;
    public String name;
    public String address;
    public int photo;

    public Device(int id, String name, String address) {
        super();
        this.id = id;
        this.name = name;
        this.address = address;
    }
}
