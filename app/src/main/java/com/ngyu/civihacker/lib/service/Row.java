package com.ngyu.civihacker.lib.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Row {

    @SerializedName("APV_PERM_REG_MGT_NO")
    @Expose
    private String aPVPERMREGMGTNO;
    @SerializedName("CGG_CODE")
    @Expose
    private String cGGCODE;
    @SerializedName("APPL_CL_CODE")
    @Expose
    private String aPPLCLCODE;
    @SerializedName("BSN_CL_CODE")
    @Expose
    private String bSNCLCODE;
    @SerializedName("BSN_STATE_CODE")
    @Expose
    private String bSNSTATECODE;
    @SerializedName("RESI_ORGZ_NM")
    @Expose
    private String rESIORGZNM;
    @SerializedName("ADDR")
    @Expose
    private String aDDR;
    @SerializedName("TELNO")
    @Expose
    private String tELNO;
    @SerializedName("APV_PERM_YMD")
    @Expose
    private String aPVPERMYMD;
    @SerializedName("APV_PERM_CN")
    @Expose
    private String aPVPERMCN;
    @SerializedName("APV_PERM_WHY_CN")
    @Expose
    private String aPVPERMWHYCN;
    @SerializedName("NT_YMD")
    @Expose
    private String nTYMD;
    @SerializedName("JGONG_YMD")
    @Expose
    private String jGONGYMD;
    @SerializedName("CGG_APV_PERM_NO")
    @Expose
    private String cGGAPVPERMNO;
    @SerializedName("DELETE_YMD")
    @Expose
    private String dELETEYMD;
    @SerializedName("CANCEL_YMD")
    @Expose
    private String cANCELYMD;
    @SerializedName("FRST_REG_TS")
    @Expose
    private String fRSTREGTS;
    @SerializedName("LAST_MOD_TS")
    @Expose
    private String lASTMODTS;
    @SerializedName("TGT_SNO")
    @Expose
    private Double tGTSNO;
    @SerializedName("REPSNT_ORGZ_MGID")
    @Expose
    private Double rEPSNTORGZMGID;
    @SerializedName("ORGZ_MGT_ID")
    @Expose
    private Double oRGZMGTID;
    @SerializedName("PARENT_ORGZ_MGT_ID")
    @Expose
    private Double pARENTORGZMGTID;
    @SerializedName("ORGZ_NORG_SE_CODE")
    @Expose
    private String oRGZNORGSECODE;
    @SerializedName("POST_NO")
    @Expose
    private String pOSTNO;
    @SerializedName("REGN_CODE")
    @Expose
    private String rEGNCODE;
    @SerializedName("ADMDNG_CODE")
    @Expose
    private String aDMDNGCODE;
    @SerializedName("SAN")
    @Expose
    private String sAN;
    @SerializedName("BUNJI")
    @Expose
    private String bUNJI;
    @SerializedName("HO")
    @Expose
    private String hO;
    @SerializedName("SPEC_ADDR")
    @Expose
    private String sPECADDR;

    public String getAPVPERMREGMGTNO() {
        return aPVPERMREGMGTNO;
    }

    public void setAPVPERMREGMGTNO(String aPVPERMREGMGTNO) {
        this.aPVPERMREGMGTNO = aPVPERMREGMGTNO;
    }

    public String getCGGCODE() {
        return cGGCODE;
    }

    public void setCGGCODE(String cGGCODE) {
        this.cGGCODE = cGGCODE;
    }

    public String getAPPLCLCODE() {
        return aPPLCLCODE;
    }

    public void setAPPLCLCODE(String aPPLCLCODE) {
        this.aPPLCLCODE = aPPLCLCODE;
    }

    public String getBSNCLCODE() {
        return bSNCLCODE;
    }

    public void setBSNCLCODE(String bSNCLCODE) {
        this.bSNCLCODE = bSNCLCODE;
    }

    public String getBSNSTATECODE() {
        return bSNSTATECODE;
    }

    public void setBSNSTATECODE(String bSNSTATECODE) {
        this.bSNSTATECODE = bSNSTATECODE;
    }

    public String getRESIORGZNM() {
        return rESIORGZNM;
    }

    public void setRESIORGZNM(String rESIORGZNM) {
        this.rESIORGZNM = rESIORGZNM;
    }

    public String getADDR() {
        return aDDR;
    }

    public void setADDR(String aDDR) {
        this.aDDR = aDDR;
    }

    public String getTELNO() {
        return tELNO;
    }

    public void setTELNO(String tELNO) {
        this.tELNO = tELNO;
    }

    public String getAPVPERMYMD() {
        return aPVPERMYMD;
    }

    public void setAPVPERMYMD(String aPVPERMYMD) {
        this.aPVPERMYMD = aPVPERMYMD;
    }

    public String getAPVPERMCN() {
        return aPVPERMCN;
    }

    public void setAPVPERMCN(String aPVPERMCN) {
        this.aPVPERMCN = aPVPERMCN;
    }

    public String getAPVPERMWHYCN() {
        return aPVPERMWHYCN;
    }

    public void setAPVPERMWHYCN(String aPVPERMWHYCN) {
        this.aPVPERMWHYCN = aPVPERMWHYCN;
    }

    public String getNTYMD() {
        return nTYMD;
    }

    public void setNTYMD(String nTYMD) {
        this.nTYMD = nTYMD;
    }

    public String getJGONGYMD() {
        return jGONGYMD;
    }

    public void setJGONGYMD(String jGONGYMD) {
        this.jGONGYMD = jGONGYMD;
    }

    public String getCGGAPVPERMNO() {
        return cGGAPVPERMNO;
    }

    public void setCGGAPVPERMNO(String cGGAPVPERMNO) {
        this.cGGAPVPERMNO = cGGAPVPERMNO;
    }

    public String getDELETEYMD() {
        return dELETEYMD;
    }

    public void setDELETEYMD(String dELETEYMD) {
        this.dELETEYMD = dELETEYMD;
    }

    public String getCANCELYMD() {
        return cANCELYMD;
    }

    public void setCANCELYMD(String cANCELYMD) {
        this.cANCELYMD = cANCELYMD;
    }

    public String getFRSTREGTS() {
        return fRSTREGTS;
    }

    public void setFRSTREGTS(String fRSTREGTS) {
        this.fRSTREGTS = fRSTREGTS;
    }

    public String getLASTMODTS() {
        return lASTMODTS;
    }

    public void setLASTMODTS(String lASTMODTS) {
        this.lASTMODTS = lASTMODTS;
    }

    public Double getTGTSNO() {
        return tGTSNO;
    }

    public void setTGTSNO(Double tGTSNO) {
        this.tGTSNO = tGTSNO;
    }

    public Double getREPSNTORGZMGID() {
        return rEPSNTORGZMGID;
    }

    public void setREPSNTORGZMGID(Double rEPSNTORGZMGID) {
        this.rEPSNTORGZMGID = rEPSNTORGZMGID;
    }

    public Double getORGZMGTID() {
        return oRGZMGTID;
    }

    public void setORGZMGTID(Double oRGZMGTID) {
        this.oRGZMGTID = oRGZMGTID;
    }

    public Double getPARENTORGZMGTID() {
        return pARENTORGZMGTID;
    }

    public void setPARENTORGZMGTID(Double pARENTORGZMGTID) {
        this.pARENTORGZMGTID = pARENTORGZMGTID;
    }

    public String getORGZNORGSECODE() {
        return oRGZNORGSECODE;
    }

    public void setORGZNORGSECODE(String oRGZNORGSECODE) {
        this.oRGZNORGSECODE = oRGZNORGSECODE;
    }

    public String getPOSTNO() {
        return pOSTNO;
    }

    public void setPOSTNO(String pOSTNO) {
        this.pOSTNO = pOSTNO;
    }

    public String getREGNCODE() {
        return rEGNCODE;
    }

    public void setREGNCODE(String rEGNCODE) {
        this.rEGNCODE = rEGNCODE;
    }

    public String getADMDNGCODE() {
        return aDMDNGCODE;
    }

    public void setADMDNGCODE(String aDMDNGCODE) {
        this.aDMDNGCODE = aDMDNGCODE;
    }

    public String getSAN() {
        return sAN;
    }

    public void setSAN(String sAN) {
        this.sAN = sAN;
    }

    public String getBUNJI() {
        return bUNJI;
    }

    public void setBUNJI(String bUNJI) {
        this.bUNJI = bUNJI;
    }

    public String getHO() {
        return hO;
    }

    public void setHO(String hO) {
        this.hO = hO;
    }

    public String getSPECADDR() {
        return sPECADDR;
    }

    public void setSPECADDR(String sPECADDR) {
        this.sPECADDR = sPECADDR;
    }

}