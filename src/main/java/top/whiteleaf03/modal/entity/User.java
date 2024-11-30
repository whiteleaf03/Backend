package top.whiteleaf03.modal.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author WhiteLeaf03
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 对应角色
     */
    @TableField(exist=false, select=false)
    private Role role;

    public void setRole(String level) {
        this.role = new Role(level);
    }
}
