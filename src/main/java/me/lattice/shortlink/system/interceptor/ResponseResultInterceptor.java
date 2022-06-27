package me.lattice.shortlink.system.interceptor;

import me.lattice.shortlink.annotation.ResponseResult;
import me.lattice.shortlink.annotation.SimpleController;
import me.lattice.shortlink.common.constant.HttpConstant;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @description: API 出参拦截器
 * @author: Lattice
 * @date 2022/6/27 09:51
 */
public class ResponseResultInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            final HandlerMethod handlerMethod = (HandlerMethod) handler;
            Class<?> clazz = handlerMethod.getBeanType();
            Method method = handlerMethod.getMethod();
            if (clazz.isAnnotationPresent(ResponseResult.class)) {
                request.setAttribute(HttpConstant.RESP_RESULT_ANNOTATION, clazz.getAnnotation(ResponseResult.class));
            } else if (method.isAnnotationPresent(ResponseResult.class)) {
                request.setAttribute(HttpConstant.RESP_RESULT_ANNOTATION, method.getAnnotation(ResponseResult.class));
            } else if (clazz.isAnnotationPresent(SimpleController.class)) {
                request.setAttribute(HttpConstant.RESP_RESULT_ANNOTATION, clazz.getAnnotation(SimpleController.class));
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
