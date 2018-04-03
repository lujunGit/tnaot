package com.mongcent.tnaot.model;

import java.util.Date;

public class BaseEntity {

    private Date create_time;
    private Date update_time;

    public BaseEntity(Date create_time, Date update_time) {
        this.create_time = create_time;
        this.update_time = update_time;
    }

    public BaseEntity() {}

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "create_time=" + create_time +
                ", update_time=" + update_time +
                '}';
    }
}
