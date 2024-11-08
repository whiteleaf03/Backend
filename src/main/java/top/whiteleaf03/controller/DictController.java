package top.whiteleaf03.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.whiteleaf03.modal.dto.AddDictDTO;
import top.whiteleaf03.modal.dto.PageParams;
import top.whiteleaf03.modal.entity.DictData;
import top.whiteleaf03.service.DictService;
import top.whiteleaf03.utils.Result;

import java.util.List;

/**
 * @author WhiteLeaf03
 */
@RestController
@RequestMapping("dict")
public class DictController {
    @Autowired
    private DictService dictService;

    @GetMapping("list")
    public Result getDictInfoList(PageParams pageParams, String name, String key, String describe) {
        return dictService.getDictInfoList(pageParams, name, key, describe);
    }

    @GetMapping("")
    public Result getDictById(Long dictInfoId) {
        List<DictData> dictDataList = dictService.getDictDataByDictInfoId(dictInfoId);
        return Result.success(dictDataList);
    }

    @PostMapping("")
    public Result addDict(@RequestBody AddDictDTO addDictDTO) {
        return dictService.addDict(addDictDTO);
    }

    @PostMapping("data")
    public Result addDictData(@RequestBody List<DictData> dictDataList) {
        return dictService.addDictData(dictDataList);
    }

    @PutMapping("data")
    public Result updateDict(@RequestBody List<DictData> dictDataList) {
        return dictService.update(dictDataList);
    }
}
