package com.digitalmedia.users.model.dto;

public class NonAdminUser {
    private String userName;
    private String email;

    public NonAdminUser() {
        //No-args constructor
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "NonAdminUser{" +
                "userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
