package com.thestall.splash.ui.profile;

public class Profile {
    public String name;
    public String phone;
    public int age;
    public boolean isPrivate;

    public Profile() {
        this.name = "";
        this.phone = "";
        this.age = 0;
        this.isPrivate = false;
    }


    public Profile(String name, String phone, int age, boolean isPrivate) {
        this.name = name;
        this.phone = phone;
        this.age = age;
        this.isPrivate = isPrivate;
    }
}
