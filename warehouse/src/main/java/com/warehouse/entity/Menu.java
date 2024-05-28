package com.warehouse.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author promefire
 * @since 2024-05-09
 */
@ApiModel(value="Menu对象", description="")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "菜单编码")
    @TableField("menuCode")
    private String menucode;

    @ApiModelProperty(value = "菜单名字")
    @TableField("menuName")
    private String menuname;

    @ApiModelProperty(value = "菜单级别")
    @TableField("menuLevel")
    private String menulevel;

    @ApiModelProperty(value = "菜单的父code")
    @TableField("menuParentCode")
    private String menuparentcode;

    @ApiModelProperty(value = "点击触发的函数")
    @TableField("menuClick")
    private String menuclick;

    @ApiModelProperty(value = "权限 0超级管理员，1表示管理员，2表示普通用户，可以用逗号组合使用")
    @TableField("menuRight")
    private String menuright;

    @ApiModelProperty(value = "对应Vue菜单组件")
    @TableField("menuComponent")
    private String menucomponent;

    @ApiModelProperty(value = "菜单图标")
    @TableField("menuIcon")
    private String menuicon;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenucode() {
        return menucode;
    }

    public void setMenucode(String menucode) {
        this.menucode = menucode;
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }

    public String getMenulevel() {
        return menulevel;
    }

    public void setMenulevel(String menulevel) {
        this.menulevel = menulevel;
    }

    public String getMenuparentcode() {
        return menuparentcode;
    }

    public void setMenuparentcode(String menuparentcode) {
        this.menuparentcode = menuparentcode;
    }

    public String getMenuclick() {
        return menuclick;
    }

    public void setMenuclick(String menuclick) {
        this.menuclick = menuclick;
    }

    public String getMenuright() {
        return menuright;
    }

    public void setMenuright(String menuright) {
        this.menuright = menuright;
    }

    public String getMenucomponent() {
        return menucomponent;
    }

    public void setMenucomponent(String menucomponent) {
        this.menucomponent = menucomponent;
    }

    public String getMenuicon() {
        return menuicon;
    }

    public void setMenuicon(String menuicon) {
        this.menuicon = menuicon;
    }
}
