package com.example.renhaifeng.myapplication;

import java.io.Serializable;

/**
 * Created by Rahul on 2015/11/9.
 */

public class Device implements Serializable {
    public  static final int  DEVICE_SOCKET=1;
    public static final int DEVICE_CLOSESTOOL=2;
    private static final long serialVersionUID = 1L;
    public int id;
    public String name;
    public String address;
    public int devicetype;

    public Device(int id, String name, String address,int devicetype) {
        super();
        this.id = id;
        this.name = name;
        this.address = address;
        this.devicetype=devicetype;
    }
}
