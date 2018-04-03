package com.mongcent.tnaot.model;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
public class Channel  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private Integer parent_id;
    private Integer sort_order;
    private Byte is_enabled;
    private Byte is_default;
    private String image_url;
    private Byte is_video_area;
    private String description;
    private String create_user;
    private Byte is_fixed;
    private String remark;
    private Date create_time;
    private Date update_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public Integer getSort_order() {
        return sort_order;
    }

    public void setSort_order(Integer sort_order) {
        this.sort_order = sort_order;
    }

    public Byte getIs_enabled() {
        return is_enabled;
    }

    public void setIs_enabled(Byte is_enabled) {
        this.is_enabled = is_enabled;
    }

    public Byte getIs_default() {
        return is_default;
    }

    public void setIs_default(Byte is_default) {
        this.is_default = is_default;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public Byte getIs_video_area() {
        return is_video_area;
    }

    public void setIs_video_area(Byte is_video_area) {
        this.is_video_area = is_video_area;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreate_user() {
        return create_user;
    }

    public void setCreate_user(String create_user) {
        this.create_user = create_user;
    }

    public Byte getIs_fixed() {
        return is_fixed;
    }

    public void setIs_fixed(Byte is_fixed) {
        this.is_fixed = is_fixed;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

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

    public Channel() {
    }

    public Channel(String name, Integer parent_id, Integer sort_order, Byte is_enabled, Byte is_default,
            String image_url, Byte is_video_area, String description, String create_user, Byte is_fixed,
            String remark, Date create_time, Date update_time) {
        this.name = name;
        this.parent_id = parent_id;
        this.sort_order = sort_order;
        this.is_enabled = is_enabled;
        this.is_default = is_default;
        this.image_url = image_url;
        this.is_video_area = is_video_area;
        this.description = description;
        this.create_user = create_user;
        this.is_fixed = is_fixed;
        this.remark = remark;
        this.create_time = create_time;
        this.update_time = update_time;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parent_id=" + parent_id +
                ", sort_order=" + sort_order +
                ", is_enabled=" + is_enabled +
                ", is_default=" + is_default +
                ", image_url='" + image_url + '\'' +
                ", is_video_area=" + is_video_area +
                ", description='" + description + '\'' +
                ", create_user='" + create_user + '\'' +
                ", is_fixed=" + is_fixed +
                ", remark='" + remark + '\'' +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                '}';
    }
}
