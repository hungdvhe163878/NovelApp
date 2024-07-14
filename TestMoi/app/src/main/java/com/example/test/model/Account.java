package com.example.test.model;

public class Account {
    private int mId;
    private String mUsername;
    private String mPass;
    private String mEmail;
    private int mRole;

    public Account(String mUsername, String mPass, String mEmail, int mRole) {
        this.mUsername = mUsername;
        this.mPass = mPass;
        this.mEmail = mEmail;
        this.mRole = mRole;
    }

    public Account(String mUsername, String mEmail) {
        this.mUsername = mUsername;
        this.mEmail = mEmail;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmUsername() {
        return mUsername;
    }

    public void setmUsername(String mUsername) {
        this.mUsername = mUsername;
    }

    public String getmPass() {
        return mPass;
    }

    public void setmPass(String mPass) {
        this.mPass = mPass;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public int getmRole() {
        return mRole;
    }

    public void setmRole(int mRole) {
        this.mRole = mRole;
    }
}
