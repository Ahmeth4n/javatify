package com.magnakod.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;
import java.util.List;

@Document
@Entity
public class Settings {
    private String proxyList;
    private Date lastUpdateDate;
    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getProxyList() {
        return proxyList;
    }

    public void setProxyList(String proxyList) {
        this.proxyList = proxyList;
    }

}
