package com.company;

public class GameResult {
    private String name;
    private int  triesCount;
    private long userTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTriesCount(int triesCount) {
        this.triesCount = triesCount;
    }

    public void setUserTime(long userTime) {
        this.userTime = userTime;
    }

    public int getTriesCount() {
        return triesCount;
    }

    public long getUserTime(){
        return userTime;
    }
}
