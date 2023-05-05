package com.magnakod.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Entity
public class Servers {
    @Id
    private String id;
    private String serverIp;
    private int serverPort;
    private SERVER_STATUS serverStatus;
    private Date createdDate;
    private Date lastUseDate;
    public enum SERVER_STATUS{
        IN_PROCESS,DISABLED,ACTIVE
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public SERVER_STATUS getServerStatus() {
        return serverStatus;
    }

    public void setServerStatus(SERVER_STATUS serverStatus) {
        this.serverStatus = serverStatus;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastUseDate() {
        return lastUseDate;
    }

    public void setLastUseDate(Date lastUseDate) {
        this.lastUseDate = lastUseDate;
    }
}
