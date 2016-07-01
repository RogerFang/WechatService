package edu.whu.irlab.client.interceptor;

import edu.whu.irlab.client.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Roger on 2016/6/4.
 */
public class RegisterInterceptor extends HandlerInterceptorAdapter {

    private static Logger logger = LoggerFactory.getLogger(RegisterInterceptor.class);
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String openId = null;
        String requestOpenId = request.getParameter("openId");
        String sessionOpenId = (String) request.getSession().getAttribute("openId");

        String method = request.getMethod();

        if (method.toUpperCase().equals("POST")){
            return super.preHandle(request, response, handler);
        }

        if (requestOpenId != null){
            openId = requestOpenId;
        }else if (sessionOpenId != null){
            openId = sessionOpenId;
        }else {
            logger.info("Interceptor: openId=" + openId);
            return false;
        }

        System.out.println("Interceptor: openId=" + openId);
        if (sessionOpenId == null){
            request.getSession().setAttribute("openId", openId);
        }

        if (userService.findWechatUserByOpenId(openId) == null){
            request.getRequestDispatcher("/WEB-INF/jsp/member/login.jsp").forward(request, response);
            System.out.println("Interceptor: to login！");
            // response.sendRedirect("/member/register");
            return false;
        }

        return super.preHandle(request, response, handler);
    }
}
