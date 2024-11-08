package top.whiteleaf03.modal.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * @author WhiteLeaf03
 */
@Data
public class User {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(fill = FieldFill.INSERT, value = "create_time")
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE, value = "update_time")
    private Date updateTime;

    @TableLogic(value = "0", delval = "1")
    private Boolean isDelete = false;

    private String username;

    private String password;

    private String nickname;

    private Long roleId;

    @TableField(exist=false, select=false)
    private Role role;

    public void setRole(String level) {
        this.role = new Role(level);
    }
}
