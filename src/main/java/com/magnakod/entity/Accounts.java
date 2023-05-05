package com.magnakod.entity;

import com.magnakod.emulator.builder.SpotifyHeaders;
import com.magnakod.emulator.session.SpotifySession;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;

@Document
@Entity
public class Accounts {
    @Id
    private String id;
    private String email;
    private String username;
    private String password;
    private boolean is_mail_verified;
    private boolean is_banned;
    private Date created_date;
    private Date last_login_date;
    private SpotifyHeaders headers;
    private SpotifySession session;
    @Column(name = "is_usage")
    private boolean usage;
    private Date lastUpdatedDate;

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public boolean isUsage() {
        return usage;
    }

    public void setUsage(boolean val) {
        usage = val;
    }

    public boolean isIs_banned() {
        return is_banned;
    }

    public void setIs_banned(boolean is_banned) {
        this.is_banned = is_banned;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isIs_mail_verified() {
        return is_mail_verified;
    }

    public void setIs_mail_verified(boolean is_mail_verified) {
        this.is_mail_verified = is_mail_verified;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public Date getLast_login_date() {
        return last_login_date;
    }

    public void setLast_login_date(Date last_login_date) {
        this.last_login_date = last_login_date;
    }

    public SpotifyHeaders getHeaders() {
        return headers;
    }

    public void setHeaders(SpotifyHeaders headers) {
        this.headers = headers;
    }

    public SpotifySession getSession() {
        return session;
    }

    public void setSession(SpotifySession session) {
        this.session = session;
    }
}
