package top.whiteleaf03.modal.dto;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.poi.ss.formula.functions.T;

/**
 * @author WhiteLeaf03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageParams {
    /**
     * 页号 从0开始
     */
    Integer page;

    /**
     * 每页大小
     */
    Integer size;

    public void check() {
        if (page != null && page > -1 && size != null && size > 0) {
            return;
        }
        throw new RuntimeException("分页参数错误");
    }

    public IPage<T> toPage() {
        if (page != null && page > -1 && size != null && size > 0) {
            return new Page<>(page, size);
        }
        throw new RuntimeException("分页参数错误");
    }
}
