package top.whiteleaf03.modal.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 数据字典 键值
 * @author WhiteLeaf03
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DictData extends BaseEntity {
    /**
     * 字典id
     */
    private Long dictInfoId;

    /**
     * 键名
     */
    private String name;

    /**
     * 序号
     */
    @TableField(value = "`index`")
    private Integer index;
}
