package com.giventech.giventechoauth.model;

public class Welcome {
    public String getMesssage() {
        return messsage;
    }

    public void setMesssage(String messsage) {
        this.messsage = messsage;
    }

    String messsage;
    public Welcome(String messsage) {
        this.messsage = messsage;
    }
}
