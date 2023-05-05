package com.magnakod.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Document(collection = "server_reports")
@Entity
public class ServerReports {
    @Id
    private String id;
    private String serverReports;
    private Date date;
    private long totalSuccededCount;
    private long totalFailedCount;

    public String getServerReports() {
        return serverReports;
    }

    public void setServerReports(String serverReports) {
        this.serverReports = serverReports;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getTotalSuccededCount() {
        return totalSuccededCount;
    }

    public void setTotalSuccededCount(long totalSuccededCount) {
        this.totalSuccededCount = totalSuccededCount;
    }

    public long getTotalFailedCount() {
        return totalFailedCount;
    }

    public void setTotalFailedCount(long totalFailedCount) {
        this.totalFailedCount = totalFailedCount;
    }
}
