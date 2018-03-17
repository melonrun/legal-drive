package com.wordstalk.legal.drive.controller.common;


/**
 * Created by y on 2018/1/7.
 */
public class Result<T> extends BaseResult<Result.Data<T>>{

    public Result(Head head){
        this.head = head;
        this.data = new Data<T>();
    }

    public void setResult(T t) {
        data.setResult(t);
    }

    public void setMsg(String msg) {
        data.setMsg(msg);
    }

    public void setStatus(boolean status) {
        data.setStatus(status);
    }

    public static class Data<T> {
        private boolean status;
        private T result;
        private String msg;

        public boolean getStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }

        public T getResult() {
            return result;
        }

        public void setResult(T result) {
            this.result = result;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

}
