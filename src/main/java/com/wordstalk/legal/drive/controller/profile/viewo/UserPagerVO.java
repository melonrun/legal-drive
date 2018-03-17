package com.wordstalk.legal.drive.controller.profile.viewo;

import com.wordstalk.legal.drive.access.service.profile.model.UserShowDTO;

import java.util.List;

/**
 * Created by guoyong on 2018/1/27.
 */
public class UserPagerVO {

    private List<UserShowDTO> users;
    private long totalItem;
    private long totalPage;

    public List<UserShowDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserShowDTO> users) {
        this.users = users;
    }

    public long getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(long totalItem) {
        this.totalItem = totalItem;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }

    @Override
    public String toString() {
        return "UserPagerVO{" +
                "users=" + users +
                ", totalItem=" + totalItem +
                ", totalPage=" + totalPage +
                '}';
    }
}
