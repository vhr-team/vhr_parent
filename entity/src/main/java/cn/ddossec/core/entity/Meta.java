package cn.ddossec.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author 30315
 * @title: Meta
 * @projectName vhr_parent
 * @description: TODO
 * @date 2020-04-088:36
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Meta {

    private Boolean keepAlive;
    private Boolean requireAuth;

}
