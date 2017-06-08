package com.aibinxiao.handle;

import com.aibinxiao.domain.Result;
import com.aibinxiao.exception.GirlException;
import com.aibinxiao.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by aibin on 2017/6/8.
 */
@ControllerAdvice
public class ExceptionHandle {

    private final static Logger Logger = LoggerFactory.getLogger(ExceptionHandle.class);

    // 处理Controller中抛出的异常
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e){
        if(e instanceof GirlException){
            GirlException girlException = (GirlException) e;
            return ResultUtil.error(girlException.getCode(), girlException.getMessage());
        }
        Logger.error("【系统异常】{}",e);// 打印其他异常的日志信息
        return ResultUtil.error(-1, "未知错误");
    }
}
