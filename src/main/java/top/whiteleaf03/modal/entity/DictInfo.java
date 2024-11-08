package top.whiteleaf03.modal.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 数据字典 总表
 * @author WhiteLeaf03
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DictInfo extends BaseEntity {
    /**
     * 字典名
     */
    private String name;

    /**
     * 字典key
     */
    @TableField(value = "`key`")
    private String key;

    /**
     * 描述
     */
    private String description;
}
