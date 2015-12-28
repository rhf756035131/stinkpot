package com.example.renhaifeng.myapplication;



import java.io.Serializable;

/**
 * Created by rahul on 15-12-28.
 */
public class times implements Serializable {
    private static final long serialVersionUID = 1L;
    public String times_titel;
    public String times_subinfo;
    public int  status;
    public long   id;

    public times(String times_titel, String times_subinfo,int status,int id) {
        super();
        this.times_titel = times_titel;
        this.times_subinfo = times_subinfo;
        this.status = status;
        this.id =id;
    }
}
