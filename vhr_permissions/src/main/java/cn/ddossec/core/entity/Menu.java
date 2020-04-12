package cn.ddossec.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 30315
 * @title: Menu
 * @projectName vhr_parent
 * @description: TODO
 * @date 2020-04-088:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "menu")
public class Menu {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /* 接口Url */
    @TableField(value = "url")
    private String url;

    /* 访问的路径 */
    @TableField(value = "path")
    private String path;

    /* 该菜单对应的组件 */
    @TableField(value = "component")
    private String component;

    /* 菜单名称 */
    @TableField(value = "name")
    private String name;

    /* 菜单图标 */
    @TableField(value = "icon")
    private String icon;

    @TableField(value = "keepAlive")
    private Boolean keepalive;

    @TableField(value = "requireAuth")
    private Boolean requireauth;

    /* 父菜单ID */
    @TableField(value = "parentId")
    private Integer parentid;

    /* 菜单是否启用 */
    @TableField(value = "enabled")
    private Boolean enabled;

    @TableField(exist = false)
    private Meta meta;

    /* 子菜单项 */
    @TableField(exist = false)
    private List<Menu> children;

    @TableField(exist = false)
    private List<Role> roles;

    public static final String COL_ID = "id";

    public static final String COL_URL = "url";

    public static final String COL_PATH = "path";

    public static final String COL_COMPONENT = "component";

    public static final String COL_NAME = "name";

    public static final String COL_ICON = "icon";

    public static final String COL_KEEPALIVE = "keepAlive";

    public static final String COL_REQUIREAUTH = "requireAuth";

    public static final String COL_PARENTID = "parentId";

    public static final String COL_ENABLED = "enabled";
}
