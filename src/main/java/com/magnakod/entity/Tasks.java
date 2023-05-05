package com.magnakod.entity;

import com.magnakod.Constants;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.Nullable;
import java.util.Date;

@Document
@Entity
public class Tasks {
    @Id
    private String id;
    private Constants.SPOTIFY_TASK_TYPE taskType;
    private String taskUrl;
    @Nullable
    private Date taskAddedDate;
    @Nullable
    private Date taskLastUpdateDate;
    private boolean taskStatus;
    private boolean taskProxyStatus;
    private long taskCount;
    private String taskExtras;
    public boolean isTaskProxyStatus() {
        return taskProxyStatus;
    }

    public void setTaskProxyStatus(boolean taskProxyStatus) {
        this.taskProxyStatus = taskProxyStatus;
    }

    public long getTaskCount() {
        return taskCount;
    }

    public void setTaskCount(long taskCount) {
        this.taskCount = taskCount;
    }

    public Constants.SPOTIFY_TASK_TYPE getTaskType() {
        return taskType;
    }

    public void setTaskType(Constants.SPOTIFY_TASK_TYPE taskType) {
        this.taskType = taskType;
    }

    public String getTaskUrl() {
        return taskUrl;
    }

    public void setTaskUrl(String taskUrl) {
        this.taskUrl = taskUrl;
    }

    public Date getTaskAddedDate() {
        return taskAddedDate;
    }

    public void setTaskAddedDate(Date taskAddedDate) {
        this.taskAddedDate = taskAddedDate;
    }

    public Date getTaskLastUpdateDate() {
        return taskLastUpdateDate;
    }

    public void setTaskLastUpdateDate(Date taskLastUpdateDate) {
        this.taskLastUpdateDate = taskLastUpdateDate;
    }

    public boolean isTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(boolean taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskExtras() {
        return taskExtras;
    }

    public void setTaskExtras(String taskExtras) {
        this.taskExtras = taskExtras;
    }
    public String getId() {
        return id;
    }
}
