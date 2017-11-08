package com.lihao.netty.decode;


import org.msgpack.annotation.Message;

@Message
public class CallBackData {

    // 1 表示UserInfo  2 Student
    private int type ;

    private UserInfo userInfo;
    private Student student;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
