package top.whiteleaf03.aop;

import cn.hutool.core.util.ArrayUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import top.whiteleaf03.modal.entity.User;
import top.whiteleaf03.utils.RedisUtil;
import top.whiteleaf03.utils.Result;

/**
 * @author WhiteLeaf03
 */
@Slf4j
@Aspect
@Component
public class TokenAspect {
    private final RedisUtil redisUtil;

    @Autowired
    public TokenAspect(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    @Pointcut("@annotation(top.whiteleaf03.aop.TokenCheck)")
    public void tokenRequiredMethods() {
    }

    @Around("tokenRequiredMethods() && @annotation(tokenCheck)")
    public Object checkToken(ProceedingJoinPoint joinPoint, TokenCheck tokenCheck) throws Throwable {
        User user = (User) RequestContextHolder.getRequestAttributes().getAttribute("UserInfo", RequestAttributes.SCOPE_REQUEST);

        //鉴权
        if (ArrayUtil.isNotEmpty(tokenCheck.value()) && !ArrayUtil.contains(tokenCheck.value(), user.getRole())) {
            return Result.authFailed("无权限访问");
        }

        return joinPoint.proceed();
    }
}
