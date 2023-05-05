package com.magnakod.mapper;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

public class DataTableModel<T> implements Serializable {
    @SerializedName("data")
    private List<T> data;

    @SerializedName("recordsTotal")
    private long recordsTotal = 0L;

    @SerializedName("recordsFiltered")
    private long recordsFiltered = 0L;

    public DataTableModel(List<T> data, long recordsTotal ,long recordsFiltered) {
        this.data = data;
        this.recordsFiltered = recordsFiltered;
        this.recordsTotal = recordsTotal;
    }
}
