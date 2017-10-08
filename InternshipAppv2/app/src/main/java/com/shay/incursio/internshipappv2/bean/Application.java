package com.shay.incursio.internshipappv2.bean;
import com.google.gson.annotations.SerializedName;
/**
 * Created by shay on 8/16/2017.
 */

public class Application {

    @SerializedName("id_student")
    public String id_student;

    @SerializedName("id_vacancy")
    public String id_vacancy;

    @SerializedName("apply_date")
    public String apply_date;

    @SerializedName("application_status")
    public String application_status;

    @SerializedName("id_company")
    public String id_company;

    @SerializedName("position")
    public String position;

    @SerializedName("description")
    public String description;

    @SerializedName("name")
    public String name;

    @SerializedName("COMP_NAME")
    public String COMP_NAME;
}
