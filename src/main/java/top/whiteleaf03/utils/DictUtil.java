package top.whiteleaf03.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.whiteleaf03.modal.entity.DictData;
import top.whiteleaf03.service.DictService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author WhiteLeaf03
 */
@Component
public class DictUtil {
    private static DictService dictService;

    @Autowired
    public void setDictService(DictService dictService) {
        DictUtil.dictService = dictService;
    }

    public static Map<Integer, String> getDictMap(String dictInfoKey) {
        List<DictData> dictDataList = dictService.getDictDataByKey(dictInfoKey);
        Map<Integer, String> dictMap = new HashMap<>();
        for (DictData dictData : dictDataList) {
            dictMap.put(dictData.getIndex(), dictData.getName());
        }
        return dictMap;
    }
}
