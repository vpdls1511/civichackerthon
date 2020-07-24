package com.ngyu.civihacker.lib.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RetrofitRepo {

    @SerializedName("MpDisinfectionInfo")
    @Expose
    private MpDisinfectionInfo mpDisinfectionInfo;

    public MpDisinfectionInfo getMpDisinfectionInfo() {
        return mpDisinfectionInfo;
    }

    public void setMpDisinfectionInfo(MpDisinfectionInfo mpDisinfectionInfo) {
        this.mpDisinfectionInfo = mpDisinfectionInfo;
    }

}
