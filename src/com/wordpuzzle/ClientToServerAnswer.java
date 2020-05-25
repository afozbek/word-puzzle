package com.wordpuzzle;

import java.io.Serializable;

public class ClientToServerAnswer implements Serializable {

    private static final long serialVersionUID = -7974858570811025493L;
    private String nickName;
    private String answer;

    public ClientToServerAnswer(String nickName, String answer) {
        this.nickName = nickName;
        this.answer = answer;
    }


    @Override
    public String toString() {
        return "ClientToServerAnswer{" +
                "nickName='" + nickName + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }


}
