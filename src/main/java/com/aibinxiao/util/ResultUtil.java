package com.aibinxiao.util;


import com.aibinxiao.domain.Result;

/**
 * Created by aibin on 2017/6/8.
 */
public class ResultUtil {

    public static Result success(Object Object){
        Result result = new Result();
        result.setCode(0);
        result.setMsg("成功");
        result.setData(Object);
        return result;
    }

    public static Result success(){
        return success(null);
    }

    public static Result error(Integer code, String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
