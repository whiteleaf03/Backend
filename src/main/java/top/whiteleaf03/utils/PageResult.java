package top.whiteleaf03.utils;

import java.util.Collection;

/**
 * @author WhiteLeaf03
 */
public class PageResult extends Result {
    private Long total;

    public PageResult(Integer status, String msg, Long total, Collection<?> list) {
        super(status, msg, list);
        this.total = total;
    }

    public static PageResult success(Collection<?> list) {
        return new PageResult(0, "OK", (long) list.size(), list);
    }

    public static PageResult success(Long total, Collection<?> list) {
        return new PageResult(0, "OK", total, list);
    }
}
