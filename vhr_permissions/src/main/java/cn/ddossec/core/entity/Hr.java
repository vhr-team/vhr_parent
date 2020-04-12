package cn.ddossec.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author 30315
 * @title: Hr
 * @projectName vhr_parent
 * @description: TODO
 * @date 2020-04-0615:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "hr")
public class Hr implements UserDetails {
    /**
     * hrID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 姓名
     */
    @TableField(value = "name")
    private String name;

    /**
     * 手机号码
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 住宅电话
     */
    @TableField(value = "telephone")
    private String telephone;

    /**
     * 联系地址
     */
    @TableField(value = "address")
    private String address;

    @TableField(value = "enabled")
    private Boolean enabled;

    /**
     * 用户名
     */
    @TableField(value = "username")
    private String username;

    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;

    @TableField(value = "userface")
    private String userface;

    @TableField(value = "remark")
    private String remark;

    public static final String COL_ID = "id";

    public static final String COL_NAME = "name";

    public static final String COL_PHONE = "phone";

    public static final String COL_TELEPHONE = "telephone";

    public static final String COL_ADDRESS = "address";

    public static final String COL_ENABLED = "enabled";

    public static final String COL_USERNAME = "username";

    public static final String COL_PASSWORD = "password";

    public static final String COL_USERFACE = "userface";

    public static final String COL_REMARK = "remark";

    /* 该字段数据中没有，所以需要加上以下注解，备注为不存在的字段 */
    @TableField(exist = false)
    private List<Role> roles;

    /* 该用户的角色 */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>(roles.size());
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    /* 账户是否没有过期 */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /* 账户是否没有被锁定 */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /* 密码是否过期 */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /* 是否启用 */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
