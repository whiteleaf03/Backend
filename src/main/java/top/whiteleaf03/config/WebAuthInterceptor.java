package top.whiteleaf03.config;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import top.whiteleaf03.modal.entity.User;
import top.whiteleaf03.utils.RedisUtil;
import top.whiteleaf03.utils.Result;
import top.whiteleaf03.utils.TokenUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author WhiteLeaf03
 */
@Component
@Slf4j
public class WebAuthInterceptor implements HandlerInterceptor {
    private final List<String> WHITE_LIST_URLS = Arrays.asList("/api/user/login", "/api/user/register", "/api/user/captcha");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        log.info("请求访问接口: {}", uri);
        if (WHITE_LIST_URLS.contains(uri)) {
            // 登录请求 放行
            return true;
        } else if (uri.endsWith("/export")) {
            // 导出请求 放行
            return true;
        } else {
            String token = request.getHeader("Authorization");
            //token判空
            if (StrUtil.isBlank(token)) {
                String responseJson = JSONUtil.toJsonStr(Result.authFailed("token为空"));
                setResponseMsg(response, responseJson);
                return false;
            }

            //获取id
            String id;
            try {
                id = TokenUtils.parseToken(token);
            } catch (RuntimeException e) {
                String jsonStr = JSONUtil.toJsonStr(Result.authFailed("token非法"));
                setResponseMsg(response, jsonStr);
                return false;
            }

            //判断是否过期
            Map<String, String> tokenMap = RedisUtil.getCacheObject("[OnlineUserToken]id:" + id);
            String validToken = tokenMap.get("token");
            if (StrUtil.isBlank(validToken) || !validToken.equals(token)) {
                String jsonStr = JSONUtil.toJsonStr(Result.authFailed("token已过期"));
                setResponseMsg(response, jsonStr);
                return false;
            }

            //判断用户是否存在
            JSONObject userJson = RedisUtil.getCacheObject("[OnlineUserInfo]id:" + id);
            User user = JSONUtil.toBean(userJson, User.class);
            if (ObjectUtil.isNull(user)) {
                String jsonStr = JSONUtil.toJsonStr(Result.authFailed("用户不存在"));
                setResponseMsg(response, jsonStr);
                return false;
            }

            //检验通过 将用户信息存入上下文
            RequestContextHolder.getRequestAttributes().setAttribute("UserInfo", user, RequestAttributes.SCOPE_REQUEST);
        }
        return true;
    }

    void setResponseMsg(HttpServletResponse response, String responseJson) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter responseWriter = response.getWriter();
        responseWriter.print(responseJson);
        responseWriter.flush();
        responseWriter.close();
    }
}
