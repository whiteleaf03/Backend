package top.whiteleaf03;

import cn.hutool.crypto.digest.DigestUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.whiteleaf03.mapper.UserMapper;
import top.whiteleaf03.modal.entity.User;

@SpringBootTest
public class UserTest {
    @Autowired
    UserMapper userMapper;

    @Test
    void addDefaultUser() {
        userMapper.insert(new User() {{
            setUsername("admin");
            setPassword(DigestUtil.bcrypt("admin"));
            setRoleId(1L);
            setNickname("admin管理员");
        }});
    }
}
