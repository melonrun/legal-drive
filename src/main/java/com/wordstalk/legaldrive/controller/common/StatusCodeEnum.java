package com.wordstalk.legaldrive.controller.common;

/**
 * Created by y on 2018/1/7.
 */
public enum StatusCodeEnum{
    CLIENT_ERROR_BAD_REQUEST(400),
    CLIENT_ERROR_NOT_ACCEPTABLE(406),
    SERVER_ERROR_INTERNAL(500),
    CLIENT_ORFORBIDDEN(403),
    SERCER_NOT_RESPONSE(404);

    private final int value;

    StatusCodeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    /**
     * Find a the enum type by its integer value, as defined in the Thrift IDL.
     *
     * @return null if the value is not found.
     */
    public static StatusCodeEnum findByValue(int value) {
        switch (value) {
            case 400:
                return CLIENT_ERROR_BAD_REQUEST;
            case 406:
                return CLIENT_ERROR_NOT_ACCEPTABLE;
            case 500:
                return SERVER_ERROR_INTERNAL;
            case 403:
                return CLIENT_ORFORBIDDEN;
            case 404:
                return SERCER_NOT_RESPONSE;
            default:
                return null;
        }
    }
}
