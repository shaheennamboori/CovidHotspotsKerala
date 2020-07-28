package com.vidya.HotspotsKerala;

public class HotspotModel {
    private String district;
    private String lsgd;
    private String wards;

    public HotspotModel() {
    }

    public HotspotModel(String district, String lsgd, String wards) {
        this.district = district;
        this.lsgd = lsgd;
        this.wards = wards;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getLsgd() {
        return lsgd;
    }

    public void setLsgd(String lsgd) {
        this.lsgd = lsgd;
    }

    public String getWards() {
        return wards;
    }

    public void setWards(String wards) {
        this.wards = wards;
    }
}
