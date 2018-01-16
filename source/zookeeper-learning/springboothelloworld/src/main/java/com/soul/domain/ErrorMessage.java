package com.soul.domain;

/***
 * @author wangkun1
 * @version 2018/1/12 
 */
public class ErrorMessage {

    private String msg;
    private Integer code;

    public ErrorMessage(FailEnum failEnum){
        this.code = failEnum.intValue();
        this.msg = failEnum.getMsg();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public enum FailEnum{

        FAIL_ENUM("请求错误！",400);

        FailEnum(String msg, int code) {
            this.code = code;
            this.msg = msg;
        }
        private Integer code;
        private String msg;

        public int intValue(){
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }
}
