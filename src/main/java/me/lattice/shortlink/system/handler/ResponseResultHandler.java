package me.lattice.shortlink.system.handler;

import lombok.extern.slf4j.Slf4j;
import me.lattice.shortlink.common.result.Result;
import me.lattice.shortlink.common.constant.HttpConstant;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: API出参增强器
 * @author: Lattice
 * @date 2022/6/27 10:01
 */
@Slf4j
@ControllerAdvice
public class ResponseResultHandler implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert servletRequestAttributes != null;
        HttpServletRequest request = servletRequestAttributes.getRequest();
        /* 判断是否需要统一包装，如果attribute中有，则表示需要处理，返回true */
        Object annotation = request.getAttribute(HttpConstant.RESP_RESULT_ANNOTATION);
        return !(annotation == null);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (!(body instanceof Result)) {
            return Result.success(body);
        }
        return body;
    }

    /***
     * @description: 系统异常，内部错误包装返回
     * @param: [e] 全局异常
     * @author: Vayne.Luo
     * @date: 2022/6/27 10:08
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result<?> exceptionHandler(Exception e) {
        log.error(e.getMessage(), e);
        return Result.failure(HttpStatus.INTERNAL_SERVER_ERROR.value(), "系统异常，请联系管理员");
    }
}
