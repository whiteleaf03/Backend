package top.whiteleaf03.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import top.whiteleaf03.modal.entity.User;

/**
 * @author WhiteLeaf03
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select u.username as username, u.password as password, r.`level` as role, u.nickname as nickname from `user` u join `role` r on u.`role_id` = r.id;")
    User findUserByUsername(String username);
}
