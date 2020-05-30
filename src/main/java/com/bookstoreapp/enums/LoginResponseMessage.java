package com.bookstoreapp.enums;

import javax.mail.Message;

public enum LoginResponseMessage {

    RESPONSE_MESSAGE;


    public String responseMessage(boolean result)
    {
        if(result){
            return "Login Successfully";
        }
        return "Incorrect Password";
    }
}
