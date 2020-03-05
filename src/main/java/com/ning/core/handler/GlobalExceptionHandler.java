package com.ning.core.handler;

import com.ning.core.exception.LimitException;
import com.ning.core.model.Result;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Order(-1)
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(LimitException.class)
    public Result limitException(HttpServletResponse response, LimitException ex) {
        Result result = new Result();
        result.setData(ex.getMessage());
        return result;
    }

}
