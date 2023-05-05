package com.magnakod.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
public class LoginRequestModel {
    private final boolean status;
    private final String token;
    public LoginRequestModel(boolean status, String token) {
        this.status = status;
        this.token = token;
    }


}
