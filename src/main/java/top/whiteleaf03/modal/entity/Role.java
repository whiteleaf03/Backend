package top.whiteleaf03.modal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author WhiteLeaf03
 */
@Data
public class Role {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String level;

    public Role(String level) {
        this.level = level;
    }
}
