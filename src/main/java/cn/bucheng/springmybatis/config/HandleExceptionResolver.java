package cn.bucheng.springmybatis.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
// 返回对象
@ResponseBody
public class HandleExceptionResolver {


    // 可以对不同的异常做不同的处理
    @ExceptionHandler(Exception.class)
    public Object resolveException(HttpServletRequest request, Exception e) throws Exception {
        System.out.println("-------------->error:");
        return new Object();

    }
}
