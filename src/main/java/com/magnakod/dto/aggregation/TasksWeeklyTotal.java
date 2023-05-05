package com.magnakod.dto.aggregation;

import java.util.Date;

public class TasksWeeklyTotal {
    private Date taskAddedDate;
    private long totalTaskCount;

    public Date getTaskAddedDate() {
        return taskAddedDate;
    }

    public void setTaskAddedDate(Date taskAddedDate) {
        this.taskAddedDate = taskAddedDate;
    }

    public long getTotalTaskCount() {
        return totalTaskCount;
    }

    public void setTotalTaskCount(long totalTaskCount) {
        this.totalTaskCount = totalTaskCount;
    }
}