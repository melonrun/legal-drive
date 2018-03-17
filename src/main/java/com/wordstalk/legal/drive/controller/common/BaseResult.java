package com.wordstalk.legal.drive.controller.common;

import com.wordstalk.legal.drive.inject.configuration.ServerConfiguration;

import java.io.Serializable;

/**
 * Created by y on 2018/1/7.
 */
public class BaseResult<T> implements Serializable {

    private static final long serialVersionUID = 3213858706482139601L;

    protected Head head;
    protected T data;

    public BaseResult(Head head){
        this.head = head;
    }

    public BaseResult(){}

    public Head getHead(){
        return head;
    }

    public void setHead(Head head){
        this.head = head;
    }

    public T getData(){
        return data;
    }

    public static class Head implements Serializable {

        private static final long serialVersionUID = -9208552996402549160L;

        public Head(ServerConfiguration serverConfiguration) {
            this.server = serverConfiguration.getName();
            this.version = serverConfiguration.getVersion();
        }

        private int code = 200;
        private long time = System.currentTimeMillis();
        private String msg = "[^_^]";
        private String server = null;
        private String version = null;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public long getTime() {
            return time;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getServer() {
            return server;
        }

        public String getVersion() {
            return version;
        }

        @Override
        public String toString() {
            return "Result.Head{" +
                    "errcode=" + code +
                    ", time=" + time +
                    ", message='" + msg + '\'' +
                    ", server='" + server + '\'' +
                    ", version='" + version + '\'' +
                    '}';
        }
    }

}
