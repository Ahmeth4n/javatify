package com.magnakod.mapper;

import org.jetbrains.annotations.NotNull;

public class DataTableBody {
    @NotNull
    private Integer start;
    @NotNull
    private Integer draw;
    @NotNull
    private Integer length;

    @NotNull
    public Integer getStart() {
        return start;
    }

    public void setStart(@NotNull Integer start) {
        this.start = start;
    }

    @NotNull
    public Integer getDraw() {
        return draw;
    }

    public void setDraw(@NotNull Integer draw) {
        this.draw = draw;
    }

    @NotNull
    public Integer getLength() {
        return length;
    }

    public void setLength(@NotNull Integer length) {
        this.length = length;
    }
    @Override
    public String toString() {
        return "DataTableBody{" +
                "start=" + start +
                ", draw=" + draw +
                ", length=" + length +
                '}';
    }
}
