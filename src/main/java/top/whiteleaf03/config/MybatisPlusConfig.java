package top.whiteleaf03.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * @author WhiteLeaf03
 */
@Configuration
public class MybatisPlusConfig implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        Date currentDate =  new Date();
        this.strictInsertFill(metaObject, "createTime", Date.class, currentDate);
        this.strictInsertFill(metaObject, "updateTime", Date.class, currentDate);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "updateTime", Date.class, new Date());
    }
}
