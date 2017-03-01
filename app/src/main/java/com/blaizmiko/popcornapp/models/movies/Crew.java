package com.blaizmiko.popcornapp.models.movies;

import com.google.gson.annotations.SerializedName;

public class Crew {
    @SerializedName("credit_id")
    private String mCreditId;
    @SerializedName("department")
    private String mDepartment;
    @SerializedName("id")
    private int mId;
    @SerializedName("job")
    private String mJob;
    @SerializedName("name")
    private String mName;
    @SerializedName("profile_path")
    private String mProfilePath;

    public String getCreditId() {
        return mCreditId;
    }

    public void setCreditId(String creditId) {
        mCreditId = creditId;
    }

    public String getDepartment() {
        return mDepartment;
    }

    public void setDepartment(String department) {
        mDepartment = department;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getJob() {
        return mJob;
    }

    public void setJob(String job) {
        mJob = job;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getProfilePath() {
        return mProfilePath;
    }

    public void setProfilePath(String profilePath) {
        mProfilePath = profilePath;
    }
}
