package com.guhun.locatorapplication07.data.model;

public class SiteModel {
    private int siteId;
    private String siteName;
    private String siteLocate;
    private int siteMapId;
    private String siteAttribute;
    private String siteMapName;

    @Override
    public String toString() {
        return "SiteModel{" +
                "siteId=" + siteId +
                ", siteName='" + siteName + '\'' +
                ", siteLocate='" + siteLocate + '\'' +
                ", siteMapId=" + siteMapId +
                ", siteAttribute='" + siteAttribute + '\'' +
                ", siteMapName='" + siteMapName + '\'' +
                '}';
    }

    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getSiteLocate() {
        return siteLocate;
    }

    public void setSiteLocate(String siteLocate) {
        this.siteLocate = siteLocate;
    }

    public int getSiteMapId() {
        return siteMapId;
    }

    public void setSiteMapId(int siteMapId) {
        this.siteMapId = siteMapId;
    }

    public String getSiteAttribute() {
        return siteAttribute;
    }

    public void setSiteAttribute(String siteAttribute) {
        this.siteAttribute = siteAttribute;
    }

    public String getSiteMapName() {
        return siteMapName;
    }

    public void setSiteMapName(String siteMapName) {
        this.siteMapName = siteMapName;
    }
}
