package com.shay.incursio.internshipappv2.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 08/10/2017.
 */

public class Batch {
    @SerializedName("id")
    public String id;

    @SerializedName("batch")
    public String batch;

    @SerializedName("sem")
    public String sem;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getSem() {
        return sem;
    }

    public void setSem(String sem) {
        this.sem = sem;
    }
}
