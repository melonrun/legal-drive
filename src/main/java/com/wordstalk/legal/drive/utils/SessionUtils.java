package com.wordstalk.legal.drive.utils;

import com.wordstalk.legal.drive.access.service.profile.model.EntryDTO;
import com.wordstalk.legal.drive.controller.common.CommonField;

import javax.servlet.http.HttpSession;

/**
 * Created by y on 2018/1/16.
 */
public class SessionUtils {

    public static boolean putSessionAfterLogin(HttpSession session, EntryDTO entryDTO){
        session.setAttribute(CommonField.SESSION_ASSERTION_LD, entryDTO);
        session.setMaxInactiveInterval(CommonField.SESSION_TIMEOUT_MS);
        return true;
    }

}
