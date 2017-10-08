package com.shay.incursio.internshipappv2.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shay on 7/23/2017.
 */

public class Vacan {
    @SerializedName("id_vacancy")
    public String id_vacancy;

    @SerializedName("id_company")
    public String id_company;

    @SerializedName("position")
    public String position;

    @SerializedName("no_of_vacancy")
    public String no_of_vacancy;

    @SerializedName("description")
    public String description;

    @SerializedName("vcncy_ad_date")
    public String vcncy_ad_date;

    @SerializedName("status")
    public String status;

    @SerializedName("comp_name")
    public String comp_name;

    @SerializedName("comp_addrss")
    public String comp_addrss;

    @SerializedName("comp_postcode")
    public String comp_postcode;

    @SerializedName("comp_location")
    public String comp_location;

    @SerializedName("comp_state")
    public String comp_state;

    @SerializedName("comp_tel")
    public String comp_tel;

    @SerializedName("comp_email")
    public String comp_email;
}
