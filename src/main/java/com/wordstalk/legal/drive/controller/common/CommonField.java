package com.wordstalk.legal.drive.controller.common;

/**
 * Created by y on 2018/1/16.
 */
public class CommonField {

    public static final String SESSION_ASSERTION_LD = "_const_legal_drive_assertion_";

    /* session 超时时间 4h */
    public static final int SESSION_TIMEOUT_MS = 86400;

    public static final int STATUS_ENABLE = 1;
    public static final int STATUS_DISABLE = 0;

    public static final String ROLE_PARTNER_DISPLAY = "合伙人";
    public static final String TEAM_ALL = "ALL";
    public static final int DEFAULT_PAGE_SIZE = 10;

    public static final int NORMAL_FILE = 1;
    public static final int NORMAL_FOLDER = 2;
    public static final int SHARED_FOLDER = 3;

    public static final String DEFAULT_ROOT_PATH = "/root";
}
