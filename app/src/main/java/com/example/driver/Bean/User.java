package com.example.driver.Bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class User {
    @Id(autoincrement = true)
    private long _id;
    private String name;
    private long psw;
    @Generated(hash = 91047209)
    public User(long _id, String name, long psw) {
        this._id = _id;
        this.name = name;
        this.psw = psw;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public long get_id() {
        return this._id;
    }
    public void set_id(long _id) {
        this._id = _id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public long getPsw() {
        return this.psw;
    }
    public void setPsw(long psw) {
        this.psw = psw;
    }

}
