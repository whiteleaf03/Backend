package top.whiteleaf03.aop;

import cn.hutool.core.util.ArrayUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import top.whiteleaf03.modal.entity.User;
import top.whiteleaf03.utils.Result;

/**
 * @author WhiteLeaf03
 */
@Slf4j
@Aspect
@Component
public class RoleAspect {
    @Pointcut("@annotation(top.whiteleaf03.aop.RoleCheck)")
    public void roleRequiredMethods() {
    }

    @Around("roleRequiredMethods() && @annotation(roleCheck)")
    public Object checkToken(ProceedingJoinPoint joinPoint, RoleCheck roleCheck) throws Throwable {
        User user = (User) RequestContextHolder.getRequestAttributes().getAttribute("UserInfo", RequestAttributes.SCOPE_REQUEST);

        //鉴权
        if (ArrayUtil.isNotEmpty(roleCheck.value()) && !ArrayUtil.contains(roleCheck.value(), user.getRole())) {
            return Result.authFailed("无权限访问");
        }

        return joinPoint.proceed();
    }
}
