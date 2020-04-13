package cn.ddossec.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 30315
 * @title: Role
 * @projectName vhr_parent
 * @description: TODO
 * @date 2020-04-1216:19
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "role")
public class Role {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "name")
    private String name;

    /**
     * 角色名称
     */
    @TableField(value = "nameZh")
    private String namezh;

    public static final String COL_ID = "id";

    public static final String COL_NAME = "name";

    public static final String COL_NAMEZH = "nameZh";
}
