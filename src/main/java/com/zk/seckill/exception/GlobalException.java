package com.zk.seckill.exception;

import com.zk.seckill.result.CodeMsg;

/**
 * @auther ZhangKe
 * @date 2018/11/22 14:57
 * 全局异常处理器
 */
public class GlobalException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private CodeMsg codeMsg;

    public GlobalException(CodeMsg codeMsg) {
        super(codeMsg.toString());
        this.codeMsg = codeMsg;
    }

    public CodeMsg getCodeMsg() {
        return codeMsg;
    }

}

