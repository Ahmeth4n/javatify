package com.magnakod.dto;

import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

public class AccountSaveModel {
    private final boolean status;
    private final String generatedUsername;
    private final Date created_date;

    @Autowired
    public AccountSaveModel(boolean status, String generatedUsername, Date created_date) {
        this.status = status;
        this.generatedUsername = generatedUsername;
        this.created_date = created_date;
    }
}
