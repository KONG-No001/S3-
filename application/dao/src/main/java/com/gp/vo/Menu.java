package com.gp.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName()
public class Menu {
    @TableId("menu_id")
    private Integer id;
    @TableField(value = "menu_field")
    private String field;
    @TableField(value = "menu_title")
    private String title;
    @TableField(value = "menu_url")
    private String url;
    @TableField(value = "menu_icon")
    private String icon;
    @TableField(value = "menu_print")
    private Integer print;
    @TableField(value = "menu_prints")
    private String prints;
    @TableField(value = "menu_index")
    private Integer i;
    @TableField(value = "menu_level")
    private Integer level;
    @TableField(value = "menu_is_parent")
    private Integer isParent;
    @TableField(exist=false)
    private Object[] children;

    public Menu() {
    }


    public Menu(Integer id, String field, String title, String url, String icon, Integer print, String prints, Integer i, Integer level, Integer isParent, Object[] children) {
        this.id = id;
        this.field = field;
        this.title = title;
        this.url = url;
        this.icon = icon;
        this.print = print;
        this.prints = prints;
        this.i = i;
        this.level = level;
        this.isParent = isParent;
        this.children = children;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", field='" + field + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", icon='" + icon + '\'' +
                ", print=" + print +
                ", prints='" + prints + '\'' +
                ", i=" + i +
                ", level=" + level +
                ", isParent=" + isParent +
                ", children=" + children +
                '}';
    }

    public Object getChildren() {
        return children;
    }

    public void setChildren(Object[] children) {
        this.children = children;
    }

    public Integer getIsParent() {
        return isParent;
    }

    public void setIsParent(Integer isParent) {
        this.isParent = isParent;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getPrint() {
        return print;
    }

    public void setPrint(Integer print) {
        this.print = print;
    }

    public String getPrints() {
        return prints;
    }

    public void setPrints(String prints) {
        this.prints = prints;
    }

    public Integer getI() {
        return i;
    }

    public void setI(Integer i) {
        this.i = i;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
