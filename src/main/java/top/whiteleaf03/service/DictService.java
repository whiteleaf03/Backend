package top.whiteleaf03.service;

import top.whiteleaf03.modal.dto.AddDictDTO;
import top.whiteleaf03.modal.dto.PageParams;
import top.whiteleaf03.modal.entity.DictData;
import top.whiteleaf03.utils.Result;

import java.util.List;

/**
 * @author WhiteLeaf03
 */
public interface DictService {
    Result getDictInfoList(PageParams pageParams, String name, String key, String describe);

    List<DictData> getDictDataByDictInfoId(Long id);

    Result addDict(AddDictDTO addDictDTO);

    Result update(List<DictData> dictDataList);

    Result addDictData(List<DictData> dictDataList);

    List<DictData> getDictDataByKey(String dictInfoKey);
}
