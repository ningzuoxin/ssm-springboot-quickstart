package com.ning.core.handler;

import com.ning.core.exception.LimitException;
import com.ning.core.model.JsonResult;
import com.ning.core.model.Result;
import org.springframework.core.annotation.Order;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

@Order(-1)
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 接口防刷异常处理
     *
     * @param response
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(LimitException.class)
    public Result limitException(HttpServletResponse response, LimitException ex) {
        Result result = new Result();
        result.setData(ex.getMessage());
        return result;
    }

    /**
     * 参数校验异常处理
     *
     * @param e
     * @return
     */
//    @ResponseBody
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public JsonResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
//        return JsonResult.fail(-1001, e.getBindingResult().getFieldError().getDefaultMessage());
//    }

    /**
     * post请求参数校验抛出的异常
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public JsonResult methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        //获取异常中随机一个异常信息
        String defaultMessage = e.getBindingResult().getFieldError().getDefaultMessage();
        return JsonResult.fail(defaultMessage);
    }

    /**
     * get请求参数校验抛出的异常
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(BindException.class)
    public JsonResult bindExceptionHandler(BindException e) {
        //获取异常中随机一个异常信息
        String defaultMessage = e.getBindingResult().getFieldError().getDefaultMessage();
        return JsonResult.fail(defaultMessage);
    }

    /**
     * 请求方法中校验抛出的异常
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    public JsonResult constraintViolationExceptionHandler(ConstraintViolationException e) {
        // 获取异常中第一个错误信息
        String message = e.getConstraintViolations().iterator().next().getMessage();
        return JsonResult.fail(message);
    }

}
