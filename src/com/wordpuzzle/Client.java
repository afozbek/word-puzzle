package com.wordpuzzle;

import java.io.Serializable;

public class Client implements Serializable {

    private static final long serialVersionUID = 1234L;
    private String nickName;
    private int score;


    public Client(String nickName, int score) {
        this.nickName = nickName;
        this.score = score;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "NickName: " + nickName + "Score: " + score;
    }
}
