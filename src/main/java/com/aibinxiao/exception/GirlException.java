package com.aibinxiao.exception;

import com.aibinxiao.enums.ResultEnum;

/**
 * Created by aibin on 2017/6/8.
 */
public class GirlException extends RuntimeException {
    // 继承运行时异常RuntimeException，才会进行事务回滚
    // 为了区分不同的异常，我们添加一个code属性来区分
    private Integer code;

    public GirlException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
