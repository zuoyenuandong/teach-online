package com.kuang.service.base.exception;

import com.kuang.common.base.result.ResultCodeEnum;
import lombok.Data;

@Data
public class MyException  extends RuntimeException{
    private Integer code;

    public MyException(String message,Integer code){
        super(message);
        this.code = code;
    }
    public MyException(ResultCodeEnum resultCodeEnum){
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

    @Override
    public String toString() {
        return "MyException{" +
                "code=" + code +
                ",message="+ this.getMessage()+
                '}';
    }
}
