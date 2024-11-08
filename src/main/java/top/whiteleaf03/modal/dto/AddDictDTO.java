package top.whiteleaf03.modal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.whiteleaf03.modal.entity.DictData;
import top.whiteleaf03.modal.entity.DictInfo;

import java.util.List;

/**
 * @author WhiteLeaf03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddDictDTO {
    /**
     * 字典名
     */
    private String name;

    /**
     * 字典key
     */
    private String key;

    /**
     * 描述
     */
    private String description;

    /**
     * 字典键值对列表
     */
    private List<DictData> dictDataList;

    public DictInfo getDictInfo() {
        DictInfo dictInfo = new DictInfo();
        dictInfo.setName(this.name);
        dictInfo.setKey(this.key);
        dictInfo.setDescription(this.description);
        return dictInfo;
    }
}
