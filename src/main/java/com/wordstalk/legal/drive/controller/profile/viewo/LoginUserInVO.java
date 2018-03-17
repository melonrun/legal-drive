package com.wordstalk.legal.drive.controller.profile.viewo;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * Created by y on 2018/1/7.
 */
public class LoginUserInVO implements Serializable{
    private static final long serialVersionUID = 4746558445715373909L;

    @NotBlank(message = "userName must not be null.")
    private String userName;
    @NotBlank(message = "userPass must not be null.")
    private String userPass;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    @Override
    public String toString() {
        return "WUserVO{" +
                "userName='" + userName + '\'' +
                ", userPass='" + userPass + '\'' +
                '}';
    }
}
