package top.whiteleaf03.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import top.whiteleaf03.mapper.UserMapper;
import top.whiteleaf03.modal.entity.User;
import top.whiteleaf03.service.UserService;
import top.whiteleaf03.utils.RedisUtil;
import top.whiteleaf03.utils.Result;
import top.whiteleaf03.utils.TokenUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WhiteLeaf03
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getCurrentUser() {
        return (User) RequestContextHolder.getRequestAttributes().getAttribute("UserInfo", RequestAttributes.SCOPE_REQUEST);
    }

    @Override
    public Result login(String username, String password) {
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("username", username);
        User user = userMapper.selectOne(qw);
        if (user == null) {
            return Result.error("用户不存在，请检查用户名或密码是否正确");
        }
        if (DigestUtil.bcryptCheck(password, user.getPassword())) {
            String token = TokenUtils.createToken(user.getId());
            Map<String, Object> map = new HashMap() {{
                put("token", token);
                put("nickname", user.getNickname());
                put("role", user.getRole());
            }};
            RedisUtil.setCacheObject("[OnlineUserToken]", token);
            return Result.success(map);
        }
        return Result.error("用户名或密码错误");
    }
}
