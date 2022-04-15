package com.ztwo.book.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ztwo.book.bean.Router;
import com.ztwo.book.common.util.JwtUtils;
import com.ztwo.book.common.vo.ResultCode;
import com.ztwo.book.common.vo.ResultVo;
import com.ztwo.book.service.RouterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
 * @Author ZTwo
 * @Date 2021/12/21 15:15
 */
@Component
@Slf4j
public class TokenInterceptor implements HandlerInterceptor {

    @Value("${jwt.tokenHead}")
    private String TOKEN_PREFIX;
    @Value("${jwt.account_user}")
    private String ACCOUNT_U;
    @Value("${jwt.account_merchant}")
    private String ACCOUNT_M;

    private List<Router> visitorRouters;
    private List<Router> userRouters;
    private List<Router> merchantRoutes;

    @Resource
    private JwtUtils jwtUtils;
    @Resource
    private RouterService routerService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (userRouters == null || merchantRoutes == null) initRouters();
        if("options".equalsIgnoreCase(request.getMethod())) return true;

        //封装成路由对象
        String url = request.getRequestURI().substring(request.getContextPath().length());
        String method = request.getMethod();
        Router router = new Router(url, method);

        //判断是否是游客路径
        if (visitorRouters.contains(router)) {
            return true;
        }

        //请求头里面读取token
        String token = request.getHeader("Authorization");
        if (token == null || "".equals(token) || !token.startsWith(TOKEN_PREFIX + "_")) {
            log.info("{} : Unknown token => method:{}", request.getServletPath(), request.getMethod());
            //拦截
            doRespond(response, 200, ResultVo.error(ResultCode.UNAUTHORIZED));
            return false;
        }

        //解析令牌
        Map<String, Object> claim = jwtUtils.verifyToken(token);
        //路由访问过滤
        String account = (String) claim.get("account");
        if (ACCOUNT_U.equals(account) && !userRouters.contains(router)) {
            doRespond(response, 200, ResultVo.error(ResultCode.UNAUTHORIZED));
            return false;
        }
        if (ACCOUNT_M.equals(account) && !merchantRoutes.contains(router)) {
            doRespond(response, 200, ResultVo.error(ResultCode.UNAUTHORIZED));
            return false;
        }
        //添加到上下文
        Integer userId = (Integer) claim.get("userId");
        ContextHandler.setUserId(userId);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ContextHandler.shutdown();
    }

    /**
     * 初始化路由
     */
    private void initRouters() {
        visitorRouters = routerService.getVisitorRouters();
        userRouters = routerService.getUserRouters();
        merchantRoutes = routerService.getMerchantRouters();
    }

    private void doRespond(HttpServletResponse response, Integer status, ResultVo resultVo) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; character=utf-8");
        response.setStatus(status);
        PrintWriter out = response.getWriter();
        out.write(new ObjectMapper().writeValueAsString(resultVo));
        out.flush();
        out.close();
    }
}
