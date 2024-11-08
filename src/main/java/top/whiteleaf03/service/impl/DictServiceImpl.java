package top.whiteleaf03.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.whiteleaf03.mapper.DictDataMapper;
import top.whiteleaf03.mapper.DictInfoMapper;
import top.whiteleaf03.modal.dto.AddDictDTO;
import top.whiteleaf03.modal.dto.PageParams;
import top.whiteleaf03.modal.entity.DictData;
import top.whiteleaf03.modal.entity.DictInfo;
import top.whiteleaf03.service.DictService;
import top.whiteleaf03.utils.PageResult;
import top.whiteleaf03.utils.Result;

import java.util.List;

/**
 * @author WhiteLeaf03
 */
@Service
public class DictServiceImpl implements DictService {
    @Autowired
    private DictInfoMapper dictInfoMapper;

    @Autowired
    private DictDataMapper dictDataMapper;

    @Override
    public PageResult getDictInfoList(PageParams pageParams, String name, String key, String description) {
        QueryWrapper<DictInfo> qw = new QueryWrapper<>();
        if (StrUtil.isNotBlank(name)) {
            qw.like("name", "%" + name + "%");
        }
        if (StrUtil.isNotBlank(key)) {
            qw.like("name", "%" + key + "%");
        }
        if (StrUtil.isNotBlank(description)) {
            qw.like("name", "%" + description + "%");
        }
        Page<DictInfo> page = dictInfoMapper.selectPage(pageParams.toPage(), qw);
        return PageResult.success(page.getTotal(), page.getRecords());
    }

    @Override
    public List<DictData> getDictDataByDictInfoId(Long dictInfoId) {
        QueryWrapper<DictData> queryWrapper = new QueryWrapper<>();
        return dictDataMapper.selectList(queryWrapper.eq("dict_info_id", dictInfoId));
    }

    @Override
    @Transactional
    public Result addDict(AddDictDTO addDictDTO) {
        DictInfo dictInfo = addDictDTO.getDictInfo();
        dictInfoMapper.insert(dictInfo);
        List<DictData> dictDataList = addDictDTO.getDictDataList();
        Long dictInfoId = dictInfo.getId();
        if (dictInfoId == null) {
            throw new RuntimeException("新增字典信息失败");
        }
        for (DictData dictData : dictDataList) {
            dictData.setDictInfoId(dictInfoId);
        }
        dictDataMapper.insert(dictDataList);
        return Result.success();
    }

    @Override
    @Transactional
    public Result update(List<DictData> dictDataList) {
        dictDataMapper.updateById(dictDataList);
        return Result.success();
    }

    @Override
    public Result addDictData(List<DictData> dictDataList) {
        dictDataMapper.insert(dictDataList);
        return Result.success();
    }

    @Override
    public List<DictData> getDictDataByKey(String dictInfoKey) {
        QueryWrapper<DictInfo> queryWrapper = new QueryWrapper<>();
        List<DictInfo> dictInfoList = dictInfoMapper.selectList(queryWrapper.eq("`key`", dictInfoKey));
        if (dictInfoList.isEmpty()) {
            throw new RuntimeException("字典不存在!");
        } else {
            return this.getDictDataByDictInfoId(dictInfoList.get(0).getId());
        }
    }
}
