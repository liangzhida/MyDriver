package com.example.driver.Bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
@Entity
public class Bean {
    @Id(autoincrement = true)
    private Long _id;
    private String name;
    private String psw;
    @Generated(hash = 1050657979)
    public Bean(Long _id, String name, String psw) {
        this._id = _id;
        this.name = name;
        this.psw = psw;
    }
    @Generated(hash = 80546095)
    public Bean() {
    }
    public Long get_id() {
        return this._id;
    }
    public void set_id(Long _id) {
        this._id = _id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPsw() {
        return this.psw;
    }
    public void setPsw(String psw) {
        this.psw = psw;
    }


}
